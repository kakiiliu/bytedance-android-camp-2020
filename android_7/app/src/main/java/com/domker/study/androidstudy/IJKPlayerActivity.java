package com.domker.study.androidstudy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.domker.study.androidstudy.player.VideoPlayerIJK;
import com.domker.study.androidstudy.player.VideoPlayerListener;

import java.util.Timer;
import java.util.TimerTask;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;


public class IJKPlayerActivity extends Activity implements View.OnClickListener {
    VideoPlayerIJK ijkPlayer = null;
    ImageButton btnScreen;
    Button btnPlay;
    SeekBar seekBar;
    TextView tvTime;
    ImageView imageView;
    RelativeLayout rlLoading;
    RelativeLayout rlPlayer;
    int mVideoWidth = 0;
    int mVideoHeight = 0;

    private boolean isPortrait = true;

    private Handler handler;
    public static final int MSG_REFRESH = 1001;

    private boolean menu_visible = true;
    RelativeLayout rl_bottom;
    boolean isPlayFinish = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myijkplayer);
        init();
        initIJKPlayer();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


    private void init() {
        btnPlay = findViewById(R.id.btn_play);
        seekBar = findViewById(R.id.seekBar);
        btnScreen = findViewById(R.id.btn_screen);

        rl_bottom = (RelativeLayout) findViewById(R.id.playbtns);
        VideoPlayerIJK ijkPlayerView = findViewById(R.id.ijkPlayer);

        tvTime = findViewById(R.id.tv_time);
        imageView = findViewById(R.id.img_loading);
        rlLoading = findViewById(R.id.rl_loading);
        rlPlayer = findViewById(R.id.rl_player);

        ijkPlayerView.setOnClickListener(this);
        btnScreen.setOnClickListener(this);
        btnPlay.setOnClickListener(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeCallbacksAndMessages(null);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                ijkPlayer.seekTo(ijkPlayer.getDuration() * seekBar.getProgress() / 100);
                handler.sendEmptyMessageDelayed(MSG_REFRESH, 100);
            }
        });

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_REFRESH:
                        if (ijkPlayer.isPlaying()) {
                            refresh();
                            handler.sendEmptyMessageDelayed(MSG_REFRESH, 50);
                        }

                        break;
                }

            }
        };
    }

    private void initIJKPlayer() {
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }

        ijkPlayer = findViewById(R.id.ijkPlayer);
        ijkPlayer.setListener(new VideoPlayerListener());
//        ijkPlayer.setVideoResource(R.raw.bytedance);
        ijkPlayer.setVideoPath("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8");

        ijkPlayer.setListener(new VideoPlayerListener() {
            @Override
            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
            }

            @Override
            public void onCompletion(IMediaPlayer mp) {
                seekBar.setProgress(100);
                btnPlay.setText("Again");
            }

            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
                return false;
            }

            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {
                return false;
            }

            @Override
            public void onPrepared(IMediaPlayer mp) {
                refresh();
                handler.sendEmptyMessageDelayed(MSG_REFRESH, 50);
                isPlayFinish = false;
                mVideoWidth = mp.getVideoWidth();
                mVideoHeight = mp.getVideoHeight();
                Play();
                mp.start();
                rlLoading.setVisibility(View.GONE);
            }

            @Override
            public void onSeekComplete(IMediaPlayer mp) {
            }

            @Override
            public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sar_num, int sar_den) {
                mVideoWidth = mp.getVideoWidth();
                mVideoHeight = mp.getVideoHeight();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessageDelayed(MSG_REFRESH, 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (ijkPlayer != null && ijkPlayer.isPlaying()) {
            ijkPlayer.stop();
        }
        IjkMediaPlayer.native_profileEnd();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onDestroy() {
        if (ijkPlayer != null) {
            ijkPlayer.stop();
            ijkPlayer.release();
            ijkPlayer = null;
        }

        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ijkPlayer:
                if (menu_visible == false) {
                    rl_bottom.setVisibility(View.VISIBLE);
                    @SuppressLint("ResourceType") Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.layout.show_playbtns);
                    rl_bottom.startAnimation(animation);
                    menu_visible = true;
                } else {
                    rl_bottom.setVisibility(View.INVISIBLE);
                    @SuppressLint("ResourceType") Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.layout.hide_playbtns);
                    rl_bottom.startAnimation(animation);
                    menu_visible = false;
                }

                break;
            case R.id.btn_screen:
                if(!isPortrait) {
                    ijkPlayer.pause();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    isPortrait = true;
                    WindowManager wm = (WindowManager) this
                            .getSystemService(Context.WINDOW_SERVICE);
                    float width = wm.getDefaultDisplay().getWidth();
                    float height = wm.getDefaultDisplay().getHeight();
                    float ratio = width / height;
                    if (width < height) {
                        ratio = height/width;
                    }

                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rlPlayer.getLayoutParams();
                    layoutParams.height = (int) (mVideoHeight * ratio);
                    layoutParams.width = (int) width;
                    rlPlayer.setLayoutParams(layoutParams);
                    ijkPlayer.start();
                }
                else {
                    ijkPlayer.pause();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    isPortrait = false;
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rlPlayer.getLayoutParams();

                    layoutParams.height = (int) RelativeLayout.LayoutParams.MATCH_PARENT;
                    layoutParams.width = (int) RelativeLayout.LayoutParams.MATCH_PARENT;
                    rlPlayer.setLayoutParams(layoutParams);
                    ijkPlayer.start();
                }
                break;
            case R.id.btn_play:
                if (btnPlay.getText().toString().equals(getResources().getString(R.string.pause))) {
                    ijkPlayer.pause();
                    btnPlay.setText(getResources().getString(R.string.play));
                } else {
                    ijkPlayer.start();
                    btnPlay.setText(getResources().getString(R.string.pause));
                    handler.sendEmptyMessageDelayed(MSG_REFRESH, 500);
                }
                break;
        }
    }

    private void Play() {
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        float width = wm.getDefaultDisplay().getWidth();
        float height = wm.getDefaultDisplay().getHeight();
        float ratio = width / height;
        if (width < height) {
            ratio = height/width;
        }

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rlPlayer.getLayoutParams();
        layoutParams.height = (int) (mVideoHeight * ratio);
        layoutParams.width = (int) width;
        rlPlayer.setLayoutParams(layoutParams);
    }

    private void refresh() {
        long current = ijkPlayer.getCurrentPosition() / 1000;
        long duration = ijkPlayer.getDuration() / 1000;
        long current_second = current % 60;
        long current_minute = current / 60;
        long total_second = duration % 60;
        long total_minute = duration / 60;
        String time = current_minute + ":" + current_second + "/" + total_minute + ":" + total_second;
        tvTime.setText(time);
        if (duration != 0) {
            seekBar.setProgress((int) (current * 100 / duration));
        }
    }

}
