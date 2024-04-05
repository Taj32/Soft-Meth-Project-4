module com.example.softmethproject4new {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.example.softmethproject4new to javafx.fxml;
    exports com.example.softmethproject4new;
}