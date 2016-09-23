package com.example.android.meuappfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.List;

import static android.widget.ArrayAdapter.createFromResource;

public class EnviarProvas extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String mensagem;
    String materia;
    String data;
    String conteudo;
    String curso;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_provas);

        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Cursos, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Firebase.setAndroidContext(this);
    }

    public void voltar(){
        Intent tela = new Intent(this, MainActivity.class);
        startActivity(tela);
    }

    public void enviarProva(View view){
        Firebase ref = new Firebase("https://facetest-68b86.firebaseio.com/");
        Firebase refProvas = ref.child(curso);

        materia = pegarDadoMateria();
        data = pegarDadoData();
        conteudo = pegarDadoConteudo();

        if(mensagem == null) {
            mensagem = "Materia: " + materia + "\nData: " + data + "\nConteudo: " + conteudo + "\n\n";
        } else {
            mensagem = "Materia: " + materia + "\nData: " + data + "\nConteudo: " + conteudo + "\n\n" + mensagem;
        }

        refProvas.setValue(mensagem);

        Toast.makeText(getApplicationContext(),"Prova cadastrada com sucesso!",Toast.LENGTH_SHORT).show();
        voltar();
    }


    private String pegarDadoMateria(){
        EditText materia = (EditText) findViewById(R.id.edit_materia);
        return materia.getText().toString();
    }

    private String pegarDadoData(){
        EditText data = (EditText) findViewById(R.id.edit_data);
        return data.getText().toString();
    }

    private String pegarDadoConteudo(){
        EditText conteudo = (EditText) findViewById(R.id.edit_conteudo);
        return conteudo.getText().toString();
    }

    private void lerDados(){
        Firebase ref = new Firebase("https://facetest-68b86.firebaseio.com/");
        Firebase refProvas = ref.child(curso);

        refProvas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mensagem = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView text = (TextView) view;
        curso = text.getText().toString();
        lerDados();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        return;
    }
}
