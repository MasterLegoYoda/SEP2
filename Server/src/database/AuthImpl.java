package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AuthImpl implements Auth
{
    private Connection c;

    public AuthImpl(){
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5433", "postgres",
                            "password");
        }
        catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticate(int id, int password) throws SQLException {
    return false;
    }
}
