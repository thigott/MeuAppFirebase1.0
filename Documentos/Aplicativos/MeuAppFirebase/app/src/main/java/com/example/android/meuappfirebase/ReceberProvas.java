package com.example.android.meuappfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.collection.ArraySortedMap;
import com.firebase.ui.FirebaseListAdapter;

public class ReceberProvas extends AppCompatActivity {

    private ProgressDialog progressDialog;
    String curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receber_provas);

        Firebase.setAndroidContext(this);

        curso = Cursos.cursoParametro;

        progressDialog = new ProgressDialog(this);

        lerDados();

    }

    private void lerDados(){
        Firebase ref = new Firebase("https://facetest-68b86.firebaseio.com/");
        Firebase refTest = ref.child(curso);

        progressDialog.setMessage("Buscando Dados...");
        progressDialog.show();

        refTest.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String mensagem = dataSnapshot.getValue(String.class);
                TextView text = (TextView) findViewById(R.id.provas);
                text.setText(mensagem);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void voltar(View view){
        Intent tela = new Intent(this, MainActivity.class);
        startActivity(tela);
    }
}
