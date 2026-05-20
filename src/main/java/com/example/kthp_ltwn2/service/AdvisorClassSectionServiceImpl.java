package com.example.kthp_ltwn2.service;

import com.example.kthp_ltwn2.dto.AdvisorClassSectionRequest;
import com.example.kthp_ltwn2.dto.AdvisorClassSectionResponse;
import com.example.kthp_ltwn2.entity.AdvisorClassSection;
import com.example.kthp_ltwn2.repository.AdvisorClassSectionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AdvisorClassSectionServiceImpl implements AdvisorClassSectionService {

    private final AdvisorClassSectionRepository repository;
    private final com.example.kthp_ltwn2.repository.StudentClassRepository studentClassRepository;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AdvisorClassSectionServiceImpl.class);

    public AdvisorClassSectionServiceImpl(AdvisorClassSectionRepository repository, com.example.kthp_ltwn2.repository.StudentClassRepository studentClassRepository) {
        this.repository = repository;
        this.studentClassRepository = studentClassRepository;
    }

    @Override
    public Page<AdvisorClassSectionResponse> getAll(int page, int size) {
        return repository.findByIsActiveTrue(PageRequest.of(page, size, Sort.by("createdAt").descending()))
                .map(this::toResponse);
    }

    @Override
    public AdvisorClassSectionResponse getById(UUID id) {
        return repository.findByIdAndIsActiveTrue(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phân công cố vấn với ID: " + id));
    }

    @Override
    public AdvisorClassSectionResponse create(AdvisorClassSectionRequest request) {
        AdvisorClassSection entity = new AdvisorClassSection();
        mapRequestToEntity(request, entity);
        AdvisorClassSection saved = repository.save(entity);
        log.info("Created AdvisorClassSection for class ID: {}", request.getStudentClasseId());
        return toResponse(saved);
    }

    @Override
    public AdvisorClassSectionResponse update(UUID id, AdvisorClassSectionRequest request) {
        AdvisorClassSection entity = repository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phân công cố vấn với ID: " + id));
        mapRequestToEntity(request, entity);
        return toResponse(repository.save(entity));
    }

    @Override
    public void softDelete(UUID id) {
        AdvisorClassSection entity = repository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phân công cố vấn với ID: " + id));
        entity.setIsActive(false);
        entity.setDeletedAt(LocalDateTime.now());
        repository.save(entity);
        log.info("Soft-deleted AdvisorClassSection with ID: {}", id);
    }

    private void mapRequestToEntity(AdvisorClassSectionRequest req, AdvisorClassSection entity) {
        entity.setStudentClasseId(req.getStudentClasseId());
        entity.setEmployeeId(req.getEmployeeId());
        entity.setStartDate(req.getStartDate());
        entity.setEndDate(req.getEndDate());
        entity.setDescription(req.getDescription());
        entity.setReason(req.getReason());
    }

    private AdvisorClassSectionResponse toResponse(AdvisorClassSection s) {
        return AdvisorClassSectionResponse.builder()
                .id(s.getId())
                .studentClasseId(s.getStudentClasseId())
                .studentClasseCode(s.getStudentClasseId() != null ? 
                    studentClassRepository.findById(s.getStudentClasseId())
                    .map(com.example.kthp_ltwn2.entity.StudentClass::getCode).orElse(null) : null)
                .employeeId(s.getEmployeeId())
                .employeeName(s.getEmployeeId() != null ? 
                    (s.getEmployeeId().trim().toUpperCase().equals("GV001") ? "Giảng viên Nguyễn Hoàng Anh" :
                     s.getEmployeeId().trim().toUpperCase().equals("GV002") ? "Giảng viên Lê Thị Hoài An" :
                     s.getEmployeeId().trim().toUpperCase().equals("GV003") ? "Giảng viên Phạm Minh Trí" :
                     s.getEmployeeId().trim().toUpperCase().equals("GV004") ? "Giảng viên Trần Thị Tuyết" :
                     s.getEmployeeId().trim().toUpperCase().equals("GV005") ? "Giảng viên Vũ Anh Tuấn" :
                     s.getEmployeeId().trim().toUpperCase().equals("GV006") ? "Giảng viên Đỗ Hoàng Long" :
                     s.getEmployeeId().trim().toUpperCase().equals("GV007") ? "Giảng viên Bùi Thị Ngọc" :
                     s.getEmployeeId().trim().toUpperCase().equals("GV008") ? "Giảng viên Phan Thanh Sơn" :
                     s.getEmployeeId().trim().toUpperCase().equals("NGUYỄN VĂN SINH") ? "Giảng viên Nguyễn Văn Sinh" :
                     s.getEmployeeId().trim().startsWith("GV") ? "Giảng viên " + s.getEmployeeId() : s.getEmployeeId()) : "Chưa phân công") 
                .startDate(s.getStartDate())
                .endDate(s.getEndDate())
                .description(s.getDescription())
                .reason(s.getReason())
                .createdAt(s.getCreatedAt())
                .updatedAt(s.getUpdatedAt())
                .isActive(s.getIsActive())
                .build();
    }
}
