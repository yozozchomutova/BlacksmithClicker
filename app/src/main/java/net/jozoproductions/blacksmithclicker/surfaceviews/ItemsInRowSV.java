package net.jozoproductions.blacksmithclicker.surfaceviews;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class ItemsInRowSV extends SurfaceView implements SurfaceHolder.Callback {

    public ItemsInRowSV(Context context) {
        super(context);
        init();
    }

    public ItemsInRowSV(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemsInRowSV(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ItemsInRowSV(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}
