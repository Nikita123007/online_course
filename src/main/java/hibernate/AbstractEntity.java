package hibernate;

import common.ActionType;

public interface AbstractEntity {
    boolean checkRights(UserEntity user, ActionType action);
}
