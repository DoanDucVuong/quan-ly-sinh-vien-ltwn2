package com.example.kthp_ltwn2.controller;

import com.example.kthp_ltwn2.dto.StudentStatusRequest;
import com.example.kthp_ltwn2.dto.StudentStatusResponse;
import com.example.kthp_ltwn2.service.StudentStatusService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/student-status")
public class StudentStatusWebController {

    private final StudentStatusService service;
    private final com.example.kthp_ltwn2.service.StudentService studentService;

    public StudentStatusWebController(StudentStatusService service,
            com.example.kthp_ltwn2.service.StudentService studentService) {
        this.service = service;
        this.studentService = studentService;
    }

    @GetMapping
    public String list(Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<StudentStatusResponse> statuses = service.getAll(page, size);
        model.addAttribute("statuses", statuses);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", statuses.getTotalPages());
        model.addAttribute("totalElements", statuses.getTotalElements());
        model.addAttribute("pageSize", size);
        return "student-status/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable UUID id, Model model) {
        StudentStatusResponse res = service.getById(id);
        model.addAttribute("status", res);
        return "student-status/detail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("statusReq", new StudentStatusRequest());
        model.addAttribute("isEdit", false);
        model.addAttribute("students", studentService.getAll("", 0, 1000).getContent());
        return "student-status/form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("statusReq") StudentStatusRequest request,
            BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "student-status/form";
        }
        try {
            service.create(request);
            ra.addFlashAttribute("successMsg", "✅ Cập nhật trạng thái thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "❌ " + e.getMessage());
        }
        return "redirect:/student-status";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable UUID id, Model model) {
        StudentStatusResponse res = service.getById(id);
        StudentStatusRequest req = new StudentStatusRequest();
        req.setStudentId(res.getStudentId());
        req.setStatusCode(res.getStatusCode());
        req.setStatusName(res.getStatusName());
        req.setStartDate(res.getStartDate());
        req.setEndDate(res.getEndDate());
        req.setDescription(res.getDescription());
        req.setReason(res.getReason());

        model.addAttribute("statusReq", req);
        model.addAttribute("statusId", id);
        model.addAttribute("isEdit", true);
        model.addAttribute("students", studentService.getAll("", 0, 1000).getContent());
        return "student-status/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable UUID id,
            @Valid @ModelAttribute("statusReq") StudentStatusRequest request,
            BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            model.addAttribute("statusId", id);
            return "student-status/form";
        }
        try {
            service.update(id, request);
            ra.addFlashAttribute("successMsg", "✅ Cập nhật trạng thái thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "❌ " + e.getMessage());
        }
        return "redirect:/student-status";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable UUID id, RedirectAttributes ra) {
        try {
            service.softDelete(id);
            ra.addFlashAttribute("successMsg", "✅ Xóa trạng thái thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "❌ " + e.getMessage());
        }
        return "redirect:/student-status";
    }
}
