package net.jozoproductions.blacksmithclicker.particlemanaging;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import net.jozoproductions.blacksmithclicker.MainActivity;
import net.jozoproductions.blacksmithclicker.R;

public class Particle {

    public ConstraintLayout parent;
    public ImageView image;

    public float posX;
    public float posY;

    public float randomVelX;
    public float randomVelY;

    @SuppressLint("ResourceType")
    public Particle(ConstraintLayout parent, int particleDrawable, float spawnX, float spawnY) {
        this.parent = parent;

        //Setup image
        image = new ImageView(parent.getContext());
        image.setId(123);
        image.setImageDrawable(ResourcesCompat.getDrawable(parent.getResources(), particleDrawable, null));
        image.setMaxWidth(40);
        image.setMinimumWidth(40);
        image.setMaxHeight(40);
        image.setMinimumHeight(40);
        parent.addView(image);

        //Position setup
        posX = spawnX;
        posY = spawnY;
        randomVelX = (MainActivity.random.nextFloat() - 0.5f) * 10f;
        randomVelY = (MainActivity.random.nextFloat() - 0.8f) * 10f;
        UpdatePosition();

        //Disappear animation
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(1000);
        image.startAnimation(alphaAnimation);
    }

    public void Move() {
        posX += randomVelX;
        posY += randomVelY;
        UpdatePosition();

        //Gravity
        randomVelY += 0.1f;
    }

    private void UpdatePosition() {
        image.setX(posX);
        image.setY(posY);
    }

    public void Adios() {
        image.clearAnimation();
        parent.removeView(image);
    }
}
