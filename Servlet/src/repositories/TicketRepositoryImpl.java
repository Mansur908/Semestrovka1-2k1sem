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
            "departurePlace = ? AND arrivalPlace = ?";

    public TicketRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;

    @Override
    public List<Ticket> findByPlaces(String departurePlace, String arrivalPlace) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_DEPARTURE_AND_ARRIVAL);
            statement.setString(1,departurePlace);
            statement.setString(2,arrivalPlace);
            ResultSet resultSet = statement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String company = resultSet.getString("company");
                String departureTime = resultSet.getString("departureTime");
                String arrivalTime = resultSet.getString("arrivalTime");
                Ticket ticket = new Ticket(id, departurePlace, arrivalPlace, company, departureTime, arrivalTime);
                tickets.add(ticket);
            }
            return tickets;
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
