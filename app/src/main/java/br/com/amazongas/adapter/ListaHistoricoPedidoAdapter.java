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



public class ListaHistoricoPedidoAdapter extends BaseAdapter {
	
	private List<HistoricoPedido> lista;
	private LayoutInflater mInflater;
	private ViewHolder holder;
	private NumberFormat formato = new DecimalFormat("##0.00");
	
	static class ViewHolder{
		private TextView tvCodigo, tvData, tvHora, tvStatus, tvQtd, tvTotal, tvRevenda, tvTelefone;
	}
	
	public ListaHistoricoPedidoAdapter(Context context, List<HistoricoPedido> lista) {
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
			convertView = mInflater.inflate(R.layout.adapter_lista_historico_pedido, null);
			
			holder = new ViewHolder();
			holder.tvCodigo = (TextView) convertView.findViewById(R.id.tvHistoricoNoPedido);
			holder.tvData = (TextView) convertView.findViewById(R.id.tvHistoricoData);
			holder.tvHora = (TextView) convertView.findViewById(R.id.tvHistoricoHora);
			holder.tvStatus = (TextView) convertView.findViewById(R.id.tvHistoricoStatus);
//			holder.tvQtd = (TextView) convertView.findViewById(R.id.tvHistoricoQtd);
//			holder.tvTotal = (TextView) convertView.findViewById(R.id.tvHistoricoTotal);
			holder.tvRevenda = (TextView) convertView.findViewById(R.id.tvHistoricoRevenda);
			holder.tvTelefone = (TextView) convertView.findViewById(R.id.tvHistoricoTelefone);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
 
			holder.tvCodigo.setText(String.valueOf(lista.get(posicao).getCodigo()));
			holder.tvData.setText(lista.get(posicao).getData());
			holder.tvHora.setText(lista.get(posicao).getHora());
			holder.tvStatus.setText(lista.get(posicao).getStatus());
//			holder.tvQtd.setText(String.valueOf(lista.get(posicao).getQtd()));
//			holder.tvTotal.setText(formato.format(lista.get(posicao).getTotal()));
			holder.tvRevenda.setText(lista.get(posicao).getRevenda());
			holder.tvTelefone.setText(lista.get(posicao).getTelefone());
 
		return convertView;
	}


		
}
