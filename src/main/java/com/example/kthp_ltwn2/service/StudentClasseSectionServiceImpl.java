package com.example.kthp_ltwn2.service;

import com.example.kthp_ltwn2.dto.StudentClasseSectionRequest;
import com.example.kthp_ltwn2.dto.StudentClasseSectionResponse;
import com.example.kthp_ltwn2.entity.StudentClasseSection;
import com.example.kthp_ltwn2.repository.StudentClasseSectionRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class StudentClasseSectionServiceImpl implements StudentClasseSectionService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StudentClasseSectionServiceImpl.class);



    private final StudentClasseSectionRepository repository;
    private final com.example.kthp_ltwn2.repository.StudentClassRepository studentClassRepository;
    private final com.example.kthp_ltwn2.repository.StudentRepository studentRepository;
    private final com.example.kthp_ltwn2.repository.StudentStatusRepository studentStatusRepository;

    public StudentClasseSectionServiceImpl(StudentClasseSectionRepository repository, 
                                           com.example.kthp_ltwn2.repository.StudentClassRepository studentClassRepository,
                                           com.example.kthp_ltwn2.repository.StudentRepository studentRepository,
                                           com.example.kthp_ltwn2.repository.StudentStatusRepository studentStatusRepository) {
        this.repository = repository;
        this.studentClassRepository = studentClassRepository;
        this.studentRepository = studentRepository;
        this.studentStatusRepository = studentStatusRepository;
    }



    @Override
    public Page<StudentClasseSectionResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return repository.findByIsActiveTrue(pageable).map(this::toResponse);
    }

    @Override
    public StudentClasseSectionResponse getById(UUID id) {
        return repository.findByIdAndIsActiveTrue(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu phân lớp với ID: " + id));
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public StudentClasseSectionResponse create(StudentClasseSectionRequest request) {
        StudentClasseSection saved = repository.save(toEntity(request));
        syncStudentData(saved.getStudentId(), saved.getStudentClasseId(), saved.getStatus());
        log.info("Created StudentClasseSection with ID: {}", saved.getId());
        return toResponse(saved);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public StudentClasseSectionResponse update(UUID id, StudentClasseSectionRequest request) {
        StudentClasseSection entity = repository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu phân lớp với ID: " + id));
        applyRequest(entity, request);
        StudentClasseSection saved = repository.save(entity);
        syncStudentData(saved.getStudentId(), saved.getStudentClasseId(), saved.getStatus());
        return toResponse(saved);
    }

    @Override
    public void softDelete(UUID id) {
        StudentClasseSection entity = repository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu phân lớp với ID: " + id));
        entity.setIsActive(false);
        entity.setDeletedAt(LocalDateTime.now());
        repository.save(entity);
        log.info("Soft-deleted StudentClasseSection with ID: {}", id);
    }

    // ── Mappers ────────────────────────────────────────────────────────────────

    private void applyRequest(StudentClasseSection entity, StudentClasseSectionRequest request) {
        entity.setStudentId(request.getStudentId());
        entity.setStudentClasseId(request.getStudentClasseId());
        entity.setStatus(request.getStatus());
        entity.setNote(request.getNote());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
    }

    private StudentClasseSection toEntity(StudentClasseSectionRequest request) {
        StudentClasseSection entity = new StudentClasseSection();
        applyRequest(entity, request);
        return entity;
    }

    private StudentClasseSectionResponse toResponse(StudentClasseSection s) {
        return StudentClasseSectionResponse.builder()
                .id(s.getId())
                .studentId(s.getStudentId())
                .studentCode(s.getStudentId() != null ? 
                    studentRepository.findById(s.getStudentId())
                    .map(com.example.kthp_ltwn2.entity.Student::getCode).orElse(null) : null)
                .studentFullName(s.getStudentId() != null ? 
                    studentRepository.findById(s.getStudentId())
                    .map(com.example.kthp_ltwn2.entity.Student::getFullName).orElse(null) : null)
                .studentClasseId(s.getStudentClasseId())
                .studentClasseCode(s.getStudentClasseId() != null ? 
                    studentClassRepository.findById(s.getStudentClasseId())
                    .map(com.example.kthp_ltwn2.entity.StudentClass::getCode).orElse(null) : null)
                .status(s.getStatus())
                .note(s.getNote())
                .startDate(s.getStartDate())
                .endDate(s.getEndDate())
                .createdAt(s.getCreatedAt())
                .updatedAt(s.getUpdatedAt())
                .isActive(s.getIsActive())
                .build();
    }

    private void syncStudentData(UUID studentId, UUID studentClassId, String status) {
        if (studentId == null) return;
        
        // 1. Update Student
        studentRepository.findById(studentId).ifPresent(s -> {
            s.setStudentClasseId(studentClassId);
            s.setStatus(status);
            studentRepository.save(s);
            log.info("Synced Student ID: {} with Status: {} and Class: {}", studentId, status, studentClassId);
        });

        // 2. Add to StudentStatus history
        com.example.kthp_ltwn2.entity.StudentStatus ss = new com.example.kthp_ltwn2.entity.StudentStatus();
        ss.setStudentId(studentId);
        ss.setStatusCode(status);
        ss.setStatusName(status);
        ss.setStartDate(java.time.LocalDate.now());
        ss.setDescription("Cập nhật tự động từ Phân lớp học phần");
        studentStatusRepository.save(ss);
        log.info("Created StudentStatus history record for Student ID: {}", studentId);
    }
}
