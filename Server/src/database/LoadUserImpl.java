package database;

import sharedClasses.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class LoadUserImpl implements LoadUser
{
    private Connection connection;
    @Override
    public User loadUser(int id) throws SQLException {
        UserTransferVOneImpl user = new UserTransferVOneImpl(id);
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/MakerS", "postgres",
                    "password");
            Statement st = connection.createStatement();
            String query = "SELECT * FROM User WHERE id="+id;
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                user.setStatus(rs.getByte("status"));

                Array licencesDB = rs.getArray("licences");
                Boolean[] list = (Boolean[])licencesDB.getArray();
                user.setLicences(new ArrayList(Arrays.asList(list)));


                ArrayList<UsedMaterial> ums = new ArrayList<>();

                String query2 = "SELECT * FROM Usage WHERE id="+id;
                ResultSet rs2 = st.executeQuery(query2);
                while(rs2.next()){
                    String query3 = "SELECT * FROM Material WHERE material="+rs2.getString("material");
                    ResultSet rs3 = st.executeQuery(query3);
                    float price = rs3.getFloat("price");
                    ums.add(new UsedMaterial(new Material(rs2.getString("material"),price),rs2.getInt("unit")));
                }

                user.setUsedMaterials(ums);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean checkUserExistence(int id) throws SQLException {
        boolean response=false;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/MakerSpace", "postgres",
                    "password");
            Statement st = connection.createStatement();
            String query = "SELECT*FROM \"User\" WHERE id="+id;
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
