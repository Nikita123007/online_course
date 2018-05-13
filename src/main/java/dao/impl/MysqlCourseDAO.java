package dao.impl;

import dao.CourseDAO;
import dao.DAOFactory;
import hibernate.CourseEntity;
import hibernate.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import sessionFactorySingletone.SessionFactorySingletone;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
public class MysqlCourseDAO implements CourseDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public MysqlCourseDAO(){
        entityManager = Persistence.createEntityManagerFactory("EntityManager").createEntityManager();
    }

    public void add(CourseEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void remove(CourseEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.remove(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public void merge(CourseEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    public Collection<CourseEntity> getAll(){
        entityManager.clear();
        Query query = entityManager.createQuery("FROM CourseEntity");
        return (Collection<CourseEntity>) query.getResultList();
    }

    public CourseEntity get(int id) {
        try {
            entityManager.clear();
            return entityManager.find(CourseEntity.class, id);
        } catch (Exception ex) {
            return null;
        }
    }
}
