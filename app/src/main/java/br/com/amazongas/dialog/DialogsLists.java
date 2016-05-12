package br.com.amazongas.dialog;

import java.util.List;
import java.util.zip.Inflater;

import br.com.amazongas.adapter.ListaRevendaDetalheAdapter;
import br.com.amazongas.adapter.LocalidadesClienteAdapter;
import br.com.amazongas.consumidor.R;
import br.com.amazongas.model.LocalidadesCliente;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;
import br.com.amazongas.util.Util.CustomAlertDialogReturnListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DialogsLists {
	
	public interface CustomDialogsListsResultListener{
		public void CustomDialogsListsResult(int resultlist, int retornoint, String retornostring);
	}
	
	
	private static AlertDialog alerta;
	private static CustomDialogsListsResultListener listener;
	
	    
    // Dialogs Lista Localidades Cliente
    public static void listaLocalidadesCliente(Context context, List<LocalidadesCliente> lista) {
    	//listener =  (CustomDialogsListsResultListener) context;
    	
    	//listener.CustomDialogsListsResult(0, 1, "2");
    	
		final Dialog dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Dialog);
	    dialog.setContentView(R.layout.activity_dialog_lista);
	    
	    final ListView lvLista = (ListView) dialog.findViewById(R.id.lvDialogsLista);
	    final ImageView btVoltar = (ImageView) dialog.findViewById(R.id.btDialogsListaVoltar);
	    
	    LocalidadesClienteAdapter adapter = new LocalidadesClienteAdapter(context, lista);
	    lvLista.setAdapter(adapter);	    
	    

	    btVoltar.setOnClickListener(new OnClickListener() {
	    	
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	    
	    dialog.show();
	    
    }
  
    
    
        

    
}
