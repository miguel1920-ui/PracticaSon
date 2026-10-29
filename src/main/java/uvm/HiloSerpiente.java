
package uvm;

import javax.swing.JLabel;
import java.awt.Color;

public class HiloSerpiente extends Thread {

    private JLabel[][] matriz;
    private boolean pausa = false;
    private boolean terminar = false;

    public HiloSerpiente(JLabel[][] matriz) {
        this.matriz = matriz;
    }

    public void pausar() {
        pausa = true;
    }

    public void continuar() {
        pausa = false;
    }

    public void terminar() {
        terminar = true;
        pausa = false;
        interrupt();
    }

    public void run() {

        int numero = 1;

        for (int fila = 0; fila < 15; fila++) {

            for (int paso = 0; paso < 15; paso++) {

                if (terminar) {
                    return;
                }

                while (pausa && !terminar) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        if (terminar) {
                            return;
                        }
                    }
                }

                int columna;

                if (fila % 2 == 0) {
                    columna = paso;
                } else {
                    columna = 14 - paso;
                }

                matriz[fila][columna].setText("" + numero);
                matriz[fila][columna].setForeground(Color.BLUE);

                numero++;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    if (terminar) {
                        return;
                    }
                }
            }
        }
    }
}
