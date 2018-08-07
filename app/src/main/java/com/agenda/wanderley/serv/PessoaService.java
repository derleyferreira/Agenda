package com.agenda.wanderley.serv;

import android.os.AsyncTask;

import com.agenda.wanderley.agendaapp.Constantes;
import com.agenda.wanderley.agendaapp.Login;
import com.agenda.wanderley.agendaapp.Pessoas;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class PessoaService extends AsyncTask<Integer, Void, Pessoas> {

    private URL url;
    private HttpURLConnection connection;
    private Pessoas pessoa;
    private Gson gson;
    private Login login;

    private String loginEmail, loginSenha;

    @Override
    protected Pessoas doInBackground(Integer... integers) {

        if (integers[0] == 0){
            try {
                cadastrarPessoa();
                return  null;
            } catch (IOException e) {
                return  null;
            }
        }
        else if(integers[0] ==1){
            try {
                return validaLogin();
            } catch (IOException e) {
                return  null;
            }
        }
        else {
            return  null;
        }

    }

    private void configuraServico(String servico) throws IOException {

        url = new URL(Constantes.URL + servico) ;
        connection = (HttpURLConnection) url.openConnection();


    }

    private void cadastrarPessoa() throws IOException {
        if (gson == null){
            gson = new Gson();
        }

        String pessoaJson = gson.toJson(pessoa);

        configuraServico(".tbpessoa");

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

    public void cadastraPessoa(Pessoas pessoa) throws ExecutionException, InterruptedException {

        this.pessoa = pessoa;

        execute(0).get();

    }

    private Pessoas validaLogin() throws IOException {


        if (gson == null){
            gson = new Gson();
        }

        String loginJson = gson.toJson(login);

        configuraServico(".tbpessoa/Login");

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        connection.setConnectTimeout(5000);

        PrintStream printStream = new PrintStream(connection.getOutputStream());
        printStream.println(loginJson); //seta o que voce vai enviar

        connection.connect(); //envia para o servidor
        String jsonDeResposta = "";

        try {
            jsonDeResposta = new Scanner(connection.getInputStream()).next(); //pega resposta
        }catch (Exception e){
            String teste = e.getMessage();
        }

        Pessoas p = null;
        if (!jsonDeResposta.isEmpty()) {
            p = gson.fromJson(jsonDeResposta, Pessoas.class);
        }

        String abc = jsonDeResposta;

        return  p;
    }

    public boolean efetuaLogin(String pEmail, String pSenha) throws ExecutionException, InterruptedException {

        this.loginEmail  = pEmail;
        this.loginSenha = pSenha;

        login = new Login();

        login.setLogin(pEmail);
        login.setSenha(pSenha);

        Pessoas p = execute(1).get();

        if (p == null){
            return  false;
        }
        else
        {
            return  true;
        }
    }

}
