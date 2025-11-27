import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
public class VentanaMusica extends JFrame implements ActionListener {
    JLabel estado, portada;
    JButton play, pause, stop, volumen;
    Clip clip;
    FloatControl controlVolumen;
    boolean pausado = false;
    long pausaPos = 0;

    public VentanaMusica(){
        super("Alarcon, Montalvo");
        setSize(400, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cargarAudio("musica.wav");

        JLabel titulo = new JLabel("Canción: Aishitetanoni-Materu", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        portada = new JLabel(new ImageIcon("musica.png"));
        portada.setHorizontalAlignment(SwingConstants.CENTER);
        add(portada, BorderLayout.CENTER);

        JPanel abajo = new JPanel(new BorderLayout());
        JPanel controles = new JPanel();
        play = new JButton("▶️ Play");
        pause = new JButton("⏸️ Pause");
        stop = new JButton("⏹️ Stop");
        controles.add(play);
        controles.add(pause);
        controles.add(stop);

        estado = new JLabel("Estado: cargando........", SwingConstants.CENTER);
        estado.setOpaque(true);
        estado.setBackground(Color.LIGHT_GRAY);

        abajo.add(controles, BorderLayout.NORTH);
        abajo.add(estado, BorderLayout.SOUTH);
        add(abajo, BorderLayout.SOUTH);

        JPanel volum = new JPanel();
        volumen = new JButton("🔊 Volumen +");
        volum.add(volumen, BorderLayout.CENTER);
        volum.setBorder(BorderFactory.createEmptyBorder(80, 0, 80, 0));
        add(volum, BorderLayout.EAST);

        add(new JLabel("Spotifi", SwingConstants.CENTER), BorderLayout.WEST);

        play.addActionListener(this);
        pause.addActionListener(this);
        stop.addActionListener(this);
        volumen.addActionListener(this);

        setVisible(true);
    }

    private void cargarAudio(String archivo) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(archivo));
            clip = AudioSystem.getClip();
            clip.open(ais);

            controlVolumen = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == play) {
            if (pausado) {
                clip.setMicrosecondPosition(pausaPos);
                pausado = false;
            }
            clip.start();
            estado.setText("Reproduciendo");
            estado.setBackground(Color.GREEN);
        }

        else if (e.getSource() == pause) {
            pausaPos = clip.getMicrosecondPosition();
            clip.stop();
            pausado = true;
            estado.setText("pausa");
            estado.setBackground(Color.ORANGE);
        }

        else if (e.getSource() == stop) {
            clip.stop();
            clip.setMicrosecondPosition(0);
            pausado = false;
            estado.setText("detenido");
            estado.setBackground(Color.RED);
        }

        else if (e.getSource() == volumen) {
            controlVolumen.setValue(controlVolumen.getValue() - 2.0f);
            estado.setText("Subiendo volumen...");
            estado.setBackground(Color.BLACK);
        }
    }

    public static void main(String[] args) {
        new VentanaMusica();
    }
}