package Documents;

import dao.AbstractEntityDAO;
import dao.DAOFactory;
import hibernate.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RegistrationDocumentService extends DocumentService{
    AbstractEntityDAO<UserEntity> getDao(){
        return DAOFactory.getInstance().getUserDAO();
    }

    @Override
    public String getTitle(){
        return "Registration report";
    }

    @Override
    List<Table> getTables(Integer key){
        List<Table> result = new ArrayList<>();

        Collection<UserEntity> users = getDao().getAll();
        Table usersTable = new Table();
        usersTable.name = "Users";
        usersTable.rows.add(new String[]{"Name", "E-mail", "Subscription count", "Completed test count"});
        for(UserEntity user : users){
            usersTable.rows.add(new String[]{user.getName(), user.getEmail(), String.valueOf(user.getSubscriptions().size()), String.valueOf(user.getCompletedTests().size())});
        }
        result.add(usersTable);

        return result;
    }
}