package com.example.kthp_ltwn2.dto;
import java.util.UUID;
public class TrainingProgramResponse {
    private UUID id;
    private String code;
    private String name;
    private UUID majorId;
    private String majorName;
    private String degreeLevel;
    public TrainingProgramResponse() {}
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public UUID getMajorId() { return majorId; }
    public void setMajorId(UUID majorId) { this.majorId = majorId; }
    public String getMajorName() { return majorName; }
    public void setMajorName(String majorName) { this.majorName = majorName; }
    public String getDegreeLevel() { return degreeLevel; }
    public void setDegreeLevel(String degreeLevel) { this.degreeLevel = degreeLevel; }

    public static TrainingProgramResponseBuilder builder() { return new TrainingProgramResponseBuilder(); }
    public static class TrainingProgramResponseBuilder {
        private UUID id;
        private String code;
        private String name;
        private UUID majorId;
        private String majorName;
        private String degreeLevel;
        public TrainingProgramResponseBuilder id(UUID id) { this.id = id; return this; }
        public TrainingProgramResponseBuilder code(String code) { this.code = code; return this; }
        public TrainingProgramResponseBuilder name(String name) { this.name = name; return this; }
        public TrainingProgramResponseBuilder majorId(UUID majorId) { this.majorId = majorId; return this; }
        public TrainingProgramResponseBuilder majorName(String majorName) { this.majorName = majorName; return this; }
        public TrainingProgramResponseBuilder degreeLevel(String degreeLevel) { this.degreeLevel = degreeLevel; return this; }
        public TrainingProgramResponse build() {
            TrainingProgramResponse r = new TrainingProgramResponse();
            r.setId(id); r.setCode(code); r.setName(name); r.setMajorId(majorId); r.setMajorName(majorName); r.setDegreeLevel(degreeLevel);
            return r;
        }
    }
}
