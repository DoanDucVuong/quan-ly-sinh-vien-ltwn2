package com.example.kthp_ltwn2.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
public class StudentResponse {
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
    private String status;
    private LocalDateTime admissionYear;
    private UUID departmentId;
    private UUID majorId;
    private UUID trainingProgramId;
    private UUID studentClasseId;
    private String studentClasseCode;
    private UUID academicYearYear;
    private String academicYearCode;
    private String departmentName;
    private String majorName;
    private String trainingProgramName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;

    public StudentResponse() {}

    public StudentResponse(UUID id, String code, String fullName, LocalDate dateOfBirth, String gender, String personalIdentificationNumber, LocalDate dateOfIssue, String cardPlace, String address, String currentAddress, String status, LocalDateTime admissionYear, UUID departmentId, UUID majorId, UUID trainingProgramId, UUID studentClasseId, String studentClasseCode, UUID academicYearYear, String academicYearCode, String departmentName, String majorName, String trainingProgramName, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean isActive) {
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
        this.status = status;
        this.admissionYear = admissionYear;
        this.departmentId = departmentId;
        this.majorId = majorId;
        this.trainingProgramId = trainingProgramId;
        this.studentClasseId = studentClasseId;
        this.studentClasseCode = studentClasseCode;
        this.academicYearYear = academicYearYear;
        this.academicYearCode = academicYearCode;
        this.departmentName = departmentName;
        this.majorName = majorName;
        this.trainingProgramName = trainingProgramName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getAdmissionYear() { return admissionYear; }
    public void setAdmissionYear(LocalDateTime admissionYear) { this.admissionYear = admissionYear; }

    public UUID getDepartmentId() { return departmentId; }
    public void setDepartmentId(UUID departmentId) { this.departmentId = departmentId; }

    public UUID getMajorId() { return majorId; }
    public void setMajorId(UUID majorId) { this.majorId = majorId; }

    public UUID getTrainingProgramId() { return trainingProgramId; }
    public void setTrainingProgramId(UUID trainingProgramId) { this.trainingProgramId = trainingProgramId; }

    public UUID getStudentClasseId() { return studentClasseId; }
    public void setStudentClasseId(UUID studentClasseId) { this.studentClasseId = studentClasseId; }
    public String getStudentClasseCode() { return studentClasseCode; }
    public void setStudentClasseCode(String studentClasseCode) { this.studentClasseCode = studentClasseCode; }

    public UUID getAcademicYearYear() { return academicYearYear; }
    public void setAcademicYearYear(UUID academicYearYear) { this.academicYearYear = academicYearYear; }
    public String getAcademicYearCode() { return academicYearCode; }
    public void setAcademicYearCode(String academicYearCode) { this.academicYearCode = academicYearCode; }
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
    public String getMajorName() { return majorName; }
    public void setMajorName(String majorName) { this.majorName = majorName; }
    public String getTrainingProgramName() { return trainingProgramName; }
    public void setTrainingProgramName(String trainingProgramName) { this.trainingProgramName = trainingProgramName; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public static StudentResponseBuilder builder() { return new StudentResponseBuilder(); }
    public static class StudentResponseBuilder {
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
        private String status;
        private LocalDateTime admissionYear;
        private UUID departmentId;
        private UUID majorId;
        private UUID trainingProgramId;
        private UUID studentClasseId;
        private String studentClasseCode;
        private UUID academicYearYear;
        private String academicYearCode;
        private String departmentName;
        private String majorName;
        private String trainingProgramName;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Boolean isActive;

        public StudentResponseBuilder id(UUID id) { this.id = id; return this; }
        public StudentResponseBuilder code(String code) { this.code = code; return this; }
        public StudentResponseBuilder fullName(String fullName) { this.fullName = fullName; return this; }
        public StudentResponseBuilder dateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; return this; }
        public StudentResponseBuilder gender(String gender) { this.gender = gender; return this; }
        public StudentResponseBuilder personalIdentificationNumber(String personalIdentificationNumber) { this.personalIdentificationNumber = personalIdentificationNumber; return this; }
        public StudentResponseBuilder dateOfIssue(LocalDate dateOfIssue) { this.dateOfIssue = dateOfIssue; return this; }
        public StudentResponseBuilder cardPlace(String cardPlace) { this.cardPlace = cardPlace; return this; }
        public StudentResponseBuilder address(String address) { this.address = address; return this; }
        public StudentResponseBuilder currentAddress(String currentAddress) { this.currentAddress = currentAddress; return this; }
        public StudentResponseBuilder status(String status) { this.status = status; return this; }
        public StudentResponseBuilder admissionYear(LocalDateTime admissionYear) { this.admissionYear = admissionYear; return this; }
        public StudentResponseBuilder departmentId(UUID departmentId) { this.departmentId = departmentId; return this; }
        public StudentResponseBuilder majorId(UUID majorId) { this.majorId = majorId; return this; }
        public StudentResponseBuilder trainingProgramId(UUID trainingProgramId) { this.trainingProgramId = trainingProgramId; return this; }
        public StudentResponseBuilder studentClasseId(UUID studentClasseId) { this.studentClasseId = studentClasseId; return this; }
        public StudentResponseBuilder studentClasseCode(String studentClasseCode) { this.studentClasseCode = studentClasseCode; return this; }
        public StudentResponseBuilder academicYearYear(UUID academicYearYear) { this.academicYearYear = academicYearYear; return this; }
        public StudentResponseBuilder academicYearCode(String academicYearCode) { this.academicYearCode = academicYearCode; return this; }
        public StudentResponseBuilder departmentName(String departmentName) { this.departmentName = departmentName; return this; }
        public StudentResponseBuilder majorName(String majorName) { this.majorName = majorName; return this; }
        public StudentResponseBuilder trainingProgramName(String trainingProgramName) { this.trainingProgramName = trainingProgramName; return this; }
        public StudentResponseBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public StudentResponseBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public StudentResponseBuilder isActive(Boolean isActive) { this.isActive = isActive; return this; }

        public StudentResponse build() {
            return new StudentResponse(id, code, fullName, dateOfBirth, gender, personalIdentificationNumber, dateOfIssue, cardPlace, address, currentAddress, status, admissionYear, departmentId, majorId, trainingProgramId, studentClasseId, studentClasseCode, academicYearYear, academicYearCode, departmentName, majorName, trainingProgramName, createdAt, updatedAt, isActive);
        }
    }
}
