package com.agenda.wanderley.agendaapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CalendarView;

import com.agenda.wanderley.adapters.ListaHorariosAdapter;

public class Agendamento extends AppCompatActivity {

    private RecyclerView listaHorarios;
    private CalendarView calendarView;

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
                adapter.setCalendarView(calendarView);
            }
        });

    }


}
