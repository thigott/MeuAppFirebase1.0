package com.example.android.meuappfirebase;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class CadastraCurso extends AppCompatActivity {

    String universidade;
    String curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_curso);
    }

    public void cadastrarCurso(View view) throws IOException {
        universidade = getUniversidade();
        curso = getCurso();
        enviar();
        telaPrincipal();
        Toast.makeText(getApplicationContext(),"Solicitação de cadastro enviada!", Toast.LENGTH_SHORT).show();
    }

    private void enviar() throws IOException{
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + "sttsuporte@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Cadastro de curso");
        intent.putExtra(Intent.EXTRA_TEXT, "Solicitação de cadastro de curso\nUniversidade: " + universidade + "\nCurso: " + curso + "\n\nSave the Test!");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private String getUniversidade(){
        EditText text = (EditText) findViewById(R.id.edit_universidade);
        return text.getText().toString();
    }

    private String getCurso(){
        EditText text = (EditText) findViewById(R.id.edit_curso);
        return text.getText().toString();
    }

    private void telaPrincipal(){
        Intent tela = new Intent(this, MainActivity.class);
        startActivity(tela);
    }
}
