package com.corona.coronazp20t;

import android.content.Context;
import android.content.SharedPreferences;

//nepamirsti pasirasyti pblic
public class User {
    //1.objekto kintamieji(pozymiai, argumentai)
    private String username;
    private String password;
    private String email;
// Shared preferences - privatus duomenys prisijungimui prie aplikacijos
    private SharedPreferences sharedPreferences;
    private static final String PREFERENFEC_PACKAGE_NAME="com.corona.coronazp20";
    private static final String USERNAME_KEY="username";
    private static final String PASSWORD_KEY="password";
    private static final String REMEMBER_KEY="rememberMe";

    //2.konstruktorius
//desiniu peles klavisu generate -> constructor -> pasizymeti visus ir ok
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(Context context){
        this.sharedPreferences=context.getSharedPreferences(User.PREFERENFEC_PACKAGE_NAME,
                Context.MODE_PRIVATE);
    }
    //3.klases metodai: get ir set
//desiniu -> generate -> getters and setters

    public String getUsernameForRegistration() {
        return username;
    }

    public void setUsernameForRegistration(String username) {
        this.username = username;
    }

    public String getPasswordForRegistration() {
        return password;
    }

    public void setPasswordForRegistration(String password) {
        this.password = password;
    }

    public String getEmailForRegistration() {
        return email;
    }

    public void setEmailForRegistration(String email) {
        this.email = email;
    }
    public String getUsernameForLogin(){
        return this.sharedPreferences.getString(USERNAME_KEY, "");
    }
    public void setUsernameForLogin(String username){
        this.sharedPreferences.edit().putString(USERNAME_KEY, username).commit();
    }
    public String getPasswordLogin(){
        return this.sharedPreferences.getString(PASSWORD_KEY, "");
    }
    public void setPasswordForLogin(String password){
        this.sharedPreferences.edit().putString(PASSWORD_KEY, password).commit();
    }
    public boolean isRememberedForLogin(){
        return this.sharedPreferences.getBoolean(REMEMBER_KEY, false);
    }
    public void setRemembermeKeyForLogin(boolean remembermeKey){
        this.sharedPreferences.edit().putBoolean(REMEMBER_KEY, remembermeKey).commit();
    }
}
