package apk.win.first.secondgoogaut;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable, CallbackClass.Callback{

    fragment_goog_ fragmentGoog = new fragment_goog_();
    NetworkManager networkManager = new NetworkManager();
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loadFragment();
        loadFragmentIds();
    }

    private void loadFragment() {
        /*android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        */
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragmentGoog).addToBackStack("home").commit();
    }

    private void loadFragmentIds() {
        /*android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();*/
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, firstFragment)/*.addToBackStack("home")*/.commit();
    }

    @Override
    public List<Model> callingBack(int number) throws InterruptedException {
        networkManager.loadNumberFromMain(number);
        System.out.println("network number:  " + networkManager.numberObj);
        return networkManager.modelList;
    }

    @Override
    public void callingBackButton() {
      /*  if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }*/
      // if( getFragmentManager().getBackStackEntryCount() > 0)
        /***/
        //    getSupportFragmentManager().popBackStack();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void callingBackButtonFragment() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void callingBackSecondFr(Model i) throws InterruptedException {
        Bundle arguments = new Bundle();
        arguments.putSerializable("listModel", i);
        secondFragment.setArguments(arguments);
        /*android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();*/
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, secondFragment).addToBackStack("home").commit();
    }

    @Override
    public void callingBackModelSherObjectToFragOne(ModelSharPref i) {
        Bundle arguments = new Bundle();
        arguments.putSerializable("listModelSherPr", i);
        firstFragment.setArguments(arguments);
    }

    @Override
    public void callingFragment_goog() {
        loadFragment();
    }

    @Override
    public void callingFirstFragm() {
        loadFragmentIds();
    }
}
