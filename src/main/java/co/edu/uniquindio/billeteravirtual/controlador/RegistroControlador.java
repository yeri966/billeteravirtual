package co.edu.uniquindio.billeteravirtual.controlador;

import co.edu.uniquindio.billeteravirtual.modelo.Banco;
import co.edu.uniquindio.billeteravirtual.modelo.BilleteraVirtual;
import co.edu.uniquindio.billeteravirtual.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.UUID;

public class RegistroControlador {

    @FXML private TextField txtIdentificacion;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtDireccion;
    @FXML private PasswordField txtPassword;

    private final Banco banco = Banco.getInstancia();

    @FXML
    public void registrarse() {
        String id = txtIdentificacion.getText();
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String direccion = txtDireccion.getText();
        String password = txtPassword.getText();

        if (id.isEmpty() || nombre.isEmpty() || correo.isEmpty() || direccion.isEmpty() || password.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        if (banco.buscarUsuario(id) != null) {
            mostrarAlerta("Error", "Ya existe un usuario con esa identificación", Alert.AlertType.ERROR);
            return;
        }

        BilleteraVirtual billetera = new BilleteraVirtual(UUID.randomUUID().toString().substring(0, 8), 0.0);

        Usuario usuario = new Usuario(); // Usamos constructor vacío
        usuario.setIdentificacion(id);
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setDireccion(direccion);
        usuario.setPassword(password);
        usuario.setBilletera(billetera);

        banco.registrarUsuario(usuario);
        mostrarAlerta("Éxito", "Usuario registrado correctamente", Alert.AlertType.INFORMATION);
        cerrarVentana();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) txtIdentificacion.getScene().getWindow();
        stage.close();
    }
}