package com.illiaiievliev.learning.service.department_service.imlp;

import com.illiaiievliev.learning.dao.departmentDAO.interfaces.DepartmentDAO;
import com.illiaiievliev.learning.dao.exceptions.DAOException;
import com.illiaiievliev.learning.models.Department;
import com.illiaiievliev.learning.service.department_service.interfaces.DepartmentService;
import com.illiaiievliev.learning.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger log = LogManager.getLogger(DepartmentServiceImpl.class);
    private static final String EXCEPTION_OCCURRED_IN_GET_LIST_OF_DEPARTMENTS = "exception occurred in getListOfDepartments";
    private static final String EXCEPTION_OCCURRED_IN_SAVE_METHOD = "exception occured in save method";
    private static final String SERVICE_EXCEPTION_OCCURRED_IN_GET_BY_ID = "Service exception occurred in getById";
    private static final String SERVICE_EXCEPTION_OCCURRED_IN_DELETE = "service exception occurred in delete";
    private static final String SERVICE_EXCEPTION_OCCURRED_IN_GET_BY_NAME_METHOD = "service exception occurred in getByName method";
    private static final String SERVICE_EXCEPTION_OCCURRED_IN_UPDATE_METHOD = "service exception occurred in update method";
    private final DepartmentDAO departmentDAO;

    public DepartmentServiceImpl(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Override
    public List<Department> getAll() throws ServiceException {
        try {
            return departmentDAO.findAll();
        } catch (DAOException e) {
            log.error(EXCEPTION_OCCURRED_IN_GET_LIST_OF_DEPARTMENTS, e);
            throw new ServiceException(EXCEPTION_OCCURRED_IN_GET_LIST_OF_DEPARTMENTS, e);
        }
    }

    @Override
    public void save(Department department) throws ServiceException {
        try {
            departmentDAO.save(department);
        } catch (DAOException e) {
            log.error(EXCEPTION_OCCURRED_IN_SAVE_METHOD, e);
            throw new ServiceException(EXCEPTION_OCCURRED_IN_SAVE_METHOD, e);
        }
    }

    @Override
    public Department getById(int id) throws ServiceException {
        try {
            return departmentDAO.findById(id);
        } catch (DAOException e) {
            log.error(SERVICE_EXCEPTION_OCCURRED_IN_GET_BY_ID, e);
            throw new ServiceException(SERVICE_EXCEPTION_OCCURRED_IN_GET_BY_ID, e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            departmentDAO.delete(id);
        } catch (DAOException e) {
            log.error(SERVICE_EXCEPTION_OCCURRED_IN_DELETE, e);
            throw new ServiceException(SERVICE_EXCEPTION_OCCURRED_IN_DELETE, e);
        }
    }

    @Override
    public Department getByName(String name) throws ServiceException {
        try {
            return departmentDAO.findByName(name);
        } catch (DAOException e) {
            log.error(SERVICE_EXCEPTION_OCCURRED_IN_GET_BY_NAME_METHOD, e);
            throw new ServiceException(SERVICE_EXCEPTION_OCCURRED_IN_GET_BY_NAME_METHOD, e);
        }
    }

    @Override
    public void update(Department department) throws ServiceException {
        try {
            departmentDAO.update(department);
        } catch (DAOException e) {
            log.error(SERVICE_EXCEPTION_OCCURRED_IN_UPDATE_METHOD, e);
            throw new ServiceException(SERVICE_EXCEPTION_OCCURRED_IN_UPDATE_METHOD, e);
        }
    }

    protected DepartmentDAO getDepartmentDAO() {
        return departmentDAO;
    }
}
