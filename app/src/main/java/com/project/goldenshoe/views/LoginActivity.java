package com.project.goldenshoe.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.goldenshoe.Model.Users;
import com.project.goldenshoe.R;


public class LoginActivity extends AppCompatActivity {

    //creating login variables
    private EditText inputUsername, inputPassword;
    private Button loginButton;
    private ProgressDialog loadingBar;

    private String parentDbName = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //assigning variables to button and editTexts in login layout
        loginButton = (Button) findViewById(R.id.login_btn);
        inputPassword = (EditText) findViewById(R.id.login_password_input);
        inputUsername = (EditText) findViewById(R.id.login_username_input);
        loadingBar = new ProgressDialog(this);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    /**
     * login logic
     *
     * if user forgets to write in username or password, the application will tell the user to write in missing credentials
     *
     * uses the AllowAccessToAccount method to check if users credentials are correct
     */
    private void loginUser() {

        String name = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        //checking if user has written in username and password
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();


        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();


        } else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, logging you in...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            allowAccessToAccount(name, password);
        }
    }

    /**
     * passes in username and password from login user.
     *
     * checks if users credentials match inside the database.
     *
     * if they match, the user can access the application
     * else user will be denied
     *
     * @param name
     * @param password
     */
    private void allowAccessToAccount(final String name, final String password) {
        final DatabaseReference rootRef;

        rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(parentDbName).child(name).exists()) {

                    Users usersData = snapshot.child(parentDbName).child(name).getValue(Users.class);
                   //checking if users username and password matches the credentials in firebase
                    if (usersData.getUsername().equals(name)) {
                        if (usersData.getPassword().equals(password)) {
                            Toast.makeText(LoginActivity.this, "You have successfully logged in...", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }

                    }


                } else {
                    Toast.makeText(LoginActivity.this, "Account with this name:  " + name + " does not exists ", Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();
                    Toast.makeText(LoginActivity.this, "You need to create a new account", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}