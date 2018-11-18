package storabot.suka.dev.projektest.Activity;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import storabot.suka.dev.projektest.MainActivity;
import storabot.suka.dev.projektest.R;

public class SplashScreen extends Activity {
    View viewProgress;
    AnimationSet animationSet;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        viewProgress = findViewById(R.id.view_progress);
        int viewWidth = viewProgress.getWidth();
        TranslateAnimation move = new TranslateAnimation(-(getScreenWidth() / 2) + viewWidth / 2, (getScreenWidth() / 2) + viewWidth / 2 + viewWidth, 0, 0);
        move.setDuration(1000);

        TranslateAnimation move1 = new TranslateAnimation(-viewWidth, 0, 0, 0);
        move1.setDuration(500);
        ScaleAnimation laftOut = new ScaleAnimation(0, 1, 1, 1);
        laftOut.setDuration(500);
        animationSet = new AnimationSet(true);
        animationSet.addAnimation(move);
        animationSet.addAnimation(move1);
        animationSet.addAnimation(laftOut);
        animationSet.addAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideout));
        startAnimation();

    }
    private void startAnimation() {
        viewProgress.startAnimation(animationSet);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 1000);
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}