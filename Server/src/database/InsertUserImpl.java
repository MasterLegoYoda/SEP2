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
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433", "postgres",
                    "password");
            Statement st = connection.createStatement();
            String query = "SELECT*FROM \"User\" WHERE id="+id+"AND password="+password;
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                if (rs.getInt(0)==0){response = true;}
                else {response = false;}
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
