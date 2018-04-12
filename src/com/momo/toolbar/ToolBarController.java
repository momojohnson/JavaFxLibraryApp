package com.momo.toolbar;

import com.momo.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ToolBarController {

   public void initialize(){

   }


   @FXML
    private void addMember(){
       loadWindow("/com/momo/addmembers/addMember.fxml", "Add Member", 390, 540);
   }

   @FXML
   private void viewMembers(){
       loadWindow("/com/momo/membersTable/memberTable.fxml", "View Members", 750, 500);
   }

   @FXML
    private void addBook(){
       loadWindow("/com/momo/addbooks/addBook.fxml", "Add Book", 600, 390);

   }

   @FXML
    private void viewBooks(){
       loadWindow("/com/momo/booksTable/bookTable.fxml", "View Books", 800, 300);

   }
   @FXML
    private void loadSetting(){
       loadWindow("/com/momo/settings/settings.fxml", "View Books", 375, 300);
   }


    private void loadWindow(String location, String title, int width, int height) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(location));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image(Utils.TITLE_IMAGE_URL));
            stage.setTitle(title);
            stage.setScene(new Scene(parent, width, height));


            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


