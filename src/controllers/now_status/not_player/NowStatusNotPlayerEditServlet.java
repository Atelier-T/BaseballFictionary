package controllers.now_status.not_player;

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
import models.NotPlayer;
import models.NowStatus;
import models.Title;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class NowStatusNotPlayerEditServlet
 */
@WebServlet("/status/not/edit")
public class NowStatusNotPlayerEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NowStatusNotPlayerEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        NowStatus n = em.find(NowStatus.class, Integer.parseInt(request.getParameter("id")));

        NotPlayer np = n.getNot_players();

        Title t = n.getCharacters().getTitles();

        List<Character_list> characters = em.createNamedQuery("getMyAllCharacters", Character_list.class)
                .setParameter("titles", t)
                .getResultList();

        User login_user = (User)request.getSession().getAttribute("login_user");

        em.close();

        if(n != null && login_user.getUser_id() == n.getCharacters().getTitles().getUsers().getUser_id()) {
            request.setAttribute("now_status", n);
            request.setAttribute("not_players", np);
            request.setAttribute("characters", characters);
            request.setAttribute("titles", t);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("before_id", n.getCharacters().getChara_id());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/status/not/edit.jsp");
        rd.forward(request, response);
    }

}
