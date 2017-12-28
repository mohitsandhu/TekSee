package com.parse.TekSee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class RadioOption extends AppCompatActivity {

    Boolean exist = false, New = false;
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.existing: {
                if (checked) {
                    exist = true;
                    New = false;
                }
                break;
            }
            case R.id.newuser: {
                if (checked) {
                    New = true;
                    exist = false;
                }
                break;
            }
            default:{
                Log.i("Radio", "Not selected");
            }
        }
    }
    public void next(View view){
        if(exist){
            //Toast.makeText(RadioOption.this, "Existing",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(),Login.class);
            startActivity(i);
        }
        else if(New) {
            //Toast.makeText(RadioOption.this, "New",Toast.LENGTH_SHORT).show();
            Intent j = new Intent(getApplicationContext(),Signup.class);
            startActivity(j);
        }
        else {
            Toast.makeText(RadioOption.this, "No Option Selected",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiooption);
    }
}
