package com.corona.coronazp20t;

//cia susikuriam atskira klase (atskira failiuka), kad galetume ja iskviesti,
// cia susirasysim koda, kaip ir C++ panasiai su funkcijomis darome

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation { //public yra ribos/matomumas
    // final - dydis galutinis, jo reiksme nesikeis, kokia priskyrem, tokia bus

    public static final String USERNAME_REGEX_PATTERN = "^[a-zA-Z]{3,20}$";

    public static boolean isValidUsername(String username) {
        Pattern pattern = Pattern.compile(USERNAME_REGEX_PATTERN); //sukuriamas sablonas pagal apsirasytas taisykles
        Matcher matcher = pattern.matcher(username); //pagal susikurta sablona palyginami vartotojo ivesti duomenys
        return matcher.find();
        //grazina true, jeigu atitinka sablona, false - priesingu atveju, kai neatitinka
    }

    public static final String PASSWORD_REGEX_PATTERN = "^[a-zA-Z0-9.!@_]{5,20}$";

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public static final String EMAIL_REGEX_PATTERN = "^[a-zA-Z0-9@._-]{10,50}$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}