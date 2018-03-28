package dao.impl;

import connectionpool.ConnectionPool;
import dao.CourseDAO;
import dao.exception.DAOException;
import domain.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySqlCourseDAO implements CourseDAO {
    public void addCourse(Course entity) throws DAOException{
        try{
            Connection connection = ConnectionPool.getInstance().getConnection();
            String query = "INSERT INTO `online_course`.`course` " +
                    "(`author`, `name`, `price`, `level`, `duration`, `category`) VALUES " +
                    "(?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(entity.getAuthor()));
            statement.setString(2, String.valueOf(entity.getName()));
            statement.setString(3, String.valueOf(entity.getPrice()));
            statement.setObject(4, entity.getLevel());
            statement.setObject(5, entity.getDuration());
            statement.setString(6, String.valueOf(entity.getCategory()));

            statement.execute();
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(), e);
        }
    }

    /*
    public Course findCourse(int id) throws DAOException{

    }

    public List<Course> getCourses() throws  DAOException{

    }

    public void editCourse(int id, Course entity) throws  DAOException{

    }
    */
}
