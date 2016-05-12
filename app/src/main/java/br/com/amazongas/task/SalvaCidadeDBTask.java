package br.com.amazongas.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import br.com.amazongas.db.BDDataManager;
import br.com.amazongas.util.Constants;

public class SalvaCidadeDBTask extends AsyncTask<String, Void, Boolean> {
	
	public interface SalvaCidadeDBResultListener{
		public void SalvaCidadeDBResult(boolean result);
	}
	
	private SalvaCidadeDBResultListener listener;
	private Context context;

	
	public SalvaCidadeDBTask(SalvaCidadeDBResultListener listener) {
		this.listener = listener;
		this.context = (Context) listener;
	}

	@Override
	protected Boolean doInBackground(String... params) {
		Log.i(Constants.TAG, "SalvaCidadeDBTask doinbackground >>>>>>>>>>");
		BDDataManager bd = new BDDataManager(context);
		return bd.insereCidadeManaus();
			
	}

	@Override
	protected void onPostExecute(Boolean result) {
		Log.i(Constants.TAG, "SalvaCidadeDBTask onPostExecute >>>>>>>>>>"+result);
		listener.SalvaCidadeDBResult(result);
	}
	
	

}
