package com.example.kthp_ltwn2.service;

import com.example.kthp_ltwn2.dto.StudentClasseSectionRequest;
import com.example.kthp_ltwn2.dto.StudentClasseSectionResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface StudentClasseSectionService {
    Page<StudentClasseSectionResponse> getAll(int page, int size);
    StudentClasseSectionResponse getById(UUID id);
    StudentClasseSectionResponse create(StudentClasseSectionRequest request);
    StudentClasseSectionResponse update(UUID id, StudentClasseSectionRequest request);
    void softDelete(UUID id);
}
