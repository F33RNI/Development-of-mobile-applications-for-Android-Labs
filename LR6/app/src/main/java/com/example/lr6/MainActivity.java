package com.example.lr6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity implements
        OnClickListener {
    private TransitionDrawable mTransition;
    private boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView image = (ImageView)findViewById(R.id.transition_image);
        image.setOnClickListener(this);
        Resources res = this.getResources();
        mTransition =
                (TransitionDrawable)ResourcesCompat.getDrawable(res,
                        R.drawable.transition, null);
        image.setImageDrawable(mTransition);*/
    }
    @Override
    public void onClick(View v) {
        ShapeDrawable d = new ShapeDrawable(new RectShape());
        d.setIntrinsicHeight(2);
        d.setIntrinsicWidth(150);
        d.getPaint().setColor(Color.MAGENTA);
    }
}
