module propertiesmanagementcompany{
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;  // Only include JUnit 5 dependency

    opens application to javafx.graphics, javafx.fxml;
    exports application;
}