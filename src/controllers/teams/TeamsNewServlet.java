package controllers.teams;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.League;
import models.Team;
import models.Title;
import utils.DBUtil;

/**
 * Servlet implementation class TeamsNewServlet
 */
@WebServlet("/teams/new")
public class TeamsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamsNewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Title t = em.find(Title.class, Integer.parseInt(request.getParameter("id")));

        List<League> leagues = em.createNamedQuery("getMyAllLeagues", League.class)
                                    .setParameter("titles", t)
                                    .getResultList();

        em.close();

        request.setAttribute("titles", t);
        request.setAttribute("leagues", leagues);
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("teams", new Team());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/teams/new.jsp");
        rd.forward(request, response);
    }

}
