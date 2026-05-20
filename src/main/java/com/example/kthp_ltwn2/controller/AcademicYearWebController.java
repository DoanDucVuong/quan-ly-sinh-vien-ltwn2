package com.example.kthp_ltwn2.controller;
import com.example.kthp_ltwn2.dto.AcademicYearRequest;
import com.example.kthp_ltwn2.dto.AcademicYearResponse;
import com.example.kthp_ltwn2.service.AcademicYearService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.UUID;

@Controller
@RequestMapping("/academic-years")
public class AcademicYearWebController {
    private final AcademicYearService service;
    public AcademicYearWebController(AcademicYearService service) { this.service = service; }
    @GetMapping
    public String list(@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        Page<AcademicYearResponse> data = service.getAll(keyword, page, size);
        model.addAttribute("years", data.getContent());
        model.addAttribute("totalPages", data.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("keyword", keyword);
        return "academic-years/list";
    }
    @GetMapping("/create")
    public String createForm(Model model) { model.addAttribute("yearReq", new AcademicYearRequest()); model.addAttribute("isEdit", false); return "academic-years/form"; }
    @PostMapping("/create")
    public String create(@ModelAttribute AcademicYearRequest request, RedirectAttributes ra) {
        try { service.create(request); ra.addFlashAttribute("successMsg", "Thêm năm học thành công!"); } catch (Exception e) { ra.addFlashAttribute("errorMsg", e.getMessage()); }
        return "redirect:/academic-years";
    }
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable UUID id, Model model) {
        AcademicYearResponse res = service.getById(id);
        AcademicYearRequest req = new AcademicYearRequest();
        req.setCode(res.getCode()); req.setName(res.getName()); req.setStartYear(res.getStartYear()); req.setEndYear(res.getEndYear());
        model.addAttribute("yearReq", req); model.addAttribute("yearId", id); model.addAttribute("isEdit", true);
        return "academic-years/form";
    }
    @PostMapping("/{id}/edit")
    public String update(@PathVariable UUID id, @ModelAttribute AcademicYearRequest request, RedirectAttributes ra) {
        try { service.update(id, request); ra.addFlashAttribute("successMsg", "Cập nhật thành công!"); } catch (Exception e) { ra.addFlashAttribute("errorMsg", e.getMessage()); }
        return "redirect:/academic-years";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable UUID id, RedirectAttributes ra) { service.softDelete(id); ra.addFlashAttribute("successMsg", "Xóa thành công!"); return "redirect:/academic-years"; }
}
