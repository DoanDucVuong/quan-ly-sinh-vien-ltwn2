package com.example.kthp_ltwn2.controller;

import com.example.kthp_ltwn2.dto.ApiResponse;
import com.example.kthp_ltwn2.dto.AdvisorClassSectionRequest;
import com.example.kthp_ltwn2.dto.AdvisorClassSectionResponse;
import com.example.kthp_ltwn2.service.AdvisorClassSectionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/advisor-class-sections")
public class AdvisorClassSectionApiController {

    private final AdvisorClassSectionService service;

    public AdvisorClassSectionApiController(AdvisorClassSectionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<AdvisorClassSectionResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.ok("Lấy danh sách phân công cố vấn thành công",
                service.getAll(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AdvisorClassSectionResponse>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.ok("Lấy chi tiết phân công cố vấn thành công",
                service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AdvisorClassSectionResponse>> create(
            @Valid @RequestBody AdvisorClassSectionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Tạo phân công cố vấn thành công", service.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AdvisorClassSectionResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody AdvisorClassSectionRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Cập nhật phân công cố vấn thành công",
                service.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        service.softDelete(id);
        return ResponseEntity.ok(ApiResponse.ok("Xóa phân công cố vấn thành công", null));
    }
}
