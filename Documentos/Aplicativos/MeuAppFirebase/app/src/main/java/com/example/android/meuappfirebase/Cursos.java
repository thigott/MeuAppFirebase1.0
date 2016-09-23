package com.example.android.meuappfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Cursos extends AppCompatActivity {

    public static String cursoParametro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);
    }

    public void telecom(View view){
        cursoParametro = "Engenharia de Telecomunicações";
        telaReceberCurso();
    }

    public void civil(View view){
        cursoParametro = "Engenharia Civil";
        telaReceberCurso();
    }

    public void quimica(View view){
        cursoParametro = "Engenharia Química";
        telaReceberCurso();
    }

    public void mecatronica(View view){
        cursoParametro = "Engenharia Mecatrônica";
        telaReceberCurso();
    }

    public void bioprocessos(View view){
        cursoParametro = "Engenharia de Bioprocessos";
        telaReceberCurso();
    }

    public void voltarMain(View view){
        Intent tela = new Intent(this, MainActivity.class);
        startActivity(tela);
    }

    public void telaCadastraCurso(View view){
        Intent tela = new Intent(this, CadastraCurso.class);
        startActivity(tela);
    }

    private void telaReceberCurso(){
        Intent tela = new Intent(this, ReceberProvas.class);
        startActivity(tela);
    }
}
