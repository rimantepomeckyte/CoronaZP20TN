package com.corona.coronazp20t;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText usernametext, passwordtext;
    Button loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //visas kodas rasomas po sito komentaro

        Button loginbutton = (Button) findViewById(R.id.loginbutton);
        //cia istraukiamas elementas is vaizdo

        final EditText usernametext = (EditText) findViewById(R.id.usernametext); //edit text yra neapdirbtas formatas, todel isvedant reikia rasyti getText toString
        final EditText passwordtext = (EditText) findViewById(R.id.passwordtext);

        final CheckBox rememberme = (CheckBox) findViewById(R.id.rememberme);
        // bus konstruojamas vartotojo objektas perduodant context'a
        final User user = new User(LoginActivity.this);
        //patikriname, ar paskutini karta checkbox'as buvo pazymetas ar ne
        rememberme.setChecked(user.isRememberedForLogin());

        if (rememberme.isChecked()) {//jeigu checkbox'as buvo pazymetas - vartotojas norejo kad jo duomenys butu issaugoti
            usernametext.setText(user.getUsernameForLogin(), TextView.BufferType.EDITABLE);
            passwordtext.setText(user.getUsernameForLogin(), TextView.BufferType.EDITABLE);
        } else {//jeigu checbox'as buvo nepazymetas - vartotojas nenorejo kad jo duomenys butu issaugota
            usernametext.setText("", TextView.BufferType.EDITABLE);
            passwordtext.setText("", TextView.BufferType.EDITABLE);
        }
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cia bus vykdomas kodas po button paspausdimo
               /* Toast.makeText(LoginActivity.this,
                        "Prisijungimo vardas:" + usernametext.getText().toString() + "\n" +
                             "Slaptazodis:" + passwordtext.getText().toString(),
                        Toast.LENGTH_SHORT).show();*/
                if (Validation.isValidUsername(usernametext.getText().toString())) {
                    //ketinimas pereiti i paieskos langa
                    user.setUsernameForLogin(usernametext.getText().toString());
                    user.setPasswordForLogin(passwordtext.getText().toString());
                    if (rememberme.isChecked()) {
                        user.setRemembermeKeyForLogin(true);
                    }else{
                        user.setRemembermeKeyForLogin(false);
                    }

                    Intent goToSearchActivity = new Intent(LoginActivity.this,
                            SearchActivity.class);
                    startActivity(goToSearchActivity);
                } else {//kai duomenys neatitinka sablono/reikalavimo
                    usernametext.setError(getResources().getString(R.string.login_invalid_username));
                    usernametext.requestFocus();//sufokusuoja ties kuriuo yra klaida ir ismeta pranesima

                }
                Button registerbutton = (Button) findViewById(R.id.registerbutton);
                registerbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RegisterActivity();
                    }
                });

            }

            public void RegisterActivity() {
                Intent goRegisterActivity = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(goRegisterActivity);
            }
        });
    }

}
