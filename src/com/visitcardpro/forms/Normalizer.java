package com.visitcardpro.forms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Normalizer {
    public static boolean checkEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String normalizeEmail(String email) {
        email = email.trim();
        return email.toLowerCase();
    }

    public static boolean ckeckPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        String regex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static void normalizePhoneNumber(String phoneNumber) {
        phoneNumber.trim();
        phoneNumber.replace(" ", "");
        phoneNumber.replace(".", "");
        phoneNumber.replace("-", "");
    }

    public static boolean checkProperNoun(String properNoun) {
        if (properNoun == null) {
            return false;
        }
        String regex = "^[a-zA-Z -.\']{2,32}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(properNoun);
        return matcher.matches();
    }

    public static String normalizeProperNoun(String properNoun) {
        properNoun.trim();
        return properNoun.substring(0,1).toUpperCase() + properNoun.substring(1).toLowerCase();
    }

    public static boolean checkUrl(String url) {
        if (url == null) {
            return false;
        }
        String regex = "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    public static void normalizeUrl(String url) {
        url.trim();
    }

    public static boolean checkPassword(String password) {
        if (password == null) {
            return false;
        }

        //(?=.*[0-9]) Au moins un chiffre
        //(?=.*[a-z]) Au moins une lettre minuscule
        //(?=.*[A-Z]) Au moins une lettre maj
        //(?=.*[@#$%^&+=]) Au moins un carac spé
        //(?=\\S+$) Pas d'espace
        //.{8,} Au moins 8 caracs

        String regex = "^(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
