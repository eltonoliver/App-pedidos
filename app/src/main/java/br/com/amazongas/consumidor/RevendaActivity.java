package br.com.amazongas.consumidor;

import java.util.ArrayList;
import java.util.List;


import br.com.amazongas.adapter.ListaRevendaAdapter;
import br.com.amazongas.mapa.MapaActivity;
import br.com.amazongas.model.Cidades;
import br.com.amazongas.model.Clientes;
import br.com.amazongas.model.RevendasBairro;
import br.com.amazongas.task.CidadeDBTask;
import br.com.amazongas.task.CidadeDBTask.CidadeResultListener;
import br.com.amazongas.task.RevendaCidadeDBTask.RevendaCidadeResultListener;
import br.com.amazongas.task.RevendaCidadeDBTask;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.GeoCoordinate;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;


public class RevendaActivity extends Activity implements CidadeResultListener, RevendaCidadeResultListener{
	private Context context = RevendaActivity.this;
	private Spinner spListaCidade;
	private ListView lvListaBairro;
	private ImageView btVoltar, btProximo, btTodos;

	private List<Cidades> listaCidades = null;
	private String codCidade;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_revenda);
		spListaCidade = (Spinner) findViewById(R.id.spRevendaCidade);
		lvListaBairro = (ListView) findViewById(R.id.lvRevendaListar);
		btVoltar = (ImageView) findViewById(R.id.btRevendaVoltar);
		btProximo = (ImageView) findViewById(R.id.btRevendaProximo);
		//btTodos = (ImageView) findViewById(R.id.btRevendaTodos);
		
		btVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		btProximo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(SettingsHelper.getUserLogado(context)){
//					Intent it = new Intent().setClass(context, RevendaDetalhesActivity.class);
					Intent it = new Intent().setClass(context, MapaActivity.class);
					Bundle bd = new Bundle();
					bd.putString("bairro", String.valueOf(Clientes.getNomebairro()));
					bd.putString("codcidade",  String.valueOf(Clientes.getCodCidade()));
					it.putExtras(bd);
					startActivity(it);
				} else {
					Util.customAlertDialog(context, "Atenção", "Favor Efetuar Login.");
				}
			}
		});
		
//		btTodos.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				
//			}
//		});
		
		
		listaCidades();
		
		
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		if(SettingsHelper.getUserLogado(context))
//			btProximo.setImageResource(R.drawable.proximoxml);
//		else
//			btProximo.setImageResource(R.drawable.proximo1);
			
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.revenda, menu);
//		return true;
//	}
//	
	
	// Metodos criados -----------------------------------------------------------------
	private void listaCidades(){
		Util.startDialog(context, "Listando cidades...", true);
		//(new RevendaCidadeTask((RevendaCidadeResultListener) context)).execute("AM");
		(new CidadeDBTask((CidadeResultListener) context)).execute("AM");
	}

	
	
	
	// Listeners -----------------------------------------------------------------------
//	@Override
//	public void RevendaCidadeResult(List<RevendasBairro> result) {
//		Util.startDialog(context, "", true);
//		ArrayList<String> lista = new ArrayList<String>();
//		for(RevendasBairro b: result){
//			lista.add(b.getBairro().toString()+" ("+b.getQtd()+")");
//		}
//		
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, android.R.id.text1,lista);
//		lvListaBairro.setAdapter(adapter);
//		
//	}

	@Override
	public void CidadeResult(ArrayList<Cidades> result) {
		Util.startDialog(context, "", false);
		listaCidades = result;
		
		if(result != null){
			ArrayList<String> lista = new ArrayList<String>();
			for(Cidades cidade : result){
				lista.add(cidade.getNome());
			}
			
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, lista);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spListaCidade.setAdapter(dataAdapter);
			
			
			if(Clientes.getCidade() != null){ 
				spListaCidade.setSelection(Util.getIndexSpinner(spListaCidade, Clientes.getCidade()));
			}	
			//spListaCidade.setOnItemSelectedListener(context);
			spListaCidade.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
					Spinner spinner = (Spinner) parent;

					if(spinner.getId() == R.id.spRevendaCidade) {
						codCidade  = listaCidades.get(position).getCodcidade();
						Log.i(Constants.TAG, "Lista de Cidades - "+codCidade);
						
						// carrega revendas por cidade
						//Util.startDialog(context, "Carregando Revendas...", true);
						(new RevendaCidadeDBTask((RevendaCidadeResultListener) context)).execute(codCidade);
						
//						GeoCoordinate ducival = new GeoCoordinate(-3.0594196,-60.031011);
//						GeoCoordinate b1 = new GeoCoordinate(-3.055, -60.0308);
//						double distancia = ducival.distanceInKm(b1);
//						Log.i(Constants.TAG, "DISTANCIA "+distancia);
						
					} 
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		
	}
	}

	@Override
	public void RevendaCidadeResult(List<RevendasBairro> result) {
		Log.i(Constants.TAG, "RevendaCidadeResults - "+result);
		final List<RevendasBairro> lista = result;
		
		Util.startDialog(context, "", false);
//		ArrayList<String> lista = new ArrayList<String>();
//		for(RevendasBairro b: result){
//			lista.add(b.getBairro().toString().trim()+" - ("+String.valueOf(b.getQtd())+")");
//		}
		
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, android.R.id.text1,lista);
//		lvListaBairro.setAdapter(adapter);
		
//		ListaSimplesAdapter adapter;
//		adapter = new ListaSimplesAdapter(context, lista, 1);
//		lvListaBairro.setAdapter(adapter);
		
		ListaRevendaAdapter adapter;
		adapter = new ListaRevendaAdapter(context, lista);
		lvListaBairro.setAdapter(adapter);
		
		lvListaBairro.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
//				Log.i(Constants.TAG, "RevendaCidadeResult - "+lvListaBairro.getItemAtPosition(position).toString());
//				Log.i(Constants.TAG, "RevendaCidadeResult - BAIRRO - "+rb.get(position).getBairro().toString());
				Log.i(Constants.TAG, "RevendaCidadeResult - "+lista.get(position).getBairro().toString());
				
				
				Log.i("teste","Revenda >>>>>>>>> b-"+lista.get(position).getBairro().toString()+" / c-"+ lista.get(position).getCodcidade().toString());
				Intent it = new Intent().setClass(context, RevendaDetalhesActivity.class);
				Bundle bd = new Bundle();
				bd.putString("bairro", lista.get(position).getBairro().toString());
				bd.putString("codcidade", lista.get(position).getCodcidade().toString());
				it.putExtras(bd);
				startActivity(it);
			}
			
		});
		
		
	}

	
}
