package com.example.gkaakash;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.gkaakash.controller.Startup;
import com.gkaakash.controller.Transaction;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class SearchVoucher extends Activity  {
	int textlength=0;
	Context context = SearchVoucher.this;
	AlertDialog dialog;
	DecimalFormat mFormat;
	private Transaction transaction;
	static Integer client_id;
	static ArrayList<ArrayList<String>> searchedVoucherGrid;
	static ArrayList<String> searchedVoucherList;
	TableLayout vouchertable;
    TableRow tr;
    TextView label;
    static String financialFromDate;
    static String financialToDate;
	
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	        setContentView(R.layout.search_voucher);
	        
	        client_id = Startup.getClient_id();
	        transaction = new Transaction();
	        
	        //for two digit format date for dd and mm
			mFormat= new DecimalFormat("00");
			mFormat.setRoundingMode(RoundingMode.DOWN);
			
			financialFromDate =Startup.getfinancialFromDate(); 
			financialToDate = Startup.getFinancialToDate();
			
			TextView tvVFromdate = (TextView) findViewById( R.id.tvVFromdate );
		    TextView tvVTodate = (TextView) findViewById( R.id.tvVTodate );
		      
		    tvVFromdate.setText("Financial from date: " +financialFromDate);
		    tvVTodate.setText("Financial to date: " +financialToDate);
			
	        setOnSearchButtonClick();
	        
	        Object[] params = new Object[]{2,"",financialFromDate,financialToDate,""};
	        getallvouchers(params);
	        
		}

	private void setOnSearchButtonClick() {
		
		
		Button btnSearchVoucher = (Button)findViewById(R.id.btnSearchVoucher);
        btnSearchVoucher.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
				View layout = inflater.inflate(R.layout.search_voucher_by, (ViewGroup) findViewById(R.id.timeInterval));
				//Building DatepPcker dialog
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setView(layout);
				builder.setTitle("Search voucher by,");
				
				 	   	
			   	String dateParts[] = financialFromDate.split("-");
			   	String setfromday  = dateParts[0];
			   	String setfrommonth = dateParts[1];
			   	String setfromyear = dateParts[2];
			   	
			   	
			   	String dateParts1[] = financialToDate.split("-");
			   	String settoday  = dateParts1[0];
			   	String settomonth = dateParts1[1];
			   	String settoyear = dateParts1[2];
				
			   	final String fromdate = mFormat.format(Double.valueOf(setfromday))+ "-" 
					   	+(mFormat.format(Double.valueOf(Integer.parseInt((mFormat.format(Double.valueOf(setfrommonth))))+ 1))) + "-" 
					   	+ setfromyear; 
			   			
			   			
			   	final String todate = mFormat.format(Double.valueOf(settoday))+ "-" 
					   	+(mFormat.format(Double.valueOf(Integer.parseInt((mFormat.format(Double.valueOf(settomonth))))+ 1))) + "-" 
					   	+ settoyear;  
			   			
			   	
			   	DatePicker SearchVoucherFromdate = (DatePicker) layout.findViewById(R.id.dpSearchVoucherFromdate);
			   	SearchVoucherFromdate.init(Integer.parseInt(setfromyear),(Integer.parseInt(setfrommonth)-1),Integer.parseInt(setfromday), null);
			   	
			   	DatePicker SearchVoucherTodate = (DatePicker) layout.findViewById(R.id.dpSearchVoucherTodate);
			   	SearchVoucherTodate.init(Integer.parseInt(settoyear),(Integer.parseInt(settomonth)-1),Integer.parseInt(settoday), null);
			   	
				final EditText etVoucherCode = (EditText)layout.findViewById(R.id.searchByVCode);
				etVoucherCode.setVisibility(EditText.GONE);
				
				final EditText etNarration = (EditText)layout.findViewById(R.id.searchByNarration);
				etNarration.setVisibility(EditText.GONE);
				
				final LinearLayout timeInterval = (LinearLayout)layout.findViewById(R.id.timeInterval);
				timeInterval.setVisibility(LinearLayout.GONE);
				
				final Spinner searchBy = (Spinner) layout.findViewById(R.id.sSearchVoucherBy);
				searchBy.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View v, int position,long id) {
						if(position == 0){
							etNarration.setVisibility(EditText.GONE);
							timeInterval.setVisibility(LinearLayout.GONE);
							etVoucherCode.setVisibility(EditText.VISIBLE);
						}
						if(position == 1){
							etVoucherCode.setVisibility(EditText.GONE);
							etNarration.setVisibility(EditText.GONE);
							timeInterval.setVisibility(LinearLayout.VISIBLE);
						}
						if(position == 2){
							etVoucherCode.setVisibility(EditText.GONE);
							timeInterval.setVisibility(LinearLayout.GONE);
							etNarration.setVisibility(EditText.VISIBLE);
						}
						
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				
				builder.setPositiveButton("View",new  DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						int pos = searchBy.getSelectedItemPosition();
					   	System.out.println(pos);
					   	
					   	if(pos == 0){
					   		String searchByVoucherCode = etVoucherCode.getText().toString();
					   		if(searchByVoucherCode.length() < 1){
					   			String message = "Please enter voucher code";
				        		toastValidationMessage(message);
					   		}
					   		else{
					   			Object[] params = new Object[]{1,searchByVoucherCode,fromdate,todate,""};
					   			getallvouchers(params);
					   			
					   		}
					   	}
					   	else if(pos == 1){
					   		final   DatePicker dpSearchVoucherFromdate = (DatePicker) dialog.findViewById(R.id.dpSearchVoucherFromdate);
						   	int SearchVoucherFromDay = dpSearchVoucherFromdate.getDayOfMonth();
						   	int SearchVoucherFromMonth = dpSearchVoucherFromdate.getMonth();
						   	int SearchVoucherFromYear = dpSearchVoucherFromdate.getYear();
						   	
						   	String SearchVoucherFromdate = mFormat.format(Double.valueOf(SearchVoucherFromDay))+ "-" 
						   	+(mFormat.format(Double.valueOf(Integer.parseInt((mFormat.format(Double.valueOf(SearchVoucherFromMonth))))+ 1))) + "-" 
						   	+ SearchVoucherFromYear;
						   	
						   	final   DatePicker dpSearchVoucherTodate = (DatePicker) dialog.findViewById(R.id.dpSearchVoucherTodate);
						   	int SearchVoucherToDay = dpSearchVoucherTodate.getDayOfMonth();
						   	int SearchVoucherToMonth = dpSearchVoucherTodate.getMonth();
						   	int SearchVoucherToYear = dpSearchVoucherTodate.getYear();
						   	
						   	String SearchVoucherTodate = mFormat.format(Double.valueOf(SearchVoucherToDay))+ "-" 
						   	+(mFormat.format(Double.valueOf(Integer.parseInt((mFormat.format(Double.valueOf(SearchVoucherToMonth))))+ 1))) + "-" 
						   	+ SearchVoucherToYear;
						   	
						   	try {
						   		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					        	Date date1 = sdf.parse(financialFromDate);
					        	Date date2 = sdf.parse(financialToDate);
					        	Date date3 = sdf.parse(SearchVoucherFromdate);
					        	Date date4 = sdf.parse(SearchVoucherTodate);
					        	
					        	System.out.println("all dates are...........");
					        	System.out.println(financialFromDate+"---"+financialToDate+"---"+SearchVoucherFromdate+"---"+SearchVoucherTodate);
					        	Calendar cal1 = Calendar.getInstance(); //financial from date
					        	Calendar cal2 = Calendar.getInstance(); //financial to date
					        	Calendar cal3 = Calendar.getInstance(); //from date
					        	Calendar cal4 = Calendar.getInstance(); //to date
					        	cal1.setTime(date1);
					        	cal2.setTime(date2);
					        	cal3.setTime(date3);
					        	cal4.setTime(date4);  
					        	
					        	if(((cal3.after(cal1)&&(cal3.before(cal2))) || (cal3.equals(cal1) || (cal3.equals(cal2)))) 
					        			&& ((cal4.after(cal1) && (cal4.before(cal2))) || (cal4.equals(cal2)) || (cal4.equals(cal1)))){
					        		
					        		Object[] params = new Object[]{2,"",SearchVoucherFromdate,SearchVoucherTodate,""};
					        		getallvouchers(params);
					        	}
					        	else{
					        		String message = "Please enter proper date";
					        		toastValidationMessage(message);
					        	}
							} catch (Exception e) {
								// TODO: handle exception
							}
						   	
					   	}
					   	else if(pos == 2){
					   		String searchByNarration = etNarration.getText().toString();
							if(searchByNarration.length() < 1){
								String message = "Please enter narration";
				        		toastValidationMessage(message);
							}
							else{
								
								Object[] params = new Object[]{3,"",fromdate,todate,searchByNarration};
								getallvouchers(params);
							}
					   	}
						
					}

					
				});
				
				builder.setNegativeButton("Cancel",new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
					
					}	
				});
				dialog=builder.create();
				dialog.show();
				WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
				//customizing the width and location of the dialog on screen 
				lp.copyFrom(dialog.getWindow().getAttributes());
				lp.width = 700;
				dialog.getWindow().setAttributes(lp);
				
			}
		});
	}
	
	public void addTable() {
		addHeader();
		
		/** Create a TableRow dynamically **/
        for(int i=0;i<searchedVoucherGrid.size();i++){
            ArrayList<String> columnValue = new ArrayList<String>();
            columnValue.addAll(searchedVoucherGrid.get(i));
            tr = new TableRow(SearchVoucher.this);
           
            for(int j=0;j<columnValue.size();j++){
                /** Creating a TextView to add to the row **/
                addRow(columnValue.get(j));   
                label.setBackgroundColor(Color.BLACK);
                /*
                 * set center aligned gravity for amount and for others set center gravity
                 */
                if(j==6){
                    label.setGravity(Gravity.RIGHT);
                }
                else{
                    label.setGravity(Gravity.CENTER);
                }
            }
           
            // Add the TableRow to the TableLayout
            vouchertable.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
        }
	}
	
	/*
     * add column heads to the table
     */
	public void addHeader() {
		 /** Create a TableRow dynamically **/
        String[] ColumnNameList = new String[] { "Sr. No.","Reference No","Date","Voucher Type","Account Name","Particular","Amount","Narration"};
       
        tr = new TableRow(SearchVoucher.this);
       
        for(int k=0;k<ColumnNameList.length;k++){
            /** Creating a TextView to add to the row **/
            addRow(ColumnNameList[k]);
            label.setBackgroundColor(Color.parseColor("#348017"));
            label.setGravity(Gravity.CENTER);
        }
       
         // Add the TableRow to the TableLayout
        vouchertable.addView(tr, new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
		
	}

	/*
	 * this function add the value to the row
	 */
	public void addRow(String string) {
		label = new TextView(SearchVoucher.this);
        label.setText(string);
        label.setTextColor(Color.WHITE);
        label.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        label.setPadding(2, 2, 2, 2);
        LinearLayout Ll = new LinearLayout(SearchVoucher.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(1, 1, 1, 1);
        //Ll.setPadding(10, 5, 5, 5);
        Ll.addView(label,params);
        tr.addView((View)Ll); // Adding textView to tablerow.
		
	}
	
	public void getallvouchers(Object[] params){
		
		Object[] searchedVoucher = (Object[])transaction.searchVoucher(params,client_id);
		searchedVoucherGrid = new ArrayList<ArrayList<String>>();
		for(Object voucherRow : searchedVoucher){
			Object[] v = (Object[]) voucherRow;
            searchedVoucherList = new ArrayList<String>();
            for(int i=0;i<v.length;i++){
            	
            	searchedVoucherList.add((String) v[i].toString());
               
            }
            searchedVoucherGrid.add(searchedVoucherList);
		}
		
		System.out.println("grid is...");
		System.out.println(searchedVoucherGrid);
		
		vouchertable = (TableLayout)findViewById(R.id.maintable);
		vouchertable.removeAllViews();
	       
        addTable();
	}
	
	
	public void toastValidationMessage(String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            	
                            }
                        });
                
        AlertDialog alert = builder.create();
        alert.show();
		
	} 
}