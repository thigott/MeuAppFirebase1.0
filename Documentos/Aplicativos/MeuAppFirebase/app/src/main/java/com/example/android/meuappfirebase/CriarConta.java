package com.example.android.meuappfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CriarConta extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    String email;
    String senha;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
    }

    private String getEmail(){
        EditText text = (EditText) findViewById(R.id.edit_email);
        return text.getText().toString();
    }

    private String getSenha(){
        EditText text = (EditText) findViewById(R.id.edit_senha);
        return text.getText().toString();
    }

    private void telaPrincipal(){
        Intent tela = new Intent(this, MainActivity.class);
        startActivity(tela);
    }

    public void cadastrarUsuario(View view){
        email = getEmail();
        senha = getSenha();

        if(TextUtils.isEmpty(email)){
            return;
        }
        if(TextUtils.isEmpty(senha)){
            return;
        }

        progressDialog.setMessage("Registrando usuário...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    telaPrincipal();
                } else {
                    Toast.makeText(getApplicationContext(), "Usuário não pode ser cadastrado, tente novamente, por favor!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
