package net.jozoproductions.blacksmithclicker;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.core.content.res.ResourcesCompat;

import net.jozoproductions.blacksmithclicker.items.Item;
import net.jozoproductions.blacksmithclicker.particlemanaging.ParticlePack;

public class SmithingItem {

    public static Item smithingItem;
    private static float progress;

    //UI
    public static TextView name;
    public static ProgressBar progressBar;
    public static ImageView light;
    public static ImageView clickableItem;

    public static void Initialize(TextView name_, ProgressBar progressBar_, ImageView light_, ImageView clickableItem_) {
        name = name_;
        progressBar = progressBar_;
        light = light_;
        clickableItem = clickableItem_;
    }

    public static void Click(float x, float y) {
        //Research points
        float randomPercent = MainActivity.random.nextFloat() * 100;
        if (randomPercent < Player.researchPointChance) {
            Player.researchPoints++;

            //Particle
            MainActivity.endlessThread.particlePacks.add(new ParticlePack(
                    R.drawable.research_add,
                    x,
                    y,
                    2
            ));
        }

        //Progress
        progress += Player.forgeEffectiveness;

        if (progress >= smithingItem.requiredClicks) {
            progress = 0;
            Player.AddMoney(smithingItem.sellPrice);
            Player.totalForgedItems++;
            MainActivity.endlessThread.particlePacks.add(new ParticlePack(R.drawable.particle_star, 60, 60, 20));
        }

        Player.totalClicks++;
        UpdateProgressBar();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public static void ChangeItem(Item newItem) {
        smithingItem = newItem;

        name.setText(smithingItem.name);
        light.setColorFilter(light.getResources().getColor(newItem.itemGroup.rarity.colorId));
        clickableItem.setImageDrawable(clickableItem.getResources().getDrawable(smithingItem.iconId));
        progressBar.setMax(smithingItem.requiredClicks);

        progress = 0;
        UpdateProgressBar();
    }

    public static void UpdateProgressBar() {
        progressBar.setProgress((int)progress);
    }
}
