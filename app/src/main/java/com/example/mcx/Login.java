package com.example.mcx;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
  public   EditText phone;
    Button submit;
    DatabaseReference reference;
    public String FirebasePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone=(EditText)findViewById(R.id.phone);
        submit=(Button)findViewById(R.id.submit);
        reference= FirebaseDatabase.getInstance().getReference("Profile");

    }
    public void submit(View v){
       final String mobile = phone.getText().toString().trim();



        if(mobile.isEmpty() || mobile.length() < 10){
            phone.setError("Enter a valid mobile");
            phone.requestFocus();
            return;
        }
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot subSnapshot : dataSnapshot.getChildren()) {
                    ProfileC profileC = subSnapshot.getValue(ProfileC.class);
                    String  phone1=phone.getText().toString();
                    //  Toast.makeText(FirstPage.this,phone1 +"   "+password1,Toast.LENGTH_LONG).show();

                    try {
                        FirebasePhone=profileC.getMobile();
                        if(FirebasePhone.equals(phone1)){
                            Toast.makeText(Login.this,"Number already exists!!!",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(Login.this,FirstPage.class);
                            startActivity(intent);
                            finish();
                        }
                        else{

                            Intent intent = new Intent(Login.this, Verify.class);
                            intent.putExtra("mobile", mobile);
                            startActivity(intent);
                            finish();
                        }

                    }
                    catch (NullPointerException e){
                        System.out.print("NullPointerException Caught");

                    }



                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}



