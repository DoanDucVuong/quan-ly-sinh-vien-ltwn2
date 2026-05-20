package com.example.kthp_ltwn2.service;

import com.example.kthp_ltwn2.dto.StudentRequest;
import com.example.kthp_ltwn2.dto.StudentResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface StudentService {
    Page<StudentResponse> getAll(String keyword, int page, int size);
    StudentResponse getById(UUID id);
    StudentResponse create(StudentRequest request);
    StudentResponse update(UUID id, StudentRequest request);
    void softDelete(UUID id);
}
