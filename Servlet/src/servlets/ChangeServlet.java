package servlets;

import filters.AuthFilter;
import models.User;
import repositories.Singleton;
import repositories.UsersRepository;
import repositories.UsersRepositoryJdbcImpl;
import services.Helper;
import services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.*;


@WebServlet("/change")
public class ChangeServlet extends HttpServlet {
    private Helper helper;
    private LoginService loginService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        helper.render(req, resp, "change.ftl",new HashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPassword = req.getParameter("new");
        String oldPassword = req.getParameter("old");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        Cookie[] cookie = req.getCookies();
        String username = null;
        if (cookie != null) {
            username = AuthFilter.getCookieValue(cookie);
        }
        User user = usersRepository.changePassword(newPassword,oldPassword,username);
        if (user == null) {
            root.put("message", "Old password isn't correct");
            helper.render(req, resp, "change.ftl", root);
        } else {
//            root.put("message", "Successfully");
            helper.render(req, resp, "profile.ftl", root);
//            req.getRequestDispatcher("/profile").forward(req,resp);
        }
    }

    private Connection connection;
    private UsersRepository usersRepository;
    @Override
    public void init()  {
        helper = new Helper();
        loginService = new LoginService();

        try{
            Connection connection = Singleton.getSingleton().doSinglton();
            usersRepository = new UsersRepositoryJdbcImpl(connection);
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
