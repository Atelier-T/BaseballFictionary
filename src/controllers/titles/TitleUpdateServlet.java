package controllers.titles;

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

import models.Title;
import models.validators.TitleValidator;
import utils.DBUtil;

/**
 * Servlet implementation class TitleUpdateServlet
 */
@WebServlet("/titles/update")
public class TitleUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TitleUpdateServlet() {
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

            t.setTitle_name(request.getParameter("title_name"));
            t.setTitle_url(request.getParameter("title_url"));
            t.setTitle_information(request.getParameter("title_information"));

            try {
                t.setTitle_count(Integer.parseInt(request.getParameter("title_count")));
            } catch (Exception e) { }
            try {
                t.setYear(Integer.parseInt(request.getParameter("year")));
            } catch (Exception e) { }
            try {
                t.setElapsed_year(Integer.parseInt(request.getParameter("elapsed_year")));
            } catch (Exception e) { }

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            t.setUpdated_at(currentTime);

            List<String> errors = TitleValidator.validate(t);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("titles", t);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/titles/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("title_id");

                response.sendRedirect(request.getContextPath() + "/titles/index");
            }
        }
    }
}
