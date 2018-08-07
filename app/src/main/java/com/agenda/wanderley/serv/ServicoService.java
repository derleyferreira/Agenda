package com.agenda.wanderley.serv;

import android.os.AsyncTask;

import com.agenda.wanderley.agendaapp.Constantes;
import com.agenda.wanderley.agendaapp.Servicos;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class ServicoService extends  AsyncTask<Integer, Void, List<Servicos>> {


    private URL url;
    private HttpURLConnection connection;

    @Override
    protected List<Servicos> doInBackground(Integer... integers) {

        if (integers[0] == 0){
            try {
                return carregaServicos();
            } catch (IOException e) {
                return null;
            }
        }
        else{
            return  null;
        }

    }


    private List<Servicos> carregaServicos() throws IOException {


        configuraServico(".tbservicos");

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

        if (resposta.length() > 0){

            Servicos[] servicos = new Gson().fromJson(resposta.toString(),Servicos[].class);

            return Arrays.asList(servicos);
        }
        else{
            return  null;
        }

    }

    public List<Servicos> retornaListaServicos(){
        try {
            return execute(0).get();
        } catch (InterruptedException e) {
            return  null;
        } catch (ExecutionException e) {
            return  null;
        }
    }

    private void configuraServico(String pServico) throws IOException {

        url = new URL(Constantes.URL + pServico) ;
        connection = (HttpURLConnection) url.openConnection();

    }


}
