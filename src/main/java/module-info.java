module com.example.te {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.te to javafx.fxml;
    exports com.example.te;
}