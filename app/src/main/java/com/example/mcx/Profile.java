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
  public   String mName,mPassword,mcPassword,mMobile,mRefer;
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


        mMobile=phone;
     //   name.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);




    }
    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public void add(View view) {
        mName=name.getText().toString().trim();
        mPassword=password.getText().toString().trim();
        mcPassword=confirm.getText().toString().trim();
        mRefer=getAlphaNumericString(6);
        if(mcPassword.equals(mPassword)) {
            ProfileC profileC = new ProfileC();
            profileC.setPassword(mPassword);
            profileC.setName(mName);
            profileC.setMobile(mMobile);
            profileC.setRefer(mRefer);
             Toast.makeText(Profile.this,mRefer, Toast.LENGTH_LONG).show();


            String StudentRecordIDFromServer = myRef.push().getKey();
            myRef.child(StudentRecordIDFromServer).setValue(profileC);

            Intent intent = new Intent(Profile.this, Test.class);
            startActivity(intent);
            finish();
            // Showing Toast message after successfully data submit.
            //  Toast.makeText(Profile.this,"Data Inserted Successfully into Firebase Database", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(Profile.this,"Password didnot match!!",Toast.LENGTH_LONG).show();
        }
    }

 }

