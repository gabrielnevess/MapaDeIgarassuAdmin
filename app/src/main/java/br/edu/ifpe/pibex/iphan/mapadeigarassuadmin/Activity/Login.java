package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.Class.User;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.Firebase.ConfigurationFirebase;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.R;

public class Login extends Activity {

    private EditText email;
    private EditText password;
    private Button signIn;
    private FirebaseAuth authentication;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        signIn = (Button) findViewById(R.id.sign_in);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().length() != 0 && password.getText().length() != 0) {
                    user = new User(email.getText().toString(), password.getText().toString());
                    validationLogin();
                } else {
                    Toast.makeText(Login.this, "Preencha os campos e-mail e senha!", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    private void validationLogin() {

        final ProgressDialog progressDialog = new ProgressDialog(Login.this);
        progressDialog.setMessage("Autenticando...");
        progressDialog.show();

        authentication = ConfigurationFirebase.getFirebaseAuth();
        authentication
                .signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.cancel();
                            openHome();
                            Toast.makeText(Login.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.cancel();
                            Toast.makeText(Login.this, "Usuário ou senha incorreto!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void openHome() {
        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
    }

}

