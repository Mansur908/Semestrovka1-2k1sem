package servlets;

import models.User;
import repositories.Singleton;
import repositories.UsersRepository;
import repositories.UsersRepositoryJdbcImpl;
import services.Helper;
import services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.*;


@WebServlet("/reg")
public class RegServlet extends HttpServlet {
    private Helper helper;
    private LoginService loginService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        helper.render(req, resp, "registration.ftl",new HashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        User user = usersRepository.findByUsername(username);
        if (user == null) {
            root.put("message", "You are reristered");
            helper.render(req, resp, "login.ftl", root);
            usersRepository.insertUser(username, password);
        } else {
            root.put("message", "You already have account");
            helper.render(req, resp, "login.ftl", root);
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