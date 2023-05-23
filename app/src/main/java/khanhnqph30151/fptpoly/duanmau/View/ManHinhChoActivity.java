package khanhnqph30151.fptpoly.duanmau.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import khanhnqph30151.fptpoly.duanmau.R;

public class ManHinhChoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_cho);

//        ImageView ivLogo =  findViewById(R.id.iv_HomeScreen);
//        Dung anh gif
//        Glide.with(this).load(R.drawable.home_screen).into(ivLogo);


        CountDownTimer countDownTimer = new CountDownTimer(3000, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                Intent i = new Intent(ManHinhChoActivity.this, LoginActivity.class);
                startActivity(i);
            }
        };
        countDownTimer.start();
    }
}