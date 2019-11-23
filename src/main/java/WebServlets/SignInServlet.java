package WebServlets;

import DAO.DAOLoginHash;
import DAO.DAOLoginPassword;
import Entity.UserHash;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class SignInServlet extends HttpServlet {

    public static String URL = "http://localhost:8080/WebLab3V5_war_exploded/sing_in";
    public static String JSP = "sing_in.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        System.out.println(message);

        if (message != null) {
            request.setAttribute("message", message);
        }
        request.getRequestDispatcher(JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        DAOLoginPassword daoLoginPassword = new DAOLoginPassword();

        boolean isOk = daoLoginPassword.hasUser(login, password);
        if (isOk){
            String id = UUID.randomUUID().toString();
            DAOLoginHash daoLoginHash = new DAOLoginHash();
            UserHash userHash = new UserHash(login, id);
            daoLoginHash.addHash(userHash);

            Cookie cookie = new Cookie("SessionId", id);
            response.addCookie(cookie);
            response.sendRedirect(EnterServlet.URL);
        }
        else{
            response.sendRedirect(SignUpServlet.URL);
        }
    }
}
