package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;

/**
 *
 * @author Tux
 */
public class val {

    public static final String EFECTIVO = "[0-9]+\\.[0-9]{2}";

    static void efectivo(final JTextField x) {
        x.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent a) {
                int typ = (int) a.getKeyChar();
                if ((typ == 46) || (typ >= 48 && typ <= 57)) {
                    if (typ == 46) {
                        String dat = x.getText();
                        if (dat.contains(".")) {
                            a.consume();

                        }
                    }
                } else {
                    a.consume();

                }
                
            }

        });
    }
}