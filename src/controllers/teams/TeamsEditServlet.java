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
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class TeamsEditServlet
 */
@WebServlet("/teams/edit")
public class TeamsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamsEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Team t = em.find(Team.class, Integer.parseInt(request.getParameter("id")));

        List<League> leagues = em.createNamedQuery("getMyAllLeagues", League.class)
                .setParameter("titles", t.getTitles())
                .getResultList();

        em.close();

        User login_user = (User)request.getSession().getAttribute("login_user");
        if(t != null && login_user.getUser_id() == t.getTitles().getUsers().getUser_id()) {
            request.setAttribute("teams", t);
            request.setAttribute("leagues", leagues);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("team_id", t.getTeam_id());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/teams/edit.jsp");
        rd.forward(request, response);
    }

}
