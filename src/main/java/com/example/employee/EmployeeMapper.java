package com.example.employee;

import java.util.List;

import org.mybatis.scripting.thymeleaf.SqlGenerator;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import static com.example.employee.FileLoader.load;

@Repository
public class EmployeeMapper {
	private final NamedParameterJdbcTemplate jdbcTemplate;

	private final SqlGenerator sqlGenerator;

	private final RowMapper<Employee> rowMapper = new DataClassRowMapper<>(Employee.class);

	public EmployeeMapper(NamedParameterJdbcTemplate jdbcTemplate, SqlGenerator sqlGenerator) {
		this.jdbcTemplate = jdbcTemplate;
		this.sqlGenerator = sqlGenerator;
	}

	public Employee findById(int id) {
		final MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
		final String sql = this.sqlGenerator.generate(load("com/example/employee/EmployeeMapper/findById.sql"), params.getValues(), params::addValue);
		return this.jdbcTemplate.queryForObject(sql, params, this.rowMapper);
	}

	public List<Employee> findAll(EmployeeCriteria criteria) {
		final MapSqlParameterSource params = criteria.toSqlParameterSource();
		final String sql = this.sqlGenerator.generate(load("com/example/employee/EmployeeMapper/findAll.sql"), params.getValues(), params::addValue);
		return this.jdbcTemplate.query(sql, params, this.rowMapper);
	}
}
