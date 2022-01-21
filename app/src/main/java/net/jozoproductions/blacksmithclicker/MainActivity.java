package net.jozoproductions.blacksmithclicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.jozoproductions.blacksmithclicker.activities.CrateMenuActivity;
import net.jozoproductions.blacksmithclicker.activities.InfoActivity;
import net.jozoproductions.blacksmithclicker.activities.ItemAtlasActivity;
import net.jozoproductions.blacksmithclicker.activities.MaterialInfoActivity;
import net.jozoproductions.blacksmithclicker.activities.ProfileActivity;
import net.jozoproductions.blacksmithclicker.activities.ResearchActivity;
import net.jozoproductions.blacksmithclicker.audio.AudioSystem;
import net.jozoproductions.blacksmithclicker.crates.Crate;
import net.jozoproductions.blacksmithclicker.items.Item;
import net.jozoproductions.blacksmithclicker.items.Rarity;
import net.jozoproductions.blacksmithclicker.materials.Material;
import net.jozoproductions.blacksmithclicker.particlemanaging.ParticlePack;
import net.jozoproductions.blacksmithclicker.research.Research;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity implements SensorEventListener {

    public static Random random = new Random();

    public static EndlessThread endlessThread;

    public static float SCREEN_WIDTH;
    public static float SCREEN_HEIGHT;

    public static ConstraintLayout loadingScreen;

    private static TextView forgingWarning;

    private static SharedPreferences sharedPreferences;

    private Vibrator vibrator;

    public ImageView shakeBonusIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);

        //Screen size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        SCREEN_HEIGHT = displayMetrics.heightPixels;
        SCREEN_WIDTH = displayMetrics.widthPixels;

        //Fullscreen
        //getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //Initialize items & rarities
        Item.Init(this);
        Item.InitializeItemGroupItemsArrayLists();

        Rarity.init(getResources());

        //Load sounds
        AudioSystem.Init(this, 2);

        //Setup shake sensor
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        SensorManager sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorMgr.registerListener(this, sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        //24/7 thread
        shakeBonusIcon = findViewById(R.id.shakeBonusIcon);

        endlessThread = new EndlessThread(shakeBonusIcon);
        endlessThread.context = this;

        forgingWarning = findViewById(R.id.forgingWarning);

        Player.setMoneyText(findViewById(R.id.coin_count));

        SmithingItem.Initialize(findViewById(R.id.item_name), findViewById(R.id.smith_progress), findViewById(R.id.light), findViewById(R.id.clickable_item), findViewById(R.id.matIcon), findViewById(R.id.matCount));
        SmithingItem.ChangeItem(Item.STICK);

        ImageView clickableItem = findViewById(R.id.clickable_item);
        Animation itemClickAnim = AnimationUtils.loadAnimation(this, R.anim.item_click);

        ParticlePack.parent = findViewById(R.id.root);

        clickableItem.setOnTouchListener((v, event) -> {
            float x = event.getRawX();
            float y = event.getRawY();

            int actionId = event.getAction();
            if (actionId == MotionEvent.ACTION_DOWN || MotionEvent.ACTION_POINTER_DOWN == event.getActionMasked()) {
                boolean isCriticalHit = random.nextFloat() < Player.criticalHitChance;

                endlessThread.particlePacks.add(new ParticlePack(
                        SmithingItem.material.sparksDrawableId,
                        x,
                        y,
                        5
                ));

                clickableItem.startAnimation(itemClickAnim);
                SmithingItem.Click(x, y, isCriticalHit);
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

        findViewById(R.id.forgeMine_switch).setOnClickListener(v -> {
            SmithingItem.SwitchMode();
        });

        //Start endless thread
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(endlessThread, 0, 10, TimeUnit.MILLISECONDS);

        //Load save
        Load();

        //Setup preferences
        sharedPreferences = getSharedPreferences("blackSmithSlot1", MODE_PRIVATE);

        SmithingItem.UpdateMatCount();
        UpdateForgingWarning();
    }

    public static void UpdateForgingWarning() {
        if (!SmithingItem.material.isResearched()) {
            forgingWarning.setText("Material is not researched enough!");
        } else if (SmithingItem.material.count < SmithingItem.smithingItem.materialCost) {
            forgingWarning.setText("Not enough materials! Harvest some first!");
        } else {
            forgingWarning.setText("");
        }
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

    public static void Save() {
        //Convert items array to item names array
        Set<String> itemNames = new HashSet<>();

        Item[] items = Item.values();

        for (int i = 0; i < items.length; i++) {
            if (items[i].owningItem)
                itemNames.add(items[i].name());
        }

        //Setup preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Save
        editor.putString("name", Player.name);
        editor.putFloat("money", Player.money);
        editor.putFloat("xp", Player.xp);
        editor.putFloat("researchPoints", Player.researchPoints);

        //Save player stats
        editor.putInt("totalClicks", Player.totalClicks);
        editor.putInt("totalForgedItems", Player.totalForgedItems);
        editor.putInt("totalForgedMaterials", Player.totalForgedMaterials);
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

        //Save materials count
        Material[] materials = Material.values();

        for (int i = 0; i < materials.length; i++) {
            editor.putFloat("matcount_" + materials[i].name(), materials[i].count);
            editor.putInt("matresearch_" + materials[i].name(), materials[i].curResearches);
        }

        //Material market
        editor.putLong("marketRefreshTime", Material.MaterialMarket.nextOfferTime);

        for (int i = 0; i < materials.length; i++) {
            editor.putFloat("matMarketValue_" + materials[i].name(), materials[i].offerMultiplierValue);
        }

        //Server info
        editor.putString("playerContinent", Player.continentName);
        editor.putLong("nextTimeToInvest", Player.nextTimeToInvest);

        //Gold mine
        editor.putLong("currentMillisTime", System.currentTimeMillis());

        editor.putBoolean("first_time_launch", false);
        editor.commit();
    }

    private void Load() {
        //Happens every time, player launches game
        Player.UnlockItem(Item.STICK);

        //Load
        SharedPreferences sp = getSharedPreferences("blackSmithSlot1", MODE_PRIVATE);

        //Load player stats
        Player.totalClicks = sp.getInt("totalClicks", 0);
        Player.totalForgedItems = sp.getInt("totalForgedItems", 0);
        Player.totalForgedMaterials = sp.getInt("totalForgedMaterials", 0);
        Player.totalMoneyMade = sp.getInt("totalMoneyMade", 0);
        Player.totalCratesOpened = sp.getInt("totalCratesOpened", 0);

        Player.name = sp.getString("name", "Guest32557");
        Player.AddMoney(sp.getFloat("money", 0f), false);
        Player.xp = sp.getFloat("xp", Player.money);
        Player.researchPoints = sp.getFloat("researchPoints", 0);

        Set<String> itemNamesSet = sp.getStringSet("items", new HashSet<>());

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

        Player.CalculateResearchUpgrades();

        //Load materials count
        Material[] materials = Material.values();

        for (int i = 0; i < materials.length; i++) {
            materials[i].count = sp.getFloat("matcount_" + materials[i].name(), materials[i].count);
            materials[i].curResearches = sp.getInt("matresearch_" + materials[i].name(), materials[i].curResearches);
        }

        //Material market
        int positiveOfferCurPos = 0;
        int negativeOfferCurPos = 0;
        Material.MaterialMarket.GenerateNewOffer();

        Material.MaterialMarket.nextOfferTime = sp.getLong("marketRefreshTime", 0);

        for (int i = 0; i < materials.length; i++) {
            materials[i].offerMultiplierValue = sp.getFloat("matMarketValue_" + materials[i].name(), 1f);

            if (materials[i].offerMultiplierValue > 1f) {
                Material.MaterialMarket.positiveMatOffer[positiveOfferCurPos] = materials[i];
                positiveOfferCurPos++;
            } else if (materials[i].offerMultiplierValue < 1f) {
                Material.MaterialMarket.negativeMatOffer[negativeOfferCurPos] = materials[i];
                negativeOfferCurPos++;
            }
        }

        //Server info
        Player.continentName = sp.getString("playerContinent", getContinentName());
        Player.nextTimeToInvest = sp.getLong("nextTimeToInvest", 0);

        //Gold mine
        long lastMillisTime = sp.getLong("currentMillisTime", 0);

        if (Research.GOLD_MINE.curLevel > 0) {
            //Add money
            long difference = System.currentTimeMillis() - lastMillisTime;
            float moneyMade = difference / 10000f * Research.GOLD_MINE.curLevel;
            Player.AddMoney(moneyMade, true);

            //Message about gold mine
            AlertDialog.Builder message = new AlertDialog.Builder(this);
            message.setCancelable(false);
            message.setPositiveButton("Very gud", (dialog, which) -> {});

            message.setTitle("Gold mine");
            message.setMessage("While you were offline, you made " + (int) moneyMade + " €.");

            message.show();
        }
    }

    private void FirstTimeLaunch() {
        //Tutorial
        findViewById(R.id.guideHelpText).setVisibility(View.VISIBLE);

        //Message for new players
        AlertDialog.Builder message = new AlertDialog.Builder(this);
        message.setCancelable(false);
        message.setPositiveButton("OK", (dialog, which) -> {});

        message.setTitle("Welcome");
        message.setMessage("Welcome on Blacksmith Clicker. Don't forget to read guide for help or better understanding. I gave you some starting resources. Enjoy your stay :>");

        message.show();

        //Starting resources
        Player.AddMoney(120, true);
        Player.AddMaterial(Material.WOOD, 25);
        Player.AddMaterial(Material.STONE, 10);
    }

    private static String json_str = "{\"AD\":\"Europe\",\"AE\":\"Asia\",\"AF\":\"Asia\",\"AG\":\"North America\",\"AI\":\"North America\",\"AL\":\"Europe\",\"AM\":\"Asia\",\"AN\":\"North America\",\"AO\":\"Africa\",\"AQ\":\"Australia\",\"AR\":\"South America\",\"AS\":\"Australia\",\"AT\":\"Europe\",\"AU\":\"Australia\",\"AW\":\"North America\",\"AZ\":\"Asia\",\"BA\":\"Europe\",\"BB\":\"North America\",\"BD\":\"Asia\",\"BE\":\"Europe\",\"BF\":\"Africa\",\"BG\":\"Europe\",\"BH\":\"Asia\",\"BI\":\"Africa\",\"BJ\":\"Africa\",\"BM\":\"North America\",\"BN\":\"Asia\",\"BO\":\"South America\",\"BR\":\"South America\",\"BS\":\"North America\",\"BT\":\"Asia\",\"BW\":\"Africa\",\"BY\":\"Europe\",\"BZ\":\"North America\",\"CA\":\"North America\",\"CC\":\"Asia\",\"CD\":\"Africa\",\"CF\":\"Africa\",\"CG\":\"Africa\",\"CH\":\"Europe\",\"CI\":\"Africa\",\"CK\":\"Australia\",\"CL\":\"South America\",\"CM\":\"Africa\",\"CN\":\"Asia\",\"CO\":\"South America\",\"CR\":\"North America\",\"CU\":\"North America\",\"CV\":\"Africa\",\"CX\":\"Asia\",\"CY\":\"Asia\",\"CZ\":\"Europe\",\"DE\":\"Europe\",\"DJ\":\"Africa\",\"DK\":\"Europe\",\"DM\":\"North America\",\"DO\":\"North America\",\"DZ\":\"Africa\",\"EC\":\"South America\",\"EE\":\"Europe\",\"EG\":\"Africa\",\"EH\":\"Africa\",\"ER\":\"Africa\",\"ES\":\"Europe\",\"ET\":\"Africa\",\"FI\":\"Europe\",\"FJ\":\"Australia\",\"FK\":\"South America\",\"FM\":\"Australia\",\"FO\":\"Europe\",\"FR\":\"Europe\",\"GA\":\"Africa\",\"GB\":\"Europe\",\"GD\":\"North America\",\"GE\":\"Asia\",\"GF\":\"South America\",\"GG\":\"Europe\",\"GH\":\"Africa\",\"GI\":\"Europe\",\"GL\":\"North America\",\"GM\":\"Africa\",\"GN\":\"Africa\",\"GP\":\"North America\",\"GQ\":\"Africa\",\"GR\":\"Europe\",\"GS\":\"Australia\",\"GT\":\"North America\",\"GU\":\"Australia\",\"GW\":\"Africa\",\"GY\":\"South America\",\"HK\":\"Asia\",\"HN\":\"North America\",\"HR\":\"Europe\",\"HT\":\"North America\",\"HU\":\"Europe\",\"ID\":\"Asia\",\"IE\":\"Europe\",\"IL\":\"Asia\",\"IM\":\"Europe\",\"IN\":\"Asia\",\"IO\":\"Asia\",\"IQ\":\"Asia\",\"IR\":\"Asia\",\"IS\":\"Europe\",\"IT\":\"Europe\",\"JE\":\"Europe\",\"JM\":\"North America\",\"JO\":\"Asia\",\"JP\":\"Asia\",\"KE\":\"Africa\",\"KG\":\"Asia\",\"KH\":\"Asia\",\"KI\":\"Australia\",\"KM\":\"Africa\",\"KN\":\"North America\",\"KP\":\"Asia\",\"KR\":\"Asia\",\"KW\":\"Asia\",\"KY\":\"North America\",\"KZ\":\"Asia\",\"LA\":\"Asia\",\"LB\":\"Asia\",\"LC\":\"North America\",\"LI\":\"Europe\",\"LK\":\"Asia\",\"LR\":\"Africa\",\"LS\":\"Africa\",\"LT\":\"Europe\",\"LU\":\"Europe\",\"LV\":\"Europe\",\"LY\":\"Africa\",\"MA\":\"Africa\",\"MC\":\"Europe\",\"MD\":\"Europe\",\"ME\":\"Europe\",\"MG\":\"Africa\",\"MH\":\"Australia\",\"MK\":\"Europe\",\"ML\":\"Africa\",\"MM\":\"Asia\",\"MN\":\"Asia\",\"MO\":\"Asia\",\"MP\":\"Australia\",\"MQ\":\"North America\",\"MR\":\"Africa\",\"MS\":\"North America\",\"MT\":\"Europe\",\"MU\":\"Africa\",\"MV\":\"Asia\",\"MW\":\"Africa\",\"MX\":\"North America\",\"MY\":\"Asia\",\"MZ\":\"Africa\",\"NA\":\"Africa\",\"NC\":\"Australia\",\"NE\":\"Africa\",\"NF\":\"Australia\",\"NG\":\"Africa\",\"NI\":\"North America\",\"NL\":\"Europe\",\"NO\":\"Europe\",\"NP\":\"Asia\",\"NR\":\"Australia\",\"NU\":\"Australia\",\"NZ\":\"Australia\",\"OM\":\"Asia\",\"PA\":\"North America\",\"PE\":\"South America\",\"PF\":\"Australia\",\"PG\":\"Australia\",\"PH\":\"Asia\",\"PK\":\"Asia\",\"PL\":\"Europe\",\"PM\":\"North America\",\"PN\":\"Australia\",\"PR\":\"North America\",\"PS\":\"Asia\",\"PT\":\"Europe\",\"PW\":\"Australia\",\"PY\":\"South America\",\"QA\":\"Asia\",\"RE\":\"Africa\",\"RO\":\"Europe\",\"RS\":\"Europe\",\"RU\":\"Europe\",\"RW\":\"Africa\",\"SA\":\"Asia\",\"SB\":\"Australia\",\"SC\":\"Africa\",\"SD\":\"Africa\",\"SE\":\"Europe\",\"SG\":\"Asia\",\"SH\":\"Africa\",\"SI\":\"Europe\",\"SJ\":\"Europe\",\"SK\":\"Europe\",\"SL\":\"Africa\",\"SM\":\"Europe\",\"SN\":\"Africa\",\"SO\":\"Africa\",\"SR\":\"South America\",\"ST\":\"Africa\",\"SV\":\"North America\",\"SY\":\"Asia\",\"SZ\":\"Africa\",\"TC\":\"North America\",\"TD\":\"Africa\",\"TF\":\"Australia\",\"TG\":\"Africa\",\"TH\":\"Asia\",\"TJ\":\"Asia\",\"TK\":\"Australia\",\"TM\":\"Asia\",\"TN\":\"Africa\",\"TO\":\"Australia\",\"TR\":\"Asia\",\"TT\":\"North America\",\"TV\":\"Australia\",\"TW\":\"Asia\",\"TZ\":\"Africa\",\"UA\":\"Europe\",\"UG\":\"Africa\",\"US\":\"North America\",\"UY\":\"South America\",\"UZ\":\"Asia\",\"VC\":\"North America\",\"VE\":\"South America\",\"VG\":\"North America\",\"VI\":\"North America\",\"VN\":\"Asia\",\"VU\":\"Australia\",\"WF\":\"Australia\",\"WS\":\"Australia\",\"YE\":\"Asia\",\"YT\":\"Africa\",\"ZA\":\"Africa\",\"ZM\":\"Africa\",\"ZW\":\"Africa\"}";

    public String getContinentName() {
        try {
            JSONObject jsonObject = new JSONObject(json_str);
            String locale = getResources().getConfiguration().locale.getCountry();
            return jsonObject.getString(locale);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "Invalid";
    }

    //Some random code
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        mAccelLast = mAccelCurrent;
        mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
        float delta = mAccelCurrent - mAccelLast;
        mAccel = mAccel * 0.9f + delta;

        if (mAccel > 35) {

            //Shake
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(500);
            }

            //Activate shake bonus
            Player.curShakeBonusEffect = 3f + Research.SHAKE_BONUS_EFFICIENCY.getEffect();
            Player.shakeBonusCountDown = 500 + random.nextInt(1000);
            Player.shakeBonusActivated = true;
            Player.shakeBonusReady = false;
            shakeBonusIcon.setVisibility(View.INVISIBLE);

            Toast.makeText(getApplicationContext(), "Shake bonus activated", Toast.LENGTH_SHORT).show();
        }
    }

    @Override public void onAccuracyChanged(Sensor sensor, int i) {}
}