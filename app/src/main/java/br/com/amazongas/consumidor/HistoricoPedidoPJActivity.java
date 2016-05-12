package br.com.amazongas.consumidor;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.amazongas.adapter.ListaHistoricoPedidoAdapter;
import br.com.amazongas.model.HistoricoPedido;
import br.com.amazongas.model.HistoricoPedidoItens;
import br.com.amazongas.task.HistoricoPedidoItemTask;
import br.com.amazongas.task.HistoricoPedidoItemTask.HistoricoPedidoItemResultListener;
import br.com.amazongas.task.HistoricoPedidoTask;
import br.com.amazongas.task.PrecoProdutosTask;
import br.com.amazongas.task.HistoricoPedidoTask.HistoricoPedidoResultListener;
import br.com.amazongas.task.PrecoProdutosTask.PrecoProdutosResultListener;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.CustomDialogDate;
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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class HistoricoPedidoPJActivity extends Activity implements HistoricoPedidoResultListener {
	
	private Context context = HistoricoPedidoPJActivity.this;
	private Spinner spLista;
	private ImageView ivAtualizar, ivVoltar;
	private ListView lvHistorico;
	private String[] aLista = {"Hoje","Últimos 30 dias","Últimos 60 dias", "Últimos 90 dias"};
	private int opcao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historico_pedidopj);
		
		spLista = (Spinner) findViewById(R.id.spHistoricoTipo);
		ivAtualizar = (ImageView) findViewById(R.id.ivHistoricoAtualizar);
		lvHistorico = (ListView) findViewById(R.id.lvHistoricoPedido);
		ivVoltar = (ImageView) findViewById(R.id.btHistoricoPedidoVoltar);
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, aLista);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spLista.setAdapter(null);
		spLista.setAdapter(dataAdapter);
		
		ivAtualizar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listaHistorico();
			}
		});
		
		ivVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
		spLista.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int posicao, long id) {
				opcao = posicao+1;
				listaHistorico();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
	}

		
	private void iniciaLogin(){
		int dia, mes, ano;
		dia = Integer.parseInt(Util.dataHoraAtual("D"));
		mes = Integer.parseInt(Util.dataHoraAtual("M"));
		ano = Integer.parseInt(Util.dataHoraAtual("A"));
		
		//CustomDialogDate data = new CustomDialogDate(context, dia, mes, ano);
		//data.showDateDialog();
	}
	
	
	private void listaHistorico(){
		Util.startDialog(context, "Aguarde, atualizando...", true);
		//Util.startCustomDialog(context, "Aguarde, atualizando...", true);
		(new HistoricoPedidoTask((HistoricoPedidoResultListener) context)).execute(String.valueOf(SettingsHelper.getUserCodConsumidor(context)), String.valueOf(opcao));
	}


	@Override
	public void HistoricoPedidoResult(List<HistoricoPedido> result) {
		final List<HistoricoPedido> lista = result;
		lvHistorico.setAdapter(null);
		
		Util.startDialog(context, "", false);
		if(result != null){
			ListaHistoricoPedidoAdapter adapter = new ListaHistoricoPedidoAdapter(context, result);
			lvHistorico.setAdapter(adapter);
			
			lvHistorico.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
					
					Intent it = new Intent().setClass(context, HistoricoPedidoItemActivity.class);
					Bundle bd = new Bundle();
					bd.putInt("pedido", lista.get(position).getCodigo());
					bd.putDouble("total", lista.get(position).getTotal());
					bd.putString("troco", lista.get(position).getObsTroco());
					Log.i("Teste","TROCO >>>>>>>>>>>>>>> "+lista.get(position).getObsTroco());
					it.putExtras(bd);
					startActivity(it);

				}
				
			});
		}	
		
	}




}
