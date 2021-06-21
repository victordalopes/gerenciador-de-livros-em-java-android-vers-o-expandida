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
import com.example.gerenciadordelivros.adapter.AutorAdapter;
import com.example.gerenciadordelivros.daos.AutorDAO;
import com.example.gerenciadordelivros.dialogs.DeleteDialogAutor;
import com.example.gerenciadordelivros.dominio.Autor;

import java.util.List;

public class AutorActivity extends AppCompatActivity implements AutorAdapter.OnAutorListener, DeleteDialogAutor.OnDeleteListener {

    private AutorDAO autorDAO;
    AutorAdapter autorAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewautor);

        RecyclerView recyclerview = findViewById(R.id.recyclerviewautor);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        autorDAO = AutorDAO.getInstance(this);

        List<Autor> listaAutores = autorDAO.list();

        autorAdapter = new AutorAdapter(listaAutores,this, this);

        recyclerview.setAdapter(autorAdapter);
    }

    public void autores (View view) {
        Intent intenta = new Intent(getApplicationContext(), EditarAutorActivity.class);
        startActivity(intenta);
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
                atualizaListaAutores();
        }
        if(requestCode == 101 && resultCode == RESULT_OK){
            atualizaListaAutores();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void atualizaListaAutores(){
        List<Autor> autores = autorDAO.list();
        autorAdapter.setItems(autores);
        autorAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAutorClick(int posicao) {

        Intent intent = new Intent(getApplicationContext(),EditarAutorActivity.class);
        intent.putExtra("autor",autorAdapter.getItem(posicao));
        startActivityForResult(intent, 101);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onAutorLongClick(int posicao) {

        Autor autor = autorAdapter.getItem(posicao);

        DeleteDialogAutor dialog = new DeleteDialogAutor();
        dialog.setAutor(autor);
        dialog.show(getSupportFragmentManager(), "deleteDialog");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onDelete(Autor autor) {
        autorDAO.delete(autor);;
        atualizaListaAutores();

        Toast.makeText(this,"Autor excluído com sucesso!", Toast.LENGTH_LONG).show();
    }
}
