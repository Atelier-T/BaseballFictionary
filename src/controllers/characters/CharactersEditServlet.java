package controllers.characters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Character_list;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class CharactersEditServlet
 */
@WebServlet("/characters/edit")
public class CharactersEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CharactersEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Character_list c = em.find(Character_list.class, Integer.parseInt(request.getParameter("id")));

        User login_user = (User)request.getSession().getAttribute("login_user");

        em.close();

        if(c != null && login_user.getUser_id() == c.getTitles().getUsers().getUser_id()) {
            request.setAttribute("characters", c);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("chara_id", c.getChara_id());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/characters/edit.jsp");
        rd.forward(request, response);
    }

}
