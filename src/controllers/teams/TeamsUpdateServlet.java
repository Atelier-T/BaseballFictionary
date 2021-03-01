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
import models.validators.TeamValidator;
import utils.DBUtil;

/**
 * Servlet implementation class TeamsUpdateServlet
 */
@WebServlet("/teams/update")
public class TeamsUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamsUpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Team t = em.find(Team.class, (Integer)(request.getSession().getAttribute("team_id")));

            try {
                League l = em.find(League.class, Integer.parseInt(request.getParameter("league_name")));
                t.setLeagues(l);
            } catch (Exception e) { }

            t.setTeam_name(request.getParameter("team_name"));
            t.setTeam_information(request.getParameter("team_information"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            t.setUpdated_at(currentTime);

            List<String> errors = TeamValidator.validate(t);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("teams", t);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/teams/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("team_id");

                response.sendRedirect(request.getContextPath() + "/teams/index?id=" + t.getTitles().getTitle_id());
            }
        }
    }

}
