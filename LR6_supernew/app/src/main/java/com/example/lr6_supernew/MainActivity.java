package com.example.lr6_supernew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

             return true;
    }
    //public boolean onOptionsItemSelected(MenuItem item) {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final ConstraintLayout mylayout =
                (ConstraintLayout)findViewById(R.id.root);

        ShapeDrawable d;
        switch (item.getItemId()) {
            case R.id.rectangle:
                d = new ShapeDrawable(new RectShape());
                d.setIntrinsicHeight(2);
                d.setIntrinsicWidth(150);
                d.getPaint().setColor(Color.MAGENTA);

                return true;
            case R.id.star:
                Path p = new Path();
                p.moveTo(50, 0);
                p.lineTo(25,100);
                p.lineTo(100,50);
                p.lineTo(0,50);
                p.lineTo(75,100);
                p.lineTo(50,0);
                d = new ShapeDrawable(new PathShape(p, 100, 100));
                d.setIntrinsicHeight(200);
                d.setIntrinsicWidth(200);
                d.getPaint().setColor(Color.RED);
                d.getPaint().setStyle(Paint.Style.STROKE);

                return true;
            case R.id.oval:
                float[] outR = new float[] { 10, 10, 50, 50, 20, 20, 20, 20 };
                RectF rectF = new RectF(10, 10, 10, 10);
                float[] inR = new float[] { 40, 40, 40, 40, 40, 40, 40, 40 };
                d = new ShapeDrawable(new RoundRectShape(outR, rectF ,
                        inR));
                d.setIntrinsicHeight(300);
                d.setIntrinsicWidth(500);
                d.getPaint().setColor(Color.BLUE);


                return true;

            case R.id.duga:
                d = new ShapeDrawable(new ArcShape(0, 255));
                d.setIntrinsicHeight(200);
                d.setIntrinsicWidth(200);
                d.getPaint().setColor(Color.RED);

            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
    private void drawShape(ShapeDrawable shapeDrawable) {
        shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.setIntrinsicHeight(2);
        shapeDrawable.setIntrinsicWidth(150);
        shapeDrawable.getPaint().setColor(Color.MAGENTA);

        View shapeView = new View(this) {
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                shapeDrawable.setBounds(new Rect(0, 0, getWidth(), getHeight()));
                shapeDrawable.draw(canvas);
            }
        };

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        containerLayout.addView(shapeView, layoutParams);
    }

    /*public boolean onMenuItemClick(@NonNull MenuItem item) {
        final ConstraintLayout mylayout =
                (ConstraintLayout)findViewById(R.id.root);
        ShapeDrawable d = new ShapeDrawable();
        switch (item.getItemId()) {
            case R.id.rectangle:
                d = new ShapeDrawable(new RectShape());
                d.setIntrinsicHeight(2);
                d.setIntrinsicWidth(150);
                d.getPaint().setColor(Color.MAGENTA);
                return true;
            case R.id.star:
                Path p = new Path();
                p.moveTo(50, 0);
                p.lineTo(25,100);
                p.lineTo(100,50);
                p.lineTo(0,50);
                p.lineTo(75,100);
                p.lineTo(50,0);
                d = new ShapeDrawable(new PathShape(p, 100, 100));
                d.setIntrinsicHeight(200);
                d.setIntrinsicWidth(200);
                d.getPaint().setColor(Color.RED);
                d.getPaint().setStyle(Paint.Style.STROKE);

                return true;
            case R.id.oval:
                float[] outR = new float[] { 10, 10, 50, 50, 20, 20, 20, 20 };
                RectF rectF = new RectF(10, 10, 10, 10);
                float[] inR = new float[] { 40, 40, 40, 40, 40, 40, 40, 40 };
                d = new ShapeDrawable(new RoundRectShape(outR, rectF ,
                        inR));
                d.setIntrinsicHeight(300);
                d.setIntrinsicWidth(500);
                d.getPaint().setColor(Color.BLUE);


                return true;

            case R.id.duga:
                d = new ShapeDrawable(new ArcShape(0, 255));
                d.setIntrinsicHeight(200);
                d.setIntrinsicWidth(200);
                d.getPaint().setColor(Color.RED);

            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}