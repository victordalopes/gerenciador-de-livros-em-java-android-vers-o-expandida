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
import com.example.gerenciadordelivros.adapter.LivroAdapter;
import com.example.gerenciadordelivros.daos.LivroDAO;
import com.example.gerenciadordelivros.dialogs.DeleteDialogLivro;
import com.example.gerenciadordelivros.dominio.Livro;

import java.util.List;

public class LivroActivity extends AppCompatActivity implements LivroAdapter.OnLivroListener, DeleteDialogLivro.OnDeleteListener {

    private LivroDAO livroDAO;
    LivroAdapter livroAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewlivro);

        RecyclerView recyclerview = findViewById(R.id.recyclerviewlivro);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));

//        Dados antigos usados para preencher o banco antes da criação da classe privada livroDAO

//        listaLivros.add(new Livro(1L,"Android para Leigos","Michael Burton","Alta books",0));
//        listaLivros.add(new Livro(2L,"Android para Programadores","Paul J, Deitel","Bookman",1));
//        listaLivros.add(new Livro(3L,"Desenvolvimento para Android","Griffiths, David","Alta books",0));
//        listaLivros.add(new Livro(4L,"Android Base de Dados","Queirós, Ricardo","FCA Editora",1));
//        listaLivros.add(new Livro(5L,"Android em Ação","King, Chris","Elsevier - Campus",0));
//        listaLivros.add(new Livro(6L,"Jogos em Android","Queirós, Ricardo","FCA - Editora",1));
//        listaLivros.add(new Livro(7L,"Android Essencial com Kotlin","Ricardo R.","NOVATEC",0));

        livroDAO = LivroDAO.getInstance(this);

        List<Livro> listaLivros = livroDAO.list();

        livroAdapter = new LivroAdapter(listaLivros,this, this);

        recyclerview.setAdapter(livroAdapter);
    }

    public void livros (View view) {
        Intent intentl = new Intent(getApplicationContext(), EditarLivroActivity.class);
        startActivity(intentl);
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
                atualizaListaLivros();
        }
        if(requestCode == 101 && resultCode == RESULT_OK){
            atualizaListaLivros();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void atualizaListaLivros(){
        List<Livro> livros = livroDAO.list();
        livroAdapter.setItems(livros);
        livroAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLivroClick(int posicao) {

        Intent intent = new Intent(getApplicationContext(),EditarLivroActivity.class);
        intent.putExtra("livro",livroAdapter.getItem(posicao));
        startActivityForResult(intent, 101);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onLivroLongClick(int posicao) {

        Livro livro = livroAdapter.getItem(posicao);

        DeleteDialogLivro dialog = new DeleteDialogLivro();
        dialog.setLivro(livro);
        dialog.show(getSupportFragmentManager(), "deleteDialog");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onDelete(Livro livro) {
        livroDAO.delete(livro);;
        atualizaListaLivros();

        Toast.makeText(this,"Livro excluído com sucesso!", Toast.LENGTH_LONG).show();
    }
}