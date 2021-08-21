package net.jozoproductions.blacksmithclicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import net.jozoproductions.blacksmithclicker.activities.CrateMenuActivity;
import net.jozoproductions.blacksmithclicker.activities.ItemAtlasActivity;
import net.jozoproductions.blacksmithclicker.activities.ProfileActivity;
import net.jozoproductions.blacksmithclicker.activities.ResearchActivity;
import net.jozoproductions.blacksmithclicker.audio.AudioSystem;
import net.jozoproductions.blacksmithclicker.dialog.GuideDialog;
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
                endlessThread.particlePacks.add(new ParticlePack(
                        R.drawable.particle_spark,
                        x,
                        y,
                        10
                ));
                clickableItem.startAnimation(itemClickAnim);
                SmithingItem.Click(x, y);

                AudioSystem.PlayAudio("AnvilStrike");
            }
            return true;
        });

        findViewById(R.id.select_item_btn).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ItemAtlasActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.research).setOnClickListener(v -> {
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
            GuideDialog guideDialog = new GuideDialog(MainActivity.this);
            guideDialog.show();
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

        findViewById(R.id.changelog_btn).setOnClickListener(v -> {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.dialog_changelog);
            dialog.show();
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
        Set<String> researchNames = new HashSet<>();

        Item[] items = Item.values();
        Research[] research = Research.values();

        for (int i = 0; i < items.length; i++) {
            if (items[i].owningItem)
                itemNames.add(items[i].name());
        }

        for (int i = 0; i < research.length; i++) {
            if (research[i].researched)
                researchNames.add(research[i].name());
        }

        //Save
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("name", Player.name);
        editor.putFloat("money", Player.money);
        editor.putFloat("xp", Player.xp);
        editor.putFloat("researchPoints", Player.researchPoints);

        editor.putInt("common_crate_open_count", Player.commonCrateOpenCount);
        editor.putInt("uncommon_crate_open_count", Player.uncommonCrateOpenCount);
        editor.putInt("rare_crate_open_count", Player.rareCrateOpenCount);
        editor.putInt("epic_crate_open_count", Player.epicCrateOpenCount);
        editor.putInt("legendary_crate_open_count", Player.legendaryCrateOpenCount);
        editor.putInt("mythic_crate_open_count", Player.mythicCrateOpenCount);

        editor.putStringSet("items", itemNames);
        editor.putStringSet("research", researchNames);

        editor.putBoolean("first_time_launch", false);
        editor.apply();
    }

    private void Load() {
        //Happens every time, player launches game
        Player.UnlockItem(Item.STICK);
        Research.BLACKSMITH.researched = true;

        //Load
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);

        Player.name = sp.getString("name", "Guest32557");
        Player.AddMoney(sp.getFloat("money", 0f));
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

        //Convert research names array to research
        ArrayList<String> researchNames = new ArrayList<>(researchNamesSet);

        for (int i = 0; i < researchNames.size(); i++) {
            Research.valueOf(researchNames.get(i)).researched = true;
        }

        Player.commonCrateOpenCount = sp.getInt("common_crate_open_count", Player.commonCrateOpenCount);
        Player.uncommonCrateOpenCount = sp.getInt("uncommon_crate_open_count", Player.uncommonCrateOpenCount);
        Player.rareCrateOpenCount = sp.getInt("rare_crate_open_count", Player.rareCrateOpenCount);
        Player.epicCrateOpenCount = sp.getInt("epic_crate_open_count", Player.epicCrateOpenCount);
        Player.legendaryCrateOpenCount = sp.getInt("legendary_crate_open_count", Player.legendaryCrateOpenCount);
        Player.mythicCrateOpenCount = sp.getInt("mythic_crate_open_count", Player.mythicCrateOpenCount);
    }

    private void FirstTimeLaunch() {
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