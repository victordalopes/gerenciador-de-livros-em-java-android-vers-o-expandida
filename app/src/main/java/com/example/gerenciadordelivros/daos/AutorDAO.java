package com.example.gerenciadordelivros.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.gerenciadordelivros.data.DBHelper;
import com.example.gerenciadordelivros.data.AutorContract;
import com.example.gerenciadordelivros.dominio.Autor;

import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    private SQLiteDatabase bd;
    private static AutorDAO instance;

    private AutorDAO(Context context){
        DBHelper dbHelper = DBHelper.getInstance(context);
        bd = dbHelper.getWritableDatabase();
    }

    public static AutorDAO getInstance(Context context){
        if(instance == null){
            instance = new AutorDAO(context.getApplicationContext());
        }

        return instance;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public List<Autor> list(){

        String[] columns = {
                AutorContract.Columns._ID,
                AutorContract.Columns.nome,
                AutorContract.Columns.idade,
                AutorContract.Columns.editora,
                AutorContract.Columns.genero
        };

        List<Autor> autores = new ArrayList<>();

        try(
            Cursor c = bd.query(AutorContract.TABLE_NAME,
                        columns,
                        null,
                        null,
                        null,
                        null,
                        AutorContract.Columns.nome)
                ){
                    if(c.moveToFirst()){
                        do {
                            Autor l = AutorDAO.fromCursor(c);
                            autores.add(l);
                        }while (c.moveToNext());
                    }
        }
        return autores;
    }

    private static Autor fromCursor(Cursor c){

        Long id = c.getLong(c.getColumnIndex(AutorContract.Columns._ID));
        String nome = c.getString(c.getColumnIndex(AutorContract.Columns.nome));
        String idade = c.getString(c.getColumnIndex(AutorContract.Columns.idade));
        String editora = c.getString(c.getColumnIndex(AutorContract.Columns.editora));
        String genero = c.getString(c.getColumnIndex(AutorContract.Columns.genero));

        return new Autor(id,nome,idade,editora,genero);
    }

    public void save(Autor autor){
        ContentValues values = new ContentValues();

        values.put(AutorContract.Columns.nome,autor.getNome());
        values.put(AutorContract.Columns.idade,autor.getIdade());
        values.put(AutorContract.Columns.editora,autor.getEditora());
        values.put(AutorContract.Columns.genero,autor.getGenero());

        Long id = bd.insert(AutorContract.TABLE_NAME,null,values);
        autor.setId(id);
    }

    public void update(Autor autor){
        ContentValues values = new ContentValues();

        values.put(AutorContract.Columns.nome,autor.getNome());
        values.put(AutorContract.Columns.idade,autor.getIdade());
        values.put(AutorContract.Columns.editora,autor.getEditora());
        values.put(AutorContract.Columns.genero,autor.getGenero());

        bd.update(AutorContract.TABLE_NAME,
                values,
                AutorContract.Columns._ID+"=?",
                new String[]{String.valueOf(autor.getId())}
                );

    }

    public void delete(Autor autor){
        bd.delete(AutorContract.TABLE_NAME,
                AutorContract.Columns._ID+"=?",
                new String[]{String.valueOf(autor.getId())}
        );
    }
}