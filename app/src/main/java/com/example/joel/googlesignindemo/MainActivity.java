package com.example.joel.googlesignindemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    private LinearLayout Prof_sec;
    private SignInButton SignIn;
    private Button SignOut;
    private TextView Name,Email;
    private GoogleApiClient googleApiClient;
 //   private ImageView prof_pic;
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
      //  prof_pic = findViewById(R.id.prof_pic);

        SignIn.setOnClickListener(this);
        SignOut.setOnClickListener(this);
        Prof_sec.setVisibility(View.GONE);


    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
