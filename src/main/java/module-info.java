module org.example.softwareengineeringproject2_week2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.softwareengineeringproject2_week2 to javafx.fxml;
    exports org.example.softwareengineeringproject2_week2;
}