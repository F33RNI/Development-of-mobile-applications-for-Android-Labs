package com.example.photocube;

import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

/** * Created by Kalidoss on 18/07/16. */


public class MainActivity extends AppCompatActivity {

    public GLSurfaceView mGLSurfaceView;
    float mPreviousX,mPreviousY;
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    MyGLRender mRenderer;
    RelativeLayout cubelayout;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cubelayout=(RelativeLayout)findViewById(R.id.cude_layout);
        mGLSurfaceView = new GLSurfaceView(this); // Allocate a GLSurfaceView
        mRenderer=new MyGLRender(this);


        //make cube transparent

        mGLSurfaceView.setZOrderOnTop(true);
        mGLSurfaceView.setEGLConfigChooser( 8, 8, 8, 8, 16, 0 );
        mGLSurfaceView.getHolder().setFormat(PixelFormat.RGBA_8888);
        mGLSurfaceView.setRenderer(mRenderer);
        mGLSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        cubelayout.addView(mGLSurfaceView);
    }

    @Override    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - mPreviousX;
                float dy = y - mPreviousY;
                mRenderer.mAngleX += dx * TOUCH_SCALE_FACTOR;
                mRenderer.mAngleY += dy * TOUCH_SCALE_FACTOR;
                mGLSurfaceView.requestRender();
                break;
        }
        mPreviousX = x;
        mPreviousY = y;
        return true;
    }
}