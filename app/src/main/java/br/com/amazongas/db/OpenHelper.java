package br.com.amazongas.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

class OpenHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "amgasconsumidor.db";
	private static final int DATABASE_VERSION = 1;

	OpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		Log.i("DB","Inicio Construtor");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("DB","OnCreate");
		this.createTables(db);
	}

	private void createTables(SQLiteDatabase db) {	
		Log.i("DB","Criando tabelas");
		// tabela de Bairros
		StringBuilder sbsql = new StringBuilder();
		sbsql.append("CREATE TABLE IF NOT EXISTS bairro (");
		sbsql.append(BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sbsql.append("codbairro INTEGER, ");
		sbsql.append("nomebairro VARCHAR(30), ");
		sbsql.append("estado VARCHAR(2), ");
		sbsql.append("codcidade VARCHAR(9) ");
		sbsql.append(");");
		db.execSQL(sbsql.toString());
		
		// tabela de Cidades
		StringBuilder sbsql2 = new StringBuilder();
		sbsql2.append("CREATE TABLE IF NOT EXISTS cidade (");
		sbsql2.append(BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sbsql2.append("nomecidade VARCHAR(35),");
		sbsql2.append("uf VARCHAR(2), ");
		sbsql2.append("codcidade VARCHAR(9), ");
		sbsql2.append("codibge VARCHAR(9) ");
		sbsql2.append(");");
		db.execSQL(sbsql2.toString());
		
		// tabela de Revendas
		StringBuilder sbsql3 = new StringBuilder();
		sbsql3.append("CREATE TABLE IF NOT EXISTS revenda (");
		sbsql3.append(BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sbsql3.append("nomelocalidade VARCHAR(50),");
		sbsql3.append("endereco VARCHAR(50),");
		sbsql3.append("bairro VARCHAR(30),");
		sbsql3.append("telefone1 VARCHAR(14),");
		sbsql3.append("telefone2 VARCHAR(14),");
		sbsql3.append("pontoreferencia VARCHAR(120),");
		sbsql3.append("codcidade VARCHAR(9), ");
		sbsql3.append("latitude VARCHAR(20), ");
		sbsql3.append("longitude VARCHAR(20)");
		sbsql3.append(");");
		db.execSQL(sbsql3.toString());
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("DB","Upgrade Tabelas");

		db.execSQL("DROP TABLE IF EXISTS bairro ");
		onCreate(db);
	}
}
