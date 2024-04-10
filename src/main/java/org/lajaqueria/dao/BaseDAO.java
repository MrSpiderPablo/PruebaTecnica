package org.lajaqueria.dao;

import java.sql.*;

public abstract class BaseDAO {

    protected void createDatabase(String dbPath){

        String url = "jdbc:sqlite:" + dbPath;

        String sql = """
                 CREATE TABLE IF NOT EXISTS Empleados (
                 id integer PRIMARY KEY,
                 nombre text NOT NULL,
                 apellido text NOT NULL,
                 edad integer NOT NULL,
                 departamento integer NOT NULL,
                 FOREIGN KEY (departamento) REFERENCES Departamentos(id)
                );""";

        String sql2 = """
                 CREATE TABLE IF NOT EXISTS Departamentos (
                 id integer PRIMARY KEY,
                 nombreDep text NOT NULL,
                );""";

        try(Connection con = DriverManager.getConnection(url)) {

            if (con != null){
                DatabaseMetaData data = con.getMetaData();
                System.out.println("The driver name is: " + data.getDriverName());
                System.out.println("A new database has been created");
            }

            Statement st = con.createStatement();
            st.execute(sql2);

            Statement st2 = con.createStatement();
            st2.execute(sql);


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
