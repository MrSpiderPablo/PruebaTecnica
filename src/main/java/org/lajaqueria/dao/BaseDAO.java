package org.lajaqueria.dao;

import java.sql.*;

public abstract class BaseDAO {

    protected void createDatabase(String dbPath) {

        Connection con = null;

        Statement st = null;

        Statement st2 = null;

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

        try  {

            con = DriverManager.getConnection(url);

            DatabaseMetaData data = con.getMetaData();
            System.out.println("The driver name is: " + data.getDriverName());
            System.out.println("A new database has been created");


            st = con.createStatement();
            st.execute(sql2);

            st2 = con.createStatement();
            st2.execute(sql);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            if (con != null){
                try {
                    con.close();
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }

            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

                if (st2 != null) {
                    try {
                        st2.close();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                }
            }
        }
    }
}