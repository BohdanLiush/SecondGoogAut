package apk.win.first.secondgoogaut;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import android.content.SharedPreferences;

public class fragment_goog_ extends Fragment {

    SignInButton signInButton;
    Button signOutButton;
    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "SignInActivity";
    public static final String TAG_TWO = "ServerAuthCodeActivity";
    public TextView mStatusTextView;
    GoogleSignInOptions gso;

    private static final int RC_SIGN_IN = 0;
    private static final int RC_GET_AUTH_CODE = 9003;

    ModelSharPref modelSharPref = new ModelSharPref();
    ModelSharPref modelThree;

    SharedPreferences sPref;
    SharedPreferences.Editor ed;
    String LOGIN_TEXT = "login_text";
    View rootview;

    Button buttonChangFr;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_goog,container,false);

        mStatusTextView = rootview.findViewById(R.id.statuslabel);

        signInButton = rootview.findViewById(R.id.sign_in_button);
        signOutButton = rootview.findViewById(R.id.sign_out_button);
        buttonChangFr = rootview.findViewById(R.id.changeFrag);
        progressBar = rootview.findViewById(R.id.progress_Bar);

        /** serverClientId */
        String serverClientId = getString(R.string.server_client_id);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                // .requestScopes(new Scope(Scopes.DRIVE_APPFOLDER))
                // .requestIdToken(serverClientId)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this.getContext(),gso);

        signInButton.setSize(SignInButton.SIZE_STANDARD);
        validateServerClientID();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        buttonChangFr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activityHome = (MainActivity) view.getContext();
                CallbackClass callbacks = new CallbackClass();
                callbacks.registerCallBack(activityHome);
                callbacks.loadFirstFragm();
            }
        });
        return rootview.getRootView();
    }

    private void handleSignInResult(@NonNull Task<GoogleSignInAccount> completedTask) {
        try {

            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String idToken = account.getIdToken();
            System.out.println("idToken is: " + idToken);
            String name = account.getDisplayName();
            System.out.println("name " + name);
            String email = account.getEmail();
            System.out.println("email " + email);

            modelSharPref.setEmailSher(name);
            modelSharPref.setNameSher(email);

            saveText();
            loadText();
            // TODO(developer): send ID Token to server and validate
            updateUI(account);

           /* if (modelSharPref!=null) {
                MainActivity activityHome = (MainActivity) rootview.getContext();
                CallbackClass callbacks = new CallbackClass();
                callbacks.registerCallBack(activityHome);
                callbacks.buttonBack();
            }*/

        } catch (ApiException e) {
            Log.w(TAG, "handleSignInResult:error", e);
            updateUI(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GET_AUTH_CODE){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                /*GoogleSignInAccount account = task.getResult(ApiException.class);
                String idToken = account.getIdToken();
                updateUI(account);*/
            handleSignInResult(task);

            progressBar.setVisibility(View.INVISIBLE);
            /** так після авторизації швидко вертались на попередній фрагмент*/
            MainActivity activityHome = (MainActivity) rootview.getContext();
            CallbackClass callbacks = new CallbackClass();
            callbacks.registerCallBack(activityHome);
            callbacks.buttonBack();
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        //startActivityForResult(signInIntent, RC_SIGN_IN);
        startActivityForResult(signInIntent, RC_GET_AUTH_CODE);
    }

    public void signOut(){
        mGoogleSignInClient.signOut().addOnCompleteListener(this.getActivity(), new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                updateUI(null);
                clearSpref();
            }
        });
    }


    public void updateUI(GoogleSignInAccount account){
        if(account!=null){
            mStatusTextView.setText(getString(R.string.signed_in_fmt, account.getDisplayName()));
            signInButton.setVisibility(View.INVISIBLE);
            signOutButton.setVisibility(View.VISIBLE);
        } else {
            mStatusTextView.setText(R.string.signed_out);
            signInButton.setVisibility(View.VISIBLE);
        }
    }

    private void validateServerClientID() {
        String serverClientId = getString(R.string.server_client_id);
        String suffix = ".apps.googleusercontent.com";
        if (!serverClientId.trim().endsWith(suffix)) {
            String message = "Invalid server client ID in strings.xml, must end with " + suffix;

            Log.w(TAG, message);
            Toast.makeText(this.getContext(), message, Toast.LENGTH_LONG).show();
        }
        else {
            Log.w(TAG, "Good Work");
        }
    }

    void saveText() {

        sPref = getActivity().getApplicationContext().getSharedPreferences("MyPrefsFilesSix", Context.MODE_PRIVATE);
        ed = sPref.edit();

        /** model saving */
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(modelSharPref);

        ed.putString(LOGIN_TEXT, serializedObject);
        ed.apply();
        //Toast.makeText(rootview.getContext(), "Login and password and address saved", Toast.LENGTH_SHORT).show();
    }

    void loadText() throws InterruptedException {

        sPref = getActivity().getApplicationContext().getSharedPreferences("MyPrefsFilesSix", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sPref.getString(LOGIN_TEXT, "");

        modelThree = gson.fromJson(json, ModelSharPref.class);

        if (modelThree == null) {
            Toast.makeText(rootview.getContext(),"all clear",Toast.LENGTH_SHORT).show();
        } else {
            MainActivity activityHome = (MainActivity) rootview.getContext();
            CallbackClass callbacks = new CallbackClass();
            callbacks.registerCallBack(activityHome);
            callbacks.sendModelSherObj (modelThree);
        }
    }

    void clearSpref() {
        if (sPref == null) {
            Toast.makeText(rootview.getContext(), "all clear", Toast.LENGTH_SHORT).show();
        } else {
            sPref.edit().clear().apply();
        }
    }
}
