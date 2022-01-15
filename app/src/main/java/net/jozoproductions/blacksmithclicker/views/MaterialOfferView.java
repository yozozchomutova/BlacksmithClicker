package net.jozoproductions.blacksmithclicker.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import net.jozoproductions.blacksmithclicker.R;
import net.jozoproductions.blacksmithclicker.items.Item;
import net.jozoproductions.blacksmithclicker.materials.Material;

public class MaterialOfferView extends ConstraintLayout {

    private ConstraintLayout root;

    private TextView nameTV;
    private TextView multiplierTV;
    private ImageView matIcon;
    private ImageView offerArrowIcon;

    public MaterialOfferView(@NonNull Context context) {
        super(context);
    }

    public MaterialOfferView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialOfferView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MaterialOfferView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(Material material, boolean positive) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_material_offer, this, true);

        //get views
        root = findViewById(R.id.root);
        nameTV = findViewById(R.id.name);
        multiplierTV = findViewById(R.id.multiplier);
        matIcon = findViewById(R.id.materialIcon);
        offerArrowIcon = findViewById(R.id.offerIcon);

        //Configure views
        root.setBackgroundColor(positive ? Color.argb(50, 0, 255, 0) : Color.argb(50, 255, 0, 0));
        nameTV.setText("" + material.name);
        multiplierTV.setText("Multiplier:" + ((int)(material.offerMultiplierValue*100)) / 100f);
        matIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), material.drawableId));
        offerArrowIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), positive ? R.drawable.arrow_up : R.drawable.arrow_down));
    }
}
