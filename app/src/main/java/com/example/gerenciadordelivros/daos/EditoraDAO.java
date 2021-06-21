package com.example.gerenciadordelivros.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.gerenciadordelivros.data.DBHelper;
import com.example.gerenciadordelivros.data.EditoraContract;
import com.example.gerenciadordelivros.dominio.Editora;

import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {

    private SQLiteDatabase bd;
    private static EditoraDAO instance;

    private EditoraDAO(Context context){
        DBHelper dbHelper = DBHelper.getInstance(context);
        bd = dbHelper.getWritableDatabase();
    }

    public static EditoraDAO getInstance(Context context){
        if(instance == null){
            instance = new EditoraDAO(context.getApplicationContext());
        }

        return instance;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public List<Editora> list(){

        String[] columns = {
                EditoraContract.Columns._ID,
                EditoraContract.Columns.nome,
                EditoraContract.Columns.fundacao,
                EditoraContract.Columns.sede,
                EditoraContract.Columns.cnpj
        };

        List<Editora> editoras = new ArrayList<>();

        try(
            Cursor c = bd.query(EditoraContract.TABLE_NAME,
                        columns,
                        null,
                        null,
                        null,
                        null,
                        EditoraContract.Columns.nome)
                ){
                    if(c.moveToFirst()){
                        do {
                            Editora l = EditoraDAO.fromCursor(c);
                            editoras.add(l);
                        }while (c.moveToNext());
                    }
        }
        return editoras;
    }

    private static Editora fromCursor(Cursor c){

        Long id = c.getLong(c.getColumnIndex(EditoraContract.Columns._ID));
        String nome = c.getString(c.getColumnIndex(EditoraContract.Columns.nome));
        String fundacao = c.getString(c.getColumnIndex(EditoraContract.Columns.fundacao));
        String sede = c.getString(c.getColumnIndex(EditoraContract.Columns.sede));
        String cnpj = c.getString(c.getColumnIndex(EditoraContract.Columns.cnpj));

        return new Editora(id,nome,fundacao,sede,cnpj);
    }

    public void save(Editora editora){
        ContentValues values = new ContentValues();

        values.put(EditoraContract.Columns.nome,editora.getNome());
        values.put(EditoraContract.Columns.fundacao,editora.getFundacao());
        values.put(EditoraContract.Columns.sede,editora.getSede());
        values.put(EditoraContract.Columns.cnpj,editora.getCnpj());

        Long id = bd.insert(EditoraContract.TABLE_NAME,null,values);
        editora.setId(id);
    }

    public void update(Editora editora){
        ContentValues values = new ContentValues();

        values.put(EditoraContract.Columns.nome,editora.getNome());
        values.put(EditoraContract.Columns.fundacao,editora.getFundacao());
        values.put(EditoraContract.Columns.sede,editora.getSede());
        values.put(EditoraContract.Columns.cnpj,editora.getCnpj());

        bd.update(EditoraContract.TABLE_NAME,
                values,
                EditoraContract.Columns._ID+"=?",
                new String[]{String.valueOf(editora.getId())}
                );

    }

    public void delete(Editora editora){
        bd.delete(EditoraContract.TABLE_NAME,
                EditoraContract.Columns._ID+"=?",
                new String[]{String.valueOf(editora.getId())}
        );
    }
}