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
import models.NowStatus;
import utils.DBUtil;

/**
 * Servlet implementation class CharacterShowServlet
 */
@WebServlet("/characters/show")
public class CharactersShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CharactersShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Character_list c = em.find(Character_list.class, Integer.parseInt(request.getParameter("id")));

        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) { }

        List<NowStatus> now_status = em.createNamedQuery("getCharactersAllNowStatus", NowStatus.class)
                .setParameter("characters", c)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

        long status_count = (long)em.createNamedQuery("getCharactersNowStatusCount", Long.class)
                      .setParameter("characters", c)
                      .getSingleResult();

        em.close();

        request.setAttribute("characters", c);
        request.setAttribute("now_status", now_status);
        request.setAttribute("status_count", status_count);
        request.setAttribute("page", page);
        request.setAttribute("_token", request.getSession().getId());

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/characters/show.jsp");
        rd.forward(request, response);
    }
}
