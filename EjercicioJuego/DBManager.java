import java.sql.*;

public class DBManager{
    private String url = "jdbc:sqlite:datos_juego.db";

    public DBManager(){
        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS partidas (" +"id INTEGER PRIMARY KEY, " +"nombre TEXT, " +"tiempo TEXT, " +"escenario TEXT, " +"ropaSuperior TEXT, " +"ropaInferior TEXT, " +"zapatos TEXT)";
            st.execute(sql);

            st.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Partida getPartida(int id){
        try {
            Connection conn = DriverManager.getConnection(url);

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM partidas WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            Partida p = null;

            if (rs.next()) {
                p = new Partida(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("tiempo"),
                    rs.getString("escenario"),
                    rs.getString("ropaSuperior"),
                    rs.getString("ropaInferior"),
                    rs.getString("zapatos")
                );
            }

            rs.close();
            ps.close();
            conn.close();
            return p;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void guardarPartida(int id, String nombre, String tiempo, String escenario, String ropaSup, String ropaInf, String zapatos) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);

            PreparedStatement ps = conn.prepareStatement("INSERT OR REPLACE INTO partidas " +"(id, nombre, tiempo, escenario, ropaSuperior, ropaInferior, zapatos) " +"VALUES (?, ?, ?, ?, ?, ?, ?)");

            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setString(3, tiempo);
            ps.setString(4, escenario);
            ps.setString(5, ropaSup);
            ps.setString(6, ropaInf);
            ps.setString(7, zapatos);

            ps.executeUpdate();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}