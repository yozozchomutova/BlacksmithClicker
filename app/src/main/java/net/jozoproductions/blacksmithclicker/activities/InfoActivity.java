package net.jozoproductions.blacksmithclicker.activities;

import static net.jozoproductions.blacksmithclicker.MainActivity.Save;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.jozoproductions.blacksmithclicker.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_info);

        //Listeners
        findViewById(R.id.close_activity).setOnClickListener(view -> finish());

        findViewById(R.id.discCopy).setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("link", "https://discord.gg/mXFwmrdX9B");
            clipboard.setPrimaryClip(clip);
        });

        findViewById(R.id.discOpen).setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://discord.gg/mXFwmrdX9B"));
            startActivity(browserIntent);
        });
    }

    @Override
    protected void onPause() {
        Save();
        super.onPause();
    }
}
