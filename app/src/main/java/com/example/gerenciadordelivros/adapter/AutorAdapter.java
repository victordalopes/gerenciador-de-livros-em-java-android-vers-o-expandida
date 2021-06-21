package com.example.gerenciadordelivros.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gerenciadordelivros.R;
import com.example.gerenciadordelivros.dominio.Autor;

import java.util.List;

public class AutorAdapter extends RecyclerView.Adapter<AutorAdapter.AutorHolder>{

    private List<Autor> autores;
    private Context context;

    private OnAutorListener onAutorListener;

    public AutorAdapter(List<Autor> autores, Context context, OnAutorListener onAutorListener) {
        this.autores = autores;
        this.context = context;
        this.onAutorListener = onAutorListener;
    }

    @NonNull
    @Override
    public AutorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_autor,parent,false);
        AutorHolder autorHolder = new AutorHolder(view, onAutorListener);

        return autorHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AutorHolder holder, int position) {

        Autor autor = autores.get(position);

        holder.txtNome.setText(autor.getNome());
        holder.txtIdade.setText(autor.getIdade());
        holder.txtEditora.setText(autor.getEditora());
        holder.txtGenero.setText(autor.getGenero());
    }

    @Override
    public int getItemCount() {
        return autores.size();
    }

    public void setItems(List<Autor> autores){
        this.autores = autores;
    }

    public Autor getItem(int posicao){
        return autores.get(posicao);
    }

    public class AutorHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView txtNome;
        public TextView txtIdade;
        public TextView txtEditora;
        public TextView txtGenero;

        public OnAutorListener onAutorListener;

        public AutorHolder(View view, OnAutorListener onAutorListener){
            super(view);

            txtNome = view.findViewById(R.id.txtNome);
            txtIdade = view.findViewById(R.id.txtIdade);
            txtEditora = view.findViewById(R.id.txtEditora);
            txtGenero = view.findViewById(R.id.txtGenero);

            this.onAutorListener = onAutorListener;

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onAutorListener.onAutorClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            onAutorListener.onAutorLongClick(getAdapterPosition());
            return true;
        }
    }

    public interface OnAutorListener{
        void onAutorClick(int posicao);
        void onAutorLongClick(int posicao);
    }
}