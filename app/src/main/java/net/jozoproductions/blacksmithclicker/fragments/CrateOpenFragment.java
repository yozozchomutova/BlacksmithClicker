package net.jozoproductions.blacksmithclicker.fragments;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import net.jozoproductions.blacksmithclicker.MainActivity;
import net.jozoproductions.blacksmithclicker.Player;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.crates.Crate;
import net.jozoproductions.blacksmithclicker.crates.CrateItem;
import net.jozoproductions.blacksmithclicker.items.Item;
import net.jozoproductions.blacksmithclicker.items.Rarity;
import net.jozoproductions.blacksmithclicker.particlemanaging.ParticlePack;

import java.util.ArrayList;
import java.util.Random;

public class CrateOpenFragment extends Fragment {

    private static final Random random = new Random();

    private ImageButton openBtn;
    private TextView guaranteedRarityTV;
    private TextView nextRarityChanceTV;

    public static Crate openingCrate;
    public static Rarity curRarity;
    public static boolean stillOpening;

    public CrateOpenFragment() {
        super(R.layout.fragment_crate_open);
        curRarity = Rarity.COMMON;
        stillOpening = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crate_open, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialize texts
        guaranteedRarityTV = view.findViewById(R.id.garantuedTV);
        nextRarityChanceTV = view.findViewById(R.id.nextRarityTV);

        UpdateTexts();

        openBtn = view.findViewById(R.id.anvil);

        Animation itemClickAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.item_click);

        openBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Visuals
                    MainActivity.endlessThread.particlePacks.add(new ParticlePack(
                            R.drawable.particle_spark,
                            event.getRawX(),
                            event.getRawY(),
                            10
                    ));
                    openBtn.startAnimation(itemClickAnim);

                    //Code
                    if (stillOpening) {
                        float randomNumber = random.nextFloat() * 100f;
                        float percentageRequired;

                        //Setup percentageRequired
                        if (curRarity == Rarity.COMMON) {
                            percentageRequired = openingCrate.UNCOMMON_CHANCE;
                        } else if (curRarity == Rarity.UNCOMMON) {
                            percentageRequired = openingCrate.RARE_CHANCE;
                        } else if (curRarity == Rarity.RARE) {
                            percentageRequired = openingCrate.EPIC_CHANCE;
                        } else if (curRarity == Rarity.EPIC) {
                            percentageRequired = openingCrate.LEGENDARY_CHANCE;
                        } else if (curRarity == Rarity.LEGENDARY) {
                            percentageRequired = openingCrate.MYTHIC_CHANCE;
                        } else {
                            percentageRequired = 0f;
                        }

                        if (randomNumber < percentageRequired) {
                            //Next level
                            curRarity = Rarity.NextRarity(curRarity);

                            UpdateTexts();
                            ((ImageView) view.findViewById(R.id.background)).setColorFilter(ContextCompat.getColor(getContext(), curRarity.colorId));

                            //Is last/Reached end?
                            if (curRarity == null) {
                                curRarity = Rarity.MYTHIC;
                                stillOpening = false;
                                DrawRandomItem();
                            }
                        } else {
                            stillOpening = false;

                            //Drop random item and show it to player
                            Item droppedItem = DrawRandomItem();
                            openBtn.setImageDrawable(getResources().getDrawable(droppedItem.iconId));

                            //Update texts
                            int rarityColor = ContextCompat.getColor(getContext(), droppedItem.rarity.colorId);

                            guaranteedRarityTV.setText(droppedItem.name);
                            nextRarityChanceTV.setText(droppedItem.rarity.name + " Item");
                            nextRarityChanceTV.setTextColor(rarityColor);
                            ((ImageView) view.findViewById(R.id.background)).setColorFilter(rarityColor);
                        }
                    } else {
                        //Finish activity
                        AppCompatActivity curActivity = (AppCompatActivity) getActivity();
                        curActivity.finish();
                    }
                }
                return false;
            }
        });
    }

    public void UpdateTexts() {
        guaranteedRarityTV.setText("Guaranteed rarity: " + curRarity.name);

        Rarity nextRarity = Rarity.NextRarity(curRarity);

        if (nextRarity != null) {
            switch (nextRarity) {
                case UNCOMMON:
                    SetNextRarityChanceTV(nextRarity, openingCrate.UNCOMMON_CHANCE, R.color.rarity_uncommon);
                    break;
                case RARE:
                    SetNextRarityChanceTV(nextRarity, openingCrate.RARE_CHANCE, R.color.rarity_rare);
                    break;
                case EPIC:
                    SetNextRarityChanceTV(nextRarity, openingCrate.EPIC_CHANCE, R.color.rarity_epic);
                    break;
                case LEGENDARY:
                    SetNextRarityChanceTV(nextRarity, openingCrate.LEGENDARY_CHANCE, R.color.rarity_legendary);
                    break;
                case MYTHIC:
                    SetNextRarityChanceTV(nextRarity, openingCrate.MYTHIC_CHANCE, R.color.rarity_mythic);
                    break;
            }
        } else {
            nextRarityChanceTV.setText("MAX");
        }
    }

    private void SetNextRarityChanceTV(Rarity nextRarity, float chance, int colorId) {
        nextRarityChanceTV.setText("Chance of " + nextRarity.name + " rarity: " + chance + "%");
        nextRarityChanceTV.setTextColor(ContextCompat.getColor(getContext(), colorId));
    }

    private Item DrawRandomItem() {
        ArrayList<Item> items = curRarity.items;
        Item randomizedItem;

        if (!items.isEmpty()) {
            int randomItemIndex = random.nextInt(items.size());
            randomizedItem = items.get(randomItemIndex);
        } else {
            //Add default item
            randomizedItem = Item.STICK;
        }

        Player.AddItemRecipe(randomizedItem);
        return randomizedItem;
    }
}
