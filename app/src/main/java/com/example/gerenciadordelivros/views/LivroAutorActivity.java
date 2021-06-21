package com.example.gerenciadordelivros.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gerenciadordelivros.R;
import com.example.gerenciadordelivros.daos.LivroAutorDAO;
import com.example.gerenciadordelivros.data.DBHelper;
import com.example.gerenciadordelivros.dominio.LivroAutor;

public class LivroAutorActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnClick;

    private DBHelper dbHelper;

    private EditText titulo;
    private EditText autor;
    private LivroAutorDAO dao;
    private LivroAutor livaut = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livro_autor);

        btnClick = (Button) findViewById(R.id.btnSalvar);
        btnClick.setOnClickListener(this);

        titulo = findViewById(R.id.editLivro);
        autor = findViewById(R.id.editAutor);

        dao = new LivroAutorDAO(this);

        Intent it = getIntent( );
        if (it.hasExtra("livro")) {
            livaut = (LivroAutor)
                    it.getSerializableExtra("livro");
            titulo.setText(livaut.getTitulo( ));
            autor.setText(livaut.getAutor( ));
        }

        final TextView textViewLivro = findViewById(R.id.editLivro);
        final Spinner spinnerlivros = findViewById(R.id.spinnerLivros);

        final TextView textViewAutor = findViewById(R.id.editAutor);
        final Spinner spinnerautores = findViewById(R.id.spinnerAutores);

        spinnerlivros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener( ) {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textViewLivro.setText(spinnerlivros.getSelectedItem( ).toString( ));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerautores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener( ) {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textViewAutor.setText(spinnerautores.getSelectedItem( ).toString( ));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        DBHelper dbHelper = new DBHelper(this);

        ArrayAdapter<String> adapter_spinnerlivros = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, dbHelper.listaLivros( ));

        spinnerlivros.setAdapter(adapter_spinnerlivros);

        ArrayAdapter<String> adapter_spinnerautores = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, dbHelper.listaAutores( ));

        spinnerautores.setAdapter(adapter_spinnerautores);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSalvar) {
            if (livaut == null) {
                livaut = new LivroAutor();

                livaut.setTitulo(titulo.getText().toString());
                livaut.setAutor(autor.getText().toString());
                long id = dao.add(livaut);
                Toast.makeText(this, "Relação estabelecida com id:" + id, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId( );

        if (id == android.R.id.home) {
            finish( );
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}