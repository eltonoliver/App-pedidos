package br.com.amazongas.consumidor;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.crypto.spec.IvParameterSpec;

import br.com.amazongas.dialog.Dialogs;
import br.com.amazongas.dialog.Dialogs.CustomAlertDialogsResultListener;
import br.com.amazongas.model.PrecoProdutos;
import br.com.amazongas.task.PrecoProdutosTask;
import br.com.amazongas.task.PrecoProdutosTask.PrecoProdutosResultListener;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.SettingsHelper;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProdutoActivity extends Activity implements PrecoProdutosResultListener, CustomAlertDialogsResultListener{
	private Context context = this;
	private TextView tvP7, tvP13, tvP20, tvP45; 
	private ImageView btVoltar;
	private NumberFormat formato = new DecimalFormat("##0.00");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_produto);
		
		tvP7 = (TextView) findViewById(R.id.tvProdutoP7);
		tvP13 = (TextView) findViewById(R.id.tvProdutoP13);
		tvP20 = (TextView) findViewById(R.id.tvProdutoP20);
		tvP45 = (TextView) findViewById(R.id.tvProdutoP45);
		
		btVoltar = (ImageView) findViewById(R.id.ivProdutoVoltar);
		btVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		
		// Verifica se usuario está logado
		if(SettingsHelper.getUserLogado(context) != true){
			Log.i("MAPA iniciado", "tt "+Activity.RESULT_OK);
			 Intent it = new Intent(context, MapaCidadesActivity.class);
		     startActivityForResult(it, 1);
		} else{
			listaPreco();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("MAPA", "onActivityResult 1 - "+requestCode);
		if(resultCode == 1){
			SettingsHelper.setUserCodCidade(context, data.getStringExtra("codcidade"));
			SettingsHelper.setUserEstado(context, data.getStringExtra("uf"));
			Log.i("MAPA selecionado",SettingsHelper.getUserCodCidade(context));
			Log.i("MAPA selecionado 2",SettingsHelper.getUserEstado(context));
			listaPreco();
		} else{
			finish();
		}
	}
	
	private void listaPreco(){
		Log.i("Preco","Preco estado - "+SettingsHelper.getUserEstado(context));
		Util.startDialog(context, "Aguarde, atualizando...", true);
		(new PrecoProdutosTask((PrecoProdutosResultListener) context)).execute(SettingsHelper.getUserEstado(context));
		
	}

	@Override
	public void PrecoProdutosResult(List<PrecoProdutos> result) {
		Util.startDialog(context, "", false);
		if(result != null){
			for(PrecoProdutos p : result){
				switch (p.getCodProduto()) {
				case 109: tvP7.setText("R$ "+formato.format(p.getPreco()));
					break;
				case 101: tvP13.setText("R$ "+formato.format(p.getPreco()));
					break;
				case 104: tvP20.setText("R$ "+formato.format(p.getPreco()));
					break;
				case 102: tvP45.setText("R$ "+formato.format(p.getPreco()));
					break;
				}
			}
		} else {
			//Util.customAlertDialogReturn(context, "Atenção", Constants.ERRO_DADOS_WEBSERVICE, 1);
			Dialogs.customAlertDialogs(context, "ATENÇÃO",  Constants.ERRO_DADOS_WEBSERVICE, 1);
		}
		
		
	}

	
	@Override
	public void CustomAlertDialogsResult(int result, int retorno) {
		if(result == Constants.RESULT_CONFIRMAR){
			finish();
		}
		
	}


}
