package com.example.menmarket.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.menmarket.R;
import com.example.menmarket.data.model.Product;
import com.example.menmarket.ui.acivity.ProductRecyclerViewActivity;

import java.util.ArrayList;

public class ProductRecycleViewAdapter extends RecyclerView.Adapter<ProductRecycleViewAdapter.ViewHolder> {
    private ArrayList<Product> productArrayList;
    RecyclerViewClickInterface recyclerViewClickInterface;
    Context context;

    public ProductRecycleViewAdapter(ArrayList<Product> productArrayList,RecyclerViewClickInterface recyclerViewClickInterface ) {
        this.productArrayList = productArrayList;
        this.recyclerViewClickInterface = recyclerViewClickInterface;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        context=parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product currentItem = productArrayList.get(position);
        //holder.productImageView.setImageResource(currentItem.getImage());

        Glide.with(context).load(currentItem.getImage()).into(holder.productImageView);

        holder.nameTextView.setText(currentItem.getName());
        holder.priceTextView.setText(currentItem.getPrice()+" EGP");

    }


    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView productImageView;
        public TextView nameTextView;
        public TextView priceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView=itemView.findViewById(R.id.product_image_recycler);
            nameTextView=itemView.findViewById(R.id.product_name_recycler);
            priceTextView=itemView.findViewById(R.id.price_recycler);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
