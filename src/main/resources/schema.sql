CREATE TABLE IF NOT EXISTS employee
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(64),
    last_name  VARCHAR(64),
    salary     INT
);
CREATE INDEX IF NOT EXISTS employee_first_name ON employee (first_name);
CREATE INDEX IF NOT EXISTS employee_last_name ON employee (last_name);
CREATE INDEX IF NOT EXISTS employee_salary ON employee (salary);