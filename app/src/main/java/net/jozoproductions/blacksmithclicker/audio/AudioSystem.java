package net.jozoproductions.blacksmithclicker.audio;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.HashMap;

public class AudioSystem {

    public static HashMap<String, MediaPlayer> loadedSoundIds = new HashMap<>();

    public static MediaPlayer music;

    public static void LoadAudio(Context context, String key, int rawId) {
        loadedSoundIds.put(key, MediaPlayer.create(context, rawId));
    }

    public static void PlayAudio(String key) {
        MediaPlayer mediaPlayer = loadedSoundIds.get(key);

        if (mediaPlayer.isPlaying())
            loadedSoundIds.get(key).seekTo(0);
        else
            loadedSoundIds.get(key).start();
    }

    public static void SetMusic(Context context, int rawId) {
        music = MediaPlayer.create(context, rawId);
        music.setLooping(true);
        music.start();
    }
}
