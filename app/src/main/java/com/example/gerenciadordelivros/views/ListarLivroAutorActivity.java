package com.example.gerenciadordelivros.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.example.gerenciadordelivros.daos.LivroAutorDAO;
import com.example.gerenciadordelivros.dominio.LivroAutor;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import com.example.gerenciadordelivros.R;
import java.util.ArrayList;
import java.util.List;

public class ListarLivroAutorActivity extends AppCompatActivity {

    private ListView listView;
    private LivroAutorDAO dao;
    private List<LivroAutor> livaut;
    private List<LivroAutor> livautfiltrados = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_listar_livro_autor);

        listView = findViewById(R.id.ListView);
        dao = new LivroAutorDAO(this);
        livaut = dao.getAll();
        livautfiltrados.addAll(livaut);
        ArrayAdapter<LivroAutor> adaptador = new ArrayAdapter<LivroAutor>(this, android.R.layout.simple_list_item_1, livautfiltrados);

        listView.setAdapter(adaptador);

        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.cadastro, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener( ) {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                buscaLivroAutor(s);
                return false;
            }
        });
        return true;
    }
    public void buscaLivroAutor(String titulo){
        livautfiltrados.clear();
        for (LivroAutor pm : livaut){
            if (pm.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                livautfiltrados.add(pm);
            }
        }
        listView.invalidateViews();
    }

    public void add (MenuItem item){
        Intent it = new Intent(this, LivroActivity.class);
        startActivity(it);
    }
    public void delete(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final LivroAutor livautExcluir = livautfiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Tem certeza de que deseja exluir a relação?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        livautfiltrados.remove(livautExcluir);
                        livaut.remove(livautExcluir);
                        dao.excluir(livautExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();

    }

    @Override
    public void onResume(){
        super.onResume();
        livaut = dao.getAll();
        livautfiltrados.clear();
        livautfiltrados.addAll(livaut);
        listView.invalidateViews();

    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.excluatu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}