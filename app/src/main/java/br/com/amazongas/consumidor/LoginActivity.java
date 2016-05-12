package br.com.amazongas.consumidor;

import java.util.ArrayList;

import br.com.amazongas.dialog.Dialogs;
import br.com.amazongas.dialog.Dialogs.CustomAlertDialogsResultListener;
import br.com.amazongas.listener.PostResultListener;
import br.com.amazongas.model.Clientes;
import br.com.amazongas.model.ClientesPJ;
import br.com.amazongas.task.BairroDBTask;
import br.com.amazongas.task.EnviaEmailPJTask;
import br.com.amazongas.task.EnviaEmailPJTask.EnviaEmailPJResultListener;
import br.com.amazongas.task.LoginPJTask;
import br.com.amazongas.task.LoginTask;
import br.com.amazongas.task.LoginPJTask.LoginPJResultListener;
import br.com.amazongas.task.LoginTask.LoginResultListener;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.CustomDialogDate;
import br.com.amazongas.util.CustomDialogDate.CustomDialogDateListener;
import br.com.amazongas.util.Mask;
import br.com.amazongas.util.SettingsHelper;
import br.com.amazongas.util.Statics;
import br.com.amazongas.util.Util;
import br.com.amazongas.util.Util.CustomDialogButtonResultListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends Activity implements LoginResultListener,
CustomAlertDialogsResultListener, LoginPJResultListener, EnviaEmailPJResultListener{
	private Context context = LoginActivity.this;
	
	private ImageView ivCpf, ivCnpj, btVoltar, btEntrar;
	private EditText etCpfCnpj, etSenha;
	private TextWatcher cpfMask;
	private TextWatcher cnpjMask;
	private String sLogin, sSenha;
	private boolean tipoUsuario;
	private TextView tvTituloCpfCnpj, tvTituloSenha, tvObterSenha;
	private int tpUsuario = 0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
//		ivCpf = (ImageView) findViewById(R.id.ivLoginCpf);
//		ivCnpj = (ImageView) findViewById(R.id.ivLoginCnpj);
		
		btEntrar = (ImageView) findViewById(R.id.btLoginEntrar);
		btVoltar = (ImageView) findViewById(R.id.btLoginVoltar);
		
		etCpfCnpj = (EditText) findViewById(R.id.etLoginCnpjcpf);
		etSenha   = (EditText) findViewById(R.id.etLoginSenha);
		
		tvTituloCpfCnpj   = (TextView) findViewById(R.id.tvTituloLoginCnpjcpf);
		tvTituloSenha   = (TextView) findViewById(R.id.tvTituloLoginSenha);
		tvObterSenha   = (TextView) findViewById(R.id.tvLoginObterSenha);
		
		tvTituloCpfCnpj.setText("CNPJ/CPF");
		
		// Máscaras dos Edits
		cpfMask = Mask.insert("###.###.###-##", etCpfCnpj);
        cnpjMask = Mask.insert("##.###.###/####-##", etCpfCnpj);
        
        //etSenha.addTextChangedListener(Mask.insert("(##)####-####", etSenha));
		
		//Cliques dos botões
		//ivCpf.setOnClickListener(ivCpfClick);
		//ivCnpj.setOnClickListener(ivCnpjClick);
		btEntrar.setOnClickListener(btEntrarClick);
		btVoltar.setOnClickListener(btVoltarClick);
		tvObterSenha.setOnClickListener(tvObterSenhaClick);
		// Inicia e posiciona o botao na opção de CPF
		//selecionaCpfCnpj(true);
		//if(SettingsHelper.getUserLogado(LoginActivity.this) != true || Clientes.getNome() == null){
		//	etCpfCnpj.setText(SettingsHelper.getUserLogin(LoginActivity.this));
		//	iniciaLogin();
	//	}
		
		
		Bundle extras = getIntent().getExtras();
		if(extras.getInt("valor") == Constants.RESULT_PF){
			tvTituloCpfCnpj.setText("CPF");
			tvTituloSenha.setText("TELEFONE");
			selecionaCpfCnpj(true);
			 etSenha.addTextChangedListener(Mask.insert("(##)#####-####", etSenha));
 			 etSenha.setInputType(InputType.TYPE_CLASS_NUMBER |InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
			 tvObterSenha.setVisibility(View.INVISIBLE);
			 tpUsuario = 1;
		} else {
			tvTituloCpfCnpj.setText("CNPJ");
			tvTituloSenha.setText("SENHA");
			selecionaCpfCnpj(false);
			etSenha.removeTextChangedListener(Mask.insert("(##)#####-####", etSenha));
			etSenha.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
			tvObterSenha.setVisibility(View.VISIBLE);
			tpUsuario = 2;
		}
		etCpfCnpj.setText("");
		etSenha.setText("");
		
	}

	
	
	//Metodos de Cliques dos botões
	public OnClickListener tvObterSenhaClick = (new OnClickListener() {
		@Override
		public void onClick(View v) {
			sLogin = Mask.unmask(etCpfCnpj.getText().toString());
			if(sLogin != "")
				Dialogs.customDialogs(context, "Confirma a obtenção de senha?", Constants.RETURN_OBTER_SENHA);
			else
				Dialogs.customAlertDialogs(context, "ATENÇÃO", "CNPJ em branco.", Constants.RETURN_SEM_RETORNO);
				
		}
	});
	public OnClickListener ivCpfClick = (new OnClickListener() {
		@Override
		public void onClick(View v) {
			selecionaCpfCnpj(true);
		}
	});
	
	public OnClickListener ivCnpjClick = (new OnClickListener() {
		@Override
		public void onClick(View v) {
			selecionaCpfCnpj(false);			
		}
	});
	
	public OnClickListener btEntrarClick = (new OnClickListener() {
		@Override
		public void onClick(View v) {
			sLogin = Mask.unmask(etCpfCnpj.getText().toString());
			sSenha = Mask.unmask(etSenha.getText().toString());
			
			if(sLogin != "" && sSenha !=""){
				Log.e(Constants.TAG, "tpUsuario >>>>>>>>- "+tpUsuario);
				Util.startDialog(LoginActivity.this, "Aguarde, autenticando...", true);
				if(tpUsuario == 1)
					(new LoginTask(LoginActivity.this)).execute(sLogin, sSenha);
				else
					(new LoginPJTask(LoginActivity.this)).execute(etCpfCnpj.getText().toString(), etSenha.getText().toString());
			} else {
				//Util.customAlertDialog(LoginActivity.this, "Atenção", Constants.MSG_LOGIN_BRANCO);
				Dialogs.customAlertDialogs(context, "ATENÇÃO", Constants.MSG_LOGIN_BRANCO, 0);
			}
			
		}
	});
	
	public OnClickListener btVoltarClick = (new OnClickListener() {
		@Override	
		public void onClick(View v) {
			finish();
		}
	});
	
	
	private void selecionaCpfCnpj(boolean tipo){
		etCpfCnpj.removeTextChangedListener(cnpjMask);
		etCpfCnpj.removeTextChangedListener(cpfMask);
		if(tipo){
			//ivCnpj.setImageResource(R.drawable.btopcoes);
			//ivCpf.setImageResource(R.drawable.btopcoes1);
			etCpfCnpj.setText("");
			etCpfCnpj.addTextChangedListener(cpfMask);			
		} else {
			//ivCpf.setImageResource(R.drawable.btopcoes);
			//ivCnpj.setImageResource(R.drawable.btopcoes1);
			etCpfCnpj.setText("");
			etCpfCnpj.addTextChangedListener(cnpjMask);			
		}
	}

	@Override
	public void LoginResult(boolean result) {
		Util.startDialog(LoginActivity.this, "", false);
		if(result){
			SettingsHelper.setUserLogado(LoginActivity.this, true);
			SettingsHelper.setUserCodConsumidor(LoginActivity.this,Clientes.getCodConsumidor());
			SettingsHelper.setUserLogin(LoginActivity.this, etCpfCnpj.getText().toString());
			SettingsHelper.setUserSenha(LoginActivity.this, etSenha.getText().toString());
			SettingsHelper.setUserEstado(LoginActivity.this,Clientes.getEstado());
			SettingsHelper.setUserBairro(LoginActivity.this, Clientes.getNomebairro());
			SettingsHelper.setUserCodCidade(LoginActivity.this, Clientes.getCodCidade());
			SettingsHelper.setUserNomeUsuario(LoginActivity.this, Clientes.getNome());
			SettingsHelper.setUserCodBairro(LoginActivity.this, Clientes.getCodBairro());
			if(Mask.unmask(etCpfCnpj.getText().toString()).length()>11)
				SettingsHelper.setUserTipoUsuario(LoginActivity.this, "J");
			else
				SettingsHelper.setUserTipoUsuario(LoginActivity.this, "F");
			
			//Util.mostraMensagem(context, "Bem vindo. Sessão Iniciada.");
			Dialogs.customToastDialogs(context, "Bem vindo. Sessão Iniciada.");
			Statics.principalInicioTela = true;
			finish();
		} else {
			//Util.customAlertDialog(LoginActivity.this, "Atenção", Constants.ERRO_DADOS_WEBSERVICE);
			Dialogs.customAlertDialogs(context, "ATENÇÃO", Constants.ERRO_DADOS_WEBSERVICE, 0);
		}
		
	}

	@Override
	public void CustomAlertDialogsResult(int result, int retorno) {
		
		if(retorno != Constants.RETURN_SEM_RETORNO){
			// Confirmar
			if(result == Constants.RESULT_CONFIRMAR){
				if(retorno == Constants.RETURN_OBTER_SENHA){
					//Dialogs.customAlertDialogs(context, "ATENÇÃO", "Senha enviada para o e-mail cadastrado.", Constants.RETURN_SEM_RETORNO);
					Util.startDialog(context, "Enviando e-mail...", true);
					(new EnviaEmailPJTask((EnviaEmailPJResultListener) context)).execute(SettingsHelper.getUserLogin(context).toString());
				}
				
			}
		}
		
	}

	@Override
	public void LoginPJResult(boolean result) {
		Util.startDialog(LoginActivity.this, "", false);
		if(result){
			SettingsHelper.setUserLogado(LoginActivity.this, true);
			SettingsHelper.setUserCodConsumidor(LoginActivity.this,ClientesPJ.getCodCliente());
			SettingsHelper.setUserLogin(LoginActivity.this, etCpfCnpj.getText().toString());
			SettingsHelper.setUserSenha(LoginActivity.this, etSenha.getText().toString());
			SettingsHelper.setUserEstado(LoginActivity.this,"AM");
			//SettingsHelper.setUserBairro(LoginActivity.this, Clientes.getNomebairro());
			SettingsHelper.setUserCodCidade(LoginActivity.this, "130.260-4");
			SettingsHelper.setUserNomeUsuario(LoginActivity.this, ClientesPJ.getRazaoSocial());
			SettingsHelper.setUserCodBairro(LoginActivity.this, ClientesPJ.getCodBairro());
			
			SettingsHelper.setUserTipoUsuario(LoginActivity.this, "J");
			
			//Util.mostraMensagem(context, "Bem vindo. Sessão Iniciada.");
			Dialogs.customToastDialogs(context, "Bem vindo. Sessão Iniciada.");
			Statics.principalInicioTela = true;
			finish();
		} else {
			//Util.customAlertDialog(LoginActivity.this, "Atenção", Constants.ERRO_DADOS_WEBSERVICE);
			Dialogs.customAlertDialogs(context, "ATENÇÃO", Constants.ERRO_DADOS_WEBSERVICE, 0);
		}
		
	}

	@Override
	public void onEnviaEmailPJResult(boolean result) {
		Util.startDialog(context, "", false);
		if(result)
			Dialogs.customAlertDialogs(context, "ATENÇÃO", "Senha enviada para o e-mail cadastrado.", Constants.RETURN_SEM_RETORNO);
		
	}
	
	

}
