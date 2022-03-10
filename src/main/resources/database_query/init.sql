CREATE
    DATABASE department_db;
USE
    department_db;

CREATE TABLE departments
(
    department_id   INT          NOT NULL PRIMARY KEY UNIQUE AUTO_INCREMENT,
    department_name VARCHAR(120) NOT NULL UNIQUE
);


CREATE TABLE employees
(
    employee_id   INT                                         NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
    employee_name VARCHAR(120)                                NOT NULL,
    position      ENUM ('accountant', 'salesman', 'director') NOT NULL,
    birthday      DATE                                        NOT NULL,
    email         VARCHAR(120)                                NOT NULL UNIQUE,
    department_id INT                                         NOT NULL,
    FOREIGN KEY (department_id) REFERENCES departments (department_id) ON DELETE CASCADE
);