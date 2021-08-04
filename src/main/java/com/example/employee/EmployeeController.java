package com.example.employee;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	private final EmployeeMapper employeeMapper;

	public EmployeeController(EmployeeMapper employeeMapper) {
		this.employeeMapper = employeeMapper;
	}

	@GetMapping(path = "employees/{id}")
	public Employee getEmployee(@PathVariable Integer id) {
		return this.employeeMapper.findById(id);
	}

	@GetMapping(path = "employees")
	public List<Employee> getEmployees(EmployeeCriteria criteria) {
		return this.employeeMapper.findAll(criteria);
	}
}
