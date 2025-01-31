module com.example.prepare {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prepare to javafx.fxml;
    exports com.example.prepare;
    exports com.example.prepare.final_option_1;
    opens com.example.prepare.final_option_1 to javafx.fxml;

    exports com.example.prepare.midterm.midterm_option_1;
    exports com.example.prepare.midterm.midterm_option_2;
}