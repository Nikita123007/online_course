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

    public void addUser(UserEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public void mergeUser(UserEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public Collection<UserEntity> getAll(){
        Query query = entityManager.createQuery("FROM UserEntity");
        return (Collection<UserEntity>) query.getResultList();
    }

    public UserEntity getUser(int id){
        return entityManager.find(UserEntity.class, id);
    }

    public UserEntity findUser(String login){
        TypedQuery<UserEntity> tq = entityManager.createQuery("from UserEntity WHERE name=?", UserEntity.class);
        return tq.setParameter(0, login).getSingleResult();
    }
}