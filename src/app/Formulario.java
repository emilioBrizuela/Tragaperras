package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Formulario extends JFrame implements ActionListener {

    private JLabel txt1;
    private JLabel txt2;
    private JLabel txt3;
    private JLabel resultado;
    private JLabel monedasTotales;
    private double monedas = 10;
    private String[] rutaImagen = { "img/img1.jpg", "img/img2.jpg", "img/img3.jpg", "img/img4.jpg", "img/img5.jpg",
            "img/img6.jpg" };

    // RELOJ
    private JPanel panelReloj;
    private Timer temporizador;
    private JLabel hora;
    private int h;
    private int m;
    private int s;

    // BOTON
    private JButton btn;
    private int tbtn = 1;
    private JButton btnCobrar;

    public Formulario() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setTitle("Superminitragraperras");
        setLayout(null);
        setLocationRelativeTo(null);

        iniciarComponentes();

    }

    /**
     * INICIALIZA LOS OBJETOS A MOSTRAR EN LA VENTANA
     */
    private void iniciarComponentes() {

        panelReloj = new JPanel();
        panelReloj.setBounds(0, 0, 100, 40);
        panelReloj.setLayout(null);
        panelReloj.setVisible(true);
        add(panelReloj);
        cargarPanelReloj();

        txt1 = new JLabel();
        txt1.setBounds(50, 60, 150, 150);
        txt1.setIcon(imagenTamaño(txt1, rutaImagen[5]));
        add(txt1);

        txt2 = new JLabel();
        txt2.setBounds(220, 60, 150, 150);
        txt2.setIcon(imagenTamaño(txt2, rutaImagen[5]));
        add(txt2);

        txt3 = new JLabel();
        txt3.setBounds(390, 60, 150, 150);
        txt3.setIcon(imagenTamaño(txt3, rutaImagen[5]));
        add(txt3);

        btn = new JButton();
        btn.setText("Nuevo Juego");
        btn.setBounds(235, 220, 120, 30);
        add(btn);
        colorBoton();
        btn.addActionListener(this);

        resultado = new JLabel("---");
        resultado.setBounds(250, 260, 90, 30);
        add(resultado);

        monedasTotales = new JLabel(String.format("€ %.2f", monedas));
        monedasTotales.setBounds(50, 300, 90, 30);
        add(monedasTotales);

        btnCobrar = new JButton("Sacar Dinero");
        btnCobrar.setBounds(400, 300, 120, 30);
        btnCobrar.addActionListener(this);
        add(btnCobrar);

    }

    /**
     * GENERA LAS IMAGENES A MOSTRAR EN LAS ETIQUETAS Y EL RESULTADO.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method
        if (tbtn == 0 || monedas == 10) {
            btn.setText("Girar");
        }
        tbtn = 0;
        if (e.getSource() == btnCobrar) {
            finJuego(true);
        }
        if (e.getSource() ==  btn) {
            if(monedas >=0 && monedas < 1){
                JOptionPane.showMessageDialog(null, "No tienes suficiente dinero");
                finJuego(false);
            }
            monedas--;

            txt1.setIcon(imagenTamaño(txt1));
            txt2.setIcon(imagenTamaño(txt2));
            txt3.setIcon(imagenTamaño(txt3));
            // System.err.println("1:" + txt1.getName());
            // System.err.println("1:" + txt2.getName());
            // System.err.println("1:" + txt3.getName());
            if(monedas <1){
            }

            resultadoGanador();
        }
    }

    /**
     * MUESTRA POR PANTALLA EL RESULTADO DE LA IGUALDAD DE LAS IMAGENES SI LAS 3 SON
     * IGUALES AGREGA 5 MONEDAS <I><B>TEXTO VERDE Y PARPADEA EL BOTON</I><B>; SI HAY
     * 2 IGUALES AGREGA 1.5 MONEDAS <I><B>TEXTO AZUL</I></B>. SI TODAS SON
     * DIFERENTES NO HACE NADA
     */
    private void resultadoGanador() {
        if (txt1.getName() == txt2.getName() && txt1.getName() == txt3.getName()) {
            resultado.setForeground(Color.GREEN);
            tbtn = 1;
            resultado.setText("Has ganado!!!");
            monedas += 5;
        } else if ((txt1.getName() == txt2.getName()) || (txt1.getName() == txt3.getName())
                || (txt3.getName() == txt2.getName())) {
            resultado.setForeground(Color.BLUE);
            resultado.setText("Dos aciertos, BIEN!");
            monedasTotales.setText(String.format("€ %.2f", monedas));
            monedas += 1.5;
            monedasTotales.setText(String.format("€ %.2f", monedas));
        } else {
            resultado.setForeground(Color.BLACK);
            resultado.setText("NADA");
            monedasTotales.setText(String.format("€ %.2f", monedas));
        }
    }

    /***
     * Crea la imagen y genera el tamaño para agregar a la etiqueta.
     * 
     * @param txt etiqueta
     * @return imagen
     */
    private Icon imagenTamaño(JLabel txt) {
        String imgRandom = rutaImagen[(int) (Math.random() * 6)];
        ImageIcon img = new ImageIcon(imgRandom);
        txt.setName(imgRandom);
        Icon icon = new ImageIcon(
                img.getImage().getScaledInstance(txt.getWidth(), txt.getHeight(), Image.SCALE_DEFAULT));
        repaint();
        // System.out.println(txt.getName());
        return icon;
    }

    /***
     * Crea la imagen y genera el tamaño para agregar a la etiqueta.
     * 
     * @param imgRuta ruta de la imagen
     * @param txt     etiqueta
     * @return imagen
     */
    private Icon imagenTamaño(JLabel txt, String imgRuta) {
        ImageIcon img = new ImageIcon(imgRuta);
        Icon icon = new ImageIcon(
                img.getImage().getScaledInstance(txt.getWidth(), txt.getHeight(), Image.SCALE_DEFAULT));
        repaint();
        return icon;
    }

    // #region switch no utilizado
    /**
     * Da un nombre a la etiqueta y selecciona una imagen aleatoria.
     * 
     * @param txt etiqueta
     * @return
     */
    // public String imagen(JLabel txt) {
    // int key = (int) (Math.random() * 6) + 1;
    // //System.err.println(key);
    // switch (key) {
    // case 1:
    // txt.setName("img1");
    // return "img/img1.jpg";
    // case 2:
    // txt.setName("img2");
    // return "img/img2.jpg";
    // case 3:
    // txt.setName("img3");
    // return "img/img3.jpg";
    // case 4:
    // txt.setName("img4");
    // return "img/img4.jpg";
    // case 5:
    // txt.setName("img5");
    // return "img/img5.jpg";
    // default:
    // txt.setName("img6");
    // return "img/img6.jpg";
    // }

    // }
    // #endregion switch

    /**
     * Muestra un mensaje si desea volver a jugar. Si desea volver a jugar se le
     * suman 10 a las monedas que ya tenía sino muestra cuanto dinero tiene.
     * 
     * @param retiro true: retiró el dinero | false: no tiene fondos.
     * 
     */
    private void finJuego(boolean retiro) {
        tbtn = 1;
        if (retiro) {
            cuantasMonedas(0, true);
        } else {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea volver a jugar?", "Volver a jugar",
                    JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:
                    cuantasMonedas(10, false);
                    tbtn = 0;
                    btn.setText("Girar");
                    break;
                case JOptionPane.NO_OPTION:
                    cuantasMonedas(0, true);
                    break;
                case JOptionPane.CLOSED_OPTION:
                    JOptionPane.showMessageDialog(null, "No has elegido nada");
            }
        }
    }

    /**
     * Muestra la monedas que tiene. Agrega o quita monedas.
     * 
     * @param n       monedas
     * @param retirar true: retira todo el dinero. | false: inicia una nueva
     *                partida.
     */
    private void cuantasMonedas(int n, boolean retirar) {
        btn.setText("Nuevo Juego");
        btn.setSize(btn.getPreferredSize());
        if (retirar) {
            JOptionPane.showMessageDialog(null, "Tu dinero es: " + monedas);
            monedas = n;
        } else {
            monedas += n;
        }
        monedasTotales.setText(String.format("€ %.2f", monedas));
    }

    /**
     * Genera los colores del botón girar. Esperando ser puslado.
     */
    private void colorBoton() {
        Timer t = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (tbtn >= 1) {
                    btn.setForeground(Color.white);
                    tbtn++;
                    if (tbtn % 2 == 0) {
                        btn.setBackground(Color.red);
                    } else {
                        btn.setBackground(Color.green);
                    }
                } else {
                    btn.setForeground(Color.black);
                    btn.setBackground(Color.lightGray);
                }
            }

        });
        t.start();
    }

    /**
     * CARGA LOS VALORES QUE DEBE TENER EL RELOJ
     */
    private void cargarPanelReloj() {
        hora = new JLabel("");
        hora.setBounds(40, 0, 100, 50);
        panelReloj.add(hora);
        s = 0;
        h = 0;
        m = 0;
        temporizador = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // s++;
                hora.setText(String.format("%02d:%02d:%02d", h, m, s++));
                if (s > 59) {
                    s = 0;
                    m++;
                }
                if (m > 59) {
                    m = 0;
                    h++;
                }
            }
        });
        temporizador.start();
    }
}