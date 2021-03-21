package umn.ac.id;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;

import static umn.ac.id.LaguActivity.mDaftarLagu;
import static umn.ac.id.LaguActivity.mPathLagu;

public class PlayerActivity extends AppCompatActivity {

    private TextView song_name;
    private ImageView nxtBtn, backBtn, playPauseBtn;
    private MediaPlayer mediaPlayer;
    private Uri uri;
    private Handler handler = new Handler();
    private Thread playThread, nextThread, prevThread;
    SeekBar seekBar;

    int position = -1;

    public PlayerActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        song_name = findViewById(R.id.songtitle);
        nxtBtn = findViewById(R.id.next);
        backBtn = findViewById(R.id.back);
        seekBar = findViewById(R.id.SongProgress);
        playPauseBtn = findViewById(R.id.play);


        getIntentMethod();

        song_name.setText(mDaftarLagu.get(position));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer!=null && fromUser){
                    mediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        PlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    int mCurrentPosition = mediaPlayer.getCurrentPosition()/1000;
                    seekBar.setProgress(mCurrentPosition);
                }
                handler.postDelayed(this, 1000);
            }

        });
    }

    @Override
    protected void onResume() {
        playThreadBtn();
        nextThreadBtn();
        prevThreadBtn();
        super.onResume();
    }

    private void nextThreadBtn() {
        nextThread = new Thread(){
            @Override
            public void run() {
                super.run();
                nxtBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextBtnClicked();
                    }

                });
            }
        };
        nextThread.start();
    }

    private void playThreadBtn() {
        playThread = new Thread(){
            @Override
            public void run() {
                super.run();
                playPauseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playPauseBtnClicked();
                    }

                });
            }
        };
        playThread.start();
    }

    private void prevThreadBtn() {
        prevThread = new Thread(){
            @Override
            public void run() {
                super.run();
                backBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        prevBtnClicked();
                    }

                });
            }
        };
        prevThread.start();
    }

    private void nextBtnClicked(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            position = ((position+1)% mDaftarLagu.size());
            uri = Uri.parse(mPathLagu.get(position));
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
            song_name.setText(mDaftarLagu.get(position));
            seekBar.setMax(mediaPlayer.getDuration()/1000);
            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer != null){
                        int mCurrentPosition = mediaPlayer.getCurrentPosition()/1000;
                        seekBar.setProgress(mCurrentPosition);
                    }
                    handler.postDelayed(this, 1000);
                }

            });
            playPauseBtn.setImageResource(R.drawable.pause);
        }else{
            mediaPlayer.stop();
            mediaPlayer.release();
            position = ((position+1)% mDaftarLagu.size());
            uri = Uri.parse(mPathLagu.get(position));
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            //mediaPlayer.stop();
            song_name.setText(mDaftarLagu.get(position));
            seekBar.setMax(mediaPlayer.getDuration()/1000);
            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer != null){
                        int mCurrentPosition = mediaPlayer.getCurrentPosition()/1000;
                        seekBar.setProgress(mCurrentPosition);
                    }
                    handler.postDelayed(this, 1000);
                }

            });
            playPauseBtn.setImageResource(R.drawable.play_button_arrowhead);
        }
    }

    private void prevBtnClicked(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            position = ((position-1)% mDaftarLagu.size());
            uri = Uri.parse(mPathLagu.get(position));
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
            song_name.setText(mDaftarLagu.get(position));
            seekBar.setMax(mediaPlayer.getDuration()/1000);
            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer != null){
                        int mCurrentPosition = mediaPlayer.getCurrentPosition()/1000;
                        seekBar.setProgress(mCurrentPosition);
                    }
                    handler.postDelayed(this, 1000);
                }

            });
            playPauseBtn.setImageResource(R.drawable.pause);
        }else{
            mediaPlayer.stop();
            mediaPlayer.release();
            position = ((position+1)% mDaftarLagu.size());
            uri = Uri.parse(mPathLagu.get(position));
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            //mediaPlayer.stop();
            song_name.setText(mDaftarLagu.get(position));
            seekBar.setMax(mediaPlayer.getDuration()/1000);
            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer != null){
                        int mCurrentPosition = mediaPlayer.getCurrentPosition()/1000;
                        seekBar.setProgress(mCurrentPosition);
                    }
                    handler.postDelayed(this, 1000);
                }

            });
            playPauseBtn.setImageResource(R.drawable.play_button_arrowhead);
        }
    }

    private void playPauseBtnClicked() {
        if(mediaPlayer.isPlaying()){
            playPauseBtn.setImageResource(R.drawable.play_button_arrowhead);
            mediaPlayer.pause();
            seekBar.setMax(mediaPlayer.getDuration()/1000);

            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer != null){
                        int mCurrentPosition = mediaPlayer.getCurrentPosition()/1000;
                        seekBar.setProgress(mCurrentPosition);
                    }
                    handler.postDelayed(this, 1000);
                }

            });
        }else{
            playPauseBtn.setImageResource(R.drawable.pause);
            mediaPlayer.start();
            seekBar.setMax(mediaPlayer.getDuration()/1000);

            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer != null){
                        int mCurrentPosition = mediaPlayer.getCurrentPosition()/1000;
                        seekBar.setProgress(mCurrentPosition);
                    }
                    handler.postDelayed(this, 1000);
                }

            });
        }
    }

    private void getIntentMethod(){
        position = getIntent().getIntExtra("position", -1);
        if(mDaftarLagu != null){
            playPauseBtn.setImageResource(R.drawable.pause);
            uri = Uri.parse(mPathLagu.get(position));
            Log.e("erro",mPathLagu.get(position));
        }else{
            Log.e("erro","SALH");
        }

        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();

        }else{
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        }
        seekBar.setMax(mediaPlayer.getDuration()/1000);

    }

}