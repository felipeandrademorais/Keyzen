package listview.cursoandroid.com.senhaszanardo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private Button btNovo;
    private ListView lista;
    public SQLiteDatabase bancoDados ;

    private ArrayList<Integer> ids;
    private ArrayList<String> nomes;
    private ArrayList<String> usuarios;
    private ArrayList<String> senhas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            //Criação do Banco
            bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //Inicialização Variaveis
            btNovo = (Button) findViewById(R.id.novoID);
            lista = (ListView) findViewById(R.id.listView);

            //listar Dados
            recuperaDados();

            //Função Botão novo
            btNovo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, Main2Activity.class));
                }
            });

            //Função Excluir
            lista.setLongClickable(true);
            lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    removerDados(ids.get(position));
                    Toast.makeText(MainActivity.this, "Excluido com Sucesso", Toast.LENGTH_SHORT).show();
                    recuperaDados();
                    return false;
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    private void recuperaDados(){

        try{
            //Recuperar Dados
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM senhas ORDER BY id DESC",null);
            int colunaID = cursor.getColumnIndex("id");
            int colunaNome = cursor.getColumnIndex("nome");
            int colunaUsuario = cursor.getColumnIndex("usuario");
            int colunaSenha = cursor.getColumnIndex("senha");

            //Criar Adaptador
            ids = new ArrayList<Integer>();
            nomes = new ArrayList<String>();
            usuarios = new ArrayList<String>();
            senhas = new ArrayList<String>();

            CustomList adapter = new CustomList(MainActivity.this, nomes,usuarios,senhas);
            lista.setAdapter(adapter);


            //Lista os dados no Cursor
            cursor.moveToFirst();
            while(cursor != null){
                ids.add(Integer.parseInt(cursor.getString(colunaID)));
                nomes.add(cursor.getString(colunaNome));
                usuarios.add(cursor.getString(colunaUsuario));
                senhas.add(cursor.getString(colunaSenha));
                cursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void removerDados(Integer id){

        try{
            bancoDados.execSQL("DELETE FROM senhas  WHERE id=" + id);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
