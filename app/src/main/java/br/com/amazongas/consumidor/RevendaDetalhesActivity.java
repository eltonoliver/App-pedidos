package br.com.amazongas.consumidor;

import java.util.ArrayList;
import java.util.List;

import br.com.amazongas.adapter.ListaRevendaDetalheAdapter;
import br.com.amazongas.model.Revendas;
import br.com.amazongas.task.RevendaBairroDBTask;
import br.com.amazongas.task.RevendaBairroDBTask.RevendaBairroResultListener;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Util;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class RevendaDetalhesActivity extends Activity implements RevendaBairroResultListener{
	private Context context = RevendaDetalhesActivity.this;
	private TextView tvBairro;
	private ListView lvRevenda;
	private ImageView btVoltar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_revenda_detalhes);
		
		tvBairro = (TextView) findViewById(R.id.tvRevendaDetalhesBairro);
		lvRevenda = (ListView) findViewById(R.id.lvRevendaDetalhesLista);
		btVoltar = (ImageView) findViewById(R.id.btRevendaDetalheVoltar);
		
		Bundle bd = getIntent().getExtras();
		
		Log.i(Constants.TAG, "GetExtras - "+bd.getString("bairro"));
		Log.i(Constants.TAG, "GetExtras - "+bd.getString("codcidade"));
		
		Log.i("teste","Revenda Detalhes >>>>>>>>> b- "+bd.getString("bairro")+" / c- "+ bd.getString("codcidade"));
		
		tvBairro.setText(bd.getString("bairro").toString());
		
		btVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		// carrega revendas por bairro
		
		Util.startDialog(context, "Carregando Revendas...", true);
		(new RevendaBairroDBTask((RevendaBairroResultListener) context)).execute(bd.getString("bairro"), bd.getString("codcidade"));
		
		
		
	}

	@Override
	public void RevendaBairroResult(List<Revendas> result) {
		Util.startDialog(context, "", false);
		Log.i(Constants.TAG, "RevendaBairroResult - "+result);
		List<Revendas> lista = result;
		
//		List<String> lista = new ArrayList<String>();
//		for(Revendas rv : result){
//			Log.i(Constants.TAG, "Localidade - "+rv.getNomelocalidade().toString());
//			lista.add(rv.getNomelocalidade()+" - "+rv.getTelefone1());
//		}
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, android.R.id.text1,lista);
//		lvRevenda.setAdapter(adapter);
		
		ListaRevendaDetalheAdapter adapter = new ListaRevendaDetalheAdapter(context, lista);
		lvRevenda.setAdapter(adapter);
		
	}

}
