package com.example.loginsystem;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    TextView userName;
    TextView userEmail;
    TextView userPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Register Account");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragement_register, container, false);

        userName = view.findViewById(R.id.editextPersonNameRegister);
        userEmail = view.findViewById(R.id.editTextEmailAddressRegister);
        userPassword = view.findViewById(R.id.editTextPasswordRegister);


        view.findViewById(R.id.buttonSubmitRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();
                Log.d("register", "name" + name);
                Log.d("register", "email" + email);
                Log.d("register", "password" + password);


                if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                    Toast.makeText(getActivity(), "Password/Email or Password cannot be empty", Toast.LENGTH_SHORT).show();

                } else {
                    DataServices.Account account = DataServices.register(name, email, password);
                    if( account!=null){
                        Toast.makeText(getActivity(), "Successfull Registration", Toast.LENGTH_SHORT).show();
                        mListener.loginIsSuccessful(account);
                    }else {

                        Toast.makeText(getActivity(), "UnSuccessfull Registration", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


        view.findViewById(R.id.buttonCancelRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoLogin();
            }
        });

        return view;
    }


    SignupListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof SignupListener) {
            mListener = (SignupListener) context;
        }else{
            throw  new RuntimeException((context.toString()+"must implement SignupListener"));
        }
    }

    interface SignupListener {
        void loginIsSuccessful(DataServices.Account account);
        void gotoLogin();
    }
}