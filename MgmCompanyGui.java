package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.util.Duration;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class MgmCompanyGui extends Application {

	private static final int NUM_PIX = 15;
	private static final int SCALE_FACTOR = 40;
	private static final String MGMT_COMPANY = "Management Company";
	private TextField mgmNameText, mgmIdText, mgmFeeText, propNameText, propCityText, propRentText, propOwnerText,
			propPlotXText, propPlotYText, propPlotWidthText, propPlotDepthText;
	private Label mgmNameLabel, mgmIdLabel, mgmFeeLabel, propNameLabel, propCityLabel, propRentLabel, propOwnerLabel,
			propXLabel, propYLabel, propWidthLabel, propDepthLabel;

	private Button newMgmBtn, addPropertyBtn, maxRentBtn, totalRentBtn, propListBtn, exitBtn;
	Group plotFrame = new Group();
	private ManagementCompany mgmCompany;

	private void createManagementCompanyLabels() {
		mgmNameLabel = new Label("Name:");
		mgmIdLabel = new Label("Tax Id:");
		mgmFeeLabel = new Label("Fee %:");
	}

	private void createManagementCompanyTextFields() {
		mgmNameText = new TextField();
		mgmNameText.setMaxWidth(100);

		mgmIdText = new TextField();
		mgmIdText.setMaxWidth(80);

		mgmFeeText = new TextField();
		mgmFeeText.setMaxWidth(40);
	}

	private void buildManagementCompanyComponents() {
		createManagementCompanyLabels();
		createManagementCompanyTextFields();
	}
	
	

	private void addMgmtComponentsToMainPane(VBox mgmPane) {
		HBox mgmtComponentsPane = new HBox(5);
		mgmtComponentsPane.getChildren().addAll(mgmNameLabel, mgmNameText);
		mgmtComponentsPane.getChildren().addAll(mgmIdLabel, mgmIdText);
		mgmtComponentsPane.getChildren().addAll(mgmFeeLabel, mgmFeeText);
		mgmPane.getChildren().addAll(mgmtComponentsPane);
	}

	private void configureMgmtTitlePane(TitledPane mgmTitlePane) {
		mgmTitlePane.setCollapsible(false);
		mgmTitlePane.setMaxWidth(450);
		mgmTitlePane.setPadding(new Insets(20, 10, 5, 10));
	}

	private void createPropertyLabels() {
		propNameLabel = new Label("Property Name");
		propCityLabel = new Label("City");
		propRentLabel = new Label("Rent");
		propOwnerLabel = new Label("Owner");

		propXLabel = new Label("Plot X Value");
		propYLabel = new Label("Plot Y Value");
		propWidthLabel = new Label("Plot Width");
		propDepthLabel = new Label("Plot Depth");
	}

	private void createPropertyTextFields() {
		propNameText = new TextField();
		propNameText.setMaxWidth(100);

		propCityText = new TextField();
		propCityText.setMaxWidth(80);

		propRentText = new TextField();
		propRentText.setMaxWidth(80);

		propOwnerText = new TextField();
		propOwnerText.setMaxWidth(100);

		propPlotXText = new TextField();
		propPlotXText.setMaxWidth(100);

		propPlotYText = new TextField();
		propPlotYText.setMaxWidth(100);

		propPlotWidthText = new TextField();
		propPlotWidthText.setMaxWidth(100);

		propPlotDepthText = new TextField();
		propPlotDepthText.setMaxWidth(100);
	}

	private void disablePropertyTextFields() {
		propNameText.setEditable(false);
		propCityText.setEditable(false);
		propRentText.setEditable(false);
		propOwnerText.setEditable(false);
		propPlotXText.setEditable(false);
		propPlotYText.setEditable(false);
		propPlotWidthText.setEditable(false);
		propPlotDepthText.setEditable(false);
	}

	private void buildPropertyComponents() {
		createPropertyLabels();
		createPropertyTextFields();
		disablePropertyTextFields();
	}

	private void addComponentsToPropertyComponentsPane(VBox propertyComponentsPane) {
		propertyComponentsPane.getChildren().addAll(propNameLabel, propNameText);
		propertyComponentsPane.getChildren().addAll(propCityLabel, propCityText);
		propertyComponentsPane.getChildren().addAll(propRentLabel, propRentText);
		propertyComponentsPane.getChildren().addAll(propOwnerLabel, propOwnerText);
		VBox.setMargin(propertyComponentsPane, new Insets(5, 30, 10, 30));
	}

	private void addComponentsToPropertyPlotPane(VBox propPlotPane) {
		propPlotPane.getChildren().addAll(propXLabel, propPlotXText);
		propPlotPane.getChildren().addAll(propYLabel, propPlotYText);
		propPlotPane.getChildren().addAll(propWidthLabel, propPlotWidthText);
		propPlotPane.getChildren().addAll(propDepthLabel, propPlotDepthText);
		VBox.setMargin(propPlotPane, new Insets(5, 30, 10, 30));
	}

	private void configurePropertyTitlePane(TitledPane propertyTitlePane) {
		propertyTitlePane.setCollapsible(false);
		propertyTitlePane.setMaxWidth(500);
		propertyTitlePane.setPadding(new Insets(5, 120, 10, 120));
	}

	private void createButtons() {
		newMgmBtn = new Button("New Management Company");
		addPropertyBtn = new Button("Add Property");
		maxRentBtn = new Button("Max Rent");
		totalRentBtn = new Button("Total Rents");
		propListBtn = new Button("List of Properties");
		exitBtn = new Button("Exit");
	}

	private void disableButtons() {
		addPropertyBtn.setDisable(true);
		maxRentBtn.setDisable(true);
		totalRentBtn.setDisable(true);
		propListBtn.setDisable(true);
	}

	private void addFirstRowButtonsPane(HBox buttonPane) {
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.getChildren().addAll(newMgmBtn, addPropertyBtn, maxRentBtn);
	}

	private void addSecondRowButtonsPane(HBox buttonPane) {
		buttonPane.setPadding(new Insets(10, 0, 10, 0));
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.getChildren().addAll(totalRentBtn, propListBtn, exitBtn);
	}

	private void addButtonsToMainPane(VBox mainPane) {
		HBox buttonPane1 = new HBox(20);
		addFirstRowButtonsPane(buttonPane1);

		HBox buttonPane2 = new HBox(20);
		addSecondRowButtonsPane(buttonPane2);
		mainPane.getChildren().addAll(buttonPane1, buttonPane2);
	}

	private void setButtonActions() {
		newMgmBtn.setOnAction(new ButtonEventHandler());
		addPropertyBtn.setOnAction(new ButtonEventHandler());
		maxRentBtn.setOnAction(new ButtonEventHandler());
		propListBtn.setOnAction(new ButtonEventHandler());
		totalRentBtn.setOnAction(new ButtonEventHandler());
		exitBtn.setOnAction(new ButtonEventHandler());
	}

	// Handler class.
	private class ButtonEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			if (e.getSource() == newMgmBtn) {
				createNewMgm();
			} else if (e.getSource() == addPropertyBtn) {
				addProp();
			} else if (e.getSource() == maxRentBtn) {
				displayAlertBox(displayHighestRentPropertyInfo());
			} else if (e.getSource() == totalRentBtn) {
				displayAlertBox("Total Rent of the properties: " + mgmCompany.getTotalRent());
			} else if (e.getSource() == propListBtn) {
				displayAlertBox(mgmCompany.toString());
			} else if (e.getSource() == exitBtn)

				System.exit(0);
		}
	}
	
	private String displayHighestRentPropertyInfo() {
		Property highestRentProperty = mgmCompany.getHighestRentProperty();
		if (highestRentProperty != null) {
			return "Property Name: " + highestRentProperty.getPropertyName() +
					"\n Located in " + highestRentProperty.getCity() + 
					"\n Belonging to: " + highestRentProperty.getOwner() +
					"\n Rent Amount: " + highestRentProperty.getRentalAmount();
		} else {
			return "No properties added.";
		}
	}

	private void createNewMgm() {
		if (!isMgmFieldEmpty()) {
			if (isTaxFeeInvalid()) {
				handleInvalidMgmtFee();
			} else {
				mgmCompany = new ManagementCompany(mgmNameText.getText(), mgmIdText.getText(),
						Double.parseDouble(mgmFeeText.getText()));
				setPropertyButtons();
				setManagementTextFields();
				setPropertyTextFields();
				setPlotWindow();
			}
		}
	}
	private void displayPropertyList() {
	    StringBuilder content = new StringBuilder("List of the properties for " + mgmCompany.getName() + ", taxID: " + mgmCompany.getTaxID() + "\n");
	    content.append("______________________________________________________\n");

	    // Iterate through each property and display its details
	    for (int i = 0; i < ManagementCompany.MAX_PROPERTY; i++) {
	        Property property = mgmCompany.getProperty(i);
	        if (property != null) { 
	        	// Ensure the property is not null
	            content.append("Property Name: ").append(property.getPropertyName()).append("\n")
	                   .append("Located in: ").append(property.getCity()).append("\n")
	                   .append("Belonging to: ").append(property.getOwner()).append("\n")
	                   .append("Rent Amount: ").append(property.getRentalAmount()).append("\n")
	                   .append("---------------------------------------------------\n");
	        }
	    }

	    // Calculate and append the total management fee
	    double totalMgmtFee = mgmCompany.getTotalRent() * mgmCompany.getMgmtFee() / 100;
	    content.append("______________________________________________________\n");
	    content.append("Total management fee: ").append(totalMgmtFee).append("\n");

	    // Display the content in an alert
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Management Company");
	    alert.setHeaderText("List of the properties for " + mgmCompany.getName() + ", taxID: " + mgmCompany.getTaxID());
	    alert.setContentText(mgmCompany.toString()); // Use the toString() method of ManagementCompany
	    alert.setResizable(true);
	    alert.showAndWait();
	}








	private void drawPropertyPlot(Rectangle propRectangle, Property property) {
		int rectW = Math.min(400, property.getPlot().getWidth() * SCALE_FACTOR);
		int rectD = Math.min(400, property.getPlot().getDepth() * SCALE_FACTOR);
		int rectX = Math.min(400, property.getPlot().getX() * SCALE_FACTOR);
		int rectY = Math.min(400, property.getPlot().getY() * SCALE_FACTOR);
		propRectangle = new Rectangle(rectX, rectY, rectW, rectD);
		propRectangle.setFill(Color.TRANSPARENT);
		propRectangle.setStroke(Color.RED);
		propRectangle.setStrokeWidth(2);
		plotFrame.getChildren().addAll(propRectangle);
		displayAlertBox("Property's Plot location noted.");
	}

	private void displayAlertBox(String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Management Company");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
	}
	private void addProp() {
	    if (!isPropertyFieldEmpty()) {
	        Property property = isPropertyPlotFieldEmpty() ? buildPropertyWithDefaultPlot() : buildPropertyWithGivenPlot();

	        // Check if maximum property capacity is reached
	        if (mgmCompany.getPropertiesCount() >= ManagementCompany.MAX_PROPERTY) {
	            displayAlertBox("You have reached the maximum property capacity."); // Capacity reached message
	            displayPropertyList(); // Show the detailed list when max capacity is reached
	            return; // Exit without adding the property
	        }

	        int result = mgmCompany.addProperty(property);

	        if (result >= 0) {
	            Rectangle propRectangle = null;
	            
	            // Draw property plot if it does not overlap
	            if (!mgmCompany.checkForOverlap(property.getPlot())) {
	                drawPropertyPlot(propRectangle, property);
	                displayAlertBox("Property " + property.getPropertyName() + " was added.");
	            } else {
	                // Show a temporary red rectangle for overlap indication
	                drawPropertyPlot(propRectangle, property);
	                plotFrame.getChildren().remove(propRectangle);

	                Rectangle tempRectangle = new Rectangle(
	                    property.getPlot().getX() * SCALE_FACTOR,
	                    property.getPlot().getY() * SCALE_FACTOR,
	                    property.getPlot().getWidth() * SCALE_FACTOR,
	                    property.getPlot().getDepth() * SCALE_FACTOR
	                );
	                tempRectangle.setFill(Color.TRANSPARENT);
	                tempRectangle.setStroke(Color.RED);
	                tempRectangle.setStrokeWidth(2);
	                plotFrame.getChildren().add(tempRectangle);

	                // Display for 2 seconds, then remove
	                Timeline timeline = new Timeline(
	                    new KeyFrame(Duration.seconds(2), evt -> {
	                        plotFrame.getChildren().remove(tempRectangle);
	                        displayAlertBox("Property overlaps another; not added to the GUI plot.");
	                    })
	                );
	                timeline.setCycleCount(1);
	                timeline.play();
	            }

	            // Display the property list if the maximum capacity is now reached
	            if (mgmCompany.getPropertiesCount() == ManagementCompany.MAX_PROPERTY) {
	                displayPropertyList();
	            }
	        } else {
	            handleAddPropertyError(result, property);
	        }
	    }
	}







	private Property buildPropertyWithDefaultPlot() {
		return new Property(propNameText.getText(), propCityText.getText(), Double.parseDouble(propRentText.getText()),
				propOwnerText.getText());
	}

	private Property buildPropertyWithGivenPlot() {
		try {
			return new Property(propNameText.getText(), propCityText.getText(), Double.parseDouble(propRentText.getText()),
					propOwnerText.getText(), Integer.parseInt(propPlotXText.getText()),
					Integer.parseInt(propPlotYText.getText()), Integer.parseInt(propPlotWidthText.getText()),
					Integer.parseInt(propPlotDepthText.getText()));
		} catch (NumberFormatException e) {
			displayAlertBox("Plot location textfields are not integers");
			return new Property(propNameText.getText(), propCityText.getText(), 1000.0, propOwnerText.getText());
		}
	}

	private void handleAddPropertyError(int errorCode, Property property) {
		switch (errorCode) {
			case -1 -> displayAlertBox("This Property cannot be managed by this company.\nMax capacity reached.");
			case -2 -> displayAlertBox("Property " + property.getPropertyName() + " is null.");
			case -3 -> displayAlertBox("Property " + property.getPropertyName() + " is out of bounds.");
			case -4 -> displayAlertBox("Property " + property.getPropertyName() + " overlaps with another property.");
			default -> displayAlertBox("Unknown error occurred while adding the property.");
		}
	}

	private boolean isPropertyFieldEmpty() {
		return propNameText.getText().isEmpty() || propCityText.getText().isEmpty()
				|| propRentText.getText().isEmpty() || propOwnerText.getText().isEmpty();
	}

	private boolean isPropertyPlotFieldEmpty() {
		return propPlotXText.getText().isEmpty() || propPlotYText.getText().isEmpty()
				|| propPlotWidthText.getText().isEmpty() || propPlotDepthText.getText().isEmpty();
	}

	private boolean isMgmFieldEmpty() {
		return mgmNameText.getText().isEmpty() || mgmIdText.getText().isEmpty() || mgmFeeText.getText().isEmpty();
	}

	private boolean isTaxFeeInvalid() {
		try {
			double fee = Double.parseDouble(mgmFeeText.getText());
			return fee < 0 || fee > 100;
		} catch (NumberFormatException e) {
			return true;
		}
	}

	private void handleInvalidMgmtFee() {
		displayAlertBox("Fee is not valid; must be between 0-100.");
	}

	private void setPropertyButtons() {
		newMgmBtn.setDisable(true);
		addPropertyBtn.setDisable(false);
		maxRentBtn.setDisable(false);
		totalRentBtn.setDisable(false);
		propListBtn.setDisable(false);
	}

	private void setManagementTextFields() {
		mgmNameText.setEditable(false);
		mgmIdText.setEditable(false);
		mgmFeeText.setEditable(false);
		newMgmBtn.setDisable(true);
	}

	private void setPropertyTextFields() {
		propNameText.setEditable(true);
		propCityText.setEditable(true);
		propRentText.setEditable(true);
		propOwnerText.setEditable(true);
		propPlotXText.setEditable(true);
		propPlotYText.setEditable(true);
		propPlotWidthText.setEditable(true);
		propPlotDepthText.setEditable(true);
	}

	private void setPlotWindow() {
		Stage stage2 = new Stage();
		int sceneWidth = mgmCompany.getPlot().getWidth() * SCALE_FACTOR;
		int sceneDepth = mgmCompany.getPlot().getDepth() * SCALE_FACTOR;

		Rectangle mgmtRectangle = new Rectangle(0, 0, sceneWidth, sceneDepth);
		mgmtRectangle.setFill(Color.TRANSPARENT);
		mgmtRectangle.setStroke(Color.RED);
		mgmtRectangle.setStrokeWidth(2);
		plotFrame.getChildren().add(mgmtRectangle);
		stage2.setScene(new Scene(plotFrame, sceneWidth, sceneDepth));
		stage2.setX(10);
		stage2.setY(10);
		stage2.setTitle(mgmCompany.getName() + " plot. Property plots must fit inside this.");
		stage2.show();
	}
	@Override
	public void start(@SuppressWarnings("exports") Stage stage) {
		VBox mainPane = new VBox();
		buildManagementCompanyComponents();
		VBox mgmPane = new VBox(5);
		addMgmtComponentsToMainPane(mgmPane);

		TitledPane mgmTitlePane = new TitledPane(MGMT_COMPANY, mgmPane);
		configureMgmtTitlePane(mgmTitlePane);
		buildPropertyComponents();

		HBox propertyPane = new HBox();
		VBox propertyComponentsPane = new VBox();
		VBox propPlotPane = new VBox();
		addComponentsToPropertyComponentsPane(propertyComponentsPane);
		addComponentsToPropertyPlotPane(propPlotPane);

		propertyPane.getChildren().addAll(propertyComponentsPane, propPlotPane);
		HBox.setMargin(propertyPane, new Insets(5, 150, 10, 100));

		TitledPane propertyTitlePane = new TitledPane("Property Information", propertyPane);
		configurePropertyTitlePane(propertyTitlePane);
		mainPane.getChildren().addAll(mgmTitlePane, propertyTitlePane);

		createButtons();
		disableButtons();
		setButtonActions();
		addButtonsToMainPane(mainPane);

		Scene scene = new Scene(mainPane, 450, 430);
		stage.setScene(scene);
		stage.setTitle("Property Management");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static int getNumPix() {
		return NUM_PIX;
	}
}    
