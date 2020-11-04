package servlets;

import filters.AuthFilter;
import repositories.MessageRepository;
import repositories.MessageRepositoryImpl;
import repositories.Singleton;
import services.Helper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


@WebServlet("/addmessage")
public class AddMessageServlet extends HttpServlet {
    private Helper helper;
    private MessageRepository messageRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        helper.render(req, resp, "messages.ftl",new HashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String mes = req.getParameter("message");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Cookie[] cookie = req.getCookies();
        String username = null;
        if (cookie != null) {
            username = AuthFilter.getCookieValue(cookie);
        }
        if (username != null){
            Date dateNow = new Date();
            SimpleDateFormat formatNow = new SimpleDateFormat("dd.MM.yyyy' 'hh:mm");
            messageRepository.insertMessage(username,mes,formatNow.format(dateNow));
        }
        helper.render(req, resp, "messages.ftl",new HashMap<>());
    }

    @Override
    public void init() {
        helper = new Helper();

        try{
            Connection connection = Singleton.getSingleton().doSinglton();
            messageRepository = new MessageRepositoryImpl(connection);
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
