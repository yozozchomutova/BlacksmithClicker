package net.jozoproductions.blacksmithclicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.widget.Toast;

import net.jozoproductions.blacksmithclicker.activities.CrateMenuActivity;
import net.jozoproductions.blacksmithclicker.activities.ItemSelectActivity;
import net.jozoproductions.blacksmithclicker.activities.ProfileActivity;
import net.jozoproductions.blacksmithclicker.activities.ResearchActivity;
import net.jozoproductions.blacksmithclicker.dialog.GuideDialog;
import net.jozoproductions.blacksmithclicker.items.Item;
import net.jozoproductions.blacksmithclicker.materials.Material;
import net.jozoproductions.blacksmithclicker.particlemanaging.ParticlePack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
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

        //Initialize items & rarities
        Item.InitializeRarityItemsArrayLists();

        //24/7 thread
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
                float x = event.getRawX();
                float y = event.getRawY();

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    endlessThread.particlePacks.add(new ParticlePack(
                            R.drawable.particle_spark,
                            x,
                            y,
                            10
                    ));
                    clickableItem.startAnimation(itemClickAnim);
                    SmithingItem.Click(x, y);
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

        findViewById(R.id.research).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResearchActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.storage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CrateMenuActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.info_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuideDialog guideDialog = new GuideDialog(MainActivity.this);
                guideDialog.show();
            }
        });

        findViewById(R.id.discord_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder discordDialog = new AlertDialog.Builder(MainActivity.this);
                discordDialog.setTitle("Discord");
                discordDialog.setMessage("Join our community, send feedback and talk with others.");
                discordDialog.setPositiveButton("Copy link", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("link", "https://discord.gg/mXFwmrdX9B");
                        clipboard.setPrimaryClip(clip);
                    }
                });

                discordDialog.setNegativeButton("Open link", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://discord.gg/mXFwmrdX9B"));
                        startActivity(browserIntent);
                    }
                });
                discordDialog.create().show();
            }
        });

        findViewById(R.id.changelog_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_changelog);
                dialog.show();
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
        //Convert items array to item names array
        Set<String> itemNames = new HashSet<>();
        ArrayList<Item> items = Player.unlockedItemRecipes;

        for (int i = 0; i < items.size(); i++) {
            itemNames.add(items.get(i).name());
        }

        //Save
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("name", Player.name);
        editor.putFloat("money", Player.money);
        editor.putFloat("xp", Player.xp);
        editor.putFloat("researchPoints", Player.researchPoints);

        editor.putStringSet("items", itemNames);
        editor.putBoolean("first_time_launch", false);
        editor.apply();
    }

    private void Load() {
        //Load
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);

        Player.name = sp.getString("name", "Guest32557");
        Player.AddMoney(sp.getFloat("money", 0f));
        Player.xp = sp.getFloat("xp", Player.money);
        Player.researchPoints = sp.getFloat("researchPoints", 0);

        Set<String> itemNamesSet = sp.getStringSet("items", new HashSet<>());

        if (sp.getBoolean("first_time_launch", true))
            FirstTimeLaunch();

        //Convert item names array to items array
        ArrayList<String> itemNames = new ArrayList<>(itemNamesSet);

        for (int i = 0; i < itemNames.size(); i++) {
            Item item = Item.valueOf(itemNames.get(i));
            Player.AddItemRecipe(item);
        }
    }

    private void FirstTimeLaunch() {
        Player.AddItemRecipe(Item.STICK);

        //Message for new players
        AlertDialog.Builder message = new AlertDialog.Builder(this);
        message.setCancelable(false);
        message.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        message.setTitle("Welcome");
        message.setMessage("Please take in mind, that this is Beta and im going to update it often. You can press <!> button (at the top-left corner) for help/guide. If you want to give me feedback, join my discord server (button at top-left corner).");

        message.show();
    }
}