package servlets;

import models.User;
import repositories.Singleton;
import repositories.UsersRepository;
import repositories.UsersRepositoryJdbcImpl;
import services.Helper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;


@WebServlet("/reg")
public class RegServlet extends HttpServlet {
    private Helper helper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        helper.render(req, resp, "registration.ftl",new HashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
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
            if (user.getUsername() == ""){
                root.put("message", "Enter login and password");
                helper.render(req, resp, "registration.ftl", root);
            }
            else {
                root.put("message", "Username already exists");
                helper.render(req, resp, "registration.ftl", root);
            }
        }
    }

    private UsersRepository usersRepository;
    @Override
    public void init()  {
        helper = new Helper();

        try{
            Connection connection = Singleton.getSingleton().doSinglton();
            usersRepository = new UsersRepositoryJdbcImpl(connection);
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}