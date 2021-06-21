package com.example.gerenciadordelivros.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.gerenciadordelivros.data.DBHelper;
import com.example.gerenciadordelivros.data.UsuarioContract;
import com.example.gerenciadordelivros.dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private SQLiteDatabase bd;
    private static UsuarioDAO instance;

    public UsuarioDAO(Context context){
        DBHelper dbHelper = DBHelper.getInstance(context);
        bd = dbHelper.getWritableDatabase();
    }

    public static UsuarioDAO getInstance(Context context){
        if(instance == null){
            instance = new UsuarioDAO(context.getApplicationContext());
        }

        return instance;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public List<Usuario> list(){

        String[] columns = {
                UsuarioContract.Columns._ID,
                UsuarioContract.Columns.login,
                UsuarioContract.Columns.senha,
                UsuarioContract.Columns.status,
                UsuarioContract.Columns.tipo
        };

        List<Usuario> usuarios = new ArrayList<>();

        try(
            Cursor c = bd.query(UsuarioContract.TABLE_NAME,
                        columns,
                        null,
                        null,
                        null,
                        null,
                        UsuarioContract.Columns.login)
                ){
                    if(c.moveToFirst()){
                        do {
                            Usuario l = UsuarioDAO.fromCursor(c);
                            usuarios.add(l);
                        }while (c.moveToNext());
                    }
        }
        return usuarios;
    }

    private static Usuario fromCursor(Cursor c){

        Long id = c.getLong(c.getColumnIndex(UsuarioContract.Columns._ID));
        String login = c.getString(c.getColumnIndex(UsuarioContract.Columns.login));
        String senha = c.getString(c.getColumnIndex(UsuarioContract.Columns.senha));
        String status = c.getString(c.getColumnIndex(UsuarioContract.Columns.status));
        String tipo = c.getString(c.getColumnIndex(UsuarioContract.Columns.tipo));

        return new Usuario(id,login,senha,status,tipo);
    }

    public void save(Usuario usuario){
        ContentValues values = new ContentValues();

        values.put(UsuarioContract.Columns.login,usuario.getLogin());
        values.put(UsuarioContract.Columns.senha,usuario.getSenha());
        values.put(UsuarioContract.Columns.status,usuario.getStatus());
        values.put(UsuarioContract.Columns.tipo,usuario.getTipo());

        Long id = bd.insert(UsuarioContract.TABLE_NAME,null,values);
        usuario.setId(id);
    }

    public void update(Usuario usuario){
        ContentValues values = new ContentValues();

        values.put(UsuarioContract.Columns.login,usuario.getLogin());
        values.put(UsuarioContract.Columns.senha,usuario.getSenha());
        values.put(UsuarioContract.Columns.status,usuario.getStatus());
        values.put(UsuarioContract.Columns.tipo,usuario.getTipo());

        bd.update(UsuarioContract.TABLE_NAME,
                values,
                UsuarioContract.Columns._ID+"=?",
                new String[]{String.valueOf(usuario.getId())}
                );

    }

    public void delete(Usuario usuario){
        bd.delete(UsuarioContract.TABLE_NAME,
                UsuarioContract.Columns._ID+"=?",
                new String[]{String.valueOf(usuario.getId())}
        );
    }
}