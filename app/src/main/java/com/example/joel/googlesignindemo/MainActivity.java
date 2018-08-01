package com.example.joel.googlesignindemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    private LinearLayout Prof_sec;
    private SignInButton SignIn;
    private Button SignOut;
    private TextView Name,Email;
    private GoogleApiClient googleApiClient;
    private ImageView prof_pic;
    private static final int REQ_CODE = 9001;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Prof_sec = findViewById(R.id.prof_sec);
        SignIn = findViewById(R.id.signNInBtn);
        SignOut = findViewById(R.id.logOutBtn);
        Name = findViewById(R.id.name);
        Email = findViewById(R.id.email);
        prof_pic = findViewById(R.id.prof_pic);

        SignIn.setOnClickListener(this);
        SignOut.setOnClickListener(this);
        Prof_sec.setVisibility(View.GONE);

        GoogleSignInOptions signInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient
                .Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions)
                .build();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signNInBtn:
                signIn();
                break;
            case R.id.logOutBtn:
                signOut();
                break;
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);

    }
    private void signOut(){

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });

    }
    private void handleResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            String name =  account.getDisplayName();
            String email = account.getEmail();
//           String img_url = account.getPhotoUrl().toString();//getting a nullpoint exception
            Name.setText(name);
            Email.setText(email);
    //        Glide.with(this).load(img_url).into(prof_pic);
            updateUI(true);
        }else{
            updateUI(false);
        }

    }
    private void updateUI(boolean isLogin){

        if (isLogin){
            Prof_sec.setVisibility(View.VISIBLE);
            SignIn.setVisibility(View.GONE);

        }else {
            Prof_sec.setVisibility(View.GONE);
            SignIn.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }
}
