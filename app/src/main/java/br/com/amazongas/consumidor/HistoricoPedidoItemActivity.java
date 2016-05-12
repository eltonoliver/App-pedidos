package br.com.amazongas.consumidor;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.amazongas.adapter.ListaHistoricoPedidoAdapter;
import br.com.amazongas.adapter.ListaHistoricoPedidoItemAdapter;
import br.com.amazongas.adapter.ListaHistoricoPedidoItemPJAdapter;
import br.com.amazongas.model.HistoricoPedido;
import br.com.amazongas.model.HistoricoPedidoItens;
import br.com.amazongas.task.HistoricoPedidoItemPJTask;
import br.com.amazongas.task.HistoricoPedidoItemPJTask.HistoricoPedidoItemPJResultListener;
import br.com.amazongas.task.HistoricoPedidoItemTask;
import br.com.amazongas.task.HistoricoPedidoItemTask.HistoricoPedidoItemResultListener;
import br.com.amazongas.task.HistoricoPedidoTask;
import br.com.amazongas.task.PrecoProdutosTask;
import br.com.amazongas.task.HistoricoPedidoTask.HistoricoPedidoResultListener;
import br.com.amazongas.task.PrecoProdutosTask.PrecoProdutosResultListener;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.CustomDialogDate;
import br.com.amazongas.util.SettingsHelper;
import br.com.amazongas.util.Statics;
import br.com.amazongas.util.Util;
import br.com.amazongas.util.Util.CustomAlertDialogReturnListener;
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
import android.widget.TextView;
import android.widget.Toast;

public class HistoricoPedidoItemActivity extends Activity implements HistoricoPedidoItemResultListener, 
CustomAlertDialogReturnListener, HistoricoPedidoItemPJResultListener{
	
	private Context context = HistoricoPedidoItemActivity.this;
	private ListView lvHistoricoItem;
	private ImageView ivVoltar;
	private TextView tvTotal, tvTroco;
	private NumberFormat formato = new DecimalFormat("##0.00");
	private int consulta = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle bd = getIntent().getExtras();
		consulta = bd.getInt("consulta");

		if(SettingsHelper.getUserTipoUsuario(context).equals("F")){
			setContentView(R.layout.activity_historico_pedido_item);
			tvTotal = (TextView) findViewById(R.id.tvHistoricoPedidoItemTotal);
			tvTroco = (TextView) findViewById(R.id.tvHistoricoPedidoItemTroco);
			
			tvTotal.setText(formato.format(bd.getDouble("total")));
			tvTroco.setText(formato.format(Double.parseDouble(bd.getString("troco").trim())));
			
		} else {
			setContentView(R.layout.activity_historico_pedido_item_pj);
		}	
			
		
		lvHistoricoItem = (ListView) findViewById(R.id.lvHistoricoPedidoItem);
		ivVoltar = (ImageView) findViewById(R.id.btHistoricoPedidoItemVoltar);
		
		ivVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		listaItens(bd.getInt("pedido"));
		
	}
	
	private void listaItens(int pedido){
		Util.startDialog(context, "Aguarde, atualizando...", true);
		if(SettingsHelper.getUserTipoUsuario(context).equals("F"))
			(new HistoricoPedidoItemTask((HistoricoPedidoItemResultListener) context)).execute(String.valueOf(pedido));
		else
			(new HistoricoPedidoItemPJTask((HistoricoPedidoItemPJResultListener) context)).execute(String.valueOf(pedido));
				

	}

	@Override
	public void HistoricoPedidoItemResult(List<HistoricoPedidoItens> result) {
		Util.startDialog(context, "", false);
		if(result != null){
			ListaHistoricoPedidoItemAdapter adapter = new ListaHistoricoPedidoItemAdapter(context, result);
			lvHistoricoItem.setAdapter(adapter);
		} else {
			Util.customAlertDialogReturn(context, "Atenção", Constants.ERRO_DADOS_WEBSERVICE, 1);
		}
		
	}

	@Override
	public void customAlertDialogReturnResult(int result) {
		if(result == 1) finish();
		
	}

	@Override
	public void HistoricoPedidoItemPJResult(List<HistoricoPedidoItens> result) {
		Util.startDialog(context, "", false);
		if(result != null){
			ListaHistoricoPedidoItemPJAdapter adapter = new ListaHistoricoPedidoItemPJAdapter(context, result);
			lvHistoricoItem.setAdapter(adapter);
		} else {
			Util.customAlertDialogReturn(context, "Atenção", Constants.ERRO_DADOS_WEBSERVICE, 1);
		}
		
	}



}
