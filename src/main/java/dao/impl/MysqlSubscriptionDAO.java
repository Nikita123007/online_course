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

    public void addSubscription(SubscriptionEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public void removeSubscription(SubscriptionEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.remove(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public void mergeSubscription(SubscriptionEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public Collection<SubscriptionEntity> getAll(){
        entityManager.clear();
        Query query = entityManager.createQuery("FROM SubscriptionEntity");
        return (Collection<SubscriptionEntity>) query.getResultList();
    }

    public SubscriptionEntity getSubscription(int id) {
        try {
            entityManager.clear();
            return entityManager.find(SubscriptionEntity.class, id);
        } catch (Exception ex) {
            return null;
        }
    }

    public Collection<SubscriptionEntity> getAllSubscriptionByUser(int userId) {
        try {
            entityManager.clear();
            TypedQuery<SubscriptionEntity> query = entityManager.createQuery("FROM SubscriptionEntity WHERE user=?", SubscriptionEntity.class);
            query.setParameter(0, userId);
            return (Collection<SubscriptionEntity>) query.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
}
