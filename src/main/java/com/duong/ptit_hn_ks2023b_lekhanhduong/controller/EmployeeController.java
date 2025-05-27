package com.duong.ptit_hn_ks2023b_lekhanhduong.controller;

import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.employee.CreateEmployeeDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.employee.EmployeeListDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.dto.employee.UpdateEmployeeDTO;
import com.duong.ptit_hn_ks2023b_lekhanhduong.model.Department;
import com.duong.ptit_hn_ks2023b_lekhanhduong.model.Employee;
import com.duong.ptit_hn_ks2023b_lekhanhduong.service.department.DepartmentService;
import com.duong.ptit_hn_ks2023b_lekhanhduong.service.employee.EmployeeService;
import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    @Autowired
    private ServletContext servletContext;

    private static final int PAGE_SIZE = 5;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService, ServletContext servletContext) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String showEmployeeList(@RequestParam(value = "page", defaultValue = "1") int page,
                                   Model model) {
        int offset = (page - 1) * PAGE_SIZE;

        List<EmployeeListDTO> employees = employeeService.getEmployeePageDTO(PAGE_SIZE, offset);
        int totalPages = employeeService.getTotalPages(PAGE_SIZE);

        model.addAttribute("employees", employees);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "employee_list";
    }

    @GetMapping("/add")
    public String showAddForm(@ModelAttribute("createDTO") CreateEmployeeDTO dto, Model model) {
        List<Department> departments = departmentService.getAllDepartment();
        model.addAttribute("departments", departments);
        return "employee_add";
    }


    @PostMapping("/add")
    public String handleAddEmployee(@Valid @ModelAttribute("createDTO") CreateEmployeeDTO dto,
                                    BindingResult result,
                                    Model model) {

        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartment());
            return "employee_add";
        }

        MultipartFile file = dto.getImageFile();
        try {
            String originalFilename = file.getOriginalFilename();
            String filename = (originalFilename != null) ? originalFilename.replaceAll("\\s+", "_") : "unknown.jpg";


            String realUploadPath = servletContext.getRealPath("/uploads");
            File realDir = new File(realUploadPath);
            if (!realDir.exists()) realDir.mkdirs();

            Path srcUploadPath = Paths.get("").toAbsolutePath()
                    .resolve("src").resolve("main").resolve("webapp").resolve("uploads");
            File srcDir = srcUploadPath.toFile();
            if (!srcDir.exists()) srcDir.mkdirs();


            byte[] bytes = file.getBytes();
            FileCopyUtils.copy(bytes, new File(realDir, filename));
            FileCopyUtils.copy(bytes, new File(srcDir, filename));

            dto.setAvatar(filename);

            boolean inserted = employeeService.insertEmployee(dto);
            if (!inserted) {
                model.addAttribute("message", "Failed to insert employee!");
                return "error";
            }

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "File upload error: " + e.getMessage());
            return "error";
        }

        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<Employee> opt = employeeService.findEmployeeById(id);
        if (opt.isEmpty()) {
            model.addAttribute("message", "Cannot find employee with ID = " + id);
            return "error";
        }

        Employee e = opt.get();

        UpdateEmployeeDTO dto = new UpdateEmployeeDTO(
                e.getId(),
                e.getName(),
                e.getEmail(),
                e.getPhone(),
                e.getAvatar(),
                e.getStatus(),
                e.getCreate_at(),
                e.getDepartment_id()
        );

        model.addAttribute("updateDTO", dto);
        model.addAttribute("departments", departmentService.getAllDepartment());

        return "employee_edit";
    }


    @PostMapping("/edit")
    public String handleEditEmployee(@ModelAttribute("updateDTO") @Valid UpdateEmployeeDTO dto,
                                     BindingResult result,
                                     Model model) {

        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartment());
            return "employee_edit";
        }

        MultipartFile newImage = dto.getImageFile();
        if (newImage != null && !newImage.isEmpty()) {
            try {
                String originalFilename = newImage.getOriginalFilename();
                String filename = (originalFilename != null) ? originalFilename.replaceAll("\\s+", "_") : "unknown.jpg";

                String realUploadPath = servletContext.getRealPath("/uploads");
                File realDir = new File(realUploadPath);
                if (!realDir.exists()) realDir.mkdirs();

                Path srcUploadPath = Paths.get("").toAbsolutePath()
                        .resolve("src").resolve("main").resolve("webapp").resolve("uploads");
                File srcDir = srcUploadPath.toFile();
                if (!srcDir.exists()) srcDir.mkdirs();

                byte[] bytes = newImage.getBytes();
                FileCopyUtils.copy(bytes, new File(realDir, filename));
                FileCopyUtils.copy(bytes, new File(srcDir, filename));

                dto.setAvatar(filename);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "File upload error: " + e.getMessage());
                return "error";
            }
        }

        boolean updated = employeeService.updateEmployee(dto);
        if (!updated) {
            model.addAttribute("message", "Failed to update employee!");
            return "error";
        }

        return "redirect:/employees";
    }


    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id, Model model) {
        Optional<Employee> employeeOpt = employeeService.findEmployeeById(id);

        if (employeeOpt.isEmpty()) {
            model.addAttribute("message", "Cannot find employee with id = " + id);
            return "error";
        }

        boolean deleted = employeeService.deleteEmployee(id);
        if (!deleted) {
            model.addAttribute("message", "Failed to delete employee!");
            return "error";
        }

        return "redirect:/employees";
    }


    @GetMapping("/search")
    public String searchEmployee(@RequestParam("keyword") String keyword,
                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                 Model model) {
        int offset = (page - 1) * PAGE_SIZE;

        List<EmployeeListDTO> employees = employeeService.searchEmployeePageDTO(keyword, PAGE_SIZE, offset);
        int totalPages = (int) Math.ceil((double) employees.size() / PAGE_SIZE);

        model.addAttribute("employees", employees);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("keyword", keyword);

        return "employee_list";
    }
}
