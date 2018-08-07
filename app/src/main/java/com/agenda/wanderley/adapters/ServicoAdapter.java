package com.agenda.wanderley.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agenda.wanderley.agendaapp.R;
import com.agenda.wanderley.agendaapp.Servicos;

import java.util.List;

public class ServicoAdapter extends RecyclerView.Adapter<ServicosViewHolder> {

    private List<Servicos> servicosList;
    private Context context;

    public ServicoAdapter(List<Servicos> servicosList, Context context) {
        this.servicosList = servicosList;
        this.context = context;
    }

    @NonNull
    @Override
    public ServicosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.especialidades_item,parent,false);

        ServicosViewHolder holder = new ServicosViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServicosViewHolder holder, int position) {

        Servicos serv = servicosList.get(position);

        holder.txtTitulo.setText(serv.getSerDescricao());

    }


    @Override
    public int getItemCount() {
        return servicosList.size();
    }
}
