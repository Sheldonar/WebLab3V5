package WebServlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class WelcomeServlet extends HttpServlet {

    public static String URL = "http://localhost:8080/WebLab3V5_war_exploded/welcome";
    public static String JSP = "welcome.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        System.out.println("message"); //for tests
        if (message != null){
            request.setAttribute("message", message);
        }

        request.getRequestDispatcher(JSP).forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choice = request.getParameter("choice");
        switch (choice){
            case "yes":
                response.sendRedirect(SignInServlet.URL);
                return;
            case "no":
                response.sendRedirect(SignUpServlet.URL);
                return;
            default:
                response.sendRedirect(WelcomeServlet.URL);
        }
    }
}
