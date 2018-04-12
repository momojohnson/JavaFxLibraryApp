package com.momo.userlogin;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.momo.utils.Utils;
import com.momo.Validator;
import com.momo.datamodel.LibrarySettings;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

public class UserLoginController {

    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private Label lblUserLogin;
    @FXML
    private Text lblError;

    private LibrarySettings librarySettings;


    public void initialize(){
        librarySettings = LibrarySettings.getLibrarySettings();
    }


    @FXML
    private void loginUser(){
        if(validateInput()){

            String currentPassword = librarySettings.getPassword();
            String currentUsername = librarySettings.getUsername();
            if((currentPassword.equals(DigestUtils.shaHex(txtPassword.getText())) &&  (currentUsername.equals(txtUsername.getText())))){
                System.out.println("Login Successful");
                closeCurrentWindow();
                loadMainProgramWindow("/com/momo/mainwindow/mainWindow.fxml", "Library Interface", 950, 600);
            }else{
               txtUsername.getStyleClass().add("wrong-login-credentials");
               txtPassword.getStyleClass().add("wrong-login-credentials");
            }
        }
    }

    @FXML
    private void cancelLogin(){
        Platform.exit();
    }

    private boolean validateInput(){
        if(!Validator.isValidData(txtUsername)){
            lblError.setVisible(true);
            lblError.setText("Username is a required field.");
            lblError.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
            lblError.setFill(Color.RED);
            return false;
        }
        if(!Validator.isValidData(txtPassword)){
            lblError.setVisible(true);
            lblError.setText("password is a required field.");
            lblError.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
            lblError.setFill(Color.RED);
            return false;
        }
        lblError.setText("");
        lblError.setVisible(false);
        return true;
    }

    private void loadMainProgramWindow(String location, String title, int width, int height){
        try {
            Parent windowParent = FXMLLoader.load(getClass().getResource(location));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.getIcons().add(new Image(Utils.TITLE_IMAGE_URL));
            stage.setScene(new Scene(windowParent, width, height));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeCurrentWindow(){
        Stage stage = (Stage) txtPassword.getScene().getWindow();
        stage.close();
    }
}
