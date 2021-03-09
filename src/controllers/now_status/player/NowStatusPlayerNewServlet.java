package controllers.now_status.player;

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
import models.NowStatus;
import models.Player;
import models.Team;
import models.Title;
import utils.DBUtil;

/**
 * Servlet implementation class NowStatusPlayerNewServlet
 */
@WebServlet("/status/player/new")
public class NowStatusPlayerNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NowStatusPlayerNewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Title t = em.find(Title.class, Integer.parseInt(request.getParameter("id")));

        List<Character_list> characters = em.createNamedQuery("getMyAllCharacters", Character_list.class)
                .setParameter("titles", t)
                .getResultList();

        List<Team> teams = em.createNamedQuery("getMyAllTeams", Team.class)
                .setParameter("titles", t)
                .getResultList();

        NowStatus n = new NowStatus();
        Player p = new Player();
        //「この人物の詳細情報を登録する」用
        if(request.getSession().getAttribute("c_id") != null) {
            Character_list c = em.find(Character_list.class, (Integer)(request.getSession().getAttribute("c_id")));
            n.setCharacters(c);
        }
        //「この情報を元に詳細情報を新規作成」用
        if(request.getSession().getAttribute("now_id") != null) {
            n = em.find(NowStatus.class, (Integer)(request.getSession().getAttribute("now_id")));
            p = n.getPlayers();
        }

        em.close();

        request.setAttribute("characters", characters);
        request.setAttribute("teams", teams);
        request.setAttribute("titles", t);
        request.setAttribute("_token", request.getSession().getId());

        request.setAttribute("now_status", n);
        request.setAttribute("players", p);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/status/player/new.jsp");
        rd.forward(request, response);
    }

}
