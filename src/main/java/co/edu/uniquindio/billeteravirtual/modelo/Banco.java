package co.edu.uniquindio.billeteravirtual.modelo;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    public static Banco INSTANCIA;
    private final List<Usuario> usuarios = new ArrayList<>();

    private Banco() {}

    public static Banco getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new Banco();
        }
        return INSTANCIA;
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarUsuario(String id) {
        return usuarios.stream()
                .filter(u -> u.getIdentificacion().equals(id))
                .findFirst().orElse(null);
    }

    public Usuario validarUsuario(String id, String password) {
        Usuario u = buscarUsuario(id);
        return (u != null && u.getPassword().equals(password)) ? u : null;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
