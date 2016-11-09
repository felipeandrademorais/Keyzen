package listview.cursoandroid.com.senhaszanardo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Felipe on 09/11/2016.
 */
public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> equipamento;
    private final ArrayList<String> nome;
    private final ArrayList<String> senha;

    public CustomList(Activity context,ArrayList<String> equipamento, ArrayList<String> nome, ArrayList<String> senha){
        super(context, R.layout.list_item_last_post,nome);
        this.context = context;
        this.equipamento = equipamento;
        this.nome = nome;
        this.senha = senha;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item_last_post,null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.textViewTitle);
        TextView txtSubTitle = (TextView) rowView.findViewById(R.id.textViewDescription);
        TextView txtDescription = (TextView) rowView.findViewById(R.id.textViewDescription2);

        txtTitle.setText(equipamento.get(position));
        txtSubTitle.setText(senha.get(position));
        txtDescription.setText(nome.get(position));

        return rowView;
    }

}
