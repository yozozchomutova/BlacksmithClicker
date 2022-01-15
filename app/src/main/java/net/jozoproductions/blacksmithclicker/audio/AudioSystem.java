package net.jozoproductions.blacksmithclicker.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import net.jozoproductions.blacksmithclicker.R;

import java.util.HashMap;

public class AudioSystem {

    //Sound pool
    public static SoundPool soundPool;

    public static int ANVIL_STRIKE;
    public static int CLICK1;
    public static int MINE1;

    //Media player
    public static MediaPlayer music;
    public static float musicVolume;

    public static void Init(Context context, int maxStreams) {
        AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(maxStreams)
                .setAudioAttributes(audioAttributes)
                .build();

        //Load sounds
        ANVIL_STRIKE = soundPool.load(context, R.raw.anvil_strike, 1);
        CLICK1 = soundPool.load(context, R.raw.click, 1);
        MINE1 = soundPool.load(context, R.raw.mine1, 1);
    }

    public static void PlayAudio(int audioClip) {
        soundPool.play(audioClip, 1, 1, 0, 0, 1);
    }

    public static void SetMusic(Context context, int rawId) {
        music = MediaPlayer.create(context, rawId);
        music.setLooping(true);
        music.start();
    }

    public static void ChangeMusicAudio(float volume) {
        music.setVolume(volume, volume);
        musicVolume = volume;
    }
}
