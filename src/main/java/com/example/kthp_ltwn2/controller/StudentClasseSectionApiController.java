package com.example.kthp_ltwn2.controller;

import com.example.kthp_ltwn2.dto.ApiResponse;
import com.example.kthp_ltwn2.dto.StudentClasseSectionRequest;
import com.example.kthp_ltwn2.dto.StudentClasseSectionResponse;
import com.example.kthp_ltwn2.service.StudentClasseSectionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/student-classe-sections")
public class StudentClasseSectionApiController {

    private final StudentClasseSectionService service;
    public StudentClasseSectionApiController(StudentClasseSectionService service) {
        this.service = service;
    }



    /** GET /api/student-classe-sections?page=0&size=10 */
    @GetMapping
    public ResponseEntity<ApiResponse<Page<StudentClasseSectionResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.ok("Lấy danh sách phân lớp thành công",
                service.getAll(page, size)));
    }

    /** GET /api/student-classe-sections/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentClasseSectionResponse>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.ok("Lấy thông tin phân lớp thành công",
                service.getById(id)));
    }

    /** POST /api/student-classe-sections */
    @PostMapping
    public ResponseEntity<ApiResponse<StudentClasseSectionResponse>> create(
            @Valid @RequestBody StudentClasseSectionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Tạo phân lớp thành công", service.create(request)));
    }

    /** PUT /api/student-classe-sections/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentClasseSectionResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody StudentClasseSectionRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Cập nhật phân lớp thành công",
                service.update(id, request)));
    }

    /** DELETE /api/student-classe-sections/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        service.softDelete(id);
        return ResponseEntity.ok(ApiResponse.ok("Xóa phân lớp thành công", null));
    }
}
