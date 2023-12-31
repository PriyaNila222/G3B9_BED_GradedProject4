package com.greatlearning.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.entity.Employee;
import com.greatlearning.entity.Role;
import com.greatlearning.entity.User;
import com.greatlearning.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping
	public List<Employee> listEmployees() {
		return employeeService.getAllEmployees();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String save(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
		return "Employee details saved";
	}

	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable long id) {
		return employeeService.getEmployeeById(id);
	}

	@GetMapping("/search/{name}")
	public List<Employee> getEmployeeByName(@PathVariable String name) {
		return employeeService.getEmployeeByName(name);
	}

	@GetMapping("/sort")
	public List<Employee> getEmployeeSortedByName(
			@RequestParam(name = "order", required = false, defaultValue = "asc") String direction) {
		return employeeService.getEmployeeSortedByName(direction);
	}

	@PutMapping("/{id}")
	public String update(@PathVariable long id, @RequestBody Employee employee) {
		employeeService.updateById(id, employee);
		return "Employee id: " + id + " got updated";
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long id) {
		employeeService.deleteById(id);
	}

}
