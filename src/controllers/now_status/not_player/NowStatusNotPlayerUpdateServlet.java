package controllers.now_status.not_player;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
 * Servlet implementation class NowStatusNotPlayerUpdateServlet
 */
@WebServlet("/status/not/update")
public class NowStatusNotPlayerUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NowStatusNotPlayerUpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            //変更前の登場人物データ
            Character_list c_before = em.find(Character_list.class, (Integer)(request.getSession().getAttribute("before_id")));
            NowStatus n = c_before.getNow_status();
            NotPlayer np = n.getNot_players();

            //選択した変更要素
            Character_list c = em.find(Character_list.class, Integer.parseInt(request.getParameter("chara_name")));

            //各種データ引用の為
            Title titles = c.getTitles();

            //詳細情報の対象人物を変更した場合
            if (c.getChara_id() != c_before.getChara_id()) {
                c_before.setNow_status(null);
                c.setNow_status(n);
            }

            n.setCharacters(c);

            try{
                n.setNow_year(Integer.parseInt(request.getParameter("now_year")));
            } catch(Exception e){ }

            n.setChara_flag(1);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            n.setUpdated_at(currentTime);

            //登録名が空の場合
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

            np.setChara_type_detail(request.getParameter("chara_type_detail"));

            np.setNot_player_information(request.getParameter("not_player_information"));

            np.setUpdated_at(currentTime);

            List<String> errors = new ArrayList<String>();

            //詳細情報の対象人物の変更があった場合となかった場合でvalidate分岐
            if (c.getChara_id() == c_before.getChara_id()) {
                errors = NowStatusValidator.validate_year(n);
            } else {
                errors = NowStatusValidator.validate(n, c, true);
            }

            if(errors.size() > 0) {
                //選択肢再送信用
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

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/status/not/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();

                //変更前人物の、「最新の詳細情報」に自動で詳細情報を入れ直す
                if (c.getChara_id() != c_before.getChara_id()) {
                    List<NowStatus> latest_n = em.createNamedQuery("getCharactersAllNowStatus", NowStatus.class)
                                                    .setParameter("characters", c_before)
                                                    .getResultList();
                    if(latest_n.size() > 0) {
                        //いったん最新年度の詳細情報を入れる
                        c_before.setNow_status(latest_n.get(0));
                        //現在の作中年度と一致する詳細情報があればそちらを優先
                        for(int i = 0; i < latest_n.size(); i++) {
                            if(latest_n.get(i).getNow_year() == titles.getYear() + titles.getElapsed_year()){
                                c_before.setNow_status(latest_n.get(i));
                            }
                        }
                        em.getTransaction().begin();
                        em.getTransaction().commit();
                    }
                }

                em.close();

                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("now_id");

                response.sendRedirect(request.getContextPath() + "/characters/index?id=" + c.getTitles().getTitle_id());
            }
        }
    }

}
