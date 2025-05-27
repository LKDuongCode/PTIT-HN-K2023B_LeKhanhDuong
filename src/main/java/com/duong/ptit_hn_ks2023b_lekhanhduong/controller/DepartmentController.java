package com.duong.ptit_hn_ks2023b_lekhanhduong.controller;

import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.department.CreateDepartmentDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.department.UpdateDepartmentDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.model.Department;
import com.duong.ptit_hn_ks2023b_lekhanhduong.service.department.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String showList(Model model) {
        List<Department> departments = departmentService.getAllDepartment();
        model.addAttribute("departments", departments);
        return "department_list";
    }

    @GetMapping("/add")
    public String showAddForm(@ModelAttribute("createDTO") CreateDepartmentDTO dto) {
        return "department_add";
    }

    @PostMapping("/add")
    public String handleAddDepartment(@Valid @ModelAttribute("createDTO") CreateDepartmentDTO dto,
                                      BindingResult result,
                                      Model model) {
        if (result.hasErrors()) return "department_add";

        boolean inserted = departmentService.insertDepartment(dto);
        if (!inserted) {
            model.addAttribute("message", "Failed to insert department!");
            return "error";
        }

        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<Department> opt = departmentService.findDepartmentById(id);
        if (opt.isPresent()) {
            Department d = opt.get();
            UpdateDepartmentDTO dto = new UpdateDepartmentDTO(
                    d.getId(), d.getName(), d.getDescription(), d.getStatus()
            );
            model.addAttribute("updateDTO", dto);
            return "department_edit";
        }
        model.addAttribute("message", "Cannot find department with ID: " + id);
        return "error";
    }

    @PostMapping("/edit")
    public String handleEditDepartment(@Valid @ModelAttribute("updateDTO") UpdateDepartmentDTO dto,
                                       BindingResult result,
                                       Model model) {
        if (result.hasErrors()) return "department_edit";

        boolean updated = departmentService.updateDepartment(dto);
        if (!updated) {
            model.addAttribute("message", "Failed to update department!");
            return "error";
        }

        return "redirect:/departments";
    }

    @PostMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        int employeeCount = departmentService.countEmployeeInDepartment(id);

        if (employeeCount > 0) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Cannot delete department because it still has " + employeeCount + " employees.");
            return "redirect:/departments";
        }

        boolean deleted = departmentService.deleteDepartment(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete department with ID: " + id);
            return "redirect:/departments";
        }
        return "redirect:/departments";
    }


    @GetMapping("/search")
    public String searchDepartment(@RequestParam(value = "keyword", required = false) String keyword,
                                   Model model) {

        List<Department> departments = new ArrayList<>();
        if (keyword != null && !keyword.isBlank()) {
            departments = departmentService.searchDepartmentByNameLike(keyword);
        }

        model.addAttribute("departments", departments);
        model.addAttribute("keyword", keyword);
        return "department_list";
    }

}
