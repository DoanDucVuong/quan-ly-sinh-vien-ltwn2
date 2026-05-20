package com.example.kthp_ltwn2.controller;

import com.example.kthp_ltwn2.dto.ApiResponse;
import com.example.kthp_ltwn2.dto.StudentStatusRequest;
import com.example.kthp_ltwn2.dto.StudentStatusResponse;
import com.example.kthp_ltwn2.service.StudentStatusService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/student-status")
public class StudentStatusApiController {

    private final StudentStatusService service;

    public StudentStatusApiController(StudentStatusService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<StudentStatusResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.ok("Lấy danh sách trạng thái thành công",
                service.getAll(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentStatusResponse>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.ok("Lấy chi tiết trạng thái thành công",
                service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentStatusResponse>> create(
            @Valid @RequestBody StudentStatusRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Tạo trạng thái thành công", service.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentStatusResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody StudentStatusRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Cập nhật trạng thái thành công",
                service.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        service.softDelete(id);
        return ResponseEntity.ok(ApiResponse.ok("Xóa trạng thái thành công", null));
    }
}
