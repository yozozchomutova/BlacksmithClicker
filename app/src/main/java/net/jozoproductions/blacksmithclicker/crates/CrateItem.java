package net.jozoproductions.blacksmithclicker.crates;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentContainerView;

import net.jozoproductions.blacksmithclicker.MainActivity;
import net.jozoproductions.blacksmithclicker.Player;
import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.SmithingItem;
import net.jozoproductions.blacksmithclicker.fragments.CrateOpenFragment;
import net.jozoproductions.blacksmithclicker.particlemanaging.ParticlePack;

public class CrateItem extends ConstraintLayout {

    private Crate crate;

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
    public void Inflate(Context context, Crate crate_, int price) {
        this.crate = crate_;
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.item_crate, this, true);

        //Fetch views
        TextView nameTV = inflatedView.findViewById(R.id.name);
        TextView priceTV = inflatedView.findViewById(R.id.price);
        ImageView iconIV = inflatedView.findViewById(R.id.icon);
        ImageView rarityIV = inflatedView.findViewById(R.id.rarity);
        ImageView selectItemBtn = inflatedView.findViewById(R.id.select_item);

        //Modify views
        nameTV.setText(crate.name);
        priceTV.setText("" + price);
        iconIV.setImageDrawable(getResources().getDrawable(crate.drawableId));
        rarityIV.setColorFilter(ContextCompat.getColor(context, crate.colorId), android.graphics.PorterDuff.Mode.MULTIPLY);

        selectItemBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Player.money >= price) {
                    Player.AddMoney(-price);
                    CrateOpenFragment.openingCrate = crate;

                    ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new CrateOpenFragment()).commit();
                }
            }
        });
    }
}
