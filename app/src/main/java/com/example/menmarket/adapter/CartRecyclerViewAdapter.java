package com.example.menmarket.adapter;

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
import com.example.menmarket.data.model.CartProduct;

import java.util.ArrayList;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<CartProduct> cartProducts ;

    public CartRecyclerViewAdapter(ArrayList<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_view, parent, false);
        CartRecyclerViewAdapter.ViewHolder viewHolder = new CartRecyclerViewAdapter.ViewHolder(v);
        context=parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartProduct currentItem = cartProducts.get(position);
        //holder.productImageView.setImageResource(currentItem.getImage());

        Glide.with(context).load(currentItem.product.getImage()).into(holder.productImageView);

        holder.nameTextView.setText(currentItem.product.getName());
        holder.priceTextView.setText(currentItem.product.getPrice()+" EGP");
        holder.numTextView.setText(Integer.toString(currentItem.getNumberOfProduct()));

    }



    @Override
    public int getItemCount() {
        return cartProducts.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView productImageView;
        public TextView nameTextView;
        public TextView priceTextView;
        public TextView numTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView=itemView.findViewById(R.id.product_image_recycler);
            nameTextView=itemView.findViewById(R.id.product_name_recycler);
            priceTextView=itemView.findViewById(R.id.price_recycler);
            numTextView=itemView.findViewById(R.id.cart_item);

        }
    }
}
