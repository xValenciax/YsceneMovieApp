package com.example.ycseneapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    ImageView logIn;
    ImageView CreateAccount;
    TextView name, phone, email, password, confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // get sign up information fields
        logIn = (ImageView) findViewById(R.id.imageView4);
        CreateAccount = (ImageView) findViewById(R.id.imageView10);
        name = (TextView) findViewById(R.id.editTextTextEmailAddress4);
        phone = (TextView) findViewById(R.id.editTextTextEmailAddress3);
        email = (TextView) findViewById(R.id.editTextTextEmailAddress);
        password = (TextView) findViewById(R.id.editTextPassword);
        confirmPassword = (TextView) findViewById(R.id.editTextPassword2);

        // a click listener for create account button
        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if all fields in sign up form are filled
                if(validateName(name) && validateEmail(email) && validatePhone(phone) && validatePassword(password)
                        && confirmPassword(password, confirmPassword)){
                    Log.d("TAGCreate", "onClick: Here");
                    // create a new user with entered data
                    User user = new User(name.getText().toString(), email.getText().toString(), phone.getText().toString(),
                            password.getText().toString());
                    // open a database connection
                    db_User database = new db_User(SignupActivity.this);
                    // add user's data to database
                    database.AddUser(user);

                    Toast.makeText(SignupActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();


                    // start the login activity
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        // a click listener to go to login page
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    /*
    *   this part has all the data validation functions
    */
    public boolean validateName(TextView name){
        String val = name.getText().toString();

        if(val.matches("^[a-zA-Z]+$") && val.length() > 0)
            return true;
        else if(val.length() <= 0)
            Toast.makeText(SignupActivity.this, "Name Field Is Required !", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(SignupActivity.this, "Name Must Contain Only Letters!", Toast.LENGTH_SHORT).show();
        return false;
    }

    public boolean validatePhone(TextView phone){
        String phoneVal = phone.getText().toString();

        if(phoneVal.matches("^01[0125][0-9]{8}$") && phoneVal.length() > 0)
            return true;
        else if(phoneVal.length() <= 0)
            Toast.makeText(SignupActivity.this, "Phone Field Is Required!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(SignupActivity.this, "Your Phone Number is Not valid !\nmust be (+20)", Toast.LENGTH_SHORT).show();
        return false;
    }

    public boolean validateEmail(TextView email){
        String emailVal = email.getText().toString();

        if(emailVal.matches("^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$"))
            return true;
        else if(emailVal.length() <= 0)
            Toast.makeText(SignupActivity.this, "An Email Must Be Provided!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(SignupActivity.this, "Invalid Email Address!", Toast.LENGTH_SHORT).show();
        return false;
    }

    public boolean validatePassword(TextView pass){
        String passVal = pass.getText().toString();

        if(passVal.matches("^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]).{8,})\\S$") && passVal.length() > 8)
            return true;
        else if(passVal.length() <= 0)
            Toast.makeText(SignupActivity.this, "A Password Must Be Provided!", Toast.LENGTH_SHORT).show();
        else if(passVal.length() < 8)
            Toast.makeText(SignupActivity.this, "Password Must Be At Least 8 Characters Long", Toast.LENGTH_SHORT).show();
        else Toast.makeText(SignupActivity.this, "Password Must Contain 1 Uppercase Letter, 1 Lowercase Letter, and a digit",
                    Toast.LENGTH_SHORT).show();
        return false;
    }

    public boolean confirmPassword(TextView pass, TextView confirmPass){
        if(pass.getText().toString().equals(confirmPass.getText().toString()))
            return true;
        else
            Toast.makeText(SignupActivity.this, "Password Don't Match!", Toast.LENGTH_SHORT).show();
        return false;
    }
}