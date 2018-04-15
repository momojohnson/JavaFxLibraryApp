package com.momo.settings;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.momo.utils.Validator;
import com.momo.datamodel.LibrarySettings;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SettingsController {
    @FXML
    private JFXTextField txtNumDaysWithoutFine;
    @FXML
    private JFXTextField txtFinePerDay;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private Text errorText;

    public void initialize(){
    loadConfigFile();
    }

    @FXML
    private void btnSave(){
        if(validInput()){

            LibrarySettings.updateConfigFile(Integer.parseInt(txtNumDaysWithoutFine.getText()), Double.parseDouble(txtFinePerDay.getText()),
                    txtUserName.getText(), txtPassword.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successfully Save");
            alert.setContentText("Data has been updated successfully");
            alert.showAndWait();
        }

    }

    @FXML
    private void btnCancel(){
        Stage stage = (Stage) errorText.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void loadConfigFile(){
        LibrarySettings libraryFine = LibrarySettings.getLibrarySettings();
        txtNumDaysWithoutFine.setText(String.valueOf(libraryFine.getNumOfDayWithoutFine()));
        txtFinePerDay.setText(String.valueOf(libraryFine.getFinePerDay()));
        txtUserName.setText(libraryFine.getUsername());
        txtPassword.setText(libraryFine.getPassword());
    }


    private boolean validInput(){
        if(!Validator.isValidData(txtUserName)){
            errorText.setVisible(true);
            errorText.setText("Username is a required field");
            return false;
        }
        if(!Validator.isValidData(txtPassword)){
            errorText.setText("Password field is a required field");
            errorText.setVisible(true);
            return false;
        }
        errorText.setVisible(false);
        return true;
    }
}
