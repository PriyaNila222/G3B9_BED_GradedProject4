package com.greatlearning.service;

import java.util.List;

import com.greatlearning.entity.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();

	Employee saveEmployee(Employee employee);

	Employee getEmployeeById(long id);

	List<Employee> getEmployeeByName(String name);

	List<Employee> getEmployeeSortedByName(String strdirection);

	Employee updateById(long id, Employee employee);

	void deleteById(long id);

}
