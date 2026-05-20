package com.example.kthp_ltwn2.controller;

import com.example.kthp_ltwn2.dto.DepartmentRequest;
import com.example.kthp_ltwn2.dto.DepartmentResponse;
import com.example.kthp_ltwn2.service.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/departments")
public class DepartmentRestController {

    private final DepartmentService service;

    public DepartmentRestController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<DepartmentResponse>> getAll(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getAll(keyword, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> create(@RequestBody DepartmentRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> update(@PathVariable UUID id, @RequestBody DepartmentRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
