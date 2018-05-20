package dao.impl;

import dao.AnswerDAO;
import hibernate.AnswerEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
public class MysqlAnswerDAO implements AnswerDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public MysqlAnswerDAO(){
        entityManager = Persistence.createEntityManagerFactory("EntityManager").createEntityManager();
    }

    public void add(AnswerEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void remove(AnswerEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.remove(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void merge(AnswerEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public Collection<AnswerEntity> getAll(){
        entityManager.clear();
        Query query = entityManager.createQuery("FROM AnswerEntity");
        return (Collection<AnswerEntity>) query.getResultList();
    }

    public AnswerEntity get(int id) {
        try {
            entityManager.clear();
            return entityManager.find(AnswerEntity.class, id);
        } catch (Exception ex) {
            return null;
        }
    }
}
