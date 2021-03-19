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
 * Servlet implementation class FollowsDestroyServlet
 */
@WebServlet("/follows/destroy")
public class FollowsDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowsDestroyServlet() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        new Follow();
        Follow AllEmployee;
        if(_token!= null && _token.equals(request.getSession().getId())) {
            Employee e = (Employee)request.getSession().getAttribute("login_employee");
            EntityManager em = DBUtil.createEntityManager();
            Employee ei = em.find(Employee.class, Integer.parseInt(request.getParameter("id"))); //em.findは主キーの値しか検索できない
            AllEmployee = em.createNamedQuery("getAllEmployee", Follow.class)
             .setParameter("user_id", e)    //:user_id
             .setParameter("follow", ei)   //:follow
             .getSingleResult();
            em.getTransaction().begin();
            em.remove(AllEmployee);
            em.getTransaction().commit();


            response.sendRedirect(request.getContextPath() + "/reports/index");
        }
    }

}
