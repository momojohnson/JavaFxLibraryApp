package com.momo.booksTable;

import com.momo.addbooks.AddBookController;
import com.momo.datamodel.Book;
import com.momo.datamodel.DataSource;
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


public class BookTableController {

    @FXML
    private TableView<Book> bookTable; // A table view for the books
    private ObservableList<Book> books; // Observable list for books. It is used to set books items into book table view

    public void initialize(){
        books = FXCollections.observableArrayList(DataSource.getInstance().queryAllBooks());
        bookTable.setItems(books);


    }

    // An event handler to perform deletion of books
    @FXML
    private void deleteBook(){
        Book book = bookTable.getSelectionModel().getSelectedItem();
        if(book == null){
            CustomizeAlertMessages.showAlertErrorType("Deletion Error", "You must select a book to delete",
                    "Please make sure to select a book before clicking delete");
            return;
        }
        boolean bookStatus = DataSource.getInstance().queryBookStatus(book.getBookIsbn());
        if(!bookStatus){
            CustomizeAlertMessages.showAlertInformationType("Book can't be deleted", "This book was issued",
                    String.format("You can't delete %s because it has been issued", book.getBookTitle()));
            return;
        }
        Optional<ButtonType> response = CustomizeAlertMessages.showAlertInformationType("Delete Confirmation", "Confirm delete operation",
                String.format("Are you sure you want to delete %s with %s %s ", book.getBookTitle(), book.getAuthorFirstName(), book.getAuthorLastName()));
        if(response.get() == ButtonType.OK){
            String bookIsbn = book.getBookIsbn();
            int authorId = book.getAuthorId();
            boolean deleted = DataSource.getInstance().deleteBookAuthorAndAuthorIsbnRecord(bookIsbn, authorId);
            if(deleted) {
                books.remove(book);
                CustomizeAlertMessages.showAlertInformationType("Record Deleted", "Record has been deleted",
                        String.format("%s, with author %s %s has been deleted successfully", book.getBookTitle(), book.getAuthorFirstName(), book.getAuthorLastName()));
                return;
            }
        }
        CustomizeAlertMessages.showAlertInformationType("Delete operation canceled", "Delete operation has been canceled",
                "Deletion operation has been aborted");

    }

    // An event handler to edit book when edit is clicked on the context menu
    @FXML
    private void editBook(){
        Book book = bookTable.getSelectionModel().getSelectedItem();
        if(book == null){
            CustomizeAlertMessages.showAlertInformationType("Selection Error", "You must select a book for editing",
                    "Please select a record to perform an edit operation");
            return;
        }
        Optional<ButtonType> response = CustomizeAlertMessages.showAlertInformationType("Edit Book Record", "Confirm edit book record",
                String.format("Are you want to edit %s with author %s %s", book.getBookTitle(), book.getAuthorFirstName(), book.getAuthorLastName()));
        if(response.get() == ButtonType.CANCEL){

            CustomizeAlertMessages.showAlertInformationType("Canceled Edit", "You have canceled edit operation",
                    "Edit operation for this record has been canceled");
            return;
        }
        // Loads add book fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/momo/addbooks/addBook.fxml"));
        try {
            Parent parent = loader.load();
            AddBookController controller =  loader.getController();
            controller.setBookUis(book);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image(Utils.TITLE_IMAGE_URL));
            stage.setTitle("Edit Book");
            stage.setScene(new Scene(parent, 600, 393));
            stage.setResizable(false);
            stage.show();
            stage.setOnCloseRequest((event ->{
                refreshBooks();
            }));


        }catch (IOException exe){

        }
    }
    // A method to refresh and populate data on the table from the database
    private void refreshBooks(){
        books.clear();
        books = FXCollections.observableArrayList(DataSource.getInstance().queryAllBooks());
        bookTable.setItems(books);

    }

    // An event handler that refreshes the table
    @FXML
    private void refreshTable(){
        books = FXCollections.observableArrayList(DataSource.getInstance().queryAllBooks());
        bookTable.setItems(books);

    }


}
