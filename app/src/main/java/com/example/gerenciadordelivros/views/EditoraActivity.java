package com.example.gerenciadordelivros.views;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gerenciadordelivros.R;
import com.example.gerenciadordelivros.adapter.EditoraAdapter;
import com.example.gerenciadordelivros.daos.EditoraDAO;
import com.example.gerenciadordelivros.dialogs.DeleteDialogEditora;
import com.example.gerenciadordelivros.dominio.Editora;

import java.util.List;

public class EditoraActivity extends AppCompatActivity implements EditoraAdapter.OnEditoraListener, DeleteDialogEditora.OnDeleteListener {

    private EditoraDAO editoraDAO;
    EditoraAdapter editoraAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.recyclervieweditora);

        RecyclerView recyclerview = findViewById(R.id.recyclervieweditora);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        editoraDAO = EditoraDAO.getInstance(this);

        List<Editora> listaEditoras = editoraDAO.list();

        editoraAdapter = new EditoraAdapter(listaEditoras,this, this);

        recyclerview.setAdapter(editoraAdapter);
    }

    public void editoras (View view) {
        Intent intente = new Intent(getApplicationContext(), EditarEditoraActivity.class);
        startActivity(intente);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actions,menu);

        return super.onCreateOptionsMenu(menu);
    }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK){
                atualizaListaEditoras();
        }
        if(requestCode == 101 && resultCode == RESULT_OK){
            atualizaListaEditoras();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void atualizaListaEditoras(){
        List<Editora> editoras = editoraDAO.list();
        editoraAdapter.setItems(editoras);
        editoraAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditoraClick(int posicao) {

        Intent intent = new Intent(getApplicationContext(),EditarEditoraActivity.class);
        intent.putExtra("editora",editoraAdapter.getItem(posicao));
        startActivityForResult(intent, 101);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onEditoraLongClick(int posicao) {

        Editora editora = editoraAdapter.getItem(posicao);

        DeleteDialogEditora dialog = new DeleteDialogEditora();
        dialog.setEditora(editora);
        dialog.show(getSupportFragmentManager(), "deleteDialog");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onDelete(Editora editora) {
        editoraDAO.delete(editora);;
        atualizaListaEditoras();

        Toast.makeText(this,"Editora excluída com sucesso!", Toast.LENGTH_LONG).show();
    }
}
