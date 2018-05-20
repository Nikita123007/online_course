package dao.impl;

import dao.RoleDAO;
import hibernate.RoleEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
public class MysqlRoleDAO implements RoleDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public MysqlRoleDAO(){
        entityManager = Persistence.createEntityManagerFactory("EntityManager").createEntityManager();
    }

    public void add(RoleEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void remove(RoleEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.remove(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void merge(RoleEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public Collection<RoleEntity> getAll(){
        entityManager.clear();
        Query query = entityManager.createQuery("FROM QuestionEntity");
        return (Collection<RoleEntity>) query.getResultList();
    }

    public RoleEntity get(int id) {
        try {
            entityManager.clear();
            return entityManager.find(RoleEntity.class, id);
        } catch (Exception ex) {
            return null;
        }
    }
}
