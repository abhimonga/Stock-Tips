package com.example.mcx;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirstPage extends AppCompatActivity {
    public EditText phone,password;
   //  public  String  phone1,password1;
 //   final FirebaseDatabase database;
    DatabaseReference reference;
    public String FirebasePhone,FirebasePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        phone=(EditText)findViewById(R.id.phone);
        password=(EditText)findViewById(R.id.password);


        reference= FirebaseDatabase.getInstance().getReference("Profile");

    }

    public void Signup(View view) {
        Intent intent=new Intent(FirstPage.this,Login.class);
        startActivity(intent);
    }

    public void login(View view) {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot subSnapshot : dataSnapshot.getChildren()) {
                    ProfileC profileC = subSnapshot.getValue(ProfileC.class);
                    String  phone1=phone.getText().toString();
                    String  password1=password.getText().toString();
                  //  Toast.makeText(FirstPage.this,phone1 +"   "+password1,Toast.LENGTH_LONG).show();

                    try {
                          FirebasePassword=profileC.getPassword();
                          FirebasePhone=profileC.getMobile();
                          if(FirebasePhone.equals(phone1) && FirebasePassword.equals(password1)){
                              Intent intent=new Intent(FirstPage.this,Test.class);
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
