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
        tableCourse.rows.add(new String[]{"Lection count", String.valueOf(course.getLections().size())});
        tableCourse.rows.add(new String[]{"Test count", String.valueOf(course.getTests().size())});
        result.add(tableCourse);


        UserEntity author = course.getAuthor();
        Table tableAuthor = new Table();
        tableAuthor.name = "Author";
        tableAuthor.rows.add(new String[]{"Name", author.getName()});
        tableAuthor.rows.add(new String[]{"E-mail", author.getEmail()});
        result.add(tableAuthor);

        return result;
    }
}
