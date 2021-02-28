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
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class LeaguesEditServlet
 */
@WebServlet("/leagues/edit")
public class LeaguesEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaguesEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        League l = em.find(League.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        User login_user = (User)request.getSession().getAttribute("login_user");
        if(l != null && login_user.getUser_id() == l.getTitles().getUsers().getUser_id()) {
            request.setAttribute("leagues", l);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("league_id", l.getLeague_id());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/leagues/edit.jsp");
        rd.forward(request, response);
    }

}
