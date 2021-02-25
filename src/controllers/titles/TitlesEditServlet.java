package controllers.titles;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Title;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class TitlesEditServlet
 */
@WebServlet("/titles/edit")
public class TitlesEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TitlesEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Title t = em.find(Title.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        User login_user = (User)request.getSession().getAttribute("login_user");
        if(t != null && login_user.getUser_id() == t.getUsers().getUser_id()) {
            request.setAttribute("titles", t);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("title_id", t.getTitle_id());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/titles/edit.jsp");
        rd.forward(request, response);
    }

}
