package repositories;

import models.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageRepositoryImpl implements MessageRepository {

    //language=SQL
    private static final String SQL_FIND_ALL = "select * from messages";

    //language=SQL
    private static final String SQL_INSERT_MESSAGE = "INSERT INTO messages (username,message,date) " +
            "VALUES (?, ?, ?) returning id,username,message,date";

    public MessageRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;

    @Override
    public List<Message> findAll() {
        try {
            List<Message> messages = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String message = resultSet.getString("message");
                String date = resultSet.getString("date");
                Message mes = new Message(id,username,message,date);
                messages.add(mes);
            }
            return messages;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Message insertMessage(String username, String message, String date) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_MESSAGE);
            statement.setString(1,username);
            statement.setString(2,message);
            statement.setString(3,date);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                return new Message(id,username,message,date);
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Message> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void safe(Message entity) {

    }

    @Override
    public void update(Message entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Message entity) {

    }
}