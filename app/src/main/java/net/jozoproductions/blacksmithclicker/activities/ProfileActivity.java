package net.jozoproductions.blacksmithclicker.activities;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import net.jozoproductions.blacksmithclicker.BuildConfig;
import net.jozoproductions.blacksmithclicker.Player;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.rank.Rank;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_profile);

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
                "\nTotal money made: " + Player.totalMoneyMade +
                "\nTotal crates opened: " + Player.totalCratesOpened);

        //Close activity
        findViewById(R.id.close_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //CHEAT
        findViewById(R.id.pfpPicture).setOnClickListener(view -> {
            if (BuildConfig.DEBUG) {
                Player.AddMoney(50000);
                Player.AddResearchPoints(100);
            }
        });
    }
}
