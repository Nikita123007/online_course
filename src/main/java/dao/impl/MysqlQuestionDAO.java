package dao.impl;

import dao.QuestionDAO;
import hibernate.QuestionEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
public class MysqlQuestionDAO implements QuestionDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public MysqlQuestionDAO(){
        entityManager = Persistence.createEntityManagerFactory("EntityManager").createEntityManager();
    }

    public void add(QuestionEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void remove(QuestionEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.remove(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void merge(QuestionEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public Collection<QuestionEntity> getAll(){
        entityManager.clear();
        Query query = entityManager.createQuery("FROM QuestionEntity");
        return (Collection<QuestionEntity>) query.getResultList();
    }

    public QuestionEntity get(int id) {
        try {
            entityManager.clear();
            return entityManager.find(QuestionEntity.class, id);
        } catch (Exception ex) {
            return null;
        }
    }
}
