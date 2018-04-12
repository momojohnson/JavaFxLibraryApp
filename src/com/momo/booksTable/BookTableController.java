package com.momo.booksTable;

import com.momo.Utils.Utils;
import com.momo.addbooks.AddBookController;
import com.momo.datamodel.Book;
import com.momo.datamodel.DataSource;
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


public class BookTableController {

    @FXML
    private TableView<Book> bookTable;
    private ObservableList<Book> books;

    public void initialize(){
        books = FXCollections.observableArrayList(DataSource.getInstance().queryAllBooks());
        bookTable.setItems(books);


    }

    @FXML
    private void deleteBook(){
        Book book = bookTable.getSelectionModel().getSelectedItem();
        if(book == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Deletion Error");
            alert.setHeaderText("You must select a book to delete");
            alert.setContentText("Please make sure to select a book before clicking delete.");
            alert.showAndWait();
            return;
        }
        boolean bookStatus = DataSource.getInstance().queryBookStatus(book.getBookIsbn());
        if(!bookStatus){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Book can't be deleted");
            alert1.setHeaderText("This book was issued");
            alert1.setContentText(String.format("You can't delete %s because it has been issued", book.getBookTitle()));
            alert1.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Confirm delete operation");
        alert.setContentText(String.format("Are you sure you want to delete %s with %s %s ", book.getBookTitle(), book.getAuthorFirstName(), book.getAuthorLastName()));
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK){
            String bookIsbn = book.getBookIsbn();
            int authorId = book.getAuthorId();
            boolean deleted = DataSource.getInstance().deleteBookAuthorAndAuthorIsbnRecord(bookIsbn, authorId);
            if(deleted) {
                books.remove(book);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Record Deleted");
                alert1.setHeaderText("Record has been deleted");
                alert1.setContentText(String.format("%s, with author %s %s has been deleted successfully", book.getBookTitle(), book.getAuthorFirstName(), book.getAuthorLastName()));
                alert1.showAndWait();
                return;
            }
        }
        Alert alertCancel= new Alert(Alert.AlertType.INFORMATION);
        alertCancel.setTitle("Delete Operation cancelled");
        alertCancel.setHeaderText("Delete operation was canceled");
        alertCancel.setContentText("Deletion operation canceled.");
        alertCancel.showAndWait();

    }
    @FXML

    private void editBook(){
        Book book = bookTable.getSelectionModel().getSelectedItem();
        if(book == null){
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setTitle("Selection Error");
            errorAlert.setHeaderText("You must select a book to edit");
            errorAlert.setContentText("Please select a record perform an edit operation");
            errorAlert.showAndWait();
            return;
        }
        Alert alertEditRecord = new Alert(Alert.AlertType.CONFIRMATION);
        alertEditRecord.setTitle("Edit Book Record");
        alertEditRecord.setHeaderText("Confirm edit book record.");
        alertEditRecord.setContentText(String.format("Are you want to edit %s with author %s %s", book.getBookTitle(), book.getAuthorFirstName(), book.getAuthorLastName()));
        Optional<ButtonType> response = alertEditRecord.showAndWait();
        if(response.get() == ButtonType.CANCEL){
            Alert cancelEdit = new Alert(Alert.AlertType.INFORMATION);
            cancelEdit.setTitle("Cancel Edit");
            cancelEdit.setHeaderText("Cancel edit operation");
            cancelEdit.setContentText("Edit operation has been canceled.");
            cancelEdit.showAndWait();
            return;
        }
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

    public void refreshBooks(){
        books.clear();
        books = FXCollections.observableArrayList(DataSource.getInstance().queryAllBooks());
        bookTable.setItems(books);

    }
    @FXML
    private void refreshTable(){
        books = FXCollections.observableArrayList(DataSource.getInstance().queryAllBooks());
        bookTable.setItems(books);

    }


}
