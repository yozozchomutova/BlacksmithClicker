package net.jozoproductions.blacksmithclicker;

import android.app.Activity;
import android.content.Context;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import net.jozoproductions.blacksmithclicker.materials.Material;
import net.jozoproductions.blacksmithclicker.particlemanaging.ParticlePack;

import java.util.ArrayList;

public class EndlessThread extends Thread {

    public Context context;
    public ArrayList<ParticlePack> particlePacks = new ArrayList<>();

    private ImageView shakeBonusIcon;

    public EndlessThread(ImageView shakeBonusIcon) {
        this.shakeBonusIcon = shakeBonusIcon;
    }

    @Override
    public void run() {
        //Particles
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = particlePacks.size()-1; i >= 0; i--) {
                    //Returns, if particle pack is dead
                    if (particlePacks.get(i).NextFrame()) {
                        particlePacks.remove(i);
                    }
                }

                //Material market checker
                Material.MaterialMarket.CheckForNewOffer();

                //Shake bonus countdown
                if (Player.shakeBonusActivated) {
                    if (Player.shakeBonusCountDown <= 0) {
                        Player.shakeBonusActivated = false;
                        Player.curShakeBonusEffect = 1f;
                        Player.shakeBonusCountDown = 18000; //18,000 = 3 minutes
                        Toast.makeText(context, "Shake bonus ended.", Toast.LENGTH_SHORT).show();
                    } else {
                        Player.shakeBonusCountDown -= 1;
                    }
                } else if (Player.shakeBonusUnlocked) {
                    if (Player.shakeBonusCountDown <= 0) {
                        Player.shakeBonusReady = true;
                        shakeBonusIcon.setVisibility(View.VISIBLE);
                    } else {
                        Player.shakeBonusCountDown -= 1;
                    }
                }
            }
        });
    }
}
