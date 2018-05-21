package Documents;

import dao.AbstractEntityDAO;
import dao.DAOFactory;
import hibernate.CourseEntity;
import hibernate.SubscriptionEntity;
import hibernate.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserDocumentService extends DocumentService{
    AbstractEntityDAO<UserEntity> getDao(){
        return DAOFactory.getInstance().getUserDAO();
    }

    @Override
    public String getTitle(){
        return "User";
    }

    @Override
    List<Table> getTables(int key){
        List<Table> result = new ArrayList<>();

        UserEntity user = getDao().get(key);
        Table tableUser = new Table();
        tableUser.name = "User";
        tableUser.rows.add(new String[]{"Name", user.getName()});
        tableUser.rows.add(new String[]{"E-mail", user.getEmail()});
        tableUser.rows.add(new String[]{"Role", user.getRole().getName()});
        result.add(tableUser);

        for(SubscriptionEntity subscription : user.getSubscriptions()){
            CourseEntity course = subscription.getCourse();
            Table tableSubscription = new Table();
            tableSubscription.name = "Subscription";
            tableSubscription.rows.add(new String[]{"Name", course.getName()});
            tableSubscription.rows.add(new String[]{"Date", subscription.getDate().toString()});
            long completedCourseCount = user.getCompletedTests().stream().filter(t -> t.getTest().getCourse().getId() == course.getId()).count();
            tableSubscription.rows.add(new String[]{"Completed tests", String.valueOf(completedCourseCount)});
            result.add(tableSubscription);
        }

        return result;
    }
}
