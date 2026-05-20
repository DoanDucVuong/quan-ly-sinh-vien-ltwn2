package com.example.kthp_ltwn2.dto;
import java.util.UUID;
public class AcademicYearRequest {
    private String code;
    private String name;
    private Integer startYear;
    private Integer endYear;
    public AcademicYearRequest() {}
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getStartYear() { return startYear; }
    public void setStartYear(Integer startYear) { this.startYear = startYear; }
    public Integer getEndYear() { return endYear; }
    public void setEndYear(Integer endYear) { this.endYear = endYear; }
}
