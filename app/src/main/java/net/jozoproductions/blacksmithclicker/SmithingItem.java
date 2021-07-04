package net.jozoproductions.blacksmithclicker;

import android.annotation.SuppressLint;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.jozoproductions.blacksmithclicker.items.Item;
import net.jozoproductions.blacksmithclicker.particlemanaging.ParticlePack;

public class SmithingItem {

    public static Item smithingItem;
    private static int progress;

    //UI
    public static TextView name;
    public static ProgressBar progressBar;
    public static ImageView clickableItem;

    public static void Initialize(TextView name_, ProgressBar progressBar_, ImageView clickableItem_) {
        name = name_;
        progressBar = progressBar_;
        clickableItem = clickableItem_;
    }

    public static void Click() {
        progress++;

        if (progress >= smithingItem.requiredClicks) {
            progress = 0;
            Player.AddMoney(smithingItem.sellPrice);
            MainActivity.endlessThread.particlePacks.add(new ParticlePack(R.drawable.particle_star, 60, 60, 20));
        }

        UpdateProgressBar();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public static void ChangeItem(Item newItem) {
        smithingItem = newItem;

        name.setText(smithingItem.name);
        clickableItem.setImageDrawable(clickableItem.getResources().getDrawable(smithingItem.iconId));
        progressBar.setMax(smithingItem.requiredClicks);

        progress = 0;
        UpdateProgressBar();
    }

    public static void UpdateProgressBar() {
        progressBar.setProgress(progress);
    }
}
