package com.example.kthp_ltwn2.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "student_classe_sections")
public class StudentClasseSection {

    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "student_id", columnDefinition = "UNIQUEIDENTIFIER", nullable = false)
    private UUID studentId;

    @Column(name = "student_classe_id", columnDefinition = "UNIQUEIDENTIFIER", nullable = false)
    private UUID studentClasseId;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "created_at", updatable = false)
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

    public StudentClasseSection() {}

    public StudentClasseSection(UUID id, UUID studentId, UUID studentClasseId, String status, String note, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime createdAt, LocalDateTime updatedAt, UUID createdBy, UUID updatedBy, LocalDateTime deletedAt, UUID deletedBy, Boolean isActive) {
        this.id = id;
        this.studentId = studentId;
        this.studentClasseId = studentClasseId;
        this.status = status;
        this.note = note;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public static StudentClasseSectionBuilder builder() { return new StudentClasseSectionBuilder(); }
    public static class StudentClasseSectionBuilder {
        private UUID id;
        private UUID studentId;
        private UUID studentClasseId;
        private String status;
        private String note;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private UUID createdBy;
        private UUID updatedBy;
        private LocalDateTime deletedAt;
        private UUID deletedBy;
        private Boolean isActive;

        public StudentClasseSectionBuilder id(UUID id) { this.id = id; return this; }
        public StudentClasseSectionBuilder studentId(UUID studentId) { this.studentId = studentId; return this; }
        public StudentClasseSectionBuilder studentClasseId(UUID studentClasseId) { this.studentClasseId = studentClasseId; return this; }
        public StudentClasseSectionBuilder status(String status) { this.status = status; return this; }
        public StudentClasseSectionBuilder note(String note) { this.note = note; return this; }
        public StudentClasseSectionBuilder startDate(LocalDateTime startDate) { this.startDate = startDate; return this; }
        public StudentClasseSectionBuilder endDate(LocalDateTime endDate) { this.endDate = endDate; return this; }
        public StudentClasseSectionBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public StudentClasseSectionBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public StudentClasseSectionBuilder createdBy(UUID createdBy) { this.createdBy = createdBy; return this; }
        public StudentClasseSectionBuilder updatedBy(UUID updatedBy) { this.updatedBy = updatedBy; return this; }
        public StudentClasseSectionBuilder deletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; return this; }
        public StudentClasseSectionBuilder deletedBy(UUID deletedBy) { this.deletedBy = deletedBy; return this; }
        public StudentClasseSectionBuilder isActive(Boolean isActive) { this.isActive = isActive; return this; }

        public StudentClasseSection build() {
            return new StudentClasseSection(id, studentId, studentClasseId, status, note, startDate, endDate, createdAt, updatedAt, createdBy, updatedBy, deletedAt, deletedBy, isActive);
        }
    }
}
