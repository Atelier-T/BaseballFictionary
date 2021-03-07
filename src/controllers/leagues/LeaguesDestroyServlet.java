package controllers.leagues;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.League;
import utils.DBUtil;

/**
 * Servlet implementation class LeaguesDestroyServlet
 */
@WebServlet("/leagues/destroy")
public class LeaguesDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaguesDestroyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            // セッションスコープからメッセージのIDを取得して
            // 該当のIDのメッセージ1件のみをデータベースから取得
            League l = em.find(League.class, (Integer)(request.getSession().getAttribute("league_id")));

            em.getTransaction().begin();
            em.remove(l);       // データ削除
            em.getTransaction().commit();
            em.close();

            // セッションスコープ上の不要になったデータを削除
            request.getSession().removeAttribute("league_id");

            // indexページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/leagues/index?id=" + l.getTitles().getTitle_id());
        }
    }

}
