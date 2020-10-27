package repositories;

import models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from users";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from users where id = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_USERNAME = "select * from users where username = ?";

    //language=SQL
    private static final String SQL_INSERT_USER = "INSERT INTO users (username,password) VALUES (?, ?)";

    //language=SQL
    private static final String SQL_UPDATE_USER = "UPDATE users SET password = (?) " +
            "WHERE password = (SELECT password FROM users WHERE password = ? AND username = ?) AND " +
            "username = (SELECT username FROM users WHERE username = ?) returning id,username, password";



    public UsersRepositoryJdbcImpl(Connection connection){
        this.connection = connection;
    }

    private Connection connection;

    @Override
    public List<User> findAll() {
        Connection connection = null;
        try {
            List<User> users = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                User user = new User(id, username, password);

                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                return Optional.of(new User(id,username,password));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_USERNAME);
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String password = resultSet.getString("password");
                return new User(id,username,password);
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public User insertUser(String username, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER);
            statement.setString(1,username);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                return new User(id,username,password);
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public User changePassword(String newPassword,String oldPassword,String username) throws IllegalStateException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1, newPassword);
            statement.setString(2, oldPassword);
            statement.setString(3, username);
            statement.setString(4, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                return new User(id, username, newPassword);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public void safe(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(User entity) {

    }
}
