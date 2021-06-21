package com.example.gerenciadordelivros.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gerenciadordelivros.R;
import com.example.gerenciadordelivros.daos.EditoraDAO;
import com.example.gerenciadordelivros.dominio.Editora;

public class EditarEditoraActivity extends AppCompatActivity {

    private EditText edt_nome;
    private EditText edt_fundacao;
    private EditText edt_sede;
    private EditText edt_cnpj;

    private EditoraDAO editoraDAO;

    private Editora editora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_editora);

        edt_nome = findViewById(R.id.edt_nome);
        edt_fundacao = findViewById(R.id.edt_fundacao);
        edt_sede = findViewById(R.id.edt_sede);
        edt_cnpj = findViewById(R.id.edt_cnpj);

        editoraDAO = EditoraDAO.getInstance(this);

        editora = (Editora) getIntent().getSerializableExtra("editora");

        if(editora != null){
            edt_nome.setText(editora.getNome());
            edt_fundacao.setText(editora.getFundacao());
            edt_sede.setText(editora.getSede());
            edt_cnpj.setText(editora.getCnpj());
        }
    }

    public void cancelar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void processar(View view) {

        String nome = edt_nome.getText().toString();
        String fundacao = edt_fundacao.getText().toString();
        String sede = edt_sede.getText().toString();
        String cnpj = edt_cnpj.getText().toString();

        String msg;

        if (editora == null) {
            Editora editora = new Editora(nome, fundacao, sede, cnpj);
            editoraDAO.save(editora);
            msg = "Editora adicionado com sucesso! ID= "+editora.getId();
        }else{
            editora.setNome(nome);
            editora.setFundacao(fundacao);
            editora.setSede(sede);
            editora.setCnpj(cnpj);

            editoraDAO.update(editora);
            msg = "Editora atualizada com sucesso! ID= "+editora.getId();
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        Intent intente = new Intent(getApplicationContext(), EditoraActivity.class);
        startActivityForResult(intente, 100);
    }
}