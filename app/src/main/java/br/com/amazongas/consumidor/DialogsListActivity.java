package br.com.amazongas.consumidor;

import java.util.List;

import br.com.amazongas.adapter.LocalidadesClienteAdapter;
import br.com.amazongas.consumidor.R;
import br.com.amazongas.consumidor.R.layout;
import br.com.amazongas.consumidor.R.menu;
import br.com.amazongas.model.LocalidadesCliente;
import br.com.amazongas.task.LocalidadesClienteTask;
import br.com.amazongas.task.RevendaCidadeDBTask;
import br.com.amazongas.task.LocalidadesClienteTask.LocalidadesClienteResultListener;
import br.com.amazongas.task.RevendaCidadeDBTask.RevendaCidadeResultListener;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.SettingsHelper;
import br.com.amazongas.util.Util;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class DialogsListActivity extends Activity implements LocalidadesClienteResultListener {
	private Context context = DialogsListActivity.this;
	
	private TextView tvTitulo;
	private ListView lvLista;
	private ImageView btVoltar;
	
	
	private String request_code = null;
	private int codLocalidade;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialogs_list);
		
		tvTitulo = (TextView) findViewById(R.id.tvDialogsListTitulo);
		lvLista = (ListView) findViewById(R.id.lvDialogsListLista);
		btVoltar = (ImageView) findViewById(R.id.btDialogsListVoltar);
		
		btVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				codLocalidade = -1;
				finish();
			}
		});
		
		Log.i("TESTE","DialogsListActivity >>>>> Inicio");
		
		Intent it = getIntent();
		request_code =it.getStringExtra("tipochamada");
		Log.i("TESTE","DialogsListActivity >>>>> 1 - "+request_code);
		
		switch (Integer.parseInt(request_code)) {
		case Constants.REQUEST_CODE_LOCALIDADES_CLIENTE:
			Log.i("TESTE","DialogsListActivity >>>>> 2 - "+request_code);
			consultaLocalidade();
			break;

		default:
			break;
		}
	
		
	}
	
	@Override
	public void finish() {
		switch (Integer.parseInt(request_code)) {
		case Constants.REQUEST_CODE_LOCALIDADES_CLIENTE:
			Log.i("TESTE","DialogsListActivity >>>>>Finish "+codLocalidade);
			Intent data = new Intent();
			data.putExtra("retorno", String.valueOf(codLocalidade));
			setResult(RESULT_OK, data);
			break;

		default:
			setResult(RESULT_CANCELED, null);
			break;
		}
		
		super.finish();
	}

	// Métodos Criados ------------------------------------------------------------------------
	private void consultaLocalidade(){
		Log.i("TESTE","DialogsListActivity >>>>> 3 - "+request_code);
		Util.startDialog(context, "Consultando Localidades...", true);
		(new LocalidadesClienteTask((LocalidadesClienteResultListener) context)).execute(String.valueOf(SettingsHelper.getUserCodConsumidor(context)));
		
		
	}


	
	// Retorno de Listeners ---------------------------------------------------------------
	@Override
	public void LocalidadesClienteResult(List<LocalidadesCliente> result) {
		Util.startDialog(context, "", false);
		final List<LocalidadesCliente> lista = result;
		Log.i("TESTE","DialogsListActivity >>>>>LocalidadesClienteResult "+request_code);
		Log.i("TESTE","DialogsListActivity >>>>QTD LOCALIDADES >>>>> "+lista.size());
		if(result != null){
			 if(lista.size() > 1){
				 LocalidadesClienteAdapter adapter = new LocalidadesClienteAdapter(context, lista);
				 lvLista.setAdapter(adapter);
				 
				 lvLista.setOnItemClickListener(new OnItemClickListener() {
	
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
							Log.i("TESTE","DialogsListActivity >>>>>onItemClick "+lista.get(position).getCodigo());
							codLocalidade  = lista.get(position).getCodigo();
							finish();
						}
						
					});
			 
			 } else {
				codLocalidade  = lista.get(0).getCodigo();
				finish();
			 }
		}
	}


}
