package com.example.gerenciadordelivros.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gerenciadordelivros.R;
import com.example.gerenciadordelivros.dominio.Editora;

import java.util.List;

public class EditoraAdapter extends RecyclerView.Adapter<EditoraAdapter.EditoraHolder>{

    private List<Editora> editoras;
    private Context context;

    private OnEditoraListener onEditoraListener;

    public EditoraAdapter(List<Editora> editoras, Context context, OnEditoraListener onEditoraListener) {
        this.editoras = editoras;
        this.context = context;
        this.onEditoraListener = onEditoraListener;
    }

    @NonNull
    @Override
    public EditoraHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_editora,parent,false);
        EditoraHolder editoraHolder = new EditoraHolder(view, onEditoraListener);

        return editoraHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EditoraHolder holder, int position) {

        Editora editora = editoras.get(position);

        holder.txtNome.setText(editora.getNome());
        holder.txtFundacao.setText(editora.getFundacao());
        holder.txtSede.setText(editora.getSede());
        holder.txtCnpj.setText(editora.getCnpj());
    }

    @Override
    public int getItemCount() {
        return editoras.size();
    }

    public void setItems(List<Editora> editoras){
        this.editoras = editoras;
    }

    public Editora getItem(int posicao){
        return editoras.get(posicao);
    }

    public class EditoraHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView txtNome;
        public TextView txtFundacao;
        public TextView txtSede;
        public TextView txtCnpj;

        public OnEditoraListener onEditoraListener;

        public EditoraHolder(View view, OnEditoraListener onEditoraListener){
            super(view);

            txtNome = view.findViewById(R.id.txtNome);
            txtFundacao = view.findViewById(R.id.txtFundacao);
            txtSede = view.findViewById(R.id.txtSede);
            txtCnpj = view.findViewById(R.id.txtCnpj);

            this.onEditoraListener = onEditoraListener;

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onEditoraListener.onEditoraClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            onEditoraListener.onEditoraLongClick(getAdapterPosition());
            return true;
        }
    }

    public interface OnEditoraListener{
        void onEditoraClick(int posicao);
        void onEditoraLongClick(int posicao);
    }
}