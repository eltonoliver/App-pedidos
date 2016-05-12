package br.com.amazongas.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import br.com.amazongas.db.BDDataManager;
import br.com.amazongas.util.Constants;

public class SalvaBairroDBTask extends AsyncTask<String, Void, Boolean> {
	
	public interface SalvaBairroDBResultListener{
		public void SalvaBairroDBResult(boolean result);
	}
	
	private SalvaBairroDBResultListener listener;
	private Context context;

	
	public SalvaBairroDBTask(SalvaBairroDBResultListener listener) {
		this.listener = listener;
		this.context = (Context) listener;
	}

	@Override
	protected Boolean doInBackground(String... params) {
		Log.i(Constants.TAG, "SalvaBairroDBTask doinbackground >>>>>>>>>>");
		BDDataManager bd = new BDDataManager(context);
		return bd.insereBairrosManaus();
			
	}

	@Override
	protected void onPostExecute(Boolean result) {
		Log.i(Constants.TAG, "SalvaBairroDBTask onPostExecute >>>>>>>>>>");
		listener.SalvaBairroDBResult(result);
	}
	
	

}
