package br.com.amazongas.consumidor;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class ListaSimplesActivity extends Activity {
	private ListView listasimples;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_simples);
		listasimples = (ListView) findViewById(R.id.lvListaSimples);
		
		
		
	}

	
}
