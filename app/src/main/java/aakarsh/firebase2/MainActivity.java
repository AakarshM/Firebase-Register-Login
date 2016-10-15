package aakarsh.firebase2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    Button button, button2;
    public FirebaseAuth auth;
    public FirebaseAuth.AuthStateListener authlistener;
    String email, pass;
    ProgressBar bar;

    public View.OnClickListener buttonListener = new View.OnClickListener() {
        public void onClick (View view){
            email = editText.getText().toString().trim();
            pass = editText2.getText().toString().trim();
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            createUser();
        }};

    public View.OnClickListener backButtonListener = new View.OnClickListener() {
        public void onClick (View view){
            Intent backtoMain = new Intent(getApplicationContext(), FrontPageActivity.class);
            startActivity(backtoMain);

        }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        button = (Button)findViewById(R.id.button2);
        auth = FirebaseAuth.getInstance();
        button.setOnClickListener(buttonListener);
        bar = (ProgressBar)findViewById(R.id.progressBar2);
        bar.setVisibility(View.INVISIBLE);
        button2 = (Button)findViewById(R.id.button5);
        button2.setOnClickListener(backButtonListener);
    }

    public void createUser(){
        bar.setVisibility(View.VISIBLE);

        auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                            Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(loginActivity);
                            finish();
                            bar.setVisibility(View.INVISIBLE);
                        } else{
                            Toast.makeText(getApplicationContext(), "Failed, try again.", Toast.LENGTH_LONG).show();
                            bar.setVisibility(View.INVISIBLE);
                        }

                    }
                });


    }
}
