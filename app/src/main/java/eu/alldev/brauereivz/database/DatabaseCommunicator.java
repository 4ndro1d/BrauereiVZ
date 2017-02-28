package eu.alldev.brauereivz.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

 abstract class DatabaseCommunicator {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    void setupReference(String databaseName){
        databaseReference = database.getReference(databaseName);
    }

    public abstract void setupDatabaseListener();
}
