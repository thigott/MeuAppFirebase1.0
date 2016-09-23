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

import static com.example.android.meuappfirebase.R.drawable.senha;

 public class RedefinirSenha extends AppCompatActivity {

     String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha);
    }

     public void refinir(View view){
         email = getEmail();
         enviarEmail(email);
     }

    private String getEmail(){
        EditText text = (EditText) findViewById(R.id.edit_email);
        return text.getText().toString();
    }

     private void telaPrincipal(){
         Intent tela = new Intent(this, MainActivity.class);
         startActivity(tela);
     }

     private void enviarEmail(String email){
         FirebaseAuth auth = FirebaseAuth.getInstance();
         auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
             @Override
             public void onComplete(@NonNull Task<Void> task) {
                 if(task.isSuccessful()){
                     Toast.makeText(getApplicationContext(),"Email enviado!", Toast.LENGTH_SHORT).show();
                     telaPrincipal();
                 } else {
                     Toast.makeText(getApplicationContext(),"Email não enviado, tente novamente!", Toast.LENGTH_SHORT).show();
                 }
             }
         });
     }
}
