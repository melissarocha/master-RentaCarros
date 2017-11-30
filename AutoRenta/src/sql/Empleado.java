package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Empleado {
    
    static boolean isAdmin = false;
    static String nombreUsuario;
    static int IdEmpleado;

    /**
     * Metodo para agregar empleados a la base de datos.
     * @param usuario
     * @param contra
     * @param nombre
     * @param rol
     * @param fechaNacimiento
     * @param direccion
     * @param email
     * @param telefono
     * @param telefonoCasa
     * @return 
     */
    public static boolean addEmpleados(String usuario, String contra, int rol, String nombre, 
            String fechaNacimiento, String direccion, String email, String telefono, String telefonoCasa) {

        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        String Query = "insert into EMPLEADOS(USUARIO, CONTRASENIA, ROL_DEFINITION,"
                        + " NOMBRE, FECHA_NACIMIENTO,"
                        + " DIRECCION, EMAIL,"
                        + " TELEFONO, TELEFONO_CASA)"
                        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {

            PreparedStatement stat = link.prepareStatement(Query);

            stat.setString(1, usuario);
            stat.setString(2, contra);
            stat.setInt(3, rol);
            stat.setString(4, nombre);
            stat.setString(5, fechaNacimiento);
            stat.setString(6, direccion);
            stat.setString(7, email);
            stat.setString(8, telefono);
            stat.setString(9, telefonoCasa);
            stat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Metodo para obtener usuario y contraseña del empleado para inicio de sesion
     * @param usuario
     * @param contra
     * @return 
     */
    public static boolean ingresarUsuario(String usuario,String contra){
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        String Query = "select * from EMPLEADOS where USUARIO like ? and CONTRASENIA = ?";
        ResultSet rs = null;

        try {
             PreparedStatement stat = link.prepareStatement(Query);

            stat.setString(1, usuario);
            stat.setString(2, contra);
            
            stat.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    /**
     * Metodo para obtener el nombre de los empleados de la base de datos
     * @return true si el query se ejecuto correctamente
     */
    public static ResultSet getEmpleados(){
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        ResultSet val = null;
        String Query = "SELECT NOMBRE FROM EMPLEADOS";

        try {
            PreparedStatement stat = link.prepareStatement(Query);
            val = stat.executeQuery();

            return val;

        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return val;
        }
    }
    
    /**
     * Metodo para borrar empleados de la base de datos mediante su nombre
     * @param nombre que es el nombre del empleados
     * @return true si el query se ejecuto correctamente
     */
    public static boolean borrarEmpleado(String nombre){
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        String Query;

        Query = "DELETE FROM EMPLEADOS WHERE NOMBRE = ?";
        try {
            PreparedStatement stat = link.prepareStatement(Query);

            stat.setString(1, nombre);

            stat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

        //Compara dos String en este caso el contenido de una caja de contraseñas contra otro que debe ser igual,
        //Y evalua si la longitud esta entre 5 y 15, las rechaza si no cumple con alguna de estas condiciones.
    public static boolean validarContrasenia(String contra1, String contra2) {
        if (contra2.equals(contra1)) {
            if (contra1.length() <= 15 && contra1.length() >= 5) {
                System.out.println("Contraseña de longitud correcta");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "La contraseña debe tener una longitud de entre 5 y 15 caracteres.");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            return false;
        }
    }
    
    public static ResultSet buscarEmpleado(String x) {
        String Query;
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();

        Query = "SELECT * FROM EMPLEADOS WHERE NOMBRE LIKE ? ";

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
    
    public static boolean validarUsuario(String usr, String pass) {

        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        String Query;
        ResultSet val = null;

        Query = "SELECT * FROM EMPLEADOS WHERE USUARIO=? AND CONTRASENIA=?";

        try {
            PreparedStatement stat = link.prepareStatement(Query);

            stat.setString(1, usr);
            stat.setString(2, pass);

            val = stat.executeQuery();

            if (val.next()) {
               
                if (val.getInt("ROL_DEFINITION")==1){
                     Empleado.setIsAdmin(true);
                }
                else{
                    Empleado.setIsAdmin(false);

                }
                
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
       }
    }
    
    public static boolean isAdmin() {
        return isAdmin;
    }
    
    public static void setIsAdmin(boolean isAdmin) {
        Empleado.isAdmin = isAdmin;
    }
    
    public static void setNombreEmpleado(String user) {
        nombreUsuario = user;
    }
    
    public static String getNombreEmpleado() {
        return nombreUsuario;
    }
    
    public static void setIdEmpleado(int id) {
        IdEmpleado = id;
    }
    
    public static int getIdEmpleado() {
        return IdEmpleado;
    }
    
    public static boolean nombreUser(String user) {

        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        String Query;
        ResultSet val = null;

        Query = "SELECT NOMBRE, ID_EMPLEADO FROM EMPLEADOS WHERE USUARIO=?";

        try {
            PreparedStatement stat = link.prepareStatement(Query);

            stat.setString(1, user);
            
            val = stat.executeQuery();

            if (val.next()) {
                setNombreEmpleado(val.getString("NOMBRE"));
                setIdEmpleado(val.getInt("ID_EMPLEADO"));
                return true;
            } else {
                return false;

            }

        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
}
