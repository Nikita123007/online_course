package dao.impl;

import dao.DAOFactory;
import dao.TestDAO;
import hibernate.TestEntity;
import hibernate.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
public class MysqlTestDAO implements TestDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public MysqlTestDAO(){
        entityManager = Persistence.createEntityManagerFactory("EntityManager").createEntityManager();
    }

    public void add(TestEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void remove(TestEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.remove(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void merge(TestEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public Collection<TestEntity> getAll(){
        entityManager.clear();
        Query query = entityManager.createQuery("FROM TestEntity");
        return (Collection<TestEntity>) query.getResultList();
    }

    public TestEntity get(int id) {
        try {
            entityManager.clear();
            return entityManager.find(TestEntity.class, id);
        } catch (Exception ex) {
            return null;
        }
    }
}
