package net.jozoproductions.blacksmithclicker.activities;

import static net.jozoproductions.blacksmithclicker.MainActivity.Save;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import net.jozoproductions.blacksmithclicker.Player;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.rank.Rank;
import net.jozoproductions.blacksmithclicker.research.Research;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class WorldMatsActivity extends AppCompatActivity {

    private static FirebaseFirestore worldMatsFF;

    //Fetched moneys
    private int europeMoney;
    private int northAmericaMoney;
    private int southAmericaMoney;
    private int asiaMoney;
    private int africaMoney;
    private int australiaMoney;

    //Continent data
    private TextView cont1Title;
    private TextView cont1Money;

    private TextView cont2Title;
    private TextView cont2Money;

    private TextView cont3Title;
    private TextView cont3Money;

    private TextView cont4Title;
    private TextView cont4Money;

    private TextView cont5Title;
    private TextView cont5Money;

    private TextView cont6Title;
    private TextView cont6Money;

    private TextView yourCountryTV;
    private TextView yourBonusTV;

    private SeekBar investAmountPercentage;
    private TextView investMoneyTxt;
    private ImageButton investBtn;

    private int curMoneyInInvestment = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_worldmats);

        //
        yourCountryTV = findViewById(R.id.yourCont);
        yourBonusTV = findViewById(R.id.yourBonus);

        investAmountPercentage = findViewById(R.id.investAmount);
        investMoneyTxt = findViewById(R.id.investMoneyTxt);
        investBtn = findViewById(R.id.investBtn);

        //Continent data
        cont1Title = findViewById(R.id.country1Title);
        cont1Money = findViewById(R.id.country1Money);

        cont2Title = findViewById(R.id.country2Title);
        cont2Money = findViewById(R.id.country2Money);

        cont3Title = findViewById(R.id.country3Title);
        cont3Money = findViewById(R.id.country3Money);

        cont4Title = findViewById(R.id.country4Title);
        cont4Money = findViewById(R.id.country4Money);

        cont5Title = findViewById(R.id.country5Title);
        cont5Money = findViewById(R.id.country5Money);

        cont6Title = findViewById(R.id.country6Title);
        cont6Money = findViewById(R.id.country6Money);

        //Firebase init
        init();

        //Listeners
        findViewById(R.id.close_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        investAmountPercentage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                curMoneyInInvestment = (int) (Player.money / 100f * i);
                investMoneyTxt.setText("" + curMoneyInInvestment);
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        //Apply data to UI
        yourCountryTV.setText("Your continent: " + Player.continentName);
        yourBonusTV.setText("Your bonus: None");

        //Invest btn
        //Is avalable?
        if (Player.xp < Rank.GOLD.neededExpToAdvance) {
            investBtn.setEnabled(false);
            investBtn.setImageDrawable(ContextCompat.getDrawable(WorldMatsActivity.this, R.drawable.rank_platinum));
        }

        investBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check for spam
                long curTime = System.currentTimeMillis();

                if (curMoneyInInvestment < 10) {
                    Toast.makeText(WorldMatsActivity.this, "Invest at least 10 €.", Toast.LENGTH_SHORT).show();
                } else if (curTime >= Player.nextTimeToInvest) {
                    //First of all, get data
                    worldMatsFF.collection("worldMats").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().isEmpty()) {
                                    Toast.makeText(WorldMatsActivity.this, "Error downloading data. (Empty)", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                DocumentSnapshot matsDoc = task.getResult().getDocuments().get(0);

                                //Fetch money
                                europeMoney = Integer.parseInt(matsDoc.get("europeMoney").toString());
                                northAmericaMoney = Integer.parseInt(matsDoc.get("northAmericaMoney").toString());
                                southAmericaMoney = Integer.parseInt(matsDoc.get("southAmericaMoney").toString());
                                asiaMoney = Integer.parseInt(matsDoc.get("asiaMoney").toString());
                                africaMoney = Integer.parseInt(matsDoc.get("africaMoney").toString());
                                australiaMoney = Integer.parseInt(matsDoc.get("australiaMoney").toString());

                                float additionalBonus = curMoneyInInvestment * Research.BOOST_CONTINENT.getEffect();

                                switch (Player.continentName) {
                                    case "Europe":
                                        europeMoney += curMoneyInInvestment + additionalBonus;
                                        break;
                                    case "North America":
                                        northAmericaMoney += curMoneyInInvestment + additionalBonus;
                                        break;
                                    case "South America":
                                        southAmericaMoney += curMoneyInInvestment + additionalBonus;
                                        break;
                                    case "Asia":
                                        asiaMoney += curMoneyInInvestment + additionalBonus;
                                        break;
                                    case "Africa":
                                        africaMoney += curMoneyInInvestment + additionalBonus;
                                        break;
                                    case "Australia":
                                        australiaMoney += curMoneyInInvestment + additionalBonus;
                                        break;
                                    default:
                                        break;
                                }

                                // Create a new user with a first and last name
                                Map<String, Object> continentMoneys = new HashMap<>();
                                continentMoneys.put("europeMoney", europeMoney);
                                continentMoneys.put("northAmericaMoney", northAmericaMoney);
                                continentMoneys.put("southAmericaMoney", southAmericaMoney);
                                continentMoneys.put("asiaMoney", asiaMoney);
                                continentMoneys.put("africaMoney", africaMoney);
                                continentMoneys.put("australiaMoney", australiaMoney);

                                // Add a new document with a generated ID
                                worldMatsFF.collection("worldMats").document("mats")
                                        .update(continentMoneys)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Player.AddMoney(-curMoneyInInvestment);
                                                    Player.nextTimeToInvest = System.currentTimeMillis() + 43200000;

                                                    Toast.makeText(WorldMatsActivity.this, "Money successfully added!", Toast.LENGTH_SHORT).show();
                                                    updateMoneyTexts();
                                                    investAmountPercentage.setProgress(1);
                                                } else {
                                                    Toast.makeText(WorldMatsActivity.this, "Error sending money!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(WorldMatsActivity.this, "Failed - Check your internet connection", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    int difference = (int) ((Player.nextTimeToInvest - curTime) / 600f);
                    Toast.makeText(WorldMatsActivity.this, "You can invest again in " + (difference / 100f) + " minutes.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        Save();
        super.onPause();
    }

    public void init() {
        try {
            worldMatsFF = FirebaseFirestore.getInstance();
        } catch (Exception e) {
            Toast.makeText(this, "Server may doesn't exist", Toast.LENGTH_SHORT).show();
            finish();
        }

        //Setup bonuses array
        List<Float> bonuses = new ArrayList<>();
        bonuses.add(1.2f);
        bonuses.add(1.15f);
        bonuses.add(1.1f);
        bonuses.add(1.05f);
        bonuses.add(1f);
        bonuses.add(1f);

        //Get money data
        worldMatsFF.collection("worldMats").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                    Toast.makeText(WorldMatsActivity.this, "Error downloading data. (Empty)", Toast.LENGTH_SHORT).show();
                    return;
                }

                DocumentSnapshot matsDoc = queryDocumentSnapshots.getDocuments().get(0);

                //Fetch money
                europeMoney = Integer.parseInt(matsDoc.get("europeMoney").toString());
                northAmericaMoney = Integer.parseInt(matsDoc.get("northAmericaMoney").toString());
                southAmericaMoney = Integer.parseInt(matsDoc.get("southAmericaMoney").toString());
                asiaMoney = Integer.parseInt(matsDoc.get("asiaMoney").toString());
                africaMoney = Integer.parseInt(matsDoc.get("africaMoney").toString());
                australiaMoney = Integer.parseInt(matsDoc.get("australiaMoney").toString());

                List<String> sortedTitles = updateMoneyTexts();

                //Give player bonus
                for (int i = 0; i < sortedTitles.size(); i++) {
                    if (sortedTitles.get(i).equals(Player.continentName)) {
                        Player.serverBonus = bonuses.get(i) + bonuses.get(i) * Research.BRIBE_CONTINENT.getEffect();
                        yourBonusTV.setText("Your bonus: " + Player.serverBonus + "x money boost.");
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(WorldMatsActivity.this, "Error downloading data. (Fail)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<String> updateMoneyTexts() {
        List<String> sortedTitles = new ArrayList<>();
        List<Integer> sortedMoney = new ArrayList<>();

        //Sort
        AddContinentToList(sortedTitles, sortedMoney, "Europe", europeMoney);
        AddContinentToList(sortedTitles, sortedMoney, "North America", northAmericaMoney);
        AddContinentToList(sortedTitles, sortedMoney, "South America", southAmericaMoney);
        AddContinentToList(sortedTitles, sortedMoney, "Asia", asiaMoney);
        AddContinentToList(sortedTitles, sortedMoney, "Africa", africaMoney);
        AddContinentToList(sortedTitles, sortedMoney, "Australia", australiaMoney);

        //Set text
        cont1Title.setText("1# - " + sortedTitles.get(0));
        cont1Money.setText("" + sortedMoney.get(0));
        cont2Title.setText("2# - " + sortedTitles.get(1));
        cont2Money.setText("" + sortedMoney.get(1));
        cont3Title.setText("3# - " + sortedTitles.get(2));
        cont3Money.setText("" + sortedMoney.get(2));
        cont4Title.setText("4# - " + sortedTitles.get(3));
        cont4Money.setText("" + sortedMoney.get(3));
        cont5Title.setText("5# - " + sortedTitles.get(4));
        cont5Money.setText("" + sortedMoney.get(4));
        cont6Title.setText("6# - " + sortedTitles.get(5));
        cont6Money.setText("" + sortedMoney.get(5));

        return sortedTitles;
    }

    private void AddContinentToList(List<String> sortedNames, List<Integer> sortedMoney, String contName, int money) {
        int addIndex = 0;

        for (int i = 0; i < sortedMoney.size(); i++) {
            if (sortedMoney.get(i) > money) {
                addIndex++;
            }
        }

        sortedNames.add(addIndex, contName);
        sortedMoney.add(addIndex, money);
    }
}
