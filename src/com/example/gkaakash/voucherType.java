package com.example.gkaakash;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class voucherType extends ListActivity {
	//adding voucher list items
	String[] voucherType = new String[] { "Contra","Journal","Payment","Receipt","Credit Note","Debit Note","Sales","Sales Return","Purchase","Purchase Return" };
	final Context context = this;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//calling transactions.xml page
		setListAdapter(new ArrayAdapter<String>(this, R.layout.transactions,voucherType));
		
		//setting title
		setTitle("Transaction");
		
		//getting the list view and setting background
		final ListView listView = getListView();
		listView.setTextFilterEnabled(true);
		listView.setBackgroundColor(Color.BLACK);
		listView.setCacheColorHint(Color.TRANSPARENT);
		
		//when voucher list items are clicked, code for respective actions goes here ...
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				//for "Contra" voucher
				if(position == 0)
				{
					//String voucherTypeFlag = parent.getItemAtPosition(position).toString();
					Intent intent = new Intent(context, Voucher.class);
					// To pass on the value to the next page
					startActivity(intent);
				}
				
			} 
		});
	}
}
