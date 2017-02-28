package eu.alldev.brauereivz.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import eu.alldev.brauereivz.R;
import eu.alldev.brauereivz.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> mDataset;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.tv_product_name);
        }
    }

    public ProductAdapter(List<Product> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        View itemView =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_cardview, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mDataset.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}