package in.skr.shivamkumar.livechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> chatList;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference firebaseDatabaseReference;
    AdapterList adapter;
    String username;
    public Boolean isDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final Intent intent = getIntent();
        username = intent.getStringExtra("USERNAME");
        String ss="shivam";
        if(username.length()>6)
            ss = username.substring(0,7);
        if(ss.equals("doctor"))
            isDoctor = true;
        firebaseDatabase = FirebaseDatabaseReference.getDatabaseInstance();

        recyclerView = findViewById(R.id.list_recycler_view);
        chatList = new ArrayList<>();
        ClickInterface clickInterface = new ClickInterface() {
            @Override
            public void onViewClick(View view, int position) {
                Toast.makeText(ListActivity.this,"Click "+position,Toast.LENGTH_SHORT).show();
                String doctorUsername = chatList.get(position);
                String referenceString = doctorUsername+username;
                Intent intent1 = new Intent(ListActivity.this,ChatActivity.class);
                intent1.putExtra("REFERENCESTRING",referenceString);
                intent1.putExtra("ISDOCTOR",isDoctor);
                startActivity(intent1);
            }
        };
        adapter = new AdapterList(this,chatList,clickInterface);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        forUserFetchData();
    }

    private void forUserFetchData() {
        firebaseDatabaseReference = firebaseDatabase.getReference("LISTOFDOCTORS");
        firebaseDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String ss = dataSnapshot.getValue(String.class);
                chatList.add(ss);
                Toast.makeText(ListActivity.this,ss,Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
