package controllers.now_status;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Character_list;
import models.NowStatus;
import utils.DBUtil;

/**
 * Servlet implementation class NowStatusChangeServlet
 */
@WebServlet("/status/change")
public class NowStatusChangeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NowStatusChangeServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            NowStatus n = em.find(NowStatus.class, Integer.parseInt(request.getParameter("status_year")));
            Character_list c = n.getCharacters();

            c.setNow_status(n);

            em.getTransaction().begin();
            em.getTransaction().commit();

            em.close();
            request.getSession().setAttribute("flush", "詳細情報を切り替えました。");

            response.sendRedirect(request.getContextPath() + "/characters/show?id=" + c.getChara_id());
        }
    }
}
