package listview.cursoandroid.com.senhaszanardo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main2Activity extends MainActivity {

    private EditText equipamento;
    private EditText usuario;
    private EditText senha;
    private Button btSalvar;
    private Button btVoltar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        try {
            //Criando tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS senhas (id INTEGER PRIMARY KEY AUTOINCREMENT,nome VARCHAR, usuario VARCHAR, senha VARCHAR)");

            //Inicializa variaveis
            equipamento = (EditText) findViewById(R.id.equipID);
            usuario = (EditText) findViewById(R.id.userID);
            senha = (EditText) findViewById(R.id.senhaID);

            //Inicializa Botões
            btSalvar = (Button) findViewById(R.id.salvarID);
            btVoltar = (Button) findViewById(R.id.voltarID);

            //Função Botão Salvar
            btSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String equi = equipamento.getText().toString();
                    String usu = usuario.getText().toString();
                    String sen = senha.getText().toString();

                    bancoDados.execSQL("INSERT INTO senhas(nome,usuario,senha) VALUES ('"+equi+"','"+usu+"','"+sen+"')");
                    btSalvar.setEnabled(false);
                    Toast.makeText(Main2Activity.this, "Salvo com Sucesso", Toast.LENGTH_SHORT).show();
                }
            });

            //Função Botão Voltar
            btVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Main2Activity.this, MainActivity.class));
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
