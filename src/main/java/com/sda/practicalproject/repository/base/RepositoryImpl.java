package com.sda.practicalproject.repository.base;

import com.sda.practicalproject.repository.exception.EntityUpdateFailedException;
import com.sda.practicalproject.utils.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class RepositoryImpl<T> implements Repository<T>  {

    private final Class<T> entityClass;

    public RepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void save(T entity) throws EntityUpdateFailedException {
        Transaction transaction = null;
        try(Session session = SessionManager.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            session.save(entity);

            transaction.commit();
        } catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            throw new EntityUpdateFailedException("Save failed for " + entityClass.getName(), e);
        }
    }

    @Override
    public void update(T entity) throws EntityUpdateFailedException {
        Transaction transaction = null;
        try(Session session = SessionManager.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            session.update(entity);

            transaction.commit();
        } catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            throw new EntityUpdateFailedException("Update failed for " + entityClass.getName(), e);
        }
    }

    @Override
    public void delete(T entity) throws EntityUpdateFailedException {
        Transaction transaction = null;
        try(Session session = SessionManager.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            session.delete(entity);

            transaction.commit();
        } catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            throw new EntityUpdateFailedException("Delete failed for " + entityClass.getName(), e);
        }
    }

    @Override
    public Optional<T> findById(long id) {
        try(Session session = SessionManager.getSessionFactory().openSession()){
            return Optional.ofNullable(session.find(entityClass,id));
        }
    }

    @Override
    public List<T> findAll() {
        try(Session session = SessionManager.getSessionFactory().openSession()){
            Query<T> query = session.createQuery("from " + entityClass.getSimpleName());
            return query.list();
        }
    }
}
