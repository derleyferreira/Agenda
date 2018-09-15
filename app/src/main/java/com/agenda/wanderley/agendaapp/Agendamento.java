package com.agenda.wanderley.agendaapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;


import com.agenda.wanderley.adapters.ListaHorariosAdapter;
import com.agenda.wanderley.adapters.ListaHorariosViewHolder;
import com.agenda.wanderley.serv.AgendamentoService;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

public class Agendamento extends AppCompatActivity {

    private RecyclerView listaHorarios;
    private CalendarView calendarView;

    private List<Time> horariosAgendados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);

        calendarView = findViewById(R.id.agendamento_calendario);

        final ListaHorariosAdapter adapter = new ListaHorariosAdapter(this);

        listaHorarios = findViewById(R.id.agendamento_ListaHorarios);

        listaHorarios.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        listaHorarios.setAdapter(adapter);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dayOfMonth++;

                adapter.setCalendarView(calendarView, year,month,dayOfMonth);

                Calendar c = GregorianCalendar.getInstance();
                c.set(year,month,dayOfMonth);

                try {
                    carregaAgendamentosNaData(c);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void carregaAgendamentosNaData(Calendar c) throws ExecutionException, InterruptedException {

        for (int i = 0; i < listaHorarios.getChildCount(); i++) {

            View item = listaHorarios.getChildAt(i);

            TextView txtSituacao = item.findViewById(R.id.ageItem_situacao);
            txtSituacao.setTypeface(null, Typeface.NORMAL);
            ImageView imgLegenda = item.findViewById(R.id.ageItem_Legenda);
            txtSituacao.setText(R.string.situacao_disponivel);
            txtSituacao.setTextColor(Color.GREEN);
            imgLegenda.setImageResource(android.R.drawable.presence_online);
        }


        AgendamentoService serv = new AgendamentoService();

        List<Agendamentos> listaAgendados = serv.retornaListaAgendamentos(c);

        if (listaAgendados != null) {
            int teste = listaAgendados.size();

            for (Agendamentos agendamento: listaAgendados) {

                String hora = agendamento.getAgeHora().toString();

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));

                Date hora2 = agendamento.getAgeHora();

                String s = sdf.format(hora2);

                String s2 = s;

                for (int i = 0; i < listaHorarios.getChildCount(); i++) {

                    View item = listaHorarios.getChildAt(i);

                    TextView txtHora = item.findViewById(R.id.ageItem_Hora);
                    ImageView imgSituacao = item.findViewById(R.id.ageItem_Legenda);
                    if (txtHora.getText().toString().equals(s)) {

                        TextView txtSituacao = item.findViewById(R.id.ageItem_situacao);

                        if (Global.usuarioLogado.getPesId() != agendamento.getAgeCliente()) {
                            txtSituacao.setText(R.string.situacao_indiposnivel);
                            txtSituacao.setTextColor(Color.RED);
                            imgSituacao.setImageResource(android.R.drawable.presence_offline);
                        }
                        else
                        {
                            txtSituacao.setText(R.string.seu_agendamento);
                            txtSituacao.setTextColor(Color.BLUE);
                            txtSituacao.setTypeface(null, Typeface.BOLD);
                            imgSituacao.setImageResource(android.R.drawable.presence_online);
                        }

                        break;
                    }
                }
            }

        }
        else {
            for (int i = 0; i < listaHorarios.getChildCount(); i++) {

                View item = listaHorarios.getChildAt(i);

                TextView txtSituacao = item.findViewById(R.id.ageItem_situacao);
                ImageView imgSituacao = item.findViewById(R.id.ageItem_Legenda);
                txtSituacao.setText(R.string.situacao_disponivel);
                txtSituacao.setTextColor(Color.GREEN);
                imgSituacao.setImageResource(android.R.drawable.presence_online);

            }
        }



    }


}
