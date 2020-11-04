package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Message;
import repositories.MessageRepository;
import repositories.MessageRepositoryImpl;
import repositories.Singleton;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/messages")
public class MessagesServlet extends HttpServlet {
    private MessageRepository messageRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Message> massages =  messageRepository.findAll();
        String result = objectMapper.writeValueAsString(massages);
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