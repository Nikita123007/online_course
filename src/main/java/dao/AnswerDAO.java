package dao;

import hibernate.AbstractEntity;
import hibernate.AnswerEntity;
import hibernate.QuestionEntity;

import java.util.Collection;

public interface AnswerDAO extends AbstractEntityDAO<AnswerEntity> {
    default AbstractEntity getParent(int parentId){
        return DAOFactory.getInstance().getQuestionDAO().get(parentId);
    }
    default Collection<AnswerEntity> getAllByParent(AbstractEntity parent) {
        if(parent instanceof QuestionEntity){
            return ((QuestionEntity)parent).getAnswers();
        }
        return null;
    }
}
