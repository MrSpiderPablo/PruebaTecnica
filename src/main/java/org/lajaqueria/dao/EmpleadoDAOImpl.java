package org.lajaqueria.dao;

import org.lajaqueria.core.Departamento;
import org.lajaqueria.core.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAOImpl extends BaseDAO implements EmpleadoDAO {

    private String dbPath;

    public EmpleadoDAOImpl(String dbPath){

        this.dbPath = dbPath;
        createDatabase(dbPath);

    }

    private Connection connect(){

        String url = "jdbc:sqlite:" + dbPath;

        Connection con = null;

        try {
            con = DriverManager.getConnection(url);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return con;
    }
    @Override
    public void insertar(Empleado empleado) {
        insert(empleado.getId(), empleado.getNombre(), empleado.getApellido(), empleado.getEdad(), empleado.getDepartamento());
    }

    private void insert(Integer id, String nombre, String apellido, Integer edad, Departamento departamento){

        String sql = "INSERT INTO Empleados(id, nombre, apellido, edad, departamento) VALUES(?,?,?,?,?)";

        try(Connection con = this.connect();
            PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellido);
            pstmt.setInt(4, edad);
            pstmt.setInt(5, departamento.getId());
            pstmt.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void borrar(int id) {

        String sql = "DELETE  FROM Empleados WHERE id = ?";

        try(Connection con = this.connect();
        PreparedStatement pstmt2 = con.prepareStatement(sql)) {

            pstmt2.setInt(1, id);

            pstmt2.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Empleado> getAll() {

        String sql = "SELECT * FROM Empleados";

        List<Empleado> list = new ArrayList<>();

        try(Connection con = this.connect();
        PreparedStatement pstmt4 = con.prepareStatement(sql)) {



            ResultSet rs = pstmt4.executeQuery();

            while (rs.next()){
                list.add(convertirEmpleado(rs));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Empleado obtenerEmpleadoPorId(int id) {

        String sql = "SELECT * FROM Empleados WHERE id = ?";

        try(Connection con = this.connect();
        PreparedStatement pstmt3 = con.prepareStatement(sql)) {

            pstmt3.setInt(1, id);

           ResultSet rs = pstmt3.executeQuery();


           if (rs.next()){

               return convertirEmpleado(rs);
           }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Empleado actualizarEmpleado(Empleado empleado) {

        String sql = "UPDATE Empleados SET  nombre = ?, apellido = ?, edad = ? WHERE id = ?";

        try(Connection con = this.connect();
        PreparedStatement pstmt5 = con.prepareStatement(sql)) {

            pstmt5.setString(1, empleado.getNombre());
            pstmt5.setString(2, empleado.getApellido());
            pstmt5.setInt(3, empleado.getEdad());
            pstmt5.setInt(4, empleado.getId());

            pstmt5.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return empleado;
    }

    private Empleado convertirEmpleado(ResultSet rs) throws SQLException {

        Empleado empleado = new Empleado();
        Departamento departamento = new Departamento();

        empleado.setId(rs.getInt(1));
        empleado.setNombre(rs.getString(2));
        empleado.setApellido(rs.getString(3));
        empleado.setEdad(rs.getInt(4));
        empleado.setDepartamento(departamento);
        empleado.getDepartamento().setId(rs.getInt(5));

        return empleado;
    }
}
