package controllers.data.tool1;

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
import models.League;
import models.NotPlayer;
import models.NowStatus;
import models.Player;
import models.Team;
import models.Title;
import models.validators.Tool1Validator;
import utils.DBUtil;

/**
 * Servlet implementation class Tool1CreateServlet
 */
@WebServlet("/data/tool1/create")
public class Tool1CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tool1CreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Title t = em.find(Title.class, (Integer)(request.getSession().getAttribute("title_id")));

            List<String> errors = Tool1Validator.validate(request.getParameter("create_year"));

            if(errors.size() > 0){
                List<League> leagues = em.createNamedQuery("getMyAllLeagues", League.class)
                        .setParameter("titles", t)
                        .getResultList();

                List<Team> teams = em.createNamedQuery("getMyAllTeams", Team.class)
                        .setParameter("titles", t)
                        .getResultList();

                List<Character_list> characters = em.createNamedQuery("getMyAllCharacters", Character_list.class)
                        .setParameter("titles", t)
                        .getResultList();

                em.close();

                request.setAttribute("titles", t);
                request.setAttribute("leagues", leagues);
                request.setAttribute("teams", teams);
                request.setAttribute("characters", characters);
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/data/tool1/index.jsp");
                rd.forward(request, response);
            }
            else {
              //作成する年度
                Integer create_year = Integer.parseInt(request.getParameter("create_year"));

                //対象作品の球団関係者
                List<Character_list> characters_p = em.createNamedQuery("getMyAllCharactersPlayers", Character_list.class)
                                                        .setParameter("titles", t)
                                                        .getResultList();
                //対象作品の球団関係者以外
                List<Character_list> characters_n = em.createNamedQuery("getMyAllCharactersNotPlayers", Character_list.class)
                                                        .setParameter("titles", t)
                                                        .getResultList();
                //作成NGの人物格納用
                List<Character_list> ng_p = new ArrayList<Character_list>();
                List<Character_list> ng_n = new ArrayList<Character_list>();
                Character_list now_ng = new Character_list();

                //作成NGの人物を作成リストから除外する
                for(int i = 0; i < characters_p.size(); i++){
                    if(request.getParameter("NG1_" + i) != null){
                        ng_p.add(em.find(Character_list.class, Integer.parseInt(request.getParameter("NG1_" + i))));
                    }
                }
                if(ng_p.size() > 0){
                    for(int i = 0; i < ng_p.size(); i++){
                        now_ng = ng_p.get(i);
                        characters_p.remove(characters_p.indexOf(now_ng));
                    }
                }

                for(int i = 0; i < characters_n.size(); i++){
                    if(request.getParameter("NG2_" + i) != null){
                        ng_n.add(em.find(Character_list.class, Integer.parseInt(request.getParameter("NG2_" + i))));
                    }
                }
                if(ng_n.size() > 0){
                    for(int i = 0; i < ng_n.size(); i++){
                        now_ng = ng_n.get(i);
                        characters_n.remove(characters_n.indexOf(now_ng));
                    }
                }

                //作成用の変数
                Character_list now_c = new Character_list();
                List<NowStatus> create_n_list = new ArrayList<NowStatus>();
                NowStatus create_n = new NowStatus();
                NowStatus new_n = new NowStatus();
                Player create_p = new Player();
                Player new_p = new Player();
                NotPlayer create_np = new NotPlayer();
                NotPlayer new_np = new NotPlayer();

                //作成リストから順番に人物を取り出し、作成　player
                for(int i = 0; i < characters_p.size(); i++){
                    //いったんデータをクリアする
                    now_c = null;
                    create_n_list = null;
                    create_n = null;
                    new_n = null;
                    create_p = null;
                    new_p = null;

                    now_c = characters_p.get(i);
                    create_n_list = em.createNamedQuery("getCharactersAndYearsNowStatus", NowStatus.class)
                                    .setParameter("characters", now_c)
                                    .setParameter("now_year", create_year)
                                    .getResultList();

                    //作成する年度の詳細情報がない場合
                    if(create_n_list.size() == 0){
                        create_n_list = em.createNamedQuery("getCharactersNowStatusLatestYear", NowStatus.class)
                                    .setParameter("characters", now_c)
                                    .getResultList();
                        //最新年度の詳細情報が取得できた場合
                        if(create_n_list.size() > 0) {
                            create_n = create_n_list.get(0);
                            create_p = create_n.getPlayers();
                            //複製
                            new_n = new NowStatus(create_n);
                            new_p = new Player(create_p);

                            //複製データの編集
                            new_n.setNow_year(create_year);
                            new_n.setPlayers(null);
                            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                            new_n.setCreated_at(currentTime);
                            new_n.setUpdated_at(currentTime);
                            new_p.setCreated_at(currentTime);
                            new_p.setUpdated_at(currentTime);

                            em.getTransaction().begin();
                            em.persist(new_n);
                            em.getTransaction().commit();

                            new_p.setNow_status(new_n);

                            em.getTransaction().begin();
                            em.persist(new_p);
                            em.getTransaction().commit();

                            new_n.setPlayers(new_p);

                            em.getTransaction().begin();
                            em.persist(new_n);
                            em.getTransaction().commit();

                            //登録年度が作中年度と同じなら、最新の詳細情報を変更
                            if(t.getYear() + t.getElapsed_year() == create_year){
                                now_c.setNow_status(new_n);

                                em.getTransaction().begin();
                                em.persist(now_c);
                                em.getTransaction().commit();
                            }
                        }
                    }
                }

                //作成リストから順番に人物を取り出し、作成　not_player
                for(int i = 0; i < characters_n.size(); i++){
                    //いったんデータをクリアする
                    now_c = null;
                    create_n_list = null;
                    create_n = null;
                    new_n = null;
                    create_np = null;
                    new_np = null;

                    now_c = characters_n.get(i);
                    create_n_list = em.createNamedQuery("getCharactersAndYearsNowStatus", NowStatus.class)
                                        .setParameter("characters", now_c)
                                        .setParameter("now_year", create_year)
                                        .getResultList();
                    //作成する年度の詳細情報がない場合
                    if(create_n_list.size() == 0){
                        create_n_list = em.createNamedQuery("getCharactersNowStatusLatestYear", NowStatus.class)
                                            .setParameter("characters", now_c)
                                            .getResultList();
                        //最新年度の詳細情報が取得できた場合
                        if(create_n_list.size() > 0) {
                            create_n = create_n_list.get(0);
                            create_np = create_n.getNot_players();
                            //複製
                            new_n = new NowStatus(create_n);
                            new_np = new NotPlayer(create_np);

                            //複製データの編集
                            new_n.setNow_year(create_year);
                            new_n.setNot_players(null);
                            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                            new_n.setCreated_at(currentTime);
                            new_n.setUpdated_at(currentTime);
                            new_np.setCreated_at(currentTime);
                            new_np.setUpdated_at(currentTime);

                            em.getTransaction().begin();
                            em.persist(new_n);
                            em.getTransaction().commit();

                            new_np.setNow_status(new_n);

                            em.getTransaction().begin();
                            em.persist(new_np);
                            em.getTransaction().commit();

                            new_n.setNot_players(new_np);

                            em.getTransaction().begin();
                            em.persist(new_n);
                            em.getTransaction().commit();

                            //登録年度が作中年度と同じなら、最新の詳細情報を変更
                            if(t.getYear() + t.getElapsed_year() == create_year){
                                now_c.setNow_status(new_n);

                                em.getTransaction().begin();
                                em.persist(now_c);
                                em.getTransaction().commit();
                            }
                        }
                    }
                }

                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/data/index?id=" + t.getTitle_id());

                request.getSession().removeAttribute("title_id");
            }
        }
    }

}
