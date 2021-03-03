package controllers.characters;

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

import models.Character;
import models.Title;
import models.validators.CharacterValidator;
import utils.DBUtil;

/**
 * Servlet implementation class CharactersCreateServlet
 */
@WebServlet("/characters/create")
public class CharactersCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CharactersCreateServlet() {
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

            Character c = new Character();

            request.getSession().setAttribute("title_id", t);

            c.setChara_name(request.getParameter("chara_name"));
            c.setChara_name_read(request.getParameter("chara_name_read"));

            c.setTitles(t);

            c.setOriginal(request.getParameter("original"));
            c.setChara_model(request.getParameter("chara_model"));
            c.setChara_priority(Integer.parseInt(request.getParameter("chara_priority")));

            try{
                c.setBirth_year(Integer.parseInt(request.getParameter("birth_year")));
            } catch(Exception e){ }

            c.setBirth_place(request.getParameter("birth_place"));

            try{
                c.setAppearance(Integer.parseInt(request.getParameter("appearance")));
            } catch(Exception e){ }

            c.setAppearance_flag(Integer.parseInt(request.getParameter("appearance_flag")));

            c.setChara_information(request.getParameter("chara_information"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            c.setCreated_at(currentTime);
            c.setUpdated_at(currentTime);

            List<String> errors = CharacterValidator.validate(c);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("titles", t);
                request.setAttribute("characters", c);
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/characters/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(c);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/characters/index?id=" + t.getTitle_id());
            }

            request.getSession().removeAttribute("title_id");
        }
    }
}
