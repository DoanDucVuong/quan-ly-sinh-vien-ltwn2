package com.example.kthp_ltwn2.controller;

import com.example.kthp_ltwn2.dto.ApiResponse;
import com.example.kthp_ltwn2.dto.StudentClassRequest;
import com.example.kthp_ltwn2.dto.StudentClassResponse;
import com.example.kthp_ltwn2.service.StudentClassService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/student-classes")
public class StudentClassApiController {

    private final StudentClassService service;
    public StudentClassApiController(StudentClassService service) {
        this.service = service;
    }



    /** GET /api/student-classes?keyword=&page=0&size=10 */
    @GetMapping
    public ResponseEntity<ApiResponse<Page<StudentClassResponse>>> getAll(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.ok("Lấy danh sách lớp thành công",
                service.getAll(keyword, page, size)));
    }

    /** GET /api/student-classes/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentClassResponse>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.ok("Lấy thông tin lớp thành công",
                service.getById(id)));
    }

    /** POST /api/student-classes */
    @PostMapping
    public ResponseEntity<ApiResponse<StudentClassResponse>> create(
            @Valid @RequestBody StudentClassRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Tạo lớp thành công", service.create(request)));
    }

    /** PUT /api/student-classes/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentClassResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody StudentClassRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Cập nhật lớp thành công",
                service.update(id, request)));
    }

    /** DELETE /api/student-classes/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        service.softDelete(id);
        return ResponseEntity.ok(ApiResponse.ok("Xóa lớp thành công", null));
    }
}
