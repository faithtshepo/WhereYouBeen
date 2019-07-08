package com.example.whereyaat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

public class SecondMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GoogleSignInClient mGoogleSignInClient;
    Button sign_out;
    TextView nameTv;
    TextView emailTv;
    TextView idTv;
    ImageView photoIv;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);


        
        //Intent appLinkIntent = getIntent();
        //String appLinkAction = appLinkIntent.getAction();
        //Uri appLinkData = appLinkIntent.getData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Home");


        toolbar.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary_dark));
        //toolbar.setTitle("Welcome");



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Open Messages?...", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent inteMes = new Intent(SecondMainActivity.this, BasicMesssagesActivity.class);
                                startActivity(inteMes);
                            }
                        }).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        View navHeaderView = navigationView.getHeaderView(0);

        sign_out = (Button)findViewById(R.id.logout);
        nameTv = (TextView)navHeaderView.findViewById(R.id.nameTv);
        emailTv = (TextView)navHeaderView.findViewById(R.id.emailTv);
        photoIv = (ImageView)navHeaderView.findViewById(R.id.imageView);

        //configure sign in to request sign in email shii
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //build a google sign in with the options specify by gso
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(SecondMainActivity.this);
        if (acct != null)
        {
            String personName = acct.getDisplayName();
            String email = acct.getEmail();
            String familyName = acct.getFamilyName();
            Uri personPhoto = acct.getPhotoUrl();


            nameTv.setText(familyName + "");
            emailTv.setText(email + "");

            //Glide.with(this).load(personPhoto).into(photoIv);
            Glide.with(SecondMainActivity.this)
                    .load(personPhoto)
                    .into(photoIv);


        }else{

            Toast.makeText(this,"Account Null!!",Toast.LENGTH_SHORT);

        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second_main, menu);

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(SecondMainActivity.this,"Successfully signed out! ",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SecondMainActivity.this, MainActivity.class));
                            finish();

                        }
                    });

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_adminPrev) {
            // Handle the camera action
            Intent intentMessage = new Intent(SecondMainActivity.this, DirectoryActivity.class);
            startActivity(intentMessage);


        } else if (id == R.id.nav_history) {

            Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com/maps/timeline?pb"));
            startActivity(browserIntent);



        } else if (id == R.id.nav_mess1) {

            Intent intent = new Intent(SecondMainActivity.this, BasicMesssagesActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
