import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Temperatura extends JFrame implements ActionListener{
    JTextField[] campos;
    JButton boton;
    PanelGrafico panel;
    public Temperatura() {
        super("Alarcon, Montalvo");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel ingreso=new JPanel(new GridLayout(2, 7, 5, 5));
        String[] dias={"Lun", "Mar", "Mié", "Jue", "Vie", "Sab", "Dom"};

        campos=new JTextField[7];
        for(int i=0; i < 7; i++){
            ingreso.add(new JLabel(dias[i], SwingConstants.CENTER));
        }
        for(int i=0; i < 7; i++){
            campos[i] = new JTextField();
            ingreso.add(campos[i]);
        }

        add(ingreso, BorderLayout.NORTH);
        panel =new PanelGrafico();
        add(panel, BorderLayout.CENTER);
        boton=new JButton("Mostrar");
        boton.addActionListener(this);
        add(boton, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        try{
            int[] temps=new int[7];
            for (int i=0; i < 7; i++){
                temps[i]=Integer.parseInt(campos[i].getText());
            }
            panel.actualizarTemperaturas(temps);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this,"Error","Alarcon, Montalvo",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    class PanelGrafico extends JPanel{
        int[] temperaturas={0, 0, 0, 0, 0, 0, 0};
        public void actualizarTemperaturas(int[] t){
            temperaturas = t;
            repaint();
        }
        protected void paintComponent(Graphics g){
            super.paintComponent(g);

            Graphics2D g2=(Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            int ancho=getWidth();
            int alto=getHeight();
            int margen=40;
            int espacioX=(ancho - 2 *margen)/ 6;
            int maxTemp = 0;
            for (int t : temperaturas) if (t > maxTemp) maxTemp = t;
            if (maxTemp == 0) maxTemp = 1;
            int ejeY = alto - margen;

            g2.drawLine(margen, margen, margen, ejeY);
            g2.drawLine(margen, ejeY, ancho-margen, ejeY);
            int[] x=new int[7];
            int[] y=new int[7];
            for(int i = 0; i < 7; i++){
                x[i]=margen+i*espacioX;
                y[i]=ejeY-(temperaturas[i]*(alto -2*margen)/maxTemp);
            }
            g2.setColor(Color.RED);
            for (int i = 0; i < 6; i++){
                g2.drawLine(x[i], y[i], x[i+1], y[i+1]);
            }
            for (int i = 0; i < 7; i++) {
                g2.fillOval(x[i]-4, y[i]-4, 8, 8);
            }
        }
    }
    public static void main(String[] args){
        new Temperatura();
    }
}