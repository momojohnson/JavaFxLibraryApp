package com.momo.addbooks;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.momo.datamodel.Book;
import com.momo.datamodel.DataSource;
import com.momo.utils.CustomizeAlertMessages;
import com.momo.utils.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddBookController {
    @FXML
    private JFXTextField authorFirstName; // Author's first name
    @FXML
    private JFXTextField authorLastName; // Author's last name
    @FXML
    private JFXTextField authorCity; // Author's city
    @FXML
    private JFXTextField authorState; // Author's State
    @FXML
    private JFXDatePicker authorDateOfBirth; // Author's date of birth
    @FXML
    private JFXTextField bookTitle; // Book title
    @FXML
    private JFXTextField copyRightYear; // Copyright year
    @FXML
    private JFXTextField bookPublisher; // Book publisher
    @FXML
    private JFXTextField bookIsbn; // Book isbn number
    @FXML
    private JFXTextField bookEdition; // Book edition number
    @FXML
    private Label lblError; // Label to display error;

    private boolean isUpdatable = false; // Check if the book is updatable
    @FXML
    private AnchorPane anchorPane; // An anchorpane for the add books

    @FXML
    private DataSource dataSource; // Data source instance of database class
    private DateTimeFormatter formatter;

    private int authorID; // Author Id


    public void initialize(){
        // Get an instance of the datasource class and create all tables when the program starts. If tables are already
        // Call to creating tables is ignore
        dataSource = DataSource.getInstance();
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");



    }

    // An event handler that handles saving data to database
    @FXML
    public void saveBookToDatabase(){


        if(validData()){
            LocalDate birthDate = authorDateOfBirth.getValue();
            // A condition to update book information
            if(isUpdatable){
                DataSource.getInstance().updateAuthorRecord(authorFirstName.getText(), authorLastName.getText(), authorCity.getText(),
                       authorState.getText(), birthDate, authorID);
                DataSource.getInstance().updateBookRecord(bookTitle.getText(), Integer.parseInt(bookEdition.getText()), bookPublisher.getText(), Integer.parseInt(copyRightYear.getText()),
                        bookIsbn.getText());
                CustomizeAlertMessages.showAlertInformationType("Book Updated", String.format("Book record updated"), "Book has been updated successfully");

                return;

            }
            String firstName = authorFirstName.getText();
            String lastName = authorLastName.getText();
            String city = authorCity.getText();
            String state = authorState.getText();
            LocalDate birthDateAuthor = authorDateOfBirth.getValue();
            String title = bookTitle.getText();
            String copyRight = copyRightYear.getText();
            String publisher = bookPublisher.getText();
            String isbn = bookIsbn.getText();
            int edition = Integer.parseInt(bookEdition.getText());
            Date date = Date.valueOf(birthDateAuthor.toString());
            boolean isBookSave = dataSource.insertRecordIntoAuthorIsbnTable(firstName, lastName, date, city, state,
                    isbn, title,edition,publisher,copyRight,true);

            // This is new book. Add the book to the database
            if(!isBookSave){
                CustomizeAlertMessages.showAlertInformationType("Book information saved", "Book information saved",
                        "Book information has been saved successfully");
                return;

            }
            // Informed that user that the book does exist in the database. Therefore the book will not be added.
            CustomizeAlertMessages.showAlertInformationType("Book Exist", "This book already exist in our database", "This book has already been saved.");

        }

    }

    // Cancel the save operation
    @FXML
    public void cancelSave(){
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();

    }


    // Validate various input control to make sure they contain valid entry. Using method from validator class
    private boolean validData(){
        if(!Validator.validateFirstName(authorFirstName)){
            authorFirstName.clear();
            authorFirstName.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("First name required");
            return false;

        }
        if(!Validator.validateLastName(authorLastName)){
            authorLastName.clear();
            authorLastName.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("Last name required field");
            return false;

        }
        if(!Validator.validateCity(authorCity)){
            authorCity.clear();
            authorCity.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("City Field is required");
            return false;

        }
        if(!Validator.validateState(authorState)){
            authorState.clear();
            authorState.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("State Field is required");
            return false;


        }
        if(!Validator.validateDate(authorDateOfBirth)){
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("Birth Date is required");
            return false;
        }
        if(!Validator.validateBookTitle(bookTitle)){
            bookTitle.clear();
            bookTitle.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("Book Title is required");
            return false;
        }
        if(!Validator.validateCopyRightYear(copyRightYear)){
            copyRightYear.clear();
            copyRightYear.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("Copyright year is required");
            return false;

        }

        if(!Validator.validateBookTitle(bookPublisher)){
            bookPublisher.clear();
            bookPublisher.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("Publisher is required");
            return false;
        }
        if(!Validator.bookIsbn(bookIsbn)){
            bookIsbn.clear();
            bookIsbn.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("Book Isbn is required");
            return false;

        }
        if(!Validator.validateBookEdition(bookEdition)){
            bookEdition.clear();
            bookEdition.requestFocus();
            lblError.setFont(Font.font("Time New Roman", 12));
            lblError.setTextFill(Color.RED);
            lblError.setText("Book edition is required");
            return false;

        }

        return true;
    }

    // Set books value in various inputs
    public void setBookUis(Book books){
        authorFirstName.setText(books.getAuthorFirstName());
        authorLastName.setText(books.getAuthorLastName());
        authorCity.setText(books.getAuthorCity());
        authorState.setText(books.getAuthorState());
        authorDateOfBirth.setValue(books.getAuthorDateOfBirth().toLocalDate());
        bookTitle.setText(books.getBookTitle());
        copyRightYear.setText(books.getCopyRightYear());
        bookPublisher.setText(books.getBookPublisher());
        bookIsbn.setText(books.getBookIsbn());
        bookEdition.setText(String.format("%d", books.getBookEdition()));
        bookIsbn.setEditable(false);
        isUpdatable = true;
        authorID = DataSource.getInstance().getAuthorID(authorFirstName.getText(), authorLastName.getText(), authorCity.getText());

    }

}
