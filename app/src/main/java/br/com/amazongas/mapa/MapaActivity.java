package br.com.amazongas.mapa;

import java.util.List;

import br.com.amazongas.consumidor.R;
import br.com.amazongas.model.Clientes;
import br.com.amazongas.model.Revendas;
import br.com.amazongas.task.RevendaBairroDBTask;
import br.com.amazongas.task.RevendaBairroDBTask.RevendaBairroResultListener;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.SettingsHelper;
import br.com.amazongas.util.Util;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.GpsStatus.NmeaListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;


public class MapaActivity extends FragmentActivity implements RevendaBairroResultListener {
	private Context context = MapaActivity.this;
	
	 private GoogleMap mMap;
	 private List<Revendas> lista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapa);
	}

	@Override
    protected void onResume() {
        super.onResume();
        //setUpMapIfNeeded();
		Util.startDialog(context, "Carregando Mapa...", true);
		(new RevendaBairroDBTask((RevendaBairroResultListener) context)).execute(SettingsHelper.getUserBairro(context), SettingsHelper.getUserCodCidade(context));

        
    }
	
	 private void setUpMapIfNeeded() {
	        // Do a null check to confirm that we have not already instantiated the map.
	        if (mMap == null) {
	            // Try to obtain the map from the SupportMapFragment.
	            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
	                    .getMap();
	            // Check if we were successful in obtaining the map.
	            if (mMap != null) {
	                setUpMap();
	            }
	        }
	    }
	 
	    private void setUpMap() {
	        //mMap.addMarker(new MarkerOptions().position(new LatLng(-3.0594196, -60.031011)).title("Ducival").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        
	        mMap.addMarker(new MarkerOptions().position(new LatLng(-3.0594196, -60.031011))
	        		.title("COMSUMIDOR").snippet("Telefone: 2121-2525")
	        		.icon(BitmapDescriptorFactory.fromResource(R.drawable.consumidor_mapa)));

	        mMap.addMarker(new MarkerOptions().position(new LatLng(-3.05500, -60.0308))
	        		.title("MERCADINHO SANTOS DUMONT").snippet("Telefone: 2121-2525")
	        		.icon(BitmapDescriptorFactory.fromResource(R.drawable.botijamapa)));
	        
	        
	        mMap.addMarker(new MarkerOptions().position(new LatLng(-3.063430, -60.032559))
	        		.title("FRANCISCO GOMES DE MORAES MERCEARIA").snippet("Telefone: 2121-2525")
	        		.icon(BitmapDescriptorFactory.fromResource(R.drawable.botijamapa)));
	        

//	        mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(Clientes.getLatitude().trim().replace(",",".")), Double.parseDouble(Clientes.getLongitude().trim().replace(",",".")) ))
//    		.title(Clientes.getNome().trim()).snippet(Clientes.getTelefone().trim())
//    		.icon(BitmapDescriptorFactory.fromResource(R.drawable.consumidor_mapa)));
	    	
//	        for (Revendas rv : lista) {
//	        	Log.i("MAPA","Lat/log >>>> ");
//	        	//Log.i("MAPA","Lat/log >>>> "+rv.getLatitude());
////		        mMap.addMarker(new MarkerOptions().position(new LatLng( Double.parseDouble(rv.getLatitude().trim().replace(",",".")), Double.parseDouble(rv.getLongitude().trim().replace(",","."))))
////		        		.title(rv.getNomelocalidade().trim()).snippet(rv.getTelefone1().trim())
////		        		.icon(BitmapDescriptorFactory.fromResource(R.drawable.botijamapa)));
//			}
////	        
	        
	        //mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(-3.0594196, -60.031011)));

	        mMap.getUiSettings().setZoomControlsEnabled(false);
	        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-3.0594196, -60.031011), 15.5f));
	    }

		@Override
		public void RevendaBairroResult(List<Revendas> result) {
			Util.startDialog(context, "", false);
			lista = result;
	        for (Revendas rv : lista) {
	        	Log.i("MAPA","Lat/log >>>> "+rv.getNomelocalidade());
	        	Log.i("MAPA","Lat/log >>>>>>>>>> "+rv.getLatitude()+ " / "+rv.getLongitude());
			}
			setUpMapIfNeeded();
		}


}
