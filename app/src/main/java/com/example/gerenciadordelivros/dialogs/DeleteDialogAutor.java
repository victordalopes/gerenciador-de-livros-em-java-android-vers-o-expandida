package com.example.gerenciadordelivros.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.gerenciadordelivros.dominio.Autor;

public class DeleteDialogAutor extends DialogFragment implements DialogInterface.OnClickListener {

    private Autor autor;

    private OnDeleteListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(!(context instanceof OnDeleteListener)){
            throw new RuntimeException("Não é um OnDeleteListener!");
        }

        this.listener = (OnDeleteListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Deseja excluir o autor "+autor.getNome());
        builder.setPositiveButton("SIM", this);
        builder.setNegativeButton("NÃO",this);
        return builder.create();
    }

    public void setAutor(Autor autor){
        this.autor = autor;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == DialogInterface.BUTTON_POSITIVE){
            listener.onDelete(autor);
        }
    }

    public interface OnDeleteListener{
        void onDelete(Autor autor);
    }
}
