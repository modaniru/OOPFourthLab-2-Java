module com.example.ooplab42 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ooplab42 to javafx.fxml;
    exports com.example.ooplab42;
}