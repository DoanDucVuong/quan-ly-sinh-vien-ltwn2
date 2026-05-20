package com.example.kthp_ltwn2.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "code", length = 100, unique = true)
    private String code;

    @Column(name = "full_name", length = 255)
    private String fullName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "personal_identification_number", length = 20)
    private String personalIdentificationNumber;

    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;

    @Column(name = "card_place", length = 100)
    private String cardPlace;

    @Column(name = "address", length = 300)
    private String address;

    @Column(name = "current_address", length = 300)
    private String currentAddress;

    @Column(name = "academic_year_year", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID academicYearYear;

    @Column(name = "department_id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID departmentId;

    @Column(name = "major_id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID majorId;

    @Column(name = "training_program_id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID trainingProgramId;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "student_classe_id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID studentClasseId;

    @Column(name = "admission_year")
    private LocalDateTime admissionYear;

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

    // Boolean field: @Data tạo getIsActive() (không phải isIsActive())
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

    public Student() {}

    public Student(UUID id, String code, String fullName, LocalDate dateOfBirth, String gender, String personalIdentificationNumber, LocalDate dateOfIssue, String cardPlace, String address, String currentAddress, UUID academicYearYear, UUID departmentId, UUID majorId, UUID trainingProgramId, String status, UUID studentClasseId, LocalDateTime admissionYear, LocalDateTime createdAt, LocalDateTime updatedAt, UUID createdBy, UUID updatedBy, LocalDateTime deletedAt, UUID deletedBy, Boolean isActive) {
        this.id = id;
        this.code = code;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.personalIdentificationNumber = personalIdentificationNumber;
        this.dateOfIssue = dateOfIssue;
        this.cardPlace = cardPlace;
        this.address = address;
        this.currentAddress = currentAddress;
        this.academicYearYear = academicYearYear;
        this.departmentId = departmentId;
        this.majorId = majorId;
        this.trainingProgramId = trainingProgramId;
        this.status = status;
        this.studentClasseId = studentClasseId;
        this.admissionYear = admissionYear;
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

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPersonalIdentificationNumber() { return personalIdentificationNumber; }
    public void setPersonalIdentificationNumber(String personalIdentificationNumber) { this.personalIdentificationNumber = personalIdentificationNumber; }

    public LocalDate getDateOfIssue() { return dateOfIssue; }
    public void setDateOfIssue(LocalDate dateOfIssue) { this.dateOfIssue = dateOfIssue; }

    public String getCardPlace() { return cardPlace; }
    public void setCardPlace(String cardPlace) { this.cardPlace = cardPlace; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCurrentAddress() { return currentAddress; }
    public void setCurrentAddress(String currentAddress) { this.currentAddress = currentAddress; }

    public UUID getAcademicYearYear() { return academicYearYear; }
    public void setAcademicYearYear(UUID academicYearYear) { this.academicYearYear = academicYearYear; }

    public UUID getDepartmentId() { return departmentId; }
    public void setDepartmentId(UUID departmentId) { this.departmentId = departmentId; }

    public UUID getMajorId() { return majorId; }
    public void setMajorId(UUID majorId) { this.majorId = majorId; }

    public UUID getTrainingProgramId() { return trainingProgramId; }
    public void setTrainingProgramId(UUID trainingProgramId) { this.trainingProgramId = trainingProgramId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public UUID getStudentClasseId() { return studentClasseId; }
    public void setStudentClasseId(UUID studentClasseId) { this.studentClasseId = studentClasseId; }

    public LocalDateTime getAdmissionYear() { return admissionYear; }
    public void setAdmissionYear(LocalDateTime admissionYear) { this.admissionYear = admissionYear; }

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

    public static StudentBuilder builder() { return new StudentBuilder(); }
    public static class StudentBuilder {
        private UUID id;
        private String code;
        private String fullName;
        private LocalDate dateOfBirth;
        private String gender;
        private String personalIdentificationNumber;
        private LocalDate dateOfIssue;
        private String cardPlace;
        private String address;
        private String currentAddress;
        private UUID academicYearYear;
        private UUID departmentId;
        private UUID majorId;
        private UUID trainingProgramId;
        private String status;
        private UUID studentClasseId;
        private LocalDateTime admissionYear;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private UUID createdBy;
        private UUID updatedBy;
        private LocalDateTime deletedAt;
        private UUID deletedBy;
        private Boolean isActive;

        public StudentBuilder id(UUID id) { this.id = id; return this; }
        public StudentBuilder code(String code) { this.code = code; return this; }
        public StudentBuilder fullName(String fullName) { this.fullName = fullName; return this; }
        public StudentBuilder dateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; return this; }
        public StudentBuilder gender(String gender) { this.gender = gender; return this; }
        public StudentBuilder personalIdentificationNumber(String personalIdentificationNumber) { this.personalIdentificationNumber = personalIdentificationNumber; return this; }
        public StudentBuilder dateOfIssue(LocalDate dateOfIssue) { this.dateOfIssue = dateOfIssue; return this; }
        public StudentBuilder cardPlace(String cardPlace) { this.cardPlace = cardPlace; return this; }
        public StudentBuilder address(String address) { this.address = address; return this; }
        public StudentBuilder currentAddress(String currentAddress) { this.currentAddress = currentAddress; return this; }
        public StudentBuilder academicYearYear(UUID academicYearYear) { this.academicYearYear = academicYearYear; return this; }
        public StudentBuilder departmentId(UUID departmentId) { this.departmentId = departmentId; return this; }
        public StudentBuilder majorId(UUID majorId) { this.majorId = majorId; return this; }
        public StudentBuilder trainingProgramId(UUID trainingProgramId) { this.trainingProgramId = trainingProgramId; return this; }
        public StudentBuilder status(String status) { this.status = status; return this; }
        public StudentBuilder studentClasseId(UUID studentClasseId) { this.studentClasseId = studentClasseId; return this; }
        public StudentBuilder admissionYear(LocalDateTime admissionYear) { this.admissionYear = admissionYear; return this; }
        public StudentBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public StudentBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public StudentBuilder createdBy(UUID createdBy) { this.createdBy = createdBy; return this; }
        public StudentBuilder updatedBy(UUID updatedBy) { this.updatedBy = updatedBy; return this; }
        public StudentBuilder deletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; return this; }
        public StudentBuilder deletedBy(UUID deletedBy) { this.deletedBy = deletedBy; return this; }
        public StudentBuilder isActive(Boolean isActive) { this.isActive = isActive; return this; }

        public Student build() {
            return new Student(id, code, fullName, dateOfBirth, gender, personalIdentificationNumber, dateOfIssue, cardPlace, address, currentAddress, academicYearYear, departmentId, majorId, trainingProgramId, status, studentClasseId, admissionYear, createdAt, updatedAt, createdBy, updatedBy, deletedAt, deletedBy, isActive);
        }
    }
}
