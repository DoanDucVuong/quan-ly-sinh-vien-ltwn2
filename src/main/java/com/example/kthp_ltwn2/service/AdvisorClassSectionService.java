package com.example.kthp_ltwn2.service;

import com.example.kthp_ltwn2.dto.AdvisorClassSectionRequest;
import com.example.kthp_ltwn2.dto.AdvisorClassSectionResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AdvisorClassSectionService {
    Page<AdvisorClassSectionResponse> getAll(int page, int size);
    AdvisorClassSectionResponse getById(UUID id);
    AdvisorClassSectionResponse create(AdvisorClassSectionRequest request);
    AdvisorClassSectionResponse update(UUID id, AdvisorClassSectionRequest request);
    void softDelete(UUID id);
}
