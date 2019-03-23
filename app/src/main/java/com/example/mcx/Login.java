package com.example.mcx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
  public   EditText phone;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone=(EditText)findViewById(R.id.phone);
        submit=(Button)findViewById(R.id.submit);
    }
    public void submit(View v){
        String mobile = phone.getText().toString().trim();

        if(mobile.isEmpty() || mobile.length() < 10){
            phone.setError("Enter a valid mobile");
            phone.requestFocus();
            return;
        }

        Intent intent = new Intent(Login.this, Verify.class);
        intent.putExtra("mobile", mobile);
        startActivity(intent);



    }
}



