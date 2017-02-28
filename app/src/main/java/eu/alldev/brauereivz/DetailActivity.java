package eu.alldev.brauereivz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import eu.alldev.brauereivz.adapter.ProductAdapter;
import eu.alldev.brauereivz.database.ProductDatabase;
import eu.alldev.brauereivz.database.RelationDatabase;
import eu.alldev.brauereivz.listener.ProductChangedListener;
import eu.alldev.brauereivz.listener.RelationChangedListener;
import eu.alldev.brauereivz.model.Product;
import eu.alldev.brauereivz.model.Relation;
import eu.alldev.brauereivz.util.Constants;

public class DetailActivity extends AppCompatActivity implements ProductChangedListener, RelationChangedListener {

    ProductDatabase productDatabase = new ProductDatabase();
    RelationDatabase relationDatabase = new RelationDatabase();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Long producerId;

    private List<Relation> relations = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent currentIntent = getIntent();

        setTitle(currentIntent.getStringExtra(Constants.EXTRA_PRODUCER_NAME));
        producerId = currentIntent.getLongExtra(Constants.EXTRA_PRODUCER_ID, 0);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_products);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        productDatabase.addListener(this);
        relationDatabase.addListener(this);
    }

    @Override
    public void productsChanged(List<Product> entities) {
        products = entities;
        filterProducts();
    }

    @Override
    public void relationsChanged(List<Relation> entities) {
        relations = entities;
        filterProducts();
    }

    public boolean isDataComplete() {
        return !relations.isEmpty() && !products.isEmpty();
    }


    public void filterProducts() {
        if (isDataComplete()) {

            //TODO
            // instead of filtering here we should only fetch the necessary relation and
            // products from the database

            Relation currentRelation = null;
            for (Relation r : relations) {
                if (r.getProducer().equals(producerId)) {
                    currentRelation = r;
                    break;
                }
            }

            if (currentRelation != null) {
                List<Product> relatedProducts = new ArrayList<>();
                for (Product p : products) {
                    if (p.getId().equals(currentRelation.getProduct())) {
                        relatedProducts.add(p);
                    }
                }

                mAdapter = new ProductAdapter(relatedProducts);
                mRecyclerView.setAdapter(mAdapter);
            }
        }
    }
}
