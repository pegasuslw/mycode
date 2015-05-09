package com.tcl.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.SurfaceHolder;

public class AudioPlayer {

	private String TAG = "AudioPlayer";
	private MediaPlayer mPlayer;
	
	
	public void play(Context context){
		if(mPlayer == null){
			mPlayer = MediaPlayer.create(context, R.raw.guangming);
			//mPlayer = MediaPlayer.create(context, R.raw.apollo_17_stroll);
			mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					Log.d(TAG, "onCompletion");
					AudioPlayer.this.stop();
				}
			});
		}
		
		if(!mPlayer.isPlaying())  // 只有在没播放的时候，才调用start开始播放
		{
			mPlayer.start();
		}

	}
	
	public void stop(){
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}
	
	public void setDisplay(SurfaceHolder holder){
		if (mPlayer != null){
			mPlayer.setDisplay(holder);
		}
		
	}
}
