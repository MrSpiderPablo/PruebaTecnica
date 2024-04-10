package org.lajaqueria.dao;

import java.sql.*;

public abstract class BaseDAO {

    protected void createDatabase(String dbPath){

        String url = "jdbc:sqlite:" + dbPath;

        String sql = "CREATE TABLE IF NOT EXISTS Empleados (\n" +
                " id integer PRIMARY KEY, \n" +
                " nombre text NOT NULL, \n" +
                " apellido text NOT NULL, \n" +
                " edad integer NOT NULL, \n" +
                " departamento integer NOT NULL, \n" +
                " FOREIGN KEY (departamento) REFERENCES Departamentos(id)\n" +
                ");";

        String sql2 = "CREATE TABLE IF NOT EXISTS Departamentos (\n" +
                " id integer PRIMARY KEY, \n" +
                " nombreDep text NOT NULL\n" +
                ");";

        try(Connection con = DriverManager.getConnection(url)) {

            if (con != null){
                DatabaseMetaData data = con.getMetaData();
                System.out.println("The driver name is: " + data.getDriverName());
                System.out.println("A new database has been created");
            }

            Statement st = con.createStatement();
            //System.out.println(sql2);
            st.execute(sql2);

            Statement st2 = con.createStatement();
            //System.out.println(sql);
            st2.execute(sql);


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
