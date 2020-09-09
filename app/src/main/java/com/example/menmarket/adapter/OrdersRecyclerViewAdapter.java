package com.example.menmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menmarket.R;
import com.example.menmarket.data.model.Request;

import java.util.ArrayList;

public class OrdersRecyclerViewAdapter extends RecyclerView.Adapter <OrdersRecyclerViewAdapter.OrderViewHolder>{
    ArrayList<Request> requestArrayList=new ArrayList<>();
    Context context;

    public OrdersRecyclerViewAdapter(ArrayList<Request> requestArrayList) {
        this.requestArrayList = requestArrayList;
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView order;
        TextView price ;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            order=itemView.findViewById(R.id.order);
            price=itemView.findViewById(R.id.price);
        }
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        OrderViewHolder orderViewHolder = new OrderViewHolder(v);
        context=parent.getContext();
        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.date.setText(requestArrayList.get(position).getOrderTime());
        String orderDet=null;
        for (int i=0 ; i<requestArrayList.get(position).getNames().size();i++){
            orderDet=orderDet+requestArrayList.get(position).getNum().get(i)+requestArrayList.get(position).getNames().get(i)+"\n";
        }
        holder.date.setText(orderDet);
        holder.price.setText(requestArrayList.get(position).getTotalPrice());

    }


    @Override
    public int getItemCount() {
        return requestArrayList.size();
    }
}
