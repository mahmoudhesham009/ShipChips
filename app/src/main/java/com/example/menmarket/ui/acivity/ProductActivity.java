package com.example.menmarket.ui.acivity;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.menmarket.R;
import com.example.menmarket.data.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;


public class ProductActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    TextView nameTextView;
    TextView descTextView;
    TextView priceTextView;
    Button addCart;

    Product product;


    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference("cart");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Gson gson = new Gson();
        product = gson.fromJson(getIntent().getStringExtra("product"), Product.class);
        RequestBuilder<Drawable> requestBuilder = Glide.with(this).load(product.getImage());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        imageView = findViewById(R.id.product_imageview);
        nameTextView = findViewById(R.id.product_name_product);
        descTextView = findViewById(R.id.product_description_product);
        priceTextView = findViewById(R.id.product_price);
        addCart = findViewById(R.id.button);

        addCart = findViewById(R.id.button);
        requestBuilder.into(imageView);
        nameTextView.setText(product.getName());
        descTextView.setText(product.getDescription());
        priceTextView.setText(product.getPrice() + " EGP");

        checkConnection();

        addCart.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        addCart.setClickable(false);
        //addCart.setVisibility(View.INVISIBLE);

        putInCart();


    }

    private void putInCart() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child(loadData()).push().setValue(product);
                onBackPressed();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public String loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreference", MODE_PRIVATE);
        return sharedPreferences.getString("userPhone", "");
    }

    public void checkConnection() {
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    addCart.setVisibility(View.VISIBLE);
                    addCart.setClickable(true);
                } else {
                    addCart.setVisibility(View.INVISIBLE);
                    addCart.setClickable(false);
                    Toast.makeText(ProductActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }


}
