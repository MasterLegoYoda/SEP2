package database;

import java.sql.*;

public class AuthImpl implements Auth
{
    private Connection connection;

    @Override
    public boolean authenticate(int id, int password) throws SQLException {
        boolean response=false;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/MakerSpace", "postgres",
                    "password");
            Statement st = connection.createStatement();
            String query = "SELECT*FROM \"User\" WHERE id="+id+"AND password="+password;
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                response = true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }
}
