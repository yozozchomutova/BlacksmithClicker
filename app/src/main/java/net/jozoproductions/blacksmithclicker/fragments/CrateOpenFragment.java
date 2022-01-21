package net.jozoproductions.blacksmithclicker.fragments;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import net.jozoproductions.blacksmithclicker.MainActivity;
import net.jozoproductions.blacksmithclicker.Player;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.audio.AudioSystem;
import net.jozoproductions.blacksmithclicker.crates.Crate;
import net.jozoproductions.blacksmithclicker.items.Item;
import net.jozoproductions.blacksmithclicker.items.Rarity;
import net.jozoproductions.blacksmithclicker.research.Research;

import java.util.Random;

public class CrateOpenFragment extends Fragment {

    private static final Random random = new Random();
    private static float lastPrice;

    private TextView moneyTV;

    private ImageButton buyCrate;
    private TextView priceTV;

    public Crate openingCrate;

    private ConstraintLayout root;

    private SurfaceView itemTiles;

    private ConstraintLayout buyPanel;
    private ConstraintLayout skipPanel;

    private int maxTilesOnScreen;

    public OpeningCrateThread openingCrateThread;

    //ItemPreview root
    private ConstraintLayout itemPreviewRoot;

    private TextView itemOwningInfo;
    private TextView itemName;
    private ImageView itemRarity;
    private ImageView itemIcon;

    private ImageView materialIcon;
    private TextView materialProgress;

    public CrateOpenFragment(Crate openingCrate) {
        super(R.layout.fragment_crate_open);

        this.openingCrate = openingCrate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crate_open, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialize
        moneyTV = view.findViewById(R.id.money);

        priceTV = view.findViewById(R.id.price);
        buyCrate = view.findViewById(R.id.buy);

        root = view.findViewById(R.id.root);

        itemTiles = view.findViewById(R.id.itemTiles);

        buyPanel = view.findViewById(R.id.buy_panel);
        skipPanel = view.findViewById(R.id.skip_panel);

        itemTiles = view.findViewById(R.id.itemTiles);

        //Item preview root
        itemPreviewRoot = view.findViewById(R.id.itemPreviewRoot);

        itemOwningInfo = view.findViewById(R.id.item_owning_info);
        itemName = view.findViewById(R.id.item_name);
        itemRarity = view.findViewById(R.id.item_rarity);
        itemIcon = view.findViewById(R.id.item_icon);

        materialIcon = view.findViewById(R.id.mat_icon);
        materialProgress = view.findViewById(R.id.mat_progress);

        //crate icon & crate name
        ((ImageView) view.findViewById(R.id.crate_icon)).setImageDrawable(ContextCompat.getDrawable(view.getContext(), openingCrate.drawableId));
        ((TextView) view.findViewById(R.id.crate_name)).setText(openingCrate.name);

        //Listeners
        buyCrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Player has money?
                float cratePrice = openingCrate.getRealPrice();

                if (Player.money >= cratePrice) {
                    lastPrice = cratePrice;

                    Player.AddMoney(-cratePrice);
                    openingCrate.openCount++;

                    //Hide buy panel & Show skip panel
                    buyPanel.setVisibility(View.GONE);
                    skipPanel.setVisibility(View.VISIBLE);

                    //Roll the roulette
                    openingCrateThread = new OpeningCrateThread(CrateOpenFragment.this);
                    openingCrateThread.setDaemon(true);
                    openingCrateThread.start();

                    updatePriceTV();
                }
            }
        });

        view.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new CrateListFragment()).commit();
            }
        });

        view.findViewById(R.id.skip_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openingCrateThread.run = false;
                onOpeningCrateFinish(openingCrateThread.droppedItem);
            }
        });

        updatePriceTV();

        //Perform some calculations before generating tiles
        maxTilesOnScreen = (int) Math.floor(MainActivity.SCREEN_WIDTH / 192f) + 2;
    }

    private void updatePriceTV() {
        moneyTV.setText("" + (int) Player.money);
        priceTV.setText("" + (int) openingCrate.getRealPrice());
    }

    public Item pickRandomItemForItemInCrate() {
        float randomNum = random.nextFloat() * 100f;

        if (openingCrate == Crate.CHRISTMAS_CRATE) {
            return randomItem(Rarity.CHRISTMAS);
        }

        else if (randomNum <= openingCrate.MYTHIC_CHANCE) {
            return randomItem(Rarity.MYTHIC);
        } else if (randomNum <= openingCrate.LEGENDARY_CHANCE) {
            return randomItem(Rarity.LEGENDARY);
        } else if (randomNum <= openingCrate.EPIC_CHANCE) {
            return randomItem(Rarity.EPIC);
        } else if (randomNum <= openingCrate.RARE_CHANCE) {
            return randomItem(Rarity.RARE);
        } else if (randomNum <= openingCrate.UNCOMMON_CHANCE) {
            return randomItem(Rarity.UNCOMMON);
        } else {
            return randomItem(Rarity.COMMON);
        }
    }

    private Item randomItem(Rarity rarity) {
        Item[] itemList = rarity.items.toArray(new Item[0]);
        return itemList[random.nextInt(itemList.length)];
    }

    private void onOpeningCrateFinish(Item droppedItem) {
        droppedItem.material.curResearches = Math.min(droppedItem.material.requiredResearches, droppedItem.material.curResearches + 1 + (int) Research.FASTER_MATERIAL_RESEARCH.getEffect());
        Player.totalCratesOpened++;

        ((AppCompatActivity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                itemName.setText(droppedItem.name);
                itemRarity.setColorFilter(getResources().getColor(droppedItem.itemGroup.rarity.colorId));
                itemIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), droppedItem.iconId));

                materialIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), droppedItem.material.drawableId));
                materialProgress.setText(droppedItem.material.name + ": " + droppedItem.material.curResearches + "/" + droppedItem.material.requiredResearches);

                itemPreviewRoot.setVisibility(View.VISIBLE);

                buyPanel.setVisibility(View.VISIBLE);
                skipPanel.setVisibility(View.GONE);

                //Is it new item or player already has item?
                if (droppedItem.owningItem) {
                    float moneyToRefund = lastPrice * Research.CRATE_REFUND.getEffect();
                    Player.AddMoney(moneyToRefund, true);
                    updatePriceTV();

                    itemOwningInfo.setText("Money refunded: " + ((int) moneyToRefund) + " (" + ((int) (Research.CRATE_REFUND.getEffect()*100)) + "%)");
                } else {
                    Player.UnlockItem(droppedItem);

                    itemOwningInfo.setText("New item!");
                }
            }
        });
    }

    public static class OpeningCrateThread extends Thread {

        private float speed;
        private float speedDecreaseRate = 0.12f;
        private float curX;

        private int curItemTileId = 0;

        private Item[] generatedItemTile;

        //Calculated
        private float timeToCompleteStop;
        private float trajectoryLength;

        private CrateOpenFragment crateOpenFragment;

        private volatile boolean run = true;

        private Item droppedItem;

        private static Paint defaultPaint;

        public OpeningCrateThread(CrateOpenFragment crateOpenFragment) {
            this.crateOpenFragment = crateOpenFragment;
            defaultPaint = new Paint();
        }

        @Override
        public void run() {
            speed = (random.nextInt(20) + 30);
            run = true;

            curItemTileId = 0;

            timeToCompleteStop = speed / speedDecreaseRate;
            trajectoryLength = speed * timeToCompleteStop - 0.5f * speedDecreaseRate * (timeToCompleteStop * timeToCompleteStop);//KINEMATIC: s = v0 * t - 1/2 * a * t2

            //Generate item
            int itemTilesSize = (int) (trajectoryLength / 192f) + crateOpenFragment.maxTilesOnScreen + 1;
            generatedItemTile = new Item[itemTilesSize];

            for (int i = 0; i < itemTilesSize; i++) {
                generatedItemTile[i] = crateOpenFragment.pickRandomItemForItemInCrate();
            }

            droppedItem = generatedItemTile[(int) ((trajectoryLength + MainActivity.SCREEN_WIDTH/2f) / 192f)];

            while (run) {
                try {
                    curX += speed;
                    speed -= speedDecreaseRate;

                    Canvas canvas1 = crateOpenFragment.itemTiles.getHolder().lockCanvas();

                    defaultPaint.setColorFilter(null);

                    for (int i = 0; i < crateOpenFragment.maxTilesOnScreen; i++) {
                        canvas1.drawBitmap(generatedItemTile[i + curItemTileId].itemGroup.rarity.itemTileBcg, i * 192f - curX, 0, defaultPaint);
                    }

                    defaultPaint.setColorFilter(new PorterDuffColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN));

                    for (int i = 0; i < crateOpenFragment.maxTilesOnScreen; i++) {
                        canvas1.drawBitmap(generatedItemTile[i + curItemTileId].itemShadow, i * 192f - curX + 16, 48, defaultPaint);
                    }

                    crateOpenFragment.itemTiles.getHolder().unlockCanvasAndPost(canvas1);

                    if (curX >= 192f) {
                        curX -= 192f;
                        curItemTileId++;
                        AudioSystem.PlayAudio(AudioSystem.CLICK1);
                    }

                    //End?
                    if (speed <= 0f) {
                        crateOpenFragment.onOpeningCrateFinish(droppedItem);
                        run = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
