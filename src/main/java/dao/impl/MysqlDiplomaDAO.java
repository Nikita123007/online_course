package dao.impl;

import dao.DiplomaDAO;
import hibernate.DiplomaEntity;

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

    public void addDiploma(DiplomaEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public void mergeDiploma(DiplomaEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public Collection<DiplomaEntity> getAll(){
        Query query = entityManager.createQuery("FROM DiplomaEntity");
        return (Collection<DiplomaEntity>) query.getResultList();
    }

    public DiplomaEntity getDiploma(int id) {
        try {
            return entityManager.find(DiplomaEntity.class, id);
        } catch (Exception ex) {
            return null;
        }
    }
}
