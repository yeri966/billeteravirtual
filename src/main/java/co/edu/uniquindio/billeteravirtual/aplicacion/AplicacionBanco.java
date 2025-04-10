package co.edu.uniquindio.billeteravirtual.aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static javafx.application.Application.launch;



public class AplicacionBanco extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/inicio.fxml"));
            primaryStage.setTitle("Banco");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icono.png")));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
}
