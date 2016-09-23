package com.example.android.meuappfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.Map;

import static com.example.android.meuappfirebase.R.id.enviar;

public class MainActivity extends AppCompatActivity {

    String name;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void enviar(View view){
        Intent tela = new Intent(this, EnviarProvas.class);
        startActivity(tela);
    }

    public void receber(View view){
        Intent tela = new Intent(this, Cursos.class);
        startActivity(tela);
    }

    public void alterar(View view){
        Intent tela = new Intent(this, AlterarSenha.class);
        startActivity(tela);
    }

    public void sair(View view){
        firebaseAuth.signOut();
        Intent tela = new Intent(this, Login.class);
        startActivity(tela);
    }

}
