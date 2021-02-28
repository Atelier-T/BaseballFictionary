package controllers.teams;

import java.io.IOException;
import java.sql.Timestamp;
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
import models.validators.TeamValidator;
import utils.DBUtil;

/**
 * Servlet implementation class TeamsCreateServlet
 */
@WebServlet("/teams/create")
public class TeamsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamsCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Title t = em.find(Title.class, Integer.parseInt(request.getParameter("id")));

            request.getSession().setAttribute("title_id", t);

            Team te = new Team();

            te.setTeam_name(request.getParameter("team_name"));
            te.setLeagues((League)request.getSession().getAttribute("league_name"));
            te.setTitles((Title)request.getSession().getAttribute("title_id"));
            te.setTeam_information(request.getParameter("team_information"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            te.setCreated_at(currentTime);
            te.setUpdated_at(currentTime);

            List<String> errors = TeamValidator.validate(te);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("titles", t);
                request.setAttribute("teams", te);
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/teams/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(te);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/teams/index?id=" + t.getTitle_id());
            }

            request.getSession().removeAttribute("title_id");
        }
    }

}
