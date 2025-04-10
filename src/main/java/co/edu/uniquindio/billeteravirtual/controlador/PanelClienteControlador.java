package co.edu.uniquindio.billeteravirtual.controlador;

import co.edu.uniquindio.billeteravirtual.modelo.Sesion;
import co.edu.uniquindio.billeteravirtual.modelo.Transaccion;
import co.edu.uniquindio.billeteravirtual.modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PanelClienteControlador {

    @FXML
    private Label lblNombre;

    @FXML
    private TableView<Transaccion> tablaTransacciones;
    @FXML
    private TableColumn<Transaccion, String> colDescripcion;
    @FXML
    private TableColumn<Transaccion, Double> colMonto;
    @FXML
    private TableColumn<Transaccion, String> colCategoria;
    @FXML
    private TableColumn<Transaccion, String> colFecha;

    private Usuario usuario;

    @FXML
    public void initialize() {
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    }

    public void inicializarValores(Usuario usuario) {
        this.usuario = usuario;
        lblNombre.setText(usuario.getNombre() + ", bienvenido a su banco. Aquí puede ver sus transacciones.");
        cargarTransacciones();
    }

    private void cargarTransacciones() {
        tablaTransacciones.setItems(FXCollections.observableArrayList(
                usuario.getBilletera().getTransacciones()
        ));
    }

    @FXML
    public void consultarSaldo() {
        double saldo = usuario.getBilletera().getSaldo();
        mostrarAlerta("Saldo actual", "Tu saldo es: $" + saldo, Alert.AlertType.INFORMATION);
    }

    @FXML
    public void abrirTransferencia() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transferencia.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Transferencia");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editarPerfil() {
        mostrarAlerta("Editar perfil", "Aquí se abriría la edición del perfil", Alert.AlertType.INFORMATION);
    }

    @FXML
    public void cerrarSesion() {
        Sesion.getInstancia().cerrarSesion();
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
        Stage stage = (Stage) lblNombre.getScene().getWindow();
        stage.close();
    }
}
