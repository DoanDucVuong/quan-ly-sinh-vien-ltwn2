package com.example.kthp_ltwn2.controller;

import com.example.kthp_ltwn2.dto.StudentRequest;
import com.example.kthp_ltwn2.dto.StudentResponse;
import com.example.kthp_ltwn2.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/students")
public class StudentWebController {

    private final StudentService studentService;
    private final com.example.kthp_ltwn2.service.StudentClassService studentClassService;
    private final com.example.kthp_ltwn2.service.AcademicYearService academicYearService;
    private final com.example.kthp_ltwn2.service.DepartmentService departmentService;
    private final com.example.kthp_ltwn2.service.MajorService majorService;
    private final com.example.kthp_ltwn2.service.TrainingProgramService trainingProgramService;

    public StudentWebController(StudentService studentService, 
                                com.example.kthp_ltwn2.service.StudentClassService studentClassService,
                                com.example.kthp_ltwn2.service.AcademicYearService academicYearService,
                                com.example.kthp_ltwn2.service.DepartmentService departmentService,
                                com.example.kthp_ltwn2.service.MajorService majorService,
                                com.example.kthp_ltwn2.service.TrainingProgramService trainingProgramService) {
        this.studentService = studentService;
        this.studentClassService = studentClassService;
        this.academicYearService = academicYearService;
        this.departmentService = departmentService;
        this.majorService = majorService;
        this.trainingProgramService = trainingProgramService;
    }



    @GetMapping
    public String list(Model model,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size) {
        Page<StudentResponse> students = studentService.getAll(keyword, page, size);
        model.addAttribute("students", students);
        model.addAttribute("classes", studentClassService.getAll("", 0, 1000).getContent());
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", students.getTotalPages());
        model.addAttribute("totalElements", students.getTotalElements());
        model.addAttribute("pageSize", size);
        return "students/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("student", new StudentRequest());
        model.addAttribute("isEdit", false);
        model.addAttribute("classes", studentClassService.getAll("", 0, 1000).getContent());
        model.addAttribute("academicYears", academicYearService.getAll("", 0, 1000).getContent());
        model.addAttribute("departments", departmentService.getAll("", 0, 1000).getContent());
        model.addAttribute("majors", majorService.getAll("", 0, 1000).getContent());
        model.addAttribute("programs", trainingProgramService.getAll("", 0, 1000).getContent());
        return "students/form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("student") StudentRequest request,
                         BindingResult result,
                         Model model,
                         RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "students/form";
        }
        try {
            studentService.create(request);
            ra.addFlashAttribute("successMsg", "✅ Thêm sinh viên thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "❌ " + e.getMessage());
        }
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable UUID id, Model model) {
        StudentResponse sv = studentService.getById(id);
        StudentRequest req = StudentRequest.builder()
                .code(sv.getCode()).fullName(sv.getFullName())
                .dateOfBirth(sv.getDateOfBirth()).gender(sv.getGender())
                .personalIdentificationNumber(sv.getPersonalIdentificationNumber())
                .dateOfIssue(sv.getDateOfIssue()).cardPlace(sv.getCardPlace())
                .address(sv.getAddress()).currentAddress(sv.getCurrentAddress())
                .status(sv.getStatus()).admissionYear(sv.getAdmissionYear())
                .departmentId(sv.getDepartmentId()).majorId(sv.getMajorId())
                .trainingProgramId(sv.getTrainingProgramId())
                .studentClasseId(sv.getStudentClasseId())
                .academicYearYear(sv.getAcademicYearYear())
                .build();
        model.addAttribute("student", req);
        model.addAttribute("studentId", id);
        model.addAttribute("isEdit", true);
        model.addAttribute("classes", studentClassService.getAll("", 0, 1000).getContent());
        model.addAttribute("academicYears", academicYearService.getAll("", 0, 1000).getContent());
        model.addAttribute("departments", departmentService.getAll("", 0, 1000).getContent());
        model.addAttribute("majors", majorService.getAll("", 0, 1000).getContent());
        model.addAttribute("programs", trainingProgramService.getAll("", 0, 1000).getContent());
        return "students/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable UUID id,
                         @Valid @ModelAttribute("student") StudentRequest request,
                         BindingResult result,
                         Model model,
                         RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            model.addAttribute("studentId", id);
            return "students/form";
        }
        try {
            studentService.update(id, request);
            ra.addFlashAttribute("successMsg", "✅ Cập nhật sinh viên thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "❌ " + e.getMessage());
        }
        return "redirect:/students";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable UUID id, RedirectAttributes ra) {
        try {
            studentService.softDelete(id);
            ra.addFlashAttribute("successMsg", "✅ Xóa sinh viên thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMsg", "❌ " + e.getMessage());
        }
        return "redirect:/students";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable UUID id, Model model) {
        model.addAttribute("student", studentService.getById(id));
        return "students/detail";
    }
}
