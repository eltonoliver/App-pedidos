package br.com.amazongas.consumidor;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.amazongas.dialog.Dialogs;
import br.com.amazongas.dialog.Dialogs.CustomAlertDialogsResultListener;
import br.com.amazongas.dialog.DialogsLists;
import br.com.amazongas.dialog.DialogsLists.CustomDialogsListsResultListener;
import br.com.amazongas.listener.PostResultListener;
import br.com.amazongas.model.EnviarPedido;
import br.com.amazongas.model.LocalidadesCliente;
import br.com.amazongas.model.PrecoProdutos;
import br.com.amazongas.model.StatusRetornoPost;
import br.com.amazongas.task.LocalidadesClienteTask;
import br.com.amazongas.task.PostPedidoPJTask;
import br.com.amazongas.task.PostPedidoTask;
import br.com.amazongas.task.PrecoProdutosTask;
import br.com.amazongas.task.LocalidadesClienteTask.LocalidadesClienteResultListener;
import br.com.amazongas.task.PrecoProdutosTask.PrecoProdutosResultListener;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.CustomDialogDate;
import br.com.amazongas.util.CustomDialogDate.CustomDialogDateListener;
import br.com.amazongas.util.SettingsHelper;
import br.com.amazongas.util.Util;
import br.com.amazongas.util.Util.CustomAlertDialogReturnListener;
import br.com.amazongas.util.Util.CustomDialogEditTextResultListener;
import br.com.amazongas.util.Util.CustomDialogNumberResultListener;
import br.com.amazongas.util.Util.CustomDialogResultListener;
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

public class PedidoActivity extends Activity implements PrecoProdutosResultListener, CustomDialogNumberResultListener, PostResultListener, 
CustomAlertDialogReturnListener, CustomDialogResultListener, CustomDialogEditTextResultListener, 
CustomAlertDialogsResultListener, CustomDialogDateListener, LocalidadesClienteResultListener{
	private Context context = PedidoActivity.this;
	private TextView tvP7, tvQtdP7, tvP13, tvQtdP13, tvP20, tvQtdP20, tvP45, tvQtdP45, tvTotal, tvGranel;
	private ImageView ivP7, ivP13, ivP20, ivP45, ivGranel;
	private ImageView btVoltar, btSalvar;
	private LinearLayout llGranel;
	private NumberFormat formato = new DecimalFormat("##0.00");
	
	private int qtdP7, qtdP13, qtdP20, qtdP45;
	private double vlP7, vlP13, vlP20, vlP45, vlTotal;
	private String resultadoEditText;
	
	private int codLocalidade = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pedido);
		
		ivP7 = (ImageView) findViewById(R.id.ivPedidoP7);
		tvP7 = (TextView) findViewById(R.id.tvPedidoP7);
		tvQtdP7 = (TextView) findViewById(R.id.tvPedidoQtdP7);

		ivP13 = (ImageView) findViewById(R.id.ivPedidoP13);
		tvP13 = (TextView) findViewById(R.id.tvPedidoP13);
		tvQtdP13 = (TextView) findViewById(R.id.tvPedidoQtdP13);
		
		ivP20 = (ImageView) findViewById(R.id.ivPedidoP20);
		tvP20 = (TextView) findViewById(R.id.tvPedidoP20);
		tvQtdP20 = (TextView) findViewById(R.id.tvPedidoQtdP20);

		ivP45 = (ImageView) findViewById(R.id.ivPedidoP45);
		tvP45 = (TextView) findViewById(R.id.tvPedidoP45);
		tvQtdP45 = (TextView) findViewById(R.id.tvPedidoQtdP45);
		
		tvTotal = (TextView) findViewById(R.id.tvPedidoTotal);
		
		ivGranel = (ImageView) findViewById(R.id.ivPedidoGranel);
		tvGranel = (TextView) findViewById(R.id.tvPedidoGranel);
		
		llGranel = (LinearLayout) findViewById(R.id.llPedidoGranel);
		
		btSalvar = (ImageView) findViewById(R.id.ivPedidoSalvar);
		btVoltar = (ImageView) findViewById(R.id.ivPedidoVoltar);
		
		ivP7.setOnClickListener(ivP7Click);
		ivP13.setOnClickListener(ivP13Click);
		ivP20.setOnClickListener(ivP20Click);
		ivP45.setOnClickListener(ivP45Click);
		ivGranel.setOnClickListener(ivGranelClick);
		
		btSalvar.setOnClickListener(ivbtSalvarClick);
		btVoltar.setOnClickListener(ivbtVoltarClick);
		
		limpar();
		carregaPreco();
		
	}
	
	// Clique das imagens ----------------
	
	private OnClickListener ivP7Click = ( new OnClickListener() {
		@Override
		public void onClick(View v) {
			Util.customNumberDialog(context, "Quantidade", 0, 5, qtdP7, "7");
			
		}
	});
	
	private OnClickListener ivP13Click = ( new OnClickListener() {
		@Override
		public void onClick(View v) {
			Util.customNumberDialog(context, "Quantidade", 0, 5, qtdP13, "13");
			
		}
	});

	private OnClickListener ivP20Click = ( new OnClickListener() {
		@Override
		public void onClick(View v) {
			Util.customNumberDialog(context, "Quantidade", 0, 5, qtdP20, "20");

			
		}
	});

	private OnClickListener ivP45Click = ( new OnClickListener() {
		@Override
		public void onClick(View v) {
			Util.customNumberDialog(context, "Quantidade", 0, 5, qtdP45, "45");
	
		}
	});
	
	private OnClickListener ivGranelClick = ( new OnClickListener() {
		@Override
		public void onClick(View v) {
			//Dialogs.customAlertDialogs(context, "ATENÇÃO", "dia: "+String.valueOf(Util.diaSemana(8, 5, 2014))+"\nNome: hjhjkhjhkjhkjhkjhkj"+"\nTeste: hjkhjkhkjhjh", Constants.RETURN_SEM_RETORNO);
			//Util.dataHoraAtual(tipo)
			
			
			//CustomDialogDate dtEntrega = new CustomDialogDate(context, 8, 5, 2014);
			//dtEntrega.showDateDialog();
			Intent it = new Intent().setClass(context, PedidoGranelActivity.class);
			startActivity(it);
			
		}
	});

	private OnClickListener ivbtSalvarClick = ( new OnClickListener() {
		@Override
		public void onClick(View v) {
			//Util.customMessageDialog(context, "ATENÇÃO", Constants.MSG_CONFIRMA_DADOS, 1);
			if(verificaProdutoSelecionado()){
				if(verificaQtdProdutos()){
					if(SettingsHelper.getUserTipoUsuario(context).equals("F"))
						Util.customMessageDialogEditText(context, "Total: "+tvTotal.getText().toString().trim(), "Troco pra quanto?", 1);
					else
						//Dialogs.customDialogs(context, Constants.MSG_CONFIRMA_DADOS, Constants.RETURN_PEDIDO_CONSUMIDOR);
						consultaLocalidade();
				} else {
					Dialogs.customAlertDialogs(context, "ATENÇÂO", "Quantidade Pedida maior que 5.", Constants.RETURN_SEM_RETORNO);
				}
			} else {
				Dialogs.customAlertDialogs(context, "ATENÇÂO", "Produto não selecionado!", Constants.RETURN_SEM_RETORNO);
			}
		}	
	});
	
	private OnClickListener ivbtVoltarClick = ( new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	});
	
//----------------------------------------------------------------------------	
	
	private boolean verificaProdutoSelecionado(){
		return ((qtdP7+qtdP13+qtdP20+qtdP45) > 0);
	}
	
	private void consultaLocalidade(){
		//Util.startDialog(context, "Consultando Localidades...", true);
		//(new LocalidadesClienteTask((LocalidadesClienteResultListener) context)).execute(String.valueOf(SettingsHelper.getUserCodConsumidor(context)));
		if(verificaQtdProdutos()){
			Intent it = new Intent().setClass(context, DialogsListActivity.class);
			it.putExtra("tipochamada", String.valueOf(Constants.REQUEST_CODE_LOCALIDADES_CLIENTE));
			startActivityForResult(it, Constants.REQUEST_CODE_LOCALIDADES_CLIENTE);
		} else {
			Util.customAlertDialog(context, "Atenção", "Quantidade Pedida maior que 5.");
		}

		
		
	}
	
	private void salvaPedido(List<EnviarPedido> ep){
		Util.startDialog(context, "Salvando Pedido...", true);
		if(SettingsHelper.getUserTipoUsuario(context).equals("F"))
			(new PostPedidoTask((PostResultListener) context, ep)).execute();
		else
			(new PostPedidoPJTask((PostResultListener) context, ep)).execute();
			
		

	}
	
	private void carregaPreco(){
		Log.i("Preco","Pedido estado - "+SettingsHelper.getUserEstado(context));
		Util.startDialog(context, "Aguarde, atualizando...", true);
		(new PrecoProdutosTask((PrecoProdutosResultListener) context)).execute(SettingsHelper.getUserEstado(context));
	}

	@Override
	public void PrecoProdutosResult(List<PrecoProdutos> result) {
		Util.startDialog(context, "", false);
		if(result != null){
			for(PrecoProdutos p : result){
				switch (p.getCodProduto()) {
				case 109: tvP7.setText("R$ "+formato.format(p.getPreco())); vlP7 = p.getPreco();
					break;
				case 101: tvP13.setText("R$ "+formato.format(p.getPreco())); vlP13 = p.getPreco();
					break;
				case 104: tvP20.setText("R$ "+formato.format(p.getPreco())); vlP20 = p.getPreco();
					break;
				case 102: tvP45.setText("R$ "+formato.format(p.getPreco())); vlP45 = p.getPreco();
					break;
				}
				
				qtdP7 = 0; qtdP13 = 0; qtdP20 = 0; qtdP45 = 0;
				
				Log.i("Preco","Preco ******** - "+vlP7+" - "+vlP13+" - "+vlP20+" - "+vlP45);
				
			}
		} else {
			Util.customAlertDialogReturn(context, "Atenção", Constants.ERRO_DADOS_WEBSERVICE, 1);
		}
		
	}

	@Override
	public void customDialogNumberResult(int resultvalue, String resulttext) {
		switch (Integer.parseInt(resulttext)) {
		case 7: qtdP7 = resultvalue; tvQtdP7.setText("("+qtdP7+")"); 
			break;
		case 13: qtdP13 = resultvalue; tvQtdP13.setText("("+qtdP13+")");
			break;
		case 20: qtdP20 = resultvalue; tvQtdP20.setText("("+qtdP20+")");
			break;
		case 45: qtdP45 = resultvalue; tvQtdP45.setText("("+qtdP45+")");
			break;
		}
		
		Log.i("Preco","qtd >>>>>>>>> - "+qtdP7+" - "+qtdP13+" - "+qtdP20+" - "+qtdP45);
	
		total();
		
	}
	
	private void total(){
		vlTotal = ((vlP7*qtdP7)+(vlP13*qtdP13)+(vlP20*qtdP20)+(vlP45*qtdP45));
		tvTotal.setText("R$ "+formato.format(vlTotal));
	}
	
	private boolean verificaQtdProdutos(){
		return ((qtdP7+qtdP13+qtdP20+qtdP45) <= 5);
	}
	
	
	private void limpar(){
//		tvP7.setText("(0)"); tvP13.setText("(0)"); tvP20.setText("(0)"); tvP45.setText("(0)");
		tvP7.setText(""); tvP13.setText(""); tvP20.setText(""); tvP45.setText("");
		tvQtdP7.setText("(0)"); tvQtdP13.setText("(0)"); tvQtdP20.setText("(0)"); tvQtdP45.setText("(0)"); tvTotal.setText("0.00");
		
		if(SettingsHelper.getUserTipoUsuario(context).equals("F")){
			llGranel.setVisibility(View.GONE);
		} else {
			llGranel.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void postResult(boolean result, int tipo) {
		Util.startDialog(context, "", false);
		if(result){
			//Util.customAlertDialogReturn(context, "Atenção", "Pedido salvo com sucesso.",1);
//			Dialogs.customAlertDialogs(context, "ATENÇÃO",  "Pedido salvo com sucesso.\n\n"+StatusRetornoPost.getMsg().toString().trim(), Constants.RETURN_SAIR_TELA);
			Dialogs.customAlertDialogs(context, "ATENÇÃO",  "Pedido salvo com sucesso.", Constants.RETURN_SAIR_TELA);
		} else {
			//Util.customAlertDialog(context, "Atenção", Constants.ERRO_DADOS_CRUD);
			Dialogs.customAlertDialogs(context, "ATENÇÃO",  Constants.ERRO_DADOS_CRUD, Constants.RETURN_SEM_RETORNO);
		}
		
	}

	@Override
	public void customAlertDialogReturnResult(int result) {
		if(result == 1) finish();
	}

	@Override
	public void customDialogResult(int result) {
		if(result == 1){
			if(verificaQtdProdutos()){
				ArrayList<EnviarPedido> ep = new ArrayList<EnviarPedido>();
				if(qtdP7 > 0){
					Log.i("Preco","P7 ********** ");
					EnviarPedido e = new EnviarPedido();
					e.setCodConsumidor(SettingsHelper.getUserCodConsumidor(context));
					e.setCodProduto(109);
					e.setQtd(qtdP7);
					e.setValor(vlP7);
//					e.setObsTroco("Troco para R$"+resultadoEditText);
					e.setObsTroco(resultadoEditText);
					ep.add(e);
					Log.i("Preco","valor P7******** - "+e.getTotal());
				}
				
				if(qtdP13 > 0){
					Log.i("Preco","P13 ********** ");
					EnviarPedido e = new EnviarPedido();
					e.setCodConsumidor(SettingsHelper.getUserCodConsumidor(context));
					e.setCodProduto(101);
					e.setQtd(qtdP13);
					e.setValor(vlP13);
					e.setObsTroco(resultadoEditText);
					ep.add(e);
					Log.i("Preco","valor P13******** - "+e.getTotal());
	
				}
				
				if(qtdP20 > 0){
					Log.i("Preco","P20 ********** ");
					EnviarPedido e = new EnviarPedido();
					e.setCodConsumidor(SettingsHelper.getUserCodConsumidor(context));
					e.setCodProduto(104);
					e.setQtd(qtdP20);
					e.setValor(vlP20);
					e.setObsTroco(resultadoEditText);
					ep.add(e);
					Log.i("Preco","valor P20******** - "+e.getTotal());
	
				}
				
				if(qtdP45 > 0){
					Log.i("Preco","P45 ********** ");
					EnviarPedido e = new EnviarPedido();
					e.setCodConsumidor(SettingsHelper.getUserCodConsumidor(context));
					e.setCodProduto(102);
					e.setQtd(qtdP45);
					e.setValor(vlP45);
					e.setObsTroco(resultadoEditText);
					ep.add(e);
					Log.i("Preco","valor P45******** - "+e.getTotal());
	
				}
				
				salvaPedido(ep);	
				
			} else {
				Util.customAlertDialog(context, "Atenção", "Quantidade Pedida maior que 5.");
			}
		}
	}

	@Override
	public void customDialogEditTextResult(int result, String resulttext) {
		if(result == 1){
			resultadoEditText = resulttext;
			//Util.customMessageDialog(context, "ATENÇÃO", Constants.MSG_CONFIRMA_DADOS, 1);
			Dialogs.customDialogs(context, Constants.MSG_CONFIRMA_DADOS, Constants.RETURN_PEDIDO_CONSUMIDOR);
		}
		
	}

	@Override
	public void CustomAlertDialogsResult(int result, int retorno) {
		if(retorno != Constants.RETURN_SEM_RETORNO){
			
			if(retorno == Constants.RETURN_SAIR_TELA) finish();
			
			if(result == Constants.RESULT_CONFIRMAR){
				if(retorno == Constants.RETURN_PEDIDO_CONSUMIDOR){
					salvaPedidoConfirmado();
					
				}
			}
			
		}
		
	}
	
	
	// Salva Pedido Confirmado
	private void salvaPedidoConfirmado(){
		if(verificaQtdProdutos()){
			ArrayList<EnviarPedido> ep = new ArrayList<EnviarPedido>();
			if(qtdP7 > 0){
				Log.i("Preco","P7 ********** ");
				EnviarPedido e = new EnviarPedido();
				e.setCodConsumidor(SettingsHelper.getUserCodConsumidor(context));
				e.setCodLocalidade(codLocalidade);
				e.setCodProduto(109);
				e.setQtd(qtdP7);
				e.setValor(vlP7);
//				e.setObsTroco("Troco para R$"+resultadoEditText);
				e.setObsTroco(resultadoEditText);
				ep.add(e);
				Log.i("Preco","valor P7******** - "+e.getTotal());
			}
			
			if(qtdP13 > 0){
				Log.i("Preco","P13 ********** ");
				EnviarPedido e = new EnviarPedido();
				e.setCodConsumidor(SettingsHelper.getUserCodConsumidor(context));
				e.setCodLocalidade(codLocalidade);
				e.setCodProduto(101);
				e.setQtd(qtdP13);
				e.setValor(vlP13);
				e.setObsTroco(resultadoEditText);
				ep.add(e);
				Log.i("Preco","valor P13******** - "+e.getTotal());

			}
			
			if(qtdP20 > 0){
				Log.i("Preco","P20 ********** ");
				EnviarPedido e = new EnviarPedido();
				e.setCodConsumidor(SettingsHelper.getUserCodConsumidor(context));
				e.setCodLocalidade(codLocalidade);
				e.setCodProduto(104);
				e.setQtd(qtdP20);
				e.setValor(vlP20);
				e.setObsTroco(resultadoEditText);
				ep.add(e);
				Log.i("Preco","valor P20******** - "+e.getTotal());

			}
			
			if(qtdP45 > 0){
				Log.i("Preco","P45 ********** ");
				EnviarPedido e = new EnviarPedido();
				e.setCodConsumidor(SettingsHelper.getUserCodConsumidor(context));
				e.setCodLocalidade(codLocalidade);
				e.setCodProduto(102);
				e.setQtd(qtdP45);
				e.setValor(vlP45);
				e.setObsTroco(resultadoEditText);
				ep.add(e);
				Log.i("Preco","valor P45******** - "+e.getTotal());

			}
			
			salvaPedido(ep);	
			
		} else {
			//Util.customAlertDialog(context, "Atenção", "Quantidade Pedida maior que 5.");
			Dialogs.customAlertDialogs(context, "ATENÇÃO", "Quantidade Pedida maior que 5.", Constants.RETURN_SEM_RETORNO);
		}

	}

	@Override
	public void onCustomDialogDateResultListener(String result) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void LocalidadesClienteResult(List<LocalidadesCliente> result) {
		Util.startDialog(context, "", false);
		//Log.i("TESTE","LocalidadesClienteResult TOTAL>>>>> 1 - "+String.valueOf(result.size()));
		
		if(result != null){
			Log.i("TESTE","LocalidadesClienteResult >>>>> 2 - "+result);
			//DialogsLists.listaLocalidadesCliente(context, result);
			Intent it = new Intent().setClass(context, DialogsListActivity.class);
			it.putExtra("tipochamada", String.valueOf(Constants.REQUEST_CODE_LOCALIDADES_CLIENTE));
			startActivityForResult(it, 5); //Constants.REQUEST_CODE_LOCALIDADES_CLIENTE);
	//		startActivity(it);
			
			//Intent it = new Intent().setClass(context, DialogsListActivity.class);
			//startActivity(it);

		} else {
			Dialogs.customAlertDialogs(context, "ATENÇÃO", "Erro ao acessar localidades.", Constants.RETURN_SEM_RETORNO);
			
		}

		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		Log.i("TESTE","onActivityResult >>>>> requestcode - "+requestCode+ " / resultcode - "+resultCode+ " / data - "+data.getStringExtra("retorno"));
		if(requestCode == Constants.REQUEST_CODE_LOCALIDADES_CLIENTE){
			codLocalidade = Integer.parseInt(data.getStringExtra("retorno"));
			if(codLocalidade >= 0)
				Dialogs.customDialogs(context, Constants.MSG_CONFIRMA_DADOS, Constants.RETURN_PEDIDO_CONSUMIDOR);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
