package com.greatlearning.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greatlearning.entity.Employee;
import com.greatlearning.repository.EmployeeJpaRepository;
import com.greatlearning.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeSeviceImpl implements EmployeeService {

	@Autowired
	EmployeeJpaRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(long id) {
		return employeeRepository.findById(id).get();
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		return employeeRepository.findByFirstNameLike(name);

	}

	@Override
	public List<Employee> getEmployeeSortedByName(String strDirection) {
		Sort.Direction direction = strDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		return employeeRepository.findAll(Sort.by(direction, "firstName"));
	}

	@Override
	public Employee updateById(long id, Employee employee) {
		Employee existingEmployee = getEmployeeById(id);
		existingEmployee.setId(id);
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		return saveEmployee(existingEmployee);
	}

	@Override
	public void deleteById(long id) {
		employeeRepository.deleteById(id);

	}

}
