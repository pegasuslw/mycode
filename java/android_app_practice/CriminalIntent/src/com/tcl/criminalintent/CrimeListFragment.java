package com.tcl.criminalintent;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class CrimeListFragment extends ListFragment {
	
	private static String TAG = "CrimeListFragment";
	
	ArrayList<Crime> mCrimes;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.crimes_title);
		mCrimes = CrimeLab.getCrimeLab(this.getActivity()).getCrimes();
		
/*		ArrayAdapter<Crime> adapter = new ArrayAdapter(getActivity(), 
																												android.R.layout.simple_list_item_1,
																												mCrimes);*/
		
		ArrayAdapter<Crime> adapter = new ArrayAdapter(getActivity(), 0, mCrimes){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if(convertView == null){
					convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
				}

				Crime c = (Crime)getItem(position);
				
				TextView titleTextView = (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
				titleTextView.setText(c.getTitle());
				
				TextView dateTextView = (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
				dateTextView.setText(c.getDate().toString());
				
				CheckBox solvedCheckBox = (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckbox);
				solvedCheckBox.setChecked(c.getSolved());
				
				return convertView;
			}
		};
		
		setListAdapter(adapter);
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Crime crime = (Crime) getListAdapter().getItem(position);
		Log.d(TAG,  crime.getTitle() +  " was clicked.");
	}
}
