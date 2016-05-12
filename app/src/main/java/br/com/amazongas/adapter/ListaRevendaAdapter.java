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
import br.com.amazongas.model.RevendasBairro;



public class ListaRevendaAdapter extends BaseAdapter {
	
	private List<RevendasBairro> lista;
	private LayoutInflater mInflater;
	private ViewHolder holder;
	
	static class ViewHolder{
		private TextView tvDescricao, tvQtd;
	}
	
	public ListaRevendaAdapter(Context context, List<RevendasBairro> lista) {
		mInflater = LayoutInflater.from(context);
		this.lista = lista;
	}
	
	@Override
	public int getCount() {
		return lista.size();
	}
	
	@Override
	public RevendasBairro getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int posicao, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_lista_revenda, null);
			
			holder = new ViewHolder();
			holder.tvDescricao = (TextView) convertView.findViewById(R.id.tvDescricaoAdapterRevenda);
			holder.tvQtd = (TextView) convertView.findViewById(R.id.tvQtdAdapterRevenda);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
 
 
		holder.tvDescricao.setText(lista.get(posicao).getBairro().toString());
		holder.tvQtd.setText(String.valueOf(lista.get(posicao).getQtd()));
 
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
