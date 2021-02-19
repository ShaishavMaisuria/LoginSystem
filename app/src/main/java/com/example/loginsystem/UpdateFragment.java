package com.example.loginsystem;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateFragment extends Fragment {


    private static final String ARG_PARAM_UPDATE = "ARG_PARAM_UPDATE";

    // TODO: Rename and change types of parameters
    private DataServices.Account oldAccount;

    public UpdateFragment() {
        // Required empty public constructor
    }


    public static UpdateFragment newInstance(DataServices.Account oldAccount) {
        UpdateFragment fragment = new UpdateFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_UPDATE, oldAccount);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            oldAccount =(DataServices.Account) getArguments().getSerializable(ARG_PARAM_UPDATE);
        }
    }

    TextView emailTextView;
    TextView passwordView;
    TextView nameView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_update, container, false);

       emailTextView= view.findViewById(R.id.textViewEmailUpdate);
        nameView=view.findViewById(R.id.editTextPersonNameUpdate);
        passwordView=view.findViewById(R.id.editTextTextPasswordUpdate);

        emailTextView.setText(oldAccount.getEmail().toString());
        passwordView.setText(oldAccount.getPassword().toString());
        nameView.setText(oldAccount.getName().toString());

        getActivity().setTitle("Update Account");
        view.findViewById(R.id.buttonSubmitUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               nameView=view.findViewById(R.id.editTextPersonNameUpdate);
//                passwordView=view.findViewById(R.id.editTextTextPasswordUpdate);
                String newName=nameView.getText().toString();
                String newPassword=passwordView.getText().toString();

                if(newName.isEmpty() || newPassword.isEmpty()){
                    Toast.makeText(getActivity(),"Password/Email cannot be empty",Toast.LENGTH_SHORT).show();

                }
                else {
                    DataServices.Account account = DataServices.update(oldAccount, newName, newPassword);
                    mListener.updateToAccount(account);
                }
            }
        });


        view.findViewById(R.id.buttonCancelUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mListener.backToAccount();
            }
        });


        return view;
    }


    UpdateListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof UpdateListener) {
            mListener = (UpdateListener)context;
        }else{
            throw  new RuntimeException((context.toString()+"must implement updateListener"));
        }
    }
    interface UpdateListener {
        void updateToAccount(DataServices.Account account);
        void backToAccount();
    }
}