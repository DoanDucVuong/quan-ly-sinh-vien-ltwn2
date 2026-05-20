package com.example.kthp_ltwn2.controller;
import com.example.kthp_ltwn2.dto.TrainingProgramRequest;
import com.example.kthp_ltwn2.dto.TrainingProgramResponse;
import com.example.kthp_ltwn2.service.TrainingProgramService;
import com.example.kthp_ltwn2.service.MajorService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.UUID;

@Controller
@RequestMapping("/training-programs")
public class TrainingProgramWebController {
    private final TrainingProgramService service;
    private final MajorService majorService;
    public TrainingProgramWebController(TrainingProgramService service, MajorService majorService) { 
        this.service = service; 
        this.majorService = majorService;
    }
    @GetMapping
    public String list(@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        Page<TrainingProgramResponse> data = service.getAll(keyword, page, size);
        model.addAttribute("programs", data.getContent());
        model.addAttribute("totalPages", data.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("keyword", keyword);
        return "training-programs/list";
    }
    @GetMapping("/create")
    public String createForm(Model model) { 
        model.addAttribute("progReq", new TrainingProgramRequest()); 
        model.addAttribute("isEdit", false);
        model.addAttribute("majors", majorService.getAll("", 0, 1000).getContent());
        return "training-programs/form"; 
    }
    @PostMapping("/create")
    public String create(@ModelAttribute TrainingProgramRequest request, RedirectAttributes ra) {
        try { service.create(request); ra.addFlashAttribute("successMsg", "Thêm chương trình học thành công!"); } catch (Exception e) { ra.addFlashAttribute("errorMsg", e.getMessage()); }
        return "redirect:/training-programs";
    }
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable UUID id, Model model) {
        TrainingProgramResponse res = service.getById(id);
        TrainingProgramRequest req = new TrainingProgramRequest();
        req.setCode(res.getCode()); req.setName(res.getName()); req.setMajorId(res.getMajorId()); req.setDegreeLevel(res.getDegreeLevel());
        model.addAttribute("progReq", req); model.addAttribute("progId", id); model.addAttribute("isEdit", true);
        model.addAttribute("majors", majorService.getAll("", 0, 1000).getContent());
        return "training-programs/form";
    }
    @PostMapping("/{id}/edit")
    public String update(@PathVariable UUID id, @ModelAttribute TrainingProgramRequest request, RedirectAttributes ra) {
        try { service.update(id, request); ra.addFlashAttribute("successMsg", "Cập nhật thành công!"); } catch (Exception e) { ra.addFlashAttribute("errorMsg", e.getMessage()); }
        return "redirect:/training-programs";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable UUID id, RedirectAttributes ra) { service.softDelete(id); ra.addFlashAttribute("successMsg", "Xóa thành công!"); return "redirect:/training-programs"; }
}
