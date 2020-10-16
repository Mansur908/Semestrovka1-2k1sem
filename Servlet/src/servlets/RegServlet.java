package servlets;

import models.User;
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

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        boolean result = loginService.registration(username, password);
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
//        Map<String, Object> root = new HashMap<>();
//        if(result){
//            root.put("message","You are reristered,enter login and password");
//            helper.render(req, resp, "login.ftl", root);
//        }else{
//            root.put("message","You already have account");
//            helper.render(req, resp, "login.ftl", root);
//        }
//    }
//
//    @Override
//    public void init() throws ServletException {
//        helper = new Helper();
//        loginService = new LoginService();
//    }
//}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        boolean result = loginService.registration(username, password);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        User user = usersRepository.findByUsrername(username);
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
    public void init() throws ServletException {
        helper = new Helper();
        loginService = new LoginService();

        try{
            String dbUrl ="jdbc:postgresql://localhost:5432/sem1";
            String dbUsername="postgres";
            String dbPassword ="mansur1213";
            String driverClassName ="org.postgresql.Driver";

            Class.forName(driverClassName);
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            usersRepository = new UsersRepositoryJdbcImpl(connection);

        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}