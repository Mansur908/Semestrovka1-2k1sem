package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/profile")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession(false);

        Cookie [] cookie = request.getCookies();
        String userValue = null;
        if (cookie != null) {
            userValue = getCookieValue(cookie);
        }

        if ((session == null || session.getAttribute("username") == null) && userValue == null){
            request.getServletContext().getRequestDispatcher("/login").forward(request, response);
        }
        filterChain.doFilter(request,response);
    }

    public static String getCookieValue(Cookie [] a){
        String str = null;
            for (Cookie c : a) {
                if (c.getName().equals("username")) {
                    str = c.getValue();
                }
            }
        return str;
    }

    @Override
    public void destroy() {

    }
}
