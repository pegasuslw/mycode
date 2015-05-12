package com.tcl.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class CrimeLab {
	
	private static CrimeLab sCrimeLab;
	private Context mAppContext;
	private  ArrayList<Crime> mCrimes;
	
    private CrimeLab(Context context){
    	mAppContext = context;
    	mCrimes = new ArrayList<Crime>();
    	
    	for(int i=0;i<100;i++){
    		Crime c = new Crime();
    		c.setTitle("Crime #" + i);
    		c.setSolved(i % 2 == 0);
    		mCrimes.add(c);
    	}
    }
    
    static public CrimeLab getCrimeLab(Context context){
    	if (sCrimeLab == null){
    		sCrimeLab = new CrimeLab(context.getApplicationContext());
    	}
    	return sCrimeLab;
    }
	
    public ArrayList<Crime> getCrimes(){
    	return mCrimes;
    }
    public Crime getCrime(UUID id){
    	for (Crime c : mCrimes){
    		if(c.getId().equals(id)){
    			return c;
    		}
    	}
    	return null;
    }
	
	
	
}
