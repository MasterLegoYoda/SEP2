package database;

import sharedClasses.User;

import java.sql.*;

public class InsertUserImpl implements InsertUser
{
    private Connection connection;

    @Override
    public void insertUser(User user) throws SQLException {
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/MakerSpace", "postgres",
                    "password");
            Statement st = connection.createStatement();
            String query = "UPDATE User SET status="+user.getStatus()+" WHERE id="+user.getID();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
