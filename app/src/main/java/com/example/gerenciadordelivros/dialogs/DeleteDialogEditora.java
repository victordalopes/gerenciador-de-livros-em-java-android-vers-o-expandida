package com.example.gerenciadordelivros.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.gerenciadordelivros.dominio.Editora;

public class DeleteDialogEditora extends DialogFragment implements DialogInterface.OnClickListener {

    private Editora editora;
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
        builder.setMessage("Deseja excluir a editora "+editora.getNome());
        builder.setPositiveButton("SIM", this);
        builder.setNegativeButton("NÃO",this);
        return builder.create();
    }

    public void setEditora(Editora editora){
        this.editora = editora;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == DialogInterface.BUTTON_POSITIVE){
            listener.onDelete(editora);
        }
    }

    public interface OnDeleteListener{
        void onDelete(Editora editora);
    }
}
