package database;

import sharedClasses.Material;
import sharedClasses.MaterialList;

import java.sql.*;
import java.util.ArrayList;

public class LoadMaterialImpl implements LoadMaterial
{
    private Connection connection;
    @Override
    public MaterialList loadMaterial() throws SQLException {
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/MakerSpace", "postgres",
                    "password");
            Statement st = connection.createStatement();
            String query = "SELECT * FROM Material";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
