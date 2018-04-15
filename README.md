## Javafx Library App
### The Javafx Library app contains 6  tables namely, 
### Author table contains information of the author
### Book table contains information about each book  in the database
### AuthorIsbn table establishes a many to many relationship between the author and books in the database.
### Member table contain information of each member which has foreign key relationship to the address table
### Address table contain address of each member in the database
### Issue table keeps track of book that was issue to each member in our database. It has a foreign key relationship to the member table
## The program has the following features as of now.
### 1  Add a new Book to the Library database
### 2  Add a new Member to the Library database
### 3  View books that have been added to the database through a books table
### 4  views members that have been added to the database through member table
### 5  Edit and delete book and member information that has been added to the database
### 6  Issue a book to a member who wish to borrow a book
### 7  Process renewal of a book that is being requested by a member
### 8 Process return of a book that is being requested by a user
## How to run the program
### 1  Make sure you have an instance of MySQL downloaded and installed on your computer. Here is the link to download mysql https://dev.mysql.com/downloads/mysql/
### 2   My sure you have MySQL JDBC include in your class path in order to connect to the MySQL server.
### When everything is setup, run the program. When you run the program, a default user will be created with username: “username” and password: “password”. Enter these credentials to login into the app. 

### Login Screen
![image](https://user-images.githubusercontent.com/29076661/38783937-81b182aa-40cf-11e8-9735-c6f5e7593a39.png)
### Error Message upon login
![image](https://user-images.githubusercontent.com/29076661/38783957-d4c0c1c2-40cf-11e8-98de-3b43e9bd4620.png)
### Main screen after login
![image](https://user-images.githubusercontent.com/29076661/38783963-fea9f58a-40cf-11e8-9ff6-a8621b6a7f2f.png)
### When hamburger icon is clicked
![image](https://user-images.githubusercontent.com/29076661/38783972-2bc9301c-40d0-11e8-82ff-351911645d17.png)
## Book Issue Tab
## 1 Enter the book isbn in the book isbn text field input. An assumption is made that you have added this book to the database. You will need to enter a valid isbn number because I implemented regular expression checks to ensure that the isbn entered is a valid. Press enter to display book information and availability.
### 2 Enter the member id you wish to issue the book to. An assumption is made that you have added this member to the database. Press enter to display the member information. Verify that the book isbn and member information is present before clicking the issue button. If the information isn't accurate, book issuance will not be successful.
### You get helpful error message telling what needs to be done. If the information entered is not valid, you will get an error like the ones on the screen below: The issue button will be disabled.
![image](https://user-images.githubusercontent.com/29076661/38784025-04aa39bc-40d1-11e8-9aae-4ffb51086cf9.png)
### If the information is accurate, you will get a screen like the one shown below:
![image](https://user-images.githubusercontent.com/29076661/38784016-d7f32eec-40d0-11e8-8337-353698b2f8fd.png)

### If the book has already been issued. You get a screen like the one shown below. Available will be “NO”
![image](https://user-images.githubusercontent.com/29076661/38784061-ddf758a8-40d1-11e8-9e42-4188e46a95bf.png)
### Add new member information window
![image](https://user-images.githubusercontent.com/29076661/38784076-18510558-40d2-11e8-81d8-a9d78730d42a.png)
### View Members table when view button is clicked on the hamburger toolbar
![image](https://user-images.githubusercontent.com/29076661/38784087-4d82ab5a-40d2-11e8-9ab5-619ec7360bd2.png)
### Add new book information
![image](https://user-images.githubusercontent.com/29076661/38784096-6cd7baea-40d2-11e8-83a7-bd5c304050bd.png)
### View book table
![image](https://user-images.githubusercontent.com/29076661/38784108-9db64f00-40d2-11e8-927e-d27fa1097ba4.png)
### Setting window
![image](https://user-images.githubusercontent.com/29076661/38784117-d8dd1aa0-40d2-11e8-9175-dd1f6639d07c.png)
### Renewal/Return tab
![image](https://user-images.githubusercontent.com/29076661/38784123-06d5a170-40d3-11e8-8585-f72a5af92aa3.png)
### Enter the book isbn you wish to return or renew in the book isbn text field. Press enter to display the member who has the book and the book information. If the information enter isn't valid you will get an error message like shown below. The renewal and return buttons will be deactivated
![image](https://user-images.githubusercontent.com/29076661/38784150-7aecd9fc-40d3-11e8-8bc1-3c35636703bc.png)
### If the isbn is correct but the book hasn't been issued. You will get the below error. Renewal and return buttons will be deactivate
![image](https://user-images.githubusercontent.com/29076661/38784345-1aa7aeac-40d6-11e8-839b-2aa5c7e1c0c2.png)
### If the book has been issued and isbn number is correct, you will get a screen like the one shown below
![image](https://user-images.githubusercontent.com/29076661/38784384-b711483e-40d6-11e8-81c9-860c4d86e5fa.png)

### To edit or delete book or members. From the member table or book table, right click on the record you would like to 
### and choose either edit or delete from the context menu.

### This app isn't fully completed; I am still working it.
