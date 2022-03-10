package com.illiaiievliev.learning.dao.departmentDAO.impl;

import com.illiaiievliev.learning.dao.HibernateDAOAbstract;
import com.illiaiievliev.learning.dao.departmentDAO.interfaces.DepartmentDAO;
import com.illiaiievliev.learning.models.Department;
import org.hibernate.Query;

import java.util.List;

public class DepartmentHibernateDAOImpl extends HibernateDAOAbstract<Department> implements DepartmentDAO {

    private static final String FROM_DEPARTMENT_HQL_QUERY = "FROM Department";
    private static final String DELETE_FROM_DEPARTMENTS_WHERE_DEPARTMENT_ID_HQL_QUERY = "DELETE FROM Department WHERE department_id = :id";
    private static final String FROM_DEPARTMENT_WHERE_DEPARTMENT_NAME_HQL_QUERY = "FROM Department WHERE department_name = :name";
    private static final String NAME_PARAMETER_FOR_QUERY = "name";



    @Override
    public List<Department> findAll() {
        return templateFindAll(FROM_DEPARTMENT_HQL_QUERY);
    }

    @Override
    public Department findById(int id) {
        return templateFindById(Department.class, id);
    }

    @Override
    public void update(Department department) {
        templateUpdate(department);
    }

    @Override
    public void delete(int id) {
        templateDelete(DELETE_FROM_DEPARTMENTS_WHERE_DEPARTMENT_ID_HQL_QUERY, id);
    }

    @Override
    public void save(Department department) {
        templateSave(department);
    }

    @Override
    public Department findByName(String name) {
        openCurrentSession();
        Query query = currentSession.createQuery(FROM_DEPARTMENT_WHERE_DEPARTMENT_NAME_HQL_QUERY);
        query.setParameter(NAME_PARAMETER_FOR_QUERY, name);
        if (query.list().isEmpty()) {
            return null;
        }
        Department department = (Department) query.list().get(0); // TODO: 28.12.21 can i do something like this
        closeCurrentSession();
        return department;
    }
}
