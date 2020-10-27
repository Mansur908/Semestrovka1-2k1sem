package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.TicketFormData;
import models.Ticket;
import repositories.Singleton;
import repositories.TicketRepository;
import repositories.UsersRepository;
import repositories.UsersRepositoryJdbcImpl;
import services.Helper;
import services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/start")
public class test extends HttpServlet {
    private Helper helper;
    private UsersRepository usersRepository;
    private ObjectMapper objectMapper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        helper.render(req, resp, "tickets.ftl", new HashMap<>());
    }

    private Connection connection;
    private TicketRepository ticketRepository;

    @Override
    public void init() throws ServletException {
        helper = new Helper();
    }
}
