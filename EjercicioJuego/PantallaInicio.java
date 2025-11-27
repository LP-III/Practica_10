import java.sql.*;

public class DBManager{
    private String url ="jdbc:sqlite:datos_juego.db";
    public DBManager(){
        try{
            Connection conn =DriverManager.getConnection(url);
            Statement st=conn.createStatement();
            String sql="CREATE TABLE IF NOT EXISTS partidas(" +
                    "id INTEGER PRIMARY KEY," +
                    "nombre TEXT," +
                    "tiempo TEXT," +
                    "escenario TEXT)";
            st.execute(sql);
            st.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Partida getPartida(int id) {
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
                        rs.getString("escenario")
                );
            }
            rs.close();
            ps.close();
            conn.close();
            return p;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void guardarPartida(int id, String nombre, String tiempo, String escenario){
        try{
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT OR REPLACE INTO partidas(id, nombre, tiempo, escenario) VALUES(?,?,?,?)"
            );
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setString(3, tiempo);
            ps.setString(4, escenario);
            ps.executeUpdate();
            ps.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
