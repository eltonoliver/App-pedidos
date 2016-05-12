package br.com.amazongas.dialog;

import br.com.amazongas.consumidor.R;
import br.com.amazongas.consumidor.R.layout;
import br.com.amazongas.consumidor.R.menu;
import br.com.amazongas.util.Constants;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class DialogPFPJActivity extends Activity {
	private ImageView ivPF, ivPJ, ivVoltar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_pfpj);
		
		ivPF = (ImageView) findViewById(R.id.btPF);
		ivPJ = (ImageView) findViewById(R.id.btPJ);
		ivVoltar = (ImageView) findViewById(R.id.btPJPFVoltar);
		
		ivPF.setOnClickListener(ivPFClick);
		ivPJ.setOnClickListener(ivPJClick);
		ivVoltar.setOnClickListener(ivVoltarClick);
		 
		
	}
	
	private OnClickListener ivPFClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent data = new Intent();
		//	data.putExtra("resultado", Constants.RESULT_LOGIN_PF);
			setResult(RESULT_OK, data);
			finish();
		}
	};
	
	private OnClickListener ivPJClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent data = new Intent();
			//data.putExtra("resultado", Constants.RESULT_LOGIN_PJ);
			setResult(RESULT_OK, data);
			finish();
		}
	};
	
	private OnClickListener ivVoltarClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			setResult(RESULT_CANCELED);
			finish();
		}
	};
	

}
