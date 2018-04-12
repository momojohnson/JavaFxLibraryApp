package com.momo;

import com.momo.datamodel.DataSource;
import com.momo.datamodel.LibrarySettings;

import java.io.File;


public class TestDB {
    public static void main(String[] args) {
        DataSource.getInstance().openConnection();
//        boolean value = DataSource.getInstance().createLibraryTables();
//        System.out.println(value);
//       LocalDateTime date =  LocalDateTime.of(1987, 4, 4, 0, 0);
//        Date sqlDate  = Date.valueOf(date.toLocalDate());
//        System.out.println(sqlDate);
//        //try{
//
//        //DataSource.getInstance().insertIntoIssueTable("978-1-891830-75-4", "B0012");
//        Issue issue = DataSource.getInstance().issueInfo("979-10-90636-07-1");
//        System.out.println("This is date "+ issue.getDateTime());
//
//        List<Member> bookList = DataSource.getInstance().queryAllMembers();
//        if(bookList.isEmpty()){
//            System.out.println("There is no books currently in our Library");
//        }
//        for(Member book : bookList){
//            System.out.println(book.getAuthorFirstName());
//        }

       // }
        DataSource.getInstance().closeConnection();

       //DataSource.getInstance().insertRecordIntoAuthorIsbnTable("James", "Gray", "12347", "I love to Run", "Oreilly", "34567", true);
    Directory dir = new Directory();
    dir.displayCurrentDir();
    }

}

class Directory{
public  void displayCurrentDir(){
        String url = this.getClass().getClassLoader().getResource("").getPath();
    File f = new File("config.txt");
    if(f.exists()) {
        System.out.println("File Exist ");
    }else {
        System.out.println("Creating file");
        LibrarySettings.initConfig();
    }

        System.out.println(url);
        }
}