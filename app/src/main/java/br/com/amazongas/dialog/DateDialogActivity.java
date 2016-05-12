package br.com.amazongas.dialog;

import br.com.amazongas.consumidor.R;
import br.com.amazongas.consumidor.R.layout;
import br.com.amazongas.consumidor.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DateDialogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_dialog);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.date_dialog, menu);
		return true;
	}

}
