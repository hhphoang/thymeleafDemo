package com.luv2code.springboot.thymeleafDemo.controller;

import com.luv2code.springboot.thymeleafDemo.entity.Employee;
import com.luv2code.springboot.thymeleafDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("ALL")
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String getListEmployees(Model theModel) {
        List<Employee> employeeList = employeeService.findAll();
        theModel.addAttribute("employeesList", employeeList);
        return "employees/listEmployee";
    }

    @GetMapping("/showAddForm")
    public String showAddEmployeeForm(Model theModel) {
        Employee newEmployee = new Employee();
        theModel.addAttribute("newEmployee", newEmployee);
        return "employees/addEmployeeForm";
    }

    @PostMapping("/save")
    public String saveNewEmployee(@ModelAttribute("newEmployee") Employee newEmployee) {
        employeeService.save(newEmployee);
        return "redirect:/employees/list";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("employeeId") int theId, Model theModel) {
        Employee updatedEmployee = employeeService.findById(theId);
        theModel.addAttribute("newEmployee",updatedEmployee);
        return "employees/addEmployeeForm";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int theId) {
        employeeService.deleteById(theId);
        return "redirect:/employees/list";
    }

}
