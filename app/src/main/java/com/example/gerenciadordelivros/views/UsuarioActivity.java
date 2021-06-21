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
import com.example.gerenciadordelivros.adapter.UsuarioAdapter;
import com.example.gerenciadordelivros.daos.UsuarioDAO;
import com.example.gerenciadordelivros.dialogs.DeleteDialogUsuario;
import com.example.gerenciadordelivros.dominio.Usuario;

import java.util.List;

public class UsuarioActivity extends AppCompatActivity implements UsuarioAdapter.OnUsuarioListener, DeleteDialogUsuario.OnDeleteListener {

    private UsuarioDAO usuarioDAO;
    UsuarioAdapter usuarioAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewusuario);

        RecyclerView recyclerview = findViewById(R.id.recyclerviewusuario);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        usuarioDAO = UsuarioDAO.getInstance(this);

        List<Usuario> listaUsuarios = usuarioDAO.list();

        usuarioAdapter = new UsuarioAdapter(listaUsuarios,this, this);

        recyclerview.setAdapter(usuarioAdapter);
    }

    public void usuarios (View view) {
        Intent intentu = new Intent(getApplicationContext(), EditarUsuarioActivity.class);
        startActivity(intentu);
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
                atualizaListaUsuarios();
        }
        if(requestCode == 101 && resultCode == RESULT_OK){
            atualizaListaUsuarios();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void atualizaListaUsuarios(){
        List<Usuario> usuarios = usuarioDAO.list();
        usuarioAdapter.setItems(usuarios);
        usuarioAdapter.notifyDataSetChanged();
    }

    @Override
    public void onUsuarioClick(int posicao) {

        Intent intent = new Intent(getApplicationContext(),EditarUsuarioActivity.class);
        intent.putExtra("usuario",usuarioAdapter.getItem(posicao));
        startActivityForResult(intent, 101);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onUsuarioLongClick(int posicao) {

        Usuario usuario = usuarioAdapter.getItem(posicao);

        DeleteDialogUsuario dialog = new DeleteDialogUsuario();
        dialog.setUsuario(usuario);
        dialog.show(getSupportFragmentManager(), "deleteDialog");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onDelete(Usuario usuario) {
        usuarioDAO.delete(usuario);;
        atualizaListaUsuarios();

        Toast.makeText(this,"Usuario excluída com sucesso!", Toast.LENGTH_LONG).show();
    }
}
