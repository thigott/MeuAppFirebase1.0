package com.example.android.meuappfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.android.meuappfirebase.R.id.alterar;

public class AlterarSenha extends AppCompatActivity {

    String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);
    }

    public void alterarSenha(View view){
        senha = getSenha();
        alterar(senha);
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

    private void alterar(String senha){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updatePassword(senha).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Senha alterada com sucesso!", Toast.LENGTH_SHORT).show();
                    telaPrincipal();
                } else {
                    Toast.makeText(getApplicationContext(), "Senha não pode ser alterada, tente novamente", Toast.LENGTH_SHORT).show();
                    telaPrincipal();
                }
            }
        });
    }
}
