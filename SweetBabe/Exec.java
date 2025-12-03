import javax.swing.*;

public class Exec{
    public static void main(String[]args){
        JFrame ventana= new JFrame("Sweet Babe");
        ventana.setSize(900,700);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        ventana.setContentPane(new PantallaInicio(ventana));
        ventana.setVisible(true);
    }
}