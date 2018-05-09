package dao.impl;

import dao.CompletedTestDAO;
import dao.UserDAO;
import hibernate.CompletedTestEntity;
import hibernate.UserEntity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
public class MysqlCompletedTestDAO implements CompletedTestDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public MysqlCompletedTestDAO(){
        entityManager = Persistence.createEntityManagerFactory("EntityManager").createEntityManager();
    }

    public void addCompletedTest(CompletedTestEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public void removeCompletedTest(CompletedTestEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.remove(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public void mergeCompletedTest(CompletedTestEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public Collection<CompletedTestEntity> getAll(){
        Query query = entityManager.createQuery("FROM CompletedTestEntity");
        return (Collection<CompletedTestEntity>) query.getResultList();
    }

    public CompletedTestEntity getCompletedTest(int idUser, int idTest) {
        try {
            TypedQuery<CompletedTestEntity> tq = entityManager.createQuery("FROM CompletedTestEntity WHERE user=? AND test=?", CompletedTestEntity.class);
            tq.setParameter(0, idUser);
            return tq.setParameter(1, idTest).getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
}
