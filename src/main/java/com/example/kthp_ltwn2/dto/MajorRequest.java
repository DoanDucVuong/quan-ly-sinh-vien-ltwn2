package com.example.kthp_ltwn2.dto;
import java.util.UUID;
public class MajorRequest {
    private String code;
    private String name;
    private UUID departmentId;
    public MajorRequest() {}
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public UUID getDepartmentId() { return departmentId; }
    public void setDepartmentId(UUID departmentId) { this.departmentId = departmentId; }
}
