package com.example.kthp_ltwn2.dto;
import java.util.UUID;
public class TrainingProgramRequest {
    private String code;
    private String name;
    private UUID majorId;
    private String degreeLevel;
    public TrainingProgramRequest() {}
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public UUID getMajorId() { return majorId; }
    public void setMajorId(UUID majorId) { this.majorId = majorId; }
    public String getDegreeLevel() { return degreeLevel; }
    public void setDegreeLevel(String degreeLevel) { this.degreeLevel = degreeLevel; }
}
