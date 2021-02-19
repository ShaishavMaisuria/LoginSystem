package com.example.loginsystem;
/*
* @author Shaishav Maisuria
 */
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginListener, RegisterFragment.SignupListener, AccountFragment.AccountListener
,UpdateFragment.UpdateListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView,new LoginFragment())
                .commit();
    }

    DataServices.Account mAccount;

    @Override
    public void loginIsSuccessful(DataServices.Account account) {
        this.mAccount= account;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, AccountFragment.newInstance(this.mAccount),"accountFragment")
                .commit();
    }

    @Override
    public void gotoLogin() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new LoginFragment())
                .commit();
    }

    @Override
    public void gotoRegistration() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new RegisterFragment())
                .commit();
    }

    @Override
    public void gotoUpdate() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,UpdateFragment.newInstance(this.mAccount))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void logout() {
        this.mAccount = null;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new LoginFragment())
                .commit();
    }

    @Override
    public void updateToAccount(DataServices.Account account) {
        this.mAccount=account;

        AccountFragment accountFragment= (AccountFragment) getSupportFragmentManager().findFragmentByTag("accountFragment");
       if(accountFragment!=null) {
           accountFragment.setAccountDetails(mAccount);
       }

       getSupportFragmentManager().popBackStack();


    }

    @Override
    public void backToAccount() {

        getSupportFragmentManager().popBackStack();
    }
}