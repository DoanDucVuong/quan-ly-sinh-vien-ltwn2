package com.example.kthp_ltwn2.service;
import com.example.kthp_ltwn2.dto.AcademicYearRequest;
import com.example.kthp_ltwn2.dto.AcademicYearResponse;
import org.springframework.data.domain.Page;
import java.util.UUID;
public interface AcademicYearService {
    Page<AcademicYearResponse> getAll(String keyword, int page, int size);
    AcademicYearResponse getById(UUID id);
    AcademicYearResponse create(AcademicYearRequest request);
    AcademicYearResponse update(UUID id, AcademicYearRequest request);
    void softDelete(UUID id);
}
