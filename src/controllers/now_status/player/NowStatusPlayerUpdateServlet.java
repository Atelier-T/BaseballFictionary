package controllers.now_status.player;

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
import models.NowStatus;
import models.Player;
import models.Team;
import models.Title;
import models.validators.NowStatusValidator;
import utils.DBUtil;

/**
 * Servlet implementation class NowStatusPlayerUpdateServlet
 */
@WebServlet("/status/player/update")
public class NowStatusPlayerUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NowStatusPlayerUpdateServlet() {
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
            Character_list c_before = em.find(Character_list.class, (Integer)(request.getSession().getAttribute("now_id")));
            NowStatus n = c_before.getNow_status();
            Player p = n.getPlayers();

            //選択した変更要素
            Character_list c = em.find(Character_list.class, Integer.parseInt(request.getParameter("chara_name")));
            Team t = em.find(Team.class, Integer.parseInt(request.getParameter("team_name")));

            n.setCharacters(c);

            try{
                n.setNow_year(Integer.parseInt(request.getParameter("now_year")));
            } catch(Exception e){ }

            n.setChara_flag(0);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            n.setUpdated_at(currentTime);


            List<String> errors = new ArrayList<String>();

            //登場人物の変更があった場合となかった場合でvalidate分岐
            if (c.getChara_id() == c_before.getChara_id()) {
                errors = NowStatusValidator.validate_year(n);
            } else {
                errors = NowStatusValidator.validate(n, c, true);
            }

            if(errors.size() > 0) {
                Title titles = c_before.getTitles();

                List<Character_list> characters = em.createNamedQuery("getMyAllCharacters", Character_list.class)
                        .setParameter("titles", titles)
                        .getResultList();

                List<Team> teams = em.createNamedQuery("getMyAllTeams", Team.class)
                        .setParameter("titles", titles)
                        .getResultList();

                em.close();

                request.setAttribute("now_status", n);
                request.setAttribute("players", p);
                request.setAttribute("characters", characters);
                request.setAttribute("teams", teams);
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/status/player/edit.jsp");
                rd.forward(request, response);
            } else {
                p.setNow_status(n);

                if(request.getParameter("player_name") == null || request.getParameter("player_name").equals("")){
                    p.setPlayer_name(c.getChara_name());
                } else {
                    p.setPlayer_name(request.getParameter("player_name"));
                }
                if(request.getParameter("player_name_read") == null || request.getParameter("player_name_read").equals("")){
                    p.setPlayer_name_read(c.getChara_name_read());
                } else {
                    p.setPlayer_name_read(request.getParameter("player_name_read"));
                }

                p.setTeams(t);

                p.setPosision1(Integer.parseInt(request.getParameter("posision1")));
                p.setPosision2(Integer.parseInt(request.getParameter("posision2")));
                p.setPosision3(Integer.parseInt(request.getParameter("posision3")));
                p.setPosision4(Integer.parseInt(request.getParameter("posision4")));
                p.setPosision5(Integer.parseInt(request.getParameter("posision5")));

                p.setPosision_detail(request.getParameter("posision_detail"));
                p.setNumber(request.getParameter("number"));

                p.setThrowing(Integer.parseInt(request.getParameter("throwing")));
                p.setBatting(Integer.parseInt(request.getParameter("batting")));
                p.setPlayer_type1(Integer.parseInt(request.getParameter("player_type1")));
                p.setPlayer_type2(Integer.parseInt(request.getParameter("player_type2")));

                try{
                    p.setSalary(Integer.parseInt(request.getParameter("salary")));
                } catch(Exception e){ }


                p.setMusic(request.getParameter("music"));
                p.setPlayer_information(request.getParameter("player_information"));
                p.setPerformance(request.getParameter("performance"));
                p.setAther_player_information(request.getParameter("ather_player_information"));

                p.setUpdated_at(currentTime);

                if (c.getChara_id() != c_before.getChara_id()) {
                    c_before.setNow_status(null);
                    c.setNow_status(n);
                }

                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("now_id");

                response.sendRedirect(request.getContextPath() + "/characters/index?id=" + c.getTitles().getTitle_id());
            }
        }
    }

}
