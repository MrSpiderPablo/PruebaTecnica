import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertApp {

    private Connection connect(){

        String url = "jdbc:sqlite:C://sqlite/db/gestionEmpresarial.db";

        Connection con = null;

        try {
            con = DriverManager.getConnection(url);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return con;
    }

    private void insert(Integer id, String nombre, String apellido, Integer edad, String departamento){

        String sql = "INSERT INTO Empleados(id, nombre, apellido, edad, departamento) VALUES(?,?,?,?,?)";

        try(Connection con = this.connect();
        PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellido);
            pstmt.setInt(4, edad);
            pstmt.setString(5, departamento);
            pstmt.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){

        InsertApp app = new InsertApp();

        app.insert(77244057, "Pablo", "Ramos Blanquez", 21, "Informatica");
        app.insert(66588943, "Manolo", "Escobar", 40, "Marketing");
    }
}
