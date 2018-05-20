package dao.impl;

import dao.CategoryDAO;
import hibernate.CategoryEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

public class MysqlCategoryDAO implements CategoryDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public MysqlCategoryDAO(){
        entityManager = Persistence.createEntityManagerFactory("EntityManager").createEntityManager();
    }

    public void add(CategoryEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void remove(CategoryEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.remove(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void merge(CategoryEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public Collection<CategoryEntity> getAll(){
        entityManager.clear();
        Query query = entityManager.createQuery("FROM QuestionEntity");
        return (Collection<CategoryEntity>) query.getResultList();
    }

    public CategoryEntity get(int id) {
        try {
            entityManager.clear();
            return entityManager.find(CategoryEntity.class, id);
        } catch (Exception ex) {
            return null;
        }
    }
}
