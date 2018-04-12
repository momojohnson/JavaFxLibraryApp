package com.momo.membersTable;

import com.momo.utils.Utils;
import com.momo.addmembers.AddMemberController;
import com.momo.datamodel.DataSource;
import com.momo.datamodel.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

public class memberTableController {

    @FXML
    private TableView<Member> memberTable;
    private ObservableList<Member> memberInfoList;
    public void  initialize(){
        memberInfoList = FXCollections.observableArrayList(DataSource.getInstance().queryAllMembers());
        memberTable.setItems(memberInfoList);
    }

    @FXML
    private void deleteMember(){

        Member member =  memberTable.getSelectionModel().getSelectedItem();
        if(member == null){
            Alert alertNull= new Alert(Alert.AlertType.ERROR);
            alertNull.setTitle("Deletion Error");
            alertNull.setHeaderText("Deletion Error");
            alertNull.setContentText("Please select a record in order to perform deletion");
            alertNull.showAndWait();
           return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm deletion operation");
        alert.setHeaderText("Confirm delete operation");
        alert.setContentText(String.format("Are you sure you want to delete %s %s?", member.getFirstName(), member.getLastName()));
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK){
            boolean deleted = DataSource.getInstance().deleteMemberRecord(member.getMemberId());
            if(deleted){
                memberInfoList.remove(member);
                Alert alertDelete = new Alert(Alert.AlertType.INFORMATION);
                alertDelete.setTitle("Record Deleted");
                alertDelete.setHeaderText(String.format("%s %s has been deleted successfully", member.getFirstName(), member.getLastName()));
                alertDelete.showAndWait();
                return;
            }
        }
        Alert alertCancel = new Alert(Alert.AlertType.INFORMATION);
        alertCancel.setTitle("Deletion Operation canceled");
        alertCancel.setHeaderText("Deletion operation has been canceled");
        alertCancel.setContentText("Deletion of record was canceled");
        alertCancel.showAndWait();
    }

    @FXML
    private void editMember(){
        try{
            Member member =  memberTable.getSelectionModel().getSelectedItem();
            System.out.println(member.getFirstName());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/momo/addmembers/addMember.fxml"));
            Parent parent = loader.load();
           AddMemberController controller =  loader.getController();
           controller.setUiValues(member);
            System.out.println(member.getId());
           Stage stage = new Stage();
           stage.initStyle(StageStyle.DECORATED);
           stage.getIcons().add(new Image(Utils.TITLE_IMAGE_URL));
           stage.setTitle("Edit Member");
           stage.setScene(new Scene(parent, 390, 540));
           stage.setResizable(false);
           stage.show();
           stage.setOnCloseRequest(e->{
               refreshTable();
           });


        }catch (IOException exe){
            System.out.println(exe.getMessage());
        }

    }
    @FXML
    public void refreshTable(){
        memberInfoList.clear();
        memberInfoList = FXCollections.observableArrayList(DataSource.getInstance().queryAllMembers());
        memberTable.setItems(memberInfoList);
    }
}
