import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PantallaCargar extends JPanel {

    private JFrame ventana;
    private Image fondo;
    private DBManager db;

    public PantallaCargar(JFrame ventana) {
        this.ventana = ventana;
        setLayout(null);

        fondo = new ImageIcon("assets/fondo_juego.png").getImage();
        db = new DBManager();

        colocarTitulo();
        colocarSlots();
        colocarBotonVolver();
    }

    private void colocarTitulo() {
        JLabel titulo = new JLabel(new ImageIcon("assets/titulo_cargar.png"));
        titulo.setBounds(250, 30, 500, 100);
        add(titulo);
    }

    private void colocarSlots() {
        Partida p1 = db.getPartida(1);
        Partida p2 = db.getPartida(2);
        Partida p3 = db.getPartida(3);

        SlotPartida slot1 = new SlotPartida(p1);
        slot1.setBounds(200, 180, 600, 140);
        add(slot1);
        SlotPartida slot2 = new SlotPartida(p2);
        slot2.setBounds(200, 340, 600, 140);
        add(slot2);
        SlotPartida slot3 = new SlotPartida(p3);
        slot3.setBounds(200, 500, 600, 140);
        add(slot3);

        slot1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarPartida(1);
            }
        });
        slot2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarPartida(2);
            }
        });
        slot3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarPartida(3);
            }
        });
    }

    private void cargarPartida(int idSlot) {
        Partida p = db.getPartida(idSlot);
        if (p == null) {
            JOptionPane.showMessageDialog(null, "No hay partida guardada");
            return;
        }
        ventana.setContentPane(new PantallaPrincipal(ventana, idSlot));
        ventana.revalidate();
    }

    private void colocarBotonVolver() {
        JButton volver = new JButton(new ImageIcon("assets/boton_regresar.png"));
        volver.setRolloverIcon(new ImageIcon("assets/boton_regresar_hover.png"));
        volver.setBorderPainted(false);
        volver.setContentAreaFilled(false);
        volver.setFocusPainted(false);
        volver.setBounds(20, 20, 150, 60);

        volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.setContentPane(new PantallaInicio(ventana));
                ventana.revalidate();
            }
        });
        add(volver);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}
