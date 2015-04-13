package com.tcl.geoquiz;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends ActionBarActivity {
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
	}
	
	private void judge(boolean select){
		if (mQuestions[mQuestionIndex].isCorrect() == select){
			Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
		}
	}
	
	private void showPrevQuestion(){
		mQuestionIndex = (mQuestionIndex+mQuestions.length -1 )% mQuestions.length;
		mQuestion.setText(mQuestions[mQuestionIndex].getQuestion());
	}
	private void showNextQuestion(){
		mQuestionIndex = (mQuestionIndex + 1) % mQuestions.length;
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
}
