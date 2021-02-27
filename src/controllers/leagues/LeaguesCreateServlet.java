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
import models.Title;
import models.validators.LeagueValidator;
import utils.DBUtil;

/**
 * Servlet implementation class LeaguesCreateServlet
 */
@WebServlet("/leagues/create")
public class LeaguesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaguesCreateServlet() {
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

            League l = new League();

            l.setLeague_name(request.getParameter("league_name"));
            l.setTitles((Title)request.getSession().getAttribute("title_id"));
            l.setCountry_flag(Integer.parseInt(request.getParameter("country_flag")));
            l.setLeague_information(request.getParameter("league_information"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            l.setCreated_at(currentTime);
            l.setUpdated_at(currentTime);

            List<String> errors = LeagueValidator.validate(l);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("titles", t);
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("leagues", l);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/leagues/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(l);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/leagues/index?id=" + t.getTitle_id());
            }

            request.getSession().removeAttribute("title_id");
        }
    }
}
