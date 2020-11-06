package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton {
    private static Singleton singleton;
    private static Connection connection;

    public static Singleton getSingleton(){
        if (singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }

    private Singleton(){
    }

    public Connection doSinglton() throws SQLException, ClassNotFoundException {
            String dbUrl ="jdbc:postgresql://localhost:5432/sem1";
            String dbUsername="postgres";
            String dbPassword ="mansur1213";
            String driverClassName ="org.postgresql.Driver";

            Class.forName(driverClassName);
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            return connection;
        }
    }

