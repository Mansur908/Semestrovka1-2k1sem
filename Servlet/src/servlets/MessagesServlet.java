package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import filters.AuthFilter;
import models.Message;
import repositories.MessageRepository;
import repositories.MessageRepositoryImpl;
import repositories.Singleton;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/messages")
public class MessagesServlet extends HttpServlet {
    private MessageRepository messageRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Cookie[] cookie = req.getCookies();
        String user = null;
        if (cookie != null) {
            user = AuthFilter.getCookieValue(cookie);
        }
        List<Message> mes =  messageRepository.findAll();
        List<Message> messages = new ArrayList<>();
        for (Message m : mes){
            if (m.getUsername().equals(user)){
                m.setEgualCookie(true);
                messages.add(m);
            }
            else {
                messages.add(m);
            }
        }
        String result = objectMapper.writeValueAsString(messages);
        resp.setStatus(200);
        resp.getWriter().write(result);
    }

    @Override
    public void init() {

        try{
            Connection connection = Singleton.getSingleton().doSinglton();
            messageRepository = new MessageRepositoryImpl(connection);
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}