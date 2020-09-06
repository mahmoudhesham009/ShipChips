package com.example.menmarket.ui.fragment.homeFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menmarket.R;
import com.example.menmarket.adapter.CartRecyclerViewAdapter;
import com.example.menmarket.data.model.CartProduct;
import com.example.menmarket.data.model.Product;
import com.example.menmarket.ui.acivity.ProductActivity;
import com.example.menmarket.ui.fragment.BaseFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartFragment extends BaseFragment {

    RecyclerView recyclerView;
    CartRecyclerViewAdapter cartRecyclerViewAdapter;
    ArrayList<CartProduct> cartProductArrayList;
    RecyclerView.LayoutManager mLayoutManager;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference("cart");
    TextView totalPrice;
    Button request;

    String userPhone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inif();
        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        userPhone=loadData();

        cartProductArrayList = new ArrayList<CartProduct>();
        recyclerView = v.findViewById(R.id.cart_recycler_view);
        totalPrice=v.findViewById(R.id.total_price);
        request=v.findViewById(R.id.request_button);
        totalPrice.setVisibility(View.INVISIBLE);
        request.setClickable(false);
        request.setVisibility(View.INVISIBLE);

        cartRecyclerViewAdapter = new CartRecyclerViewAdapter(cartProductArrayList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        getProducts();
        checkConnection();
        return v;
    }


    public void getProducts() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.child(userPhone).getChildren()) {
                    cartProductArrayList.add(new CartProduct(postSnapshot.getValue(Product.class), 1));
                }

                for (int i = 0; i<cartProductArrayList.size(); i++) {
                    for (int j = i+1; j<cartProductArrayList.size(); j++){
                        if (cartProductArrayList.get(i).getProduct().getName().equals(cartProductArrayList.get(j).getProduct().getName())){
                            int num = cartProductArrayList.get(i).getNumberOfProduct();
                            cartProductArrayList.get(i).setNumberOfProduct(++num);
                            cartProductArrayList.remove(j);
                            j--;
                        }
                    }
                }

                int price=0;
                totalPrice.setVisibility(View.VISIBLE);
                request.setClickable(true);
                request.setVisibility(View.VISIBLE);

                for (int i=0; i<cartProductArrayList.size();i++){
                    price=price+cartProductArrayList.get(i).getNumberOfProduct()*Integer.valueOf(cartProductArrayList.get(i).getProduct().getPrice());
                }
                totalPrice.setText(Integer.toString(price)+" EGP");

                recyclerView.setAdapter(cartRecyclerViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }

    public String loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPreference", Context.MODE_PRIVATE);
        return sharedPreferences.getString("userPhone", "");
    }

    public void checkConnection() {
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    request.setClickable(true);
                    request.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    request.setClickable(false);
                    request.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}
