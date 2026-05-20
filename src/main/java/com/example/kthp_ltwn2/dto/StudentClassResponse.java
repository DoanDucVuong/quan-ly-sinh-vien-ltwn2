package com.example.kthp_ltwn2.dto;

import java.time.LocalDateTime;
import java.util.UUID;
public class StudentClassResponse {
    private UUID id;
    private String code;
    private String name;
    private UUID academicYearId;
    private String academicYearCode;
    private UUID departmentId;
    private String departmentName;
    private UUID majorId;
    private String majorName;
    private UUID trainingProgramId;
    private String trainingProgramName;
    private UUID employeeId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;

    public StudentClassResponse() {}

    public StudentClassResponse(UUID id, String code, String name, UUID academicYearId, String academicYearCode, UUID departmentId, String departmentName, UUID majorId, String majorName, UUID trainingProgramId, String trainingProgramName, UUID employeeId, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean isActive) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.academicYearId = academicYearId;
        this.academicYearCode = academicYearCode;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.majorId = majorId;
        this.majorName = majorName;
        this.trainingProgramId = trainingProgramId;
        this.trainingProgramName = trainingProgramName;
        this.employeeId = employeeId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isActive = isActive;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public UUID getAcademicYearId() { return academicYearId; }
    public void setAcademicYearId(UUID academicYearId) { this.academicYearId = academicYearId; }

    public String getAcademicYearCode() { return academicYearCode; }
    public void setAcademicYearCode(String academicYearCode) { this.academicYearCode = academicYearCode; }

    public UUID getDepartmentId() { return departmentId; }
    public void setDepartmentId(UUID departmentId) { this.departmentId = departmentId; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public UUID getMajorId() { return majorId; }
    public void setMajorId(UUID majorId) { this.majorId = majorId; }

    public String getMajorName() { return majorName; }
    public void setMajorName(String majorName) { this.majorName = majorName; }

    public UUID getTrainingProgramId() { return trainingProgramId; }
    public void setTrainingProgramId(UUID trainingProgramId) { this.trainingProgramId = trainingProgramId; }

    public String getTrainingProgramName() { return trainingProgramName; }
    public void setTrainingProgramName(String trainingProgramName) { this.trainingProgramName = trainingProgramName; }

    public UUID getEmployeeId() { return employeeId; }
    public void setEmployeeId(UUID employeeId) { this.employeeId = employeeId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public static StudentClassResponseBuilder builder() { return new StudentClassResponseBuilder(); }
    public static class StudentClassResponseBuilder {
        private UUID id;
        private String code;
        private String name;
        private UUID academicYearId;
        private String academicYearCode;
        private UUID departmentId;
        private String departmentName;
        private UUID majorId;
        private String majorName;
        private UUID trainingProgramId;
        private String trainingProgramName;
        private UUID employeeId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Boolean isActive;

        public StudentClassResponseBuilder id(UUID id) { this.id = id; return this; }
        public StudentClassResponseBuilder code(String code) { this.code = code; return this; }
        public StudentClassResponseBuilder name(String name) { this.name = name; return this; }
        public StudentClassResponseBuilder academicYearId(UUID academicYearId) { this.academicYearId = academicYearId; return this; }
        public StudentClassResponseBuilder academicYearCode(String academicYearCode) { this.academicYearCode = academicYearCode; return this; }
        public StudentClassResponseBuilder departmentId(UUID departmentId) { this.departmentId = departmentId; return this; }
        public StudentClassResponseBuilder departmentName(String departmentName) { this.departmentName = departmentName; return this; }
        public StudentClassResponseBuilder majorId(UUID majorId) { this.majorId = majorId; return this; }
        public StudentClassResponseBuilder majorName(String majorName) { this.majorName = majorName; return this; }
        public StudentClassResponseBuilder trainingProgramId(UUID trainingProgramId) { this.trainingProgramId = trainingProgramId; return this; }
        public StudentClassResponseBuilder trainingProgramName(String trainingProgramName) { this.trainingProgramName = trainingProgramName; return this; }
        public StudentClassResponseBuilder employeeId(UUID employeeId) { this.employeeId = employeeId; return this; }
        public StudentClassResponseBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public StudentClassResponseBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public StudentClassResponseBuilder isActive(Boolean isActive) { this.isActive = isActive; return this; }

        public StudentClassResponse build() {
            return new StudentClassResponse(id, code, name, academicYearId, academicYearCode, departmentId, departmentName, majorId, majorName, trainingProgramId, trainingProgramName, employeeId, createdAt, updatedAt, isActive);
        }
    }
}
