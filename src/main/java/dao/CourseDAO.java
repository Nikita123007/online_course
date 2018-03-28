package dao;

import dao.exception.DAOException;
import domain.Course;
import java.util.List;

public interface CourseDAO {
    void addCourse(Course entity) throws DAOException;
    //Course findCourse(int id) throws DAOException;
    //List<Course> getCourses() throws  DAOException;
    //void editCourse(int id, Course entity) throws  DAOException;
}

