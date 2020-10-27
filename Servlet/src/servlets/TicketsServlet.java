package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Ticket;
import models.User;
import repositories.*;
import services.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/ticket")
public class TicketsServlet extends HttpServlet {
    private Helper helper;
    private TicketRepository ticketRepository;
//    private ObjectMapper objectMapper;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        helper.render(req, resp, "tickets.ftl", new HashMap<>());
        ObjectMapper objectMapper = new ObjectMapper();
        String query = req.getParameter("query");
        String query1 = req.getParameter("query1");
        List<Ticket> tickets =  ticketRepository.findByPlaces(query,query1);
        System.out.println(tickets);
        String result = objectMapper.writeValueAsString(tickets);
//        resp.setStatus(200);
        resp.getWriter().write(result);
    }

    @Override
    public void init() throws ServletException {
        helper = new Helper();

        try{
            Connection connection = Singleton.getSingleton().doSinglton();
            ticketRepository = new TicketRepositoryImpl(connection);
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

    }
}