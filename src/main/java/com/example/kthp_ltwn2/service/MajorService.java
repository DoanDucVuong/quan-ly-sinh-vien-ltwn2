package com.example.kthp_ltwn2.service;
import com.example.kthp_ltwn2.dto.MajorRequest;
import com.example.kthp_ltwn2.dto.MajorResponse;
import org.springframework.data.domain.Page;
import java.util.UUID;
public interface MajorService {
    Page<MajorResponse> getAll(String keyword, int page, int size);
    MajorResponse getById(UUID id);
    MajorResponse create(MajorRequest request);
    MajorResponse update(UUID id, MajorRequest request);
    void softDelete(UUID id);
}
