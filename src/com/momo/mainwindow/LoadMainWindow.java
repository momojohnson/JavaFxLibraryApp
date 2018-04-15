package com.momo.mainwindow;

import com.momo.utils.Utils;
import com.momo.datamodel.DataSource;
import com.momo.datamodel.LibrarySettings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

// Main application entry point
public class LoadMainWindow extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Loads the user login screen
        Parent root = FXMLLoader.load(getClass().getResource("/com/momo/userlogin/userLogin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(Utils.TITLE_IMAGE_URL));
        primaryStage.setTitle("User Login");

        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Opens connection to the database and create config file with a default user. username: admin password: password
    @Override
    public void init() throws Exception {
        super.init();
        DataSource.getInstance().openConnection();
        LibrarySettings.createConfigFile();


    }

    // Closes connection to the database when the application closes.
    @Override
    public void stop() throws Exception {
        super.stop();
        DataSource.getInstance().closeConnection();

    }
}




