package servlets;

import models.User;
import repositories.Singleton;
import repositories.UsersRepository;
import repositories.UsersRepositoryJdbcImpl;
import services.Helper;
import services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private Helper helper;
    private LoginService loginService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        helper.render(req, resp, "login.ftl",new HashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        User user = usersRepository.findByUsername(username);
        if(user != null && user.getPassword().equals(password)){
            root.put("name", username);
            Cookie cookie = new Cookie("username",username);
            cookie.setMaxAge(40000000);
            resp.addCookie(cookie);
            HttpSession session = req.getSession();
            session.setAttribute("username",username);
            helper.render(req, resp, "profile.ftl", root);
            // исправить,добавить root
        }
        if (user != null && !(user.getPassword().equals(password))){
            root.put("message","incorrect password");
            helper.render(req, resp, "login.ftl", root);
        }
        if (user == null){
            root.put("message","User not found");
            helper.render(req, resp, "login.ftl", root);
        }
        }

    private Connection connection;
    private UsersRepository usersRepository;
    @Override
    public void init() {
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
