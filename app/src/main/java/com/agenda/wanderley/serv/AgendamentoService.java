package com.agenda.wanderley.serv;

import android.os.AsyncTask;

import com.agenda.wanderley.agendaapp.Agendamento;
import com.agenda.wanderley.agendaapp.Agendamentos;
import com.agenda.wanderley.agendaapp.Constantes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class AgendamentoService extends AsyncTask<Integer,Void,List<Agendamentos>> {

    private URL url;
    private HttpURLConnection connection;
    private Gson gson;
    private Agendamentos agendamentos;

    private Calendar dataPesquisa;

    @Override
    protected List<Agendamentos> doInBackground(Integer... integers) {

        if (integers[0] == 0){
            try {
               if  (cadastrarAgendamento(agendamentos)){
                   List<Agendamentos> retorno = new ArrayList<>();
                   retorno.add(agendamentos);
                   return retorno;
               }
            } catch (IOException e) {
                return null;
            }
        }
        else if (integers[0] == 1){
            try {
                return carregaAgendamentos(dataPesquisa);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            return  null;
        }
        return  null;
    }



    public void gravaAgendamento(Agendamentos agendamento) throws IOException {
        configuraServico("tbagendamento");


    }

    private void configuraServico(String pServico) throws IOException {
        url = new URL(Constantes.URL + pServico);
        connection = (HttpURLConnection) url.openConnection();
    }

    private boolean cadastrarAgendamento(Agendamentos agendamentos) throws IOException {
        if (gson == null){
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        }

        String pessoaJson = gson.toJson(agendamentos);

        configuraServico(".tbagendamento");

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        connection.setConnectTimeout(5000);

        PrintStream printStream = new PrintStream(connection.getOutputStream());
        printStream.println(pessoaJson); //seta o que voce vai enviar

        connection.connect(); //envia para o servidor

        try {

            String jsonDeResposta = new Scanner(connection.getInputStream()).next(); //pega resposta
            return  true;
        }catch (NoSuchElementException erro)
        {
            return  true;
            //sem resposta do servidor
        }catch (Exception erro)
        {
            return  false;
        }
    }

    private List<Agendamentos> carregaAgendamentos(Calendar c) throws IOException {


        int ano, mes,dia;

        ano = c.get(c.YEAR);
        mes = c.get(c.MONTH);
        dia = c.get(c.DATE);

        configuraServico(".tbagendamento/pordata" + "/"+ano+"/"+mes+"/"+dia) ;

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        connection.setConnectTimeout(5000);

        connection.connect();

        Scanner scanner = new Scanner(url.openStream());
        StringBuilder resposta = new StringBuilder();

        while(scanner.hasNext()){
            resposta.append(scanner.next());
        }

        if (resposta.length() > 2){

            Agendamentos[] lista = new Gson().fromJson(resposta.toString(),Agendamentos[].class);

            return Arrays.asList(lista);
        }
        else{
            return  null;
        }

    }

    public List<Agendamentos> retornaListaAgendamentos(Calendar c) throws ExecutionException, InterruptedException {
        dataPesquisa = c;
        return execute(1).get();
    }


    public boolean cadastraAgendamento(Agendamentos agendamentos) throws ExecutionException, InterruptedException {
        this.agendamentos = agendamentos;
        return  ( execute(0).get() != null);
    }


}
