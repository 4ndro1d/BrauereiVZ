package eu.alldev.brauereivz.database;


import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import eu.alldev.brauereivz.listener.ProducerChangedListener;
import eu.alldev.brauereivz.model.Producer;
import eu.alldev.brauereivz.util.Constants;

public class ProducerDatabase extends DatabaseCommunicator {

    private List<ProducerChangedListener> listeners = new ArrayList<>();
    private List<Producer> producers = new ArrayList<>();

    public ProducerDatabase() {
        setupReference();
        setupDatabaseListener();
    }

    private void setupReference() {
        super.setupReference(Constants.DATABASE_PRODUCER);
    }

    @Override
    public void setupDatabaseListener() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                producers.clear();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    Producer p = new Producer();
                    p.setName((String) childDataSnapshot.child(Constants.FIELD_NAME).getValue());
                    p.setId((Long) childDataSnapshot.child(Constants.FIELD_ID).getValue());
                    producers.add(p);
                }
                notifyListeners();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("TAG", "Failed to read value.", error.toException());
            }
        });
    }


    public void addListener(ProducerChangedListener listener) {
        listeners.add(listener);
    }


    private void notifyListeners() {
        for (ProducerChangedListener listener : listeners) {
            listener.producersChanged(producers);
        }
    }
}
