/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author eopg9
 */
public class Vehiculos {

    /**
     * Metodo para agregar vehiculos a la base de datos
     *
     * @param marca
     * @param modelo
     * @param color
     * @param transmision
     * @param precio
     * @param tanque
     * @param inventario
     * @return
     */
    public static boolean addVehiculos(String noSerie, String marca, String modelo, String color,
            String transmision, float precio, float tanque) {
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        String Query = "insert into VEHICULO(NO_SERIE, MARCA, MODELO,"
                + " COLOR, TRANSMISION,"
                + " PRECIO_RENTA, TANQUE,"
                + " ESTADO)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, 1)";

        try {
            PreparedStatement stat = link.prepareStatement(Query);

            stat.setString(1, noSerie);
            stat.setString(2, marca);
            stat.setString(3, modelo);
            stat.setString(4, color);
            stat.setString(5, transmision);
            stat.setFloat(6, precio);
            stat.setFloat(7, tanque);
            stat.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Vehiculos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Metodo para obtener todos los datos del vehiculo de la base de datos
     *
     * @return
     */
    public static ResultSet getVehiculos(int i) {
        ResultSet val = null;
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        String Query;

        Query = "SELECT * FROM VEHICULO WHERE ID_VEHICULO = ? AND ESTADO = 1";

        try {
            PreparedStatement stat = link.prepareStatement(Query);
            stat.setInt(1, i);
            val = stat.executeQuery();

            return val;

        } catch (SQLException ex) {
            Logger.getLogger(Vehiculos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Metodo para modificar todos los campos del vehiculo de la base de datos
     * @param marca
     * @param modelo
     * @param color
     * @param transmision
     * @param precio
     * @param tanque
     * @param inventario
     * @param codVehiculo
     * @return 
     */
    public static boolean modificarVehiculos(String marca, String modelo, String color,
            String transmision, Float precio, Float tanque, String estado, int codVehiculo) {
    sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        String Query;

        Query = "UPDATE VEHICULO SET MARCA=?, MODELO=?, COLOR=?, TRANSMISION=?, "
                + "PRECIO=?, TANQUE=?, ESTADO=? WHERE ID_VEHICULO=?";
        try {
            PreparedStatement stat = link.prepareStatement(Query);

            stat.setString(1, marca);
            stat.setString(2, modelo);
            stat.setString(3, color);
            stat.setString(4, transmision);
            stat.setDouble(5, precio);
            stat.setFloat(6, tanque);
            stat.setString(7, estado);
            stat.setInt(9, codVehiculo);
            stat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Metodo para borrar un vehiculo de la base de datos mediante su id.
     * @param idVehiculo
     * @return 
     */
    public static boolean borrarVehiculo(int idVehiculo){
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        String Query;
        
        Query = "DELETE FROM FACTURAS WHERE ID_VEHICULO = ?";
       
        try {
            PreparedStatement stat = link.prepareStatement(Query);

            stat.setInt(1, idVehiculo);

            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }

        Query = "DELETE FROM VEHICULO WHERE ID_VEHICULO= ?";
        try {
            PreparedStatement stat = link.prepareStatement(Query);

            stat.setInt(1, idVehiculo);

            stat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
     
    
    public static ResultSet buscarVehiculo(String marca) {
        String Query;
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();

        Query = "SELECT * FROM VEHICULO WHERE MARCA LIKE ? AND ESTADO = 1";

        try {
            PreparedStatement stat = link.prepareStatement(Query);
            stat.setString(1, marca + "%");
            ResultSet busqueda = stat.executeQuery();
            return busqueda;
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static String codigo(String marca, String modelo, String color, float precio ) {
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        String Query;
        String Codigo;

        Query = "SELECT ID_VEHICULO FROM VEHICULO WHERE MARCA = ? AND MODELO = ?"
                + "AND COLOR = ? AND PRECIO_RENTA = ? ";

        try {
            PreparedStatement stat = link.prepareStatement(Query);
            stat.setString(1, marca);
            stat.setString(2, modelo);
            stat.setString(3, color);
            stat.setFloat(4, precio);
            ResultSet codigo = stat.executeQuery();
            if (codigo.next()) {
                Codigo = codigo.getString("ID_VEHICULO");
                
                return Codigo;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Vehiculos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     /**
     * Metodo cambiar el estado de un vehiculo de la base de datos mediante su id.
     * @param idVehiculo 
     */
    public static boolean rentarVehiculo(int idVehiculo)
    {
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        String Query;

        Query = "UPDATE VEHICULO SET ESTADO=0 WHERE ID_VEHICULO=?";
        try {
            PreparedStatement stat = link.prepareStatement(Query);

            stat.setInt(1, idVehiculo);
            

            stat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean devolverVehiculo(int idVehiculo)
    {
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();
        String Query;

        Query = "UPDATE VEHICULO SET ESTADO=1 WHERE ID_VEHICULO=?";
        try {
            PreparedStatement stat = link.prepareStatement(Query);

            stat.setInt(1, idVehiculo);
            

            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Query = "UPDATE FACTURAS SET ESTADO=1 WHERE ID_VEHICULO=?";
        try {
            PreparedStatement stat = link.prepareStatement(Query);

            stat.setInt(1, idVehiculo);
            

            stat.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

  public static ResultSet buscarVehiculoRentado(String marca) {
        String Query;
        sql.Conexion mysql = new sql.Conexion();
        Connection link = mysql.Conectar();

        Query = "select empleados.NOMBRE, vehiculo.ID_VEHICULO, vehiculo.PRECIO_RENTA, facturas.FECHA, vehiculo.NO_SERIE, facturas.FECHA_ENTREGA, clientes.nombre from facturas\n" +
"inner join empleados on empleados.ID_EMPLEADO = facturas.ID_EMPLEADO\n" +
"inner join vehiculo on vehiculo.ID_VEHICULO = facturas.ID_VEHICULO\n" +
"inner join clientes on clientes.ID_CLIENTE = facturas.ID_CLIENTE WHERE clientes.nombre like ? AND facturas.ESTADO = 0  ";

        try {
            PreparedStatement stat = link.prepareStatement(Query);
            stat.setString(1, marca + "%");
            ResultSet busqueda = stat.executeQuery();
            return busqueda;
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
