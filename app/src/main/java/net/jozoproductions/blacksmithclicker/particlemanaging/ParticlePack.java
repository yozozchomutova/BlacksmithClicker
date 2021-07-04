package net.jozoproductions.blacksmithclicker.particlemanaging;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ParticlePack {

    public static final int LIFE_TIME = 100;
    public static ConstraintLayout parent;

    public Particle[] particles;
    public int curLifeTime;

    public ParticlePack(int particleDrawable, float spawnX, float spawnY, int spawnCount) {
        curLifeTime = LIFE_TIME;
        particles = new Particle[spawnCount];

        for (int i = 0; i < spawnCount; i++) {
            particles[i] = new Particle(parent, particleDrawable, spawnX, spawnY);
        }
    }

    public boolean NextFrame() {
        for (int i = 0; i < particles.length; i++) {
            particles[i].Move();
        }

        curLifeTime--;

        if (curLifeTime <= 0) {
            for (int i = 0; i < particles.length; i++) {
                particles[i].Adios();
            }

            return true;
        }

        return false;
    }
}
