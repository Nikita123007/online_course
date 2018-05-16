package dao.impl;

import dao.TestAnswerDAO;
import dao.TestQuestionDAO;
import hibernate.TestAnswerEntity;
import hibernate.TestQuestionEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
public class MysqlTestAnswerDAO implements TestAnswerDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public MysqlTestAnswerDAO(){
        entityManager = Persistence.createEntityManagerFactory("EntityManager").createEntityManager();
    }

    public void add(TestAnswerEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void remove(TestAnswerEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.remove(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void merge(TestAnswerEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public Collection<TestAnswerEntity> getAll(){
        entityManager.clear();
        Query query = entityManager.createQuery("FROM TestAnswerEntity");
        return (Collection<TestAnswerEntity>) query.getResultList();
    }

    public TestAnswerEntity get(int id) {
        try {
            entityManager.clear();
            return entityManager.find(TestAnswerEntity.class, id);
        } catch (Exception ex) {
            return null;
        }
    }
}
