package com.example.kthp_ltwn2.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;
public class StudentClasseSectionRequest {

    @NotNull(message = "ID Sinh viên không được để trống")
    private UUID studentId;

    @NotNull(message = "ID Lớp học không được để trống")
    private UUID studentClasseId;

    private String status;
    private String note;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public StudentClasseSectionRequest() {}

    public StudentClasseSectionRequest(UUID studentId, UUID studentClasseId, String status, String note, LocalDateTime startDate, LocalDateTime endDate) {
        this.studentId = studentId;
        this.studentClasseId = studentClasseId;
        this.status = status;
        this.note = note;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UUID getStudentId() { return studentId; }
    public void setStudentId(UUID studentId) { this.studentId = studentId; }

    public UUID getStudentClasseId() { return studentClasseId; }
    public void setStudentClasseId(UUID studentClasseId) { this.studentClasseId = studentClasseId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public static StudentClasseSectionRequestBuilder builder() { return new StudentClasseSectionRequestBuilder(); }
    public static class StudentClasseSectionRequestBuilder {
        private UUID studentId;
        private UUID studentClasseId;
        private String status;
        private String note;
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        public StudentClasseSectionRequestBuilder studentId(UUID studentId) { this.studentId = studentId; return this; }
        public StudentClasseSectionRequestBuilder studentClasseId(UUID studentClasseId) { this.studentClasseId = studentClasseId; return this; }
        public StudentClasseSectionRequestBuilder status(String status) { this.status = status; return this; }
        public StudentClasseSectionRequestBuilder note(String note) { this.note = note; return this; }
        public StudentClasseSectionRequestBuilder startDate(LocalDateTime startDate) { this.startDate = startDate; return this; }
        public StudentClasseSectionRequestBuilder endDate(LocalDateTime endDate) { this.endDate = endDate; return this; }

        public StudentClasseSectionRequest build() {
            return new StudentClasseSectionRequest(studentId, studentClasseId, status, note, startDate, endDate);
        }
    }
}
