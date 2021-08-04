package com.example.employee;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.scripting.thymeleaf.SqlGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest(properties = {
		"spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver",
		"spring.datasource.url=jdbc:tc:postgresql:11:///employee"
})
@AutoConfigureTestDatabase(replace = Replace.NONE)
class EmployeeMapperTest {
	@Autowired
	EmployeeMapper employeeMapper;

	@Test
	void findById() {
		final Employee employee = this.employeeMapper.findById(10);
		assertThat(employee.getId()).isEqualTo(10);
		assertThat(employee.getFirstName()).isEqualTo("Wilford");
		assertThat(employee.getLastName()).isEqualTo("Nolan");
		assertThat(employee.getSalary()).isEqualTo(48070);
	}

	@Test
	void findAll() {
		final EmployeeCriteria criteria = new EmployeeCriteria();
		final List<Employee> employees = this.employeeMapper.findAll(criteria);
		assertThat(employees).hasSize(100);
	}

	@Test
	void findByFirstName() {
		final EmployeeCriteria criteria = new EmployeeCriteria();
		criteria.setFirstName("de");
		final List<Employee> employees = this.employeeMapper.findAll(criteria);
		assertThat(employees).hasSize(4);
	}

	@Test
	void findByLastName() {
		final EmployeeCriteria criteria = new EmployeeCriteria();
		criteria.setLastName("de");
		final List<Employee> employees = this.employeeMapper.findAll(criteria);
		assertThat(employees).hasSize(3);
	}

	@Test
	void findBySalaryMax() {
		final EmployeeCriteria criteria = new EmployeeCriteria();
		criteria.setSalaryMax(40_000);
		final List<Employee> employees = this.employeeMapper.findAll(criteria);
		assertThat(employees).hasSize(7);
	}

	@Test
	void findBySalaryMin() {
		final EmployeeCriteria criteria = new EmployeeCriteria();
		criteria.setSalaryMin(150_000);
		final List<Employee> employees = this.employeeMapper.findAll(criteria);
		assertThat(employees).hasSize(14);
	}

	@Test
	void findBySalary() {
		final EmployeeCriteria criteria = new EmployeeCriteria();
		criteria.setSalaryMin(80_000);
		criteria.setSalaryMax(100_000);
		final List<Employee> employees = this.employeeMapper.findAll(criteria);
		assertThat(employees).hasSize(21);
	}

	@Test
	void findByNameAndSalary() {
		final EmployeeCriteria criteria = new EmployeeCriteria();
		criteria.setFirstName("A");
		criteria.setLastName("K");
		criteria.setSalaryMin(80_000);
		criteria.setSalaryMax(100_000);
		final List<Employee> employees = this.employeeMapper.findAll(criteria);
		assertThat(employees).hasSize(3);
	}

	@Configuration
	@Import(AppConfig.class)
	static class Config {
		@Bean
		public EmployeeMapper employeeMapper(NamedParameterJdbcTemplate jdbcTemplate, SqlGenerator sqlGenerator) {
			return new EmployeeMapper(jdbcTemplate, sqlGenerator);
		}
	}
}