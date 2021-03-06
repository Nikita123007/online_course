package dao;

import dao.impl.*;

public class DAOFactory {

    private static final DAOFactory factory = new DAOFactory();

    private final CourseDAO courseDAO = new MysqlCourseDAO();
    private final UserDAO userDAO = new MysqlUserDAO();
    private final DiplomaDAO diplomaDAO = new MysqlDiplomaDAO();
    private final LectionDAO lectionDAO = new MysqlLectionDAO();
    private final TestDAO testDAO = new MysqlTestDAO();
    private final SubscriptionDAO subscriptionDAO = new MysqlSubscriptionDAO();
    private final CompletedTestDAO completedTestDAO = new MysqlCompletedTestDAO();
    private final QuestionDAO questionDAO = new MysqlQuestionDAO();
    private final AnswerDAO answerDAO = new MysqlAnswerDAO();
    private final MysqlRoleDAO roleDAO = new MysqlRoleDAO();
    private final MysqlCategoryDAO categoryDAO = new MysqlCategoryDAO();

    private DAOFactory() { }

    public static DAOFactory getInstance() {
        return factory;
    }

    public CourseDAO getCourseDAO() {
        return courseDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public DiplomaDAO getDiplomaDAO() { return diplomaDAO; }

    public LectionDAO getLectionDAO() { return  lectionDAO; }

    public TestDAO getTestDAO() { return testDAO; }

    public SubscriptionDAO getSubscriptionDAO() { return subscriptionDAO; }

    public CompletedTestDAO getCompletedTestDAO() { return completedTestDAO; }

    public QuestionDAO getQuestionDAO() { return questionDAO; }

    public AnswerDAO getAnswerDAO() { return answerDAO; }

    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    public CategoryDAO getCategoryDAO() {return categoryDAO;}
}