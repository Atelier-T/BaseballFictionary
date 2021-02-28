package controllers.leagues;

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
import models.validators.LeagueValidator;
import utils.DBUtil;

/**
 * Servlet implementation class LeaguesUpdateServlet
 */
@WebServlet("/leagues/update")
public class LeaguesUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaguesUpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            League l = em.find(League.class, (Integer)(request.getSession().getAttribute("league_id")));

            l.setLeague_name(request.getParameter("league_name"));
            l.setLeague_information(request.getParameter("league_information"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            l.setUpdated_at(currentTime);

            List<String> errors = LeagueValidator.validate(l);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("leagues", l);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/leagues/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("league_id");

                response.sendRedirect(request.getContextPath() + "/leagues/index?id=" + l.getTitles().getTitle_id());
            }
        }
    }
}
