package gui;

/**
 * Clase principal, se utiliza como lanzador para el programa principal
 * incializar aqui los componentes del programa
 *
 * @author Daniela Santillanes Castro
 */
public class Launcher {

    public static void main(String[] args) {
        FrmLogin frmLogin = new FrmLogin();
        frmLogin.setLocationRelativeTo(frmLogin);
        frmLogin.setVisible(true);

    }

}
