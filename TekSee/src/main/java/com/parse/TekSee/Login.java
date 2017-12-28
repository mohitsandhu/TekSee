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
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {
    EditText username;
    EditText password;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if (i == keyEvent.KEYCODE_ENTER && keyEvent.getAction() == keyEvent.ACTION_DOWN) {
            Login(view);
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

    public void Login (View view) {
        if (username.getText().toString().equals("") || password.getText().toString().equals("")) {

            Toast.makeText(this, "Username and Password are required", Toast.LENGTH_SHORT).show();
        }
        ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
            @Override
            public void done(final ParseUser user, ParseException e) {
                if (user != null) {
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            char temp = String.valueOf(user.getEmail()).charAt(0);
                            if(temp == 'r') {
                                Thread welcomeThread = new Thread() {
                                    @Override
                                    public void run() {
                                        try {
                                            super.run();
                                            sleep(1050);
                                        } catch (Exception e) {

                                        } finally {

                                            Intent i = new Intent(getApplicationContext(),RiderActivity.class);
                                            startActivity(i);
                                            finish();
                                        }
                                    }
                                };
                                welcomeThread.start();
                            } else if(temp == 'd'){
                                Thread welcomeThread = new Thread() {
                                    @Override
                                    public void run() {
                                        try {
                                            super.run();
                                            sleep(1050);
                                        } catch (Exception e) {

                                        } finally {
                                            Intent j = new Intent(getApplicationContext(),ViewRequestsActivity.class);
                                            startActivity(j);
                                            finish();
                                        }
                                    }
                                };
                                welcomeThread.start();
                            }
                        //}
                    //});

                }else {

                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        password.setOnKeyListener(this);

        RelativeLayout background = (RelativeLayout) findViewById(R.id.background);
        ImageView logo = (ImageView) findViewById(R.id.imageView);
        background.setOnClickListener(this);
        logo.setOnClickListener(this);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
