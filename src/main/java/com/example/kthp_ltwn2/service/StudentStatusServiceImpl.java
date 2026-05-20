package com.example.kthp_ltwn2.service;

import com.example.kthp_ltwn2.dto.StudentStatusRequest;
import com.example.kthp_ltwn2.dto.StudentStatusResponse;
import com.example.kthp_ltwn2.entity.StudentStatus;
import com.example.kthp_ltwn2.repository.StudentStatusRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class StudentStatusServiceImpl implements StudentStatusService {

    private final StudentStatusRepository repository;
    private final com.example.kthp_ltwn2.repository.StudentRepository studentRepository;
    private final com.example.kthp_ltwn2.repository.StudentClasseSectionRepository studentClasseSectionRepository;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StudentStatusServiceImpl.class);

    public StudentStatusServiceImpl(StudentStatusRepository repository, 
                                    com.example.kthp_ltwn2.repository.StudentRepository studentRepository,
                                    com.example.kthp_ltwn2.repository.StudentClasseSectionRepository studentClasseSectionRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.studentClasseSectionRepository = studentClasseSectionRepository;
    }

    @Override
    public Page<StudentStatusResponse> getAll(int page, int size) {
        return repository.findByIsActiveTrue(PageRequest.of(page, size, Sort.by("createdAt").descending()))
                .map(this::toResponse);
    }

    @Override
    public StudentStatusResponse getById(UUID id) {
        return repository.findByIdAndIsActiveTrue(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy trạng thái với ID: " + id));
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public StudentStatusResponse create(StudentStatusRequest request) {
        StudentStatus entity = new StudentStatus();
        mapRequestToEntity(request, entity);
        StudentStatus saved = repository.save(entity);
        
        // Sync back to Student
        syncStatusToStudent(saved.getStudentId(), saved.getStatusCode());
        
        log.info("Created StudentStatus for student ID: {}", request.getStudentId());
        return toResponse(saved);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public StudentStatusResponse update(UUID id, StudentStatusRequest request) {
        StudentStatus entity = repository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy trạng thái với ID: " + id));
        mapRequestToEntity(request, entity);
        StudentStatus saved = repository.save(entity);
        
        // Sync back to Student
        syncStatusToStudent(saved.getStudentId(), saved.getStatusCode());
        
        return toResponse(saved);
    }

    @Override
    public void softDelete(UUID id) {
        StudentStatus entity = repository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy trạng thái với ID: " + id));
        entity.setIsActive(false);
        entity.setDeletedAt(LocalDateTime.now());
        repository.save(entity);
        log.info("Soft-deleted StudentStatus with ID: {}", id);
    }

    private void mapRequestToEntity(StudentStatusRequest req, StudentStatus entity) {
        entity.setStudentId(req.getStudentId());
        entity.setStatusCode(req.getStatusCode());
        entity.setStatusName(req.getStatusName());
        entity.setStartDate(req.getStartDate());
        entity.setEndDate(req.getEndDate());
        entity.setDescription(req.getDescription());
        entity.setReason(req.getReason());
    }

    private StudentStatusResponse toResponse(StudentStatus s) {
        return StudentStatusResponse.builder()
                .id(s.getId())
                .studentId(s.getStudentId())
                .studentCode(s.getStudentId() != null ? 
                    studentRepository.findById(s.getStudentId())
                    .map(com.example.kthp_ltwn2.entity.Student::getCode).orElse(null) : null)
                .studentFullName(s.getStudentId() != null ? 
                    studentRepository.findById(s.getStudentId())
                    .map(com.example.kthp_ltwn2.entity.Student::getFullName).orElse(null) : null)
                .statusCode(s.getStatusCode())
                .statusName(s.getStatusName())
                .startDate(s.getStartDate())
                .endDate(s.getEndDate())
                .description(s.getDescription())
                .reason(s.getReason())
                .createdAt(s.getCreatedAt())
                .updatedAt(s.getUpdatedAt())
                .isActive(s.getIsActive())
                .build();
    }

    private void syncStatusToStudent(UUID studentId, String status) {
        if (studentId == null) return;
        
        // 1. Update Student
        studentRepository.findById(studentId).ifPresent(s -> {
            s.setStatus(status);
            studentRepository.save(s);
            log.info("Synced Status: {} to Student ID: {}", status, studentId);
            
            // 2. Update all active Class Sections for this student
            java.util.List<com.example.kthp_ltwn2.entity.StudentClasseSection> sections = 
                studentClasseSectionRepository.findByStudentIdAndIsActiveTrue(studentId);
            for (com.example.kthp_ltwn2.entity.StudentClasseSection sec : sections) {
                sec.setStatus(status);
                sec.setStudentClasseId(s.getStudentClasseId());
            }
            studentClasseSectionRepository.saveAll(sections);
            log.info("Synced Status: {} to {} Class Sections for Student ID: {}", status, sections.size(), studentId);
        });
    }
}
