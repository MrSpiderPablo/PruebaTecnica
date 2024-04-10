package org.lajaqueria.dao;

import org.lajaqueria.core.Departamento;
import org.lajaqueria.core.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAOImpl extends BaseDAO implements DepartamentoDAO {

    private String dbPath;

    public DepartamentoDAOImpl(String dbPath){
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
    public void insertar(Departamento departamento) {
        insert(departamento.getId(), departamento.getNombreDep());
    }

    private void insert(Integer id, String nombreDep){

        String sql = "INSERT INTO Departamentos(id, nombreDep) VALUES(?,?)";

        try(Connection con = this.connect();
            PreparedStatement psmt = con.prepareStatement(sql)) {

                psmt.setInt(1, id);
                psmt.setString(2, nombreDep);
                psmt.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void borrar(int id) {

        String sql = "DELETE  FROM Departamentos WHERE id = ?";

        try(Connection con = this.connect();
            PreparedStatement psmt2 = con.prepareStatement(sql)) {

            psmt2.setInt(1, id);

            psmt2.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Departamento> getAll() {
        String sql = "SELECT * FROM Departamentos";

        List<Departamento> list = new ArrayList<>();

        try(Connection con = this.connect();
            PreparedStatement psmt4 = con.prepareStatement(sql)) {



            ResultSet rs = psmt4.executeQuery();

            while (rs.next()){
                list.add(convertirDepartamento(rs));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return  list;
    }

    @Override
    public Departamento obtenerDepartamentoPorId(int id) {

        String sql = "SELECT * FROM Departamentos WHERE id = ?";

        try(Connection con = this.connect();
            PreparedStatement psmt3 = con.prepareStatement(sql)) {

            psmt3.setInt(1, id);

            ResultSet rs = psmt3.executeQuery();


            if (rs.next()){

                return convertirDepartamento(rs);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private Departamento convertirDepartamento(ResultSet rs)throws SQLException {

        Departamento departamento = new Departamento();

        departamento.setId(rs.getInt(1));
        departamento.setNombreDep(rs.getString(2));

        return departamento;

    }

    @Override
    public Departamento actuañizarDepartamento(Departamento departamento) {
        String sql = "UPDATE Departamentos SET  nombreDep = ? WHERE id = ?";

        try(Connection con = this.connect();
            PreparedStatement psmt5 = con.prepareStatement(sql)) {

            psmt5.setString(1, departamento.getNombreDep());
            psmt5.setInt(2, departamento.getId());

            psmt5.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return departamento;
    }
}
