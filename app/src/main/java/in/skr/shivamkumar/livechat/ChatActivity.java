package in.skr.shivamkumar.livechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference firebaseDatabaseReference;
    ArrayList<ChatData> messageItems;
    AdapterChat adapterChat;
    RecyclerView recyclerView;
    EditText editTextMessage;
    Button buttonSend;
    boolean isDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        Intent intent = getIntent();
        String s = intent.getStringExtra("REFERENCESTRING");
        isDoctor = intent.getBooleanExtra("ISDOCTOR",false);
        messageItems = new ArrayList<>();
        recyclerView = findViewById(R.id.chat_recycler_view);
        adapterChat = new AdapterChat(this,messageItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapterChat);
        firebaseDatabase = FirebaseDatabaseReference.getDatabaseInstance();
        firebaseDatabaseReference = firebaseDatabase.getReference(s);
        firebaseDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatData chatData = dataSnapshot.getValue(ChatData.class);
                messageItems.add(chatData);
                int x = messageItems.size();
                recyclerView.findViewHolderForAdapterPosition(x - 1);
                recyclerView.findViewHolderForLayoutPosition(x-1);
                adapterChat.notifyDataSetChanged();
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
        editTextMessage = findViewById(R.id.editText3);
        buttonSend = findViewById(R.id.button_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatData chatData = new ChatData();
                chatData.message = editTextMessage.getText().toString();
                editTextMessage.setText("");
                chatData.isDoctor = isDoctor;
                Long time = System.currentTimeMillis();
                chatData.timeStamp = time;
                firebaseDatabaseReference.push().setValue(chatData);
            }
        });
    }
}
