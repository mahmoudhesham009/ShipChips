package com.example.menmarket.ui.acivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menmarket.R;
import com.example.menmarket.adapter.ProductRecycleViewAdapter;
import com.example.menmarket.adapter.RecyclerViewClickInterface;
import com.example.menmarket.data.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ProductRecyclerViewActivity extends AppCompatActivity implements RecyclerViewClickInterface {
    RecyclerView recyclerView;
    ProductRecycleViewAdapter productRecycleViewAdapter;
    ArrayList<Product> productArrayList;
    RecyclerView.LayoutManager mLayoutManager;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference("product");
    String category;
    TextView categoryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_recycler_view);

        Bundle extras = getIntent().getExtras();
        category=extras.getString("category");

        categoryTextView=findViewById(R.id.categoryTextView);
        categoryTextView.setText(category.toUpperCase());

        productArrayList=new ArrayList<Product>();
        recyclerView=findViewById(R.id.RecyclerView);
        productRecycleViewAdapter=new ProductRecycleViewAdapter(productArrayList,this);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        getProducts();
        checkConnection();



    }

    public void getProducts(){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    if (postSnapshot.getValue(Product.class).getCategory().equals(category))
                        productArrayList.add(postSnapshot.getValue(Product.class));
                }
                recyclerView.setAdapter(productRecycleViewAdapter);
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
                    findViewById(R.id.noInternetimageView).setVisibility(View.INVISIBLE);


                } else {
                    if (productArrayList.size()==0)
                        findViewById(R.id.noInternetimageView).setVisibility(View.VISIBLE);

                    Toast.makeText(ProductRecyclerViewActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Gson gson = new Gson();
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("product", gson.toJson(productArrayList.get(position)));
        startActivity(intent);
    }
}
