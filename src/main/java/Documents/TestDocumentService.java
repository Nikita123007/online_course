package Documents;

import dao.AbstractEntityDAO;
import dao.DAOFactory;
import hibernate.CompletedTestEntity;
import hibernate.TestEntity;
import hibernate.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class TestDocumentService extends DocumentService{
    AbstractEntityDAO<TestEntity> getDao(){
        return DAOFactory.getInstance().getTestDAO();
    }

    @Override
    public String getTitle(){
        return "Test";
    }

    @Override
    List<Table> getTables(Integer key){
        List<Table> result = new ArrayList<>();

        TestEntity test = getDao().get(key);
        Table tableTest = new Table();
        tableTest.name = "Test";
        tableTest.rows.add(new String[]{"Name", test.getName()});
        tableTest.rows.add(new String[]{"Course", test.getCourse().getName()});
        result.add(tableTest);

        for(CompletedTestEntity completedTest : test.getCompletedTests()){
            UserEntity user = completedTest.getUser();
            Table tableCompletedTest = new Table();
            tableCompletedTest.name = "Completed test";
            tableCompletedTest.rows.add(new String[]{"User", user.getName()});
            tableCompletedTest.rows.add(new String[]{"E-mail", user.getEmail()});
            tableCompletedTest.rows.add(new String[]{"Date", completedTest.getDate().toString()});
            tableCompletedTest.rows.add(new String[]{"Correct answers", String.valueOf(completedTest.getCorrectAnswers())});
            result.add(tableCompletedTest);
        }

        return result;
    }
}