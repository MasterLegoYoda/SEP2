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
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        for(int i=0;i<usedMaterials.size();i++){
        name.add(usedMaterials.get(i).getMaterial().getName());
        quantity.add(usedMaterials.get(i).getUnitUsed());
        }

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/MakerSpace", "postgres",
                    "password");
            PreparedStatement st = connection.prepareStatement("UPDATE USER SET status=?, licences=?,materials=?,quantity=? WHERE ID="+user.getID());

            Array materialNames = connection.createArrayOf("char",name.toArray());
            Array quantities = connection.createArrayOf("integer",quantity.toArray());
            Array licences = connection.createArrayOf("integer",user.getLicences().toArray());

            st.setByte(1,user.getStatus());
            st.setArray(2,licences);
            st.setArray(3,materialNames);
            st.setArray(4,quantities);
            st.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
