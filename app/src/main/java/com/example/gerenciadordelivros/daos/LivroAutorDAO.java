package com.example.gerenciadordelivros.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.gerenciadordelivros.data.DBHelper;
import com.example.gerenciadordelivros.dominio.LivroAutor;
import java.util.ArrayList;
import java.util.List;

public class LivroAutorDAO {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public LivroAutorDAO(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase( );
    }

    public long add(LivroAutor livroAutor) {
        ContentValues values = new ContentValues( );
        values.put("titulo", livroAutor.getTitulo( ));
        values.put("autor", livroAutor.getAutor( ));
        return database.insert("livaut", null, values);
    }

    public List<LivroAutor> getAll() {
        List<LivroAutor> livroAutor = new ArrayList<>( );
        Cursor cursor = database.query("livaut", new String[]{"id", "titulo", "autor"},
                null, null, null, null, null);
        while (cursor.moveToNext( )) {
            LivroAutor m = new LivroAutor( );
            m.setId(cursor.getLong(0));
            m.setTitulo(cursor.getString(1));
            m.setAutor(cursor.getString(2));
            livroAutor.add(m);
        }
        return livroAutor;
    }

    public void excluir(LivroAutor pm) {
    }

    public ArrayList<String> getLivroAutor () {
        ArrayList<String> livroAutor = new ArrayList<String>( );
        Cursor cursor = database.rawQuery("SELECT * FROM livaut", null);
        if (cursor != null && cursor.moveToFirst( )) {
            do {
                livroAutor.add(cursor.getString(cursor.getColumnIndex("nome")));
            } while (cursor.moveToNext( ));
        }
        return livroAutor;
    }
}