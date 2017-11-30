/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dani
 */
public class DevolucionVehiculo extends javax.swing.JFrame {

    static int count = 0;
    static float MultaTotal;

    /**
     * Creates new form BuscarVehiculo
     */
    public DevolucionVehiculo() {
        initComponents();
        listInfo("");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        txtBuscar.requestFocus();
        this.setLocationRelativeTo(null);
        this.setTitle("Vehiculos rentados");
        this.getContentPane().setBackground(Color.getColor("#8e8ed1"));
    }

    /**
     * Metodo para enlistar los vehiculos, faltan las busquedas sql
     */
    public static void listInfo(String x) {
        int y;
        DefaultTableModel modelo1 = (DefaultTableModel) jTable1.getModel();
        for (y = jTable1.getRowCount() - 1; y >= 0; y--) {
            modelo1.removeRow(y);
        }
        ResultSet data = sql.Vehiculos.buscarVehiculoRentado(x);
        try {
            while (data.next()) {
                int codigo = data.getInt("VEHICULO.ID_VEHICULO");
                String nombre = data.getString("CLIENTES.NOMBRE");
                String fecha = data.getString("FACTURAS.FECHA");
                String fechaEntrega = data.getString("FACTURAS.FECHA_ENTREGA");
                String noSerie = data.getString("VEHICULO.NO_SERIE");
                float PrecioRenta = data.getFloat("VEHICULO.PRECIO_RENTA");
                //Convertir el String fechaEntrega a Date.
                SimpleDateFormat formateador = new SimpleDateFormat("yy-MM-dd");
                Date FechaEntregaDate = formateador.parse(fechaEntrega);
                Date Hoy = new Date();
                int Diferencia = Fechas.diferenciasEntreFechas(FechaEntregaDate, Hoy);
                MultaTotal = 0;
                float Porcentaje = (float) 1.5;
                if (Diferencia > 0) {
                    MultaTotal = Porcentaje * (PrecioRenta * Diferencia);
                } else {
                    MultaTotal = 0;
                }
                //float multa = data.getFloat("MULTA");
                modelo1.addRow(new Object[]{codigo, nombre, fecha, fechaEntrega, MultaTotal, noSerie});
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmBorrarVehiculos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DevolucionVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Nombre = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Nombre.setText("Marca del Vehiculo:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Vehiculo", "Nombre del Cliente", "Fecha de Renta", "Fecha de Entrega", "Multa", "No. Serie"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Nombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                            .addComponent(txtBuscar))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Nombre))
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        count++;
        //Evalua si el renglon de la tabla es clickeado dos veces.
        if (count == 2) {
            int row = jTable1.getSelectedRow();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            //Toma el valor del String en la posicion 1 del renglon, es decir el nombre y obtiene el Id 
            //con el metodo obtenerCodigo que esta en la clase Inicio.
            int id = (Integer) modelo.getValueAt(row, 0);
            float multa = (Float) modelo.getValueAt(row, 4);
            if (multa > 0.0) {
                CobrarMultas cobrar = new CobrarMultas();
                cobrar.setVisible(true);
                cobrar.setLocationRelativeTo(this);
            } else {
                if (sql.Vehiculos.devolverVehiculo(id)) {
                    JOptionPane.showMessageDialog(this, "Vehiculo Devuelto.");
                    listInfo("");
                    txtBuscar.setText(null);
                    txtBuscar.requestFocus();
                    count=0;
                } else {
                    JOptionPane.showMessageDialog(this, "Error al devolver vehiculo.");
                }
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed

    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        String texto = txtBuscar.getText();
        listInfo(texto);
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped

    }//GEN-LAST:event_txtBuscarKeyTyped

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int row = jTable1.getSelectedRow();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            //Toma el valor del String en la posicion 1 del renglon, es decir el nombre y obtiene el Id 
            //con el metodo obtenerCodigo que esta en la clase Inicio.
            int id = (Integer) modelo.getValueAt(row, 0);
            float multa = (Float) modelo.getValueAt(row, 4);
            if (multa > 0.0) {
                CobrarMultas cobrar = new CobrarMultas();
                cobrar.setVisible(true);
                cobrar.setLocationRelativeTo(this);
            } else {
                if (sql.Vehiculos.devolverVehiculo(id)) {
                    JOptionPane.showMessageDialog(this, "Vehiculo Devuelto.");
                    listInfo("");
                    txtBuscar.setText(null);
                    txtBuscar.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al devolver vehiculo.");
                }
            }
        }
    }//GEN-LAST:event_jTable1KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DevolucionVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DevolucionVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DevolucionVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DevolucionVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DevolucionVehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Nombre;
    private javax.swing.JButton jButton7;
    private javax.swing.JScrollPane jScrollPane1;
    static javax.swing.JTable jTable1;
    public static javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
