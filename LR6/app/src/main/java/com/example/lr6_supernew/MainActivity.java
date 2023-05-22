package com.example.lr6_supernew;

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
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ShapeDrawable shapeDrawable;

        switch (item.getItemId()) {
            case R.id.rectangle:
                shapeDrawable = new ShapeDrawable(new RectShape());
                shapeDrawable.setIntrinsicHeight(2);
                shapeDrawable.setIntrinsicWidth(15);
                shapeDrawable.getPaint().setColor(Color.MAGENTA);

                imageView.setImageDrawable(shapeDrawable);
                return true;

            case R.id.star:
                Path path = new Path();
                path.moveTo(50, 0);
                path.lineTo(25, 100);
                path.lineTo(100, 50);
                path.lineTo(0, 50);
                path.lineTo(75, 100);
                path.lineTo(50, 0);

                shapeDrawable = new ShapeDrawable(new PathShape(path, 100, 100));
                shapeDrawable.setIntrinsicHeight(200);
                shapeDrawable.setIntrinsicWidth(200);
                shapeDrawable.getPaint().setColor(Color.RED);
                shapeDrawable.getPaint().setStyle(Paint.Style.STROKE);

                imageView.setImageDrawable(shapeDrawable);
                return true;

            case R.id.oval:
                float[] outR = new float[]{10, 10, 50, 50, 20, 20, 20, 20};
                RectF rectF = new RectF(10, 10, 10, 10);
                float[] inR = new float[]{40, 40, 40, 40, 40, 40, 40, 40};
                shapeDrawable = new ShapeDrawable(new RoundRectShape(outR, rectF, inR));
                shapeDrawable.setIntrinsicHeight(300);
                shapeDrawable.setIntrinsicWidth(500);
                shapeDrawable.getPaint().setColor(Color.BLUE);

                imageView.setImageDrawable(shapeDrawable);
                return true;

            case R.id.duga:
                shapeDrawable = new ShapeDrawable(new ArcShape(0, 255));
                shapeDrawable.setIntrinsicHeight(200);
                shapeDrawable.setIntrinsicWidth(200);
                shapeDrawable.getPaint().setColor(Color.RED);

                imageView.setImageDrawable(shapeDrawable);
                return true;

            case R.id.exit:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}