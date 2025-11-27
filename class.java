import java.util.ArrayList;
class User{
    private String nombre;
    public User(String nombre){
        this.nombre=nombre;
    }
    public void recibirNotificacion(String mensaje){
        System.out.println("¡HOLA! " + nombre + " Recibio: " + mensaje);
    }
}
class CanalNoticias{
    private ArrayList<User> usuarios=new ArrayList<>();
    public void agregarUsuario(User u){
        usuarios.add(u);
    }
    public void quitarUsuario(User u){
        usuarios.remove(u);
    }
    public void publicarNoticia(String noticia){
        System.out.println("\nNueva Noticia: "+noticia);
        notificarUsuarios(noticia);
    }
    public void publicarComentario(String comentario){
        System.out.println("\nComentario: "+comentario);
        notificarUsuarios(noticia);
    }
    private void notificarUsuarios(String mensaje){
        for(User u:usuarios){
            u.recibirNotificacion(mensaje);
        }
    }
}
public class Observador	{
    public static void main(String[]args){
        CanalNoticias canal=new CanalNoticias();
        User u1=new User("Sophia");
        User u2=new User("Diego");
        User u3=new User("Laura");
        canal.agregarUsuario(u1);
        canal.agregarUsuario(u2);
        canal.agregarUsuario(u3);
        canal.publicarNoticia("\nSe cayo cloudflare demandan al Diego \n");
        canal.publicarNoticia("\nCierra puertas en el mall \n");
        canal.quitarUsuario(u2);
        canal.publicarNoticia("\nViaja por todo el mundo con bloxfly, nuevos destinos en tendencia para vacionar\n");
        canal.agregarUsuario(new User("Fernando"));
        canal.publicarNoticia("\nEl internet llega a tu casa, hoy se añaden nuevos usuarios a la plataforma\n");
    }
}