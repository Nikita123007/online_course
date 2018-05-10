package dao.impl;

import dao.LectionDAO;
import hibernate.LectionEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
public class MysqlLectionDAO implements LectionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public MysqlLectionDAO(){
        entityManager = Persistence.createEntityManagerFactory("EntityManager").createEntityManager();
    }

    public void addLection(LectionEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public void removeLection(LectionEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.remove(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public void mergeLection(LectionEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public Collection<LectionEntity> getAll(){
        entityManager.clear();
        Query query = entityManager.createQuery("FROM LectionEntity");
        return (Collection<LectionEntity>) query.getResultList();
    }

    public LectionEntity getLection(int id) {
        try {
            entityManager.clear();
            return entityManager.find(LectionEntity.class, id);
        } catch (Exception ex) {
            return null;
        }
    }
}
