package com.example.kthp_ltwn2.controller;

import com.example.kthp_ltwn2.dto.ApiResponse;
import com.example.kthp_ltwn2.dto.StudentRequest;
import com.example.kthp_ltwn2.dto.StudentResponse;
import com.example.kthp_ltwn2.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentApiController {

    private final StudentService studentService;
    public StudentApiController(StudentService studentService) {
        this.studentService = studentService;
    }



    /** GET /api/students?keyword=&page=0&size=10 */
    @GetMapping
    public ResponseEntity<ApiResponse<Page<StudentResponse>>> getAll(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<StudentResponse> data = studentService.getAll(keyword, page, size);
        return ResponseEntity.ok(ApiResponse.ok("Lấy danh sách sinh viên thành công", data));
    }

    /** GET /api/students/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.ok("Lấy thông tin sinh viên thành công",
                studentService.getById(id)));
    }

    /** POST /api/students */
    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponse>> create(
            @Valid @RequestBody StudentRequest request) {
        StudentResponse data = studentService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Tạo sinh viên thành công", data));
    }

    /** PUT /api/students/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody StudentRequest request) {
        return ResponseEntity.ok(ApiResponse.ok("Cập nhật sinh viên thành công",
                studentService.update(id, request)));
    }

    /** DELETE /api/students/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        studentService.softDelete(id);
        return ResponseEntity.ok(ApiResponse.ok("Xóa sinh viên thành công", null));
    }
}
