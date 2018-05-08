package dao.impl;

import dao.CourseDAO;
import hibernate.CourseEntity;
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

    public void addCourse(CourseEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public void removeCourse(CourseEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.remove(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public void mergeCourse(CourseEntity entity){
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entity);
        }
        finally {
            entityManager.getTransaction().commit();
        }
    }

    public Collection<CourseEntity> getAll(){
        Query query = entityManager.createQuery("FROM CourseEntity");
        return (Collection<CourseEntity>) query.getResultList();
    }

    public CourseEntity getCourse(int id) {
        try {
            return entityManager.find(CourseEntity.class, id);
        } catch (Exception ex) {
            return null;
        }
    }

    public Collection<CourseEntity> getAllByUser(int userId){
        try {
            Query query = entityManager.createQuery("FROM CourseEntity WHERE author=?");
            query.setParameter(0, userId);
            return (Collection<CourseEntity>) query.getResultList();
        }catch (Exception ex){
            return new ArrayList<CourseEntity>();
        }
    }
}
