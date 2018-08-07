package com.agenda.wanderley.serv;

import android.os.AsyncTask;

import com.agenda.wanderley.agendaapp.Agendamentos;
import com.agenda.wanderley.agendaapp.Constantes;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class AgendamentoService extends AsyncTask<Integer,Void,Agendamentos> {

    private URL url;
    private HttpURLConnection connection;
    private Gson gson;
    private Agendamentos agendamentos;


    @Override
    protected Agendamentos doInBackground(Integer... integers) {

        if (integers[0] == 0){
            try {
                cadastrarAgendamento(agendamentos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }



    public void gravaAgendamento(Agendamentos agendamento) throws IOException {
        configuraServico("tbagendamento");


    }

    private void configuraServico(String pServico) throws IOException {
        url = new URL(Constantes.URL + pServico);
        connection = (HttpURLConnection) url.openConnection();
    }

    private void cadastrarAgendamento(Agendamentos agendamentos) throws IOException {
        if (gson == null){
            gson = new Gson();
        }

        String pessoaJson = gson.toJson(agendamentos);

        configuraServico(".tbagend");

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        connection.setConnectTimeout(5000);

        PrintStream printStream = new PrintStream(connection.getOutputStream());
        printStream.println(pessoaJson); //seta o que voce vai enviar

        connection.connect(); //envia para o servidor

        String jsonDeResposta = new Scanner(connection.getInputStream()).next(); //pega resposta


    }

    public void cadastraAgendamento(Agendamentos agendamentos) throws ExecutionException, InterruptedException {
        this.agendamentos = agendamentos;
        execute(0).get();
    }


}
