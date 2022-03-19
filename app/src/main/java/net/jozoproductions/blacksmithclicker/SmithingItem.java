package net.jozoproductions.blacksmithclicker;

import static net.jozoproductions.blacksmithclicker.Player.curShakeBonusEffect;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import net.jozoproductions.blacksmithclicker.audio.AudioSystem;
import net.jozoproductions.blacksmithclicker.items.Item;
import net.jozoproductions.blacksmithclicker.materials.Material;
import net.jozoproductions.blacksmithclicker.particlemanaging.ParticlePack;
import net.jozoproductions.blacksmithclicker.rank.Rank;
import net.jozoproductions.blacksmithclicker.research.Research;

public class SmithingItem {

    public static Item smithingItem;
    public static Material material;

    private static float progress;

    private static boolean materialMode = false;

    //UI
    public static TextView name;
    public static ProgressBar progressBar;
    public static ImageView light;
    public static ImageView clickableItem;

    public static ImageView matIcon;
    public static TextView matCount;

    public static void Initialize(TextView name_, ProgressBar progressBar_, ImageView light_, ImageView clickableItem_, ImageView matIcon_, TextView matCount_) {
        name = name_;
        progressBar = progressBar_;
        light = light_;
        clickableItem = clickableItem_;

        matIcon = matIcon_;
        matCount = matCount_;
    }

    public static void Click(float x, float y, boolean isCriticalHit) {
        MainActivity.UpdateForgingWarning();

        //If not enough materials/not researched = cancel
        if (!materialMode && smithingItem.material.count < smithingItem.materialCost || !smithingItem.material.isResearched()) {
            return;
        }

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

        if (materialMode) {
            AudioSystem.PlayAudio(AudioSystem.MINE1);

            //Progress
            if (isCriticalHit) {
                progress += Player.materialForgeEffectiveness * curShakeBonusEffect * Player.criticalHitBonus;
            } else
                progress += Player.materialForgeEffectiveness * curShakeBonusEffect;

            if (progress >= material.requiredClicksToMine) {
                progress = 0;
                Player.AddMaterial(material, 1 + Research.MORE_MATERIALS.getEffect());

                UpdateMatCount();
            }
        } else {
            AudioSystem.PlayAudio(AudioSystem.ANVIL_STRIKE);

            //Progress
            if (isCriticalHit) {
                progress += Player.itemForgeEffectiveness * curShakeBonusEffect * Player.criticalHitBonus;
            } else
                progress += Player.itemForgeEffectiveness * curShakeBonusEffect;

            if (progress >= smithingItem.requiredClicks) {
                progress = 0;
                Player.AddMoney(smithingItem.sellPrice * smithingItem.material.materialValue * smithingItem.material.offerMultiplierValue * Rank.GetRank(Player.xp).earningMultiplier * Player.serverBonus);
                Player.AddMaterial(smithingItem.material, -smithingItem.materialCost);
                Player.totalForgedItems++;
                MainActivity.endlessThread.particlePacks.add(new ParticlePack(R.drawable.particle_star, 60, 60, 3));

                UpdateMatCount();
            }
        }

        Player.totalClicks++;
        UpdateProgressBar();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public static void ChangeItem(Item newItem) {
        smithingItem = newItem;
        material = newItem.material;

        matIcon.setImageDrawable(ContextCompat.getDrawable(matIcon.getContext(), material.drawableId));
        UpdateMatCount();
        UpdateInfo();
    }

    public static void SwitchMode() {
        materialMode = !materialMode;
        UpdateInfo();
    }

    public static void UpdateInfo() {
        MainActivity.UpdateForgingWarning();

        if (materialMode)
            UpdateMatInfo();
        else
            UpdateItemInfo();
    }

    private static void UpdateItemInfo() {
        name.setText(smithingItem.name);
        light.setColorFilter(light.getResources().getColor(smithingItem.itemGroup.rarity.colorId));
        clickableItem.setImageDrawable(clickableItem.getResources().getDrawable(smithingItem.iconId));
        progressBar.setMax(smithingItem.requiredClicks);

        progress = 0;
        UpdateProgressBar();
    }

    private static void UpdateMatInfo() {
        name.setText(material.name);
        light.setColorFilter(light.getResources().getColor(smithingItem.itemGroup.rarity.colorId));
        clickableItem.setImageDrawable(clickableItem.getResources().getDrawable(material.drawableId));
        progressBar.setMax(material.requiredClicksToMine);

        progress = 0;
        UpdateProgressBar();
    }

    public static void UpdateMatCount() {
        matCount.setText((int) smithingItem.materialCost + "/" + (int) material.count);
    }

    public static void UpdateProgressBar() {
        progressBar.setProgress((int)progress);
    }
}
