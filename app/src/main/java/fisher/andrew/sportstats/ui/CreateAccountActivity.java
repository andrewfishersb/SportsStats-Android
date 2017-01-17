package fisher.andrew.sportstats.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import fisher.andrew.sportstats.R;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.createEmail) EditText mCreateEmail;
    @Bind(R.id.createPassword) EditText mCreatePass;
    @Bind(R.id.confirmPassword) EditText mConfirmPass;
    @Bind(R.id.createAccountButton) Button mCreateAccountButton;
    @Bind(R.id.loginTextView) TextView mLoginTextView;

    //user authentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private ProgressDialog mAuthProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
        createAuthProgressDialog();


        mCreateAccountButton.setOnClickListener(this);
        mLoginTextView.setOnClickListener(this);

    }

    public void onClick(View v){
        if(v == mLoginTextView){
            Intent intent = new Intent(CreateAccountActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if(v ==mCreateAccountButton){
            createUser();
        }
    }

    private void createAuthStateListener(){
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    public void createUser(){
        final String email = mCreateEmail.getText().toString().trim();
        String password = mCreatePass.getText().toString().trim();
        String confirmPassword = mConfirmPass.getText().toString().trim();

        boolean validEmail = isValidEmail(email);
        boolean matchingPasswords = isValidPassword(password,confirmPassword);

        if(!validEmail || !matchingPasswords) return;

        //all below happens when passwords match and valid email
        mAuthProgressDialog.show();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mAuthProgressDialog.dismiss();

                if(task.isSuccessful()){
                }else{
                    Toast.makeText(CreateAccountActivity.this, "Cannot Create Account/An Account With This Email May Already Exist.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private boolean isValidEmail(String email){
        boolean isGoodEmail = (email !=null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if(!isGoodEmail){
            mCreateEmail.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidPassword(String password, String confirmPassword){
        if(password.length()<6){
            mCreatePass.setError("Please create a password containing at least 6 characters");
            return false;
        }else if(!password.equals(confirmPassword)){
            mCreatePass.setError("Passwords do not match");
            return false;
        }
        return true;
    }



    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Creating Account...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }




    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

}
