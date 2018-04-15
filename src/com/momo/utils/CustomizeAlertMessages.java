package com.momo.utils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.util.List;
import java.util.Optional;

public class CustomizeAlertMessages {

    // Customize Alert message for alert type Information
    public static Optional<ButtonType> showAlertInformationType(String title, String headerText, String textContent){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(textContent);

        return alert.showAndWait();
    }

    // A customize alert message for alert Confirmation
    public static Optional<ButtonType> showAlertConfirmationType(String title, String headerText, String textContent){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(textContent);
        return alert.showAndWait();
    }
    
    public static Optional<ButtonType> showAlertErrorType(String title, String headerText, String textContent){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(textContent);
        return alert.showAndWait();
    }

    public static void showMaterialDialog(StackPane root, Node nodeToBlur, String cssClass,
                                          List<JFXButton> controls, String headerText)
    {
        BoxBlur blur = new BoxBlur(3, 3, 3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);
        controls.forEach(control -> {
            control.getStyleClass().add(cssClass);
            control.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event)-> {
                dialog.close();
            });


        });
        Label label = new Label(headerText);
        label.getStyleClass().add("style-dialog-fonts");
        //label.setFont(Font.font("Bernard MT Condensed", FontWeight.EXTRA_BOLD, 14));
        label.setStyle("-fx-text-fill: black");
        dialogLayout.setHeading(label);
        dialogLayout.setActions(controls);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event)->{
            nodeToBlur.setEffect(null);
        });
        nodeToBlur.setEffect(blur);
    }

}
