package com.example.kthp_ltwn2.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class StudentStatusResponse {
    private UUID id;
    private UUID studentId;
    private String studentCode;
    private String studentFullName;
    private String statusCode;
    private String statusName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String reason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;

    public StudentStatusResponse() {}

    public StudentStatusResponse(UUID id, UUID studentId, String studentCode, String studentFullName, String statusCode, String statusName, LocalDate startDate, LocalDate endDate, String description, String reason, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean isActive) {
        this.id = id;
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.studentFullName = studentFullName;
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.reason = reason;
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
    public String getStatusCode() { return statusCode; }
    public void setStatusCode(String statusCode) { this.statusCode = statusCode; }
    public String getStatusName() { return statusName; }
    public void setStatusName(String statusName) { this.statusName = statusName; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public static StudentStatusResponseBuilder builder() { return new StudentStatusResponseBuilder(); }

    public static class StudentStatusResponseBuilder {
        private UUID id;
        private UUID studentId;
        private String studentCode;
        private String studentFullName;
        private String statusCode;
        private String statusName;
        private LocalDate startDate;
        private LocalDate endDate;
        private String description;
        private String reason;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Boolean isActive;

        public StudentStatusResponseBuilder id(UUID id) { this.id = id; return this; }
        public StudentStatusResponseBuilder studentId(UUID studentId) { this.studentId = studentId; return this; }
        public StudentStatusResponseBuilder studentCode(String studentCode) { this.studentCode = studentCode; return this; }
        public StudentStatusResponseBuilder studentFullName(String studentFullName) { this.studentFullName = studentFullName; return this; }
        public StudentStatusResponseBuilder statusCode(String statusCode) { this.statusCode = statusCode; return this; }
        public StudentStatusResponseBuilder statusName(String statusName) { this.statusName = statusName; return this; }
        public StudentStatusResponseBuilder startDate(LocalDate startDate) { this.startDate = startDate; return this; }
        public StudentStatusResponseBuilder endDate(LocalDate endDate) { this.endDate = endDate; return this; }
        public StudentStatusResponseBuilder description(String description) { this.description = description; return this; }
        public StudentStatusResponseBuilder reason(String reason) { this.reason = reason; return this; }
        public StudentStatusResponseBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public StudentStatusResponseBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public StudentStatusResponseBuilder isActive(Boolean isActive) { this.isActive = isActive; return this; }

        public StudentStatusResponse build() {
            return new StudentStatusResponse(id, studentId, studentCode, studentFullName, statusCode, statusName, startDate, endDate, description, reason, createdAt, updatedAt, isActive);
        }
    }
}
