package com.example.kthp_ltwn2.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
public class StudentClassRequest {

    @NotBlank(message = "Mã lớp không được để trống")
    private String code;

    @NotBlank(message = "Tên lớp không được để trống")
    private String name;

    private UUID academicYearId;
    private UUID departmentId;
    private UUID majorId;
    private UUID trainingProgramId;
    private UUID employeeId;

    public StudentClassRequest() {}

    public StudentClassRequest(String code, String name, UUID academicYearId, UUID departmentId, UUID majorId, UUID trainingProgramId, UUID employeeId) {
        this.code = code;
        this.name = name;
        this.academicYearId = academicYearId;
        this.departmentId = departmentId;
        this.majorId = majorId;
        this.trainingProgramId = trainingProgramId;
        this.employeeId = employeeId;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public UUID getAcademicYearId() { return academicYearId; }
    public void setAcademicYearId(UUID academicYearId) { this.academicYearId = academicYearId; }

    public UUID getDepartmentId() { return departmentId; }
    public void setDepartmentId(UUID departmentId) { this.departmentId = departmentId; }

    public UUID getMajorId() { return majorId; }
    public void setMajorId(UUID majorId) { this.majorId = majorId; }

    public UUID getTrainingProgramId() { return trainingProgramId; }
    public void setTrainingProgramId(UUID trainingProgramId) { this.trainingProgramId = trainingProgramId; }

    public UUID getEmployeeId() { return employeeId; }
    public void setEmployeeId(UUID employeeId) { this.employeeId = employeeId; }

    public static StudentClassRequestBuilder builder() { return new StudentClassRequestBuilder(); }
    public static class StudentClassRequestBuilder {
        private String code;
        private String name;
        private UUID academicYearId;
        private UUID departmentId;
        private UUID majorId;
        private UUID trainingProgramId;
        private UUID employeeId;

        public StudentClassRequestBuilder code(String code) { this.code = code; return this; }
        public StudentClassRequestBuilder name(String name) { this.name = name; return this; }
        public StudentClassRequestBuilder academicYearId(UUID academicYearId) { this.academicYearId = academicYearId; return this; }
        public StudentClassRequestBuilder departmentId(UUID departmentId) { this.departmentId = departmentId; return this; }
        public StudentClassRequestBuilder majorId(UUID majorId) { this.majorId = majorId; return this; }
        public StudentClassRequestBuilder trainingProgramId(UUID trainingProgramId) { this.trainingProgramId = trainingProgramId; return this; }
        public StudentClassRequestBuilder employeeId(UUID employeeId) { this.employeeId = employeeId; return this; }

        public StudentClassRequest build() {
            return new StudentClassRequest(code, name, academicYearId, departmentId, majorId, trainingProgramId, employeeId);
        }
    }
}
