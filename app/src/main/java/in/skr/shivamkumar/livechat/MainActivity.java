package in.skr.shivamkumar.livechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference loginReference;
    Integer key=-1;
    HashMap<String,Integer> allUsersMap;
    HashMap<String,String> allPasswordsMap;
    LoginCredentials loginCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabaseReference.getDatabaseInstance();
        loginReference = database.getReference("logindataencryption");
        loginReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                loginCredentials = dataSnapshot.getValue(LoginCredentials.class);
                if(loginCredentials==null){
                    loginCredentials = new LoginCredentials();
                }
                allUsersMap = loginCredentials.getAllUsersMap();
                allPasswordsMap = loginCredentials.getAllPasswordsMap();

                if(allUsersMap==null){
                    Toast.makeText(MainActivity.this,"null class rec",Toast.LENGTH_SHORT).show();
                    loginCredentials.setAllPasswordsMap(new HashMap<String, String>());
                    loginCredentials.setAllUsersMap(new HashMap<String, Integer>());
                    allUsersMap = new HashMap<>();
                    allPasswordsMap = new HashMap<>();
                    loginReference.setValue(loginCredentials);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void login(View view) {
        EditText username = findViewById(R.id.editText);
        EditText password = findViewById(R.id.editText2);
        String uName ="";
        uName = username.getText().toString().trim();
        String pass = "";
        pass = password.getText().toString().trim();
        if(uName!=""&&pass!= ""){
            if(allUsersMap.containsKey(uName)){
                key = allUsersMap.get(uName);
                if(allPasswordsMap.get(uName).equals(pass)){
                    Intent intent = new Intent(this,ListActivity.class);
                    intent.putExtra("USERNAME",uName);
                    startActivity(intent);
                }else
                    Toast.makeText(MainActivity.this,"Wrong Username/Password ",Toast.LENGTH_SHORT).show();


            }else{
                Toast.makeText(MainActivity.this,"Wrong Username/Password ",Toast.LENGTH_SHORT).show();

            }
        }else{
            Toast.makeText(this,"Enter Correctly",Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        EditText username = findViewById(R.id.editText);
        EditText password = findViewById(R.id.editText2);
        String uName ="";
        uName = username.getText().toString().trim();
        String pass = "";
        pass = password.getText().toString().trim();
        if(uName!=""&&pass!= ""){
            //Registeration:
                if(allUsersMap.containsKey(uName)){
                Toast.makeText(this,"User Already Exists",Toast.LENGTH_SHORT).show();
            }else{
                Integer size = allUsersMap.size();
                allUsersMap.put(uName,size+1);
                allPasswordsMap.put(uName,pass);
                loginCredentials.setAllUsersMap(allUsersMap);
                loginCredentials.setAllPasswordsMap(allPasswordsMap);

                loginReference.setValue(loginCredentials);
                Toast.makeText(this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"Enter username and Password to Login",Toast.LENGTH_SHORT).show();
                username.setText("");
                password.setText("");
            }


        }else
            Toast.makeText(this,"Enter UserName and Password",Toast.LENGTH_SHORT).show();
    }
}
