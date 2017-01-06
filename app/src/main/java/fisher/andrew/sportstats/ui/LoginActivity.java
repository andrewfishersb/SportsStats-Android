package fisher.andrew.sportstats.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import fisher.andrew.sportstats.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.loginEmail) EditText mLoginEmail;
    @Bind(R.id.loginPassword) EditText mLoginPassword;
    @Bind(R.id.loginButton) Button mLoginButton;
    @Bind(R.id.registerTextView) TextView mSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
//        mLoginButton.setOnClickListener(this);
        mSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view ==mSignUp){
            Intent intent = new Intent(LoginActivity.this,CreateAccountActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
