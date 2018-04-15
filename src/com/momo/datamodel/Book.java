package com.momo.datamodel;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

public class Book {
    private SimpleStringProperty authorFirstName; // Author first name
    private SimpleStringProperty authorLastName; // author last name
    private SimpleStringProperty bookTitle; // book title
    private SimpleStringProperty bookPublisher; // publisher name
    private SimpleStringProperty bookIsbn; // book isbn
    private SimpleIntegerProperty bookEdition; // Book edition
    private SimpleBooleanProperty bookAvailability; // book availability
    private SimpleIntegerProperty authorId; // author id
    private String authorCity; // author city
    private String authorState; // author state
    private LocalDateTime authorDateOfBirth; // author date of birth
    private String copyRightYear; // book copyright year

// Constructor
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

    //return author first name
    public String getAuthorFirstName() {
        return authorFirstName.get();
    }


    // sets author first name
    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName.set(authorFirstName);
    }

    // return author last name
    public String getAuthorLastName() {
        return authorLastName.get();
    }


    // sets author last name
    public void setAuthorLastName(String authorLastName) {
        this.authorLastName.set(authorLastName);
    }

    // return book title
    public String getBookTitle() {
        return bookTitle.get();
    }

    // sets book title
    public void setBookTitle(String bookTitle) {
        this.bookTitle.set(bookTitle);
    }

    // return book publisher
    public String getBookPublisher() {
        return bookPublisher.get();
    }

    // set Book publisher
    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher.set(bookPublisher);
    }

    // return book isbn
    public String getBookIsbn() {
        return bookIsbn.get();
    }

    // sets book isbn
    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn.set(bookIsbn);
    }


    // return book edition
    public int getBookEdition() {
        return bookEdition.get();
    }

    // set books edition
    public void setBookEdition(int bookEdition) {
        this.bookEdition.set(bookEdition);
    }

    // return book availability
    public boolean isBookAvailability() {
        return bookAvailability.get();
    }

    //set books availability
    public void setBookAvailability(boolean bookAvailability) {
        this.bookAvailability.set(bookAvailability);
    }

   // return author id
    public int getAuthorId() {
        return authorId.get();
    }

    // set author id
    public void setAuthorId(int authorId) {
        this.authorId.set(authorId);
    }

    // return author city
    public String getAuthorCity() {
        return authorCity;
    }
    // sets author city
    public void setAuthorCity(String authorCity) {
        this.authorCity = authorCity;
    }

    // return author state
    public String getAuthorState() {
        return authorState;
    }
    // set author state
    public void setAuthorState(String authorState) {
        this.authorState = authorState;
    }

    // return author date of birth
    public LocalDateTime getAuthorDateOfBirth() {
        return authorDateOfBirth;
    }

    // set authors date of birth
    public void setAuthorDateOfBirth(LocalDateTime authorDateOfBirth) {
        this.authorDateOfBirth = authorDateOfBirth;
    }

    // return copyright year
    public String getCopyRightYear() {
        return copyRightYear;
    }
    // set copyright year
    public void setCopyRightYear(String copyRightYear) {
        this.copyRightYear = copyRightYear;
    }
}
