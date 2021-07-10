package net.jozoproductions.blacksmithclicker.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.jozoproductions.blacksmithclicker.R;

public class GuideDialog extends Dialog {

    public GuideDialog(@NonNull Context context) {
        super(context);
    }

    public GuideDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected GuideDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_guide);
    }
}
