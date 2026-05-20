package com.example.kthp_ltwn2.controller;

import com.example.kthp_ltwn2.dto.StudentClasseSectionRequest;
import com.example.kthp_ltwn2.dto.StudentClasseSectionResponse;
import com.example.kthp_ltwn2.service.StudentClasseSectionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/student-classe-sections")
public class StudentClasseSectionWebController {

    private final StudentClasseSectionService service;
    private final com.example.kthp_ltwn2.service.StudentService studentService;
    private final com.example.kthp_ltwn2.service.StudentClassService studentClassService;

    public StudentClasseSectionWebController(StudentClasseSectionService service, com.example.kthp_ltwn2.service.StudentService studentService, com.example.kthp_ltwn2.service.StudentClassService studentClassService) {
        this.service = service;
        this.studentService = studentService;
        this.studentClassService = studentClassService;
    }



    @GetMapping
    public String list(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size) {
        Page<StudentClasseSectionResponse> sections = service.getAll(page, size);
        model.addAttribute("sections", sections);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", sections.getTotalPages());
        model.addAttribute("totalElements", sections.getTotalElements());
        model.addAttribute("pageSize", size);
        return "student-classe-sections/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("section", new StudentClasseSectionRequest());
        model.addAttribute("isEdit", false);
        model.addAttribute("students", studentService.getAll("", 0, 1000).getContent());
        model.addAttribute("classes", studentClassService.getAll("", 0, 1000).getContent());
        return "student-classe-sections/form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("section") StudentClasseSectionRequest request,
                         BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "student-classe-sections/form";
        }
        try {
            service.create(request);
            ra.addFlashAttribute("successMsg", "✅ Thêm phân lớp thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "❌ " + e.getMessage());
        }
        return "redirect:/student-classe-sections";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable UUID id, Model model) {
        StudentClasseSectionResponse res = service.getById(id);
        StudentClasseSectionRequest req = StudentClasseSectionRequest.builder()
                .studentId(res.getStudentId())
                .studentClasseId(res.getStudentClasseId())
                .status(res.getStatus())
                .note(res.getNote())
                .startDate(res.getStartDate())
                .endDate(res.getEndDate())
                .build();
        model.addAttribute("section", req);
        model.addAttribute("sectionId", id);
        model.addAttribute("isEdit", true);
        model.addAttribute("students", studentService.getAll("", 0, 1000).getContent());
        model.addAttribute("classes", studentClassService.getAll("", 0, 1000).getContent());
        return "student-classe-sections/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable UUID id,
                         @Valid @ModelAttribute("section") StudentClasseSectionRequest request,
                         BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            model.addAttribute("sectionId", id);
            return "student-classe-sections/form";
        }
        try {
            service.update(id, request);
            ra.addFlashAttribute("successMsg", "✅ Cập nhật phân lớp thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "❌ " + e.getMessage());
        }
        return "redirect:/student-classe-sections";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable UUID id, RedirectAttributes ra) {
        try {
            service.softDelete(id);
            ra.addFlashAttribute("successMsg", "✅ Xóa phân lớp thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "❌ " + e.getMessage());
        }
        return "redirect:/student-classe-sections";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable UUID id, Model model) {
        model.addAttribute("section", service.getById(id));
        return "student-classe-sections/detail";
    }
}
