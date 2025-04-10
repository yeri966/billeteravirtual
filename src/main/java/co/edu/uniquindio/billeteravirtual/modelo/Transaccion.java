package co.edu.uniquindio.billeteravirtual.modelo;

import lombok.*;
import java.util.Date;

@Getter
@Setter
public class Transaccion {

    private String descripcion;
    private double monto;
    private String categoria;
    private Date fecha;

    public Transaccion() {
        this.fecha = new Date();
    }

    public Transaccion(String descripcion, double monto, String categoria) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.categoria = categoria;
        this.fecha = new Date(); // la fecha se pone automáticamente
    }

    public Transaccion(String descripcion, double monto, String categoria, Date fecha) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.categoria = categoria;
        this.fecha = fecha;
    }
}
