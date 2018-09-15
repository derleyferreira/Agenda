package com.agenda.wanderley.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agenda.wanderley.agendaapp.MainActivity;
import com.agenda.wanderley.agendaapp.R;
import com.agenda.wanderley.interfaces.ItemClickListener;

public class ListaHorariosViewHolder extends RecyclerView.ViewHolder
implements View.OnClickListener{

    public TextView txtHora;
    public TextView txtSituacao;
    public ImageView imgLegenda;


    private ItemClickListener itemClickListener;

    public ListaHorariosViewHolder(View itemView) {
        super(itemView);

        txtHora = itemView.findViewById(R.id.ageItem_Hora);
        txtSituacao = itemView.findViewById(R.id.ageItem_situacao);
        imgLegenda = itemView.findViewById(R.id.ageItem_Legenda);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        itemClickListener.OnClick(v,getAdapterPosition(),false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}


