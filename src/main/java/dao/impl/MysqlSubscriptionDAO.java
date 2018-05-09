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
        Query query = entityManager.createQuery("FROM SubscriptionEntity");
        return (Collection<SubscriptionEntity>) query.getResultList();
    }

    public SubscriptionEntity getSubscription(int id) {
        try {
            return entityManager.find(SubscriptionEntity.class, id);
        } catch (Exception ex) {
            return null;
        }
    }

    public Collection<SubscriptionEntity> getAllSubscriptionByUser(int userId) {
        try {
            TypedQuery<SubscriptionEntity> query = entityManager.createQuery("FROM SubscriptionEntity WHERE user=?", SubscriptionEntity.class);
            query.setParameter(0, userId);
            return (Collection<SubscriptionEntity>) query.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public SubscriptionEntity getAllSubscriptionByUserAndCourse(int userId, int courseId) {
        try {
            TypedQuery<SubscriptionEntity> tq = entityManager.createQuery("FROM SubscriptionEntity WHERE user=? AND course=?", SubscriptionEntity.class);
            tq.setParameter(0, userId);
            return tq.setParameter(1, courseId).getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
}
