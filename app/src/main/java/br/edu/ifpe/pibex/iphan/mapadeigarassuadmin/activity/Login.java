package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.dialog.AlertDialogMessage;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.model.User;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.model.ConfigurationFirebase;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.R;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.util.SharedPreferencesUtil;

public class Login extends Activity {

    private EditText email;
    private EditText password;
    private Button signIn;
    private FirebaseAuth authentication;
    private User user;
    private Context context;

    public Login(){
        this.context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * Verificação se o usuário está logado
         */
        if(SharedPreferencesUtil.isLogged(context)){
            openHome();
        }

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
                    //Alert
                    AlertDialogMessage.alertDialogMessage(context, "Atenção!", "Preencha os campos e-mail e senha!");
                }
            }

        });

    }

    //Método de validação do usuário
    private void validationLogin() {

        final ProgressDialog progressDialog = new ProgressDialog(Login.this);
        progressDialog.setTitle("Aguarde");
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
                            SharedPreferencesUtil.isLogged(context, true);
                            openHome();
                        } else {
                            progressDialog.cancel();
                            //Alert
                            AlertDialogMessage.alertDialogMessage(context, "Erro!", "Usuário ou senha incorreto!");
                        }
                    }
                });
    }

    private void openHome() {
        Intent intent = new Intent(Login.this, HomeActivity.class);
        startActivity(intent);
    }

}

