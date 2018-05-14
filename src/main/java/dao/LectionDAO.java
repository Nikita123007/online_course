package dao;

import hibernate.AbstractEntity;
import hibernate.CourseEntity;
import hibernate.LectionEntity;
import hibernate.TestEntity;

import java.util.Collection;

public interface LectionDAO extends AbstractEntityDAO<LectionEntity> {
    default AbstractEntity getParent(int parentId){
        return DAOFactory.getInstance().getCourseDAO().get(parentId);
    }
    default Collection<LectionEntity> getAllByParent(AbstractEntity parent) {
        if(parent instanceof CourseEntity){
            return ((CourseEntity)parent).getLectionsByIdCourse();
        }
        return null;
    }
}
