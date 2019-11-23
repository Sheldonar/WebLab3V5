package Filter;

import WebServlets.EnterServlet;
import WebServlets.WelcomeServlet;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            if ((((HttpServletRequest) servletRequest).getRequestURI().equals("/WebLab31_war_exploded/enter"))) {
                ((HttpServletResponse) servletResponse).sendRedirect(WelcomeServlet.URL);
                return;
            }
            else
                filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
            int counter = 0;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("SessionId")) {
                    String s = request.getRequestURI();
                    if (request.getRequestURI().equals("/WebLab31_war_exploded/welcome")) {
                        response.sendRedirect(EnterServlet.URL);
                        return;
                    }
                    else if (request.getRequestURI().equals("/WebLab31_war_exploded/sign_in")) {
                        response.sendRedirect(EnterServlet.URL);
                        return;
                    }
                    else if (request.getRequestURI().equals("/WebLab31_war_exploded/sign_up")) {
                        response.sendRedirect(EnterServlet.URL);
                        return;
                    }
                }
                counter++;
            }
            if (counter < 2){
                if (request.getRequestURI().equals("/WebLab31_war_exploded/enter")) {
                    response.sendRedirect(WelcomeServlet.URL);
                    return;
                }
            }
            if (!request.getRequestURI().equals("/WebLab31_war_exploded/enter")
                    && !request.getRequestURI().equals("/WebLab31_war_exploded/welcome")
                    && !request.getRequestURI().equals("/WebLab31_war_exploded/sign_in")
                    && !request.getRequestURI().equals("/WebLab31_war_exploded/sign_up")) {
                response.sendRedirect(WelcomeServlet.URL);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

}
