package com.illiaiievliev.learning.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public abstract class HibernateDAOAbstract<T> {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); // TODO: 05.01.22 how can i inject it with spring
    protected Session currentSession;
    protected Transaction currentTransaction;

    protected void openCurrentSession() {
        currentSession = sessionFactory.openSession();
    }

    protected void openCurrentSessionWithTransaction() {
        currentSession = sessionFactory.openSession();
        currentTransaction = currentSession.beginTransaction();
    }

    protected void closeCurrentSession() {
        currentSession.close();
    }

    protected void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    protected List<T> templateFindAll(String hqlQuery) {
        openCurrentSession();
        List <T> listFromFindAll = (List<T>)currentSession.createQuery(hqlQuery).list();
        closeCurrentSession();
        return listFromFindAll;
    }

    protected T templateFindById(Class<T> entityType, int id) {
        openCurrentSession();
        T t = currentSession.get(entityType, id);
        closeCurrentSession();
        return t;
    }

    protected void templateUpdate (T t){
        openCurrentSessionWithTransaction();
        currentSession.update(t);
        closeCurrentSessionWithTransaction();
    }

    protected void templateDelete(String hqlQuery, int id) {
        openCurrentSessionWithTransaction();
        Query query = currentSession.createQuery(hqlQuery);
        query.setParameter("id", id);
        query.executeUpdate();
        closeCurrentSessionWithTransaction();
    }

    protected void templateSave (T t){
        openCurrentSessionWithTransaction();
        currentSession.save(t);
        closeCurrentSessionWithTransaction();
    }
}
