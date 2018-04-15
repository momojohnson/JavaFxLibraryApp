package com.momo.mainwindow;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import com.momo.datamodel.Book;
import com.momo.datamodel.DataSource;
import com.momo.datamodel.Issue;
import com.momo.datamodel.Member;
import com.momo.utils.CustomizeAlertMessages;
import com.momo.utils.Validator;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Optional;


public class MainController {
    @FXML
    private HBox bookInfo;// An HBox that contains book information
    @FXML
    private HBox memberInfo; // An HBox that contains member information
    @FXML
    private TextField bookISBN; // Text Field to enter book Isbn number
    @FXML
    private Text txtBookTitle; // Text property to display book title
    @FXML
    private Text txtAuthorFirstAndLastName; // Text property to display author first and last name
    @FXML
    private Text txtBookAvailability; // Text property to display book availability information
    @FXML
    private TextField memberId; // Text field for member id
    @FXML

    private Text memberName; // Text property to show member name
    @FXML
    private Text txtMemberEmailAndPhoneNumber; // Text property to show member email and phone number
    @FXML
    private Text address; // Text property to show member address informaton

    @FXML
    private JFXTextField bookIsbnNumber; // Book Isbn number Textfield
    @FXML
    private ObservableList<String> displayBookIssueInfo; // An observable list to display book and member information within the book renewal tab
    @FXML
    private Text txtMemberFirstAndLastName; // Text property for member first and last name
    @FXML
    private Text textMemberInfo; // text property for member information
    @FXML
    private Text textMemberAddress; // text property for member address
    @FXML
    private Text txtBookAuthorName; // text property for book author name
    @FXML
    private Text txtBookTitleHBox; // Text property to display book Title in HBok with the renewal tab session
    @FXML
    private Text txtBookPublisher; // Text property that displays book publisher information
    @FXML
    private Text txtIssueDate; // Text property that displays issue date of a book to a member
    @FXML
    private Text txtDaysBookIsOut; // Text property that displays number of days a book is out
    @FXML
    private Text txtLibraryFineAmount; // Text property and display Library fine amount
    @FXML
    private JFXButton btnBookRenew; // A book renew button
    @FXML
    private JFXButton btnBookReturn; // A book return button
    @FXML
    private JFXHamburger hamburger; // Hamburger icon
    @FXML
    private JFXDrawer drawer; // Drawer for the hamburger
    @FXML
    private StackPane rootPane; // Main window of the application
    @FXML
    private HBox submissionData; // HBox the shows information before submission of a book is done.
    @FXML
    private BorderPane borderPane; // Borderpane layout
    @FXML
    private JFXButton btnBookIssue; // issue button


    private DateTimeFormatter dateFormatter;


    public void initialize() {

        DataSource.getInstance().createLibraryTables(); // Create all tables upon program initialization
        JFXDepthManager.setDepth(bookInfo, 1);
        JFXDepthManager.setDepth(memberInfo, 1);
        dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss a");
        displayBookIssueInfo = FXCollections.observableArrayList();
        loadToolBar();
    }


    // Load book information
    @FXML
    private void loadBookInfo() {
        String bookIsbn = bookISBN.getText();
        Book bookInfo = DataSource.getInstance().getBookInfo(bookIsbn);
        if (bookInfo.getBookTitle() == null) {
            CustomizeAlertMessages.showMaterialDialog(rootPane, borderPane, "button-style", Arrays.asList(new JFXButton("Okay")),
                    "ISBN number doesn't exist in our Library" );
            txtAuthorFirstAndLastName.setVisible(false);
            txtBookAvailability.setVisible(false);
            btnBookIssue.setDisable(true);
            return;
        } else {
            txtAuthorFirstAndLastName.setVisible(true);
            txtBookAvailability.setVisible(true);
            txtBookTitle.setVisible(true);
            btnBookIssue.setDisable(false);

            txtBookTitle.setText(bookInfo.getBookTitle());
            txtAuthorFirstAndLastName.setText(String.format("%s %s", bookInfo.getAuthorFirstName(), bookInfo.getAuthorLastName()));
            if (bookInfo.isBookAvailability()) {
                txtBookAvailability.setText(String.format("Available: %s", "Yes"));
            } else {
                txtBookAvailability.setText(String.format("Available: %s ", "No"));
                btnBookIssue.setDisable(true);

            }
        }


    }

    // Load member information event method
    @FXML
    private void loadMemberInfo() {
        String availableMemberId = memberId.getText();
        Member memberInfo = DataSource.getInstance().getMemberInfo(availableMemberId);

        if (memberInfo.getFirstName() == null) {
            CustomizeAlertMessages.showMaterialDialog(rootPane, borderPane, "button-style", Arrays.asList(new JFXButton("Okay")), "This member Id doesn't exist");
            txtMemberEmailAndPhoneNumber.setVisible(false);
            address.setVisible(false);
            memberName.setVisible(false);
            btnBookIssue.setDisable(true);
            return;
        }
        txtMemberEmailAndPhoneNumber.setVisible(true);
        address.setVisible(true);
        btnBookIssue.setDisable(false);
        memberName.setText(String.format("%s %s", memberInfo.getFirstName(), memberInfo.getLastName()));
        txtMemberEmailAndPhoneNumber.setText(String.format("%s | %s", memberInfo.getPhoneNumber(), memberInfo.getEmail()));
        address.setText(String.format("%s", memberInfo.getStreetAddress()));
        memberName.setVisible(true);
    }

    // Issue a book event
    @FXML
    private void issueBook() {
        if (validateInputs()) {
            String availableMember = memberId.getText();
            System.out.println(availableMember);
            String bookIsnNumber = bookISBN.getText();
            Optional<ButtonType> response = CustomizeAlertMessages.showAlertInformationType("Confirmed Issue", "Please confirm issue book request", String.format("Are you sure you to issue %s to %s", txtBookTitle.getText(), memberName.getText()));
            if (response.get() == ButtonType.OK) {
                boolean bookStatus = DataSource.getInstance().queryBookStatus(bookIsnNumber);
                if (bookStatus) {
                    DataSource.getInstance().insertIntoIssueTable(bookIsnNumber, availableMember);
                    DataSource.getInstance().bookIssueAndReturn(bookIsnNumber, false);
                    CustomizeAlertMessages.showAlertInformationType("Book Issue Successful", "Book has been issued successfully",
                            String.format("%s was issued to %s on %s", txtBookTitle.getText(), memberName.getText(),
                                    dateFormatter.format(LocalDateTime.now()).toString()));
                    return;
                } else {
                    CustomizeAlertMessages.showAlertInformationType("Book has been issued", "This book has already been issued", String.format("%s was issued ", txtBookTitle.getText()));
                    return;
                }

            }
            CustomizeAlertMessages.showAlertInformationType("Issue Operation canceled", "You canceled the issue operation", "No changes will be saved; you have aborted issue operation");

        }


    }


    // Load book info event method two
    @FXML
    private void loadBookInfoTwo() {
        if (!validateIsbn()) {
            CustomizeAlertMessages.showMaterialDialog(rootPane, borderPane, "button-style", Arrays.asList(new JFXButton("Okay")),
                    "Please enter a valid Isbn number");
            disableControls(false);

            return;
        }

        Book bookInfo = DataSource.getInstance().getBookInfo(bookIsbnNumber.getText());
        Issue issueBook = DataSource.getInstance().issueInfo(bookIsbnNumber.getText());
        if (issueBook.getDateTime() == null) {
            CustomizeAlertMessages.showMaterialDialog(rootPane, borderPane, "button-style", Arrays.asList(new JFXButton("Okay")),
                    "This book hasn't been issued");
            disableControls(true);

            return;
        }
        enableDialogControls();
        Member member = DataSource.getInstance().getMemberInfo(issueBook.getMemberId());
        txtMemberFirstAndLastName.setText(String.format("%s %s", member.getFirstName(), member.getLastName()));
        textMemberAddress.setText(String.format("%s", member.getStreetAddress()));
        textMemberInfo.setText(String.format("%s | %s", member.getEmail(), member.getPhoneNumber()));
        displayBookIssueInfo.add("Book Information");
        txtBookTitleHBox.setText(String.format("%s", bookInfo.getBookTitle()));
        txtBookAuthorName.setText(String.format("%s %s", bookInfo.getAuthorFirstName(), bookInfo.getAuthorLastName()));
        txtBookPublisher.setText(String.format("%s", bookInfo.getBookPublisher()));
        txtIssueDate.setText(String.format("Issue date and Time: %s ", dateFormatter.format(issueBook.getDateTime())));
        LocalDate date = issueBook.getDateTime().toLocalDate();
        LocalDate date1 = LocalDateTime.now().toLocalDate();
        Long elapsedDays = ChronoUnit.DAYS.between(date, date1);
        txtDaysBookIsOut.setText(String.format("%d", elapsedDays));

    }

    // Return book event method upon return button clicked
    @FXML
    private void returnBook() {
        if (!validateIsbn()) {
            CustomizeAlertMessages.showMaterialDialog(rootPane, borderPane, "button-style", Arrays.asList(new JFXButton("Okay")),
                    "Please enter a valid isbn number");
            return;
        }
        String bookIsbn = bookIsbnNumber.getText();
        boolean bookStatus = DataSource.getInstance().queryBookStatus(bookIsbn);
        if (bookStatus) {
            CustomizeAlertMessages.showMaterialDialog(rootPane, borderPane, "button-style", Arrays.asList(new JFXButton("Okay")),
                    "This book has been returned");
            return;

        }
        Optional<ButtonType> confirm = CustomizeAlertMessages.showAlertInformationType("Return Confirmation", "Confirm return operation",
                String.format("Are you sure you want to return the book %s ?", txtBookTitleHBox.getText()));
        if (confirm.get() == ButtonType.OK) {
            DataSource.getInstance().bookIssueAndReturn(bookIsbn, true);
            DataSource.getInstance().deleteRecordFromIssuesTable(bookIsbn);
            CustomizeAlertMessages.showAlertInformationType("Book Return Successful", "Book return operation successful",
                    "The book has been return successfully. Thanks you for using our library");
            return;
        }
        CustomizeAlertMessages.showAlertInformationType("Returned Book Canceled", "You have canceled the return book operation",
                "You have canceled the book return operation. The book wasn't returned");
    }

    // Renewal book event method upon renew button clicked
    @FXML
    private void renewBook() {
        if (!validateIsbn()) {
            CustomizeAlertMessages.showMaterialDialog(rootPane, borderPane, "button-style", Arrays.asList(new JFXButton("Okay")),
                    "Please enter a valid isbn number");

            return;
        }
        boolean bookStatus = DataSource.getInstance().queryBookStatus(bookIsbnNumber.getText());
        if (!bookStatus) {
            enableDialogControls();
            Optional<ButtonType> response = CustomizeAlertMessages.showAlertInformationType("Confirm Renewal", "Please confirm renewal",
                    "Are you sure you want to confirm renewal of this book?");
            if (response.get() == ButtonType.OK) {
                DataSource.getInstance().updateBookRenewal(bookIsbnNumber.getText());
                CustomizeAlertMessages.showAlertInformationType("Book Renewal", "Book has been renewed",
                        "Your request for the book renewal has been process successfully");
                clearInputs();
                return;
            }
            CustomizeAlertMessages.showAlertInformationType("Book Renewal Canceled", "Book renewal has been canceled",
                    "The book renewal operation has been canceled");


        } else {

            CustomizeAlertMessages.showMaterialDialog(rootPane, borderPane, "button-style", Arrays.asList(new JFXButton("Okay")),
                    "You can't renewed this book because it has been issued to another member.");



        }
    }

    // Validates book Isbn number
    private boolean validateIsbn() {
        if (!Validator.bookIsbn(bookIsbnNumber)) {
            return false;
        }
        return true;
    }


    // Clears bookIsbn inputs
    private boolean validateInputs() {
        if (!Validator.bookIsbn(bookISBN)) {
            CustomizeAlertMessages.showMaterialDialog(rootPane, borderPane, "button-style", Arrays.asList(new JFXButton("Okay")),
                    "Please enter a valid book Isbn number." );
            return false;
        }
        if (!Validator.validateMemberId(memberId)) {
            CustomizeAlertMessages.showMaterialDialog(rootPane, borderPane, "button-style", Arrays.asList(new JFXButton("Okay")), "Please enter valid member Id");

            return false;
        }

        return true;
    }

    // Close the main window when the close on the window menu is clicked
    @FXML
    private void menuClose() {
        Platform.exit();
    }

    // Make a the window to be a full screen
    @FXML
    private void loadFullScreen() {
        Stage stage = (Stage) bookISBN.getScene().getWindow();
        stage.setFullScreen(!stage.isFullScreen());
    }

    // Loads the toolbar on the right of the hamburger when the hamburger is clicked.
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

        // Clears Uis inputs
    private void clearInputs(){
        txtDaysBookIsOut.setText("No. of Days Book Out");
        txtLibraryFineAmount.setText("Total Fine Amount");
        txtIssueDate.setText("Date Issue");
        txtBookPublisher.setText("Book Publisher");
        txtBookAuthorName.setText("Book Author");
        txtBookTitleHBox.setText("Book Title");
        textMemberInfo.setText("Contact Info");
        textMemberAddress.setText("Address");
        txtMemberFirstAndLastName.setText("Member Name");
    }
    // Disable controls when the input enter isn't valid
    private void disableControls(boolean isEnable){
        if(!isEnable){
            btnBookRenew.setDisable(true);
            btnBookReturn.setDisable(true);
            submissionData.setOpacity(0);
        }else {
            btnBookReturn.setDisable(false);
            btnBookRenew.setDisable(false);


        }
    }

    // Enable dialog controls
    private void enableDialogControls(){
        submissionData.setOpacity(1);
        disableControls(true);
    }

}




