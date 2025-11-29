import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PantallaInicio extends JPanel{
    private Image fondo;
    private JFrame ventana;

    public PantallaInicio(JFrame ventana){
        this.ventana = ventana;
        setLayout(null);
        cargarImagenFondo("assets/fondo_juego.png");
        inicializarBotones();
        inicializarLogo();
    }
    private void cargarImagenFondo(String ruta) {
        fondo = new ImageIcon(ruta).getImage();
    }
    private void inicializarLogo(){
        JLabel logo = new JLabel(new ImageIcon("assets/logo.png"));
        logo.setBounds(280, 50, 400, 250);
        add(logo);
    }
    private void inicializarBotones(){
        JButton nueva = crearBotonImagen("assets/boton_nueva.png","assets/boton_nueva_hover.png",380, 250);
        JButton cargar = crearBotonImagen("assets/boton_cargar.png","assets/boton_cargar_hover.png",380, 320);
        JButton ajustes = crearBotonImagen("assets/boton_ajustes.png","assets/boton_ajustes_hover.png",380, 390);
        JButton salir = crearBotonImagen("assets/boton_salir.png","assets/boton_salir_hover.png",380, 460);

        nueva.addActionListener(e -> {
            ventana.setContentPane(new PantallaNueva(ventana));
            ventana.revalidate();
        });
        cargar.addActionListener(e -> {
            ventana.setContentPane(new PantallaCargar(ventana));
            ventana.revalidate();
        });
        ajustes.addActionListener(e -> {
            ventana.setContentPane(new PantallaAjustes(ventana));
            ventana.revalidate();
        });
        salir.addActionListener(e -> System.exit(0));

        add(nueva);
        add(cargar);
        add(ajustes);
        add(salir);
    }


    private JButton crearBotonImagen(String rutaNormal, String rutaHover, int x, int y){
        JButton b = new JButton(new ImageIcon(rutaNormal));
        b.setRolloverIcon(new ImageIcon(rutaHover));
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.setBounds(x, y, 200, 50);
        return b;
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}