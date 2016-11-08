package listview.cursoandroid.com.senhaszanardo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private Button btNovo;
    private ListView lista;
    public SQLiteDatabase bancoDados ;

    private ArrayAdapter<String> itensAdaptador;
    private ArrayList<Integer> ids;
    private ArrayList<String> nomes;
    private ArrayList<String> usuarios;
    private ArrayList<String> senhas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bancoDados = openOrCreateDatabase("app",MODE_PRIVATE,null);

        btNovo = (Button) findViewById(R.id.novoID);
        lista = (ListView) findViewById(R.id.listView);

        //lista Dados
        recuperaDados();

        btNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
    }

    private void recuperaDados(){

        try{

            //Recuperar Dados
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM senhas",null);
            int colunaID = cursor.getColumnIndex("id");
            int colunaNome = cursor.getColumnIndex("nome");
            int colunaUsuario = cursor.getColumnIndex("usuario");
            int colunaSenha = cursor.getColumnIndex("senha");

            //Criar Adaptador
            ids = new ArrayList<Integer>();
            nomes = new ArrayList<String>();
            usuarios = new ArrayList<String>();
            senhas = new ArrayList<String>();

            itensAdaptador = new ArrayAdapter<String>(
                    getApplicationContext(),
                    R.layout.list_item_last_post,
                    R.id.list_item_post_title_textview,
                    nomes
            );

            lista.setAdapter(itensAdaptador);

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

}
