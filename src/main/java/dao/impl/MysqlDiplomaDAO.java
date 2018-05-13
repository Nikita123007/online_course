package dao.impl;

import dao.DAOFactory;
import dao.DiplomaDAO;
import hibernate.DiplomaEntity;
import hibernate.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
public class MysqlDiplomaDAO implements DiplomaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public MysqlDiplomaDAO(){
        entityManager = Persistence.createEntityManagerFactory("EntityManager").createEntityManager();
    }

    public void add(DiplomaEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void remove(DiplomaEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.remove(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void merge(DiplomaEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public Collection<DiplomaEntity> getAll(){
        entityManager.clear();
        Query query = entityManager.createQuery("FROM DiplomaEntity");
        return (Collection<DiplomaEntity>) query.getResultList();
    }

    public DiplomaEntity get(int id) {
        try {
            entityManager.clear();
            return entityManager.find(DiplomaEntity.class, id);
        } catch (Exception ex) {
            return null;
        }
    }
}
