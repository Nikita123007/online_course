package dao;

import hibernate.AbstractEntity;
import hibernate.TestEntity;

import java.util.Collection;

public interface TestDAO extends AbstractEntityDAO<TestEntity> {
    default AbstractEntity getParent(int parentId){
        return DAOFactory.getInstance().getCourseDAO().get(parentId);
    }
}
