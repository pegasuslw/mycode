package com.tcl.geoquiz;


public class Question {

		private int mQuestion;
		private boolean mCorrect ;
		
		public Question(int resQuestionId, boolean bCorrect){
			mQuestion = resQuestionId;
			mCorrect = bCorrect;
		}

		public int getQuestion() {
			return mQuestion;
		}

		public void setQuestion(int question) {
			mQuestion = question;
		}

		public boolean isCorrect() {
			return mCorrect;
		}

		public void setCorrect(boolean correct) {
			mCorrect = correct;
		}


		
}
