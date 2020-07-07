package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class User {

    private static User instance;

    public User() throws SQLException
    {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized User getInstance() throws SQLException {
        if(instance==null)
        {
            instance = new User();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5433/MakerSpace", "postgres",
                "password");
    }

    public boolean create(String name, String password){

    }
}
