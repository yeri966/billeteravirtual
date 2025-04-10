package co.edu.uniquindio.billeteravirtual.controlador;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class InicioControlador {

    private void abrirVentana(String ruta, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void irIniciarSesion(javafx.event.ActionEvent actionEvent) {
        abrirVentana("/login.fxml", "Iniciar Sesión");
    }

    public void irRegistroCliente(javafx.event.ActionEvent actionEvent) {
        abrirVentana("/registro.fxml", "Registro");
    }
}