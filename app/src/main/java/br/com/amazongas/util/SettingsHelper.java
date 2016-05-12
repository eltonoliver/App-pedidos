package br.com.amazongas.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SettingsHelper {
	
    private static final String PREFS_NAME = "PREFS_NAME";
    private static final String userLogin = "LOGIN"; 					// Login do usuario
    private static final String userSenha = "SENHA"; 					// Senha do usuario
    private static final String userInicializacao = "INICIALIZACAO";  	// App inicializada pela primeira vez
    private static final String userLogado = "LOGADO";  				// Usuario logado
    private static final String userCodConsumidor = "CODCONSUMIDOR";  	// Codigo do consumidor
    private static final String userEstado = "ESTADO";  				// UF do consumidor
    private static final String userBairro = "BAIRRO";  				// nome do bairro
    private static final String userCodcidade = "CODCIDADE";  			// Código da cidade
    private static final String userTipoUsuario = "TIPOUSUARIO";  		// Tipo de usuario (F-Pessoa Fisica, J- Pessoa Juridica
    private static final String userNomeUsuario = "NOMEUSUARIO";  		// Nome do Usuário
    private static final String userCodBairro = "CODBAIRRO";  			// Codigo do bairro
    private static final String userIniCidade = "INICIDADE";  			// Inicializa Cidade
    private static final String userIniBairro = "INIBAIRRO";  			// Inicializa Bairros
    private static final String userIniRevenda = "INIREVENDA";  		// Inicializa Revenda
    
    

    public static void setUserLogin(Context context, String valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(userLogin, valor);
        editor.commit();
    }

    public static String getUserLogin(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(userLogin, "");
    }
    //-----------------------------------------------------------------------------
    
    public static void setUserSenha(Context context, String valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(userSenha, valor);
        editor.commit();
    }

    public static String getUserSenha(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(userSenha, "");
    }
    //-----------------------------------------------------------------------------
    
    public static void setUserInicializacao(Context context, boolean valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(userInicializacao, valor);
        editor.commit();
    }

    public static boolean getUserInicializacao(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getBoolean(userInicializacao, false);
    }
    //-----------------------------------------------------------------------------
    
    public static void setUserLogado(Context context, boolean valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(userLogado, valor);
        editor.commit();
    }

    public static boolean getUserLogado(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getBoolean(userLogado, false);
    }
    //-----------------------------------------------------------------------------

    public static void setUserCodConsumidor(Context context, int valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(userCodConsumidor, valor);
        editor.commit();
    }

    public static int getUserCodConsumidor(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getInt(userCodConsumidor, 0);
    }
    //-----------------------------------------------------------------------------
    
    public static void setUserEstado(Context context, String valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(userEstado, valor);
        editor.commit();
    }

    public static String getUserEstado(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(userEstado, "");
    }
    //-----------------------------------------------------------------------------
    
    public static void setUserBairro(Context context, String valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(userBairro, valor);
        editor.commit();
    }

    public static String getUserBairro(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(userBairro, "");
    }
    //-----------------------------------------------------------------------------
    
    public static void setUserCodCidade(Context context, String valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(userCodcidade, valor);
        editor.commit();
    }

    public static String getUserCodCidade(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(userCodcidade, "");
    }
    //-----------------------------------------------------------------------------

    public static void setUserTipoUsuario(Context context, String valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(userTipoUsuario, valor);
        editor.commit();
    }

    public static String getUserNomeUsuario(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(userNomeUsuario, "");
    }
    //-----------------------------------------------------------------------------
    public static void setUserNomeUsuario(Context context, String valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(userNomeUsuario, valor);
        editor.commit();
    }

    public static String getUserTipoUsuario(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(userTipoUsuario, "");
    }
    //-----------------------------------------------------------------------------
    public static void setUserCodBairro(Context context, int valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(userCodBairro, valor);
        editor.commit();
    }

    public static int getUserCodBairro(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getInt(userCodBairro, 0);
    }
    //-----------------------------------------------------------------------------
    public static void setUserIniCidade(Context context, boolean valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(userIniCidade, valor);
        editor.commit();
    }

    public static boolean getUserIniCidade(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getBoolean(userIniCidade, false);
    }
    //-----------------------------------------------------------------------------
    public static void setUserIniBairro(Context context, boolean valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(userIniBairro, valor);
        editor.commit();
    }

    public static boolean getUserIniBairro(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getBoolean(userIniBairro, false);
    }
    //-----------------------------------------------------------------------------
    public static void setUserIniRevenda(Context context, boolean valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(userIniRevenda, valor);
        editor.commit();
    }

    public static boolean getUserIniRevenda(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getBoolean(userIniRevenda, false);
    }
    //-----------------------------------------------------------------------------
    
}
