package com.example.kthp_ltwn2.service;

import com.example.kthp_ltwn2.dto.StudentRequest;
import com.example.kthp_ltwn2.dto.StudentResponse;
import com.example.kthp_ltwn2.entity.Student;
import com.example.kthp_ltwn2.repository.StudentRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StudentServiceImpl.class);



    private final StudentRepository studentRepository;
    private final com.example.kthp_ltwn2.repository.StudentClassRepository studentClassRepository;
    private final com.example.kthp_ltwn2.repository.AcademicYearRepository academicYearRepository;
    private final com.example.kthp_ltwn2.repository.DepartmentRepository departmentRepository;
    private final com.example.kthp_ltwn2.repository.MajorRepository majorRepository;
    private final com.example.kthp_ltwn2.repository.TrainingProgramRepository trainingProgramRepository;
    private final com.example.kthp_ltwn2.repository.StudentStatusRepository studentStatusRepository;
    private final com.example.kthp_ltwn2.repository.StudentClasseSectionRepository studentClasseSectionRepository;

    public StudentServiceImpl(StudentRepository studentRepository, 
                              com.example.kthp_ltwn2.repository.StudentClassRepository studentClassRepository,
                              com.example.kthp_ltwn2.repository.AcademicYearRepository academicYearRepository,
                              com.example.kthp_ltwn2.repository.DepartmentRepository departmentRepository,
                              com.example.kthp_ltwn2.repository.MajorRepository majorRepository,
                              com.example.kthp_ltwn2.repository.TrainingProgramRepository trainingProgramRepository,
                              com.example.kthp_ltwn2.repository.StudentStatusRepository studentStatusRepository,
                              com.example.kthp_ltwn2.repository.StudentClasseSectionRepository studentClasseSectionRepository) {
        this.studentRepository = studentRepository;
        this.studentClassRepository = studentClassRepository;
        this.academicYearRepository = academicYearRepository;
        this.departmentRepository = departmentRepository;
        this.majorRepository = majorRepository;
        this.trainingProgramRepository = trainingProgramRepository;
        this.studentStatusRepository = studentStatusRepository;
        this.studentClasseSectionRepository = studentClasseSectionRepository;
    }



    @Override
    public Page<StudentResponse> getAll(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Pageable nativePageable = PageRequest.of(page, size); // native query can't use JPQL sort field names
        Page<Student> students = (keyword != null && !keyword.isBlank())
                ? studentRepository.searchActive(keyword.trim(), nativePageable)
                : studentRepository.findByIsActiveTrue(pageable);
        return students.map(this::toResponse);
    }

    @Override
    public StudentResponse getById(UUID id) {
        return studentRepository.findByIdAndIsActiveTrue(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên với ID: " + id));
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public StudentResponse create(StudentRequest request) {
        if (studentRepository.existsByCode(request.getCode())) {
            throw new RuntimeException("Mã sinh viên '" + request.getCode() + "' đã tồn tại");
        }
        Student saved = studentRepository.save(toEntity(request));
        syncToModules(saved);
        log.info("Created student: {}", saved.getCode());
        return toResponse(saved);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public StudentResponse update(UUID id, StudentRequest request) {
        Student student = studentRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên với ID: " + id));
        if (studentRepository.existsByCodeAndIdNot(request.getCode(), id)) {
            throw new RuntimeException("Mã sinh viên '" + request.getCode() + "' đã tồn tại");
        }
        applyRequest(student, request);
        Student saved = studentRepository.save(student);
        syncToModules(saved);
        log.info("Updated student: {}", saved.getCode());
        return toResponse(saved);
    }

    @Override
    public void softDelete(UUID id) {
        Student student = studentRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên với ID: " + id));
        student.setIsActive(false);
        student.setDeletedAt(LocalDateTime.now());
        studentRepository.save(student);
        log.info("Soft-deleted student: {}", id);
    }

    // ── Mappers ────────────────────────────────────────────────────────────────

    private void applyRequest(Student s, StudentRequest r) {
        s.setCode(r.getCode());
        s.setFullName(r.getFullName());
        s.setDateOfBirth(r.getDateOfBirth());
        s.setGender(r.getGender());
        s.setPersonalIdentificationNumber(r.getPersonalIdentificationNumber());
        s.setDateOfIssue(r.getDateOfIssue());
        s.setCardPlace(r.getCardPlace());
        s.setAddress(r.getAddress());
        s.setCurrentAddress(r.getCurrentAddress());
        s.setStatus(r.getStatus());
        s.setAdmissionYear(r.getAdmissionYear());
        s.setDepartmentId(r.getDepartmentId());
        s.setMajorId(r.getMajorId());
        s.setTrainingProgramId(r.getTrainingProgramId());
        s.setStudentClasseId(r.getStudentClasseId());
        s.setAcademicYearYear(r.getAcademicYearYear());
    }

    private Student toEntity(StudentRequest r) {
        Student s = new Student();
        applyRequest(s, r);
        return s;
    }

    private StudentResponse toResponse(Student s) {
        return StudentResponse.builder()
                .id(s.getId())
                .code(s.getCode())
                .fullName(s.getFullName())
                .dateOfBirth(s.getDateOfBirth())
                .gender(s.getGender())
                .personalIdentificationNumber(s.getPersonalIdentificationNumber())
                .dateOfIssue(s.getDateOfIssue())
                .cardPlace(s.getCardPlace())
                .address(s.getAddress())
                .currentAddress(s.getCurrentAddress())
                .status(s.getStatus())
                .admissionYear(s.getAdmissionYear())
                .departmentId(s.getDepartmentId())
                .departmentName(s.getDepartmentId() != null ? 
                    departmentRepository.findById(s.getDepartmentId()).map(com.example.kthp_ltwn2.entity.Department::getName).orElse(null) : null)
                .majorId(s.getMajorId())
                .majorName(s.getMajorId() != null ? 
                    majorRepository.findById(s.getMajorId()).map(com.example.kthp_ltwn2.entity.Major::getName).orElse(null) : null)
                .trainingProgramId(s.getTrainingProgramId())
                .trainingProgramName(s.getTrainingProgramId() != null ? 
                    trainingProgramRepository.findById(s.getTrainingProgramId()).map(com.example.kthp_ltwn2.entity.TrainingProgram::getName).orElse(null) : null)
                .studentClasseId(s.getStudentClasseId())
                .studentClasseCode(s.getStudentClasseId() != null ? 
                    studentClassRepository.findById(s.getStudentClasseId())
                    .map(com.example.kthp_ltwn2.entity.StudentClass::getCode).orElse(null) : null)
                .academicYearYear(s.getAcademicYearYear())
                .academicYearCode(s.getAcademicYearYear() != null ? 
                    academicYearRepository.findById(s.getAcademicYearYear()).map(com.example.kthp_ltwn2.entity.AcademicYear::getCode).orElse(null) : null)
                .createdAt(s.getCreatedAt())
                .updatedAt(s.getUpdatedAt())
                .isActive(s.getIsActive())
                .build();
    }

    private void syncToModules(Student s) {
        // 1. Sync to StudentStatus (History)
        com.example.kthp_ltwn2.entity.StudentStatus ss = new com.example.kthp_ltwn2.entity.StudentStatus();
        ss.setStudentId(s.getId());
        ss.setStatusCode(s.getStatus());
        ss.setStatusName(s.getStatus());
        ss.setStartDate(java.time.LocalDate.now());
        ss.setDescription("Cập nhật từ hồ sơ Sinh viên");
        studentStatusRepository.save(ss);

        // 2. Sync to Phân lớp HP (Latest section)
        java.util.List<com.example.kthp_ltwn2.entity.StudentClasseSection> sections = 
            studentClasseSectionRepository.findByStudentIdAndIsActiveTrue(s.getId());
        if (!sections.isEmpty()) {
            // Update the most recent section or all? Let's update all active sections for this student
            for (com.example.kthp_ltwn2.entity.StudentClasseSection sec : sections) {
                sec.setStatus(s.getStatus());
                sec.setStudentClasseId(s.getStudentClasseId());
            }
            studentClasseSectionRepository.saveAll(sections);
        }
    }
}
