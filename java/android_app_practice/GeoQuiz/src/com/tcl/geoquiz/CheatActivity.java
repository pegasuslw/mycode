package com.tcl.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends ActionBarActivity {

public static String TAG = "CheatActivity";
public static String EXTRA_ANSWER_SHOWN = "com.tcl.geoquiz.anshwer_shown";
	
private boolean mAnswer;
private TextView mAnswerTextView;
private Button mShowAnswerButton;

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_cheat);
	
	Intent i = getIntent();
	mAnswer = i.getBooleanExtra(QuizActivity.EXTRA_ANSWER_IS_TRUE, false);
	
	mAnswerTextView = (TextView)findViewById(R.id.answerTextView); 
	mShowAnswerButton = (Button)findViewById(R.id.showAnswerButton);
	mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(mAnswer){
				mAnswerTextView.setText(R.string.true_button);
			}else{
				mAnswerTextView.setText(R.string.false_button);
			}
			setAnswerShownResult(true);
			//finish();
		}
	});
}

private void setAnswerShownResult(boolean isAnswerShown){
	Intent result = new Intent();
	result.putExtra(this.EXTRA_ANSWER_SHOWN, isAnswerShown);
	setResult(RESULT_OK,result);
}

@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d(TAG, "on start");
	}

@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG, "on Resume");
	}

@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d(TAG,"on Pause");
	}

@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(TAG, "on stop");
	}

@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG,"on destroy");
	}
}
