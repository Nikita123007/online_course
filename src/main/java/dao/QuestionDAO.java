package dao;

import hibernate.AbstractEntity;
import hibernate.QuestionEntity;
import hibernate.TestEntity;

import java.util.Collection;

public interface QuestionDAO extends AbstractEntityDAO<QuestionEntity> {
    default AbstractEntity getParent(int parentId){
        return DAOFactory.getInstance().getTestDAO().get(parentId);
    }
    default Collection<QuestionEntity> getAllByParent(AbstractEntity parent) {
        if(parent instanceof TestEntity){
            return ((TestEntity)parent).getQuestions();
        }
        return null;
    }
}
