package com.momo.addmembers;

import com.jfoenix.controls.JFXTextField;
import com.momo.datamodel.DataSource;
import com.momo.datamodel.Member;
import com.momo.utils.CustomizeAlertMessages;
import com.momo.utils.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AddMemberController {
    @FXML
    private JFXTextField txtFirstName; // text field for member first name
    @FXML
    private JFXTextField txtLastName; // text field for member last name
    @FXML
    private JFXTextField txtMemberId; // text field for member id
    @FXML
    private JFXTextField txtStreetAddress; // text field for member address
    @FXML
    private JFXTextField txtCity; // text field for member city
    @FXML
    private JFXTextField txtState; // text field for member state
    @FXML
    private JFXTextField txtZipCode; // text field for member zip code
    @FXML
    private JFXTextField txtPhone; // text field for member phone number
    @FXML
    private JFXTextField txtEmail; // text field for member email

    @FXML
    private Label lblError;
    @FXML
    private AnchorPane anchorPane; // An anchor hat contain inputs uis
    private boolean isUpdated = false;
    private int streetId;

    public void initialize(){

    }

    // An event handler that adds a member to the database
    @FXML
    public void addMember(){

        if(isValidData()){

            if(isUpdated){
                DataSource.getInstance().updateMemberRecord(txtFirstName.getText(), txtLastName.getText(), txtPhone.getText(), txtEmail.getText(),
                        txtMemberId.getText());
                DataSource.getInstance().updateAddressRecord(txtStreetAddress.getText(), txtCity.getText(), txtState.getText(), txtZipCode.getText(), streetId);
                clearInputs(txtFirstName, txtLastName, txtPhone, txtEmail, txtMemberId, txtStreetAddress, txtCity, txtState, txtZipCode);
                return;
            }
            String memberId = txtMemberId.getText();
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String streetAddress = txtStreetAddress.getText();
            String city = txtCity.getText();
            String state = txtState.getText();
            String zipCode = txtZipCode.getText();
            String phoneNumber = txtPhone.getText();
            String email = txtEmail.getText();
            boolean memberIsRegistered = DataSource.getInstance().insertRecordIntoMemberAndAddressTable(
                    memberId, firstName, lastName, phoneNumber, email, streetAddress, city, state, zipCode);
            System.out.println(memberIsRegistered);
            if(!memberIsRegistered){
                CustomizeAlertMessages.showAlertInformationType("Member Exist", "This member is already registered",
                        "This member has an account in our library. They can't be added.");
                clearInputs(txtFirstName, txtLastName, txtMemberId, txtStreetAddress, txtCity, txtState, txtEmail, txtZipCode, txtPhone);
                txtFirstName.clear();
                txtLastName.clear();
                txtMemberId.clear();
                txtStreetAddress.clear();
                txtCity.clear();
                txtState.clear();
                txtEmail.clear();
                txtZipCode.clear();
                txtPhone.clear();
                return;

            }

            CustomizeAlertMessages.showAlertInformationType("Record Saved", "Member record has been saved",
                    "Member record has been saved successfully");
            clearInputs(txtFirstName, txtLastName, txtMemberId, txtStreetAddress,txtCity, txtState,txtEmail,txtZipCode, txtPhone);

        }


    }

    // An event handler that handles cancel button. It closes the current opened window
    @FXML
    public void cancelSaving(){
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    // Input validations method
    private boolean isValidData(){
        if(!Validator.validateFirstName(txtFirstName)){
            txtFirstName.clear();
            txtFirstName.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("First Name is required field");
            return false;

        }
        if(!Validator.validateLastName(txtLastName)){
            txtLastName.clear();
            txtLastName.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("Last name is a required field");
            return false;
        }
        if(!Validator.validateMemberId(txtMemberId)){

            txtMemberId.clear();
            txtMemberId.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("Member Id is a required field");
            return false;
        }
        if(!Validator.validateStreetAddress(txtStreetAddress)){
            txtStreetAddress.clear();
            txtStreetAddress.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("Street address is a required field");
            return false;
        }
        if(!Validator.validateCity(txtCity)){
            txtCity.clear();
            txtCity.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("City is a required field");
            return false;
        }
        if(!Validator.validateState(txtState)){
            txtState.clear();
            txtState.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("State is a required fiedl");
            return false;
        }
        if(!Validator.validateZipCode(txtZipCode)){
            txtZipCode.clear();
            txtZipCode.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("Zip code is a required field. Must be 5 digits");
            return false;
        }
        if(!Validator.validatePhoneNumber(txtPhone)){
            txtPhone.clear();
            txtPhone.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("Please enter a valid USA phone number");
            return false;
        }
        if(!Validator.validateEmail(txtEmail)){
            txtEmail.clear();
            txtEmail.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("Please enter a valid email");
            return false;
        }
        lblError.setFont(Font.font("Times New Roman Bold", FontWeight.BOLD, 12));
        lblError.setTextFill(Color.BLACK);
        lblError.setText("Member's Information");
        return true;
    }

    // Clear inputs text that was entered
    private void clearInputs(TextInputControl control1, TextInputControl control2, TextInputControl control3,
                             TextInputControl control4, TextInputControl control5, TextInputControl control6,
                             TextInputControl control7, TextInputControl control8, TextInputControl control9)
    {
        control1.clear();
        control2.clear();
        control3.clear();
        control4.clear();
        control5.clear();
        control6.clear();
        control7.clear();
        control8.clear();
        control9.clear();
    }

// Sets Uis value in various inputs
    public void setUiValues(Member member){
        txtMemberId.setText(member.getMemberId());
        txtFirstName.setText(member.getFirstName());
        txtLastName.setText(member.getLastName());
        txtPhone.setText(member.getPhoneNumber());
        txtEmail.setText(member.getEmail());
        txtStreetAddress.setText(member.getStreetAddress());
        txtCity.setText(member.getCity());
        txtState.setText(member.getState());
        txtZipCode.setText(member.getZipCode());
        streetId = member.getId();
        isUpdated = true;


    }

}
