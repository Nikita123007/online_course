package dao;

import hibernate.AbstractEntity;
import hibernate.LectionEntity;

import java.util.Collection;

public interface LectionDAO extends AbstractEntityDAO<LectionEntity> {
    default AbstractEntity getParent(int parentId){
        return DAOFactory.getInstance().getCourseDAO().get(parentId);
    }
}
