package com.example.gerenciadordelivros.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.gerenciadordelivros.R;

public class ActivityMenu extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void livros (View view) {
        Intent intentl = new Intent(getApplicationContext(), LivroActivity.class);
        startActivity(intentl);
    }

    public void autores (View view){
        Intent intenta = new Intent(getApplicationContext(), AutorActivity.class);
        startActivity(intenta);
    }

    public void editoras (View view){
        Intent intente = new Intent(getApplicationContext(), EditoraActivity.class);
        startActivity(intente);
    }

    public void usuarios (View view){
        Intent intentu = new Intent(getApplicationContext(), UsuarioActivity.class);
        startActivity(intentu);
    }

    public void livauts (View view){
        Intent intentla = new Intent(getApplicationContext(), LivroAutorActivity.class);
        startActivity(intentla);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_adicionar_livro:
                Intent intentl = new Intent(getApplicationContext(), EditarLivroActivity.class);
                startActivity(intentl);
            case R.id.action_adicionar_autor:
                Intent intenta = new Intent(getApplicationContext(), EditarAutorActivity.class);
                startActivityForResult(intenta, 100);
                return true;
            case R.id.action_adicionar_editora:
                Intent intente = new Intent(getApplicationContext(), EditarEditoraActivity.class);
                startActivityForResult(intente, 100);
                return true;
            case R.id.action_adicionar_usuario:
                Intent intentu = new Intent(getApplicationContext(), EditarUsuarioActivity.class);
                startActivityForResult(intentu, 100);
                return true;
            case R.id.action_sair:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}