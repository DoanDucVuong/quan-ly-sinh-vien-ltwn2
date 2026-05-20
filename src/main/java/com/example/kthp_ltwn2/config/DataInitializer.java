package com.example.kthp_ltwn2.config;

import com.example.kthp_ltwn2.entity.Department;
import com.example.kthp_ltwn2.entity.Major;
import com.example.kthp_ltwn2.entity.Student;
import com.example.kthp_ltwn2.entity.StudentClass;
import com.example.kthp_ltwn2.entity.StudentStatus;
import com.example.kthp_ltwn2.repository.DepartmentRepository;
import com.example.kthp_ltwn2.repository.MajorRepository;
import com.example.kthp_ltwn2.repository.StudentClassRepository;
import com.example.kthp_ltwn2.repository.StudentRepository;
import com.example.kthp_ltwn2.repository.StudentStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DepartmentRepository departmentRepo;
    private final MajorRepository majorRepo;
    private final StudentClassRepository studentClassRepo;
    private final StudentRepository studentRepository;
    private final StudentStatusRepository studentStatusRepo;
    private final JdbcTemplate jdbcTemplate;

    public DataInitializer(DepartmentRepository departmentRepo, 
                           MajorRepository majorRepo,
                           StudentClassRepository studentClassRepo,
                           StudentRepository studentRepository,
                           StudentStatusRepository studentStatusRepo,
                           JdbcTemplate jdbcTemplate) {
        this.departmentRepo = departmentRepo;
        this.majorRepo = majorRepo;
        this.studentClassRepo = studentClassRepo;
        this.studentRepository = studentRepository;
        this.studentStatusRepo = studentStatusRepo;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        updateDepartments();
        updateMajors();
        updateClasses();
        dropStatusConstraint();
        updateStudentStatuses();
    }

    private void updateDepartments() {
        List<Department> departments = departmentRepo.findAll();
        for (Department d : departments) {
            String code = d.getCode().toUpperCase();
            if (code.contains("NN") || code.contains("FL")) {
                d.setName("Khoa Ngoại ngữ tin học");
            } else if (code.contains("QTKD") || code.contains("BA")) {
                d.setName("Khoa Thương mại điện tử");
            } else if (code.contains("KT") || code.contains("ACC")) {
                d.setName("Khoa Hệ thống thông tin quản lý");
            } else if (code.contains("CNTT") || code.contains("IT")) {
                d.setName("Khoa Công nghệ thông tin");
            } else if (code.contains("HTTT") || code.contains("IS")) {
                d.setName("Bộ môn Hệ thống thông tin");
            }
        }
        departmentRepo.saveAll(departments);
    }

    private void updateMajors() {
        List<Major> majors = majorRepo.findAll();
        for (Major m : majors) {
            String code = m.getCode().toUpperCase();
            if (code.contains("NN") || code.contains("FL")) {
                m.setName("Tiếng Anh chuyên ngành IT");
            } else if (code.contains("QTKD") || code.contains("BA")) {
                m.setName("Thương mại điện tử");
            } else if (code.contains("KT") || code.contains("ACC")) {
                m.setName("Hệ thống thông tin quản lý");
            } else if (code.contains("CNTT") || code.contains("IT")) {
                m.setName("Công nghệ thông tin");
            } else if (code.contains("HTTT") || code.contains("IS")) {
                m.setName("Hệ thống thông tin");
            } else if (code.contains("KTPM") || code.contains("SE")) {
                m.setName("Kỹ thuật phần mềm");
            }
        }
        majorRepo.saveAll(majors);
    }

    private void updateClasses() {
        List<StudentClass> classes = studentClassRepo.findAll();
        for (StudentClass sc : classes) {
            String name = sc.getName();
            if (name.contains("Tiếng Anh")) {
                sc.setName(name.replace("Tiếng Anh", "Lập trình Java"));
            } else if (name.contains("Quản trị kinh doanh")) {
                sc.setName(name.replace("Quản trị kinh doanh", "Kỹ thuật phần mềm"));
            } else if (name.contains("Kế toán")) {
                sc.setName(name.replace("Kế toán", "An toàn thông tin"));
            }
        }
        studentClassRepo.saveAll(classes);
    }

    private void updateStudentStatuses() {
        List<Student> students = studentRepository.findAll();
        for (Student s : students) {
            String st = s.getStatus();
            if (st == null) continue;
            
            if (st.equalsIgnoreCase("studying")) {
                s.setStatus("Đang học");
            } else if (st.equalsIgnoreCase("graduated")) {
                s.setStatus("Tốt nghiệp");
            } else if (st.equalsIgnoreCase("dropped")) {
                s.setStatus("Thôi học");
            } else if (st.equalsIgnoreCase("suspended")) {
                s.setStatus("Bảo lưu");
            }
        }
        studentRepository.saveAll(students);

        // Also update StudentStatus records
        List<StudentStatus> statusRecords = studentStatusRepo.findAll();
        for (StudentStatus ss : statusRecords) {
            String code = ss.getStatusCode();
            if (code == null) continue;

            if (code.equalsIgnoreCase("studying")) {
                ss.setStatusCode("Đang học");
                ss.setStatusName("Đang học");
            } else if (code.equalsIgnoreCase("graduated")) {
                ss.setStatusCode("Tốt nghiệp");
                ss.setStatusName("Tốt nghiệp");
            } else if (code.equalsIgnoreCase("dropped")) {
                ss.setStatusCode("Thôi học");
                ss.setStatusName("Thôi học");
            } else if (code.equalsIgnoreCase("suspended")) {
                ss.setStatusCode("Bảo lưu");
                ss.setStatusName("Bảo lưu");
            }
        }
        studentStatusRepo.saveAll(statusRecords);
    }

    private void dropStatusConstraint() {
        try {
            // Find the name of the check constraint on the status column of student_classe_sections
            String findConstraintSql = "SELECT name FROM sys.check_constraints " +
                    "WHERE parent_object_id = OBJECT_ID('student_classe_sections') " +
                    "AND definition LIKE '%status%'";
            
            List<String> constraints = jdbcTemplate.queryForList(findConstraintSql, String.class);
            
            for (String constraintName : constraints) {
                String dropSql = "ALTER TABLE student_classe_sections DROP CONSTRAINT " + constraintName;
                jdbcTemplate.execute(dropSql);
                System.out.println("✅ Dropped constraint: " + constraintName);
            }
        } catch (Exception e) {
            System.err.println("❌ Could not drop status constraint: " + e.getMessage());
        }
    }
}
