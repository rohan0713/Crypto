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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class signInFragment extends Fragment {

    EditText email, password;
    FirebaseAuth auth;
    ProgressBar progressBar;
    TextView f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        MaterialButton signIn = view.findViewById(R.id.signIn);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        progressBar = view.findViewById(R.id.progress_bar);
        f = view.findViewById(R.id.forgot);

        auth = FirebaseAuth.getInstance();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.length() == 0 || password.length() == 0) {
                    Toast.makeText(view.getContext(), "Please enter valid details", Toast.LENGTH_SHORT).show();
                } else {

                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.animate();
                    f.setVisibility(View.GONE);
                    signIn.setVisibility(View.GONE);

                    auth.signInWithEmailAndPassword(email.getText().toString(),
                                    password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        if (auth.getCurrentUser().isEmailVerified()) {
                                            userHelperClass.put(view.getContext(), "login", true);
                                            Intent i = new Intent(view.getContext(), homeScreen.class);
                                            startActivity(i);
                                            requireActivity().finish();
                                        } else {

                                            progressBar.setVisibility(View.GONE);
                                            signIn.setVisibility(View.VISIBLE);
                                            f.setVisibility(View.VISIBLE);

                                            Toast.makeText(view.getContext(), "verify your email address", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {

                                        progressBar.setVisibility(View.GONE);
                                        signIn.setVisibility(View.VISIBLE);
                                        f.setVisibility(View.VISIBLE);

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