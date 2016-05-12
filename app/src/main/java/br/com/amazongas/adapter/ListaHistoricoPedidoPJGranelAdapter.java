package br.com.amazongas.adapter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.amazongas.consumidor.R;
import br.com.amazongas.model.Cidades;
import br.com.amazongas.model.HistoricoPedido;
import br.com.amazongas.model.Pedido;
import br.com.amazongas.model.Revendas;
import br.com.amazongas.model.RevendasBairro;



public class ListaHistoricoPedidoPJGranelAdapter extends BaseAdapter {
	
	private List<HistoricoPedido> lista;
	private LayoutInflater mInflater;
	private ViewHolder holder;
	private NumberFormat formato = new DecimalFormat("##0.00");
	
	static class ViewHolder{
		private TextView tvCodigo, tvData, tvEntrega;
	}
	
	public ListaHistoricoPedidoPJGranelAdapter(Context context, List<HistoricoPedido> lista) {
		mInflater = LayoutInflater.from(context);
		this.lista = lista;
	}
	
	@Override
	public int getCount() {
		return lista.size();
	}
		
	@Override
	public HistoricoPedido getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int posicao, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_lista_historico_pedido_pj_granel, null);
			
			holder = new ViewHolder();
			holder.tvCodigo = (TextView) convertView.findViewById(R.id.tvHistoricoPJGranelNoPedido);
			holder.tvData = (TextView) convertView.findViewById(R.id.tvHistoricoPJGranelData);
			holder.tvEntrega = (TextView) convertView.findViewById(R.id.tvHistoricoPJGranelEntrega);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
 
			holder.tvCodigo.setText(String.valueOf(lista.get(posicao).getCodigo()));
			holder.tvData.setText(lista.get(posicao).getData());
			holder.tvEntrega.setText(lista.get(posicao).getEntrega());
 
		return convertView;
	}


		
}
