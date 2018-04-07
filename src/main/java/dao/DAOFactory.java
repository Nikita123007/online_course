package dao;

import dao.impl.MysqlCourseDAO;
import dao.impl.MysqlUserDAO;

public class DAOFactory {

    private static final DAOFactory factory = new DAOFactory();

    private final CourseDAO courseDAO = new MysqlCourseDAO();
    private final UserDAO userDAO = new MysqlUserDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return factory;
    }

    public CourseDAO getCourseDAO() {
        return courseDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}