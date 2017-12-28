package com.parse.TekSee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Signup extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {
    EditText username;
    EditText password;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }


    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if (i == keyEvent.KEYCODE_ENTER && keyEvent.getAction() == keyEvent.ACTION_DOWN) {
            signup(view);
        }
        return false;
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.background || view.getId() == R.id.imageView) {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
    public void signup(View view) {
        Switch userType = (Switch) findViewById(R.id.switch2);
        if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(this, "Username/Password required", Toast.LENGTH_SHORT).show();
        } else {
            ParseUser user = new ParseUser();
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            if(String.valueOf(userType.isChecked()).equals("true")) {
                String email = "driver"+String.valueOf(user.getUsername())+"@email.com";
                user.setEmail(email);
            } else{
                String email = "rider"+String.valueOf(user.getUsername())+"@email.com";
                user.setEmail(email);
            }
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {

                        //Log.i("Switch Value", String.valueOf(userType.isChecked()));
                        Toast.makeText(Signup.this, "Signup Successful!", Toast.LENGTH_SHORT).show();
                        Thread welcomeThread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    super.run();
                                    sleep(1050);
                                } catch (Exception e) {

                                } finally {

                                    Intent i = new Intent(getApplicationContext(),Login.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        };
                        welcomeThread.start();
                    } else {

                        Toast.makeText(Signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        if(e.getMessage().equals("Account already exists for this username.")) {
                            Thread welcomeThread = new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        super.run();
                                        sleep(1050);
                                    } catch (Exception e) {

                                    } finally {

                                        Intent i = new Intent(getApplicationContext(),Login.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            };
                            welcomeThread.start();
                        }
                    }
                }
            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        password.setOnKeyListener(this);
        RelativeLayout background = (RelativeLayout) findViewById(R.id.background);
        ImageView logo = (ImageView) findViewById(R.id.imageView);
        background.setOnClickListener(this);
        logo.setOnClickListener(this);
    }
}
