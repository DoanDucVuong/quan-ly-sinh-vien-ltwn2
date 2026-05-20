package com.example.kthp_ltwn2.service;

import com.example.kthp_ltwn2.dto.StudentClassRequest;
import com.example.kthp_ltwn2.dto.StudentClassResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface StudentClassService {
    Page<StudentClassResponse> getAll(String keyword, int page, int size);
    StudentClassResponse getById(UUID id);
    StudentClassResponse create(StudentClassRequest request);
    StudentClassResponse update(UUID id, StudentClassRequest request);
    void softDelete(UUID id);
}
