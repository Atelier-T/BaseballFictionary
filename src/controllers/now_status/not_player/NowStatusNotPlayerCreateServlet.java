package controllers.now_status.not_player;

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

import models.Character_list;
import models.NotPlayer;
import models.NowStatus;
import models.Title;
import models.validators.NowStatusValidator;
import utils.DBUtil;

/**
 * Servlet implementation class NowStatusNotPlayerCreateServlet
 */
@WebServlet("/status/not/create")
public class NowStatusNotPlayerCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NowStatusNotPlayerCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Title titles = em.find(Title.class, Integer.parseInt(request.getParameter("id")));

            NowStatus n = new NowStatus();
            NotPlayer np = new NotPlayer();

            Character_list c = em.find(Character_list.class, Integer.parseInt(request.getParameter("chara_name")));

            n.setCharacters(c);

            try{
                n.setNow_year(Integer.parseInt(request.getParameter("now_year")));
            } catch(Exception e){ }

            //0.player 1.not_player
            n.setChara_flag(1);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            n.setCreated_at(currentTime);
            n.setUpdated_at(currentTime);

            //登録名と読み方が空欄の場合、characterの名前と読み方を入れる
            if(request.getParameter("not_player_name") == null || request.getParameter("not_player_name").equals("")){
                np.setNot_player_name(c.getChara_name());
            } else {
                np.setNot_player_name(request.getParameter("not_player_name"));
            }
            if(request.getParameter("not_player_name_read") == null || request.getParameter("not_player_name_read").equals("")){
                np.setNot_player_name_read(c.getChara_name_read());
            } else {
                np.setNot_player_name_read(request.getParameter("not_player_name_read"));
            }

            np.setChara_type1(Integer.parseInt(request.getParameter("chara_type1")));
            np.setChara_type2(Integer.parseInt(request.getParameter("chara_type2")));
            np.setChara_type3(Integer.parseInt(request.getParameter("chara_type3")));

            np.setChara_type_detail(request.getParameter("Chara_type_detail"));

            np.setNot_player_information(request.getParameter("not_player_information"));

            np.setCreated_at(currentTime);
            np.setUpdated_at(currentTime);

            List<String> errors = NowStatusValidator.validate(n, c, true);
            if(errors.size() > 0) {
                List<Character_list> characters = em.createNamedQuery("getMyAllCharacters", Character_list.class)
                        .setParameter("titles", titles)
                        .getResultList();

                em.close();

                request.setAttribute("now_status", n);
                request.setAttribute("not_players", np);
                request.setAttribute("characters", characters);
                request.setAttribute("titles", titles);
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/status/not/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(n);
                em.getTransaction().commit();

                np.setNow_status(n);

                em.getTransaction().begin();
                em.persist(np);
                em.getTransaction().commit();

                n.setNot_players(np);

                em.getTransaction().begin();
                em.persist(n);
                em.getTransaction().commit();

                //作中の現在年度の詳細情報の場合、自動で最新情報に入る
                if(titles.getYear() + titles.getElapsed_year() == n.getNow_year()){
                    c.setNow_status(n);

                    em.getTransaction().begin();
                    em.persist(c);
                    em.getTransaction().commit();
                }

                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/characters/show?id=" + c.getChara_id());

                //「この情報を元に詳細情報を新規作成」用
                if(request.getSession().getAttribute("now_id") != null) {
                    request.getSession().removeAttribute("now_id");
                }
            }
        }
    }

}
