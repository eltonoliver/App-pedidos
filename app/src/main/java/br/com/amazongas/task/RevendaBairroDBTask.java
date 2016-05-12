package br.com.amazongas.task;

import java.util.List;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import br.com.amazongas.db.BDDataManager;
import br.com.amazongas.model.Revendas;
import br.com.amazongas.model.RevendasBairro;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;


public class RevendaBairroDBTask extends AsyncTask<String, Void, List<Revendas>> {
	
	public interface RevendaBairroResultListener{
		public void RevendaBairroResult(List<Revendas> result);
	}
	
	private RevendaBairroResultListener listener;
	private List<Revendas> hc = null;
	private Context context;

	
	public RevendaBairroDBTask(RevendaBairroResultListener listener) {
		this.listener = listener;
		this.context = (Context) listener;
	}

	@Override
	protected List<Revendas> doInBackground(String... params) {
		String sBairro    = params[0];
		String sCodCidade = params[1];
		Log.i("teste","Detalhes TTTT >>>>>>>>> b-"+sBairro+" / c-"+sCodCidade);
		
		try{
		  BDDataManager bd = new BDDataManager(context);
		  hc = bd.selectRevendasBairro(sBairro, sCodCidade);
		} catch (Exception e) {
			e.printStackTrace();
			Statics.mensagemErro = Constants.ERRO_DADOS_CRUD;
		}
		
		return hc;	
			
	}

	@Override
	protected void onPostExecute(List<Revendas> result) {
		Log.e(Constants.TAG, "BairroTask onPostExecute RESULT - "+result);
		listener.RevendaBairroResult(result);
	}

}
