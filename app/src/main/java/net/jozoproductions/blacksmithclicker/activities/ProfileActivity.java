package net.jozoproductions.blacksmithclicker.activities;

import static net.jozoproductions.blacksmithclicker.MainActivity.Save;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import net.jozoproductions.blacksmithclicker.BuildConfig;
import net.jozoproductions.blacksmithclicker.Player;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.audio.AudioSystem;
import net.jozoproductions.blacksmithclicker.items.Item;
import net.jozoproductions.blacksmithclicker.materials.Material;
import net.jozoproductions.blacksmithclicker.rank.Rank;
import net.jozoproductions.blacksmithclicker.research.Research;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton worldMatsBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_profile);

        worldMatsBtn = findViewById(R.id.worldMats);

        //Limit XP
        if (Player.readyToAdvanceBlacksmithRank()) {
            Player.xp = Rank.BLACKSMITH.neededExpToAdvance;
        } else {
            Player.xp = Math.min(Player.xp, Rank.AURORA.neededExpToAdvance-1);
        }

        //Check world mats access rank
        if (Player.xp < Rank.IRON.neededExpToAdvance) {
            worldMatsBtn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rank_gold));
            worldMatsBtn.setEnabled(false);
        }

        //Music volume
        SeekBar musicVolume = findViewById(R.id.musicVolume);
        musicVolume.setProgress((int) (AudioSystem.musicVolume * 100));
        musicVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                AudioSystem.ChangeMusicAudio(i / 100f);
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        //UI Initialize
        Rank playerRank = Rank.GetRank(Player.xp);
        Rank nextPlayerRank = Rank.GetNextRank(Player.xp);

        ((TextView) findViewById(R.id.player_name)).setText(Player.name);
        ((TextView) findViewById(R.id.rank_name)).setText(nextPlayerRank.name);
        ((TextView) findViewById(R.id.rank_exp_text)).setText(Player.xp + " / " + nextPlayerRank.neededExpToAdvance + " Xp.");
        ((ImageView) findViewById(R.id.rank_icon)).setImageResource(nextPlayerRank.iconId);
        ((ImageView) findViewById(R.id.rank_light)).setColorFilter(ContextCompat.getColor(this, nextPlayerRank.rankId));

        ProgressBar progressBar = findViewById(R.id.rank_exp_pb);
        progressBar.setMax(nextPlayerRank.neededExpToAdvance);
        progressBar.setProgress((int) Player.xp);

        //Stats Initialize
        ((TextView) findViewById(R.id.stats)).setText("Total clicks: " + Player.totalClicks +
                "\nTotal forged items: " + Player.totalForgedItems +
                "\nTotal mined materials: " + Player.totalForgedMaterials +
                "\nTotal money made: " + Player.totalMoneyMade +
                "\nTotal crates opened: " + Player.totalCratesOpened +
                "\n\n[Win] Total items: " + Player.totalUnlockedItems() + "/" + Item.values().length +
                "\n[Win] Total materials: " + Player.totalUnlockedMaterials() + "/" + Material.values().length +
                "\n[Win] Total researches: " + Player.totalResearchedResearches() + "/" + Player.totalMaxResearches() +
                "\n[Win] Last rank: " + (Player.xp >= Rank.BLACKSMITH.neededExpToAdvance) +
                "\n[Win] Last research: " + (Research.WIN_GAME.curLevel == 1));

        //Win game btn
        if (Player.checkForFinishedGame()) {
            ((Button) findViewById(R.id.winGameBtn)).setEnabled(true);
        }

        findViewById(R.id.winGameBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, WinGameActivity.class);
                startActivity(intent);
            }
        });

        //Close activity
        findViewById(R.id.close_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //World mats
        findViewById(R.id.worldMats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, WorldMatsActivity.class);
                startActivity(intent);
            }
        });

        //CHEAT
        findViewById(R.id.pfpPicture).setOnClickListener(view -> {
            if (BuildConfig.DEBUG) {
                Player.AddMoney(500000);
                Player.AddResearchPoints(500);
                Material.MaterialMarket.GenerateNewOffer();
            }
        });
    }

    @Override
    protected void onPause() {
        Save();
        super.onPause();
    }
}
