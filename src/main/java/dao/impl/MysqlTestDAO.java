package dao.impl;

import hibernate.TestEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
public class MysqlTestDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public MysqlTestDAO(){
        entityManager = Persistence.createEntityManagerFactory("EntityManager").createEntityManager();
    }

    public void addTest(TestEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public void mergeTest(TestEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public Collection<TestEntity> getAll(){
        Query query = entityManager.createQuery("FROM TestEntity");
        return (Collection<TestEntity>) query.getResultList();
    }

    public TestEntity getTest(int id){
        return entityManager.find(TestEntity.class, id);
    }
}
