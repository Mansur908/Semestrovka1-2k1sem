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

//    private DataSource dataSource;
public UsersRepositoryJdbcImpl(Connection connection){
    this.connection = connection;
//        try {
//        } catch (SQLException e) {
//            throw new IllegalStateException(e);
//        }
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
    public User findByUsrername(String username) {
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


//    @Override
//    public List<User> findAll() {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = dataSource.getConnection();
//            statement = connection.prepareStatement(SQL_SELECT);
////            statement.setInt();
//            resultSet = statement.executeQuery();
//
//            List<User> users = new ArrayList<>();
//            while (resultSet.next()){
//                User user = new User(
//                        resultSet.getLong("id"),
//                        resultSet.getString("username"),
//                        resultSet.getString("password"));
//                users.add(user);
//            }
//
//            return users;
//
//        }catch (SQLException e){
//            throw new IllegalStateException(e);
//        }finally {
//            if(resultSet != null){
//                try {
//                    resultSet.close();
//                }catch (SQLException ignore ){}
//            }
//            if (statement != null){
//                try {
//                    statement.close();
//                }catch (SQLException ignore){}
//            }
//            if (connection != null){
//                try {
//                    connection.close();
//                }catch (SQLException ignore){}
//            }
//        }
//    }
