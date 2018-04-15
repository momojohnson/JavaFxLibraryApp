package com.momo.utils;


import javafx.scene.control.DatePicker;
import javafx.scene.control.TextInputControl;

public class Validator
{


    // Checks if a ui is empty
   public   static <T extends TextInputControl> boolean isValidData(T control){
        if(control.getText().equals("") || control.getText().trim().equals("")){
            return false;
        }
        return true;
    }


    // Validate first name. First Name can only consist of character
    public static boolean validateFirstName(TextInputControl firstName){

        return firstName.getText().matches("[A-Z][a-zA-Z]*");
    }
    // Validate last name
   public static boolean validateLastName(TextInputControl lastName){
        return lastName.getText().matches("^[^\\s]+,?(\\s[^\\s]+)*$");
    }

    // Validate City
    public  static boolean validateCity(TextInputControl city){
        return city.getText().matches("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$");
    }

    // Validate Book Title
    public static boolean validateBookEdition(TextInputControl bookEdition){
        return bookEdition.getText().matches("\\d{1,2}");
    }

    // Validate State
    public static boolean validateState(TextInputControl control){
        return control.getText().matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
    }

    // Validate copyright year. Can only be 4 numbers
    public  static boolean validateCopyRightYear(TextInputControl copyRight)
    {
        return copyRight.getText().matches("\\d{4}");
    }

    // Validate isbn. Can be only 10 or 13 valid isbn number
    public static boolean bookIsbn(TextInputControl isbn){

        return isbn.getText().matches("^(?:ISBN(?:-1[03])?:?●)?(?=[0-9X]{10}$|(?=(?:[0-9]+[-●]){3})" +
                "[-●0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[-●]){4})[-●0-9]{17}$)" +
                "(?:97[89][-●]?)?[0-9]{1,5}[-●]?[0-9]+[-●]?[0-9]+[-●]?[0-9X]$");
    }

    // Validate Book Title
    public static boolean validateBookTitle(TextInputControl control){
        return control.getText().matches("^[A-Za-z0-9\\s?\\-_,\\.:;()''\"\"]+$");
    }

    // Validate Date
    public static boolean validateDate(DatePicker picker){
        return picker.getValue() != null;

    }
    // Validate memberId
    public static boolean validateMemberId(TextInputControl control){
        return control.getText().matches("[a-zA-Z0-9]+");
    }

    // Validate street Address
    public static boolean validateStreetAddress(TextInputControl control){
        return !control.getText().isEmpty();
    }
    public static boolean validateZipCode(TextInputControl control){

        return control.getText().matches("\\d{5}");
    }

    //Validate phone number
    public static boolean validatePhoneNumber(TextInputControl control){
        return control.getText().matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}");
    }
    // Validate email address

    public static boolean validateEmail(TextInputControl control){
        return control.getText().matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
    }


}
