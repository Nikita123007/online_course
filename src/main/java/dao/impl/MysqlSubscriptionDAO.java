package dao.impl;

import dao.SubscriptionDAO;
import dao.UserDAO;
import hibernate.SubscriptionEntity;
import hibernate.UserEntity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
public class MysqlSubscriptionDAO implements SubscriptionDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public MysqlSubscriptionDAO(){
        entityManager = Persistence.createEntityManagerFactory("EntityManager").createEntityManager();
    }

    public void add(SubscriptionEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void remove(SubscriptionEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.remove(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void merge(SubscriptionEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public Collection<SubscriptionEntity> getAll(){
        entityManager.clear();
        Query query = entityManager.createQuery("FROM SubscriptionEntity");
        return (Collection<SubscriptionEntity>) query.getResultList();
    }

    public SubscriptionEntity get(int id) {
        try {
            entityManager.clear();
            return entityManager.find(SubscriptionEntity.class, id);
        } catch (Exception ex) {
            return null;
        }
    }
}
