package net.jozoproductions.blacksmithclicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import net.jozoproductions.blacksmithclicker.activities.CrateMenuActivity;
import net.jozoproductions.blacksmithclicker.activities.InfoActivity;
import net.jozoproductions.blacksmithclicker.activities.ItemAtlasActivity;
import net.jozoproductions.blacksmithclicker.activities.MaterialInfoActivity;
import net.jozoproductions.blacksmithclicker.activities.ProfileActivity;
import net.jozoproductions.blacksmithclicker.activities.ResearchActivity;
import net.jozoproductions.blacksmithclicker.audio.AudioSystem;
import net.jozoproductions.blacksmithclicker.crates.Crate;
import net.jozoproductions.blacksmithclicker.items.Item;
import net.jozoproductions.blacksmithclicker.particlemanaging.ParticlePack;
import net.jozoproductions.blacksmithclicker.research.Research;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public static Random random = new Random();

    public static EndlessThread endlessThread;

    public static float SCREEN_WIDTH;
    public static float SCREEN_HEIGHT;

    public static ConstraintLayout loadingScreen;

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
        Item.InitializeItemGroupItemsArrayLists();

        //Load sounds
        AudioSystem.LoadAudio(this, "AnvilStrike", R.raw.anvil_strike);
        AudioSystem.SetMusic(this, R.raw.theme);

        //24/7 thread
        endlessThread = new EndlessThread();
        endlessThread.context = this;

        Player.setMoneyText(findViewById(R.id.coin_count));

        SmithingItem.Initialize(findViewById(R.id.item_name), findViewById(R.id.smith_progress), findViewById(R.id.light), findViewById(R.id.clickable_item));
        SmithingItem.ChangeItem(Item.STICK);

        ImageView clickableItem = findViewById(R.id.clickable_item);
        Animation itemClickAnim = AnimationUtils.loadAnimation(this, R.anim.item_click);

        ParticlePack.parent = findViewById(R.id.root);

        clickableItem.setOnTouchListener((v, event) -> {
            float x = event.getRawX();
            float y = event.getRawY();

            int actionId = event.getAction();
            if (actionId == MotionEvent.ACTION_DOWN || actionId == MotionEvent.ACTION_POINTER_1_DOWN) {
                boolean isCriticalHit = random.nextFloat() < Player.criticalHitChance;

                if (isCriticalHit) {
                    endlessThread.particlePacks.add(new ParticlePack(
                            R.drawable.particle_spark_critical_hit,
                            x,
                            y,
                            10
                    ));
                } else {
                    endlessThread.particlePacks.add(new ParticlePack(
                            R.drawable.particle_spark,
                            x,
                            y,
                            10
                    ));
                }
                clickableItem.startAnimation(itemClickAnim);
                SmithingItem.Click(x, y, isCriticalHit);

                AudioSystem.PlayAudio("AnvilStrike");
            }
            return true;
        });

        loadingScreen = findViewById(R.id.loading_screen);

        findViewById(R.id.select_item_btn).setOnClickListener(v -> {
            loadingScreen.setVisibility(View.VISIBLE);

            //Loading thread
            Thread loadingThread = new Thread(() -> {
                Intent intent = new Intent(MainActivity.this, ItemAtlasActivity.class);
                startActivity(intent);
            });

            loadingThread.start();
        });

        findViewById(R.id.research_list).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ResearchActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.storage).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CrateMenuActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.profile).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.info_btn).setOnClickListener(v -> {
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);

            findViewById(R.id.guideHelpText).setVisibility(View.GONE);
        });

        findViewById(R.id.materialsInfo).setOnClickListener(v -> {
            Intent intent = new Intent(this, MaterialInfoActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.discord_btn).setOnClickListener(v -> {
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
        });

        //Start endless thread
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(endlessThread, 0, 10, TimeUnit.MILLISECONDS);

        //Load save
        Load();

        Player.CalculateResearchUpgrades();
    }

    @Override
    protected void onPause() {
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

        Item[] items = Item.values();

        for (int i = 0; i < items.length; i++) {
            if (items[i].owningItem)
                itemNames.add(items[i].name());
        }

        //Save
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("name", Player.name);
        editor.putFloat("money", Player.money);
        editor.putFloat("xp", Player.xp);
        editor.putFloat("researchPoints", Player.researchPoints);

        //Save player stats
        editor.putInt("totalClicks", Player.totalClicks);
        editor.putInt("totalForgedItems", Player.totalForgedItems);
        editor.putInt("totalMoneyMade", Player.totalMoneyMade);
        editor.putInt("totalCratesOpened", Player.totalCratesOpened);

        editor.putInt("common_crate_open_count", Crate.COMMON_CRATE.openCount);
        editor.putInt("uncommon_crate_open_count", Crate.UNCOMMON_CRATE.openCount);
        editor.putInt("rare_crate_open_count", Crate.RARE_CRATE.openCount);
        editor.putInt("epic_crate_open_count", Crate.EPIC_CRATE.openCount);
        editor.putInt("legendary_crate_open_count", Crate.LEGENDARY_CRATE.openCount);
        editor.putInt("mythic_crate_open_count", Crate.MYTHIC_CRATE.openCount);
        editor.putInt("christmas_crate_open_count", Crate.CHRISTMAS_CRATE.openCount);

        editor.putStringSet("items", itemNames);

        //Save Research stats
        Research[] research = Research.values();

        for (int i = 0; i < research.length; i++) {
            editor.putInt(research[i].name() + "_lvl_", research[i].curLevel);
        }

        editor.putBoolean("first_time_launch", false);
        editor.apply();
    }

    private void Load() {
        //Happens every time, player launches game
        Player.UnlockItem(Item.STICK);

        //Load
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);

        //Load player stats
        Player.totalClicks = sp.getInt("totalClicks", 0);
        Player.totalForgedItems = sp.getInt("totalForgedItems", 0);
        Player.totalMoneyMade = sp.getInt("totalMoneyMade", 0);
        Player.totalCratesOpened = sp.getInt("totalCratesOpened", 0);

        Player.name = sp.getString("name", "Guest32557");
        Player.AddMoney(sp.getFloat("money", 0f), false);
        Player.xp = sp.getFloat("xp", Player.money);
        Player.researchPoints = sp.getFloat("researchPoints", 0);

        Set<String> itemNamesSet = sp.getStringSet("items", new HashSet<>());
        Set<String> researchNamesSet = sp.getStringSet("research", new HashSet<>());

        if (sp.getBoolean("first_time_launch", true))
            FirstTimeLaunch();

        //Convert item names array to items array
        ArrayList<String> itemNames = new ArrayList<>(itemNamesSet);

        for (int i = 0; i < itemNames.size(); i++) {
            Item item = Item.valueOf(itemNames.get(i));
            Player.UnlockItem(item);
        }

        Crate.COMMON_CRATE.openCount = sp.getInt("common_crate_open_count", 0);
        Crate.UNCOMMON_CRATE.openCount = sp.getInt("uncommon_crate_open_count", 0);
        Crate.RARE_CRATE.openCount = sp.getInt("rare_crate_open_count", 0);
        Crate.EPIC_CRATE.openCount = sp.getInt("epic_crate_open_count", 0);
        Crate.LEGENDARY_CRATE.openCount = sp.getInt("legendary_crate_open_count", 0);
        Crate.MYTHIC_CRATE.openCount = sp.getInt("mythic_crate_open_count", 0);
        Crate.CHRISTMAS_CRATE.openCount = sp.getInt("christmas_crate_open_count", 0);

        //Load Research stats
        Research[] research = Research.values();

        for (int i = 0; i < research.length; i++) {
            research[i].curLevel = sp.getInt(research[i].name() + "_lvl_", research[i].curLevel);
        }
    }

    private void FirstTimeLaunch() {
        //Tutorial
        findViewById(R.id.guideHelpText).setVisibility(View.VISIBLE);

        //Message for new players
        AlertDialog.Builder message = new AlertDialog.Builder(this);
        message.setCancelable(false);
        message.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        message.setTitle("Welcome");
        message.setMessage("Welcome on ");

        message.show();
    }
}