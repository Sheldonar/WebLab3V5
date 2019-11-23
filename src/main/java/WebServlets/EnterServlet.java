package WebServlets;

import DAO.DAOLoginPassword;
import Entity.UserPassword;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EnterServlet extends HttpServlet {

    public static String URL = "http://localhost:8080/WebLab3V5_war_exploded/enter";
    public static String JSP = "enter.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message = request.getParameter("message");
        System.out.println(message);

        if (message != null) {
            request.setAttribute("message", message);
        }

        DAOLoginPassword daoLoginPassword = new DAOLoginPassword();
        List<UserPassword> list = daoLoginPassword.toList();
        String s = "";
        for (int i = 0; i < list.size(); i++){
            s += list.get(i).getLogin() + "<br>";
        }
        request.setAttribute("s", s);
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }
}
