package org.example.util;

public class Verification {

    public static  boolean verifyEmail(String email){
        return email.matches("[a-zA-Z\\d+/_@.-]+@[a-z]+[/.][a-z]{2,3}");
    }
   public static boolean verifyPhoneNumber(String phoneNumber){
        if(phoneNumber.startsWith("+")) return phoneNumber.matches("[+][1-9][0-9]{6,12}");
        else return phoneNumber.matches("0[7-9][0-1][0-9]{8}");
    }
    public static boolean verifyPassword(String password){
        return password.matches("[A-Z][a-zA-Z]{4,}[0-9/@-_.?:^&!(){}#*%$]+");
    }

    public static String getExitedPasswordSaltValue(String password){
        String result = "";
        for(int count = 0; count < EncryptPassword.getLength();count++){
            result+=password.charAt(count);
        }
        return result;
    }
    public static String clearSaltValueInPassword(String password){
        String result = "";
        for(int count = EncryptPassword.getLength(); count < password.length();count++){
            result+=password.charAt(count);
        }
        return result;
    }



}
