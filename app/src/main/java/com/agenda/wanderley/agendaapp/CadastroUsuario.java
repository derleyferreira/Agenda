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

                    if (!validaCadastro())
                        return;

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

    private boolean validaCadastro(){

        if (edtNome.getText().toString().isEmpty()){
            edtNome.setError(getString(R.string.infome_nome));
            return false;
        }

        if (edtNome.length() < 4){
            edtNome.setError(getString(R.string.nome_curto));
            return false;
        }

        if (edtTelefone.getText().toString().isEmpty()){
            edtTelefone.setError(getString(R.string.informe_telefone));
            return false;
        }

        if (edtTelefone.getText().length() < 10){
            edtTelefone.setError(getString(R.string.telefone_invalido));
            return false;
        }

        if (edtSenha.getText().toString().isEmpty()){
            edtSenha.setError(getString(R.string.error_invalid_password));
            return false;
        }

        if (edtSenha.getText().length() < 4){
           edtSenha.setError(getString(R.string.senha_muito_curta));
           return false;
        }

        if (edtSenha.getText().toString() != edtConfSenha.getText().toString()){
            edtConfSenha.setError(getString(R.string.senhas_nao_conferem));
            return false;
        }
        
        return true;

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
