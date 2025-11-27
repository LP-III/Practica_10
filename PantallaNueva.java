import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PantallaNueva extends JPanel {
    private JFrame ventana;
    private Image fondo;
    private DBManager db;
    private JTextField campoNombre;
    private JComboBox<String> comboEscenario;
    public PantallaNueva(JFrame ventana){
        this.ventana = ventana;
        setLayout(null);
        fondo = new ImageIcon("assets/fondo_juego.png").getImage();
        db = new DBManager();

        colocarTitulo();
        colocarFormulario();
        colocarBotonCrear();
        colocarBotonVolver();
    }

    private void colocarTitulo() {
        JLabel titulo = new JLabel(new ImageIcon("assets/titulo_nueva_partida.png"));
        titulo.setBounds(250, 40, 500, 100);
        add(titulo);
    }
    private void colocarFormulario() {
        JLabel txtNombre = new JLabel(new ImageIcon(
                TextoImagen.crearTexto("Nombre del jugador:", 30)
        ));
        txtNombre.setBounds(260, 180, 400, 40);
        add(txtNombre);
        campoNombre = new JTextField();
        campoNombre.setBounds(260, 230, 400, 35);
        campoNombre.setFont(new Font("Arial", Font.PLAIN, 20));
        add(campoNombre);
        JLabel txtEscenario = new JLabel(new ImageIcon(
                TextoImagen.crearTexto("Escenario:", 30)
        ));
        txtEscenario.setBounds(260, 300, 400, 40);
        add(txtEscenario);
        comboEscenario = new JComboBox<String>();
        comboEscenario.addItem("opc1");
        comboEscenario.addItem("opc2");
        comboEscenario.addItem("opc3");
        comboEscenario.setBounds(260, 350, 250, 35);
        add(comboEscenario);
    }

    private void colocarBotonCrear() {
        JButton crear = new JButton(new ImageIcon("assets/boton_crear.png"));
        crear.setRolloverIcon(new ImageIcon("assets/boton_crear_hover.png"));
        crear.setBorderPainted(false);
        crear.setContentAreaFilled(false);
        crear.setBounds(330, 450, 200, 60);
        crear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                crearPartida();
            }
        });

        add(crear);
    }

    private void colocarBotonVolver(){
        JButton volver = new JButton(new ImageIcon("assets/boton_regresar.png"));
        volver.setRolloverIcon(new ImageIcon("assets/boton_regresar_hover.png"));
        volver.setBorderPainted(false);
        volver.setContentAreaFilled(false);
        volver.setBounds(20, 20, 150, 60);
        volver.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ventana.setContentPane(new PantallaInicio(ventana));
                ventana.revalidate();
            }
        });

        add(volver);
    }

    private void crearPartida() {
        String nombre=campoNombre.getText();
        String escenario=comboEscenario.getSelectedItem().toString();

        if (nombre.trim().equals("")){
            JOptionPane.showMessageDialog(this, "Escribe un nombre.");
            return;
        }
        int slotLibre = buscarPrimerSlot();

        if(slotLibre==-1){
            JOptionPane.showMessageDialog(this, "Todos los slots están llenos.","alerta",  JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        db.guardarPartida(slotLibre, nombre, "0:00", escenario);
        ventana.setContentPane(new PantallaPrincipal(ventana));
        ventana.revalidate();
    }

    private int buscarPrimerSlot() {
        for (int i = 1; i <= 3; i++) {
            if (db.getPartida(i) == null) {
                return i;
            }
        }
        return -1;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}