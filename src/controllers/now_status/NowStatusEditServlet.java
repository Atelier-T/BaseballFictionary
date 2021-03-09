package controllers.now_status;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.NowStatus;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class NowStatusEditServlet
 */
@WebServlet("/status/edit")
public class NowStatusEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NowStatusEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        NowStatus n = em.find(NowStatus.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        User login_user = (User)request.getSession().getAttribute("login_user");

        if(n != null && login_user.getUser_id() == n.getCharacters().getTitles().getUsers().getUser_id()) {
            request.setAttribute("now_status", n);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("before_id", n.getCharacters().getChara_id());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/status/edit.jsp");
        rd.forward(request, response);
    }

}
