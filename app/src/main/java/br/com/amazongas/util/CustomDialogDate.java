package br.com.amazongas.util;


import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

public class CustomDialogDate {
	private int year, month, day;
	private Context context;
	private DatePickerDialog dpd;
	
	public interface CustomDialogDateListener {
		  public void onCustomDialogDateResultListener(String result);
	}
		
	private CustomDialogDateListener listener;
	
	public CustomDialogDate(Context context, int day, int month, int year){
		this.context = context;
		this.year = year;
		this.month = (month-1);
		this.day = day;
		this.listener = (CustomDialogDateListener) context;
	}
	
	
	public void showDateDialog(){
		dpd = new DatePickerDialog(context, datePickerListener, year, month,day);
		dpd.show();
	}
	
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		 
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			String dataResult = String.format("%02d",selectedDay)+"/"+String.format("%02d",selectedMonth+1)+"/"+String.format("%02d",selectedYear); 
			listener.onCustomDialogDateResultListener(dataResult);
		}
		
	};	
	
	
	
	


}
