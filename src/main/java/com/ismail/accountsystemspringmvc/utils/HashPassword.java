package com.ismail.accountsystemspringmvc.utils;

public class HashPassword {
    public static char getTheEquivalentChar(char c) {
        if(c=='\'') return 'à';
        if(c=='à') return '\'';
        String alphabetOne  ="abcdefghijklmnopqrstuvwxyz";
        String alphabetTwo  ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphaNumeric ="&;,:§!%*$£µ><?¨^~#{}[]=+°@";
        String alphaNumeric2="0.1-2/3ç4_5|6 7é8è9ôêùû•)(";

        for(int i = 0 ; i<alphabetOne.length();i++) {
            if(alphabetOne.charAt(i)==c) {
                return alphaNumeric.charAt(i);
            }
        }
        for(int i = 0 ; i<alphabetTwo.length();i++) {
            if(alphabetTwo.charAt(i)==c) {
                return alphaNumeric2.charAt(i);
            }
        }
        for(int i = 0 ; i<alphaNumeric.length();i++) {
            if(alphaNumeric.charAt(i)==c) {
                return alphabetOne.charAt(i);
            }
        }
        for(int i = 0 ; i<alphaNumeric2.length();i++) {
            if(alphaNumeric2.charAt(i)==c) {
                return alphabetTwo.charAt(i);
            }
        }

        return 'c';
    }
    public static String cryptAndDecryptPassword(String string)
    {
        String str = string.toString();
        String result = "";

        for(int i = 0 ; i<str.length();i++) {
            char c = getTheEquivalentChar(str.charAt(i));
            result = result +c;
        }

        return result;
    }
}
