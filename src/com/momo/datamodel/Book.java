package com.momo.datamodel;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

public class Book {
    private SimpleStringProperty authorFirstName;
    private SimpleStringProperty authorLastName;
    private SimpleStringProperty bookTitle;
    private SimpleStringProperty bookPublisher;
    private SimpleStringProperty bookIsbn;
    private SimpleIntegerProperty bookEdition;
    private SimpleBooleanProperty bookAvailability;
    private SimpleIntegerProperty authorId;
    private String authorCity;
    private String authorState;
    private LocalDateTime authorDateOfBirth;
    private String copyRightYear;


    public Book(){
        this.authorFirstName = new SimpleStringProperty();
        this.authorLastName = new SimpleStringProperty();
        this.bookTitle = new SimpleStringProperty();
        this.bookPublisher = new SimpleStringProperty();
        this.bookIsbn = new SimpleStringProperty();
        this.bookEdition = new SimpleIntegerProperty();
        this.bookAvailability = new SimpleBooleanProperty();
        this.authorId = new SimpleIntegerProperty();


    }

    public String getAuthorFirstName() {
        return authorFirstName.get();
    }

    public SimpleStringProperty authorFirstNameProperty() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName.set(authorFirstName);
    }

    public String getAuthorLastName() {
        return authorLastName.get();
    }

    public SimpleStringProperty authorLastNameProperty() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName.set(authorLastName);
    }

    public String getBookTitle() {
        return bookTitle.get();
    }


    public void setBookTitle(String bookTitle) {
        this.bookTitle.set(bookTitle);
    }

    public String getBookPublisher() {
        return bookPublisher.get();
    }

    public SimpleStringProperty bookPublisherProperty() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher.set(bookPublisher);
    }

    public String getBookIsbn() {
        return bookIsbn.get();
    }

    public SimpleStringProperty bookIsbnProperty() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn.set(bookIsbn);
    }

    public int getBookEdition() {
        return bookEdition.get();
    }

    public SimpleIntegerProperty bookEditionProperty() {
        return bookEdition;
    }

    public void setBookEdition(int bookEdition) {
        this.bookEdition.set(bookEdition);
    }

    public boolean isBookAvailability() {
        return bookAvailability.get();
    }

    public SimpleBooleanProperty bookAvailabilityProperty() {
        return bookAvailability;
    }

    public void setBookAvailability(boolean bookAvailability) {
        this.bookAvailability.set(bookAvailability);
    }

    public SimpleStringProperty bookTitleProperty() {
        return bookTitle;
    }

    public int getAuthorId() {
        return authorId.get();
    }

    public SimpleIntegerProperty authorIdProperty() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId.set(authorId);
    }

    public String getAuthorCity() {
        return authorCity;
    }

    public void setAuthorCity(String authorCity) {
        this.authorCity = authorCity;
    }

    public String getAuthorState() {
        return authorState;
    }

    public void setAuthorState(String authorState) {
        this.authorState = authorState;
    }

    public LocalDateTime getAuthorDateOfBirth() {
        return authorDateOfBirth;
    }

    public void setAuthorDateOfBirth(LocalDateTime authorDateOfBirth) {
        this.authorDateOfBirth = authorDateOfBirth;
    }

    public String getCopyRightYear() {
        return copyRightYear;
    }

    public void setCopyRightYear(String copyRightYear) {
        this.copyRightYear = copyRightYear;
    }
}
