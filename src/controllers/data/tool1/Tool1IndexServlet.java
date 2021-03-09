package controllers.data.tool1;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Character_list;
import models.League;
import models.Team;
import models.Title;
import utils.DBUtil;

/**
 * Servlet implementation class Tool1IndexServlet
 */
@WebServlet("/data/tool1/index")
public class Tool1IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tool1IndexServlet() {
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

        List<Team> teams = em.createNamedQuery("getMyAllTeams", Team.class)
                                    .setParameter("titles", t)
                                    .getResultList();

        List<Character_list> characters = em.createNamedQuery("getMyAllCharacters", Character_list.class)
                                    .setParameter("titles", t)
                                    .getResultList();

        em.close();

        request.setAttribute("titles", t);
        request.setAttribute("leagues", leagues);
        request.setAttribute("teams", teams);
        request.setAttribute("characters", characters);
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/data/tool1/index.jsp");
        rd.forward(request, response);
    }

}
