module billeteravirtual {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires static lombok;

    opens co.edu.uniquindio.billeteravirtual.controlador to javafx.fxml;
    exports co.edu.uniquindio.billeteravirtual.aplicacion;


}