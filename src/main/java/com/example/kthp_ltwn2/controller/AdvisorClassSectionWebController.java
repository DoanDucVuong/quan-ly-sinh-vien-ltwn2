package com.example.kthp_ltwn2.controller;

import com.example.kthp_ltwn2.dto.AdvisorClassSectionRequest;
import com.example.kthp_ltwn2.dto.AdvisorClassSectionResponse;
import com.example.kthp_ltwn2.service.AdvisorClassSectionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/advisor-class-sections")
public class AdvisorClassSectionWebController {

    private final AdvisorClassSectionService service;
    private final com.example.kthp_ltwn2.service.StudentClassService studentClassService;

    public AdvisorClassSectionWebController(AdvisorClassSectionService service, com.example.kthp_ltwn2.service.StudentClassService studentClassService) {
        this.service = service;
        this.studentClassService = studentClassService;
    }

    @GetMapping
    public String list(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size) {
        Page<AdvisorClassSectionResponse> sections = service.getAll(page, size);
        model.addAttribute("sections", sections);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", sections.getTotalPages());
        model.addAttribute("totalElements", sections.getTotalElements());
        model.addAttribute("pageSize", size);
        return "advisor-class-sections/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable UUID id, Model model) {
        AdvisorClassSectionResponse res = service.getById(id);
        model.addAttribute("advisor", res);
        return "advisor-class-sections/detail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("advisorReq", new AdvisorClassSectionRequest());
        model.addAttribute("isEdit", false);
        model.addAttribute("classes", studentClassService.getAll("", 0, 1000).getContent());
        return "advisor-class-sections/form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("advisorReq") AdvisorClassSectionRequest request,
                         BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "advisor-class-sections/form";
        }
        try {
            service.create(request);
            ra.addFlashAttribute("successMsg", "✅ Phân công cố vấn thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "❌ " + e.getMessage());
        }
        return "redirect:/advisor-class-sections";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable UUID id, Model model) {
        AdvisorClassSectionResponse res = service.getById(id);
        AdvisorClassSectionRequest req = new AdvisorClassSectionRequest();
        req.setStudentClasseId(res.getStudentClasseId());
        req.setEmployeeId(res.getEmployeeId());
        req.setStartDate(res.getStartDate());
        req.setEndDate(res.getEndDate());
        req.setDescription(res.getDescription());
        req.setReason(res.getReason());
        
        model.addAttribute("advisorReq", req);
        model.addAttribute("advisorId", id);
        model.addAttribute("isEdit", true);
        model.addAttribute("classes", studentClassService.getAll("", 0, 1000).getContent());
        return "advisor-class-sections/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable UUID id,
                         @Valid @ModelAttribute("advisorReq") AdvisorClassSectionRequest request,
                         BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            model.addAttribute("advisorId", id);
            return "advisor-class-sections/form";
        }
        try {
            service.update(id, request);
            ra.addFlashAttribute("successMsg", "✅ Cập nhật phân công thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "❌ " + e.getMessage());
        }
        return "redirect:/advisor-class-sections";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable UUID id, RedirectAttributes ra) {
        try {
            service.softDelete(id);
            ra.addFlashAttribute("successMsg", "✅ Xóa phân công thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "❌ " + e.getMessage());
        }
        return "redirect:/advisor-class-sections";
    }
}
