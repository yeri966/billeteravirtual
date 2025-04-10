package co.edu.uniquindio.billeteravirtual.controlador;

import co.edu.uniquindio.billeteravirtual.modelo.Banco;
import co.edu.uniquindio.billeteravirtual.modelo.BilleteraVirtual;
import co.edu.uniquindio.billeteravirtual.modelo.Sesion;
import co.edu.uniquindio.billeteravirtual.modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TransferenciaControlador {

    @FXML
    private TextField txtDestino, txtMonto;

    @FXML
    private ComboBox<String> comboCategoria;

    private final Banco banco = Banco.getInstancia();
    private final Sesion sesion = Sesion.getInstancia();

    @FXML
    public void initialize() {
        comboCategoria.setItems(FXCollections.observableArrayList(
                "Alimentación", "Servicios", "Transporte", "Ocio", "Salud", "Educación", "Otros"
        ));
    }

    @FXML
    public void realizarTransferencia() {
        String numeroDestino = txtDestino.getText();
        String montoStr = txtMonto.getText();
        String categoria = comboCategoria.getValue();

        if (numeroDestino.isEmpty() || montoStr.isEmpty() || categoria == null) {
            mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        double monto;
        try {
            monto = Double.parseDouble(montoStr);
            if (monto <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El monto debe ser un número positivo", Alert.AlertType.ERROR);
            return;
        }

        Usuario remitente = sesion.getUsuario();
        BilleteraVirtual billeteraDestino = buscarBilleteraPorNumero(numeroDestino);

        if (billeteraDestino == null) {
            mostrarAlerta("Error", "No se encontró la billetera destino", Alert.AlertType.ERROR);
            return;
        }

        boolean exito = remitente.getBilletera().transferir(billeteraDestino, monto, categoria);

        if (exito) {
            mostrarAlerta("Éxito", "Transferencia realizada correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            mostrarAlerta("Error", "Saldo insuficiente", Alert.AlertType.ERROR);
        }
    }

    private BilleteraVirtual buscarBilleteraPorNumero(String numero) {
        return banco.getUsuarios().stream()
                .map(Usuario::getBilletera)
                .filter(b -> b.getNumero().equals(numero))
                .findFirst().orElse(null);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) txtDestino.getScene().getWindow();
        stage.close();
    }
}
