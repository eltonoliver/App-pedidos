package br.com.amazongas.task;

import java.util.List;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import br.com.amazongas.db.BDDataManager;
import br.com.amazongas.model.RevendasBairro;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;


public class RevendaCidadeDBTask extends AsyncTask<String, Void, List<RevendasBairro>> {
	
	public interface RevendaCidadeResultListener{
		public void RevendaCidadeResult(List<RevendasBairro> result);
	}
	
	private RevendaCidadeResultListener listener;
	private List<RevendasBairro> hc = null;
	private Context context;

	
	public RevendaCidadeDBTask(RevendaCidadeResultListener listener) {
		this.listener = listener;
		this.context = (Context) listener;
	}

	@Override
	protected List<RevendasBairro> doInBackground(String... params) {
		String sUf = params[0];
		try{
		  BDDataManager bd = new BDDataManager(context);
		  hc = bd.selectRevendasCidade(sUf);
		} catch (Exception e) {
			e.printStackTrace();
			Statics.mensagemErro = Constants.ERRO_DADOS_CRUD;
		}
		
		return hc;	
			
	}

	@Override
	protected void onPostExecute(List<RevendasBairro> result) {
		Log.e(Constants.TAG, "BairroTask onPostExecute RESULT - "+result);
		listener.RevendaCidadeResult(result);
	}

}
