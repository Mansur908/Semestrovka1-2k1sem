package servlets;

import models.Ticket;
import repositories.Singleton;
import repositories.TicketRepository;
import repositories.TicketRepositoryImpl;
import services.Helper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/admin")
public class AdminServlet  extends HttpServlet {
    private Helper helper;
    private TicketRepository ticketRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        helper.render(req, resp, "admin.ftl",new HashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String company = req.getParameter("com");
        String departureP = req.getParameter("depP");
        String arrivalP = req.getParameter("ariP");
        String departureT = req.getParameter("depT");
        String arrivalT = req.getParameter("ariT");
        String day = req.getParameter("day");
        String price = req.getParameter("price");
        String link = req.getParameter("link");
        Map<String, Object> root = new HashMap<>();
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Ticket ticket = ticketRepository.findTicket(departureP,arrivalP,company,departureT,arrivalT,day,price);
        if (ticket == null){
            Ticket t = ticketRepository.insertTicket(departureP,arrivalP,company,departureT,arrivalT,day,price,link);
            if (t == null){
                root.put("message", "Ticket not insert");
                helper.render(req, resp, "admin.ftl", root);
            }
            else {
                root.put("message", "Ticket added");
                helper.render(req, resp, "admin.ftl", root);
            }
        }
        else {
            root.put("message", "Ticket already exist");
            helper.render(req, resp, "admin.ftl", root);
        }

    }

    @Override
    public void init() {
        helper = new Helper();

        try{
            Connection connection = Singleton.getSingleton().doSinglton();
            ticketRepository = new TicketRepositoryImpl(connection);
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

    }
}