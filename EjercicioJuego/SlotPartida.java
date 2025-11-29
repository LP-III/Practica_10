import javax.swing.*;
public class SlotPartida extends JButton {
    public SlotPartida(Partida p) {
        setLayout(null);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);

        setIcon(new ImageIcon("assets/slot_base.png"));
        setRolloverIcon(new ImageIcon("assets/slot_base_hover.png"));
        setSize(600, 140);
        if (p != null) {
            JLabel nombreImg = new JLabel(new ImageIcon("assets/slot_nombre.png"));
            nombreImg.setBounds(40, 20, 400, 40);
            add(nombreImg);

            JLabel tiempoImg = new JLabel(new ImageIcon("assets/slot_tiempo.png"));
            tiempoImg.setBounds(40, 65, 300, 40);
            add(tiempoImg);

            JLabel escenarioImg = new JLabel(new ImageIcon("assets/slot_escenario.png"));
            escenarioImg.setBounds(260, 65, 300, 40);
            add(escenarioImg);

            JLabel txtNombre = new JLabel(p.getNombre());
            txtNombre.setBounds(140, 20, 300, 40);
            add(txtNombre);

            JLabel txtTiempo = new JLabel(p.getTiempo());
            txtTiempo.setBounds(140, 65, 150, 40);
            add(txtTiempo);

            JLabel txtEscenario = new JLabel(p.getEscenario());
            txtEscenario.setBounds(370, 65, 150, 40);
            add(txtEscenario);
        }else{
            JLabel vacio = new JLabel(new ImageIcon("assets/slot_vacio.png"));
            vacio.setBounds(230, 45, 200, 40);
            add(vacio);
        }
    }
}