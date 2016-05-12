package br.com.amazongas.consumidor;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.VideoView;

public class InicioActivity extends Activity implements Runnable {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);
		Log.e("Consumidor","Activity Iniciada.");

		// aqui é definido o delay do handler em milisegundos
//		VideoView videoView = (VideoView) findViewById(R.id.videoView1);
//		String uri = "android.resource://" + getPackageName() + "/"	+ R.drawable.entrada1;
//		videoView.setVideoURI(Uri.parse(uri));
//		videoView.start();
//
//		Handler h = new Handler();
//		h.postDelayed(this, 5000);
		
		
		Log.e("Consumidor","Activity Finalizada.");
		inicio();
		
	}

	@Override
	public void run() {
		Log.e("Consumidor","Inicio do RUN");
		Intent segundatela = new Intent();
		segundatela.setClass(InicioActivity.this, PrincipalActivity.class);

		startActivity(segundatela);// aqui é iniciada nossa 2 activity
		finish();// aqui é chamado o método finish pra finalizar a activity
					// atual no caso SplashScreen
		Log.e("Consumidor","RUN Finalizada.");
	}
	
	public void inicio() {
		Log.e("Consumidor","Inicio do Iicio");
		Intent segundatela = new Intent();
		segundatela.setClass(InicioActivity.this, PrincipalActivity.class);

		startActivity(segundatela);// aqui é iniciada nossa 2 activity
		finish();// aqui é chamado o método finish pra finalizar a activity
					// atual no caso SplashScreen
		Log.e("Consumidor","RUN Finalizada.");
	}

}
