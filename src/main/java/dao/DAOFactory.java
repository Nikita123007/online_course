package dao;

import dao.impl.MySqlCourseDAO;

public class DAOFactory {

    private static final DAOFactory factory = new DAOFactory();

    private final CourseDAO courseDAO = new MySqlCourseDAO();
    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return factory;
    }

    public CourseDAO getCourseDAO() {
        return courseDAO;
    }

}
