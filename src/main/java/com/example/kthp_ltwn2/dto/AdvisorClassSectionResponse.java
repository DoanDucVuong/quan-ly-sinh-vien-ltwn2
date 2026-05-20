package com.example.kthp_ltwn2.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class AdvisorClassSectionResponse {
    private UUID id;
    private UUID studentClasseId;
    private String studentClasseCode;
    private String employeeId;
    private String employeeName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private String reason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;

    public AdvisorClassSectionResponse() {}

    public AdvisorClassSectionResponse(UUID id, UUID studentClasseId, String studentClasseCode, String employeeId, String employeeName, LocalDateTime startDate, LocalDateTime endDate, String description, String reason, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean isActive) {
        this.id = id;
        this.studentClasseId = studentClasseId;
        this.studentClasseCode = studentClasseCode;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.reason = reason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isActive = isActive;
    }

    public static AdvisorClassSectionResponseBuilder builder() { return new AdvisorClassSectionResponseBuilder(); }
    public static class AdvisorClassSectionResponseBuilder {
        private UUID id;
        private UUID studentClasseId;
        private String studentClasseCode;
        private String employeeId;
        private String employeeName;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String description;
        private String reason;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Boolean isActive;

        public AdvisorClassSectionResponseBuilder id(UUID id) { this.id = id; return this; }
        public AdvisorClassSectionResponseBuilder studentClasseId(UUID studentClasseId) { this.studentClasseId = studentClasseId; return this; }
        public AdvisorClassSectionResponseBuilder studentClasseCode(String studentClasseCode) { this.studentClasseCode = studentClasseCode; return this; }
        public AdvisorClassSectionResponseBuilder employeeId(String employeeId) { this.employeeId = employeeId; return this; }
        public AdvisorClassSectionResponseBuilder employeeName(String employeeName) { this.employeeName = employeeName; return this; }
        public AdvisorClassSectionResponseBuilder startDate(LocalDateTime startDate) { this.startDate = startDate; return this; }
        public AdvisorClassSectionResponseBuilder endDate(LocalDateTime endDate) { this.endDate = endDate; return this; }
        public AdvisorClassSectionResponseBuilder description(String description) { this.description = description; return this; }
        public AdvisorClassSectionResponseBuilder reason(String reason) { this.reason = reason; return this; }
        public AdvisorClassSectionResponseBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public AdvisorClassSectionResponseBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public AdvisorClassSectionResponseBuilder isActive(Boolean isActive) { this.isActive = isActive; return this; }

        public AdvisorClassSectionResponse build() {
            return new AdvisorClassSectionResponse(id, studentClasseId, studentClasseCode, employeeId, employeeName, startDate, endDate, description, reason, createdAt, updatedAt, isActive);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getStudentClasseId() { return studentClasseId; }
    public void setStudentClasseId(UUID studentClasseId) { this.studentClasseId = studentClasseId; }
    public String getStudentClasseCode() { return studentClasseCode; }
    public void setStudentClasseCode(String studentClasseCode) { this.studentClasseCode = studentClasseCode; }
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
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
}
