package eu.alldev.brauereivz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import eu.alldev.brauereivz.adapter.ProducerAdapter;
import eu.alldev.brauereivz.database.ProducerDatabase;
import eu.alldev.brauereivz.listener.ProducerChangedListener;
import eu.alldev.brauereivz.model.Producer;

public class MainActivity extends AppCompatActivity implements ProducerChangedListener {

    ProducerDatabase producerDatabase = new ProducerDatabase();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_producers);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        producerDatabase.addListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public void producersChanged(List<Producer> entities) {
        mAdapter = new ProducerAdapter(this, entities);
        mRecyclerView.setAdapter(mAdapter);
    }
}
