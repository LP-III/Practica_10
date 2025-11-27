import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PantallaAjustes extends JPanel{
    private Image fondo;
    private JFrame ventana;
    private boolean musicaActiva=false;
    private boolean sonidoActivo=false;
    public PantallaAjustes(JFrame ventana){
        this.ventana = ventana;
        setLayout(null);
        cargarImagenFondo("assets/fondo_juego.png");
        inicializarComponentes();
    }
    private void cargarImagenFondo(String ruta){
        fondo = new ImageIcon(ruta).getImage();
    }
    private void inicializarComponentes(){
        JLabel titulo = new JLabel(new ImageIcon("assets/titulo_ajustes.png"));
        titulo.setBounds(300, 30, 400, 120);
        add(titulo);
        JLabel chkMusica = new JLabel(new ImageIcon("assets/chk_off.png"));
        chkMusica.setBounds(350, 150, 64, 64);
        add(chkMusica);

        JLabel textoMusica = new JLabel("Música");
        textoMusica.setForeground(Color.WHITE);
        textoMusica.setFont(new Font("Serif", Font.BOLD, 22));
        textoMusica.setBounds(430, 165, 200, 40);
        add(textoMusica);

        chkMusica.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                musicaActiva = !musicaActiva;
                if (musicaActiva) {
                    chkMusica.setIcon(new ImageIcon("assets/chk_on.png"));
                } else {
                    chkMusica.setIcon(new ImageIcon("assets/chk_off.png"));
                }
            }
        });


        JLabel chkSonidos = new JLabel(new ImageIcon("assets/chk2_off.png"));
        chkSonidos.setBounds(350, 230, 64, 64);
        add(chkSonidos);

        JLabel textoSonidos = new JLabel("Efectos de sonido");
        textoSonidos.setForeground(Color.WHITE);
        textoSonidos.setFont(new Font("Serif", Font.BOLD, 22));
        textoSonidos.setBounds(430, 245, 250, 40);
        add(textoSonidos);

        chkSonidos.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                sonidoActivo = !sonidoActivo;
                if(sonidoActivo){
                    chkSonidos.setIcon(new ImageIcon("assets/chk2_on.png"));
                }else{
                    chkSonidos.setIcon(new ImageIcon("assets/chk2_off.png"));
                }
            }
        });
        JLabel textoIdioma = new JLabel("Idioma:");
        textoIdioma.setForeground(Color.WHITE);
        textoIdioma.setFont(new Font("Serif", Font.BOLD, 22));
        textoIdioma.setBounds(350, 315, 200, 40);
        add(textoIdioma);
        JLabel imgIdioma = new JLabel(new ImageIcon("assets/bandera_es.png"));
        imgIdioma.setBounds(430, 310, 64, 64);
        add(imgIdioma);
        JButton btnIdioma = new JButton();
        btnIdioma.setBounds(430, 310, 64, 64);
        btnIdioma.setOpaque(false);
        btnIdioma.setContentAreaFilled(false);
        btnIdioma.setBorderPainted(false);
        btnIdioma.addActionListener(new ActionListener() {
            //o
            public void actionPerformed(ActionEvent e) {
                ImageIcon actual = (ImageIcon) imgIdioma.getIcon();
                if (actual.toString().contains("es")) {
                    imgIdioma.setIcon(new ImageIcon("assets/bandera_en.png"));
                } else {
                    imgIdioma.setIcon(new ImageIcon("assets/bandera_es.png"));
                }
            }
        });
        add(btnIdioma);
        JButton volver = new JButton(new ImageIcon("assets/btn_volver.png"));
        volver.setBounds(380, 440, 200, 80);
        volver.setBorderPainted(false);
        volver.setContentAreaFilled(false);
        volver.setFocusPainted(false);
        volver.setOpaque(false);
        volver.addActionListener(new ActionListener() {
            //o
            public void actionPerformed(ActionEvent e) {
                ventana.setContentPane(new PantallaInicio(ventana));
                ventana.revalidate();
            }
        });
        add(volver);
    }


    //o
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}