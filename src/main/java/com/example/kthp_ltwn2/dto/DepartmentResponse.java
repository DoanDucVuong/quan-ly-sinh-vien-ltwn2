package com.example.kthp_ltwn2.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class DepartmentResponse {
    private UUID id;
    private String code;
    private String name;
    private String description;
    private LocalDateTime createdAt;

    public DepartmentResponse() {}
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public static DepartmentResponseBuilder builder() { return new DepartmentResponseBuilder(); }
    public static class DepartmentResponseBuilder {
        private UUID id;
        private String code;
        private String name;
        private String description;
        private LocalDateTime createdAt;
        public DepartmentResponseBuilder id(UUID id) { this.id = id; return this; }
        public DepartmentResponseBuilder code(String code) { this.code = code; return this; }
        public DepartmentResponseBuilder name(String name) { this.name = name; return this; }
        public DepartmentResponseBuilder description(String description) { this.description = description; return this; }
        public DepartmentResponseBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public DepartmentResponse build() {
            DepartmentResponse r = new DepartmentResponse();
            r.setId(id); r.setCode(code); r.setName(name); r.setDescription(description); r.setCreatedAt(createdAt);
            return r;
        }
    }
}
