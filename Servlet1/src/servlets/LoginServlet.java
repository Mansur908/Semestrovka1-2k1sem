package servlets;

import models.User;
import repositories.Singleton;
import repositories.UsersRepository;
import repositories.UsersRepositoryJdbcImpl;
import services.Helper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private Helper helper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        helper.render(req, resp, "login.ftl",new HashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        User user = usersRepository.findByUsername(username);
        if(user != null && user.getPassword().equals(password) && (password != "" || username != "") ){
            root.put("name", username);
            Cookie cookie = new Cookie("username",username);
            cookie.setMaxAge(40000000);
            resp.addCookie(cookie);
            HttpSession session = req.getSession();
            session.setAttribute("username",username);
            if (username.equals("123")){
                root.put("message","m");
            }
            helper.render(req, resp, "profile.ftl", root);
        }
        if (user != null && password == "" && username == ""){
            root.put("message","Enter username and password");
            helper.render(req, resp, "login.ftl", root);
        }
        if (user != null && !(user.getPassword().equals(password)) && password != ""){
            root.put("message","Incorrect password");
            helper.render(req, resp, "login.ftl", root);
        }
        if (user != null && password == "" && username != ""){
            root.put("message","Enter your password");
            helper.render(req, resp, "login.ftl", root);
        }
        if (user == null){
            root.put("message","User not found");
            helper.render(req, resp, "login.ftl", root);
        }
        }

    private UsersRepository usersRepository;
    @Override
    public void init() {
        helper = new Helper();

        try{
            Connection connection = Singleton.getSingleton().doSinglton();
            usersRepository = new UsersRepositoryJdbcImpl(connection);
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

}
