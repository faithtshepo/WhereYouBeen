package com.example.whereyaat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BasicMesssagesActivity extends AppCompatActivity {

    private Button btnSend;
    private Button btnClear;
    private TextView tvDisp;
    private EditText etMes;
    private String mess;
    private String dispName;
    private DatabaseReference db;
    private FirebaseHelper helper;
    private ArrayList<Person> listss = new ArrayList<>();
    private ListView lvMess;
    private MessageListAdapter adapter;
    private Toolbar toolbar;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_messsages);


        //declare the toolbar and set extra settings
        toolbar = (Toolbar)findViewById(R.id.toolbarMes);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitle("Messages");
        toolbar.setTitleTextColor(getResources().getColor(R.color.common_google_signin_btn_text_dark));

        //declare the listview
        lvMess = (ListView)findViewById(R.id.lvMain);
        setSupportActionBar(toolbar);

        //instatiate the db
        db = FirebaseDatabase.getInstance().getReference();

        //initialize the helper and assign the list from helper
        helper = new FirebaseHelper(db);
        listss = helper.retrieve();



        //initialize the adapter and set adapter
        adapter = new MessageListAdapter(BasicMesssagesActivity.this,listss);
        lvMess.setAdapter(adapter);




        //declare and initialize the floating button and assign action to it
        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fab2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInputDialog();
            }
        });



        //get the username from google signin
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if( acct != null){
            dispName = acct.getGivenName();

        }



    }



    public void displayInputDialog()
    {
        //initialize the dialog and assign values and correct settings
        final Dialog d=new Dialog(BasicMesssagesActivity.this);
        d.setTitle("Your Message");
        d.setContentView(R.layout.input_dialog);

        //get the input from textbox and assign action to the buttons
        final EditText etMessage = (EditText) d.findViewById(R.id.etInput);
        Button btnSend = (Button) d.findViewById(R.id.btnsend1);
        Button btnClose = (Button) d.findViewById(R.id.btnClose);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = etMessage.getText().toString();

                //initialize the person class
                Person p = new Person(dispName,message);

                if(message != null && message.length()> 0)
                {
                    if(helper.save(p))
                    {
                        etMessage.setText("");
                        adapter = new MessageListAdapter(BasicMesssagesActivity.this,helper.retrieve());
                        lvMess.setAdapter(adapter);

                    }
                }else
                {
                    Toast.makeText(BasicMesssagesActivity.this,"Message not sent, connection error!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.basic_mess, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.back2) {

            Intent intent = new Intent(BasicMesssagesActivity.this, SecondMainActivity.class);
            startActivity(intent);


            return true;
        }else
            if(id == R.id.clear3){

                DatabaseReference dr = FirebaseDatabase.getInstance().getReference("List");
                dr.removeValue();

                Toast.makeText(BasicMesssagesActivity.this, "All Messages Deleted!!",Toast.LENGTH_SHORT).show();


                return true;
            }

        return super.onOptionsItemSelected(item);
    }

}
