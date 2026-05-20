package com.example.kthp_ltwn2.service;

import com.example.kthp_ltwn2.dto.StudentClassRequest;
import com.example.kthp_ltwn2.dto.StudentClassResponse;
import com.example.kthp_ltwn2.entity.StudentClass;
import com.example.kthp_ltwn2.repository.StudentClassRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class StudentClassServiceImpl implements StudentClassService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StudentClassServiceImpl.class);



    private final StudentClassRepository repo;
    private final com.example.kthp_ltwn2.repository.DepartmentRepository departmentRepo;
    private final com.example.kthp_ltwn2.repository.MajorRepository majorRepo;
    private final com.example.kthp_ltwn2.repository.AcademicYearRepository academicYearRepo;
    private final com.example.kthp_ltwn2.repository.TrainingProgramRepository trainingProgramRepo;

    public StudentClassServiceImpl(StudentClassRepository repo, 
                                   com.example.kthp_ltwn2.repository.DepartmentRepository departmentRepo, 
                                   com.example.kthp_ltwn2.repository.MajorRepository majorRepo,
                                   com.example.kthp_ltwn2.repository.AcademicYearRepository academicYearRepo,
                                   com.example.kthp_ltwn2.repository.TrainingProgramRepository trainingProgramRepo) {
        this.repo = repo;
        this.departmentRepo = departmentRepo;
        this.majorRepo = majorRepo;
        this.academicYearRepo = academicYearRepo;
        this.trainingProgramRepo = trainingProgramRepo;
    }



    @Override
    public Page<StudentClassResponse> getAll(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<StudentClass> data = (keyword != null && !keyword.isBlank())
                ? repo.searchActive(keyword.trim(), pageable)
                : repo.findByIsActiveTrue(pageable);
        return data.map(this::toResponse);
    }

    @Override
    public StudentClassResponse getById(UUID id) {
        return repo.findByIdAndIsActiveTrue(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lớp với ID: " + id));
    }

    @Override
    public StudentClassResponse create(StudentClassRequest request) {
        if (repo.existsByCode(request.getCode())) {
            throw new RuntimeException("Mã lớp '" + request.getCode() + "' đã tồn tại");
        }
        StudentClass saved = repo.save(toEntity(request));
        log.info("Created class: {}", saved.getCode());
        return toResponse(saved);
    }

    @Override
    public StudentClassResponse update(UUID id, StudentClassRequest request) {
        StudentClass sc = repo.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lớp với ID: " + id));
        if (repo.existsByCodeAndIdNot(request.getCode(), id)) {
            throw new RuntimeException("Mã lớp '" + request.getCode() + "' đã tồn tại");
        }
        applyRequest(sc, request);
        return toResponse(repo.save(sc));
    }

    @Override
    public void softDelete(UUID id) {
        StudentClass sc = repo.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lớp với ID: " + id));
        sc.setIsActive(false);
        sc.setDeletedAt(LocalDateTime.now());
        repo.save(sc);
        log.info("Soft-deleted class: {}", id);
    }

    private void applyRequest(StudentClass sc, StudentClassRequest r) {
        sc.setCode(r.getCode());
        sc.setName(r.getName());
        sc.setAcademicYearId(r.getAcademicYearId());
        sc.setDepartmentId(r.getDepartmentId());
        sc.setMajorId(r.getMajorId());
        sc.setTrainingProgramId(r.getTrainingProgramId());
        sc.setEmployeeId(r.getEmployeeId());
    }

    private StudentClass toEntity(StudentClassRequest r) {
        StudentClass sc = new StudentClass();
        applyRequest(sc, r);
        return sc;
    }

    private StudentClassResponse toResponse(StudentClass sc) {
        String deptName = null;
        if (sc.getDepartmentId() != null) {
            deptName = departmentRepo.findById(sc.getDepartmentId())
                    .map(d -> d.getName())
                    .orElse(null);
        }

        String majorName = null;
        if (sc.getMajorId() != null) {
            majorName = majorRepo.findById(sc.getMajorId())
                    .map(m -> m.getName())
                    .orElse(null);
        }

        String ayCode = null;
        if (sc.getAcademicYearId() != null) {
            ayCode = academicYearRepo.findById(sc.getAcademicYearId())
                    .map(ay -> ay.getCode())
                    .orElse(null);
        }

        String tpName = null;
        if (sc.getTrainingProgramId() != null) {
            tpName = trainingProgramRepo.findById(sc.getTrainingProgramId())
                    .map(tp -> tp.getName())
                    .orElse(null);
        }

        return StudentClassResponse.builder()
                .id(sc.getId())
                .code(sc.getCode())
                .name(sc.getName())
                .academicYearId(sc.getAcademicYearId())
                .academicYearCode(ayCode)
                .departmentId(sc.getDepartmentId())
                .departmentName(deptName)
                .majorId(sc.getMajorId())
                .majorName(majorName)
                .trainingProgramId(sc.getTrainingProgramId())
                .trainingProgramName(tpName)
                .employeeId(sc.getEmployeeId())
                .createdAt(sc.getCreatedAt())
                .updatedAt(sc.getUpdatedAt())
                .isActive(sc.getIsActive())
                .build();
    }
}
