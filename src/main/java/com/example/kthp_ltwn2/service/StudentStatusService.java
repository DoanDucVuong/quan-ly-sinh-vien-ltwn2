package com.example.kthp_ltwn2.service;

import com.example.kthp_ltwn2.dto.StudentStatusRequest;
import com.example.kthp_ltwn2.dto.StudentStatusResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface StudentStatusService {
    Page<StudentStatusResponse> getAll(int page, int size);
    StudentStatusResponse getById(UUID id);
    StudentStatusResponse create(StudentStatusRequest request);
    StudentStatusResponse update(UUID id, StudentStatusRequest request);
    void softDelete(UUID id);
}
