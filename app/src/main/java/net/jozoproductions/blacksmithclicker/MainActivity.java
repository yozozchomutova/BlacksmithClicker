package net.jozoproductions.blacksmithclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import net.jozoproductions.blacksmithclicker.activities.ItemSelectActivity;
import net.jozoproductions.blacksmithclicker.items.Item;
import net.jozoproductions.blacksmithclicker.materials.Material;
import net.jozoproductions.blacksmithclicker.particlemanaging.ParticlePack;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public static final String DRAWABLE_PREFIX = "drawable://";

    public static Random random = new Random();

    public static EndlessThread endlessThread;

    public static float SCREEN_WIDTH;
    public static float SCREEN_HEIGHT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Screen size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        SCREEN_HEIGHT = displayMetrics.heightPixels;
        SCREEN_WIDTH = displayMetrics.widthPixels;

        //Fullscreen
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        endlessThread = new EndlessThread();
        endlessThread.context = this;

        Player.setMoneyText(findViewById(R.id.coin_count));

        SmithingItem.Initialize(findViewById(R.id.item_name), findViewById(R.id.smith_progress), findViewById(R.id.clickable_item));
        SmithingItem.ChangeItem(Item.STICK);

        ImageView clickableItem = findViewById(R.id.clickable_item);
        Animation itemClickAnim = AnimationUtils.loadAnimation(this, R.anim.item_click);

        ParticlePack.parent = findViewById(R.id.root);

        clickableItem.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    endlessThread.particlePacks.add(new ParticlePack(
                            R.drawable.particle_spark,
                            event.getRawX(),
                            event.getRawY(),
                            10
                    ));
                    clickableItem.startAnimation(itemClickAnim);
                    SmithingItem.Click();
                }
                return false;
            }
        });

        findViewById(R.id.select_item_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ItemSelectActivity.class);
                startActivity(intent);
            }
        });

        //Start endless thread
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(endlessThread, 0, 10, TimeUnit.MILLISECONDS);

        //Load save
        Load();
    }

    @Override
    protected void onPause() {
        System.out.println("rip");
        Save();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }

    private void Save() {
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("money", Player.money);
        editor.apply();
    }

    private void Load() {
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        Player.AddMoney(sp.getInt("money", 0));
    }
}