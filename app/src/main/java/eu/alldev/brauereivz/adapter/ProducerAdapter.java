package eu.alldev.brauereivz.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import eu.alldev.brauereivz.DetailActivity;
import eu.alldev.brauereivz.R;
import eu.alldev.brauereivz.model.Producer;
import eu.alldev.brauereivz.util.Constants;

public class ProducerAdapter extends RecyclerView.Adapter<ProducerAdapter.ViewHolder> {
    private List<Producer> dataset;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.tv_producer_name);
        }
    }

    public ProducerAdapter(Context myContext, List<Producer> myDataset) {
        dataset = myDataset;
        context = myContext;
    }

    @Override
    public ProducerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.producer_cardview, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String name = dataset.get(position).getName();
        final Long id = dataset.get(position).getId();

        holder.textView.setText(name);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra(Constants.EXTRA_PRODUCER_NAME, name);
                i.putExtra(Constants.EXTRA_PRODUCER_ID, id);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}