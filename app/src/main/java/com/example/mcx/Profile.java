package com.example.mcx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.SecureRandom;

import static com.example.mcx.Verify.phone;

public class Profile extends AppCompatActivity {
    EditText name,password,confirm;
  public   String mName,mPassword,mcPassword,mMobile;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    public static final String Database_Path = "Profile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
        confirm=(EditText)findViewById(R.id.confirm);
       myRef = FirebaseDatabase.getInstance().getReference(Database_Path);

        mcPassword=confirm.getText().toString().trim();
        mMobile=phone;
     //   name.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);




    }

    public void add(View view) {
        mName=name.getText().toString().trim();
        mPassword=password.getText().toString().trim();
       ProfileC profileC =  new ProfileC();
       profileC.setPassword(mPassword);
       profileC.setName(mName);
       profileC.setMobile(mMobile);
      //  Toast.makeText(Profile.this,mMobile, Toast.LENGTH_LONG).show();


        String StudentRecordIDFromServer = myRef.push().getKey();
        myRef.child(StudentRecordIDFromServer).setValue(profileC);

         Intent intent=new Intent(Profile.this,Recycler.class);
         startActivity(intent);
         finish();
        // Showing Toast message after successfully data submit.
     //  Toast.makeText(Profile.this,"Data Inserted Successfully into Firebase Database", Toast.LENGTH_LONG).show();

    }
 }

