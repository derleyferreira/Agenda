package com.agenda.wanderley.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.agenda.wanderley.agendaapp.Agendamentos;
import com.agenda.wanderley.agendaapp.R;
import com.agenda.wanderley.interfaces.ItemClickListener;
import com.agenda.wanderley.serv.AgendamentoService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListaHorariosAdapter extends RecyclerView.Adapter<ListaHorariosViewHolder> {

    private Context context;

    List<String> horarios = new ArrayList<>();

    public void setCalendarView(CalendarView calendarView) {
        this.calendarView = calendarView;
    }

    private CalendarView calendarView;

    private Agendamentos agendamentos;

    public ListaHorariosAdapter(Context context) {
        this.context = context;

        montaListaHorarios();
    }

    @NonNull
    @Override
    public ListaHorariosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_agendamento_item,parent,false);

        ListaHorariosViewHolder holder = new ListaHorariosViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ListaHorariosViewHolder holder, int position) {
        String hora = horarios.get(position);
        holder.txtHora.setText(hora);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position, boolean isLongClick) {

                //após confirmação
                populaAgendamento(holder);
                try {
                    gravaAgendamento();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
       return horarios.size();
    }

    private void montaListaHorarios(){

        horarios.add("08:00");
        horarios.add("09:00");
        horarios.add("10:00");
        horarios.add("11:00");
        horarios.add("13:00");
        horarios.add("14:00");
        horarios.add("15:00");
        horarios.add("16:00");
        horarios.add("17:00");
        horarios.add("18:00");

    }

    private void populaAgendamento(ListaHorariosViewHolder holder){

        agendamentos = new Agendamentos();

        agendamentos.setAgeData(calendarView.getDate());
        agendamentos.setAgeCliente(1);
        agendamentos.setAgeFormapgto(2);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String h = "13:30";
            Date d = sdf.parse(h);
            agendamentos.setAgeHora(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        agendamentos.setAgeObservacoes("teste teste");
        agendamentos.setAgeProfissional(5);
        agendamentos.setAgeSituacao(0);

    }

    private void gravaAgendamento() throws ExecutionException, InterruptedException {

        AgendamentoService serv = new AgendamentoService();
        serv.cadastraAgendamento(agendamentos);
    }

}
