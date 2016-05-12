package br.com.amazongas.util;

public class Constants {

	// PostResultListener
	public static final int POST_ENVIAR_PEDIDO = 1;

	
	// WebService--------------------------------------------------------------------------------------------
//	public static final String URL = "http://www.amazongas.com.br/rest/api.php"; // http://www.amazongas.com.br/rest/api.php?rquest=listalocalidadecliente_consumidor
	public static final String URL = "http://www.amazongas.com.br/ws/consumidor/api_consumidor.php"; // http://www.amazongas.com.br/rest/api.php?rquest=listalocalidadecliente_consumidor
	public static final int CONN_TIMEOUT = 15000;
	public static final int SOCKET_TIMEOUT = 15000;	

    //Erros--------------------------------------------------------------------------------------------
	public static final String ERRO_CONEXAO_INTERNET = "Sem conexão com a internet!";
	public static final String ERRO_DADOS_WEBSERVICE = "Erro ao acessar dados da web. Verifique sua conexão com a internet.";
	public static final String ERRO_DADOS_LOGIN = "Verifique se seus dados estão corretos e tente novamente";
	public static final String ERRO_DADOS_PARSE = "Erro na tabulação dos dados. Tente novamente.";
	public static final String ERRO_DADOS_TASK = "Erro na execução do processo. Tente novamente.";
	public static final String ERRO_DADOS_CRUD = "Erro na atualização dos dados!";
	public static final String ERRO_SERIAL_APARELHO = "Erro de Incompatibilidade!";
	public static final String ERRO_EXTERNAL_STORAGE = "Erro ao acessar memoria externa!";
	public static final String ERRO_EXPIROU_SESSAO = "Expirou a Sessão. Efetue o LOGIN";
	public static final String ERRO_INICIALIZACAO_DADOS = "Erro na inicialização dos dados!";
	
	
	//Mensagens--------------------------------------------------------------------------------------------
	public static final String MSG_SEM_DADOS = "Sem dados a serem retornados.";
	public static final String MSG_SEM_LOGIN = "Erro de acesso. Verifique os dados informados!";
	public static final String MSG_SEM_PEDIDOS_RECEBIDOS = "Sem PEDIDOS RECEBIDOS!";
	public static final String MSG_SEM_PEDIDOS_PENDENTES = "Sem PEDIDOS PENDENTES!";
	public static final String MSG_SEM_PEDIDOS_ENTREGUES = "Sem PEDIDOS ENTREGUES!";
	public static final String MSG_PEDIDO_SELECIONADO_REVENDEDOR = "Pedido já selecionado por outro revendedor!";
	public static final String MSG_APP_ATUALIZA_VERSAO = "Existe uma nova versão do Aplicativo. Instalar?";
	public static final String MSG_APP_CONFIRMA_OPCAO = "Confirma a opção selecionada?";
	public static final String MSG_PEDIDOGAS_OK = "Pedido de Gás Enviado com Sucesso!";
	public static final String MSG_LOGIN_BRANCO = "CNPJ/CPF ou Telefone em branco!";
	public static final String MSG_CAMPOS_BRANCO = "Existe(m) campo(s) em branco. Favor preencher.";
	public static final String MSG_DATA_INVALIDA = "Data Inválida!";
	public static final String MSG_CPFCNPJ_INVALIDO = "CPF/CNPJ inválido!";
	public static final String MSG_CONFIRMA_DADOS = "Confirma os dados?          ";
	
	// Loops--------------------------------------------------------------------------------------------
	public static final int LOOP_TASK = 3;
	public static final int LOOP_TASK_SERVICE = 10;
	
	// Versão do Aplicativo--------------------------------------------------------------------------------------------
	public static final String APP_VERSAO = "1.2";
	
	// GCM--------------------------------------------------------------------------------------------
	//public static final String NUMERO_PROJETO = "967869441095";
	
	// Chave do projeto definido no Google APIs para uso do GCM
    public static final String SENDER_ID = "163660726128"; // "967869441095"; //"163660726128";
    
	// API KEY SERVER do GCM
	public static final String NUMERO_API_KEY_SERVER = "AIzaSyDJSRCRSzU-7s02mA6XckFtsoesQBVa-4U"; //"AIzaSyDtYK_8LNgJGhMX9HQl-onnNNCZr8fLe6s"; //"AIzaSyDtYK_8LNgJGhMX9HQl-onnNNCZr8fLe6s";
	
	
	// Activity for Result Request ------------------------------------------------------------------------------
	public static final int REQUEST_LOGIN = 1;
    	
	// Activity for Result Result------------------------------------------------------------------------------
//	public static final int RESULT_LOGIN_PF = 101;
//	public static final int RESULT_LOGIN_PJ = 102;
//	public static final int RESULT_LOGIN_VOLTAR = 103;
	
	
	// Custom Result--------------------------------------------------------------------------------------------
	public static final int RESULT_PF = 201;
	public static final int RESULT_PJ = 202;
	public static final int RESULT_VOLTAR = 203;
	public static final int RESULT_CONFIRMAR = 204;
	public static final int RESULT_CANCELAR = 205;
	public static final int RESULT_BT1 = 206;
	public static final int RESULT_BT2 = 207;

	// Custom Return--------------------------------------------------------------------------------------------
	public static final int RETURN_LOGOUT= 301;
	public static final int RETURN_CADASTRO_CONSUMIDOR= 302;
	public static final int RETURN_SEM_RETORNO= 99999;
	public static final int RETURN_OBTER_SENHA= 303;
	public static final int RETURN_SAIR_TELA= 304;
	public static final int RETURN_PEDIDO_CONSUMIDOR= 305;
	public static final int RETURN_AGENDAMENTO_GRANEL= 306;
	public static final int RETURN_PEDIDO_CONSUMIDOR_PJ= 307;
	
	// Custom Request_Code --------------------------------------------------------------------------------------------
		public static final int REQUEST_CODE_LOCALIDADES_CLIENTE= 401;
		
	 	
		
	// Outros--------------------------------------------------------------------------------------------
	public static final String TAG = "Consumidor";
	

}
