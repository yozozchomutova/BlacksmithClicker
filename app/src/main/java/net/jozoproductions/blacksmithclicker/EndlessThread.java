package net.jozoproductions.blacksmithclicker;

import android.app.Activity;
import android.content.Context;

import net.jozoproductions.blacksmithclicker.particlemanaging.ParticlePack;

import java.util.ArrayList;

public class EndlessThread extends Thread {

    public Context context;
    public ArrayList<ParticlePack> particlePacks = new ArrayList<>();

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
            }
        });
    }
}
