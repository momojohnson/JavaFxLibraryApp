package com.momo.mainwindow;

import com.jfoenix.controls.*;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import com.momo.Validator;
import com.momo.datamodel.Book;
import com.momo.datamodel.DataSource;
import com.momo.datamodel.Issue;
import com.momo.datamodel.Member;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;


public class MainController {
    @FXML
    private HBox bookInfo;
    @FXML
    private HBox memberInfo;
    @FXML
    private TextField bookISBN;
    @FXML
    private Text bookTitle;
    @FXML
    private Text bookAuthor;
    @FXML
    private Text bookAvailable;
    @FXML
    private TextField memberId;
    @FXML
    private Text memberName;
    @FXML
    private Text contactInfo;
    @FXML
    private Text address;
    @FXML
    private Label errorLabel;
    @FXML
    private JFXTextField bookIsbnNumber;
    @FXML
    private ObservableList<String> displayBookIssueInfo;
    @FXML
    private Text textMemeberName;
    @FXML
    private Text textMemberInfo;
    @FXML
    private Text textMemberAddress;
    @FXML
    private Text textBookAuthor;
    @FXML
    private Text textBookName;
    @FXML
    private Text textBookPublisher;
    @FXML
    private Text textIssueDate;
    @FXML
    private Text textBookOutDays;
    @FXML
    private Text textLiberaryFine;
    @FXML
    private JFXButton bookRenew;
    @FXML
    private JFXButton bookReturn;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private StackPane rootPane;
    @FXML
    private HBox submissionData;


    private DateTimeFormatter dateFormatter;


    public void initialize() {

        DataSource.getInstance().createLibraryTables();
        JFXDepthManager.setDepth(bookInfo, 1);
        JFXDepthManager.setDepth(memberInfo, 1);
        dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss a");
        displayBookIssueInfo = FXCollections.observableArrayList();
        loadToolBar();
    }


    @FXML
    private void loadBookInfo() {
        errorLabel.setVisible(false);
        bookTitle.setVisible(true);
        String bookIsbn = bookISBN.getText();
        Book bookInfo = DataSource.getInstance().getBookInfo(bookIsbn);
        if (bookInfo.getBookTitle() == null) {
            bookTitle.setText(String.format("We don't have ISBN %s in our library", bookIsbn));
            bookAuthor.setVisible(false);
            bookAvailable.setVisible(false);
            return;
        } else {
            bookAuthor.setVisible(true);
            bookAvailable.setVisible(true);

            bookTitle.setText(bookInfo.getBookTitle());
            bookAuthor.setText(String.format("%s %s", bookInfo.getAuthorFirstName(), bookInfo.getAuthorLastName()));
            if (bookInfo.isBookAvailability()) {
                bookAvailable.setText(String.format("Available: %s", "Yes"));
            } else {
                bookAvailable.setText(String.format("Available: %s ", "No"));

            }
        }


    }

    @FXML
    private void loadMemberInfo() {
        errorLabel.setVisible(false);
        memberName.setVisible(true);
        String availableMeberId = memberId.getText();
        Member memberInfo = DataSource.getInstance().getMemberInfo(availableMeberId);

        if (memberInfo.getFirstName() == null) {
            memberName.setText("This member id isn't registered.");
            contactInfo.setVisible(false);
            address.setVisible(false);
            return;
        }
        contactInfo.setVisible(true);
        address.setVisible(true);
        memberName.setText(String.format("%s %s", memberInfo.getFirstName(), memberInfo.getLastName()));
        contactInfo.setText(String.format("%s | %s", memberInfo.getPhoneNumber(), memberInfo.getEmail()));
        address.setText(String.format("%s", memberInfo.getStreetAddress()));

    }

    @FXML
    private void issueBook() {
        if (validateInputs()) {
            String availableMember = memberId.getText();
            System.out.println(availableMember);
            String bookIsnNumber = bookISBN.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Issue");
            alert.setHeaderText("Please confirm issue of book");
            alert.setContentText(String.format("Are you sure you to issue %s to %s", bookTitle.getText(), memberName.getText()));


            Optional<ButtonType> response = alert.showAndWait();
            if (response.get() == ButtonType.OK) {
                boolean bookStatus = DataSource.getInstance().queryBookStatus(bookIsnNumber);
                if (bookStatus) {
                    DataSource.getInstance().insertIntoIssueTable(bookIsnNumber, availableMember);
                    DataSource.getInstance().bookIssueAndReturn(bookIsnNumber, false);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Book Issue Successful");
                    alert1.setHeaderText("Book has been issued successfully");
                    alert1.setContentText(String.format("%s was issued to %s on %s", bookTitle.getText(), memberName.getText(),
                            dateFormatter.format(LocalDateTime.now()).toString()));
                    alert1.showAndWait();
                    return;
                } else {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Book has been issued");
                    alert1.setHeaderText("This book has already been issued");
                    alert1.setContentText(String.format("%s was issued ", bookTitle.getText()));
                    alert1.showAndWait();
                    return;
                }

            }

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Issue Operation canceled");
            alert1.setHeaderText("You canceled the issue operation");
            alert1.setContentText("No changes will be saved; you aborted issue operation");
            alert1.showAndWait();

        }


    }


    @FXML
    private void loadBookInfoTwo() {
        if (!validateIsbn()) {
            disableDialogControls("Please enter a valid Isbn Number");
            return;
        }

        Book bookInfo = DataSource.getInstance().getBookInfo(bookIsbnNumber.getText());
        Issue issueBook = DataSource.getInstance().issueInfo(bookIsbnNumber.getText());
        if (issueBook.getDateTime() == null) {
            disableDialogControls("This book hasn't been issued");

            return;
        }
        enableDialogControls();
        Member member = DataSource.getInstance().getMemberInfo(issueBook.getMemberId());
        textMemeberName.setText(String.format("%s %s", member.getFirstName(), member.getLastName()));
        textMemberAddress.setText(String.format("%s", member.getStreetAddress()));
        textMemberInfo.setText(String.format("%s | %s", member.getEmail(), member.getPhoneNumber()));
        displayBookIssueInfo.add("Book Information");
        textBookName.setText(String.format("%s", bookInfo.getBookTitle()));
        textBookAuthor.setText(String.format("%s %s", bookInfo.getAuthorFirstName(), bookInfo.getAuthorLastName()));
        textBookPublisher.setText(String.format("%s", bookInfo.getBookPublisher()));
        textIssueDate.setText(String.format("Issue date and Time: %s ", dateFormatter.format(issueBook.getDateTime())));
        LocalDate date = issueBook.getDateTime().toLocalDate();
        LocalDate date1 = LocalDateTime.now().toLocalDate();
        Long elapsedDays = ChronoUnit.DAYS.between(date, date1);
        textBookOutDays.setText(String.format("%d", elapsedDays));

    }

    @FXML
    private void returnBook() {
        if (!validateIsbn()) {
            disableDialogControls("Please enter a valid isbn Number");
            return;
        }
        String bookIsbn = bookIsbnNumber.getText();
        boolean bookStatus = DataSource.getInstance().queryBookStatus(bookIsbn);
        if (bookStatus) {
            disableDialogControls("This book has been returned");
            return;

        }
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Return Confirmation");
        alert1.setHeaderText("Confirm return operation");
        alert1.setContentText(String.format("Are you sure you want to return the book %s ?", textBookName.getText()));
        Optional<ButtonType> confirm = alert1.showAndWait();
        if (confirm.get() == ButtonType.OK) {
            DataSource.getInstance().bookIssueAndReturn(bookIsbn, true);
            DataSource.getInstance().deleteRecordFromIssuesTable(bookIsbn);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book return successful");
            alert.setHeaderText("Book return operation successful");
            alert.setContentText("The book was successfully return. Thanks you using our library");
            clearInputs();
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Return canceled");
        alert.setHeaderText("You canceled the return.");
        alert.setContentText("You have canceled the return operation. The book wasn't returned");
        alert.showAndWait();

    }

    @FXML
    private void renewBook() {
        if (!validateIsbn()) {
            disableDialogControls("Please enter a valid isbn Number");

            return;
        }
        boolean bookStatus = DataSource.getInstance().queryBookStatus(bookIsbnNumber.getText());
        if (!bookStatus) {
            enableDialogControls();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Renewal");
            alert.setHeaderText("Please confirm renewal");
            alert.setContentText("Are you sure you want to confirm renewal of this book?");
            Optional<ButtonType> response = alert.showAndWait();
            if (response.get() == ButtonType.OK) {
                DataSource.getInstance().updateBookRenewal(bookIsbnNumber.getText());
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Book Renewed");
                alert1.setHeaderText("Book has been renewed");
                alert1.setContentText("You request for book renewal has been process successfully");
                clearInputs();
                alert1.showAndWait();
                return;
            }
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Book Renewal Canceled");
            alert2.setHeaderText("Book Renewal has been canceled");
            alert2.setContentText("The book renewal operation has been canceled");
            alert2.showAndWait();

        } else {

            disableDialogControls("You can't renewed this book because it has been issued.");


        }
    }

    private boolean validateIsbn() {
        if (!Validator.bookIsbn(bookIsbnNumber)) {
            return false;
        }
        return true;
    }


    private boolean validateInputs() {
        errorLabel.setVisible(true);
        bookTitle.setVisible(false);
        memberName.setVisible(false);
        if (!Validator.bookIsbn(bookISBN)) {
            errorLabel.setText("Please enter a valid book ISBN Number");
            errorLabel.setFont(Font.font("Time News Roman", FontWeight.BOLD, 14));
            errorLabel.setTextFill(Color.RED);
            return false;
        }
        if (!Validator.validateMemberId(memberId)) {
            errorLabel.setText("Please enter valid member Id");
            errorLabel.setFont(Font.font("Time News Roman", FontWeight.BOLD, 14));
            errorLabel.setTextFill(Color.RED);
            bookTitle.setVisible(true);

            return false;
        }
        errorLabel.setVisible(false);
        bookTitle.setVisible(true);
        memberName.setVisible(false);
        return true;
    }

    @FXML
    private void menuClose() {
        Platform.exit();
    }

    @FXML
    private void loadFullScreen() {
        Stage stage = (Stage) bookISBN.getScene().getWindow();
        stage.setFullScreen(!stage.isFullScreen());
    }

    public void loadToolBar() {
        try {
            VBox loader = FXMLLoader.load(getClass().getResource("/com/momo/toolbar/toolbar.fxml"));
            drawer.setSidePane(loader);


        } catch (IOException exe) {
            System.out.println(exe.getMessage());
        }

        HamburgerSlideCloseTransition taskTransition = new HamburgerSlideCloseTransition(hamburger);
        taskTransition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                taskTransition.setRate(taskTransition.getRate() * -1);
                taskTransition.play();
                if(drawer.isHidden()){
                    drawer.open();
                }else {
                    drawer.close();
                }

            }
        });

    }

    private void clearInputs(){
        textBookOutDays.setText("No. of Days Book Out");
        textLiberaryFine.setText("Total Fine Amount");
        textIssueDate.setText("Date Issue");
        textBookPublisher.setText("Book Publisher");
        textBookAuthor.setText("Book Author");
        textBookName.setText("Book Title");
        textMemberInfo.setText("Contact Info");
        textMemberAddress.setText("Address");
        textMemeberName.setText("Member Name");
    }

    private void disableControls(boolean isEnable){
        if(!isEnable){
            bookRenew.setDisable(true);
            bookReturn.setDisable(true);
        }else {
            bookReturn.setDisable(false);
            bookRenew.setDisable(false);
        }
    }

    private void disableDialogControls(String text){
        JFXButton button = new JFXButton("Ok");
        JFXDialogLayout layout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(rootPane, layout, JFXDialog.DialogTransition.TOP);

        button.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> {

            dialog.close();

        });
        Label label = new Label(text);
        label.setFont(Font.font("Bernard MT Condensed", FontWeight.BOLD, 14));
        label.setStyle("-fx-text-fill: black");
        layout.setBody(label);

        layout.setActions(button);
        dialog.show();
        clearInputs();
        disableControls(false);
        bookIsbnNumber.setText("");
        submissionData.setOpacity(0);
    }
    private void enableDialogControls(){
        submissionData.setOpacity(1);
        disableControls(true);
    }

}




