package com.example.kthp_ltwn2.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "student_classes")
public class StudentClass {

    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "code", length = 100)
    private String code;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "academic_year_id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID academicYearId;

    @Column(name = "department_id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID departmentId;

    @Column(name = "major_id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID majorId;

    @Column(name = "training_program_id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID trainingProgramId;

    @Column(name = "employee_id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID employeeId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID createdBy;

    @Column(name = "updated_by", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID updatedBy;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "deleted_by", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID deletedBy;

    @Column(name = "is_active")
    private Boolean isActive;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.isActive == null) this.isActive = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public StudentClass() {}

    public StudentClass(UUID id, String code, String name, UUID academicYearId, UUID departmentId, UUID majorId, UUID trainingProgramId, UUID employeeId, LocalDateTime createdAt, LocalDateTime updatedAt, UUID createdBy, UUID updatedBy, LocalDateTime deletedAt, UUID deletedBy, Boolean isActive) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.academicYearId = academicYearId;
        this.departmentId = departmentId;
        this.majorId = majorId;
        this.trainingProgramId = trainingProgramId;
        this.employeeId = employeeId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedAt = deletedAt;
        this.deletedBy = deletedBy;
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

    public UUID getDepartmentId() { return departmentId; }
    public void setDepartmentId(UUID departmentId) { this.departmentId = departmentId; }

    public UUID getMajorId() { return majorId; }
    public void setMajorId(UUID majorId) { this.majorId = majorId; }

    public UUID getTrainingProgramId() { return trainingProgramId; }
    public void setTrainingProgramId(UUID trainingProgramId) { this.trainingProgramId = trainingProgramId; }

    public UUID getEmployeeId() { return employeeId; }
    public void setEmployeeId(UUID employeeId) { this.employeeId = employeeId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public UUID getCreatedBy() { return createdBy; }
    public void setCreatedBy(UUID createdBy) { this.createdBy = createdBy; }

    public UUID getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(UUID updatedBy) { this.updatedBy = updatedBy; }

    public LocalDateTime getDeletedAt() { return deletedAt; }
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }

    public UUID getDeletedBy() { return deletedBy; }
    public void setDeletedBy(UUID deletedBy) { this.deletedBy = deletedBy; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public static StudentClassBuilder builder() { return new StudentClassBuilder(); }
    public static class StudentClassBuilder {
        private UUID id;
        private String code;
        private String name;
        private UUID academicYearId;
        private UUID departmentId;
        private UUID majorId;
        private UUID trainingProgramId;
        private UUID employeeId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private UUID createdBy;
        private UUID updatedBy;
        private LocalDateTime deletedAt;
        private UUID deletedBy;
        private Boolean isActive;

        public StudentClassBuilder id(UUID id) { this.id = id; return this; }
        public StudentClassBuilder code(String code) { this.code = code; return this; }
        public StudentClassBuilder name(String name) { this.name = name; return this; }
        public StudentClassBuilder academicYearId(UUID academicYearId) { this.academicYearId = academicYearId; return this; }
        public StudentClassBuilder departmentId(UUID departmentId) { this.departmentId = departmentId; return this; }
        public StudentClassBuilder majorId(UUID majorId) { this.majorId = majorId; return this; }
        public StudentClassBuilder trainingProgramId(UUID trainingProgramId) { this.trainingProgramId = trainingProgramId; return this; }
        public StudentClassBuilder employeeId(UUID employeeId) { this.employeeId = employeeId; return this; }
        public StudentClassBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public StudentClassBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public StudentClassBuilder createdBy(UUID createdBy) { this.createdBy = createdBy; return this; }
        public StudentClassBuilder updatedBy(UUID updatedBy) { this.updatedBy = updatedBy; return this; }
        public StudentClassBuilder deletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; return this; }
        public StudentClassBuilder deletedBy(UUID deletedBy) { this.deletedBy = deletedBy; return this; }
        public StudentClassBuilder isActive(Boolean isActive) { this.isActive = isActive; return this; }

        public StudentClass build() {
            return new StudentClass(id, code, name, academicYearId, departmentId, majorId, trainingProgramId, employeeId, createdAt, updatedAt, createdBy, updatedBy, deletedAt, deletedBy, isActive);
        }
    }
}
