package co.edu.uniquindio.billeteravirtual.modelo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {
    private String identificacion;
    private String nombre;
    private String correo;
    private String direccion;
    private String password;
    private BilleteraVirtual billetera;


    public Usuario() {
    }
}
