package com.example.menmarket.ui.fragment.homeFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menmarket.R;
import com.example.menmarket.adapter.OrdersRecyclerViewAdapter;
import com.example.menmarket.data.model.Product;
import com.example.menmarket.data.model.Request;
import com.example.menmarket.ui.acivity.ProductRecyclerViewActivity;
import com.example.menmarket.ui.fragment.BaseFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrdersFragment extends BaseFragment {
    RecyclerView recyclerView;
    OrdersRecyclerViewAdapter ordersRecyclerViewAdapter;
    ArrayList<Request> requestArrayList=new ArrayList<>();
    RecyclerView.LayoutManager mLayoutManager;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference("orders");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inif();
        View v=inflater.inflate(R.layout.fragment_order,container,false);
        recyclerView=v.findViewById(R.id.order_recycler_view);

        ordersRecyclerViewAdapter=new OrdersRecyclerViewAdapter(requestArrayList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        getProducts();
        checkConnection();


        return v;
    }

    public void getProducts(){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    requestArrayList.add(postSnapshot.getValue(Request.class));
                }
                ordersRecyclerViewAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(ordersRecyclerViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }

    public void checkConnection(){
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {

                } else {
                    Toast.makeText(getActivity(),"No Internet Connection",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
