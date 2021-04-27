import kazka.model.DataBase;
import kazka.model.FairyTale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    DataBase db = new DataBase();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameOfFairyTale = request.getParameter("nameOfFairyTale");
        String description = request.getParameter("description");
        String add = request.getParameter("add");
        String delete = request.getParameter("delete");
        String edit = request.getParameter("edit");
        String id = request.getParameter("id");
        String user = request.getParameter("user");
        String like = request.getParameter("like");


        if (like != null && like != "") {
            db.increaseLike(Integer.parseInt(like));
            db.addLikedFairyTale(Integer.parseInt(like));
        }
        if (user != null && user != "") {
            if (Integer.parseInt(user) == 1) {
                db.setAdmin(true);
                db.setInfo(true);
            } else if (Integer.parseInt(user) == 0) {
                db.setAdmin(false);
                db.setInfo(true);
            }
        }
        if (add != null && add != "") {
            if (id != null && id != "") {
                db.replace(Integer.parseInt(id), nameOfFairyTale, description);
                db.setInfo(true);
            } else {
                db.addFairyTale(0, nameOfFairyTale, description);
                db.setInfo(true);
            }
        }
        if (delete != null && delete != "") {
            int i = Integer.parseInt(delete);
            db.removeFairyTale(i);
            db.setInfo(true);
        }
        if (edit != null && edit != "") {
            response.sendRedirect("/KursachIlya_war_exploded/controller?id=" + edit);
            return;
        }
        response.sendRedirect("/KursachIlya_war_exploded/controller");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id != null && id != "") {
            FairyTale fairyTale = db.findById(Integer.parseInt(id));
            request.setAttribute("fairyTale", fairyTale);
        }
        request.setAttribute("db", db);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        db.setInfo(false);
    }
}

