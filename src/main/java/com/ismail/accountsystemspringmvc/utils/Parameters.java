package com.ismail.accountsystemspringmvc.utils;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class Parameters {
    public static final String alphabets = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * This method is to generate a unique code for the accounts
     * @return String
     */
    public static String generateAccountCode(){
        String randomString ="";
        Random random = new Random();
        for(int i = 0 ; i<6;i++){
            int r = random.nextInt(alphabets.length());
            randomString=randomString+alphabets.charAt(r);
        }
        String localTime = LocalTime.now()+"";
        localTime=localTime.replaceAll(":","");
        localTime=localTime.replaceAll("\\.","");

        String localDate = LocalDate.now()+"";
        localDate=localDate.replaceAll("-","");

        return randomString+localTime+"|"+localDate;
    }

    /**
     * This method is for generating a unique code for the user
     * @param userName as String
     * @return String
     */
    public static String generateUserCode (String userName){
        String randomString ="";
        Random random = new Random();
        for(int i = 0 ; i<6;i++){
            int r = random.nextInt(alphabets.length());
            randomString=randomString+alphabets.charAt(r);
        }
        String localTime = LocalTime.now()+"";
        localTime=localTime.replaceAll(":","");
        localTime=localTime.replaceAll("\\.","");

        String localDate = LocalDate.now()+"";
        localDate=localDate.replaceAll("-","");

        return randomString+localTime+userName.toUpperCase()+localDate;
    }

    public static Integer generateRandomInteger(int min , int max){
        Random random = new Random();
        return random.nextInt(max-min)+min;
    }
}
