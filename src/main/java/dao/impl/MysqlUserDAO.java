package dao.impl;

import dao.UserDAO;
import hibernate.UserEntity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
public class MysqlUserDAO implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public MysqlUserDAO(){
        entityManager = Persistence.createEntityManagerFactory("EntityManager").createEntityManager();
    }

    public void add(UserEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void remove(UserEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.remove(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void merge(UserEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public Collection<UserEntity> getAll(){
        Query query = entityManager.createQuery("FROM UserEntity");
        return (Collection<UserEntity>) query.getResultList();
    }

    public UserEntity get(int id) {
        try {
            entityManager.clear();
            return entityManager.find(UserEntity.class, id);
        } catch (Exception ex) {
            return null;
        }
    }

    public UserEntity findUserByLogin(String login){
        try {
            TypedQuery<UserEntity> tq = entityManager.createQuery("FROM UserEntity WHERE name=?", UserEntity.class);
            return tq.setParameter(0, login).getSingleResult();
        }catch (Exception ex) {
            return null;
        }
    }

    public UserEntity findUserByAuthToken(String authToken) {
        try {
            TypedQuery<UserEntity> tq = entityManager.createQuery("FROM UserEntity WHERE authToken=?", UserEntity.class);
            return tq.setParameter(0, authToken).getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
}
