package servlets;

        import dao.DAOFactory;
        import dao.LectionDAO;
        import hibernate.LectionEntity;
        import servlets.core.AbstractViewServlet;
        import javax.servlet.annotation.WebServlet;

@WebServlet("/Lection")
public class Lection extends AbstractViewServlet<LectionEntity, LectionDAO> {

    protected LectionDAO getDao(){
        return DAOFactory.getInstance().getLectionDAO();
    }

    @Override
    protected String getJspName(){
        return "Lection.jsp";
    }
}
