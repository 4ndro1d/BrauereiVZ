package eu.alldev.brauereivz.database;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import eu.alldev.brauereivz.listener.ProductChangedListener;
import eu.alldev.brauereivz.model.Product;
import eu.alldev.brauereivz.util.Constants;

public class ProductDatabase extends DatabaseCommunicator {

    private List<ProductChangedListener> listeners = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    public ProductDatabase() {
        setupReference();
        setupDatabaseListener();
    }

    private void setupReference() {
        super.setupReference(Constants.DATABASE_PRODUCT);
    }

    @Override
    public void setupDatabaseListener() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                products.clear();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    Product p = new Product();
                    p.setType((String) childDataSnapshot.child(Constants.FIELD_TYPE).getValue());
                    p.setId((Long) childDataSnapshot.child(Constants.FIELD_ID).getValue());
                    products.add(p);
                }
                notifyListeners();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("TAG", "Failed to read value.", error.toException());
            }
        });
    }

    public void addListener(ProductChangedListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (ProductChangedListener listener: listeners) {
            listener.productsChanged(products);
        }
    }
}
