package br.com.amazongas.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import br.com.amazongas.consumidor.R;


import com.google.android.gcm.GCMRegistrar;


public class Util {

	// Listeners-------------------------------------------------------------------------------------
	public interface CustomDialogResultListener{
		public void customDialogResult(int result);
		
	}
	
	public interface CustomDialogNumberResultListener{
		public void customDialogNumberResult(int resultvalue, String resulttext);
	}
	
	public interface CustomAlertDialogReturnListener{
		public void customAlertDialogReturnResult(int result);
		
	}
	
	public interface CustomDialogButtonResultListener{
		public void customDialogButtonResult(int result);
		
	}
	
	public interface CustomDialogEditTextResultListener{
		public void customDialogEditTextResult(int result, String resulttext);
		
	}
	
	// Parametros iniciais-----------------------------------------------------------------------------
    private static ProgressDialog progressDialog;
	private static boolean resultado;
	private static CustomDialogResultListener customDialogResultListenter;	
	private static CustomDialogNumberResultListener customDialogNumberResultListener;
	private static CustomAlertDialogReturnListener customAlertDialogReturnListener;
	private static AlertDialog alertaDialog;
	private static AlertDialog alertaDialogReturn;
	private static AlertDialog alertaCustomMessage;
	private static CustomDialogButtonResultListener customDialogButtonResultListener;
	private static CustomDialogEditTextResultListener customDialogEditTextResultListener;
	//private static RegistroGcmListenter registroGcmListenter;
	
	
	// Métodos Utilitários-------------------------------------------------------------------------------
	
	// Inicia e Finaliza a Tela de Dialog
	public static void startDialog(Context context, String msg, boolean tipo){
		if(tipo){
			progressDialog = new ProgressDialog(context);
			progressDialog.show();
			progressDialog.setContentView(R.layout.progress_dialog);
			final TextView tvMensagem = (TextView) progressDialog.findViewById(R.id.pdTexto);
			tvMensagem.setText(msg);
			
			progressDialog.setCancelable(false);

//			progressDialog.setMessage(msg);
//			progressDialog.setCancelable(false);
//			progressDialog.show();
			Statics.progressDialog = true;

		} else{
			if (progressDialog.isShowing()) {
				progressDialog.dismiss();
				Statics.progressDialog = false;	
			}
		}
	}	
	
	// Inicia e Finaliza a Tela de Dialog
	public static void startCustomDialog(Context context, String msg, boolean tipo){
		if(tipo){
			progressDialog = new ProgressDialog(context);
			progressDialog.show();
			progressDialog.setContentView(R.layout.progress_dialog);
			progressDialog.setCancelable(false);
			Statics.progressDialog = true;
		} else{
			if (progressDialog.isShowing()) {
				progressDialog.dismiss();
				Statics.progressDialog = false;	
			}
		}
	}	
	
	// Verifica se exsite conexao com a internet
	public static boolean VerificaConexaoIternet(Context context){
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(cm.getActiveNetworkInfo()!=null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()){
			return true;
		}else{
			return false;
		}
	}
	

	// Mostra caixa de diálogo de alerta de mensagem
	public static void customAlertDialog(Context context, String titulo, String mensagem){
		if(Statics.mensagemErro != null){
			mensagem = Statics.mensagemErro;
			Statics.mensagemErro = null;
		}
		
		if(Statics.progressDialog){
			if(progressDialog.isShowing()){
				progressDialog.dismiss();
				Statics.progressDialog = false;	
			}	
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(titulo);
		builder.setMessage(mensagem);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
		public void onClick(DialogInterface arg0, int arg1) {
			alertaDialog.dismiss();
		} });
		
		alertaDialog = builder.create();
		alertaDialog.setCancelable(false);
		alertaDialog.show();
	}
	
	// Mostra caixa de diálogo de alerta de mensagem com retorno
	public static void customAlertDialogReturn(Context context, String titulo, String mensagem, final int retorno){
		customAlertDialogReturnListener = (CustomAlertDialogReturnListener) context;
		
		if(Statics.mensagemErro != null){
			mensagem = Statics.mensagemErro;
			Statics.mensagemErro = null;
		}
		
		if(Statics.progressDialog){
			if(progressDialog.isShowing()){
				progressDialog.dismiss();
				Statics.progressDialog = false;	
			}	
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(titulo);
		builder.setMessage(mensagem);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
		public void onClick(DialogInterface arg0, int arg1) {
			customAlertDialogReturnListener.customAlertDialogReturnResult(retorno);
			alertaDialogReturn.dismiss();
		} });
		
		alertaDialogReturn = builder.create();
		alertaDialogReturn.setCancelable(false);
		alertaDialogReturn.show();
	}
	
	// Mostra caixa de dialógo com resposta
	public static void customMessageDialog(Context context, String titulo, String mensagem, final int retorno){
		customDialogResultListenter = (CustomDialogResultListener) context;
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(titulo);
		builder.setMessage(mensagem);

		builder.setNegativeButton("OK", new DialogInterface.OnClickListener() { 
		public void onClick(DialogInterface arg0, int arg1) {
			customDialogResultListenter.customDialogResult(retorno);
			alertaCustomMessage.dismiss();
		} });
		
		builder.setPositiveButton("Cancela", new DialogInterface.OnClickListener() { 
		public void onClick(DialogInterface arg0, int arg1) {
			customDialogResultListenter.customDialogResult(0);
			alertaCustomMessage.dismiss();
		} });

		alertaCustomMessage = builder.create();
		alertaCustomMessage.setCancelable(false);
		alertaCustomMessage.show();
	}
	
	// Mostra caixa de dialógo com Botoes PF e PJ
	public static void customMessageDialogButton(Context context, String titulo, String mensagem){
		customDialogButtonResultListener = (CustomDialogButtonResultListener) context;
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(titulo);
		builder.setMessage(mensagem);

	
		builder.setPositiveButton("Pessoa Física", new DialogInterface.OnClickListener() { 
		public void onClick(DialogInterface arg0, int arg1) {
			customDialogButtonResultListener.customDialogButtonResult(1);
			alertaCustomMessage.dismiss();
		} });
		
		builder.setNegativeButton("Pessoa Jurídica", new DialogInterface.OnClickListener() { 
		public void onClick(DialogInterface arg0, int arg1) {
			customDialogButtonResultListener.customDialogButtonResult(2);
			alertaCustomMessage.dismiss();
		} });

		alertaCustomMessage = builder.create();
		alertaCustomMessage.setCancelable(false);
		alertaCustomMessage.show();
	}
	
	// Mostra caixa de dialógo com edittext
		public static void customMessageDialogEditText(Context context, String titulo, String mensagem, final int retorno){
			customDialogEditTextResultListener = (CustomDialogEditTextResultListener) context;
			AlertDialog.Builder alert = new AlertDialog.Builder(context);

			alert.setTitle(titulo);
			alert.setMessage(mensagem);

			// Set an EditText view to get user input 
			final EditText input = new EditText(context);
			input.setInputType(InputType.TYPE_CLASS_NUMBER);
			alert.setView(input);
			
			alert.setPositiveButton("Cancela", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				customDialogEditTextResultListener.customDialogEditTextResult(0, null);
			  }
			});
			
			alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int whichButton) {
					customDialogEditTextResultListener.customDialogEditTextResult(retorno, input.getText().toString().trim());
				  }
				});
			
			alert.show();
		}
	
	// Mostra caixa de diálogo de números
	@SuppressLint("NewApi")
	public static void customNumberDialog(Context context, String titulo, int inicio, int fim, int atual, final String retorno){
		customDialogNumberResultListener = (CustomDialogNumberResultListener) context;
		int valorAtual = 0;
		if(atual == 0) valorAtual = 1;
		final int nAtual = atual+valorAtual;
		
		LayoutInflater inflater = (LayoutInflater)
		context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View npView = inflater.inflate(R.layout.custom_alert_dialog_numero, null);
		final NumberPicker np = (NumberPicker) npView.findViewById(R.id.np_Numero);
		np.setMinValue(inicio); np.setMaxValue(fim); np.setValue(nAtual);
		new AlertDialog.Builder(context)
			.setTitle(titulo)
			.setView(npView)
			.setPositiveButton("Cancela",
					new DialogInterface.OnClickListener() {
			        	public void onClick(DialogInterface dialog, int whichButton) {
			        		customDialogNumberResultListener.customDialogNumberResult(nAtual, retorno);
			            }
			 })
			 .setNegativeButton("OK",
					 new DialogInterface.OnClickListener() {
			         	public void onClick(DialogInterface dialog, int whichButton) {
			        		customDialogNumberResultListener.customDialogNumberResult(np.getValue(), retorno);
			            }
			 })
			 .create()
			 .show();
	}
		
	
	// Mostra numero de série do celular
    public static String numCel(Context context){
        String IMEI = "";
        
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        IMEI = telephonyManager.getDeviceId().toString();
        
        return IMEI;
    }
    
    // Mostra numero de série do chip do celular
    public static String numCard(Context context){
        String IMSI = "";
        
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE); 
        IMSI = telephonyManager.getSimSerialNumber();
                    
        return IMSI;
    }
    
    
	// Mostra a Data e hora. tipo = D-Dia / M-Mes / A-Ano / H-Hora / T-Minuto / S-Segundo
	public static String dataHoraAtual(String tipo){
		String data = "", hora = " ";
		Calendar c = Calendar.getInstance();
		
		for(int n = 0; n<=(tipo.length()-1); n++){
			
			if(tipo.substring(n, n+1).equals("D")) data = data + String.format("%02d",c.get(Calendar.DAY_OF_MONTH))+"/";
			if(tipo.substring(n, n+1).equals("M")) data = data + String.format("%02d",c.get(Calendar.MONTH)+1)+"/";
			if(tipo.substring(n, n+1).equals("A")) data = data + String.format("%04d",c.get(Calendar.YEAR))+"/";

			if(tipo.substring(n, n+1).equals("H")) hora = hora + String.format("%02d",c.get(Calendar.HOUR_OF_DAY))+":";
			if(tipo.substring(n, n+1).equals("T")) hora = hora + String.format("%02d",c.get(Calendar.MINUTE))+":";
			if(tipo.substring(n, n+1).equals("S")) hora = hora + String.format("%02d",c.get(Calendar.SECOND))+":";
			
		}
		
		data = data.substring(0, (data.length()-1));
		hora = hora.substring(0, (hora.length()-1));

		return ((data+hora).trim());
	}
	
	// Mostra a Data e hora. tipo = D-Dia / M-Mes / A-Ano / H-Hora / T-Minuto / S-Segundo
	public static String parteDataHora(String tipo, String datahora){
		String data = "", hora = " ";
		
		for(int n = 0; n<=(tipo.length()-1); n++){
			
			if(tipo.substring(n, n+1).equals("D")) data = data + datahora.substring(0, 2)+"/";
			if(tipo.substring(n, n+1).equals("M")) data = data + datahora.substring(3, 5)+"/";
			if(tipo.substring(n, n+1).equals("A")) data = data + datahora.substring(6, 10)+"/";
			
			if(tipo.substring(n, n+1).equals("H")) hora = hora + datahora.substring(11, 13) +":";
			if(tipo.substring(n, n+1).equals("T")) hora = hora + datahora.substring(14, 16) +":";
			if(tipo.substring(n, n+1).equals("S")) hora = hora + datahora.substring(17, 19) +":";

		}
		
		data = data.substring(0, (data.length()-1));
		hora = hora.substring(0, (hora.length()-1));

		return ((data+hora).trim());
	}
	
	
	// Inverte o formato da data. data = data / tipo = 1-DMA / 2-AMD 
	public static String dataInvertida(String data, int tipo){
		String sData = "";
		
		//DMA
		if(tipo == 1) sData = data.substring(6, (data.length()))+"/"+data.substring(3, (data.length()-5))+"/"+data.substring(0, (data.length()-8));
		//AMD
		if(tipo == 2) sData = data.substring(8, (data.length()))+"/"+data.substring(5, (data.length()-3))+"/"+data.substring(0, (data.length()-6));
		
		return sData;
	}
	
	// testa se edit text está em branco
	public static boolean testaEditBranco(EditText texto){
		String txt = null;
		txt = Mask.unmask(texto.getText().toString());
		Log.i(Constants.TAG, "ID-"+texto.getId()+"/ Texto-"+texto.getText().toString()+"/ tam-"+ txt.trim().length());
		return (txt.trim().length() == 0);
	}
	
	// testa data valida
	public static boolean testaData(String data){
		boolean resultadodt = false;
		String s = data;  
		DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");  
		df.setLenient (false);   
		try {  
		    df.parse (s);
		    resultadodt = true; 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return resultadodt;
	}
	
	// Retorna indice do spinner
	public static int getIndexSpinner(Spinner spinner, String myString){
        int index = 0;
        for (int i=0;i<spinner.getCount();i++){
              if (spinner.getItemAtPosition(i).toString().trim().equals(myString)){
                index = i;
                break;
            }
        }
        return index;
	}
	
	public static String phoneNumber(Context context){
		TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE); 
		String number = tm.getLine1Number();
		return number;
	}
	
	
	public static String primeiroNome(String nome){
		String retorno = null;
		int x = nome.indexOf(" ");
		
		if(x != -1)
		   retorno = nome.substring(0, x);

		return retorno;
	}
	
//	public static int diaSemana(int dia, int mes, int ano){
		public static int diaSemana(String sdata){
		//int dia, int mes, int ano
			int ndia = Integer.parseInt( Util.parteDataHora("D", sdata));
			int nmes = Integer.parseInt( Util.parteDataHora("M", sdata));
			int nano = Integer.parseInt( Util.parteDataHora("A", sdata));

		Calendar calendario = new GregorianCalendar(nano, nmes - 1, ndia);  
	    int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
		return diaSemana;
	}
		
//		Mostra o dia da semana
		public static String mostraDiaSemana(String sdata){
			String diaSemana = null;
			switch (diaSemana(sdata)) {
			case 1: diaSemana = "Domingo"; 
				break;
			case 2: diaSemana = "Segunda";
				break;
			case 3: diaSemana = "Terça";
				break;
			case 4: diaSemana = "Quarta";
				break;
			case 5: diaSemana = "Quinta";
				break;
			case 6: diaSemana = "Sexta";
				break;
			case 7: diaSemana = "Sábado";
				break;
			} 
			
		return diaSemana;
	}
	
	public static String comparaData(String data1, String data2){
		    String a = Mask.unmask(data1);  
		    String b = Mask.unmask(data2);  
		    String resultado;
		      
		    SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");  
		    Date dt1 = null;
		    Date dt2 = null;
			try {
				dt1 = new Date(format.parse(a).getTime());
				dt2 = new Date(format.parse(b).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}  
		      
		      
		    if(dt1.after(dt2)){  
		        System.out.println("Data: " + a + " é posterior à " + b); 
		        resultado = ">";
		    }else if(dt1.before(dt2)){  
		        System.out.println("Data: " + a + " é inferior à " + b);  
		        resultado = "<";
		    }else{  
		        System.out.println("Data: " + a + " é igual à " + b);  
		        resultado = "=";
		    }
			return resultado;
	}
	
	public static String aumentaData(String data, int nDias){
	    String a = Mask.unmask(data);  
	    String resultado;
	      
	    SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");  
	    Date dt1 = null;
		try {
			dt1 = new Date(format.parse(a).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}  
	      
		Calendar calA = Calendar.getInstance();  
		calA.setTime(dt1);  
		calA.add(Calendar.DAY_OF_MONTH, nDias);
//		resultado = format.format(calA.getTime()); //parteDataHora("DMA", format.format(calA.getTime()));
		//Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(oldstring);
		resultado =  new SimpleDateFormat("dd/MM/yyyy").format(calA.getTime());//format.format(calA.getTime());
		
		
		return resultado;
}
	
	//---------- GCM --------------------------------------------------------------------------------
	public static void gcmRegistrar(Context context, String projectId){
		GCMRegistrar.checkDevice(context);
		GCMRegistrar.checkManifest(context);
		final String regId = GCMRegistrar.getRegistrationId(context);
		if(regId.equals("")){
			GCMRegistrar.register(context, projectId);
			Log.e("AAAA","GCM Registrado com sucesso");
		}else {
			Log.e("AAAA","GCM já Registrado com sucesso, DI = "+regId);
			
		}
		
		Log.e("AAAA","GCM já Registrado com sucesso, DI = "+GCMRegistrar.getRegistrationId(context));	
		
	}
	
	public static void mostraMensagem(Context context, String mensagem){
//		Toast toast = Toast.makeText(context, mensagem, Toast.LENGTH_LONG);
//		toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
//		toast.show();
		
		Toast toast = Toast.makeText(context,"  "+mensagem+"  ", Toast.LENGTH_SHORT);
	    LinearLayout toastLayout = (LinearLayout) toast.getView();
	    TextView toastTV = (TextView) toastLayout.getChildAt(0);
	    toastTV.setTextSize(20);
	    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
	    toast.show();
	}
	
	public static void VerificaGCM(){
//		registroGcmListenter.registroGcmResult(5);
		
	}
	
	
	
}
	


