package com.momo.datamodel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    // Create author table prepared statement
    private PreparedStatement createAuthorTable;
    // Create Title table prepared statement variable
    private PreparedStatement createTitleTable;
    // Create Author Isbn prepared statement variable
    private PreparedStatement createAuthorIsbnTable; // prepared statement to create author isbn table
    private PreparedStatement createMembersTable; // prepared statement to create members table variable
    private PreparedStatement createAddressTable; // prepared statement to create address table
    private PreparedStatement createIssueTable; // prepared statement to create issues table
    private PreparedStatement insertIntoAuthors; // prepared statement to insert record into author table
    private  PreparedStatement queryAuthorIsbnTable; // prepared statement to query authorIsbn table;
    private PreparedStatement insertIntoBook; // prepared statement to insert into the book table
    private PreparedStatement queryBooksTable; // prepared statement to query books table
    private PreparedStatement insertIntoAuthorIsbn; // prepared statement to insert  record into authorisbn table
    private PreparedStatement queryIsbnForBook; // prepared statement to query isbn for book
    private PreparedStatement queryAllBooks; // prepared statement to query all books
    private PreparedStatement insertIntoMemberTable; // prepared statement that insert record into member table
    private PreparedStatement insertIntoAddressTable; // prepared statement that insert record into address table
    private PreparedStatement queryMemberId; // prepared statement to query member id
    private PreparedStatement queryAllMembers; // prepared statement to query all members from member table
    private PreparedStatement queryBookInfo; // prepared statement to query book info
    private PreparedStatement queryMemberInfo; // prepared statement to query member nfo
    private PreparedStatement insertIntoIssueTable; // prepared statement to insert into issue table
    private PreparedStatement updateBookAvailability; // prepared statement to update book availability
    private PreparedStatement queryBookStatus; // prepared statement to update book status.
    private PreparedStatement queryIssueTable; // Prepared statement to for specific columns
    private PreparedStatement queryBookAvailability; // prepared statement to query book availability
    private PreparedStatement deleteRecordFromIssuesTable; // prepared that to delete record from issue table
    private PreparedStatement updateBookRenewal; // prepared statement to update book renewal info
    private PreparedStatement deleteBookRecord; // prepared statement variable to delete book record
    private PreparedStatement deleteAuthorRecord; // prepared statement variable to delete author record
    private PreparedStatement deleteBookIsbnAuthorID; // prepared statement variable to delete record from authorisbn table
    private PreparedStatement deleteMemberRecord; // prepared statement variable to delete record from Member table
    private PreparedStatement updateBookRecord; // prepared statement to update book record from the book table
    private PreparedStatement updateAuthorRecord; // prepared statement to update author record in the author table
    private PreparedStatement queryAuthorID; // prepared statement to query authorID
    private PreparedStatement updateMemberRecord; // prepared statement to update member record
    private PreparedStatement updateAddressRecord; // prepared statement to update an address record

    // Column names for author table
    public static final String AUTHOR_TABLE = "authors";
    public static final String COLUMN_AUTHOR_AUTHOR_ID = "authorId";
    public static final String COLUMN_AUTHOR_FIRST_NAME = "firstName";
    public static final String COLUMN_AUTHOR_LAST_NAME = "lastName";
    public static final String COLUMN_IS_BOOK_AVAILABLE = "isBookAvailable";
    public static final String COLUMN_AUTHOR_DATE_OF_BIRTH = "birthDate";
    public static final String COLUMN_AUTHOR_CITY = "authorCity";
    public static final String COLUMN_AUTHOR_STATE = "authorState";


    // Column names for issue table
    public static final String ISSUE_TABLE = "issues";
    public static final String COLUMN_ISSUE_TIME_ISSUE = "timeIssue";
    public static final String COLUMN_ISSUE_RENEWAL_COUNT = "renewalCount";

    // Column names for  members table
    public static final String MEMBERS_TABLE = "members";
    public static final String COLUMN_MEMBER_FIRST_NAME = "firstName";
    public static final String COLUMN_MEMBER_LAST_NAME = "lastName";
    public static final String COLUMN_MEMBER_MEMBER_ID = "memberId";
    public static final String COLUMN_MEMBER_PHONE_NUMBER = "phoneNumber";
    public static final String COLUMN_MEMBER_EMAIL = "email";

    // Column names for address table
    public static final String ADDRESS_TABLE = "address";
    public static final String COLUMN_ADDRESS_ID = "id";
    public static final String COLUMN_ADDRESS_STREET_ADDRESS = "streetAddress";
    public static final String COLUMN_ADDRESS_CITY = "city";
    public static final String COLUMN_ADDRESS_STATE = "state";
    public static final String COLUMN_ADDRESS_ZIP_CODE = "zipCode";

    //Column names for book table
    public static final String BOOK_TABLE = "Books";
    public static final String COLUMN_BOOK_ISBN = "isbn";
    public static final String COLUMN_BOOK_BOOK_TITLE = "bookTitle";
    public static final String COLUMN_BOOK_PUBLISHER = "publisher";
    public static final String COLUMN_BOOK_COPYRIGHT = "copyRight";
    public static final String COLUMN_BOOK_EDITION = "editionNumber";

    // Column indexes for querying book and member table
    public static final int INDEX_AUTHOR_ID = 1;
    public static final int INDEX_AUTHOR_FIRST_NAME = 2;
    public static final int INDEX_AUTHOR_LAST_NAME = 3;
    public static final int INDEX_AUTHOR_DATE_OF_BIRTH = 4;
    public static final int INDEX_AUTHOR_CITY = 5;
    public static final int INDEX_AUTHOR_STATE = 6;
    public static final int INDEX_BOOK_TITLE = 7;
    public static final int INDEX_PUBLISHER = 8;
    public static final int INDEX_ISBN = 9;
    public static final int INDEX_BOOK_EDITION = 10;
    public static final int INDEX_BOOK_AVAILABILITY = 11;
    public static final int INDEX_BOOK_COPYRIGHT = 12;



    //Column indexes for querying all members table
    public static final int INDEX_MEMBER_ID = 1;
    public static final int INDEX_MEMBER_FIRST_NAME = 2;
    public static final int INDEX_MEMBER_LAST_NAME = 3;
    public static final int INDEX_MEMBER_PHONE_NUMBER = 4;
    public static final int INDEX_MEMBER_EMAIL = 5;
    public static final int INDEX_ADDRESS_ID = 6;
    public static final int INDEX_MEMBER_STREET_ADDRESS = 7;
    public static final int INDEX_MEMBER_CITY = 8;
    public static final int INDEX_MEMBER_STATE = 9;
    public static final int INDEX_MEMBER_ZIPCODE = 10;



    //Table name for author isbn table
    public static final String AUTHOR_ISBN_TABLE = "authorIsbn";

    // Database to be used for this application
    public static final String DB_Name = "library";
    // mysql driver variable
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    // Database connection url
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/"+DB_Name+"?useSSL=false";
    // root user name
    public static final String USERNAME = "root";
    // root user password
    public static final String PASSWORD = "Flower1987!";
    // Connection variable
    private Connection conn;
    // Instance of the DataSource class
    private static DataSource instance = new DataSource();

    // Private constructor. This object can only be instantiated once

    // SQL statement to create Authors table
    public static final String CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS "+ AUTHOR_TABLE +

            " ( "
            + "     authorId INT NOT NULL PRIMARY KEY AUTO_INCREMENT, "
            + "     firstName VARCHAR(200) NOT NULL, "
            + "     lastName VARCHAR(200) NOT NULL, "
            + "     authorCity VARCHAR(200) NOT NULL,"
            + "     authorState VARCHAR(200) NOT NULL,"
            + "     birthDate DATE NOT NULL"
            + " ) ";


    // SQL statement to create Title table
    public static final String CREATE_TABLE_BOOK = "CREATE TABLE IF NOT EXISTS "+ BOOK_TABLE +
            " ( "
            +"  isbn VARCHAR(25) NOT NULL PRIMARY KEY, "
            +"  bookTitle VARCHAR(150) NOT NULL, "
            + " editionNumber INT NOT NULL,"
            +"  publisher VARCHAR (200) NOT NULL, "
            +"  copyRight  INTEGER(5) NOT NULL,"
            + " isBookAvailable TINYINT(0) NOT NULL, "
            +" UNIQUE(isbn)"
            + " ) ";

    // SQL statement to create Authors ISBN Table
    public static final String CREATE_AUTHOR_ISBN_TABLE = " CREATE TABLE IF NOT EXISTS "+ AUTHOR_ISBN_TABLE +
            " ( "
            +"  authorIsbnId INT NOT NULL AUTO_INCREMENT ,"
            +"  isbn VARCHAR(25) NOT NULL, "
            +"  authorId INT NOT NULL,"
            +" PRIMARY KEY (authorIsbnId), "
            + "CONSTRAINT "+"FK_isbn FOREIGN KEY "+ " ( "+ COLUMN_BOOK_ISBN +" ) "+ " REFERENCES "+
            BOOK_TABLE + "("+ COLUMN_BOOK_ISBN + ") ON DELETE CASCADE ON UPDATE CASCADE, "
            + "CONSTRAINT "+ "FK_authorId FOREIGN KEY "+ " ( "+ COLUMN_AUTHOR_AUTHOR_ID+ " ) REFERENCES "
            + AUTHOR_TABLE + "("+COLUMN_AUTHOR_AUTHOR_ID + ") ON DELETE CASCADE ON UPDATE CASCADE"
            + ")";
    // SQL statement to create Membe Table
    public static final String CREATE_TABLE_MEMBERS = " CREATE TABLE IF NOT EXISTS "+ MEMBERS_TABLE +
            " ( "
            +"  memberId VARCHAR(15) NOT NULL PRIMARY KEY, "
            +"  firstName VARCHAR(250) NOT NULL, "
            +"  lastName VARCHAR(250) NOT NULL, "
            +"  phoneNumber VARCHAR(13) NOT NULL, "
            +"  email VARCHAR(225) NOT NULL, "
            +" UNIQUE(memberId)"
            + " ) ";
    // SQL statement to create Address  Table
    public static final String CREATE_ADDRESS_TABLE = " CREATE TABLE IF NOT EXISTS "+ ADDRESS_TABLE +
            " ( "
            +"  id INT NOT NULL AUTO_INCREMENT, "
            +"  memberId VARCHAR(15) NOT NULL, "
            +"  streetAddress VARCHAR(200) NOT NULL, "
            +"  city VARCHAR(200) NOT NULL, "
            +"  state VARCHAR(200) NOT NULL, "
            +   "zipCode VARCHAR(10) NOT NULL, "
            +"  PRIMARY KEY(id), "
            +"  CONSTRAINT "+ " FK_memberId FOREIGN KEY " + " ( " + COLUMN_MEMBER_MEMBER_ID + " ) " + " REFERENCES "
            + MEMBERS_TABLE + " ( " + COLUMN_MEMBER_MEMBER_ID + " ) ON DELETE CASCADE ON UPDATE CASCADE "
            + " ) ";

    // SQL statement to create  Issue Table
    public static final String CREATE_ISSUE_TABLE = " CREATE TABLE IF NOT EXISTS " + ISSUE_TABLE +
            " ( "
            +"  id INT NOT NULL AUTO_INCREMENT, "
            +"  isbn VARCHAR(25) NOT NULL, "
            +"  memberId VARCHAR(15) NOT NULL, "
            +"  timeIssue TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
            +"  renewalCount INTEGER DEFAULT 0, "
            +"  PRIMARY KEY(id), "
            + " CONSTRAINT FK_bookIsbn FOREIGN KEY " + " ( " + COLUMN_BOOK_ISBN + " ) " + " REFERENCES "
            + BOOK_TABLE + "(" + COLUMN_BOOK_ISBN + ") ON DELETE CASCADE ON UPDATE CASCADE, "
            + "CONSTRAINT FK_userMemberId  FOREIGN KEY " + " ( " + COLUMN_MEMBER_MEMBER_ID + " ) " + " REFERENCES "
            + MEMBERS_TABLE + "(" + COLUMN_MEMBER_MEMBER_ID + ") ON DELETE CASCADE ON UPDATE CASCADE "
            + " )";



    //SQL statement to insert record into the author table
    public static final String INSERT_INTO_AUTHOR_TABLES = " INSERT INTO "+ AUTHOR_TABLE + " ( firstName, lastName, birthDate, authorCity, authorState) "+
            " VALUES ( ?, ?, ?, ?, ?) ";

    // SQL statement to insert record into the address table
    public static final String INSERT_INTO_ADDRESS_TABLE = " INSERT INTO "+ ADDRESS_TABLE + "( memberId, streetAddress, city, state, zipCode) " +
            " VALUES (?, ?,?, ?, ?) ";


    //SQL statement to insert record into the Library members table
    public static final String INSERT_INTO_MEMBERS_TABLE = " INSERT INTO "+ MEMBERS_TABLE + "(memberId, firstName, lastName, phoneNumber, email)" +
            " VALUES(?, ?, ?, ?, ?) ";

    // SQL statement to insert data into title table
    public static final String INSERT_INTO_BOOK_TABLE = " INSERT INTO "+ BOOK_TABLE + " (isbn, bookTitle, editionNumber, publisher, copyRight, isBookAvailable)"+
            " VALUES (?, ?, ?, ?, ?, ?)";
    //SQL statement to insert record into the Author Isbn table
    public static final String INSERT_AUTHOR_ISBN_TABLE = " INSERT INTO "+ AUTHOR_ISBN_TABLE +"(isbn, authorId) VALUES ( ?, ?)";

    public static final String INSERT_INTO_ISSUE_TABLE = " INSERT INTO " + ISSUE_TABLE + " (isbn, memberId) " +
            " VALUES (?, ?)";

    // Update book available in book table
    public static final String UPDATE_BOOK_AVAILABLE = " UPDATE " + BOOK_TABLE + " SET " + COLUMN_IS_BOOK_AVAILABLE + " = ? " +
            " WHERE " + COLUMN_BOOK_ISBN + " = ? ";
    // Delete record from issue table upon book return
    public static final String DELETE_RECORD_FROM_ISSUES_TABLE = " DELETE FROM " + ISSUE_TABLE +
            " WHERE " + COLUMN_BOOK_ISBN + " = ?";
    // Update renewal count and date at book renewal
    public static final String UPDATE_BOOK_RENEWAL = " UPDATE " + ISSUE_TABLE + " SET " + " renewalCount = renewalCount + 1" + ", "+
            COLUMN_ISSUE_TIME_ISSUE + " = CURRENT_TIMESTAMP WHERE " + COLUMN_BOOK_ISBN + " = ? ";

    //Update member record
    public static final String UPDATE_MEMBER_RECORD = " UPDATE " + MEMBERS_TABLE + " SET " + COLUMN_MEMBER_FIRST_NAME + " = ? , " +
            COLUMN_MEMBER_LAST_NAME + " = ? ," + COLUMN_MEMBER_PHONE_NUMBER + " = ? ," + COLUMN_MEMBER_EMAIL + " = ? " +
            " WHERE " + COLUMN_MEMBER_MEMBER_ID + " = ? ";

    // Update address record
    public static final String UPDATE_ADDRESS_RECORD = " UPDATE " + ADDRESS_TABLE + " SET " + COLUMN_ADDRESS_STREET_ADDRESS + " = ? ," +
        COLUMN_ADDRESS_CITY + " = ? ," + COLUMN_ADDRESS_STATE + " = ? ," + COLUMN_ADDRESS_ZIP_CODE + " = ? " +
        " WHERE " + COLUMN_ADDRESS_ID + " = ? ";

    //Update Book record in database
    public static final String UPDATE_BOOK_RECORD = " UPDATE " + BOOK_TABLE + " SET " + COLUMN_BOOK_BOOK_TITLE + " = ? , " +
            COLUMN_BOOK_EDITION + " = ? , " + COLUMN_BOOK_PUBLISHER + " = ? , " + COLUMN_BOOK_COPYRIGHT + " = ? " +
            " WHERE " + COLUMN_BOOK_ISBN + " = ? ";
    // UPDATE AUTHOR RECORD
    public static final String UPDATE_AUTHOR_RECORD = " UPDATE " + AUTHOR_TABLE + " SET " + COLUMN_AUTHOR_FIRST_NAME + " = ? , "+
            COLUMN_AUTHOR_LAST_NAME + " = ? , " +  COLUMN_AUTHOR_CITY + " = ? , " + COLUMN_AUTHOR_STATE + " = ? ," +
            COLUMN_AUTHOR_DATE_OF_BIRTH + " = ? "+ " WHERE " + COLUMN_AUTHOR_AUTHOR_ID + " = ? ";


    //Get book Availability
    public static final String QUERY_BOOK_AVAILABILITY = " SELECT " + COLUMN_IS_BOOK_AVAILABLE + " FROM " +
            " WHERE " + COLUMN_BOOK_ISBN + " = ? ";

    // Query isbn number from title table
    public static final String QUERY_BOOK_TABLE = " SELECT "+ COLUMN_BOOK_ISBN + " FROM " + BOOK_TABLE +
            " WHERE "+ COLUMN_BOOK_BOOK_TITLE + " = ? ";

    // Query author and title table
    public static final String QUERY_AUTHOR_ISBN = " SELECT " + COLUMN_BOOK_ISBN +" FROM "+
            AUTHOR_ISBN_TABLE +" WHERE "+ COLUMN_BOOK_ISBN + " = ? ";

    // Query ISBN Number
    public static final String QUERY_ISBN_NUMBER = " SELECT "+ COLUMN_BOOK_ISBN + " FROM "+ AUTHOR_ISBN_TABLE +
            " WHERE "+ COLUMN_BOOK_ISBN + " = ? ";

    //Query FirstName, LastName, BookTitle, Publisher, ISBN, Edition, Availability from the database to be used book list table
     public static final String QUERY_ALL_BOOKS = " SELECT "+ AUTHOR_TABLE+ "."+ COLUMN_AUTHOR_AUTHOR_ID + ", "+ AUTHOR_TABLE+"."+COLUMN_AUTHOR_FIRST_NAME+", "+AUTHOR_TABLE+"."+
            COLUMN_AUTHOR_LAST_NAME+", " + AUTHOR_TABLE + "." + COLUMN_AUTHOR_DATE_OF_BIRTH + ", " + AUTHOR_TABLE + "." + COLUMN_AUTHOR_CITY + ", " +
            AUTHOR_TABLE + "." + COLUMN_AUTHOR_STATE + "," +  BOOK_TABLE + "." + COLUMN_BOOK_BOOK_TITLE + ", "+ BOOK_TABLE + "."+ COLUMN_BOOK_PUBLISHER + ", "+ BOOK_TABLE + "."+ COLUMN_BOOK_ISBN + ", "+ BOOK_TABLE + "." + COLUMN_BOOK_EDITION + ", " + BOOK_TABLE + "." + COLUMN_IS_BOOK_AVAILABLE + ", "+
            BOOK_TABLE + "." + COLUMN_BOOK_COPYRIGHT + " FROM "+ AUTHOR_TABLE + " INNER JOIN "+ AUTHOR_ISBN_TABLE +
            " ON "+ AUTHOR_TABLE + "." + COLUMN_AUTHOR_AUTHOR_ID + " = " + AUTHOR_ISBN_TABLE + "." + COLUMN_AUTHOR_AUTHOR_ID +
            " INNER JOIN "+ BOOK_TABLE + " ON " + BOOK_TABLE + "." + COLUMN_BOOK_ISBN + " = "+ AUTHOR_ISBN_TABLE + "." +
            COLUMN_BOOK_ISBN;

    // Query Member Id from the member table
    public static final String QUERY_MEMBER_ID = " SELECT "+  COLUMN_MEMBER_MEMBER_ID + " FROM "+ MEMBERS_TABLE +
            " WHERE "+COLUMN_MEMBER_MEMBER_ID + " = ? ";



    // Query All Member FirstName, LastName, phone, Email, Address
    public static final String QUERY_ALL_MEMBERS = " SELECT "+ MEMBERS_TABLE + "." + COLUMN_MEMBER_MEMBER_ID + ", "+ MEMBERS_TABLE + "."+ COLUMN_MEMBER_FIRST_NAME + ", " + MEMBERS_TABLE +
            "." + COLUMN_MEMBER_LAST_NAME + ", " + MEMBERS_TABLE + "." + COLUMN_MEMBER_PHONE_NUMBER + ", "+
            MEMBERS_TABLE + "." + COLUMN_MEMBER_EMAIL + ", "+ ADDRESS_TABLE + "."+ COLUMN_ADDRESS_ID + ", "+ ADDRESS_TABLE + "." + COLUMN_ADDRESS_STREET_ADDRESS + ", " +
            ADDRESS_TABLE + "." + COLUMN_ADDRESS_CITY + ", " + ADDRESS_TABLE + "." + COLUMN_ADDRESS_STATE + ", "+ ADDRESS_TABLE + "." + COLUMN_ADDRESS_ZIP_CODE +
            " FROM " + MEMBERS_TABLE + " INNER JOIN " + ADDRESS_TABLE + " ON " + ADDRESS_TABLE + "." + COLUMN_MEMBER_MEMBER_ID + " = "+
             MEMBERS_TABLE + "." + COLUMN_MEMBER_MEMBER_ID;


    //Query Book Isbn from database
    public static final String QUERY_BOOK_INFO = " SELECT "+ AUTHOR_TABLE + "." + COLUMN_AUTHOR_FIRST_NAME + ", " + AUTHOR_TABLE + "." +
            COLUMN_AUTHOR_LAST_NAME + ", " + BOOK_TABLE + "." + COLUMN_BOOK_BOOK_TITLE + ", " + BOOK_TABLE + "." + COLUMN_IS_BOOK_AVAILABLE + ","+
            BOOK_TABLE + "."+ COLUMN_BOOK_PUBLISHER +  " FROM "+ AUTHOR_TABLE + " INNER JOIN " + AUTHOR_ISBN_TABLE + " ON " + AUTHOR_TABLE + "." + COLUMN_AUTHOR_AUTHOR_ID + " = " +
            AUTHOR_ISBN_TABLE + "." + COLUMN_AUTHOR_AUTHOR_ID + " INNER JOIN " + BOOK_TABLE + " ON " + AUTHOR_ISBN_TABLE + "." +
            COLUMN_BOOK_ISBN + " = " + BOOK_TABLE + "." + COLUMN_BOOK_ISBN  + " WHERE " +  BOOK_TABLE + "." + COLUMN_BOOK_ISBN + " = ? ";


    // Query member info from database
    public static final String QUERY_MEMBER_INFO = " SELECT " + MEMBERS_TABLE + "." + COLUMN_MEMBER_FIRST_NAME + ", "+ MEMBERS_TABLE + "." + COLUMN_MEMBER_LAST_NAME + ", "
            +  MEMBERS_TABLE + "." + COLUMN_MEMBER_PHONE_NUMBER + ", " + MEMBERS_TABLE + "." + COLUMN_MEMBER_EMAIL + ", "+ ADDRESS_TABLE + "." + COLUMN_ADDRESS_STREET_ADDRESS
            + " FROM " + MEMBERS_TABLE + " INNER JOIN " + ADDRESS_TABLE + " ON " + ADDRESS_TABLE + "." + COLUMN_MEMBER_MEMBER_ID + " = "
            + MEMBERS_TABLE + "." + COLUMN_MEMBER_MEMBER_ID + " WHERE " + MEMBERS_TABLE + "." + COLUMN_MEMBER_MEMBER_ID + " = ? ";
    // Query book status from database
    public static final String QUERY_BOOK_STATUS = " SELECT " + COLUMN_IS_BOOK_AVAILABLE +
            " FROM " + BOOK_TABLE + " WHERE " + COLUMN_BOOK_ISBN + " = ? ";

    // Query book information from issues where isbn = isbn
    public static final String QUERY_ISSUE_TABLE = " SELECT " + COLUMN_BOOK_ISBN + ", " + COLUMN_MEMBER_MEMBER_ID + ", " +
            COLUMN_ISSUE_TIME_ISSUE + ", " + COLUMN_ISSUE_RENEWAL_COUNT + " FROM " + ISSUE_TABLE + " WHERE " + COLUMN_BOOK_ISBN + " = ? ";

    // Delete record from members table
    public static final String DELETE_MEMBER_RECORD = " DELETE FROM " + MEMBERS_TABLE +
            " WHERE " + COLUMN_MEMBER_MEMBER_ID + " = ?";
    // Delete record from author table
    public static final String DELETE_AUTHOR_RECORD = " DELETE FROM " + AUTHOR_TABLE + " WHERE " + COLUMN_AUTHOR_AUTHOR_ID + " = ? ";

    // Delete record from book table
    public static final String DELETE_BOOK_RECORD = " DELETE FROM " + BOOK_TABLE + " WHERE " + COLUMN_BOOK_ISBN + " = ? ";

    // Delete record from authorisbn table
    public static final String DELETE_AUTHORID_BOOKISBN = " DELETE FROM " + AUTHOR_ISBN_TABLE + " WHERE " + COLUMN_BOOK_ISBN +
            " = ? ";

    // QUERY AUTHORID
    public static final String QUERY_AUTHOR_ID = " SELECT " + COLUMN_AUTHOR_AUTHOR_ID + " FROM " + AUTHOR_TABLE +" WHERE " +
            COLUMN_AUTHOR_FIRST_NAME + " = ? AND " + COLUMN_AUTHOR_LAST_NAME + " = ? AND " + COLUMN_AUTHOR_CITY + " = ?";


    // Constructor for this class can only be instantiated. Singleton instance
    private DataSource(){


    }
    // Return the instance of this class
    public static DataSource getInstance(){
        return instance;
    }


    // Open connection to the database
    public boolean openConnection(){
        try{
            Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            createAuthorTable = conn.prepareStatement(CREATE_BOOK_TABLE);
            createTitleTable = conn.prepareStatement(CREATE_TABLE_BOOK);
            createAuthorIsbnTable = conn.prepareStatement(CREATE_AUTHOR_ISBN_TABLE);
            createIssueTable = conn.prepareStatement(CREATE_ISSUE_TABLE);
            queryAuthorIsbnTable = conn.prepareStatement(QUERY_AUTHOR_ISBN);
            queryBooksTable = conn.prepareStatement(QUERY_BOOK_TABLE);
            insertIntoAuthors = conn.prepareStatement(INSERT_INTO_AUTHOR_TABLES, Statement.RETURN_GENERATED_KEYS);
            insertIntoBook = conn.prepareStatement(INSERT_INTO_BOOK_TABLE,Statement.RETURN_GENERATED_KEYS);
            insertIntoAuthorIsbn = conn.prepareStatement(INSERT_AUTHOR_ISBN_TABLE);
            queryIsbnForBook = conn.prepareStatement(QUERY_ISBN_NUMBER);
            queryAllBooks = conn.prepareStatement(QUERY_ALL_BOOKS);
            createMembersTable = conn.prepareStatement(CREATE_TABLE_MEMBERS);
            createAddressTable = conn.prepareStatement(CREATE_ADDRESS_TABLE);
            createMembersTable = conn.prepareStatement(CREATE_TABLE_MEMBERS);
            insertIntoMemberTable = conn.prepareStatement(INSERT_INTO_MEMBERS_TABLE);
            insertIntoAddressTable = conn.prepareStatement(INSERT_INTO_ADDRESS_TABLE);
            queryMemberId = conn.prepareStatement(QUERY_MEMBER_ID);
            queryAllMembers = conn.prepareStatement(QUERY_ALL_MEMBERS);
            queryBookInfo = conn.prepareStatement(QUERY_BOOK_INFO);
            queryMemberInfo = conn.prepareStatement(QUERY_MEMBER_INFO);
            insertIntoIssueTable = conn.prepareStatement(INSERT_INTO_ISSUE_TABLE);
            updateBookAvailability = conn.prepareStatement(UPDATE_BOOK_AVAILABLE);
            queryBookStatus = conn.prepareStatement(QUERY_BOOK_STATUS);
            queryIssueTable = conn.prepareStatement(QUERY_ISSUE_TABLE);
            queryBookAvailability = conn.prepareStatement(QUERY_BOOK_AVAILABILITY);
            deleteRecordFromIssuesTable = conn.prepareStatement(DELETE_RECORD_FROM_ISSUES_TABLE);
            updateBookRenewal = conn.prepareStatement(UPDATE_BOOK_RENEWAL);
            deleteBookIsbnAuthorID = conn.prepareStatement(DELETE_AUTHORID_BOOKISBN);
            deleteAuthorRecord = conn.prepareStatement(DELETE_AUTHOR_RECORD);
            deleteBookRecord = conn.prepareStatement(DELETE_BOOK_RECORD);
            deleteMemberRecord = conn.prepareStatement(DELETE_MEMBER_RECORD);
            updateBookRecord = conn.prepareStatement(UPDATE_BOOK_RECORD);
            updateAuthorRecord = conn.prepareStatement(UPDATE_AUTHOR_RECORD);
            queryAuthorID = conn.prepareStatement(QUERY_AUTHOR_ID);
            updateMemberRecord = conn.prepareStatement(UPDATE_MEMBER_RECORD);
            updateAddressRecord = conn.prepareStatement(UPDATE_ADDRESS_RECORD);
            return true;

        }catch (SQLException ex){
            // For debugging purposes
            System.out.println("ERROR NOT CONNECTING");
        }catch (ClassNotFoundException clsExe){
            System.out.println("Class not found. "+clsExe.getMessage());

        }catch (IllegalAccessException exe){
            System.out.println("Illegal access exception "+ exe.getMessage());
        }catch (InstantiationException ex){
            System.out.println("Instantiation Exception "+ ex.getMessage());
        }
        return false;
    }

    // Close all connection to the database
    public void closeConnection(){
        try{
            if(createAuthorTable != null){
                createAuthorTable.close();
            }
            if(createTitleTable != null){
                createTitleTable.close();
            }
            if(createAuthorIsbnTable != null){
                createAuthorIsbnTable.close();
            }
            if(createIssueTable != null){
                createIssueTable.close();
            }
            if(queryAuthorIsbnTable != null){
                queryAuthorIsbnTable.close();
            }

            if(insertIntoAuthors != null){
                insertIntoAuthors.close();
            }
            if(insertIntoBook != null){
                insertIntoBook.close();
            }
            if(queryBooksTable != null){
                queryBooksTable.close();
            }
            if(insertIntoAuthorIsbn != null){
                insertIntoAuthorIsbn.close();
            }
            if(queryIsbnForBook != null){
                queryIsbnForBook.close();
            }
            if(queryAllBooks != null){
                queryAllBooks.close();
            }
            if(createMembersTable != null){
                createMembersTable.close();
            }
            if(createAddressTable != null){
                createAddressTable.close();
            }
            if(createMembersTable != null){
                createMembersTable.close();
            }
            if(insertIntoMemberTable != null){
                insertIntoMemberTable.close();
            }
            if(insertIntoAddressTable != null){
                insertIntoAddressTable.close();
            }
            if(queryMemberId != null){
                queryMemberId.close();
            }
            if(queryAllMembers != null){
                queryAllMembers.close();
            }
            if(queryBookInfo != null){
                queryBookInfo.close();
            }
            if(queryMemberInfo != null){
                queryMemberInfo.close();
            }
            if(insertIntoIssueTable != null){
                insertIntoIssueTable.close();
            }
            if(updateBookAvailability != null)
            {
                updateBookAvailability.close();
            }
            if(queryBookStatus != null)
            {
                queryBookStatus.close();
            }
            if(queryIssueTable != null){
                queryIssueTable.close();
            }
            if(queryBookAvailability != null)
            {
                queryBookAvailability.close();
            }
            if(deleteRecordFromIssuesTable != null){
                deleteRecordFromIssuesTable.close();
            }
            if(deleteBookRecord != null){
                deleteBookRecord.close();
            }
            if(deleteAuthorRecord != null){
                deleteAuthorRecord.close();
            }
            if(deleteBookIsbnAuthorID != null){
                deleteBookIsbnAuthorID.close();
            }
            if(deleteMemberRecord != null){
                deleteMemberRecord.close();
            }
            if(updateAuthorRecord != null){
                updateAuthorRecord.close();
            }
            if(updateBookRecord != null){
                updateBookRecord.close();
            }
            if(queryAuthorID != null){
                queryAuthorID.close();
            }
            if(updateMemberRecord != null){
                updateMemberRecord.close();
            }
            if(updateAddressRecord != null){
                updateAddressRecord.close();
            }
            if(conn != null){
                conn.close();

            }
            // For debugging purposes
            System.out.println("Connection close successfully");
        }catch (SQLException exe){
            System.out.println("Database connection didn't close sucessfully "+ exe.getMessage());

        }
    }




    // Create all tables if they aren't created. If tables have been created, this method ignores table creation
    public boolean createLibraryTables(){
        try{
            createMembersTable.executeUpdate();
            createAuthorTable.executeUpdate();
            createTitleTable.executeUpdate();
            createAuthorIsbnTable.executeUpdate();
            createMembersTable.executeUpdate();
            createAddressTable.executeUpdate();
            createIssueTable.executeUpdate();
            return true;

        }catch (SQLException exe){
            // For debugging purposes
            System.out.println("Couldn't create database tables" + exe.getMessage());
            exe.printStackTrace();
            return false;
        }


    }

    // Insert record into the members table
    private String insertRecordIntoMemberTable(String memberId, String firstName, String lastName,
                                               String phoneNumber, String email) throws SQLException{
            insertIntoMemberTable.setString(1, memberId);
            insertIntoMemberTable.setString(2, firstName);
            insertIntoMemberTable.setString(3, lastName);
            insertIntoMemberTable.setString(4, phoneNumber);
            insertIntoMemberTable.setString(5, email);
            insertIntoMemberTable.executeUpdate();

        return memberId;

    }

    // Insert records into member and address table
    public boolean insertRecordIntoMemberAndAddressTable(String memberId, String firstName, String lastName, String phoneNumber,
                                                         String email, String streetAddress, String city, String state, String zipCode)
    {
        try{
            queryMemberId.setString(1, memberId);
            ResultSet result = queryMemberId.executeQuery();
            if(result.next()){
                return false;
            }
            conn.setAutoCommit(false);
            String newMemberId = insertRecordIntoMemberTable(memberId, firstName, lastName, phoneNumber, email);
            insertIntoAddressTable.setString(1, newMemberId.toUpperCase());
            insertIntoAddressTable.setString(2, streetAddress);
            insertIntoAddressTable.setString(3, city);
            insertIntoAddressTable.setString(4, state);
            insertIntoAddressTable.setString(5, zipCode);
            int affectedRow = insertIntoAddressTable.executeUpdate();
            if(affectedRow == 1){

            }
        }catch (SQLException exe){
            // For debugging purposes
            System.out.println(exe.getMessage());
        }catch (Exception ex){
            try{
                conn.rollback();
                ex.printStackTrace();
            }catch (SQLException exe1){
                System.out.println("Rolling back changes. An error has occurred");
            }
        }finally {
            try{
                conn.setAutoCommit(true);
            }catch (SQLException ex){
                // For debugging purposes
                System.out.println(ex.getMessage());
            }

        }
       return true;

    }
    // Insert records into the author's table
    public int insertRecordIntoAuthorTable(String firstName, String lastName, Date birthDate, String authorCity, String authorState) throws SQLException {

            insertIntoAuthors.setString(1, firstName);
            insertIntoAuthors.setString(2, lastName);
            insertIntoAuthors.setDate(3, birthDate);
            insertIntoAuthors.setString(4, authorCity);
            insertIntoAuthors.setString(5, authorState);
            int affectedRow = insertIntoAuthors.executeUpdate();


            if (affectedRow != 1) {
                throw new SQLException("Couldn't add author to database");
            }
            // Returns the primary key to be as foreign key in the authorisbn table
            ResultSet keyGenerated = insertIntoAuthors.getGeneratedKeys();
            if(keyGenerated.next()){

                return keyGenerated.getInt(1);
                }else{
                    throw new SQLException("Couldn't get author id from table");
            }


        }

//      Insert books information in the title table. Return isbn number to be use in authorisbn table
        public String insertRecordIntoBookTable(String isbn, String bookTitle, int editionNumber, String publisher, String copyRight, boolean isBookAvailable) throws SQLException {

            insertIntoBook.setString(1, isbn);
            insertIntoBook.setString(2, bookTitle);
            insertIntoBook.setInt(3, editionNumber);
            insertIntoBook.setString(4, publisher);
            insertIntoBook.setString(5, copyRight);
            insertIntoBook.setBoolean(6, isBookAvailable);
            insertIntoBook.executeUpdate();


            return isbn;

        }

        // Insert Book, title, and then create relationship in the authorisbn table
        public boolean insertRecordIntoAuthorIsbnTable(String firstName, String lastName, Date birthDate, String authorCity, String authorState,
                                                       String isbn, String bookTitle,
                                                       int editionNUmber, String publisher, String copyRight, boolean isBookAvailable){


        try{
            // If isbn number exist, Don't add book. Return false immediately. We don't want duplicate, otherwise, add the book
            queryIsbnForBook.setString(1, isbn);
            ResultSet resultSet = queryIsbnForBook.executeQuery();
            if(resultSet.next()){
                return false;
            }
                conn.setAutoCommit(false);
                int authorId = insertRecordIntoAuthorTable(firstName, lastName, birthDate, authorCity, authorState);
            String isbnNumber = insertRecordIntoBookTable(isbn, bookTitle, editionNUmber, publisher, copyRight, isBookAvailable);
               insertIntoAuthorIsbn.setString(1, isbnNumber);
               insertIntoAuthorIsbn.setInt(2,authorId);

               int affectedRow = insertIntoAuthorIsbn.executeUpdate();
               // If insertion is successful that is affected Row equal 1, commit data to the database
               if(affectedRow == 1){
                   conn.commit();
               }


            }catch (SQLException ex){
            // For debugging purposes
                System.out.println(ex.getMessage());

            }catch (Exception exe){
                try{
                    conn.rollback(); // If there was error saving data, roll back the database
                    return false;
                }catch (SQLException e){
                    // For debugging purposes
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }

            }finally {
            try{
                conn.setAutoCommit(true);
            }catch (SQLException ex){
                // For debugging purposes
                System.out.println("Commit has been set back true "+ ex.getMessage());
                ex.printStackTrace();
            }
        }
        // If insertion of data is successful, return true
        return true;
        }

        // Query all books in the database. Return a list of books available in our database
        public List<Book> queryAllBooks(){
            System.out.println(QUERY_ALL_BOOKS);
            List<Book> booksList = new ArrayList<>();
        try{
            ResultSet booksResults = queryAllBooks.executeQuery();

            while (booksResults.next()){
                Book book = new Book();
                book.setAuthorId(booksResults.getInt(INDEX_AUTHOR_ID));
                book.setAuthorFirstName(booksResults.getString(INDEX_AUTHOR_FIRST_NAME));
                book.setAuthorLastName(booksResults.getString(INDEX_AUTHOR_LAST_NAME));
                book.setAuthorDateOfBirth(booksResults.getTimestamp(INDEX_AUTHOR_DATE_OF_BIRTH).toLocalDateTime());
                book.setAuthorCity(booksResults.getString(INDEX_AUTHOR_CITY));
                book.setAuthorState(booksResults.getString(INDEX_AUTHOR_STATE));
                book.setBookTitle(booksResults.getString(INDEX_BOOK_TITLE));
                book.setBookPublisher(booksResults.getString(INDEX_PUBLISHER));
                book.setBookIsbn(booksResults.getString(INDEX_ISBN));
                book.setBookEdition(booksResults.getInt(INDEX_BOOK_EDITION));
                book.setBookAvailability(booksResults.getBoolean(INDEX_BOOK_AVAILABILITY));
                book.setCopyRightYear(booksResults.getString(INDEX_BOOK_COPYRIGHT));
                booksList.add(book);

            }
            return booksList;
        }catch (SQLException exe){
            System.out.println(exe.getMessage());
        }
           return booksList ;
        }


    // Query and return list of members in the database
        public List<Member> queryAllMembers(){
            System.out.println(QUERY_ALL_MEMBERS);
            List<Member> memberInfoList = new ArrayList<>();
            try{
                ResultSet resultSet = queryAllMembers.executeQuery();
                while (resultSet.next()){
                    Member memberInfo = new Member();
                    memberInfo.setMemberId(resultSet.getString(INDEX_MEMBER_ID));
                    memberInfo.setFirstName(resultSet.getString(INDEX_MEMBER_FIRST_NAME));
                    memberInfo.setLastName(resultSet.getString(INDEX_MEMBER_LAST_NAME));
                    memberInfo.setPhoneNumber(resultSet.getString(INDEX_MEMBER_PHONE_NUMBER));
                    memberInfo.setEmail(resultSet.getString(INDEX_MEMBER_EMAIL));
                    memberInfo.setId(resultSet.getInt(INDEX_ADDRESS_ID));
                    memberInfo.setStreetAddress(resultSet.getString(INDEX_MEMBER_STREET_ADDRESS));
                    memberInfo.setCity(resultSet.getString(INDEX_MEMBER_CITY));
                    memberInfo.setState(resultSet.getString(INDEX_MEMBER_STATE));
                    memberInfo.setZipCode(resultSet.getString(INDEX_MEMBER_ZIPCODE));
                    memberInfoList.add(memberInfo);

                }
            }catch (SQLException exe){
                // For debugging purposes
                System.out.println(exe.getMessage());
                exe.printStackTrace();
            }
            return memberInfoList;
        }

        // Query a specific book info from the database using isbn number
        public  Book getBookInfo(String isbn){
            Book bookInfo = new Book();
        try{

            queryBookInfo.setString(1, isbn);
            ResultSet book = queryBookInfo.executeQuery();
            if(book.next()){
                bookInfo.setAuthorFirstName(book.getString(1));
                bookInfo.setAuthorLastName(book.getString(2));
                bookInfo.setBookTitle(book.getString(3));
                bookInfo.setBookAvailability(book.getBoolean(4));
                bookInfo.setBookPublisher(book.getString(5));
            }
        }catch (SQLException exe){
            // For debugging purposes
            System.out.println("An error occur while trying to query book info. ");
            exe.printStackTrace();
        }
          return bookInfo;
        }

        // Query a member from the database and return a memberinfo instance
        public Member getMemberInfo(String memberId){
            System.out.println(QUERY_MEMBER_INFO);
            Member member = new Member();
        try{
            queryMemberInfo.setString(1, memberId);
            ResultSet memberInfo = queryMemberInfo.executeQuery();
            if(memberInfo.next()){

                member.setFirstName(memberInfo.getString(1));
                member.setLastName(memberInfo.getString(2));
                member.setPhoneNumber(memberInfo.getString(3));
                member.setEmail(memberInfo.getString(4));
                member.setStreetAddress(memberInfo.getString(5));

            }
        }catch (SQLException exe){
            // For debugging purposes
            System.out.println("An error while trying to query member info from database");
            exe.printStackTrace();
        }
        return member;
        }

        // Insert record into issues table
        public boolean insertIntoIssueTable(String isbn, String memberId)
        {
            System.out.println(INSERT_INTO_ISSUE_TABLE);

            boolean recordSave = false;

            try{
                insertIntoIssueTable.setString(1, isbn);
                insertIntoIssueTable.setString(2, memberId);
               int affectedRow = insertIntoIssueTable.executeUpdate();
               System.out.println( "Affected Row "+affectedRow);
               if(affectedRow == 1){
                  recordSave = true;
               }
            }catch (SQLException exe){
                // For debugging purposes
                System.out.println("Couldn't insert record into issues table");
                exe.printStackTrace();
            }
            return recordSave;
        }

        // Update book availability in book table
        public boolean bookIssueAndReturn(String bookIsbn, boolean isBookIssue){
        boolean isUpdated = false;
        try{
            updateBookAvailability.setBoolean(1, isBookIssue);
            updateBookAvailability.setString(2, bookIsbn);
            int affectedRow = updateBookAvailability.executeUpdate();
            if(affectedRow == 1){
                isUpdated = true;
            }
        }catch (SQLException exe){

        }
        return isUpdated;
        }

        // Query Book status from database. return false if book isn't available otherwise true
        public boolean queryBookStatus(String isbn){
            System.out.println(QUERY_BOOK_STATUS);
        try{
            queryBookStatus.setString(1, isbn);
            ResultSet bookFound = queryBookStatus.executeQuery();
            bookFound.next();
            boolean bookStatus = bookFound.getBoolean(1);
            System.out.println(bookStatus);
            if(!bookStatus){
                return false;
            }
        }catch (SQLException ex){
            // For debugging purposes
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
         return true;
        }


        //Query and return a book that is being issued
        public Issue issueInfo(String isbn){
            Issue bookIssue = new Issue();
        try{

            queryIssueTable.setString(1, isbn);
            ResultSet queryIssue = queryIssueTable.executeQuery();
            if(queryIssue.next())
            {
                bookIssue.setIsbn(queryIssue.getString(1));
                bookIssue.setMemberId(queryIssue.getString(2));
                bookIssue.setDateTime(queryIssue.getTimestamp(3).toLocalDateTime());
                bookIssue.setRenewalCount(queryIssue.getInt(4));
            }
        }catch (SQLException exe)
        {
            // For debugging purposes
            System.out.println(exe.getMessage());
            exe.printStackTrace();
        }

        return bookIssue;
        }

    // Delete record from issue table
    public  boolean deleteRecordFromIssuesTable(String isbn){

        try{
            deleteRecordFromIssuesTable.setString(1, isbn);
            int affectedRow = deleteRecordFromIssuesTable.executeUpdate();
            if(affectedRow == 1){
                return true;
            }

        }catch (SQLException exe){
            // For debugging purposes
            System.out.println(exe.getMessage());
            exe.printStackTrace();
        }
       return false;
    }

    // Update book renewal information
    public boolean updateBookRenewal(String bookIsbn){
        try{
           updateBookRenewal.setString(1, bookIsbn);
           int affectedRow = updateBookRenewal.executeUpdate();
           if(affectedRow == 1){
               return true;
           }

        }catch (SQLException exe)
        {
            //For debugging purposes
            exe.printStackTrace();
            System.out.println(exe.getMessage());

        }
        return false;
    }

    // Query and delete record from book, author and authorisbn table
    public boolean deleteBookAuthorAndAuthorIsbnRecord(String bookIsbn, int authorId){
        try{
            deleteAuthorRecord.setInt(1, authorId);
            deleteBookRecord.setString(1, bookIsbn);
            deleteBookIsbnAuthorID.setString(1, bookIsbn);
            deleteAuthorRecord.executeUpdate();
            deleteBookRecord.executeUpdate();
           deleteBookIsbnAuthorID.executeUpdate();

           return true;

        }catch (SQLException exe){
            // For debugging purposes
            System.out.println(exe.getMessage());
            exe.printStackTrace();
        }
        return false;
    }
    // Query and delete record of a particular member
    public boolean deleteMemberRecord(String memberId){
        try{
           deleteMemberRecord.setString(1, memberId);
           deleteMemberRecord.executeUpdate();
           return true;
        }catch (SQLException exe){
            // For debugging purposes
            System.out.println(exe.getMessage());
            exe.printStackTrace();
        }
        return false;
    }

    // Update an author record in the database
    public boolean updateAuthorRecord(String firstName, String lastName, String authorCity, String authorState, LocalDate birthDate, int authorId){
        try{
            updateAuthorRecord.setString(1, firstName);
            updateAuthorRecord.setString(2, lastName);
            updateAuthorRecord.setString(3, authorCity);
            updateAuthorRecord.setString(4, authorState);
            updateAuthorRecord.setDate(5, Date.valueOf(birthDate));
            updateAuthorRecord.setInt(6, authorId);
            int affectedRow = updateAuthorRecord.executeUpdate();
            if(affectedRow == 1){
                return true;
            }

        }catch (SQLException exe){
            System.out.println(exe.getMessage());
        }
        return false;
    }

    // Update book record in the database
    public boolean updateBookRecord(String bookTitle, int bookEdition, String publisher, int copyRight, String bookIsbn ){
        try{
            updateBookRecord.setString(1, bookTitle);
            updateBookRecord.setInt(2, bookEdition);
            updateBookRecord.setString(3, publisher);
            updateBookRecord.setInt(4, copyRight);
            updateBookRecord.setString(5, bookIsbn);
            int affectedRow = updateBookRecord.executeUpdate();
            if(affectedRow == 1){
                return true;
            }

        }catch (SQLException exe){
            System.out.println(exe.getMessage());
            exe.printStackTrace();
        }
        return false;
    }

    // Get an author id to be used in deleted a particular record
    public int getAuthorID(String firstName, String lastName, String cityAddress){
        try{
            queryAuthorID.setString(1, firstName);
            queryAuthorID.setString(2, lastName);
            queryAuthorID.setString(3, cityAddress);
            ResultSet authorID = queryAuthorID.executeQuery();
            if(authorID.next()){
                return authorID.getInt(1);
            }
        }catch (SQLException exe){
            System.out.println(exe.getMessage());
        }
        return 0;
    }

    // Update member record in the database
    public boolean updateMemberRecord(String firstName, String lastName, String phoneNumber, String email, String memberId)
    {
        try{
            updateMemberRecord.setString(1, firstName);
            updateMemberRecord.setString(2, lastName);
            updateMemberRecord.setString(3, phoneNumber);
            updateMemberRecord.setString(4, email);
            updateMemberRecord.setString(5, memberId);
            int affectedRow = updateMemberRecord.executeUpdate();
            if(affectedRow == 1){
                return true;
            }
        }catch (SQLException exe){
            System.out.println(exe.getMessage());
        }
       return false;
    }



    // Update address record
    public boolean updateAddressRecord(String streetAddress, String city, String state, String zipcode, int id)
    {
        try{
            updateAddressRecord.setString(1, streetAddress);
            updateAddressRecord.setString(2, city);
            updateAddressRecord.setString(3, state);
            updateAddressRecord.setString(4, zipcode);
            updateAddressRecord.setInt(5, id);
            int affectedRow = updateAddressRecord.executeUpdate();
            if(affectedRow == 1){
                return true;
            }
        }catch(SQLException exe){
            System.out.println(exe.getMessage());
        }
        return false;
    }

}
