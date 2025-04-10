package co.edu.uniquindio.billeteravirtual.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class BilleteraVirtual {

    private String numero;
    private double saldo;
    private List<Transaccion> transacciones = new ArrayList<>();

    public BilleteraVirtual(String numero, double saldoInicial) {
        this.numero = numero;
        this.saldo = saldoInicial;
    }

    public void recargar(double monto) {
        if (monto > 0) {
            saldo += monto;
            transacciones.add(new Transaccion("Recarga", monto, "Recarga"));
        }
    }

    public boolean transferir(BilleteraVirtual destino, double monto, String categoria) {
        if (saldo >= monto && monto > 0) {
            saldo -= monto;
            destino.recargar(monto);

            transacciones.add(new Transaccion("Transferencia a " + destino.getNumero(), -monto, categoria));
            return true;
        }
        return false;
    }
}
