package com.agenda.wanderley.agendaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.agenda.wanderley.serv.PessoaService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class CadastroUsuario extends AppCompatActivity {

    private Button btnCadastrar;

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtTelefone;
    private EditText edtSenha;
    private EditText edtConfSenha;

    private Pessoas pessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro__usuario);

        carregaComponenets();
        atribuiEventos();
    }

    private void atribuiEventos(){
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cadastrar();
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (Exception erro){
                    erro.printStackTrace();
                }

            }
        });

    }

    private void carregaComponenets(){
        btnCadastrar = findViewById(R.id.registro_btnCadastrar);
        edtNome      = findViewById(R.id.registro_txtnome);
        edtEmail     = findViewById(R.id.registro_txtemail);
        edtTelefone  = findViewById(R.id.registro_txttelefone);
        edtSenha     = findViewById(R.id.registro_txtsenha);
        edtConfSenha = findViewById(R.id.registro_txtconfirmasenha);


    }

    private void populaObjPessoa(){
        pessoas.setPesNome(edtNome.getText().toString());
        pessoas.setPesCelular(edtTelefone.getText().toString());
        pessoas.setPesEmail(edtEmail.getText().toString());
        pessoas.setPesSenha(edtSenha.getText().toString());
    }

    private void cadastrar() throws InterruptedException, ExecutionException, IOException {

       pessoas = new Pessoas();
       populaObjPessoa();

        PessoaService serv = new PessoaService();
        serv.cadastraPessoa(pessoas);

    }


}
