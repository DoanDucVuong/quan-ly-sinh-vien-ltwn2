package com.example.kthp_ltwn2.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
public class StudentRequest {

    @NotBlank(message = "Mã sinh viên không được để trống")
    private String code;

    @NotBlank(message = "Họ và tên không được để trống")
    private String fullName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String gender;

    private String personalIdentificationNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfIssue;

    private String cardPlace;

    private String address;

    private String currentAddress;

    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime admissionYear;

    private UUID departmentId;
    private UUID majorId;
    private UUID trainingProgramId;
    private UUID studentClasseId;
    private UUID academicYearYear;

    public StudentRequest() {}

    public StudentRequest(String code, String fullName, LocalDate dateOfBirth, String gender, String personalIdentificationNumber, LocalDate dateOfIssue, String cardPlace, String address, String currentAddress, String status, LocalDateTime admissionYear, UUID departmentId, UUID majorId, UUID trainingProgramId, UUID studentClasseId, UUID academicYearYear) {
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
        this.academicYearYear = academicYearYear;
    }

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

    public UUID getAcademicYearYear() { return academicYearYear; }
    public void setAcademicYearYear(UUID academicYearYear) { this.academicYearYear = academicYearYear; }

    public static StudentRequestBuilder builder() { return new StudentRequestBuilder(); }
    public static class StudentRequestBuilder {
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
        private UUID academicYearYear;

        public StudentRequestBuilder code(String code) { this.code = code; return this; }
        public StudentRequestBuilder fullName(String fullName) { this.fullName = fullName; return this; }
        public StudentRequestBuilder dateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; return this; }
        public StudentRequestBuilder gender(String gender) { this.gender = gender; return this; }
        public StudentRequestBuilder personalIdentificationNumber(String personalIdentificationNumber) { this.personalIdentificationNumber = personalIdentificationNumber; return this; }
        public StudentRequestBuilder dateOfIssue(LocalDate dateOfIssue) { this.dateOfIssue = dateOfIssue; return this; }
        public StudentRequestBuilder cardPlace(String cardPlace) { this.cardPlace = cardPlace; return this; }
        public StudentRequestBuilder address(String address) { this.address = address; return this; }
        public StudentRequestBuilder currentAddress(String currentAddress) { this.currentAddress = currentAddress; return this; }
        public StudentRequestBuilder status(String status) { this.status = status; return this; }
        public StudentRequestBuilder admissionYear(LocalDateTime admissionYear) { this.admissionYear = admissionYear; return this; }
        public StudentRequestBuilder departmentId(UUID departmentId) { this.departmentId = departmentId; return this; }
        public StudentRequestBuilder majorId(UUID majorId) { this.majorId = majorId; return this; }
        public StudentRequestBuilder trainingProgramId(UUID trainingProgramId) { this.trainingProgramId = trainingProgramId; return this; }
        public StudentRequestBuilder studentClasseId(UUID studentClasseId) { this.studentClasseId = studentClasseId; return this; }
        public StudentRequestBuilder academicYearYear(UUID academicYearYear) { this.academicYearYear = academicYearYear; return this; }

        public StudentRequest build() {
            return new StudentRequest(code, fullName, dateOfBirth, gender, personalIdentificationNumber, dateOfIssue, cardPlace, address, currentAddress, status, admissionYear, departmentId, majorId, trainingProgramId, studentClasseId, academicYearYear);
        }
    }
}
