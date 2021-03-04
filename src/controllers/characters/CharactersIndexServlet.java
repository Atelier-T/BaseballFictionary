package controllers.characters;

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
import models.Title;
import utils.DBUtil;

/**
 * Servlet implementation class CharactersIndexServlet
 */
@WebServlet("/characters/index")
public class CharactersIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CharactersIndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Title t = em.find(Title.class, Integer.parseInt(request.getParameter("id")));

        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) { }

        List<Character_list> characters = em.createNamedQuery("getMyAllCharacters", Character_list.class)
                                .setParameter("titles", t)
                                .setFirstResult(15 * (page - 1))
                                .setMaxResults(15)
                                .getResultList();

        long characters_count = (long)em.createNamedQuery("getMyCharactersCount", Long.class)
                                      .setParameter("titles", t)
                                      .getSingleResult();

        em.close();

        request.setAttribute("page", page);
        request.setAttribute("characters", characters);
        request.setAttribute("characters_count", characters_count);
        request.setAttribute("titles", t);
        request.setAttribute("_token", request.getSession().getId());

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/characters/index.jsp");
        rd.forward(request, response);
    }

}
