package com.momo.membersTable;

import com.momo.addmembers.AddMemberController;
import com.momo.datamodel.DataSource;
import com.momo.datamodel.Member;
import com.momo.utils.CustomizeAlertMessages;
import com.momo.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

public class memberTableController {

    @FXML
    private TableView<Member> memberTable; // Table view for members
    private ObservableList<Member> memberInfoList; // Observable list for members
    public void  initialize(){
        memberInfoList = FXCollections.observableArrayList(DataSource.getInstance().queryAllMembers());
        memberTable.setItems(memberInfoList);
    }

    // An event handler that handles deletion of a member
    @FXML
    private void deleteMember(){

        Member member =  memberTable.getSelectionModel().getSelectedItem();
        if(member == null){
            CustomizeAlertMessages.showAlertInformationType("Deletion Error", "Couldn't Delete Member",
                    "Please select a record to perform deletion");
           return;
        }
        Optional<ButtonType> response = CustomizeAlertMessages.showAlertConfirmationType("Confirm Deletion Operation",
                "Confirm Delete Operation", String.format("Are you sure you want to delete %s %s?", member.getFirstName(), member.getLastName()));
        if(response.get() == ButtonType.OK){
            boolean deleted = DataSource.getInstance().deleteMemberRecord(member.getMemberId());
            if(deleted){
                memberInfoList.remove(member);
                CustomizeAlertMessages.showAlertInformationType("Record Deleted", "Record has been deleted",
                        String.format("%s %s has been deleted successfully", member.getFirstName(), member.getLastName()));
                return;
            }
        }
        CustomizeAlertMessages.showAlertInformationType("Deletion Operation Canceled", "Deletion operation has been canceled",
                "Deletion process was aborted");
    }

    // An event handler to handles editing of a member
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
    // A method to refresh member table
    @FXML
    public void refreshTable(){
        memberInfoList.clear();
        memberInfoList = FXCollections.observableArrayList(DataSource.getInstance().queryAllMembers());
        memberTable.setItems(memberInfoList);
    }
}
