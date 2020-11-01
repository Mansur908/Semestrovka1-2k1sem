package repositories;

import models.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketRepositoryImpl implements TicketRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_DEPARTURE_AND_ARRIVAL = "select * from tickets where " +
            "departurePlace = ? AND arrivalPlace = ? AND day = ?";

    //language=SQL
    private static final String SQL_INSERT_TICKET = "INSERT INTO tickets (departureplace,arrivalplace,company,departuretime,arrivaltime,day,price,link)" +
            " VALUES (?,?,?,?,?,?,?,?) returning id, departureplace, arrivalplace, company, departuretime, arrivaltime, day, price, link";

    //language=SQL
    private static final String SQL_FIND_TICKET = "SELECT * from tickets where departureplace = ? AND arrivalplace = ? AND company = ? AND " +
            "departuretime = ? AND arrivaltime = ? AND day = ? AND price = ?";

    public TicketRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;

    @Override
    public List<Ticket> findByPlaces(String departurePlace, String arrivalPlace,String day) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_DEPARTURE_AND_ARRIVAL);
            statement.setString(1,departurePlace);
            statement.setString(2,arrivalPlace);
            statement.setString(3,day);
            ResultSet resultSet = statement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String company = resultSet.getString("company");
                String departureTime = resultSet.getString("departureTime");
                String arrivalTime = resultSet.getString("arrivalTime");
                String price = resultSet.getString("price");
                String link = resultSet.getString("link");
                Ticket ticket = new Ticket(id, departurePlace, arrivalPlace, company, departureTime, arrivalTime,day,price,link);
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Ticket insertTicket(String departurePlace, String arrivalPlace, String company, String departureTime, String arrivalTime, String day, String price, String link) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_TICKET);
            statement.setString(1,departurePlace);
            statement.setString(2,arrivalPlace);
            statement.setString(3,company);
            statement.setString(4,departureTime);
            statement.setString(5,arrivalTime);
            statement.setString(6,day);
            statement.setString(7,price);
            statement.setString(8,link);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(true);
                Long id = resultSet.getLong("id");
                Ticket ticket = new Ticket(id, departurePlace, arrivalPlace, company, departureTime, arrivalTime,day,price,link);
                return ticket;
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Ticket findTicket(String departurePlace, String arrivalPlace, String company, String departureTime, String arrivalTime, String day, String price) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_TICKET);
            statement.setString(1,departurePlace);
            statement.setString(2,arrivalPlace);
            statement.setString(3,company);
            statement.setString(4,departureTime);
            statement.setString(5,arrivalTime);
            statement.setString(6,day);
            statement.setString(7,price);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String link = resultSet.getString("link");
                Ticket ticket = new Ticket(id, departurePlace, arrivalPlace, company, departureTime, arrivalTime,day,price,link);
                return ticket;
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void safe(Ticket entity) {

    }

    @Override
    public void update(Ticket entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Ticket entity) {

    }
}
