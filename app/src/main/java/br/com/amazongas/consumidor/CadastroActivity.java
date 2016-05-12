package br.com.amazongas.consumidor;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import br.com.amazongas.dialog.Dialogs;
import br.com.amazongas.dialog.Dialogs.CustomAlertDialogsResultListener;
import br.com.amazongas.model.Bairros;
import br.com.amazongas.model.Cidades;
import br.com.amazongas.model.Clientes;
import br.com.amazongas.task.BairroDBTask;
import br.com.amazongas.task.BairroDBTask.BairroResultListener;
import br.com.amazongas.task.CidadeDBTask;
import br.com.amazongas.task.CidadeDBTask.CidadeResultListener;
import br.com.amazongas.task.PostClienteTask;
import br.com.amazongas.task.PostClienteTask.ClienteResultListener;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Mask;
import br.com.amazongas.util.SettingsHelper;
import br.com.amazongas.util.Util;
import br.com.amazongas.util.Util.CustomAlertDialogReturnListener;
import br.com.amazongas.util.Util.CustomDialogResultListener;
import br.com.amazongas.util.ValidarDocumento;


public class CadastroActivity extends Activity implements BairroResultListener, OnItemSelectedListener, ClienteResultListener, 
														  CidadeResultListener, CustomAlertDialogReturnListener, 
														  CustomDialogResultListener, CustomAlertDialogsResultListener{
	private Context context = CadastroActivity.this;
	
	private EditText etCnpjCpf, etNome, etEndereco, etNumero, etCep, etUf, etTelefone, etDtNascimento, etSenha, etObs, etEmail;
	private ImageView btVoltar, btSalvar;
	private Spinner spCidade, spBairro;
	
	private List<Bairros> listaBairros = null;
	private ArrayList<Cidades> listaCidades = null;
	//private ArrayList<String> lista = new ArrayList<String>();
	
	private int codBairro = 0;
	private String nomeBairro;
	private String cidade = null;
	private String codCidade = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);
		
		etCnpjCpf = (EditText) findViewById(R.id.etCadCnpjcpf);
		etNome = (EditText) findViewById(R.id.etCadNome);
		etEndereco = (EditText) findViewById(R.id.etCadEndereco);
		etNumero = (EditText) findViewById(R.id.etCadNumero);
		spBairro = (Spinner) findViewById(R.id.spCadBairro);
//		etBairro = (EditText) findViewById(R.id.etCadBairro);
		etCep = (EditText) findViewById(R.id.etCadCep);
		spCidade = (Spinner) findViewById(R.id.spCadCidade);
		etTelefone = (EditText) findViewById(R.id.etCadTelefone);
		etUf = (EditText) findViewById(R.id.etCadUf);
		etDtNascimento = (EditText) findViewById(R.id.etCadDtNascimento);
//		etSenha = (EditText) findViewById(R.id.etCadSenha);
		etEmail = (EditText) findViewById(R.id.etCadEmail);
		etObs = (EditText) findViewById(R.id.etCadObs);
		
		
//		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//		R.array.Seleciona_cidade, R.layout.simple_spinner_item);
//		adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
//		spCidade.setAdapter(adapter);
		
//		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, R.array.Seleciona_cidade);
//		ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
//		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//		spCidade.setAdapter(spinnerArrayAdapter);		
		
		btVoltar = (ImageView) findViewById(R.id.btCadVoltar);
		btVoltar.setOnClickListener(btVoltarClick);
		btSalvar = (ImageView) findViewById(R.id.btCadSalvar);
		btSalvar.setOnClickListener(btSalvarClick);
		
		//Máscaras
		etCep.addTextChangedListener(Mask.insert("#####-###", etCep));
		etDtNascimento.addTextChangedListener(Mask.insert("##/##/####", etDtNascimento));
		etTelefone.addTextChangedListener(Mask.insert("(##)#####-####", etTelefone));

		etCnpjCpf.setEnabled(true);
		etCnpjCpf.setFocusable(true);
		etUf.setEnabled(false);
		etTelefone.setEnabled(true);
		etTelefone.setFocusable(true);
		
		//etCnpjCpf.requestFocus();
		
		//Dados do usuario
		if (SettingsHelper.getUserLogado(context) == true){
			etCnpjCpf.setText(Clientes.getCnpjCpf());
			etCnpjCpf.setEnabled(false);
			etCnpjCpf.setFocusable(false);
			etNome.setText(Clientes.getNome());
			etEndereco.setText(Clientes.getEndereco());
			etNumero.setText(Clientes.getNumero());
			etCep.setText(Clientes.getCep());
			etUf.setText(Clientes.getEstado());
			etTelefone.setText(Clientes.getTelefone());
			etTelefone.setEnabled(false);
			etTelefone.setFocusable(false);
			etDtNascimento.setText(Clientes.getDtNascimento());
			etEmail.setText(Clientes.getEmail());
			etObs.setText(Clientes.getObsAgenda());
			etNome.requestFocus();
			listaBairros("AM");
			Log.i(Constants.TAG, "CADASTRO >>>>>>>"+Clientes.getDtNascimento());
			
		} else {
			etCnpjCpf.requestFocus();
			listaBairros("AM");
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		//listaBairros("AM");

	}
	
	
	// Métodos de cliques de botões
	public OnClickListener btVoltarClick = (new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	});
	
	public OnClickListener btSalvarClick = (new OnClickListener() {
		@Override
		public void onClick(View v) {
			Log.i(Constants.TAG, "context - "+context);
			//Util.customAlertDialog(CadastroActivity.this, "Atenção2", Constants.MSG_CAMPOS_BRANCO);
			//////// Util.customMessageDialog(CadastroActivity.this, "CADASTRO", Constants.MSG_CONFIRMA_DADOS, 1);
			//String endeWeb = etEndereco.getText().toString().trim().replace(" ", "+")+","+nomeBairro.trim().replace(" ", "+")+","+cidade.trim().replace(" ", "+");
			//Log.i("teste","endereco - "+endeWeb);
			Dialogs.customDialogs(context, Constants.MSG_CONFIRMA_DADOS, Constants.RETURN_CADASTRO_CONSUMIDOR);
		}
	});
	

	// Lista bairros
	public void listaBairros(String uf){
		Log.e(Constants.TAG, "listabairros");
		Util.startDialog(CadastroActivity.this, "Iniciando informações...", true);
		(new BairroDBTask(CadastroActivity.this)).execute("AM");
	}

	@Override
	public void BairroResult(List<Bairros> result) {
		Log.e(Constants.TAG, "BairroResult - incio"+result);
		
		listaBairros = result;
		//lista.clear();
		

		if(result != null){
			ArrayList<String> lista = new ArrayList<String>();
			for(Bairros bairro : result){
				if(bairro.getNomeBairro() != "null"){
					lista.add(bairro.getNomeBairro());
				}
			}
			
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CadastroActivity.this, android.R.layout.simple_spinner_item, lista);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spBairro.setAdapter(dataAdapter);
			Log.i(Constants.TAG,"BairroResult nomebairro - "+Clientes.getNomebairro());

			if(Clientes.getNomebairro() != null){ 
				spBairro.setSelection(Util.getIndexSpinner(spBairro, Clientes.getNomebairro().trim()));
			}	
			spBairro.setOnItemSelectedListener(this);

			// Dispara thread da listagem de cidades
			(new CidadeDBTask(CadastroActivity.this)).execute("AM");
			
			
		} else {
			Log.i(Constants.TAG, "Bairro result - erro");

			Util.startDialog(CadastroActivity.this, "", false);
			Util.customAlertDialogReturn(context, "Atenção", Constants.ERRO_DADOS_WEBSERVICE,1);
		}
		Log.i(Constants.TAG, "Bairroresult - fim");
		
	}
	
	public void onItemSelected(AdapterView<?> parent, View view, int position,long id){
		//Log.i(Constants.TAG, "Bairro Selecionado - position="+position+" / id = "+id);
//		Log.i(Constants.TAG, "Bairro Selecionado - "+listaBairros.get(position).getNomeBairro()+" - cidade - "+listaCidades.get(position).getNome());
		
		Spinner spinner = (Spinner) parent;
		if(spinner.getId() == R.id.spCadBairro) {
			codBairro  = listaBairros.get(position).getCodBairro();
			nomeBairro = listaBairros.get(position).getNomeBairro();
			Log.i(Constants.TAG, "Bairro Selecionado - "+listaBairros.get(position).getNomeBairro());
		} 
		else if(spinner.getId() == R.id.spCadCidade) {
		 	cidade = listaCidades.get(position).getNome();
		 	etUf.setText("AM");
		 	codCidade = listaCidades.get(position).getCodcidade();
			Log.i(Constants.TAG, "Cidade - "+cidade);
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	/// Métodos desenvolvidos -------------------------------------------------------------------------
	
	private void salvaCadastro(){
		
		Log.i(Constants.TAG, "salvaCadastro 01");

		boolean lIsEmpty = false;
		
		if(Util.testaEditBranco(etCnpjCpf)) lIsEmpty = true;
		else if(Util.testaEditBranco(etNome))  lIsEmpty = true; 
		else if(Util.testaEditBranco(etEndereco))  lIsEmpty = true;
		else if(Util.testaEditBranco(etNumero))  lIsEmpty = true; 
		else if(Util.testaEditBranco(etCep))  lIsEmpty = true; 
		else if(Util.testaEditBranco(etUf))  lIsEmpty = true; 
		else if(Util.testaEditBranco(etTelefone))  lIsEmpty = true; 
	    else if(Util.testaEditBranco(etDtNascimento))  lIsEmpty = true; 
		else if(Util.testaEditBranco(etObs))  lIsEmpty = true;
		
		Log.i(Constants.TAG, "salvaCadastro lIsempty="+lIsEmpty);
		
		if(lIsEmpty != true){
			Log.i(Constants.TAG, "salvaCadastro - dados 1");
			if(ValidarDocumento.check(Mask.unmask(etCnpjCpf.getText().toString())) != true){
				Log.i(Constants.TAG, "salvaCadastro - dados 2 - "+etCnpjCpf.getText().toString());
				Util.customAlertDialog(CadastroActivity.this, "Atenção", Constants.MSG_CPFCNPJ_INVALIDO);	
			}
			else if(Util.testaData(etDtNascimento.getText().toString()) != true){
				Log.i(Constants.TAG, "salvaCadastro - dados 3");

				Util.customAlertDialog(CadastroActivity.this, "Atenção", Constants.MSG_DATA_INVALIDA);
			}
			else{
				Log.i(Constants.TAG, "salvaCadastro - dados 4");

				Clientes clientes = new Clientes();
				clientes.setCnpjCpf(Mask.unmask(etCnpjCpf.getText().toString().toUpperCase()));
				clientes.setNome(etNome.getText().toString().toUpperCase());
				clientes.setEndereco(etEndereco.getText().toString().toUpperCase());
				clientes.setNumero(etNumero.getText().toString().toUpperCase());
				clientes.setCodBairro(codBairro);
				clientes.setNomebairro(nomeBairro);
				clientes.setCep(Mask.unmask(etCep.getText().toString()));
				clientes.setCidade(cidade.toUpperCase());
				clientes.setEstado("AM");
				clientes.setTelefone(Mask.unmask(etTelefone.getText().toString()));
//				clientes.setDtNascimento(Util.dataInvertida(etDtNascimento.getText().toString(), 1));
				clientes.setDtNascimento(etDtNascimento.getText().toString());
				clientes.setObsAgenda(etObs.getText().toString().trim().toUpperCase());
				clientes.setEmail(etEmail.getText().toString().trim().toUpperCase());
				clientes.setCodCidade(codCidade);
				Log.i(Constants.TAG, "salvaCadastro - dados");
				salvaCliente(clientes);
			}
		}else{
			Log.i(Constants.TAG, "salvaCadastro "+Constants.MSG_CAMPOS_BRANCO);
			Util.customAlertDialog(this, "Atenção", Constants.MSG_CAMPOS_BRANCO);
		}
	}

	//-----------------------------------------------
//	private int getIndexSpinner(Spinner spinner, String myString){
//        int index = 0;
//        for (int i=0;i<spinner.getCount();i++){
//              if (spinner.getItemAtPosition(i).toString().trim().equals(myString)){
//                index = i;
//                break;
//            }
//        }
//        return index;
//	}
	
	
	public void salvaCliente(Clientes clientes){
		
		Log.i(Constants.TAG, "clientes");
		Util.startDialog(CadastroActivity.this, "Salvando cadastro...", true);
		if(SettingsHelper.getUserLogado(CadastroActivity.this) != true)
		  (new PostClienteTask(CadastroActivity.this, clientes)).execute("I");
		else
		  (new PostClienteTask(CadastroActivity.this, clientes)).execute("E");
	}

	@Override
	public void ClienteResult(boolean result) {
		Log.i(Constants.TAG, "cliente result");
		Util.startDialog(CadastroActivity.this, "", false);
		if(result){
			Util.customAlertDialogReturn(CadastroActivity.this, "Atenção", "Cadastro salvo com sucesso!", 1);
		} else {
			Util.customAlertDialog(CadastroActivity.this, "Atenção", "ERRO");
			
		}
		
	}

	@Override
	public void CidadeResult(ArrayList<Cidades> result) {
		Log.i(Constants.TAG, "CidadeResult - inicio");

		listaCidades = result;
		//lista.clear();
		
		Util.startDialog(CadastroActivity.this, "", false);

		if(result != null){
			
			ArrayList<String> lista = new ArrayList<String>();
			for(Cidades cidade : result){
				lista.add(cidade.getNome());
			}
			
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CadastroActivity.this, android.R.layout.simple_spinner_item, lista);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spCidade.setAdapter(dataAdapter);
			if(Clientes.getCidade() != null){ 
				spCidade.setSelection(Util.getIndexSpinner(spCidade, Clientes.getCidade()));
			}	
			spCidade.setOnItemSelectedListener(this);
			
			
		} else {
			Log.i(Constants.TAG, "CidadeResult - erro");
			Util.customAlertDialogReturn(context, "Atenção", Constants.ERRO_DADOS_WEBSERVICE,1);

		}
		Log.i(Constants.TAG, "CidadeResult - fim");
		
	}

	@Override
	public void customAlertDialogReturnResult(int result) {
		if(result == 1) finish();
		
	}

	@Override
	public void customDialogResult(int result) {
//		if(result == 1) {
//			salvaCadastro();
//			Log.i(Constants.TAG, "TESTE - "+context);
//		}
		
	}

	@Override
	public void CustomAlertDialogsResult(int result, int retorno) {
		Log.i(Constants.TAG, "CustomAlertDialogsResult >>>>>>>>>>>>>>> "+result);
		if(retorno == Constants.RETURN_CADASTRO_CONSUMIDOR)
			if(result == Constants.RESULT_CONFIRMAR) salvaCadastro();
	}
	
	
}
