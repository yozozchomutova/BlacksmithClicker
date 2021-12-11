package net.jozoproductions.blacksmithclicker.fragments;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
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

import androidx.constraintlayout.widget.ConstraintLayout;
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
import net.jozoproductions.blacksmithclicker.views.ItemInCrateView;

import java.util.ArrayList;
import java.util.Random;

public class CrateOpenFragment extends Fragment {

    private static final Random random = new Random();

    private TextView moneyTV;

    private ImageButton buyCrate;
    private TextView priceTV;

    public Crate openingCrate;

    private ConstraintLayout root;

    private SurfaceView itemTiles;

    private ConstraintLayout buyPanel;
    private ConstraintLayout skipPanel;

    private int maxTilesOnScreen;

    private OpeningCrateThread openingCrateThread;

    //public ItemInCrateView[] itemTiles;

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
                    Player.AddMoney(-cratePrice);

                    //Hide buy panel & Show skip panel
                    //buyPanel.setVisibility(View.GONE);
                    //skipPanel.setVisibility(View.VISIBLE);

                    //Roll the roulette
                    openingCrateThread = new OpeningCrateThread(CrateOpenFragment.this);
                    openingCrateThread.setDaemon(true);
                    openingCrateThread.start();
                }
            }
        });

        view.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new CrateListFragment()).commit();
            }
        });

        updatePriceTV();

        //Perfom some calculations before generating tiles
        maxTilesOnScreen = (int) Math.floor(MainActivity.SCREEN_WIDTH / 192f) + 2;

        //generateItemTiles(root);
    }

    private void updatePriceTV() {
        moneyTV.setText("" + (int) Player.money);
        priceTV.setText("" + (int) openingCrate.getRealPrice());
    }

    /*private void generateItemTiles(ConstraintLayout root) {
        itemTiles = new ItemInCrateView[maxTilesOnScreen];

        for (int i = 0; i < maxTilesOnScreen; i++) {
            //Pick random Item
            ItemInCrateView itemInCrateView = new ItemInCrateView(getContext());

            pickRandomItemForItemInCrate(itemInCrateView);

            itemInCrateView.setTranslationX(i * 192f);
            itemInCrateView.setTranslationY(MainActivity.SCREEN_HEIGHT/2f-129f);

            root.addView(itemInCrateView, 0);

            itemTiles[i] = itemInCrateView;
        }
    }*/

    public void pickRandomItemForItemInCrate(ItemInCrateView itemInCrateView) {
        float randomNum = random.nextFloat() * 100f;

        if (openingCrate == Crate.CHRISTMAS_CRATE) {
            itemInCrateView.setItem(randomItem(Rarity.CHRISTMAS));
        }

        else if (randomNum <= openingCrate.MYTHIC_CHANCE) {
            itemInCrateView.setItem(randomItem(Rarity.MYTHIC));
        } else if (randomNum <= openingCrate.LEGENDARY_CHANCE) {
            itemInCrateView.setItem(randomItem(Rarity.LEGENDARY));
        } else if (randomNum <= openingCrate.EPIC_CHANCE) {
            itemInCrateView.setItem(randomItem(Rarity.EPIC));
        } else if (randomNum <= openingCrate.RARE_CHANCE) {
            itemInCrateView.setItem(randomItem(Rarity.RARE));
        } else if (randomNum <= openingCrate.UNCOMMON_CHANCE) {
            itemInCrateView.setItem(randomItem(Rarity.UNCOMMON));
        } else {
            itemInCrateView.setItem(randomItem(Rarity.COMMON));
        }
    }

    private Item randomItem(Rarity rarity) {
        Item[] itemList = rarity.items.toArray(new Item[0]);
        return itemList[random.nextInt(itemList.length)];
    }

    private void onOpeningCrateFinish() {

    }

    public static class OpeningCrateThread extends Thread {

        private float speed;
        private float speedDecreaseRate = 0.2f;
        private float curX;

        private int curItemTileId = 0;

        private Item[] generatedItemTile;

        //Calculated
        private float timeToCompleteStop;
        private float trajectoryLength;

        private CrateOpenFragment crateOpenFragment;

        private volatile boolean run = true;

        private static Paint defaultPaint;

        private static Bitmap commonTile;
        private static Bitmap uncommonTile;
        private static Bitmap rareTile;
        private static Bitmap epicTile;
        private static Bitmap legendaryTile;
        private static Bitmap mythicTile;

        public OpeningCrateThread(CrateOpenFragment crateOpenFragment) {
            this.crateOpenFragment = crateOpenFragment;
            defaultPaint = new Paint();

            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inScaled = false;

            commonTile = BitmapFactory.decodeResource(crateOpenFragment.getResources(), R.drawable.item_tile_common, opts);
            uncommonTile = BitmapFactory.decodeResource(crateOpenFragment.getResources(), R.drawable.item_tile_uncommon, opts);
            rareTile = BitmapFactory.decodeResource(crateOpenFragment.getResources(), R.drawable.item_tile_rare, opts);
            epicTile = BitmapFactory.decodeResource(crateOpenFragment.getResources(), R.drawable.item_tile_epic, opts);
            legendaryTile = BitmapFactory.decodeResource(crateOpenFragment.getResources(), R.drawable.item_tile_legendary, opts);
            mythicTile = BitmapFactory.decodeResource(crateOpenFragment.getResources(), R.drawable.item_tile_mythic, opts);
        }

        @Override
        public void run() {
            speed = 20f;
            run = true;

            curItemTileId = 0;

            timeToCompleteStop = speed / speedDecreaseRate;
            trajectoryLength = speed * timeToCompleteStop - 0.5f * speedDecreaseRate * (timeToCompleteStop * timeToCompleteStop);//KINEMATIC: s = v0 * t - 1/2 * a * t2

            //Generate item
            int itemTilesSize = (int) (trajectoryLength / 192f) + 1;

            while (run) {
                try {
                    curX += speed;
                    speed -= speedDecreaseRate;

                    Canvas canvas1 = crateOpenFragment.itemTiles.getHolder().lockCanvas();

                    for (int i = 0; i < crateOpenFragment.maxTilesOnScreen; i++) {
                        canvas1.drawBitmap(commonTile, i * 192f - curX, 0, defaultPaint);

                        canvas1.drawBitmap(commonTile, i * 192f - curX, 0, defaultPaint);
                    }

                    crateOpenFragment.itemTiles.getHolder().unlockCanvasAndPost(canvas1);

                    if (curX >= 192f) {
                        curX -= 192f;
                        curItemTileId++;

                    /*for (int i = 0; i < crateOpenFragment.maxTilesOnScreen-1; i++) {
                        crateOpenFragment.itemTiles[i].setItem(crateOpenFragment.itemTiles[i+1].item);
                    }*/

                        //crateOpenFragment.pickRandomItemForItemInCrate(crateOpenFragment.itemTiles[crateOpenFragment.itemTiles.length-1]);
                    }

                    //End?
                    if (speed <= 0f) {
                        crateOpenFragment.onOpeningCrateFinish();
                        run = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
