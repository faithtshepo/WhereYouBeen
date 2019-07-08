package com.example.whereyaat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    int RC_SIGN_IN = 0;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    EditText etEmail;
    EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPass);
        Button btnCreate = (Button)findViewById(R.id.btnCreateAcc);


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if(email !=null && email.length() > 0)
                {


                }else{
                    Toast.makeText(MainActivity.this, "Both Fields Have To Be Filled",Toast.LENGTH_SHORT).show();
                }
                     if(password != null && password.length() > 0)
                    {

                    }else{
                        Toast.makeText(MainActivity.this, "Both Fields Have To Be Filled",Toast.LENGTH_SHORT).show();
                    }

                     Toast.makeText(MainActivity.this,"You have to be admin to sign in manually, Use Google Sign in below",Toast.LENGTH_LONG).show();
            }
        });


      /*  Toolbar toolbar = findViewById(R.id.toolbarSignIn);
        // setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_focused));
        toolbar.setTitle("");
        toolbar.setTitleTextColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_default));
*/

        //initiate viewa
        signInButton = findViewById(R.id.sign_in_button);

        //configure signin options to request user info
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        //Build a googlesign in with options specified by the gso
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent,RC_SIGN_IN);

            }
        });





    }

    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        //results returned from running the intent
        if (requestCode == RC_SIGN_IN)
        {   //no need to attach listener request is always completed
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handeSignInResult(task);

        }


    }

    private void handeSignInResult(Task<GoogleSignInAccount> completedTask)
    {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            //signed in sucessfully?show authenticated ui
            startActivity(new Intent(MainActivity.this, SecondMainActivity.class));
        } catch (ApiException e) {
            //this shows the error message
            Log.w("Google Sign in Error","SignInResult : failed code=" + e.getStatusCode());
            Toast.makeText(MainActivity.this, " Failed!", Toast.LENGTH_LONG).show();

        }

    }

    public void onStart() {

        //check for existing acounts
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null)
        {
            startActivity(new Intent(MainActivity.this, SecondMainActivity.class));
        }
        super.onStart();
    }




}
