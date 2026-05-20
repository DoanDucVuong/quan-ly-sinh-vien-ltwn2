package com.example.kthp_ltwn2.dto;
import java.util.UUID;
public class MajorResponse {
    private UUID id;
    private String code;
    private String name;
    private UUID departmentId;
    private String departmentName;
    public MajorResponse() {}
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public UUID getDepartmentId() { return departmentId; }
    public void setDepartmentId(UUID departmentId) { this.departmentId = departmentId; }
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public static MajorResponseBuilder builder() { return new MajorResponseBuilder(); }
    public static class MajorResponseBuilder {
        private UUID id;
        private String code;
        private String name;
        private UUID departmentId;
        private String departmentName;
        public MajorResponseBuilder id(UUID id) { this.id = id; return this; }
        public MajorResponseBuilder code(String code) { this.code = code; return this; }
        public MajorResponseBuilder name(String name) { this.name = name; return this; }
        public MajorResponseBuilder departmentId(UUID departmentId) { this.departmentId = departmentId; return this; }
        public MajorResponseBuilder departmentName(String departmentName) { this.departmentName = departmentName; return this; }
        public MajorResponse build() {
            MajorResponse r = new MajorResponse();
            r.setId(id); r.setCode(code); r.setName(name); r.setDepartmentId(departmentId); r.setDepartmentName(departmentName);
            return r;
        }
    }
}
