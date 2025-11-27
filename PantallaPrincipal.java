import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PantallaPrincipal extends JPanel implements ActionListener {
    private JFrame ventana;

    private Image fondo;

    private JLabel maniqui;
    private JLabel ropaSuperior;
    private JLabel ropaInferior;
    private JLabel zapatos;

    private JButton btnSuperior1;
    private JButton btnInferior1;
    private JButton btnZapatos1;
    private JButton btnMenu;

    public PantallaPrincipal(JFrame ventana) {
        this.ventana = ventana;

        setLayout(null);
        cargarImagenFondo("assets/fondo_juego.png");

        inicializarManiqui();
        inicializarRopa();
        inicializarMenuLateral();
        inicializarBotonMenu();
    }

    private void cargarImagenFondo(String ruta) {
        fondo = new ImageIcon(ruta).getImage();
    }

    private void inicializarManiqui() {
        maniqui = new JLabel(new ImageIcon("assets/maniqui.png"));
        maniqui.setBounds(300, 80, 300, 500);
        add(maniqui);
    }

    private void inicializarRopa() {
        ropaSuperior = new JLabel();
        ropaSuperior.setBounds(300, 80, 300, 500);
        add(ropaSuperior);

        ropaInferior = new JLabel();
        ropaInferior.setBounds(300, 80, 300, 500);
        add(ropaInferior);

        zapatos = new JLabel();
        zapatos.setBounds(300, 80, 300, 500);
        add(zapatos);
    }

    private void inicializarMenuLateral() {

        JLabel titulo = new JLabel("Ropa");
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(30, 20, 200, 30);
        add(titulo);

        btnSuperior1 = new JButton(new ImageIcon("assets/superior1.png"));
        btnSuperior1.setBounds(30, 80, 120, 120);
        btnSuperior1.setContentAreaFilled(false);
        btnSuperior1.setBorderPainted(false);
        btnSuperior1.addActionListener(this);
        add(btnSuperior1);

        btnInferior1 = new JButton(new ImageIcon("assets/inferior1.png"));
        btnInferior1.setBounds(30, 220, 120, 120);
        btnInferior1.setContentAreaFilled(false);
        btnInferior1.setBorderPainted(false);
        btnInferior1.addActionListener(this);
        add(btnInferior1);

        btnZapatos1 = new JButton(new ImageIcon("assets/zapatos1.png"));
        btnZapatos1.setBounds(30, 360, 120, 120);
        btnZapatos1.setContentAreaFilled(false);
        btnZapatos1.setBorderPainted(false);
        btnZapatos1.addActionListener(this);
        add(btnZapatos1);
    }

    private void inicializarBotonMenu() {
        btnMenu = new JButton(new ImageIcon("assets/boton_menu.png"));
        btnMenu.setBounds(700, 20, 80, 40);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setBorderPainted(false);
        btnMenu.addActionListener(this);
        add(btnMenu);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSuperior1) {
            ropaSuperior.setIcon(new ImageIcon("assets/superior1.png"));
        }

        if (e.getSource() == btnInferior1) {
            ropaInferior.setIcon(new ImageIcon("assets/inferior1.png"));
        }

        if (e.getSource() == btnZapatos1) {
            zapatos.setIcon(new ImageIcon("assets/zapatos1.png"));
        }

        if (e.getSource() == btnMenu) {
            mostrarMenuJuego();
        }
    }

    private void mostrarMenuJuego() {
        int opcion = JOptionPane.showOptionDialog(
                this,
                "¿Qué deseas hacer?",
                "Menú del Juego",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[]{"Guardar", "Salir al Inicio", "Cancelar"},
                "Cancelar"
        );

        if (opcion == 0) {
            JOptionPane.showMessageDialog(this, "Guardando partida... (luego conectar a SQLite)");
        }

        if (opcion == 1) {
            ventana.setContentPane(new PantallaInicio(ventana));
            ventana.revalidate();
        }
    }
}