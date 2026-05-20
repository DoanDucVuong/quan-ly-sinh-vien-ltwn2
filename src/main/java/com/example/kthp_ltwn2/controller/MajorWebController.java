package com.example.kthp_ltwn2.controller;
import com.example.kthp_ltwn2.dto.MajorRequest;
import com.example.kthp_ltwn2.dto.MajorResponse;
import com.example.kthp_ltwn2.service.MajorService;
import com.example.kthp_ltwn2.service.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.UUID;

@Controller
@RequestMapping("/majors")
public class MajorWebController {
    private final MajorService service;
    private final DepartmentService departmentService;
    public MajorWebController(MajorService service, DepartmentService departmentService) { 
        this.service = service; 
        this.departmentService = departmentService;
    }
    @GetMapping
    public String list(@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        Page<MajorResponse> data = service.getAll(keyword, page, size);
        model.addAttribute("majors", data.getContent());
        model.addAttribute("totalPages", data.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("keyword", keyword);
        return "majors/list";
    }
    @GetMapping("/create")
    public String createForm(Model model) { 
        model.addAttribute("majorReq", new MajorRequest()); 
        model.addAttribute("isEdit", false);
        model.addAttribute("departments", departmentService.getAll("", 0, 1000).getContent());
        return "majors/form"; 
    }
    @PostMapping("/create")
    public String create(@ModelAttribute MajorRequest request, RedirectAttributes ra) {
        try { service.create(request); ra.addFlashAttribute("successMsg", "Thêm ngành thành công!"); } catch (Exception e) { ra.addFlashAttribute("errorMsg", e.getMessage()); }
        return "redirect:/majors";
    }
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable UUID id, Model model) {
        MajorResponse res = service.getById(id);
        MajorRequest req = new MajorRequest();
        req.setCode(res.getCode()); req.setName(res.getName()); req.setDepartmentId(res.getDepartmentId());
        model.addAttribute("majorReq", req); model.addAttribute("majorId", id); model.addAttribute("isEdit", true);
        model.addAttribute("departments", departmentService.getAll("", 0, 1000).getContent());
        return "majors/form";
    }
    @PostMapping("/{id}/edit")
    public String update(@PathVariable UUID id, @ModelAttribute MajorRequest request, RedirectAttributes ra) {
        try { service.update(id, request); ra.addFlashAttribute("successMsg", "Cập nhật thành công!"); } catch (Exception e) { ra.addFlashAttribute("errorMsg", e.getMessage()); }
        return "redirect:/majors";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable UUID id, RedirectAttributes ra) { service.softDelete(id); ra.addFlashAttribute("successMsg", "Xóa thành công!"); return "redirect:/majors"; }
}
