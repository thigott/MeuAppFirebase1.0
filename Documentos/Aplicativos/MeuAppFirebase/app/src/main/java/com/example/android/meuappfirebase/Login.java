package com.example.android.meuappfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    String email;
    String senha;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        userEstado();

    }

    public void telaCriarConta(View view){
        Intent tela = new Intent(this, CriarConta.class);
        startActivity(tela);
    }

    private void telaPrincipal() {
        Intent tela = new Intent(this, MainActivity.class);
        startActivity(tela);
    }

    private void userEstado() {
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    telaPrincipal();
                }
            }
        };
    }

    private String getEmail(){
        EditText text = (EditText) findViewById(R.id.edit_email);
        return text.getText().toString();
    }

    private String getSenha(){
        EditText text = (EditText) findViewById(R.id.edit_senha);
        return text.getText().toString();
    }

    public void redefinirSenha(View view){
        Intent tela = new Intent(this, RedefinirSenha.class);
        startActivity(tela);
    }

    public void logar(View view){
        email = getEmail();
        senha = getSenha();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Email não pode ficar em branco", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(senha)){
            Toast.makeText(getApplicationContext(),"Senha não pode ficar em branco", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Entrando...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Logado com sucesso!", Toast.LENGTH_SHORT).show();
                    telaPrincipal();
                } else {
                    Toast.makeText(getApplicationContext(), "Email ou senha incorretos, por favor, tente novamente!",Toast.LENGTH_SHORT).show();
                    TextView text = (TextView) findViewById(R.id.redefinir);
                    text.setText("Esqueceu sua senha? Clique aqui");
                    progressDialog.dismiss();
                }
            }
        });
    }

    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }

    }
}
