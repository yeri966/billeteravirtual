package co.edu.uniquindio.billeteravirtual.controlador;

import co.edu.uniquindio.billeteravirtual.modelo.Banco;
import co.edu.uniquindio.billeteravirtual.modelo.Sesion;
import co.edu.uniquindio.billeteravirtual.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class LoginControlador {

    @FXML private TextField txtIdentificacion;
    @FXML private PasswordField txtPassword;

    private final Banco banco = Banco.getInstancia();

    @FXML
    public void iniciarSesion() {
        String id = txtIdentificacion.getText();
        String password = txtPassword.getText();

        if (id.isEmpty() || password.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        Usuario usuario = banco.validarUsuario(id, password);
        if (usuario == null) {
            mostrarAlerta("Error", "Usuario o contraseña incorrectos", Alert.AlertType.ERROR);
            return;
        }

        Sesion.getInstancia().setUsuario(usuario);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/panelCliente.fxml"));
            Parent root = loader.load();
            PanelClienteControlador controller = loader.getController();
            controller.inicializarValores(usuario);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel del Cliente");
            stage.show();

            cerrarVentana();
        } catch (Exception e) {
            e.printStackTrace();
        }
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