import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PantallaPrincipal extends JPanel implements ActionListener {

    private JFrame ventana;
    private Image fondo;
    private DBManager db;
    private int idPartidaActual;

    private JLayeredPane capas;

    private JLabel maniqui;
    private JLabel ropaSuperior;
    private JLabel ropaInferior;
    private JLabel zapatos;

    private JButton btnSuperior1, btnSuperior2;
    private JButton btnInferior1, btnInferior2;
    private JButton btnZapatos1, btnZapatos2;
    private JButton btnMenu;

    private String escenarioActual = "Closet";
    private String nombreJugador = "";
    private String tiempoActual = "0:00";

    public PantallaPrincipal(JFrame ventana, int idPartidaActual) {
        this.ventana = ventana;
        this.idPartidaActual = idPartidaActual;
        this.db = new DBManager();

        setLayout(null);
        cargarImagenFondo("assets/fondo_closet.png");

        capas = new JLayeredPane();
        capas.setBounds(0, 0, 900, 600);
        add(capas);

        inicializarManiqui();
        inicializarCapasRopa();
        inicializarMenuLateral();
        inicializarBotonMenu();

        cargarDatosPartida();
    }

    private void cargarImagenFondo(String ruta) {
        fondo = new ImageIcon(ruta).getImage();
    }

    private void inicializarManiqui() {
        maniqui = new JLabel(new ImageIcon("assets/maniqui.png"));
        maniqui.setBounds(300, 80, 300, 500);
        capas.add(maniqui, JLayeredPane.DEFAULT_LAYER);
    }

    private void inicializarCapasRopa() {
        ropaInferior = new JLabel();
        ropaInferior.setBounds(390, 110, 300, 500);
        capas.add(ropaInferior, JLayeredPane.PALETTE_LAYER);

        ropaSuperior = new JLabel();
        ropaSuperior.setBounds(390, 35, 300, 500);
        capas.add(ropaSuperior, JLayeredPane.MODAL_LAYER);

        zapatos = new JLabel();
        zapatos.setBounds(388, 260, 300, 500);
        capas.add(zapatos, JLayeredPane.DRAG_LAYER);
    }

    private void inicializarMenuLateral() {

        btnSuperior1 = new JButton(new ImageIcon("assets/superior1.png"));
        btnSuperior1.setBounds(30, 80, 120, 120);
        btnSuperior1.setContentAreaFilled(false);
        btnSuperior1.setBorderPainted(false);
        btnSuperior1.addActionListener(this);
        capas.add(btnSuperior1, JLayeredPane.POPUP_LAYER);

        btnSuperior2 = new JButton(new ImageIcon("assets/superior2.png"));
        btnSuperior2.setBounds(160, 80, 120, 120);
        btnSuperior2.setContentAreaFilled(false);
        btnSuperior2.setBorderPainted(false);
        btnSuperior2.addActionListener(this);
        capas.add(btnSuperior2, JLayeredPane.POPUP_LAYER);

        btnInferior1 = new JButton(new ImageIcon("assets/inferior1.png"));
        btnInferior1.setBounds(30, 230, 120, 120);
        btnInferior1.setContentAreaFilled(false);
        btnInferior1.setBorderPainted(false);
        btnInferior1.addActionListener(this);
        capas.add(btnInferior1, JLayeredPane.POPUP_LAYER);

        btnInferior2 = new JButton(new ImageIcon("assets/inferior2.png"));
        btnInferior2.setBounds(160, 230, 120, 120);
        btnInferior2.setContentAreaFilled(false);
        btnInferior2.setBorderPainted(false);
        btnInferior2.addActionListener(this);
        capas.add(btnInferior2, JLayeredPane.POPUP_LAYER);

        btnZapatos1 = new JButton(new ImageIcon("assets/zapatos1.png"));
        btnZapatos1.setBounds(30, 360, 120, 120);
        btnZapatos1.setContentAreaFilled(false);
        btnZapatos1.setBorderPainted(false);
        btnZapatos1.addActionListener(this);
        capas.add(btnZapatos1, JLayeredPane.POPUP_LAYER);

        btnZapatos2 = new JButton(new ImageIcon("assets/zapatos2.png"));
        btnZapatos2.setBounds(160, 360, 120, 120);
        btnZapatos2.setContentAreaFilled(false);
        btnZapatos2.setBorderPainted(false);
        btnZapatos2.addActionListener(this);
        capas.add(btnZapatos2, JLayeredPane.POPUP_LAYER);
    }

    private void inicializarBotonMenu() {
        btnMenu = new JButton(new ImageIcon("assets/boton_menu.png"));
        btnMenu.setBounds(700, 20, 80, 40);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setBorderPainted(false);
        btnMenu.addActionListener(this);
        capas.add(btnMenu, JLayeredPane.POPUP_LAYER);
    }

    private void cargarDatosPartida() {
        Partida p = db.getPartida(idPartidaActual);
        if (p == null) return;

        nombreJugador = p.getNombre();
        tiempoActual = p.getTiempo();
        escenarioActual = p.getEscenario();

        ropaSuperior.setIcon(p.getRopaSuperior() == null ? null : new ImageIcon(p.getRopaSuperior()));
        ropaInferior.setIcon(p.getRopaInferior() == null ? null : new ImageIcon(p.getRopaInferior()));
        zapatos.setIcon(p.getZapatos() == null ? null : new ImageIcon(p.getZapatos()));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

    public void actionPerformed(ActionEvent e){

        if (e.getSource() == btnSuperior1) {
            setIconWithDescription(ropaSuperior, "assets/superior1.png");
        }
        if (e.getSource() == btnSuperior2) {
            setIconWithDescription(ropaSuperior, "assets/superior2.png");
        }

        if (e.getSource() == btnInferior1) {
            setIconWithDescription(ropaInferior, "assets/inferior1.png");
        }
        if (e.getSource() == btnInferior2) {
            setIconWithDescription(ropaInferior, "assets/inferior2.png");
        }

        if (e.getSource() == btnZapatos1) {
            setIconWithDescription(zapatos, "assets/zapatos1.png");
        }
        if (e.getSource() == btnZapatos2) {
            setIconWithDescription(zapatos, "assets/zapatos2.png");
        }

        if (e.getSource() == btnMenu) {
            mostrarMenuJuego();
        }
    }

    private void setIconWithDescription(JLabel label, String ruta){
        ImageIcon icon = new ImageIcon(ruta);
        icon.setDescription(ruta);
        label.setIcon(icon);
    }

    private void mostrarMenuJuego() {
        int opcion = JOptionPane.showOptionDialog(
                this, "¿Qué quieres hacer?",
                "Menú del Juego",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[]{"Guardar", "Salir", "Cancelar"},
                "Cancelar"
        );

        if (opcion == 0) {
            db.guardarPartida(
                idPartidaActual,
                nombreJugador,
                tiempoActual,
                escenarioActual,
                ropaSuperior.getIcon() == null ? null : ((ImageIcon)ropaSuperior.getIcon()).getDescription(),
                ropaInferior.getIcon() == null ? null : ((ImageIcon)ropaInferior.getIcon()).getDescription(),
                zapatos.getIcon() == null ? null : ((ImageIcon)zapatos.getIcon()).getDescription()
            );

            JOptionPane.showMessageDialog(this, "Partida guardada correctamente.");
        }

        if (opcion == 1) {
            ventana.setContentPane(new PantallaInicio(ventana));
            ventana.revalidate();
        }
    }
}
