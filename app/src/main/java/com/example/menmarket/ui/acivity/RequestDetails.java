package com.example.menmarket.ui.acivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menmarket.Helper;
import com.example.menmarket.R;
import com.example.menmarket.data.model.CartProduct;
import com.example.menmarket.data.model.Product;
import com.example.menmarket.data.model.Request;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class RequestDetails extends AppCompatActivity {
    ArrayList<CartProduct> cartProduct=new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> num = new ArrayList<>();
    String price;
    String phone;
    String address;

    Request request;

    TextView phoneTextView;
    TextView addressTextView;
    Button order;

    String userphone;

    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference("orders");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);

        phoneTextView=findViewById(R.id.phone_request);
        addressTextView=findViewById(R.id.address_request);
        order=findViewById(R.id.orderButton);

        Gson gson = new Gson();
        cartProduct= gson.fromJson(getIntent().getStringExtra("requestProduct"), new TypeToken<List<CartProduct>>(){}.getType());

        for (int i=0 ; i<cartProduct.size();i++){
            names.add(cartProduct.get(i).getProduct().getName());
            num.add(String.valueOf(cartProduct.get(i).getNumberOfProduct()));
        }

        price=getIntent().getStringExtra("price");
        phone=phoneTextView.getText().toString();
        address=addressTextView.getText().toString();

        request=new Request(names,num,price,phone,address,null,null);
        userphone= loadData();

        checkConnection();

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phone.trim().length()==11){
                    order.setClickable(false);
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child(userphone).push().setValue(request);
                            firebaseDatabase.getReference("cart").child(userphone).removeValue();
                            startActivity(new Intent(getBaseContext(),HomeActivity.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else {
                    Toast.makeText(RequestDetails.this,"Phone number must contain 11 number",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public String loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreference", Context.MODE_PRIVATE);
        return sharedPreferences.getString("userPhone", "");
    }

    public void checkConnection(){
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    order.setVisibility(View.VISIBLE);
                    order.setClickable(true);

                } else {
                    order.setVisibility(View.INVISIBLE);
                    order.setClickable(false);
                    Toast.makeText(RequestDetails.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
