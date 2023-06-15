package com.example.lr6_supernew;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private ObjectAnimator rotationAnimator;
    private ObjectAnimator jumpAnimator;
    private AnimatorSet animatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Находим ImageView в макете по его идентификатору
        imageView = findViewById(R.id.imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Заполняем меню из ресурса menu.xml
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ShapeDrawable shapeDrawable;

        // Обрабатываем выбранный пункт меню
        switch (item.getItemId()) {
            case R.id.rectangle:
                // Создаем прямоугольную фигуру
                shapeDrawable = new ShapeDrawable(new RectShape());
                shapeDrawable.setIntrinsicHeight(200);
                shapeDrawable.setIntrinsicWidth(200);
                shapeDrawable.getPaint().setColor(Color.GREEN);

                // Устанавливаем созданную фигуру в ImageView
                imageView.setImageDrawable(shapeDrawable);

                // Останавливаем все анимации
                stopAnimations();
                return true;

            case R.id.star:
                // Создаем фигуру звезды
                Path path = new Path();
                path.moveTo(50, 0);
                path.lineTo(25, 100);
                path.lineTo(100, 50);
                path.lineTo(0, 50);
                path.lineTo(75, 100);
                path.lineTo(50, 0);

                shapeDrawable = new ShapeDrawable(new PathShape(path, 100, 100));
                shapeDrawable.setIntrinsicHeight(300);
                shapeDrawable.setIntrinsicWidth(300);
                shapeDrawable.getPaint().setColor(Color.BLUE);
                shapeDrawable.getPaint().setStyle(Paint.Style.STROKE);

                imageView.setImageDrawable(shapeDrawable);
                stopAnimations();
                return true;

            case R.id.Oval:
                // Создаем овальную фигуру
                float[] outR = new float[]{10, 10, 50, 50, 20, 20, 20, 20};
                RectF rectF = new RectF(10, 10, 10, 10);
                float[] inR = new float[]{40, 40, 40, 40, 40, 40, 40, 40};
                shapeDrawable = new ShapeDrawable(new RoundRectShape(outR, rectF, inR));
                shapeDrawable.setIntrinsicHeight(300);
                shapeDrawable.setIntrinsicWidth(300);
                shapeDrawable.getPaint().setColor(Color.BLUE);

                imageView.setImageDrawable(shapeDrawable);
                stopAnimations();
                return true;

            case R.id.Arc:
                // Создаем дуговую фигуру
                shapeDrawable = new ShapeDrawable(new ArcShape(0, 255));
                shapeDrawable.setIntrinsicHeight(300);
                shapeDrawable.setIntrinsicWidth(300);
                shapeDrawable.getPaint().setColor(Color.RED);

                imageView.setImageDrawable(shapeDrawable);
                stopAnimations();
                return true;

            case R.id.RotateRec:
                // Создаем вращающуюся фигуру с использованием изображения
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.rectangle_blue);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                shapeDrawable = new ShapeDrawable();
                shapeDrawable.setShape(new RectShape());
                shapeDrawable.setIntrinsicHeight(400);
                shapeDrawable.setIntrinsicWidth(600);
                shapeDrawable.getPaint().setShader(new BitmapShader(bitmapDrawable.getBitmap(), Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));

                imageView.setImageDrawable(shapeDrawable);
                startRotationAnimation();
                return true;

            case R.id.UpDown:
                // Создаем фигуру, которая прыгает вверх-вниз с использованием изображения
                Drawable upDownDrawable = ContextCompat.getDrawable(this, R.drawable.rectangle_blue);
                BitmapDrawable upDownBitmapDrawable = (BitmapDrawable) upDownDrawable;
                shapeDrawable = new ShapeDrawable();
                shapeDrawable.setShape(new RectShape());
                shapeDrawable.setIntrinsicHeight(400);
                shapeDrawable.setIntrinsicWidth(600);
                shapeDrawable.getPaint().setShader(new BitmapShader(upDownBitmapDrawable.getBitmap(), Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));

                imageView.setImageDrawable(shapeDrawable);
                startJumpAnimation();
                return true;

            case R.id.exit:
                // Завершаем активность
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startRotationAnimation() {
        // Останавливаем все активные анимации
        stopAnimations();

        // Создаем анимацию вращения
        rotationAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        rotationAnimator.setDuration(1000);
        rotationAnimator.setRepeatCount(ObjectAnimator.INFINITE);

        // Устанавливаем слушатель анимации, чтобы обработать события
        rotationAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {}

            @Override
            public void onAnimationEnd(Animator animator) {
                imageView.setRotation(0f); // Устанавливаем позицию в исходное положение
            }

            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });

        // Запускаем анимацию
        animatorSet = new AnimatorSet();
        animatorSet.play(rotationAnimator);
        animatorSet.start();
    }

    private void startJumpAnimation() {
        // Останавливаем все активные анимации
        stopAnimations();

        // Получаем начальное и конечное положение для прыжка
        float startY = imageView.getY();
        float endY = startY - 100;

        // Создаем анимацию прыжка
        jumpAnimator = ObjectAnimator.ofFloat(imageView, "y", startY, endY);
        jumpAnimator.setDuration(500);
        jumpAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        jumpAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        jumpAnimator.setRepeatCount(ObjectAnimator.INFINITE);

        // Устанавливаем слушатель анимации, чтобы обработать события
        jumpAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {}

            @Override
            public void onAnimationEnd(Animator animator) {
                imageView.setY(startY); // Устанавливаем позицию в исходное положение
            }

            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });

        // Запускаем анимацию
        animatorSet = new AnimatorSet();
        animatorSet.play(jumpAnimator);
        animatorSet.start();
    }

    private void stopAnimations() {
        // Останавливаем все анимации
        if (animatorSet != null) {
            animatorSet.cancel();
            animatorSet = null;
        }

        if (rotationAnimator != null) {
            rotationAnimator.cancel();
            rotationAnimator = null;
        }

        if (jumpAnimator != null) {
            jumpAnimator.cancel();
            jumpAnimator = null;
        }
    }
}
