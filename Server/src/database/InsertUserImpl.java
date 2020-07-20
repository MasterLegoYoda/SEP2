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

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/MakerSpace", "postgres",
                    "password");
            PreparedStatement st = connection.prepareStatement("UPDATE USER SET status=?, licences=? WHERE ID="+user.getID());

            Array licences = connection.createArrayOf("integer",user.getLicences().toArray());

            st.setByte(1,user.getStatus());
            st.setArray(2,licences);
            st.executeUpdate();

            Statement st2 = connection.prepareStatement("DELETE FROM Usage WHERE ID="+user.getID());

            Statement st3 = connection.createStatement();

            for(int i=0;i<usedMaterials.size();i++){
                String query=("INSERT INTO Usage "+"VALUES ("+user.getID()+","+usedMaterials.get(i).getMaterial().getName()+","+usedMaterials.get(i).getUnitUsed()+")");
                st3.executeUpdate(query);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
