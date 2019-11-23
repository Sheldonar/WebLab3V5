package WebServlets;

import DAO.DAOLoginHash;
import DAO.DAOLoginPassword;
import Entity.UserHash;
import Entity.UserPassword;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class SignUpServlet extends HttpServlet {

    public static String URL = "http://localhost:8080/WebLab3V5_war_exploded/sing_up";
    public static String JSP = "sing_up.jsp";

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

        boolean isValidPass = daoLoginPassword.hasUser(login, password);
        if (isValidPass) {
            response.sendRedirect(SignInServlet.URL);
        } else {
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();
            DAOLoginHash daoLoginHash = new DAOLoginHash();
            boolean isValidHash = daoLoginHash.hasHash(login, id);
            if (isValidHash){
                response.sendRedirect(SignInServlet.URL);
            } else {
                try {
                    UserPassword userPassword = new UserPassword(login, password);
                    daoLoginPassword.addUser(userPassword);
                    UserHash userHash = new UserHash(login, id);
                    daoLoginHash.addHash(userHash);
                    Cookie cookie = new Cookie("SessionId", id);
                    response.addCookie(cookie);
                    response.sendRedirect(EnterServlet.URL);
                } catch (PersistenceException e) {
                    response.sendRedirect(SignInServlet.URL);
                    System.out.println("попался");
                }
            }

        }

    }
}
