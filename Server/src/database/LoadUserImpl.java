package database;

import sharedClasses.User;
import sharedClasses.UserTransferVOneImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadUserImpl implements LoadUser
{
    private Connection connection;
    @Override
    public User loadUser(int id) throws SQLException {
        UserTransferVOneImpl user = new UserTransferVOneImpl(id);
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/MakerSpace", "postgres",
                    "password");
            Statement st = connection.createStatement();
            String query = "SELECT * FROM User WHERE id="+id;
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                user.setStatus(rs.getByte("status"));
                Array licencesDB = rs.getArray("licences");
                Boolean[] list = (Boolean[])licencesDB.getArray();
                user.setLicences(new ArrayList(Arrays.asList(list)));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }
}
