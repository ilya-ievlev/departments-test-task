INSERT INTO departments
VALUES (NULL, 'dep1');
INSERT INTO departments
VALUES (NULL, 'dep2');
INSERT INTO departments
VALUES (NULL, 'dep3');
INSERT INTO departments
VALUES (NULL, 'dep4');
INSERT INTO departments
VALUES (NULL, 'dep5');

INSERT INTO employees
VALUES (NULL, 'bob', 'accountant', '1970-12-4', 'test1@test.com',
        (SELECT department_id FROM departments WHERE department_name = 'dep2'));
INSERT INTO employees
VALUES (NULL, 'emily', 'director', '1973-12-1', 'test2@test.com',
        (SELECT department_id FROM departments WHERE department_name = 'dep2'));
INSERT INTO employees
VALUES (NULL, 'charles', 'accountant', '1976-4-5', 'test3@test.com',
        (SELECT department_id FROM departments WHERE department_name = 'dep1'));
INSERT INTO employees
VALUES (NULL, 'alex', 'salesman', '1982-11-6', 'test4@test.com',
        (SELECT department_id FROM departments WHERE department_name = 'dep3'));
INSERT INTO employees
VALUES (NULL, 'pitt', 'salesman', '1990-3-24', 'test5@test.com',
        (SELECT department_id FROM departments WHERE department_name = 'dep4'));
