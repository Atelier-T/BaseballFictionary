package controllers.now_status;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Character_list;
import models.NotPlayer;
import models.NowStatus;
import models.Player;
import utils.DBUtil;

/**
 * Servlet implementation class NowStatusDestroyServlet
 */
@WebServlet("/status/destroy")
public class NowStatusDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NowStatusDestroyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            //削除対象の詳細情報と、それに紐づく情報を抽出
            Character_list c = em.find(Character_list.class, (Integer)(request.getSession().getAttribute("before_id")));
            NowStatus n = c.getNow_status();
            Player p = new Player();
            NotPlayer np = new NotPlayer();
            if (n.getPlayers() != null) {
                p = n.getPlayers();
            }
            if (n.getNot_players() != null) {
                np = n.getNot_players();
            }

            //いったん登場人物から詳細情報を削除する
            c.setNow_status(null);

            //下位の情報から先に削除していく
            if (np != null) {
                em.getTransaction().begin();
                em.remove(np);       // データ削除
                em.getTransaction().commit();
            }

            if (p != null) {
                em.getTransaction().begin();
                em.remove(p);       // データ削除
                em.getTransaction().commit();
            }

            em.getTransaction().begin();
            em.remove(n);       // データ削除
            em.getTransaction().commit();

            //空になった最新情報に自動で別の最新情報を入れる
            List<NowStatus> latest_n = em.createNamedQuery("getCharactersAllNowStatus", NowStatus.class)
                                            .setParameter("characters", c)
                                            .getResultList();
            if(latest_n.size() > 0) {
                //いったん最新年度の詳細情報を入れる
                c.setNow_status(latest_n.get(0));
                //現在の作中年度と一致する詳細情報があればそちらを優先
                for(int i = 0; i < latest_n.size(); i++) {
                    if(latest_n.get(i).getNow_year() == c.getTitles().getYear() + c.getTitles().getElapsed_year()){
                        c.setNow_status(latest_n.get(i));
                    }
                }
                em.getTransaction().begin();
                em.getTransaction().commit();
            }

            em.close();

            // セッションスコープ上の不要になったデータを削除
            request.getSession().removeAttribute("before_id");

            response.sendRedirect(request.getContextPath() + "/characters/show?id=" + n.getCharacters().getChara_id());
        }
    }

}
