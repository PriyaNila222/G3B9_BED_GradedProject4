package com.greatlearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.entity.Employee;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByFirstNameLike(String firstName);

}
