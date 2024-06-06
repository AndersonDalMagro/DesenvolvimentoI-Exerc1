module com.ds1_ex1.ds1_ex1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ds1_ex1 to javafx.fxml;
    exports com.ds1_ex1;
}