package controllers.characters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Character_list;
import models.NowStatus;
import models.Player;
import utils.DBUtil;

/**
 * Servlet implementation class CharactersDestroyServlet
 */
@WebServlet("/characters/destroy")
public class CharactersDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CharactersDestroyServlet() {
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
            Character_list c = em.find(Character_list.class, (Integer)(request.getSession().getAttribute("chara_id")));

            List<NowStatus> n = new ArrayList<NowStatus>();
            List<Player> p = new ArrayList<Player>();
            Player _p;
            NowStatus _n;

            n = em.createNamedQuery("getCharactersAllNowStatus", NowStatus.class)
                                    .setParameter("characters", c)
                                    .getResultList();

            p = em.createNamedQuery("getCharactersAllPlayers", Player.class)
                                    .setParameter("characters", c)
                                    .getResultList();

            for(int i = 0; i < p.size(); i++){
                _p = p.get(i);
                em.getTransaction().begin();
                em.remove(_p);
                em.getTransaction().commit();
            }

            for(int i = 0; i < n.size(); i++){
                _n = n.get(i);
                em.getTransaction().begin();
                em.remove(_n);
                em.getTransaction().commit();
            }

            em.getTransaction().begin();
            em.remove(c);       // データ削除
            em.getTransaction().commit();

            em.close();

            // セッションスコープ上の不要になったデータを削除
            request.getSession().removeAttribute("character_id");

            // indexページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/characters/index?id=" + c.getTitles().getTitle_id());
        }
    }

}
