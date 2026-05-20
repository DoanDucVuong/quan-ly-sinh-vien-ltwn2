package com.example.kthp_ltwn2.service;

import com.example.kthp_ltwn2.dto.DepartmentRequest;
import com.example.kthp_ltwn2.dto.DepartmentResponse;
import com.example.kthp_ltwn2.entity.Department;
import com.example.kthp_ltwn2.repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<DepartmentResponse> getAll(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Department> departments = (keyword == null || keyword.isEmpty()) 
                ? repository.findByIsActiveTrue(pageable) 
                : repository.searchActive(keyword, pageable);
        return departments.map(this::toResponse);
    }

    @Override
    public DepartmentResponse getById(UUID id) {
        return repository.findByIdAndIsActiveTrue(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    @Transactional
    public DepartmentResponse create(DepartmentRequest request) {
        if (repository.existsByCode(request.getCode())) {
            throw new RuntimeException("Code already exists");
        }
        Department department = new Department();
        department.setCode(request.getCode());
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        return toResponse(repository.save(department));
    }

    @Override
    @Transactional
    public DepartmentResponse update(UUID id, DepartmentRequest request) {
        Department department = repository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        return toResponse(repository.save(department));
    }

    @Override
    @Transactional
    public void softDelete(UUID id) {
        Department department = repository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        department.setIsActive(false);
        department.setDeletedAt(LocalDateTime.now());
        repository.save(department);
    }

    private DepartmentResponse toResponse(Department d) {
        return DepartmentResponse.builder()
                .id(d.getId())
                .code(d.getCode())
                .name(d.getName())
                .description(d.getDescription())
                .createdAt(d.getCreatedAt())
                .build();
    }
}
