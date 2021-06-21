package com.example.gerenciadordelivros.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gerenciadordelivros.R;
import com.example.gerenciadordelivros.daos.UsuarioDAO;
import com.example.gerenciadordelivros.dominio.Usuario;

public class EditarUsuarioActivity extends AppCompatActivity {

    private EditText edt_login;
    private EditText edt_senha;
    private EditText edt_status;
    private EditText edt_tipo;

    private UsuarioDAO usuarioDAO;

    private Usuario usuario;

    // public EditarUsuarioActivity(Context baseContext) {    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        edt_login = findViewById(R.id.edt_login);
        edt_senha = findViewById(R.id.edt_senha);
        edt_status = findViewById(R.id.edt_status);
        edt_tipo = findViewById(R.id.edt_tipo);

        usuarioDAO = UsuarioDAO.getInstance(this);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        if (usuario != null) {
            edt_login.setText(usuario.getLogin());
            edt_senha.setText(usuario.getSenha());
            edt_status.setText(usuario.getStatus());
            edt_tipo.setText(usuario.getTipo());
        }
    }

    public void cancelar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void processar(View view) {

        String login = edt_login.getText().toString();
        String senha = edt_senha.getText().toString();
        String status = edt_status.getText().toString();
        String tipo = edt_tipo.getText().toString();

        String msg;

        if (usuario == null) {
            Usuario usuario = new Usuario(login, senha, status, tipo);
            usuarioDAO.save(usuario);
            msg = "Usuario adicionado com sucesso! ID= " + usuario.getId();
        } else {
            usuario.setLogin(login);
            usuario.setSenha(senha);
            usuario.setStatus(status);
            usuario.setTipo(tipo);

            usuarioDAO.update(usuario);
            msg = "Usuario atualizado com sucesso! ID= " + usuario.getId();
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        Intent intentu = new Intent(getApplicationContext(), UsuarioActivity.class);
        startActivityForResult(intentu, 100);
    }

    //NEM SEI PQ ESSA GALERA ESTAVA AQUI
    //private DBHelperUsuario dbHelper = null;
    //private SQLiteDatabase db = null;
}