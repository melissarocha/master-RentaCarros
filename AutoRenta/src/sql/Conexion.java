package sql;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Clase que crea las conexiones a utilizar en la base de datos y los demas
 * metodos del sistema.
 *
 * @author Daniela Santillanes Castro
 * @since 06/05/2015
 * @version 1.0
 */
public class Conexion {

    //Variables SQL
    static Connection cn;
    static Statement st;
    static ResultSet rs;
    
    //Variables de Conexion
    public String db = "RENTA"; //Nombre base de datos a usar
    public String url = "jdbc:mysql://localhost/" + db;
    public String user = "root";
    public String pass = ""; //Contraseña para conexion

    /**
     * Método para realizar la conexión a la base de datos.
     * @return conexion.
     */
    
    public Connection Conectar() {


        cn = null;

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            cn = DriverManager.getConnection(this.url, this.user, this.pass);
            System.out.println("Conexion Exitosa con Base de Datos");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return cn;
    }


}
