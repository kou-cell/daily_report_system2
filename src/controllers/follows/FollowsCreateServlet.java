package controllers.follows;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowsCreateServlet
 */
@WebServlet("/follows/create")
public class FollowsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowsCreateServlet() {
        super();
    }

    /**
     *
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token!= null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();
            Follow f = new Follow();
            f.setUser_id((Employee)request.getSession().getAttribute("login_employee"));
            Employee e = em.find(Employee.class, Integer.parseInt(request.getParameter("followed_id")));
            f.setFollow(e);
            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();

            response.sendRedirect(request.getContextPath() + "/reports/index");
        }

    }

}
