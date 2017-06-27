package com.tntadvance.firebasecrud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference firebaseReference;
    private EditText edName;
    private Spinner spinGroup;
    private Button btnAdd;
    private ListView listView;
    private List<UserDao> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();
        initFirebase();
    }

    private void initFirebase() {
        firebaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private void initInstances() {
        edName = (EditText) findViewById(R.id.edName);
        spinGroup = (Spinner) findViewById(R.id.spinGroup);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);

        users = new ArrayList<>();

    }

    @Override
    public void onClick(View view) {
        if (view==btnAdd){
            addUser();
        }
    }

    private void addUser() {
        String name = edName.getText().toString();
        String group = spinGroup.getSelectedItem().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {
            String id = firebaseReference.child("users").push().getKey();

            UserDao user = new UserDao();
            user.setId(id);
            user.setName(name);
            user.setGroup(group);

            firebaseReference.child("users").child(id).setValue(user);

            Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();
            edName.setText("");
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }

//    private void showArtist() {
//        Query query = databaseReference.child("users");
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                users.clear();
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    UserDao user = postSnapshot.getValue(UserDao.class);
//                    users.add(user);
//                }
//                adapter = new UserAdapter(users);
//                listView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
}
