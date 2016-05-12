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
import br.com.amazongas.model.HistoricoPedidoItens;
import br.com.amazongas.model.Pedido;
import br.com.amazongas.model.Revendas;
import br.com.amazongas.model.RevendasBairro;



public class ListaHistoricoPedidoItemPJAdapter extends BaseAdapter {
	
	private List<HistoricoPedidoItens> lista;
	private LayoutInflater mInflater;
	private ViewHolder holder;
	private NumberFormat formato = new DecimalFormat("##0.00");
	
	static class ViewHolder{
		private TextView tvProduto, tvQtd, tvValor, tvTotal;
	}
	
	public ListaHistoricoPedidoItemPJAdapter(Context context, List<HistoricoPedidoItens> lista) {
		mInflater = LayoutInflater.from(context);
		this.lista = lista;
	}
	
	@Override
	public int getCount() {
		return lista.size();
	}
	
	@Override
	public HistoricoPedidoItens getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int posicao, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_lista_historico_pedido_item_pj, null);
			
			holder = new ViewHolder();
			holder.tvProduto = (TextView) convertView.findViewById(R.id.tvHistoricoPedidoItemPJNomeProduto);
			holder.tvQtd = (TextView) convertView.findViewById(R.id.tvHistoricoPedidoItemPJQtd);
//			holder.tvValor = (TextView) convertView.findViewById(R.id.tvHistoricoItemValor);
//			holder.tvTotal = (TextView) convertView.findViewById(R.id.tvHistoricoItemTotal);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
 
			holder.tvProduto.setText(lista.get(posicao).getNome().toString());
			holder.tvQtd.setText(String.valueOf(lista.get(posicao).getQtd()));
//			holder.tvValor.setText(formato.format(lista.get(posicao).getPreco()));
//			holder.tvTotal.setText(formato.format(lista.get(posicao).getTotal()));
 
		return convertView;
	}


		
}
