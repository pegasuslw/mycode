package com.tcl.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends ActionBarActivity {
	
	private static String TAG = "QuizActivity";
	private  String Key_Index = "index";
	private String  Key_IsCheater = "is_cheater";
	
	private TextView mQuestion;
	private Question[] mQuestions = new Question[]{
			new Question(R.string.question_xian, true),
			new Question(R.string.question_taiyuan, true),
			new Question(R.string.question_beijing, false),
	};
	private int mQuestionIndex = 0;
	
	private Button mTrueButton;
	private Button mFalseButton;
//	private Button mPrevButton;
//	private Button mNextButton;
	private ImageButton mPrevButton;
	private ImageButton mNextButton;
	private Button mCheatButton;
	private boolean mIsCheater;
	
	public static  final String EXTRA_ANSWER_IS_TRUE = "com.tcl.getquiz.answer_is_true";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "on create()...");
		setContentView(R.layout.activity_quiz);
		
		mQuestion = (TextView)findViewById(R.id.question_text_view);
		mQuestion.setText(mQuestions[mQuestionIndex].getQuestion());
		mQuestion.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showNextQuestion();
			}
		});
		
		
		mTrueButton = (Button)findViewById(R.id.true_button);
		mTrueButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
				judge(true);
			}
		});
		
		mFalseButton = (Button) findViewById(R.id.false_button);
		mFalseButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				judge(false);
			}
		});
		
		mPrevButton = (ImageButton)findViewById(R.id.prev_button);
		mPrevButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showPrevQuestion();
			}
		});
		
		mNextButton = (ImageButton)findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showNextQuestion();
			}
		});
		
		mCheatButton = (Button)findViewById(R.id.cheat_button);
		mCheatButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(QuizActivity.this, CheatActivity.class);
				boolean bAnswer = mQuestions[mQuestionIndex].isCorrect();
				i.putExtra(EXTRA_ANSWER_IS_TRUE, bAnswer);
				//startActivity(i);
				startActivityForResult(i, 0);
			}
		});
		
		if(null != savedInstanceState){
			mQuestionIndex =savedInstanceState.getInt(Key_Index);
			mIsCheater = savedInstanceState.getBoolean(Key_IsCheater);
			updateQuestion();
			
			if(mIsCheater){
				Log.d(TAG," you are a cheater");
			}
		}
		
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.d(TAG, "onDestroy()...");
	}
	
	@Override
	public void onStart(){
		super.onStart();
		Log.d(TAG, "onStart()...");
	}
	
	@Override
	public void onStop(){
		super.onStop();
		Log.d(TAG, "onStop()...");
	}
	
	@Override
	public void onResume(){
		super.onResume();
		Log.d(TAG, "onResume()...");
	}
	
	@Override
	public void onPause(){
		super.onPause();
		Log.d(TAG,"onPause()...");
	}
	private void judge(boolean select){
		int messageResId = 0;
		if(mIsCheater){
			messageResId = R.string.judgement_toast;
		}else{
			if (mQuestions[mQuestionIndex].isCorrect() == select) {
				messageResId =   R.string.correct_toast;
			} else {
				messageResId = R.string.incorrect_toast;
			}
		}
		Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
	}
	
	private void showPrevQuestion(){
		mQuestionIndex = (mQuestionIndex+mQuestions.length -1 )% mQuestions.length;
		mIsCheater = false;
		updateQuestion();
	}
	private void showNextQuestion(){
		mQuestionIndex = (mQuestionIndex + 1) % mQuestions.length;
		mIsCheater = false;
		updateQuestion();
	}
	
	private void updateQuestion(){
		//Log.d(TAG, "updating question text for question #" + mQuestionIndex, new Exception());
		mQuestion.setText(mQuestions[mQuestionIndex].getQuestion());		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSaveInstanceState(Bundle saveInstanceState){
		// TODO Auto-generated method stub
		super.onSaveInstanceState(saveInstanceState);
		Log.d(TAG, "onSaveInstanceState()...");
		saveInstanceState.putInt(Key_Index, mQuestionIndex);
		saveInstanceState.putBoolean(Key_IsCheater, mIsCheater);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(data == null){
			return;
		}
		mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
		Log.d(TAG," get result... mIsCheater:" + mIsCheater);
	}
	
}
