import javax.swing.*;
import java.awt.*;
public class PantallaCargar extends JPanel {

    private JFrame ventana;
    private Image fondo;
    private DBManager db;

    public PantallaCargar(JFrame ventana) {
        this.ventana = ventana;
        setLayout(null);
        db = new DBManager();
        fondo = new ImageIcon("assets/fondo_juego.png").getImage();
        colocarTitulo();
        colocarSlots();
        colocarBotonRegresar();
    }

    private void colocarTitulo() {
        JLabel titulo = new JLabel(new ImageIcon("assets/titulo_cargar.png"));
        titulo.setBounds(260, 40, 500, 120);
        add(titulo);
    }

    private void colocarSlots() {

        Partida p1 = db.getPartida(1);
        Partida p2 = db.getPartida(2);
        Partida p3 = db.getPartida(3);

        SlotPartida s1 = new SlotPartida(p1);
        SlotPartida s2 = new SlotPartida(p2);
        SlotPartida s3 = new SlotPartida(p3);
        s1.setBounds(250, 200, 600, 140);
        s2.setBounds(250, 360, 600, 140);
        s3.setBounds(250, 520, 600, 140);
        s1.addActionListener(new CargarAccion(1));
        s2.addActionListener(new CargarAccion(2));
        s3.addActionListener(new CargarAccion(3));
        add(s1);
        add(s2);
        add(s3);
    }

    private class CargarAccion implements java.awt.event.ActionListener {
        private int id;

        public CargarAccion(int id) {
            this.id = id;
        }

        public void actionPerformed(java.awt.event.ActionEvent e) {
            System.out.println("Cargar partida: " + id);
            ventana.setContentPane(new PantallaPrincipal(ventana));
            ventana.revalidate();
        }
    }

    private void colocarBotonRegresar() {
        JButton b = new JButton(new ImageIcon("assets/boton_regresar.png"));
        b.setRolloverIcon(new ImageIcon("assets/boton_regresar_hover.png"));

        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setBounds(20, 20, 150, 60);
        b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                ventana.setContentPane(new PantallaInicio(ventana));
                ventana.revalidate();
            }
        });

        add(b);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}