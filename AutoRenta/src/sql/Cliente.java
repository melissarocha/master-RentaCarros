package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Cliente {

    /**
     * Metodo para agregar clientes a la base de datos.
     * @param usuario
     * @param contra
     * @param nombre
     * @param fechaNacimiento
     * @param direccion
     * @param email
     * @param telefono
     * @param telefonoCasa
     * @return 
     */
    public static boolean addCliente(String nombre, String ciudad, String referencia,
            String fechaNacimiento, String direccion, String telefono, String numLicencia) {

        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        String Query = "INSERT INTO CLIENTES(NOMBRE, TELEFONO,"
                + " DIRECCION, CIUDAD, FECHA_NACIMIENTO,"
                + " REFERENCIA, NUMERO_DE_LICENCIA)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {

            PreparedStatement stat = link.prepareStatement(Query);

            stat.setString(1, nombre);
            stat.setString(2, telefono);
            stat.setString(3, direccion);
            stat.setString(4, ciudad);
            stat.setString(5, fechaNacimiento);
            stat.setString(6, referencia);
            stat.setString(7, numLicencia);
            stat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static int clientesConRentas(String numLic){
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        ResultSet val = null;
        int idClient=0;
        String Query = "SELECT FACTURAS.ID_CLIENTE FROM FACTURAS "
                + "INNER JOIN CLIENTES ON FACTURAS.ID_CLIENTE = CLIENTES.ID_CLIENTE"
                + " WHERE ESTADO = 0 AND NUMERO_DE_LICENCIA=?";

        try {
            PreparedStatement stat = link.prepareStatement(Query);
            stat.setString(1, numLic);
            val = stat.executeQuery();
            if (val.next()){
                idClient=val.getInt("ID_CLIENTE");
                return idClient;
            }
            else{
                return idClient;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return idClient;
        }
    }
    
    /**
     * Metodo para obtener el nombre de los clientes de la base de datos
     * @return true si el query se ejecuto correctamente
     */
    public static ResultSet getClientes(){
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        ResultSet val = null;
        String Query = "SELECT NOMBRE FROM CLIENTES";

        try {
            PreparedStatement stat = link.prepareStatement(Query);
            val = stat.executeQuery();

            return val;

        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return val;
        }
    }
    
    /**
     * Metodo para borrar clientes de la base de datos mediante su nombre
     * @param nombre que es el nombre del empleados
     * @return true si el query se ejecuto correctamente
     */
    public static boolean borrarCliente(String nombre, int idCliente){
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        String Query;

        Query = "DELETE FROM FACTURAS WHERE ID_CLIENTE = ?";
       
        try {
            PreparedStatement stat = link.prepareStatement(Query);

            stat.setInt(1, idCliente);

            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         Query = "DELETE FROM CLIENTES WHERE NOMBRE = ?";
        try {
            PreparedStatement stat = link.prepareStatement(Query);

            stat.setString(1, nombre);

            stat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static ResultSet buscarCliente(String x) {
        String Query;
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();

        Query = "SELECT * FROM CLIENTES WHERE NOMBRE LIKE ? ";

        try {
            PreparedStatement stat = link.prepareStatement(Query);
            stat.setString(1, x + "%");
            ResultSet busqueda = stat.executeQuery();
            return busqueda;
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
