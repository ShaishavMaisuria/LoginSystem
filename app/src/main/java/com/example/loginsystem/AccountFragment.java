package com.example.loginsystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AccountFragment extends Fragment {


    private static final String ARG_PARAM_ACCOUNT = "ARG_PARAM_ACCOUNT";


    private DataServices.Account mAccount;

    public AccountFragment() {
        // Required empty public constructor
    }

// based on what I am looking it should be factor method
    public static AccountFragment newInstance(DataServices.Account account) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_ACCOUNT, account);
        Log.d("test","Account new Instance");
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            //
            mAccount=(DataServices.Account) getArguments().getSerializable(ARG_PARAM_ACCOUNT);
            Log.d("test","Account On Create, mAccount"+mAccount.getEmail());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Account");
        // Inflate the layout for this fragment
        Log.d("test","Account On CreateView, before view");
        View  view= inflater.inflate(R.layout.fragment_account, container, false);
        Log.d("test","Account On CreateView, after view");

        TextView name=view.findViewById(R.id.textViewNameAccount);
        name.setText(mAccount.getName());

        return view;
    }
}