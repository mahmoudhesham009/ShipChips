package com.example.menmarket.ui.fragment.userFragment;

import android.content.Intent;
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
import com.example.menmarket.ui.acivity.HomeActivity;
import com.example.menmarket.ui.fragment.BaseFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogInFragment extends BaseFragment {

    Button cancel;
    Button logIn;
    EditText pass;
    EditText phone;
    User user = new User();

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference database = firebaseDatabase.getReference("Users");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inif();
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        logIn = v.findViewById(R.id.loginButton_login);
        cancel = v.findViewById(R.id.cancelButton_login);
        phone = v.findViewById(R.id.phonEditText_login);
        pass = v.findViewById(R.id.passEditText_login);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (phone.getText().toString().length() == 0) {
                            Toast.makeText(getActivity(), "no input", Toast.LENGTH_SHORT).show();
                        } else if (snapshot.child(phone.getText().toString()).exists()) {
                            user = snapshot.child(phone.getText().toString()).getValue(User.class);
                            if (user.getPassWord().equals(pass.getText().toString())) {
                                Toast.makeText(getActivity(), "Welcome, " + user.getName(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), HomeActivity.class));
                            } else {
                                Toast.makeText(getActivity(), "check password", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getActivity(), "No account with that number", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }

    @Override
    public void onBack() {
        Helper.replaceFragment(getActivity().getSupportFragmentManager(), R.id.user_fragment_viewer, new StartFragment(), R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);

    }
}
