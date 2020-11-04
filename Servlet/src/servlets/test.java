package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import repositories.TicketRepository;
import repositories.UsersRepository;
import services.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

@WebServlet("/start")
public class test extends HttpServlet {
    private Helper helper;
    private UsersRepository usersRepository;
    private ObjectMapper objectMapper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        helper.render(req, resp, "messages.ftl", new HashMap<>());
    }

    private Connection connection;
    private TicketRepository ticketRepository;

    @Override
    public void init() throws ServletException {
        helper = new Helper();
    }
}
