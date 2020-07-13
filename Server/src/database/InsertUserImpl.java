package database;

import sharedClasses.User;
import sharedClasses.UserTransferVOneImpl;

import java.sql.*;
import java.util.ArrayList;

public class InsertUserImpl implements InsertUser
{
    private Connection connection;

    @Override
    public void insertUser(UserTransferVOneImpl user) throws SQLException {
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/MakerSpace", "postgres",
                    "password");
            Statement st = connection.createStatement();
            String query1 = "UPDATE User SET status="+user.getStatus()+" WHERE id="+user.getID();

            ArrayList<Boolean> lc = user.getLicences();

            String query2 = "UPDATE USER SET licences= CASE ";
            for (int i=0;i<lc.size();i++){
                query2+=" WHEN id_d["+i+"] THEN "+lc.get(i) ;
            }
            query2+="END";

            user.getUsedMaterials();
            String query3 = "UPDATE USER SET materials";
            String query4 = "UPDATE USER SET prices";

            st.executeQuery(query1+query2+query3+query4);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
