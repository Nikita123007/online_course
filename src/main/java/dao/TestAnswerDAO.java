package dao;

import hibernate.AbstractEntity;
import hibernate.TestAnswerEntity;
import hibernate.TestEntity;
import hibernate.TestQuestionEntity;

import java.util.Collection;

public interface TestAnswerDAO extends AbstractEntityDAO<TestAnswerEntity> {
    default AbstractEntity getParent(int parentId){
        return DAOFactory.getInstance().getTestQuestionDAO().get(parentId);
    }
    default Collection<TestAnswerEntity> getAllByParent(AbstractEntity parent) {
        if(parent instanceof TestQuestionEntity){
            return ((TestQuestionEntity)parent).getTestAnswersByIdTestQuestion();
        }
        return null;
    }
}
