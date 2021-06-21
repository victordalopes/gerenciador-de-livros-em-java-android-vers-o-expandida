package com.example.gerenciadordelivros.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gerenciadordelivros.R;
import com.example.gerenciadordelivros.daos.AutorDAO;
import com.example.gerenciadordelivros.dominio.Autor;

public class EditarAutorActivity extends AppCompatActivity {

    private EditText edt_nome;
    private EditText edt_idade;
    private EditText edt_editora;
    private EditText edt_genero;

    private AutorDAO autorDAO;

    private Autor autor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_autor);

        edt_nome = findViewById(R.id.edt_nome);
        edt_idade = findViewById(R.id.edt_idade);
        edt_editora = findViewById(R.id.edt_editora);
        edt_genero = findViewById(R.id.edt_genero);

        autorDAO = AutorDAO.getInstance(this);

        autor = (Autor) getIntent().getSerializableExtra("autor");

        if(autor != null){
            edt_nome.setText(autor.getNome());
            edt_idade.setText(autor.getIdade());
            edt_editora.setText(autor.getEditora());
            edt_genero.setText(autor.getGenero());
        }
    }

    public void cancelar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void processar(View view) {

        String nome = edt_nome.getText().toString();
        String idade = edt_idade.getText().toString();
        String editora = edt_editora.getText().toString();
        String genero = edt_genero.getText().toString();

        String msg;

        if (autor == null) {
            Autor autor = new Autor(nome, idade, editora, genero);
            autorDAO.save(autor);
            msg = "Autor adicionado com sucesso! ID= "+autor.getId();
        }else{
            autor.setNome(nome);
            autor.setIdade(idade);
            autor.setEditora(editora);
            autor.setGenero(genero);

            autorDAO.update(autor);
            msg = "Autor atualizado com sucesso! ID= "+autor.getId();
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        Intent intenta = new Intent(getApplicationContext(), AutorActivity.class);
        startActivityForResult(intenta, 100);
    }
}