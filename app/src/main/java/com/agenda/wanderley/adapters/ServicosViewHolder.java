package com.agenda.wanderley.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.agenda.wanderley.agendaapp.Agendamento;
import com.agenda.wanderley.agendaapp.R;

public class ServicosViewHolder extends RecyclerView.ViewHolder {

    public TextView txtTitulo;
    public AppCompatButton btnAgendar;
    private View view;
    public ServicosViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        carregaComponentes();
        atribuiEventos();
    }

    private void atribuiEventos(){
        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), Agendamento.class);
                Context context = view.getContext();
                try {
                    context.startActivity(intent);
                }catch(Exception erro){
                    String err = erro.getMessage();
                }

            }
        });
    }

    private void carregaComponentes(){
        txtTitulo  = itemView.findViewById(R.id.especialidadeitem_Titulo);
        btnAgendar = itemView.findViewById(R.id.especialidadeitem_btnAgendar);
    }

}
