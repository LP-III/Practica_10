public class Partida {
    private int id;
    private String nombre;
    private String tiempo;
    private String escenario;
    private String ropaSuperior;
    private String ropaInferior;
    private String zapatos;

    public Partida(int id, String nombre, String tiempo, String escenario, String ropaSuperior, String ropaInferior, String zapatos) {

        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.escenario = escenario;
        this.ropaSuperior = ropaSuperior;
        this.ropaInferior = ropaInferior;
        this.zapatos = zapatos;
    }

    public int getId() {
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getTiempo(){
        return tiempo;
    }
    public String getEscenario(){
        return escenario;
    }
    public String getRopaSuperior(){
        return ropaSuperior;
    }
    public String getRopaInferior(){
        return ropaInferior;
    }
    public String getZapatos(){
        return zapatos;
    }
}
