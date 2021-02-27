package controllers.leagues;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.League;
import models.Title;
import utils.DBUtil;

/**
 * Servlet implementation class LeaguesNewServlet
 */
@WebServlet("/leagues/new")
public class LeaguesNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaguesNewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Title t = em.find(Title.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("titles", t);
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("leagues", new League());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/leagues/new.jsp");
        rd.forward(request, response);
    }

}
