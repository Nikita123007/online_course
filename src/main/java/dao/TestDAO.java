package dao;

import hibernate.AbstractEntity;
import hibernate.CourseEntity;
import hibernate.TestEntity;

import java.util.Collection;

public interface TestDAO extends AbstractEntityDAO<TestEntity> {
    default AbstractEntity getParent(int parentId){
        return DAOFactory.getInstance().getCourseDAO().get(parentId);
    }
    default Collection<TestEntity> getAllByParent(AbstractEntity parent) {
        if(parent instanceof CourseEntity){
            return ((CourseEntity)parent).getTestsByIdCourse();
        }
        return null;
    }
}
