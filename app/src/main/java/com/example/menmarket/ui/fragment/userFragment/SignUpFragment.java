package com.example.menmarket.ui.fragment.userFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.menmarket.Helper;
import com.example.menmarket.R;
import com.example.menmarket.data.model.User;
import com.example.menmarket.ui.acivity.SearchProductRecyclerViewActivity;
import com.example.menmarket.ui.fragment.BaseFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpFragment extends BaseFragment {
    Button cancel;
    Button signUp;
    EditText phone;
    EditText name;
    EditText pass;
    EditText repass;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference database = firebaseDatabase.getReference("Users");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inif();
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        signUp = v.findViewById(R.id.signupButton_signUp);
        cancel = v.findViewById(R.id.cancelButton_signUp);
        phone = v.findViewById(R.id.phonEditText_signup);
        name = v.findViewById(R.id.nameEditText_signup);
        pass = v.findViewById(R.id.passEditText_signup);
        repass = v.findViewById(R.id.repassEditText_signup);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });
        checkConnection();
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phone.getText().toString().length() < 11 || !pass.getText().toString().equals(repass.getText().toString()) || name.getText().toString().length() < 5 || pass.getText().toString().length() < 5) {
                    Toast.makeText(getActivity(), "check your inputs", Toast.LENGTH_SHORT).show();
                } else {
                    database.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(phone.getText().toString()).exists()) {
                                Toast.makeText(getActivity(), "Phone registed before", Toast.LENGTH_SHORT).show();
                            } else {
                                User user = new User(name.getText().toString(), pass.getText().toString());
                                database.child(phone.getText().toString()).setValue(user);
                                Toast.makeText(getActivity(), "Ready to LogIn", Toast.LENGTH_SHORT).show();
                                onBack();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getActivity(), "check internet connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public void checkConnection() {
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    signUp.setClickable(true);
                    signUp.setVisibility(View.VISIBLE);


                } else {
                    signUp.setClickable(false);
                    signUp.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void onBack() {
        Helper.replaceFragment(getActivity().getSupportFragmentManager(), R.id.user_fragment_viewer, new StartFragment(), R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
    }
}
