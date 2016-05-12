package br.com.amazongas.consumidor;

import java.util.ArrayList;
import java.util.List;

import br.com.amazongas.dialog.Dialogs;
import br.com.amazongas.dialog.Dialogs.CustomAlertDialogsResultListener;
import br.com.amazongas.listener.PostResultListener;
import br.com.amazongas.model.EnviarPedidoGranel;
import br.com.amazongas.model.StatusRetornoPost;
import br.com.amazongas.task.PostPedidoGranelTask;
import br.com.amazongas.task.PostPedidoTask;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.CustomDialogDate;
import br.com.amazongas.util.Mask;
import br.com.amazongas.util.Statics;
import br.com.amazongas.util.CustomDialogDate.CustomDialogDateListener;
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
import android.widget.ImageView;
import android.widget.TextView;

public class PedidoGranelActivity extends Activity implements CustomDialogDateListener, CustomAlertDialogsResultListener, PostResultListener{
	private Context context = PedidoGranelActivity.this;
	private ImageView btConsulta, btConfirma, btCancela;
	private TextView tvData, tvDiaSemana;
	private String sDataAtual;
	
	private int codLocalidade = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pedido_granel);
		setTheme(android.R.style.Theme_Translucent_NoTitleBar);
		
		btConsulta = (ImageView) findViewById(R.id.ivPedidoGranelConsultaData);
		btConfirma = (ImageView) findViewById(R.id.btPedidoGranelConfirmar);
		btCancela = (ImageView) findViewById(R.id.btPedidoGranelCancelar);
		tvData = (TextView) findViewById(R.id.tvPedidoGranelData);
		tvDiaSemana = (TextView) findViewById(R.id.tvPedidoGranelDiaSemana);
		
		sDataAtual = Util.aumentaData(Util.dataHoraAtual("DMA"), 1);
		
		tvData.setText(verificaDataGranel(sDataAtual));
		tvDiaSemana.setText(Util.mostraDiaSemana(verificaDataGranel(sDataAtual)));

		
		btConsulta.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String sdata = tvData.getText().toString().trim();
				int ndia = Integer.parseInt( Util.parteDataHora("D", sdata));
				int nmes = Integer.parseInt( Util.parteDataHora("M", sdata));
				int nano = Integer.parseInt( Util.parteDataHora("A", sdata));
				
				CustomDialogDate dtEntrega = new CustomDialogDate(context, ndia, nmes, nano);
				dtEntrega.showDateDialog();
			}
		});
		
		btConfirma.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Dialogs.customDialogs(context, "Confirma o Agendamento?", Constants.RETURN_AGENDAMENTO_GRANEL);
				consultaLocalidade();
			}
		});
		
		btCancela.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	//----------------------------------------------------------------------------------------
	
	private void consultaLocalidade(){
		Intent it = new Intent().setClass(context, DialogsListActivity.class);
		it.putExtra("tipochamada", String.valueOf(Constants.REQUEST_CODE_LOCALIDADES_CLIENTE));
		startActivityForResult(it, Constants.REQUEST_CODE_LOCALIDADES_CLIENTE);
		
	}	
	
	//----------------------------------------------------------------------------------------
	@Override
	public void onCustomDialogDateResultListener(String result) {
//		int ndia = Integer.parseInt( Util.parteDataHora("D", sdata))+1;
//		int nmes = Integer.parseInt( Util.parteDataHora("M", sdata));
//		int nano = Integer.parseInt( Util.parteDataHora("A", sdata));
		
		//Util.comparaData("10/05/2014",result);
		String dt = verificaDataGranel(result);
		Log.i("teste","dia da semana retornado: "+Util.diaSemana(result));
		Log.i("teste","dia da semana verificado: "+Util.diaSemana(dt));
		
//		if(Util.comparaData(sDataAtual,result).equals(">")){
//			Dialogs.customAlertDialogs(context, "ATENÇÃO", "Data do Abastecimento inválida.", Constants.RETURN_SEM_RETORNO);
//			dt = sDataAtual;
//		} else	
//			if(Util.diaSemana(result) == 6 )
//				dt = Util.aumentaData(result, 3);
//			else
//				if(Util.diaSemana(result) == 7 )
//					dt = Util.aumentaData(result, 2);
//				else 
//					if(Util.diaSemana(result) == 1)
//						dt = Util.aumentaData(result, 1);
//					else	
//						dt = result;
		//dt = verificaDataGranel(result);
		
		tvData.setText(dt);	
		tvDiaSemana.setText(Util.mostraDiaSemana(dt));
		
//		tvData.setText(Util.dataInvertida(result, 1));
//		tvDiaSemana.setText(Util.dataInvertida(result, 2));	

		
	}

	@Override
	public void CustomAlertDialogsResult(int result, int retorno) {
		if(retorno != Constants.RETURN_SEM_RETORNO){
			if(result == Constants.RESULT_CONFIRMAR){ 
				// Agendamento Granel
				if(retorno == Constants.RETURN_AGENDAMENTO_GRANEL){
					List<EnviarPedidoGranel> listaep = new ArrayList<EnviarPedidoGranel>();
					
					EnviarPedidoGranel ep = new EnviarPedidoGranel();
					ep.setCodPedido(codLocalidade);
					ep.setCodCliente(SettingsHelper.getUserCodConsumidor(context));
					ep.setCodLocalidade(0);
					ep.setDataPedido(Util.aumentaData(Util.dataHoraAtual("DMA"), 1));
					//String dt = Util.dataInvertida(tvData.getText().toString().trim(),1);
				//	Log.i("teste","data >>>>>>>>>>> "+dt);
					ep.setDataProgramacao(tvData.getText().toString().trim());
					
					listaep.add(ep);
					
					Util.startDialog(context, "Salvando Agendamento...", true);
					(new PostPedidoGranelTask((PostResultListener) context, listaep)).execute();
					
				}
			}
			
		} 

	}
	
	private String verificaDataGranel(String result){
		String dt = sDataAtual;
		if(Util.comparaData(sDataAtual,result).equals(">")){
			Dialogs.customAlertDialogs(context, "ATENÇÃO", "Data do Abastecimento inválida.", Constants.RETURN_SEM_RETORNO);
			dt = sDataAtual;
		} else	
			if(Util.diaSemana(result) == 6 )
				dt = Util.aumentaData(result, 3);
			else
				if(Util.diaSemana(result) == 7 )
					dt = Util.aumentaData(result, 2);
				else 
					if(Util.diaSemana(result) == 1)
						dt = Util.aumentaData(result, 1);
					else	
						dt = result;
		
		return dt;
		
	}

	@Override
	public void postResult(boolean result, int tipo) {
		Util.startDialog(context, "", false);
		if(result){
			Dialogs.customAlertDialogs(context, "ATENÇÃO",  "Agendamento salvo com sucesso.\n\n"+StatusRetornoPost.getMsg().toString().trim(), Constants.RETURN_SAIR_TELA);
		} else {
			Dialogs.customAlertDialogs(context, "ATENÇÃO",  Statics.mensagemErro, Constants.RETURN_SEM_RETORNO);
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		Log.i("TESTE","onActivityResult >>>>> requestcode - "+requestCode+ " / resultcode - "+resultCode+ " / data - "+data.getStringExtra("retorno"));
		if(requestCode == Constants.REQUEST_CODE_LOCALIDADES_CLIENTE){
			codLocalidade = Integer.parseInt(data.getStringExtra("retorno"));
			if(codLocalidade >= 0)
				Dialogs.customDialogs(context, "Confirma o Agendamento?", Constants.RETURN_AGENDAMENTO_GRANEL);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	

}
