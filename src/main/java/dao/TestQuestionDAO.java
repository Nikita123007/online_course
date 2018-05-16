package dao;

import hibernate.AbstractEntity;
import hibernate.CourseEntity;
import hibernate.TestEntity;
import hibernate.TestQuestionEntity;

import java.util.Collection;

public interface TestQuestionDAO extends AbstractEntityDAO<TestQuestionEntity> {
    default AbstractEntity getParent(int parentId){
        return DAOFactory.getInstance().getTestDAO().get(parentId);
    }
    default Collection<TestQuestionEntity> getAllByParent(AbstractEntity parent) {
        if(parent instanceof TestEntity){
            return ((TestEntity)parent).getTestQuestionsByIdTest();
        }
        return null;
    }
}
