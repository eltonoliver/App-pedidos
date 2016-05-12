package br.com.amazongas.consumidor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;


public class MapaCidadesActivity extends Activity {
	private ImageView ivBoaVista, ivManaus, ivRioBranco, ivPortoVelho;
	private Intent it = new Intent();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapa_cidades);

		Log.e("Consumidor","Mapa Iniciado");
		
		ivBoaVista = (ImageView) findViewById(R.id.ivBoaVista);
		ivBoaVista.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		
		ivManaus = (ImageView) findViewById(R.id.ivManaus);
		ivManaus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//it.setClass(MapaCidadesActivity.this, PrincipalActivity.class);
				//startActivity(it);
				Intent it = new Intent();
				it.putExtra("codcidade","130.260-4");
				it.putExtra("uf","AM");
				setResult(1, it);
				finish();
			}
		});
		
		ivRioBranco = (ImageView) findViewById(R.id.ivRioBranco);
		ivRioBranco.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		
		ivPortoVelho = (ImageView) findViewById(R.id.ivPortoVelho);
		ivPortoVelho.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		Log.e("Consumidor","Mapa Finalizado");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mapa_cidades, menu);
		return true;
	}
	
	
}
