package net.jozoproductions.blacksmithclicker.crates;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import net.jozoproductions.blacksmithclicker.Player;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.fragments.CrateOpenFragment;
import net.jozoproductions.blacksmithclicker.rank.Rank;

public class CrateItem extends ConstraintLayout {

    private Crate crate;

    private int price;

    private boolean unlocked = true;

    public CrateItem(@NonNull Context context) {
        super(context);
    }

    public CrateItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CrateItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void Inflate(Context context, Crate crate_, float basePrice, int openCount, boolean currentlyAvailable) {
        this.crate = crate_;

        this.price = CalculateCratePrice(basePrice, openCount);

        View inflatedView = LayoutInflater.from(context).inflate(R.layout.item_crate, this, true);

        //Fetch views
        TextView nameTV = inflatedView.findViewById(R.id.name);
        TextView priceTV = inflatedView.findViewById(R.id.price);
        ImageView iconIV = inflatedView.findViewById(R.id.icon);
        ImageView rarityIV = inflatedView.findViewById(R.id.rarity);
        ImageView buyBtn = inflatedView.findViewById(R.id.buy);

        //Modify views
        nameTV.setText(crate.name);
        priceTV.setText("" + price);
        iconIV.setImageDrawable(getResources().getDrawable(crate.drawableId));
        rarityIV.setColorFilter(ContextCompat.getColor(context, crate.colorId), android.graphics.PorterDuff.Mode.MULTIPLY);

        //Unlocked?
        if (crate.minimalRequirementRank.ordinal() == 0) {}
        else if (Rank.values()[crate.minimalRequirementRank.ordinal()-1].neededExpToAdvance > Player.xp) {
            unlocked = false;
            buyBtn.setImageDrawable(ContextCompat.getDrawable(getContext(), crate.minimalRequirementRank.iconId));
        }

        buyBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentlyAvailable) {
                    Toast.makeText(getContext(), "Crate is currently unavailable.", Toast.LENGTH_SHORT).show();
                } else if (Player.money >= price && unlocked) {

                    if (crate == Crate.COMMON_CRATE)
                        Player.commonCrateOpenCount++;
                    else if (crate == Crate.UNCOMMON_CRATE)
                        Player.uncommonCrateOpenCount++;
                    else if (crate == Crate.RARE_CRATE)
                        Player.rareCrateOpenCount++;
                    else if (crate == Crate.EPIC_CRATE)
                        Player.epicCrateOpenCount++;
                    else if (crate == Crate.LEGENDARY_CRATE)
                        Player.legendaryCrateOpenCount++;
                    else if (crate == Crate.MYTHIC_CRATE)
                        Player.mythicCrateOpenCount++;

                    Player.AddMoney(-price);
                    CrateOpenFragment.openingCrate = crate;

                    ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new CrateOpenFragment()).commit();
                }
            }
        });

        //Coming soon?
        if (!currentlyAvailable)
            priceTV.setText("Coming soon!");
    }

    private int CalculateCratePrice(float baseCratePrice, int openCount) {
        float totalPrice = baseCratePrice;

        for (int i = 0; i < openCount; i++) {
            totalPrice = totalPrice * 1.18f;
        }

        return (int) totalPrice;
    }
}
