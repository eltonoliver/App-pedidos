package br.com.amazongas.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.amazongas.consumidor.R;
import br.com.amazongas.model.Cidades;



public class ListaSimplesImagemAdapter extends BaseAdapter {
	
	private List<String> lista;
	private LayoutInflater mInflater;
	private ViewHolder holder;
	private int pagina;
	
	static class ViewHolder{
		private TextView tvDescricao;
	}
	
	public ListaSimplesImagemAdapter(Context context, List<String> lista, int pagina) {
		mInflater = LayoutInflater.from(context);
		this.lista = lista;
		this.pagina = pagina;
	}
	
	@Override
	public int getCount() {
		return lista.size();
	}
	
	@Override
	public String getItem(int index) {
		return lista.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int posicao, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_lista_simples, null);
			
			holder = new ViewHolder();
			holder.tvDescricao = (TextView) convertView.findViewById(R.id.tvDescricao);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
 
 
		holder.tvDescricao.setText(lista.get(posicao));
 
		/*if(p.getStatus().equals("ENTREGUE")){
			holder.tvNome.setTextColor(Color.parseColor("#006400"));
			holder.tvData.setTextColor(Color.parseColor("#006400"));
			holder.tvStatus.setTextColor(Color.parseColor("#006400"));
			holder.tvPedido.setTextColor(Color.parseColor("#006400"));
		} else 
			if(p.getStatus().equals("PENDENTE")){
				holder.tvNome.setTextColor(Color.parseColor("#FF0000"));
				holder.tvData.setTextColor(Color.parseColor("#FF0000"));
				holder.tvStatus.setTextColor(Color.parseColor("#FF0000"));
				holder.tvPedido.setTextColor(Color.parseColor("#FF0000"));
			} else {
				holder.tvNome.setTextColor(Color.parseColor("#000000"));
				holder.tvData.setTextColor(Color.parseColor("#000000"));
				holder.tvStatus.setTextColor(Color.parseColor("#000000"));
				holder.tvPedido.setTextColor(Color.parseColor("#000000"));
			}*/
			
		//holder.tvStatus.setText("");

		return convertView;
	}

		
}
