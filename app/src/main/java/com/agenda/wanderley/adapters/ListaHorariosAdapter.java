package com.agenda.wanderley.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.agenda.wanderley.agendaapp.Agendamentos;
import com.agenda.wanderley.agendaapp.Global;
import com.agenda.wanderley.agendaapp.R;
import com.agenda.wanderley.interfaces.ItemClickListener;
import com.agenda.wanderley.serv.AgendamentoService;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListaHorariosAdapter extends RecyclerView.Adapter<ListaHorariosViewHolder> {

    private Context context;

    List<String> horarios = new ArrayList<>();

    private Calendar calendario;

    public void setCalendarView(CalendarView calendarView, int ano, int mes, int dia) {
        this.calendarView = calendarView;

        calendario = GregorianCalendar.getInstance();

        calendario.set(ano, mes, dia);

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

                String situacao = context.getString(R.string.situacao_disponivel);

                if (holder.txtSituacao.getText().toString().equals(situacao)) {
                    //após confirmação
                    populaAgendamento(holder);
                    try {
                        gravaAgendamento();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    holder.txtSituacao.setText(R.string.seu_agendamento);
                    holder.txtSituacao.setTextColor(Color.BLUE);
                    holder.txtSituacao.setTypeface(null, Typeface.BOLD);
                    holder.imgLegenda.setImageResource(android.R.drawable.presence_online);
                }
                else{

                    Toast.makeText(context,R.string.error_valid_horario_indisponivel,Toast.LENGTH_LONG).show();

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

        agendamentos.setAgeCliente(Global.usuarioLogado.getPesId());
        agendamentos.setAgeFormapgto(2);

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


            String s = sdf.format(calendario.getTime());


            Date d = sdf.parse(s);

            agendamentos.setAgeData(d);

            SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm");

            Date d2 = sdf2.parse(holder.txtHora.getText().toString());

            agendamentos.setAgeHora(d2);

        } catch (Exception e) {
            e.printStackTrace();
        }


        agendamentos.setAgeObservacoes("");
        agendamentos.setAgeProfissional(5);
        agendamentos.setAgeSituacao(0);

    }

    private void gravaAgendamento() throws ExecutionException, InterruptedException {

        AgendamentoService serv = new AgendamentoService();
        if (serv.cadastraAgendamento(agendamentos)){
            Toast.makeText(context,"Agendamento realizado com sucesso.", Toast.LENGTH_LONG).show();
        }
    }

}
