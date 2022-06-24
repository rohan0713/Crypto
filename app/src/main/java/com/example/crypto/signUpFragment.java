package com.example.crypto;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signUpFragment extends Fragment {

    EditText email, password, mobile;
    FirebaseAuth auth;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        MaterialButton signUp = view.findViewById(R.id.signUp);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        mobile = view.findViewById(R.id.number);
        progressBar = view.findViewById(R.id.progress_bar);
        auth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.length() == 0 || password.length() == 0 || mobile.length() == 0) {
                    Toast.makeText(view.getContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.animate();
                    signUp.setVisibility(View.GONE);

                    auth.createUserWithEmailAndPassword(email.getText().toString(),
                                    password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        auth.getCurrentUser().sendEmailVerification()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        if (task.isSuccessful()) {

                                                            progressBar.setVisibility(View.GONE);
                                                            signUp.setVisibility(View.VISIBLE);

                                                            Toast.makeText(view.getContext(), "Please Check your mail for verification", Toast.LENGTH_SHORT).show();
                                                        } else {

                                                            progressBar.setVisibility(View.GONE);
                                                            signUp.setVisibility(View.VISIBLE);

                                                            Toast.makeText(view.getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    } else {

                                        progressBar.setVisibility(View.GONE);
                                        signUp.setVisibility(View.VISIBLE);

                                        Toast.makeText(view.getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
//                Intent i = new Intent(view.getContext(), otpActivity.class);
//                startActivity(i);
                }
            }
        });
        return view;
    }
}