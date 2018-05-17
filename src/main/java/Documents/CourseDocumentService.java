package Documents;

import dao.AbstractEntityDAO;
import dao.DAOFactory;
import hibernate.CourseEntity;
import hibernate.UserEntity;
import java.util.ArrayList;
import java.util.List;

public class CourseDocumentService extends DocumentService{
    AbstractEntityDAO<CourseEntity> getDao(){
        return DAOFactory.getInstance().getCourseDAO();
    }

    @Override
    public String getTitle(){
        return "Course";
    }

    @Override
    List<Table> getTables(int key){
        List<Table> result = new ArrayList<>();

        CourseEntity course = getDao().get(key);
        Table tableCourse = new Table();
        tableCourse.name = "Course";
        tableCourse.rows.add(new String[]{"Name", course.getName()});
        tableCourse.rows.add(new String[]{"Price", course.getPrice().toString()});
        tableCourse.rows.add(new String[]{"Lection count", String.valueOf(course.getLectionsByIdCourse().size())});
        tableCourse.rows.add(new String[]{"Test count", String.valueOf(course.getTestsByIdCourse().size())});
        result.add(tableCourse);


        UserEntity author = course.getUserByAuthor();
        Table talbeAuthor = new Table();
        talbeAuthor.name = "Author";
        talbeAuthor.rows.add(new String[]{"Name", author.getName()});
        talbeAuthor.rows.add(new String[]{"E-mail", author.getEmail()});
        result.add(talbeAuthor);

        return result;
    }
}
