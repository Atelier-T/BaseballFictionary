package controllers.now_status;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Title;
import utils.DBUtil;

/**
 * Servlet implementation class NowStatusNewServlet
 */
@WebServlet("/status/new")
public class NowStatusNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NowStatusNewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Title t = em.find(Title.class, Integer.parseInt(request.getParameter("id")));

        long characters_check = (long)em.createNamedQuery("getMyCharactersCount", Long.class)
                                            .setParameter("titles", t)
                                            .getSingleResult();

        em.close();

        request.setAttribute("titles", t);
        request.setAttribute("characters_check", characters_check);
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/status/new.jsp");
        rd.forward(request, response);
    }

}
