package database;

import sharedClasses.User;
import sharedClasses.UserTransferVOneImpl;

import java.sql.*;

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
                user.setUsedMaterials();
                user.setLicences();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }
}
