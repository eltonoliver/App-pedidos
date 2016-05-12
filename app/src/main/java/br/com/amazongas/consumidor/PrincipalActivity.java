package br.com.amazongas.consumidor;

import java.util.ArrayList;

import br.com.amazongas.dialog.DialogPFPJActivity;
import br.com.amazongas.dialog.Dialogs;
import br.com.amazongas.dialog.Dialogs.CustomAlertDialogsResultListener;
import br.com.amazongas.model.Bairros;
import br.com.amazongas.model.Clientes;
import br.com.amazongas.model.ClientesPJ;
import br.com.amazongas.task.BairroDBTask;
import br.com.amazongas.task.CidadeDBTask;
import br.com.amazongas.task.LoginPJTask;
import br.com.amazongas.task.LoginPJTask.LoginPJResultListener;
import br.com.amazongas.task.LoginTask;
import br.com.amazongas.task.BairroDBTask.BairroResultListener;
import br.com.amazongas.task.LoginTask.LoginResultListener;
import br.com.amazongas.task.SalvaBairroDBTask;
import br.com.amazongas.task.SalvaBairroDBTask.SalvaBairroDBResultListener;
import br.com.amazongas.task.SalvaBairroTask;
import br.com.amazongas.task.SalvaBairroTask.SalvaBairroResultListener;
import br.com.amazongas.task.SalvaCidadeDBTask;
import br.com.amazongas.task.SalvaCidadeDBTask.SalvaCidadeDBResultListener;
import br.com.amazongas.task.SalvaCidadeTask.SalvaCidadeResultListener;
import br.com.amazongas.task.SalvaRevendaTask.SalvaRevendaResultListener;
import br.com.amazongas.task.SalvaCidadeTask;
import br.com.amazongas.task.SalvaRevendaTask;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Mask;
import br.com.amazongas.util.SettingsHelper;
import br.com.amazongas.util.Statics;
import br.com.amazongas.util.Util;
import br.com.amazongas.util.Util.CustomAlertDialogReturnListener;
import br.com.amazongas.util.Util.CustomDialogButtonResultListener;
import br.com.amazongas.util.Util.CustomDialogResultListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class PrincipalActivity extends Activity implements SalvaBairroResultListener, SalvaCidadeResultListener, 
CustomAlertDialogReturnListener, SalvaRevendaResultListener, CustomDialogButtonResultListener, 
LoginResultListener, CustomAlertDialogsResultListener, SalvaBairroDBResultListener, SalvaCidadeDBResultListener, LoginPJResultListener{
	
	private final Context context = PrincipalActivity.this;
	
	private ImageView ivPedido, ivHistorico, ivRevenda, ivContato, ivSair, ivMenu1, ivMenu2, ivCadastro, ivProduto, ivFacebook, ivLogar, ivSair2;
	private LinearLayout llMenu1, llMenu2;
	private ViewFlipper viewFlipper;
	private float x1,x2;
    private float y1, y2;
    private boolean telaRolagem = false;
	private static final int duracao = 500;
	private TextView tvUsuario, tvUsuario2;
	
	private WebView wb, wb2;
	
	private int tipoTela = 0;  //1-Cadastro / 2-Revenda

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("Consumidor","Inicio Principal: ");
		

		setContentView(R.layout.activity_principal);
		
		llMenu1 = (LinearLayout) findViewById(R.id.llMenu1);
		//ivMenu1 = (ImageView) findViewById(R.id.ivMenu1);
		ivPedido = (ImageView) findViewById(R.id.ivPedido);
		ivHistorico = (ImageView) findViewById(R.id.ivHistorico);
		ivRevenda = (ImageView) findViewById(R.id.ivRevenda);
		ivContato = (ImageView) findViewById(R.id.ivContato);
		//ivSair = (ImageView) findViewById(R.id.ivSair);
		tvUsuario = (TextView) findViewById(R.id.tvPrincipalUsuario);
		
		llMenu2 = (LinearLayout) findViewById(R.id.llMenu2);
		ivMenu2 = (ImageView) findViewById(R.id.ivMenu2);
		ivCadastro = (ImageView) findViewById(R.id.ivCadastro);
		ivProduto = (ImageView) findViewById(R.id.ivProduto);
		ivFacebook = (ImageView) findViewById(R.id.ivFacebook);
		ivLogar = (ImageView) findViewById(R.id.ivLogar);
		//ivSair2 = (ImageView) findViewById(R.id.ivSair2);
		tvUsuario2 = (TextView) findViewById(R.id.tvPrincipalUsuario2);
		
		ivPedido.setOnClickListener(ivPedidoClick);
		ivHistorico.setOnClickListener(ivHistoricoClick);
		ivRevenda.setOnClickListener(ivRevendedorClick);
		ivContato.setOnClickListener(ivContatoClick);
		//ivSair.setOnClickListener(ivSairClick);
		
		ivCadastro.setOnClickListener(ivCadastroClick);
		ivProduto.setOnClickListener(ivProdutoClick);
		ivFacebook.setOnClickListener(ivFacebookClick);
		ivLogar.setOnClickListener(ivLogarClick);
		//ivSair2.setOnClickListener(ivSair2Click);
		
		wb = (WebView) findViewById(R.id.wvAnimacao);
		wb.getSettings().setJavaScriptEnabled(true);  
		//wb.getSettings().setSupportZoom(false);
		//wb.getSettings().setBuiltInZoomControls(false);
		wb.loadUrl("file:///android_asset/mascote1.gif");
		
		wb2 = (WebView) findViewById(R.id.wvAnimacao2);
		wb2.getSettings().setJavaScriptEnabled(true);  
		wb2.loadUrl("file:///android_asset/mascote.gif");
		
		
//		String imagePath = "file://"+ base + "/photo.jpg";
//		String html = "<html><head></head><body><img src=\""+ imagePath + "\"></body></html>";
//		webView.loadDataWithBaseURL("", html, "text/html","utf-8", ""); 
		
		// ViewFlipper
 	    viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
		viewFlipper.setOnTouchListener(new ListenerTouchViewFlipper());
		wb.setOnTouchListener(new ListenerTouchViewFlipper());
		wb2.setOnTouchListener(new ListenerTouchViewFlipper());

//		tvUsuario.setText("");
//		tvUsuario2.setText("");

		
		// Rolagem da tela para a esquerda
		//viewFlipper.showNext();
		//telaRolagem = true;
		
		
		Statics.principalInicioTela = true;
		
		//Display display = ((WindowManager) getSystemService(this.WINDOW_SERVICE))  .getDefaultDisplay();
		//Log.i("Debug", "DISPLAY >>>>> "+display.getWidth()+"-"+ display.getHeight());

		Dialogs.customDialogsTelaInicial(context);
	}
	
	@Override
	protected void onResume() {
		super.onResume();

		
		
		// Verifica se usuário está logado
		if(Statics.principalInicioTela){
			ivLogar.setImageResource(R.drawable.loginxml);
			tvUsuario.setText("Deslize a tela para mais opções");
			tvUsuario2.setText("Deslize a tela para mais opções");
			
			if(SettingsHelper.getUserLogado(context)){
				if(SettingsHelper.getUserTipoUsuario(context).equals("F")){
					tvUsuario.setText("Olá, "+Util.primeiroNome(SettingsHelper.getUserNomeUsuario(context)));
					tvUsuario2.setText("Olá, "+Util.primeiroNome(SettingsHelper.getUserNomeUsuario(context)));
				} else {
					tvUsuario.setText("Olá, "+SettingsHelper.getUserNomeUsuario(context).substring(0, 11));
					tvUsuario2.setText("Olá, "+SettingsHelper.getUserNomeUsuario(context).substring(0, 11));
				}
				
				ivLogar.setImageResource(R.drawable.logoutxml);
				//viewFlipper.showPrevious();
				//telaRolagem = false;

				if(Clientes.getNome() == null && ClientesPJ.getRazaoSocial() == null)
					executaBuscaLogin();
			} 
			//else {
				
//				try {
//					Thread.sleep(5000);
//					viewFlipper.showPrevious();
//					telaRolagem = false;
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

//			}
			
			Statics.principalInicioTela = false;

		}
		

		
		// verifica se é a primeira vez que o app foi inicializado
//		if(SettingsHelper.getUserInicializacao(context) != true){
//			carregaBairros();
//		} else {
//			if(SettingsHelper.getUserLogado(context))
//				if(Clientes.getNome() == null)
//					executaBuscaLogin();
//		}	
		
		
	}
	 
	@Override
	protected void onDestroy() {
		super.onDestroy();
		sair();
	}	
	
	
	// Click das Imagens do Menu -----------------------------------------------------
	private OnClickListener ivPedidoClick = (new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(isLogin()){
				Intent it = new Intent().setClass(context, PedidoActivity.class);
				startActivity(it);
				
			}else {
				executaLogin();
			}
		}
	});
	
	private OnClickListener ivHistoricoClick = (new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(isLogin()){
				Intent it = new Intent().setClass(context, HistoricoPedidoActivity.class);
				startActivity(it);
			}else {
				executaLogin();
			}
		}
	});
	
	private OnClickListener ivRevendedorClick = (new OnClickListener() {
		@Override
		public void onClick(View v) {
			tipoTela = 2;
			if(SettingsHelper.getUserIniCidade(context) != true){
				carregaCidades();
			} else {
				if(SettingsHelper.getUserIniRevenda(context) != true){
					carregaRevenda();
				}else{
					Intent it = new Intent().setClass(context, RevendaActivity.class);
					startActivity(it);
			}	}
		}
	});

	private OnClickListener ivContatoClick = (new OnClickListener() {
		@Override
		public void onClick(View v) {
			performCall();
		}
	});
	
	private OnClickListener ivSairClick = (new OnClickListener() {
		@Override
		public void onClick(View v) {
			sair();
		}
	});	
	
	private OnClickListener ivCadastroClick = (new OnClickListener() {
		@Override
		public void onClick(View v) {
			Log.i("Consumidor","cadastro >>>> "+SettingsHelper.getUserTipoUsuario(context));
			tipoTela = 1;
			if(SettingsHelper.getUserLogado(context) == true){
				if(SettingsHelper.getUserTipoUsuario(context).equals("F")){
					if(Clientes.getNome() == null)
						executaBuscaLogin();
					else
						executaCadastro();
				} else {
					Dialogs.customAlertDialogs(context, "ATENÇÃO", "Acesso somente para Pessoa Fisica", Constants.RETURN_SEM_RETORNO);
				}
			}else {
				executaCadastro();
			}
			
			
		}
	});	
	
	private OnClickListener ivProdutoClick = (new OnClickListener() {
		@Override
		public void onClick(View v) {
			Log.e("Consumidor","Produto clicado");
			Intent it = new Intent().setClass(context, ProdutoActivity.class);
			startActivity(it);
			
		}
	});	
	
	private OnClickListener ivFacebookClick = (new OnClickListener() {
		@Override
		public void onClick(View v) {
			Log.e("Consumidor","Facebook clicado");
			Intent it = new Intent().setClass(context, FaceBookActivity.class);
			startActivity(it);

		}
	});	
	
	private OnClickListener ivLogarClick = (new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(SettingsHelper.getUserLogado(context) == true){
				//Util.customMessageDialog(context, "ATENÇÃO", "Deseja encerrar a sessão?", 1);
				Dialogs.customDialogs(context, "Deseja encerrar a sessão?", Constants.RETURN_LOGOUT);
			} else
				executaLogin();
		}
	});	

	private OnClickListener ivSair2Click = (new OnClickListener() {
		@Override
		public void onClick(View v) {
			//SettingsHelper.setUserLogado(context, false);
			sair();
		}
	});	
	
	
	// Metodos desenvolvidos ------------------------------------------------------------
	
	private void executaCadastro(){
		  Log.e("Consumidor","Cadastro clicado");
		  
		  if(SettingsHelper.getUserIniCidade(context) != true){
			  carregaCidades();
		  } else {
			  Intent it = new Intent().setClass(context, CadastroActivity.class);
			  startActivity(it);
		  }	  
	}
	
	private void executaBuscaLogin(){
		Util.startDialog(context, "Atualizando Dados...", true);
		if(SettingsHelper.getUserTipoUsuario(context).equals("F"))
			(new LoginTask((LoginResultListener) context)).execute(Mask.unmask(SettingsHelper.getUserLogin(context).toString()), Mask.unmask(SettingsHelper.getUserSenha(context).toString()));
		else
			(new LoginPJTask((LoginPJResultListener) context)).execute(SettingsHelper.getUserLogin(context).toString(),SettingsHelper.getUserSenha(context).toString());
	}
	
	private void carregaBairros(){
		Log.i(Constants.TAG, "carrega bairros >>>>>>>>>>");
		Util.startDialog(context, "Carregando Bairros...", true);
//		(new SalvaBairroTask((SalvaBairroResultListener) context)).execute("AM");
		(new SalvaBairroDBTask((SalvaBairroDBResultListener) context)).execute("AM");

	}
	
	private void performCall() {
		Intent localIntent1 = new Intent("android.intent.action.CALL");
		Uri localUri = Uri.parse("tel:9221272000");
		Intent localIntent2 = localIntent1.setData(localUri);
		startActivity(localIntent1);
		// finish();
	}
	
	private boolean isLogin(){
		return (SettingsHelper.getUserLogado(context));
	}
	
	private void executaLogin(){
		//Util.customMessageDialogButton(context, "", "Você deseja acessar o sistema como?");
		//Intent it = new Intent(context, DialogPFPJActivity.class);
		//startActivityForResult(it, Constants.REQUEST_LOGIN);
		//startActivity(it);
		
		//Dialogs.dialogAlert(context);
		Dialogs.customDialogPFPJ(context);
		
		
	}
	
	private void sair(){
		finish();

	}
	
	private void logout(){
		SettingsHelper.setUserLogado(context, false);
		//Util.mostraMensagem(context, "Sessão Finalizada.");
		Dialogs.customToastDialogs(context, "Sessão Finalizada.");
		Statics.principalInicioTela = true;
		onResume();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if(requestCode == Constants.REQUEST_LOGIN){
//			if(data.getExtras().getInt("resultado") == Constants.RESULT_LOGIN_PF){
//				
//			}
//		}
		
	}
	
	// Carrega Cidade
	private void carregaCidades(){
		Log.i(Constants.TAG, "carrega cidade >>>>>>>>>>");
		Util.startDialog(context, "Carregando Cidades...", true);
//		(new SalvaBairroTask((SalvaBairroResultListener) context)).execute("AM");
		(new SalvaCidadeDBTask((SalvaCidadeDBResultListener) context)).execute("AM");

	}
	
	// Carrega Revenda
	private void carregaRevenda(){
		Log.i(Constants.TAG, "carrega revenda >>>>>>>>>>");
		Util.startDialog(context, "Carregando Revendas...", true);
 	    (new SalvaRevendaTask((SalvaRevendaResultListener) context)).execute();

	}
 
	
   
	// ViewFlipper Touch Animation ---------------------------------------------------------------------------------------------------------
    private class ListenerTouchViewFlipper implements View.OnTouchListener{
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			 switch (event.getAction())
             {
                     case MotionEvent.ACTION_DOWN: 
                     {
                         x1 = event.getX();
                         y1 = event.getY();
                         return true;
                    }
                     case MotionEvent.ACTION_UP: 
                     {
                         x2 = event.getX();
                         y2 = event.getY(); 

                         // deslizar da desquerda para a direita
                         if (x1 < x2) 
                         {
                        	if(telaRolagem == true){
                        		viewFlipper.setInAnimation(inFromLeftAnimation());
                             	viewFlipper.setOutAnimation(outToRightAnimation());
                             	viewFlipper.showPrevious();
                             	telaRolagem = false;
                        	 }

                          }
                        
                         // deslizar da direita para esquerda
                         if (x1 > x2)
                         {
                         	if(telaRolagem == false){
                              	viewFlipper.setInAnimation(inFromRightAnimation());
                             	viewFlipper.setOutAnimation(outToLeftAnimation());
                             	viewFlipper.showNext();
                             	telaRolagem = true;
                         	}

                         }
                         
                         break;
                     }
             }
			
	          		return false;
		}
 
    }
 
    private Animation inFromRightAnimation() {
        Animation inFromRight = new TranslateAnimation(
        Animation.RELATIVE_TO_PARENT,  +1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
        Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f );
        inFromRight.setDuration(duracao);
        inFromRight.setInterpolator(new AccelerateInterpolator());
 
        return inFromRight;
 
    }
 
    private Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
        Animation.RELATIVE_TO_PARENT, 0.0f,  Animation.RELATIVE_TO_PARENT, -1.0f,
        Animation.RELATIVE_TO_PARENT, 0.0f,  Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(duracao);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }
 
    private Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
        Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
        Animation.RELATIVE_TO_PARENT, 0.0f,  Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(duracao);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }
 
    private Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, +1.0f,
        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(duracao);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }

    
    // Listeners ----------------------------------------------------------------------------------------------------
	@Override
	public void SalvaBairroResult(boolean result) {
		Log.i(Constants.TAG, "SalvaBairroResult >>>>> "+result);
		if(result){
		  // Dispara thread para carregar as cidades
		  (new SalvaCidadeTask((SalvaCidadeResultListener) context)).execute("AM");
		} else {
			Util.startDialog(context, "", false);
			//Util.customAlertDialogReturn(context, "Atenção", Constants.ERRO_INICIALIZACAO_DADOS, 1);
			Dialogs.customAlertDialogs(context, "ATENÇÃO", Constants.ERRO_INICIALIZACAO_DADOS, 1);
		}
	}

	@Override
	public void SalvaCidadeResult(boolean result) {
		Log.i(Constants.TAG, "SalvaCidadeResult >>>>> "+result);
		//Util.startDialog(context, "", false);
		if(result){
		  // Dispara thread para carregar as revendas
			Log.i(Constants.TAG, "SalvaCidadeResult >>>>> 1 "+result);
		  (new SalvaRevendaTask((SalvaRevendaResultListener) context)).execute();

		} else{
			Util.startDialog(context, "", false);
			//Util.customAlertDialogReturn(context, "Atenção", Constants.ERRO_INICIALIZACAO_DADOS, 1);
			Dialogs.customAlertDialogs(context, "ATENÇÃO", Constants.ERRO_INICIALIZACAO_DADOS, 1);
		}
	}

	@Override
	public void customAlertDialogReturnResult(int result) {
		if(result == 1) finish();
		
	}

	@Override
	public void SalvaRevendaResult(boolean result) {
		Util.startDialog(context, "", false);
		if(result != true){
	  	  //Util.customAlertDialogReturn(context, "Atenção", Constants.ERRO_INICIALIZACAO_DADOS, 1);
			Dialogs.customAlertDialogs(context, "ATENÇÃO", Constants.ERRO_INICIALIZACAO_DADOS, 1);
		}
		
		//SettingsHelper.setUserInicializacao(context, result);
		SettingsHelper.setUserIniRevenda(context, true);
		Intent it = new Intent().setClass(context, RevendaActivity.class);
		startActivity(it);

	}

	@Override
	public void customDialogButtonResult(int result) {
		if(telaRolagem == false){
			viewFlipper.showNext();
			telaRolagem = true;
		}
		Intent it = new Intent().setClass(context, LoginActivity.class);
		it.putExtra("valor", result);
		startActivity(it);
	}

//	@Override
//	public void customDialogResult(int result) {
//		if(result == 1) logout();
//		
//	}

	@Override
	public void LoginResult(boolean result) {
		Util.startDialog(context, "", false);
		if(result == true)
			//executaCadastro();
			Log.i("teste","testse");
		else
			//Util.customAlertDialog(context, "Atenção", Constants.ERRO_DADOS_WEBSERVICE);
			Dialogs.customAlertDialogs(context, "ATENÇÃO", Constants.ERRO_DADOS_WEBSERVICE, 0);
	}

	@Override
	public void CustomAlertDialogsResult(int result, int retorno) {
		if(retorno != Constants.RETURN_SEM_RETORNO){
			
			// Logout
			if(retorno == Constants.RETURN_LOGOUT){ 
				if(result == Constants.RESULT_CONFIRMAR) logout();
			} else {
				if(telaRolagem == false){
					//viewFlipper.showNext();
					//telaRolagem = true;
				}
			}
			
			// Login
			if(result == Constants.RESULT_PF || result == Constants.RESULT_PJ){	
				Intent it = new Intent().setClass(context, LoginActivity.class);
				it.putExtra("valor", result);
				startActivity(it);
			}	

			
			
		}	
	}

	@Override
	public void SalvaBairroDBResult(boolean result) {
		Log.i(Constants.TAG, "SalvaBairroDBResult >>>>>>>>>>");
		
		Util.startDialog(context, "", false);
		if(result == true){
			SettingsHelper.setUserIniCidade(context, true);
			SettingsHelper.setUserIniBairro(context, true);
			
			if(tipoTela == 1){
				Intent it = new Intent().setClass(context, CadastroActivity.class);
				startActivity(it);
			};
			
			if(tipoTela == 2){
				carregaRevenda();
			};
				

		}else{	
			//Util.customAlertDialogReturn(context, "Atenção","Erro ao carregar bairros", 1);
			Dialogs.customAlertDialogs(context, "ATENÇÃO", "Erro ao carregar bairros", 1);
		}
		
		
	}

	@Override
	public void SalvaCidadeDBResult(boolean result) {
		Log.i(Constants.TAG, "SalvaCidadeDBResult >>>>>>>>>>"+result);
		Util.startDialog(context, "", false);
		if(result == true){
			carregaBairros();
		} else {
			//Util.customAlertDialogReturn(context, "Atenção", "Erro ao carregar cidades", 1);
			Dialogs.customAlertDialogs(context, "ATENÇÃO", "Erro ao carregar cidades", 1);
		}

		
	}

	@Override
	public void LoginPJResult(boolean result) {
		Util.startDialog(context, "", false);
		if(result == true)
			//executaCadastro();
			Log.i("teste","testse");
		else
			//Util.customAlertDialog(context, "Atenção", Constants.ERRO_DADOS_WEBSERVICE);
			Dialogs.customAlertDialogs(context, "ATENÇÃO", Constants.ERRO_DADOS_WEBSERVICE, 0);
		
	}

}
