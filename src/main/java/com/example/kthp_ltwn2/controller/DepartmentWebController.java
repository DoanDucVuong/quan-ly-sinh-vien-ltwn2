package com.example.kthp_ltwn2.controller;

import com.example.kthp_ltwn2.dto.DepartmentRequest;
import com.example.kthp_ltwn2.dto.DepartmentResponse;
import com.example.kthp_ltwn2.service.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/departments")
public class DepartmentWebController {

    private final DepartmentService service;

    public DepartmentWebController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size, Model model) {
        Page<DepartmentResponse> departments = service.getAll(keyword, page, size);
        model.addAttribute("departments", departments.getContent());
        model.addAttribute("totalPages", departments.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("keyword", keyword);
        return "departments/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("deptReq", new DepartmentRequest());
        model.addAttribute("isEdit", false);
        return "departments/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute DepartmentRequest request, RedirectAttributes ra) {
        try {
            service.create(request);
            ra.addFlashAttribute("successMsg", "Thêm khoa thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/departments";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable UUID id, Model model) {
        DepartmentResponse res = service.getById(id);
        DepartmentRequest req = new DepartmentRequest();
        req.setCode(res.getCode());
        req.setName(res.getName());
        req.setDescription(res.getDescription());
        model.addAttribute("deptReq", req);
        model.addAttribute("deptId", id);
        model.addAttribute("isEdit", true);
        return "departments/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable UUID id, @ModelAttribute DepartmentRequest request, RedirectAttributes ra) {
        try {
            service.update(id, request);
            ra.addFlashAttribute("successMsg", "Cập nhật khoa thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/departments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable UUID id, RedirectAttributes ra) {
        service.softDelete(id);
        ra.addFlashAttribute("successMsg", "Xóa khoa thành công!");
        return "redirect:/departments";
    }
}
