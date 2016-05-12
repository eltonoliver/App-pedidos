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
import br.com.amazongas.model.LocalidadesCliente;
import br.com.amazongas.model.Pedido;
import br.com.amazongas.model.Revendas;
import br.com.amazongas.model.RevendasBairro;



public class LocalidadesClienteAdapter extends BaseAdapter {
	
	private List<LocalidadesCliente> lista;
	private LayoutInflater mInflater;
	private ViewHolder holder;
	private NumberFormat formato = new DecimalFormat("##0.00");
	
	static class ViewHolder{
		private TextView tvNome, tvEndereco, tvBairro, tvTelefone;
	}
	
	public LocalidadesClienteAdapter(Context context, List<LocalidadesCliente> lista) {
		mInflater = LayoutInflater.from(context);
		this.lista = lista;
	}
	
	@Override
	public int getCount() {
		return lista.size();
	}
	
	@Override
	public LocalidadesCliente getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int posicao, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_localidades_cliente, null);
			
			holder = new ViewHolder();
			holder.tvNome = (TextView) convertView.findViewById(R.id.tvLocalidadesClienteNome);
			holder.tvEndereco = (TextView) convertView.findViewById(R.id.tvLocalidadesClienteEndereco);
			holder.tvBairro = (TextView) convertView.findViewById(R.id.tvLocalidadesClienteBairro);
			holder.tvTelefone = (TextView) convertView.findViewById(R.id.tvLocalidadesClienteTelefone);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
 
			holder.tvNome.setText(lista.get(posicao).getNome());
			holder.tvEndereco.setText(lista.get(posicao).getEndereco());
			holder.tvBairro.setText(lista.get(posicao).getBairro());
			holder.tvTelefone.setText(lista.get(posicao).getTelefone());
 
		return convertView;
	}


		
}
