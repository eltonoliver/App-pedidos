package br.com.amazongas.dialog;

import java.util.zip.Inflater;

import br.com.amazongas.consumidor.R;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;
import br.com.amazongas.util.Util.CustomAlertDialogReturnListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Dialogs {
	
	public interface CustomAlertDialogsResultListener{
		public void CustomAlertDialogsResult(int result, int retorno);
	}
	
	
	private static AlertDialog alerta;
	private static CustomAlertDialogsResultListener listener;
	
	
//	public static void dialogAlert(Context context) {
//		
//		final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
//			//customDialog = new Dialog(this,R.style.Theme_Dialog_Translucent);
//		        dialog.setContentView(R.layout.activity_dialog_pfpj);//carregando o layout do dialog do xml
//		        //dialog.setTitle("Título do custom dialog");//título do dialog
//		        //dialog.
//		        final ImageView ok = (ImageView) dialog.findViewById(R.id.btPF);//se atentem ao dialog.
//		        ok.setOnClickListener(new View.OnClickListener() {
//		            public void onClick(View v) {
//		            	Log.i("teste", "PF >>>>>>>>>>>>>>>>>");
//		            	dialog.dismiss();
//		            }
//		        });
//		    dialog.show();//mostra o dialog
//	}
	
	
	 
    public static void customDialogPFPJ(Context context) {
    	listener = (CustomAlertDialogsResultListener) context;
    	
//    	View view = LayoutInflater.from(context).inflate(R.layout.activity_dialog_pfpj, null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setView(view);
//        final ImageView btPF = (ImageView) view.findViewById(R.id.btPF);
//        final ImageView btPJ = (ImageView) view.findViewById(R.id.btPJ);
//        final ImageView btVoltar = (ImageView) view.findViewById(R.id.btPJPFVoltar);
//        
//        btPF.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Log.i("teste", "PF >>>>>>>>>>>>>>>>>");
//				listener.CustomAlertDialogsResult(Constants.RESULT_PF, 0);		
//				alerta.dismiss();
//			}
//		});
//        
//        btPJ.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Log.i("teste", "PJ >>>>>>>>>>>>>>>>>");
//				listener.CustomAlertDialogsResult(Constants.RESULT_PJ, 0);		
//				alerta.dismiss();
//			}
//		});
//
//        btVoltar.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Log.i("teste", "Voltar >>>>>>>>>>>>>>>>>");
//				//listener.CustomAlertDialogsResult(Constants.RESULT_VOLTAR);		
//				alerta.dismiss();
//			}
//		});
//        //builder.setTitle("Titulo");//define o titulo
//        //builder.setMessage("Digite aqui sua mensagem!");//define a mensagem
//        //define um botão como positivo
//        
//        alerta = builder.create();//cria o AlertDialog
//        alerta.show();//Exibe
    	
		final Dialog dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
	    dialog.setContentView(R.layout.activity_dialog_pfpj);
	    
	    final ImageView btPF = (ImageView) dialog.findViewById(R.id.btPF);
	    final ImageView btPJ = (ImageView) dialog.findViewById(R.id.btPJ);
	    final ImageView btVoltar = (ImageView) dialog.findViewById(R.id.btPJPFVoltar);
	    
	    btPF.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.CustomAlertDialogsResult(Constants.RESULT_PF, 0);		
				dialog.dismiss();
			}
		});

	    btPJ.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.CustomAlertDialogsResult(Constants.RESULT_PJ,  0);		
				dialog.dismiss();
			}
		});

	    btVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	    
	    dialog.show();
	    
    }
    
    // Dialogs List
    public static void customDialogList(Context context) {
    	listener = (CustomAlertDialogsResultListener) context;
    	
    	
		final Dialog dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
	    dialog.setContentView(R.layout.activity_dialog_lista);
	    
	    final ListView lvLista = (ListView) dialog.findViewById(R.id.lvDialogsLista);
	    final ImageView btVoltar = (ImageView) dialog.findViewById(R.id.btDialogsListaVoltar);
	    

	    btVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	    
	    dialog.show();
	    
    }
  
    
    
    public static void customDialogs(Context context, String mensagem, final int retorno) {
    	listener = (CustomAlertDialogsResultListener) context;
    	
    	//final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
		final Dialog dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
	    dialog.setContentView(R.layout.activity_dialog_customdialogs);
	        
	        
	    final TextView tvMensagem = (TextView) dialog.findViewById(R.id.tvDialogsMensagem);
	    final ImageView btConfirmar = (ImageView) dialog.findViewById(R.id.btDialogsConfirmar);
	    final ImageView btCancelar = (ImageView) dialog.findViewById(R.id.btDialogsCancelar);
	    
	    tvMensagem.setText(mensagem);
	        
	    btConfirmar.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		listener.CustomAlertDialogsResult(Constants.RESULT_CONFIRMAR, retorno);	
	    		dialog.dismiss();
	    	}
	   	});
	    
	    btCancelar.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		listener.CustomAlertDialogsResult(Constants.RESULT_CANCELAR, retorno);	
	            dialog.dismiss();
	        }
	    });
	    
	    dialog.show();	
    	
    }
    
    
    // ALert Dialog ------------------------------------------------------------------------------------------------
    
    public static void customAlertDialogs(Context context, String titulo, String mensagem, final int retorno) {
    	listener = (CustomAlertDialogsResultListener) context;
    	
    	if(Statics.mensagemErro != null){
			mensagem = Statics.mensagemErro;
			Statics.mensagemErro = null;
		}
    	
    	//final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
		final Dialog dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
	    dialog.setContentView(R.layout.activity_dialog_alertcustomdialogs);
	        
	        
	    final TextView tvTitulo = (TextView) dialog.findViewById(R.id.tvAlertDialogsTitulo);
	    final TextView tvMensagem = (TextView) dialog.findViewById(R.id.tvAlertDialogsMensagem);
	    final ImageView btConfirmar = (ImageView) dialog.findViewById(R.id.btAlertDialogsConfirmar);
	    
	    tvMensagem.setText(mensagem);
	        
	    btConfirmar.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		listener.CustomAlertDialogsResult(Constants.RESULT_CONFIRMAR, retorno);	
	    		dialog.dismiss();
	    	}
	   	});
	    
	    
	    dialog.show();	
    	
    }
    
    public static void customToastDialogs(Context context, String mensagem) {
    	LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    	View view = inflater.inflate(R.layout.activity_dialog_toast, null );
    	final TextView tvmensagem = (TextView) view.findViewById(R.id.tvToastDialogsTexto);
    	tvmensagem.setText(mensagem);
    	Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
    
    // Telas Iniciais ---------------------------------------------------------------
    public static void customDialogsTelaInicial(Context context) {
//		final Dialog dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
//		final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
		final Dialog dialog = new Dialog(context,R.style.AThemeDialogCustom);
	    dialog.setContentView(R.layout.activity_dialog_tela_inicial);
	    //dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    LinearLayout ll = (LinearLayout) dialog.findViewById(R.id.llTelaInicial);
	    ll.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	    dialog.show();	
    	
    }
    

    public static void customDialogTwoButtons(Context context, int image1, int image2, final int retorno) {
    	listener = (CustomAlertDialogsResultListener) context;
    	
		final Dialog dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
	    dialog.setContentView(R.layout.activity_dialog_two_buttons);
	    
	    final ImageView bt1 = (ImageView) dialog.findViewById(R.id.btTwoButtonsBT1);
	    final ImageView bt2 = (ImageView) dialog.findViewById(R.id.btTwoButtonsBT2);
	    final ImageView btVoltar = (ImageView) dialog.findViewById(R.id.btTwoButtonsVoltar);
	    
	    bt1.setImageResource(image1);
	    bt2.setImageResource(image2);
	    
	    
	    bt1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.CustomAlertDialogsResult(Constants.RESULT_BT1, retorno);		
				dialog.dismiss();
			}
		});

	    bt2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.CustomAlertDialogsResult(Constants.RESULT_BT2,  retorno);		
				dialog.dismiss();
			}
		});

	    btVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	    
	    dialog.show();
	    
    }
    
    
    
    
}
