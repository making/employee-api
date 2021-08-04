package com.example.employee;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class EmployeeCriteria {
	private String firstName;

	private String lastName;

	private Integer salaryMin;

	private Integer salaryMax;

	public MapSqlParameterSource toSqlParameterSource() {
		return new MapSqlParameterSource()
				.addValue("firstName", this.firstName)
				.addValue("lastName", this.lastName)
				.addValue("salaryMin", this.salaryMin)
				.addValue("salaryMax", this.salaryMax);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getSalaryMin() {
		return salaryMin;
	}

	public void setSalaryMin(Integer salaryMin) {
		this.salaryMin = salaryMin;
	}

	public Integer getSalaryMax() {
		return salaryMax;
	}

	public void setSalaryMax(Integer salaryMax) {
		this.salaryMax = salaryMax;
	}
}
