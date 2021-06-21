package com.example.gerenciadordelivros.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gerenciadordelivros.R;
import com.example.gerenciadordelivros.dominio.Usuario;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioHolder>{

    private List<Usuario> usuarios;
    private Context context;

    private OnUsuarioListener onUsuarioListener;

    public UsuarioAdapter(List<Usuario> usuarios, Context context, OnUsuarioListener onUsuarioListener) {
        this.usuarios = usuarios;
        this.context = context;
        this.onUsuarioListener = onUsuarioListener;
    }

    @NonNull
    @Override
    public UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_usuario,parent,false);
        UsuarioHolder usuarioHolder = new UsuarioHolder(view, onUsuarioListener);

        return usuarioHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioHolder holder, int position) {

        Usuario usuario = usuarios.get(position);

        holder.txtLogin.setText(usuario.getLogin());
        holder.txtSenha.setText(usuario.getSenha());
        holder.txtStatus.setText(usuario.getStatus());
        holder.txtTipo.setText(usuario.getTipo());
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public void setItems(List<Usuario> usuarios){
        this.usuarios = usuarios;
    }

    public Usuario getItem(int posicao){
        return usuarios.get(posicao);
    }

    public class UsuarioHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView txtLogin;
        public TextView txtSenha;
        public TextView txtStatus;
        public TextView txtTipo;

        public OnUsuarioListener onUsuarioListener;

        public UsuarioHolder(View view, OnUsuarioListener onUsuarioListener){
            super(view);

            txtLogin = view.findViewById(R.id.txtLogin);
            txtSenha = view.findViewById(R.id.txtSenha);
            txtStatus = view.findViewById(R.id.txtStatus);
            txtTipo = view.findViewById(R.id.txtTipo);

            this.onUsuarioListener = onUsuarioListener;

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onUsuarioListener.onUsuarioClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            onUsuarioListener.onUsuarioLongClick(getAdapterPosition());
            return true;
        }
    }

    public interface OnUsuarioListener{
        void onUsuarioClick(int posicao);
        void onUsuarioLongClick(int posicao);
    }
}