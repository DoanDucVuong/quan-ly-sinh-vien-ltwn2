package com.example.kthp_ltwn2.service;

import com.example.kthp_ltwn2.dto.DepartmentRequest;
import com.example.kthp_ltwn2.dto.DepartmentResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface DepartmentService {
    Page<DepartmentResponse> getAll(String keyword, int page, int size);
    DepartmentResponse getById(UUID id);
    DepartmentResponse create(DepartmentRequest request);
    DepartmentResponse update(UUID id, DepartmentRequest request);
    void softDelete(UUID id);
}
