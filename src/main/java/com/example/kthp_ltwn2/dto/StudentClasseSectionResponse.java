package com.example.kthp_ltwn2.dto;


import java.time.LocalDateTime;
import java.util.UUID;
public class StudentClasseSectionResponse {
    private UUID id;
    private UUID studentId;
    private String studentCode;
    private String studentFullName;
    private UUID studentClasseId;
    private String studentClasseCode;
    private String status;
    private String note;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;

    public StudentClasseSectionResponse() {}

    public StudentClasseSectionResponse(UUID id, UUID studentId, String studentCode, String studentFullName, UUID studentClasseId, String studentClasseCode, String status, String note, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean isActive) {
        this.id = id;
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.studentFullName = studentFullName;
        this.studentClasseId = studentClasseId;
        this.studentClasseCode = studentClasseCode;
        this.status = status;
        this.note = note;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isActive = isActive;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getStudentId() { return studentId; }
    public void setStudentId(UUID studentId) { this.studentId = studentId; }
    public String getStudentCode() { return studentCode; }
    public void setStudentCode(String studentCode) { this.studentCode = studentCode; }
    public String getStudentFullName() { return studentFullName; }
    public void setStudentFullName(String studentFullName) { this.studentFullName = studentFullName; }
    public UUID getStudentClasseId() { return studentClasseId; }
    public void setStudentClasseId(UUID studentClasseId) { this.studentClasseId = studentClasseId; }
    public String getStudentClasseCode() { return studentClasseCode; }
    public void setStudentClasseCode(String studentClasseCode) { this.studentClasseCode = studentClasseCode; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public static StudentClasseSectionResponseBuilder builder() { return new StudentClasseSectionResponseBuilder(); }
    public static class StudentClasseSectionResponseBuilder {
        private UUID id;
        private UUID studentId;
        private String studentCode;
        private String studentFullName;
        private UUID studentClasseId;
        private String studentClasseCode;
        private String status;
        private String note;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Boolean isActive;

        public StudentClasseSectionResponseBuilder id(UUID id) { this.id = id; return this; }
        public StudentClasseSectionResponseBuilder studentId(UUID studentId) { this.studentId = studentId; return this; }
        public StudentClasseSectionResponseBuilder studentCode(String studentCode) { this.studentCode = studentCode; return this; }
        public StudentClasseSectionResponseBuilder studentFullName(String studentFullName) { this.studentFullName = studentFullName; return this; }
        public StudentClasseSectionResponseBuilder studentClasseId(UUID studentClasseId) { this.studentClasseId = studentClasseId; return this; }
        public StudentClasseSectionResponseBuilder studentClasseCode(String studentClasseCode) { this.studentClasseCode = studentClasseCode; return this; }
        public StudentClasseSectionResponseBuilder status(String status) { this.status = status; return this; }
        public StudentClasseSectionResponseBuilder note(String note) { this.note = note; return this; }
        public StudentClasseSectionResponseBuilder startDate(LocalDateTime startDate) { this.startDate = startDate; return this; }
        public StudentClasseSectionResponseBuilder endDate(LocalDateTime endDate) { this.endDate = endDate; return this; }
        public StudentClasseSectionResponseBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public StudentClasseSectionResponseBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public StudentClasseSectionResponseBuilder isActive(Boolean isActive) { this.isActive = isActive; return this; }

        public StudentClasseSectionResponse build() {
            return new StudentClasseSectionResponse(id, studentId, studentCode, studentFullName, studentClasseId, studentClasseCode, status, note, startDate, endDate, createdAt, updatedAt, isActive);
        }
    }
}
