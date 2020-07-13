package database;

import sharedClasses.UsedMaterial;
import sharedClasses.User;
import sharedClasses.UserTransferVOneImpl;

import java.sql.*;
import java.util.ArrayList;

public class InsertUserImpl implements InsertUser
{
    private Connection connection;

    @Override
    public void insertUser(User user) throws SQLException {
        ArrayList<UsedMaterial> usedMaterials = user.getUsedMaterials();
        ArrayList<String> name;
        ArrayList<Integer> quantity;
        for(int i=0;i<usedMaterials.size();i++){
        usedMaterials.get(i).getMaterial().getName();
        }
        Object[] materials;
        Object[] prices;
        Object[] licences = user.getLicences().toArray();
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/MakerSpace", "postgres",
                    "password");



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
