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
import br.com.amazongas.model.Revendas;
import br.com.amazongas.model.RevendasBairro;



public class ListaRevendaDetalheAdapter extends BaseAdapter {
	
	private List<Revendas> lista;
	private LayoutInflater mInflater;
	private ViewHolder holder;
	
	static class ViewHolder{
		private TextView tvRevenda, tvTelefone;
	}
	
	public ListaRevendaDetalheAdapter(Context context, List<Revendas> lista) {
		mInflater = LayoutInflater.from(context);
		this.lista = lista;
	}
	
	@Override
	public int getCount() {
		return lista.size();
	}
	
	@Override
	public Revendas getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int posicao, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_lista_revenda_detalhe, null);
			
			holder = new ViewHolder();
			holder.tvRevenda = (TextView) convertView.findViewById(R.id.tvRevendaDetalheAdapter);
			holder.tvTelefone = (TextView) convertView.findViewById(R.id.tvTelefoneDetalheAdapter);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
 
 
		holder.tvRevenda.setText(lista.get(posicao).getNomelocalidade().toString());
		holder.tvTelefone.setText(lista.get(posicao).getTelefone1().toString());
 
		return convertView;
	}


		
}
