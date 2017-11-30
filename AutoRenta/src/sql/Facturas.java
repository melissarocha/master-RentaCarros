
package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Facturas {
    
    /**
     * Metodo para agregar Rentas a la base de datos.
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
    
    public static boolean RegistrarRenta(int idEmpleado, int idCliente, int idVehiculo,
                                      String fechaRenta, String fechaEntrega){
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        String Query = "insert into FACTURAS(ID_EMPLEADO, ID_CLIENTE, ID_VEHICULO,"
                        + " FECHA, FECHA_ENTREGA, ESTADO)"
                        + " VALUES(?, ?, ?, ?, ?, 0)";
        try {

            PreparedStatement stat = link.prepareStatement(Query);

            stat.setInt(1, idEmpleado);
            stat.setInt(2, idCliente);
            stat.setInt(3, idVehiculo);
            stat.setString(4, fechaRenta);
            stat.setString(5, fechaEntrega);
            stat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Facturas.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
}
