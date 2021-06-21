package com.example.gerenciadordelivros.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gerenciadordelivros.dominio.Livro;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String BD_NAME = "livrosbd";
    public static final int BD_VERSION = 3;

    private static DBHelper instance;

    private static String SQL_CREATEL = String.format(
        "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, "+
        "%s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL)",
        LivroContract.TABLE_NAME,
        LivroContract.Columns._ID,
        LivroContract.Columns.titulo,
        LivroContract.Columns.autor,
        LivroContract.Columns.editora
    );

    private static String SQL_CREATEA = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "%s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL)",
            AutorContract.TABLE_NAME,
            AutorContract.Columns._ID,
            AutorContract.Columns.nome,
            AutorContract.Columns.idade,
            AutorContract.Columns.editora,
            AutorContract.Columns.genero
    );

    private static String SQL_CREATEE = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "%s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL)",
            EditoraContract.TABLE_NAME,
            EditoraContract.Columns._ID,
            EditoraContract.Columns.nome,
            EditoraContract.Columns.fundacao,
            EditoraContract.Columns.sede,
            EditoraContract.Columns.cnpj
    );

    private static String SQL_CREATEU = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "%s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL)",
            UsuarioContract.TABLE_NAME,
            UsuarioContract.Columns._ID,
            UsuarioContract.Columns.login,
            UsuarioContract.Columns.senha,
            UsuarioContract.Columns.status,
            UsuarioContract.Columns.tipo
    );

    private static String SQL_CREATELA = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "%s TEXT NOT NULL, %s TEXT NOT NULL)",
            LivroAutorContract.TABLE_NAME,
            LivroAutorContract.Columns._ID,
            LivroAutorContract.Columns.titulo,
            LivroAutorContract.Columns.autor
    );

    private static String SQL_DROPL = "DROP TABLE IF EXISTS "+LivroContract.TABLE_NAME;
    private static String SQL_DROPA = "DROP TABLE IF EXISTS "+AutorContract.TABLE_NAME;
    private static String SQL_DROPE = "DROP TABLE IF EXISTS "+EditoraContract.TABLE_NAME;
    private static String SQL_DROPU = "DROP TABLE IF EXISTS "+UsuarioContract.TABLE_NAME;
    private static String SQL_DROPLA = "DROP TABLE IF EXISTS "+LivroAutorContract.TABLE_NAME;

    public DBHelper(Context context){
        super(context,BD_NAME,null,BD_VERSION);
    }

    public static DBHelper getInstance(Context context){
        if(instance == null){
            instance = new DBHelper(context);
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(SQL_DROPL);
        bd.execSQL(SQL_DROPA);
        bd.execSQL(SQL_DROPE);
        bd.execSQL(SQL_DROPU);
        bd.execSQL(SQL_DROPLA);
        bd.execSQL(SQL_CREATEL);
        bd.execSQL(SQL_CREATEA);
        bd.execSQL(SQL_CREATEE);
        bd.execSQL(SQL_CREATEU);
        bd.execSQL(SQL_CREATELA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        onCreate(bd);
    }

    public ArrayList<String> listaLivros() {
        SQLiteDatabase db = getReadableDatabase( );
        String sql_buscalivros = "SELECT * FROM " + LivroContract.TABLE_NAME;

        ArrayList<String> livros = new ArrayList<>( );

        Cursor cursor = db.rawQuery(sql_buscalivros, null);
        while (cursor.moveToNext( )) {
            livros.add(cursor.getString(cursor.getColumnIndex("titulo")));
        }
        db.close();
        return livros;
    }

    public ArrayList<String> listaAutores() {
        SQLiteDatabase db = getReadableDatabase( );
        String sql_buscaautores = "SELECT * FROM " + AutorContract.TABLE_NAME;

        ArrayList<String> autores = new ArrayList<>( );

        Cursor cursor = db.rawQuery(sql_buscaautores, null);
        while (cursor.moveToNext( )) {
            autores.add(cursor.getString(cursor.getColumnIndex("nome")));
        }
        db.close();
        return autores;
    }

}
