package database;

import sharedClasses.Material;
import sharedClasses.UsedMaterial;
import sharedClasses.User;
import sharedClasses.UserTransferVOneImpl;

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

                Array materials = rs.getArray("materials");
                String[] materialList = (String[])materials.getArray();
                ArrayList<String> materialListAL = new ArrayList(Arrays.asList(materialList));

                Array prices = rs.getArray("prices");
                Integer[] priceList = ((Integer[])prices.getArray());
                ArrayList<Integer> priceListAL = new ArrayList<>(Arrays.asList(priceList));

                ArrayList<UsedMaterial> ums = new ArrayList<>();
                int i=0;
                while(i<materialListAL.size()){
                    ums.add(new UsedMaterial(new Material(materialListAL.get(i),priceListAL.get(i)),0));
                    i++;
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
