package com.example.kthp_ltwn2.dto;
import java.util.UUID;
public class AcademicYearResponse {
    private UUID id;
    private String code;
    private String name;
    private Integer startYear;
    private Integer endYear;
    public AcademicYearResponse() {}
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getStartYear() { return startYear; }
    public void setStartYear(Integer startYear) { this.startYear = startYear; }
    public Integer getEndYear() { return endYear; }
    public void setEndYear(Integer endYear) { this.endYear = endYear; }

    public static AcademicYearResponseBuilder builder() { return new AcademicYearResponseBuilder(); }
    public static class AcademicYearResponseBuilder {
        private UUID id;
        private String code;
        private String name;
        private Integer startYear;
        private Integer endYear;
        public AcademicYearResponseBuilder id(UUID id) { this.id = id; return this; }
        public AcademicYearResponseBuilder code(String code) { this.code = code; return this; }
        public AcademicYearResponseBuilder name(String name) { this.name = name; return this; }
        public AcademicYearResponseBuilder startYear(Integer startYear) { this.startYear = startYear; return this; }
        public AcademicYearResponseBuilder endYear(Integer endYear) { this.endYear = endYear; return this; }
        public AcademicYearResponse build() {
            AcademicYearResponse r = new AcademicYearResponse();
            r.setId(id); r.setCode(code); r.setName(name); r.setStartYear(startYear); r.setEndYear(endYear);
            return r;
        }
    }
}
