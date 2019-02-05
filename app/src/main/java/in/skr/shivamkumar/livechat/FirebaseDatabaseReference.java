package in.skr.shivamkumar.livechat;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDatabaseReference {
    private static FirebaseDatabase INSTANCE;

    public static FirebaseDatabase getDatabaseInstance() {

        if (INSTANCE == null) {
            INSTANCE = FirebaseDatabase.getInstance();
            INSTANCE.setPersistenceEnabled(true);
        }

        return INSTANCE;
    }
}
