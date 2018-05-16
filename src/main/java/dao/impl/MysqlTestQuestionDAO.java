package dao.impl;

import dao.TestDAO;
import dao.TestQuestionDAO;
import hibernate.TestEntity;
import hibernate.TestQuestionEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
public class MysqlTestQuestionDAO implements TestQuestionDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public MysqlTestQuestionDAO(){
        entityManager = Persistence.createEntityManagerFactory("EntityManager").createEntityManager();
    }

    public void add(TestQuestionEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void remove(TestQuestionEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.remove(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void merge(TestQuestionEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public Collection<TestQuestionEntity> getAll(){
        entityManager.clear();
        Query query = entityManager.createQuery("FROM TestQuestionEntity");
        return (Collection<TestQuestionEntity>) query.getResultList();
    }

    public TestQuestionEntity get(int id) {
        try {
            entityManager.clear();
            return entityManager.find(TestQuestionEntity.class, id);
        } catch (Exception ex) {
            return null;
        }
    }
}
