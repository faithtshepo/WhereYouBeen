package com.example.whereyaat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DirectoryActivity extends AppCompatActivity {

    private Button btnCreate;
    private Button btnJoin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        btnCreate = (Button)findViewById(R.id.createChatBox);
        btnJoin = (Button)findViewById(R.id.joinChat);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(DirectoryActivity.this, MessagesActivity.class);
                startActivity(intent2);
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DirectoryActivity.this, CreateActivity.class);
                startActivity(intent);

            }
        });

    }
}
