package com.example.kthp_ltwn2.controller;

import com.example.kthp_ltwn2.dto.StudentClassRequest;
import com.example.kthp_ltwn2.dto.StudentClassResponse;
import com.example.kthp_ltwn2.service.StudentClassService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/student-classes")
public class StudentClassWebController {

    private final StudentClassService service;
    public StudentClassWebController(StudentClassService service) {
        this.service = service;
    }



    @GetMapping
    public String list(Model model,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size) {
        Page<StudentClassResponse> classes = service.getAll(keyword, page, size);
        model.addAttribute("classes", classes);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", classes.getTotalPages());
        model.addAttribute("totalElements", classes.getTotalElements());
        model.addAttribute("pageSize", size);
        return "student-classes/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("studentClass", new StudentClassRequest());
        model.addAttribute("isEdit", false);
        return "student-classes/form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("studentClass") StudentClassRequest request,
                         BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "student-classes/form";
        }
        try {
            service.create(request);
            ra.addFlashAttribute("successMsg", "✅ Thêm lớp học thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "❌ " + e.getMessage());
        }
        return "redirect:/student-classes";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable UUID id, Model model) {
        StudentClassResponse sc = service.getById(id);
        StudentClassRequest req = StudentClassRequest.builder()
                .code(sc.getCode()).name(sc.getName())
                .academicYearId(sc.getAcademicYearId())
                .departmentId(sc.getDepartmentId())
                .majorId(sc.getMajorId())
                .trainingProgramId(sc.getTrainingProgramId())
                .employeeId(sc.getEmployeeId())
                .build();
        model.addAttribute("studentClass", req);
        model.addAttribute("classId", id);
        model.addAttribute("isEdit", true);
        return "student-classes/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable UUID id,
                         @Valid @ModelAttribute("studentClass") StudentClassRequest request,
                         BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            model.addAttribute("classId", id);
            return "student-classes/form";
        }
        try {
            service.update(id, request);
            ra.addFlashAttribute("successMsg", "✅ Cập nhật lớp học thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "❌ " + e.getMessage());
        }
        return "redirect:/student-classes";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable UUID id, RedirectAttributes ra) {
        try {
            service.softDelete(id);
            ra.addFlashAttribute("successMsg", "✅ Xóa lớp học thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "❌ " + e.getMessage());
        }
        return "redirect:/student-classes";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable UUID id, Model model) {
        model.addAttribute("sc", service.getById(id));
        return "student-classes/detail";
    }
}
