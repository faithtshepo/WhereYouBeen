package com.example.whereyaat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseHelper {

    private DatabaseReference db;
    private Boolean saved;
    private ArrayList<Person> lists = new ArrayList<>();

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    public Boolean save(Person person)
    {
        if(person == null)
        {
            saved = false;
        }else
        {
            try {
                db.child("List").push().setValue(person);
                saved = true;
            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved = false;
            }
        }


        return saved;
    }

    public  void fetchData(DataSnapshot dataSnapshot)
    {
        lists.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            Person ps = ds.getValue(Person.class);
            lists.add(ps);
        }
    }

    public ArrayList<Person> retrieve()
    {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return lists;
    }
}
