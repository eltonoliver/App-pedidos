package br.com.amazongas.consumidor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FaceBookActivity extends Activity {
    private WebView webview;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facebook);
		
		webview = (WebView) findViewById(R.id.wv_Mapa);
		
//		Intent it = getIntent();
//		Bundle bd = it.getExtras();
//		if(bd !=null){
//			latitude = bd.getString("latitude");
//			longitude = bd.getString("longitude");
//			
			MapaTask mapa = new MapaTask(this);
			mapa.execute();
//		}else {
//			Toast.makeText(getApplicationContext(), "Dados para consulta do mapa não encontrados.", Toast.LENGTH_LONG).show();
//			finish();
//		}
		

	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.mapa, menu);
//		return true;
//	}

	class MapaTask extends AsyncTask<Void, Void, Void>{
		private ProgressDialog progressDialog;
		private Context context;
		
		public MapaTask(Context context){
			this.context = context;
		}
		
		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(context);
			progressDialog.setMessage("Consultando...");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
		    webview.getSettings().setJavaScriptEnabled(true);
		   // webview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
		    webview.setInitialScale(100);
		    webview.setWebViewClient(new MyWebViewClient());
		    //webview.loadUrl("http://www.amazongas.com.br/mapas/mapa.php?lati=-3.1023753&longi=-59.9610854");
		    webview.loadUrl("https://www.facebook.com/pages/AmazonG%C3%A1s/524410100938152?ref=hl");
			return null;
		}
		
		private class MyWebViewClient extends WebViewClient{
			@Override
			public void onPageFinished(WebView view, String url) {
				progressDialog.dismiss();
			}
		}
	}
	
}
