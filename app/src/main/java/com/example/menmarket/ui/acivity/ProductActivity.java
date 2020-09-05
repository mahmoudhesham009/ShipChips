package com.example.menmarket.ui.acivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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


public class ProductActivity extends AppCompatActivity {
    ImageView imageView;
    TextView nameTextView;
    TextView descTextView;
    Button addCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Gson gson = new Gson();
        Product product = gson.fromJson(getIntent().getStringExtra("product"), Product.class);
        RequestBuilder<Drawable> requestBuilder=Glide.with(this).load(product.getImage());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        imageView=findViewById(R.id.product_imageview);
        nameTextView=findViewById(R.id.product_name_product);
        descTextView=findViewById(R.id.product_description_product);
        addCart=findViewById(R.id.button);
        requestBuilder.into(imageView);
        nameTextView.setText(product.getName());
        descTextView.setText(product.getDescription());

    }
}
