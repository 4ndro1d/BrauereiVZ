package eu.alldev.brauereivz.database;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import eu.alldev.brauereivz.listener.RelationChangedListener;
import eu.alldev.brauereivz.model.Relation;
import eu.alldev.brauereivz.util.Constants;

public class RelationDatabase extends DatabaseCommunicator {

    private List<RelationChangedListener> listeners = new ArrayList<>();
    private List<Relation> relations = new ArrayList<>();

    public RelationDatabase() {
        setupReference();
        setupDatabaseListener();
    }

    private void setupReference() {
        super.setupReference(Constants.DATABASE_RELATION);
    }

    @Override
    public void setupDatabaseListener() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                relations.clear();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    Relation r = new Relation();
                    r.setProducer((Long) childDataSnapshot.child(Constants.FIELD_RELATION_PRODUCER).getValue());
                    r.setProduct((Long) childDataSnapshot.child(Constants.FIELD_RELATION_PRODUCT).getValue());
                    relations.add(r);
                }
                notifyListeners();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("TAG", "Failed to read value.", error.toException());
            }
        });
    }

    public void addListener(RelationChangedListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (RelationChangedListener listener: listeners) {
            listener.relationsChanged(relations);
        }
    }
}
