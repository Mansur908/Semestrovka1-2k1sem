package servlets;

import filters.AuthFilter;
import services.Helper;
import services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private Helper helper;
    private LoginService loginService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        Cookie[] cookie = req.getCookies();
        String user = null;
        if (cookie != null) {
            user = AuthFilter.getCookieValue(cookie);
        }
        if (user != null) {
            if (user.equals("123")) {
                root.put("message", "m");
            }
        }
        helper.render(req, resp, "profile.ftl",root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getAttribute("username");
        String exit = req.getParameter("exit");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        if (exit.equals("exit")) {
            if (req.getSession(false) != null) {
                if (req.getSession(false).getAttribute("username") != null) {
                    HttpSession session = req.getSession(false);
                    session.removeAttribute("username");
                }
            }
            Cookie cookie = new Cookie("username","");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
            helper.render(req, resp, "login.ftl",new HashMap<>());
        }
    }

    @Override
    public void init() throws ServletException {
        helper = new Helper();
        loginService = new LoginService();
    }
}