/**
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org/>
 */
 
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

        // Initialize ImageView
        imageView = findViewById(R.id.imageView);
    }

    /**
     * Generates menu from menu.xml
     * @param menu Menu object
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Handles selected action from menu
     * @param item MenuItem
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Create new shape object
        ShapeDrawable shapeDrawable;

        switch (item.getItemId()) {
            // Draw rectangle
            case R.id.rectangle:
                shapeDrawable = new ShapeDrawable(new RectShape());
                shapeDrawable.setIntrinsicHeight(200);
                shapeDrawable.setIntrinsicWidth(200);
                shapeDrawable.getPaint().setColor(Color.GREEN);
                break;

            // Draw star
            case R.id.star:
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
                break;

            // Draw oval
            case R.id.Oval:
                float[] outR = new float[]{10, 10, 50, 50, 20, 20, 20, 20};
                RectF rectF = new RectF(10, 10, 10, 10);
                float[] inR = new float[]{40, 40, 40, 40, 40, 40, 40, 40};
                shapeDrawable = new ShapeDrawable(new RoundRectShape(outR, rectF, inR));
                shapeDrawable.setIntrinsicHeight(300);
                shapeDrawable.setIntrinsicWidth(300);
                shapeDrawable.getPaint().setColor(Color.BLUE);
                break;

            // Draw arc
            case R.id.Arc:
                shapeDrawable = new ShapeDrawable(new ArcShape(0, 255));
                shapeDrawable.setIntrinsicHeight(300);
                shapeDrawable.setIntrinsicWidth(300);
                shapeDrawable.getPaint().setColor(Color.RED);
                break;

            // Animated rectangle
            case R.id.RotateRec:
            case R.id.UpDown:
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.rectangle_blue);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                shapeDrawable = new ShapeDrawable();
                shapeDrawable.setShape(new RectShape());
                shapeDrawable.setIntrinsicHeight(400);
                shapeDrawable.setIntrinsicWidth(600);
                shapeDrawable.getPaint().setShader(new BitmapShader(bitmapDrawable.getBitmap(),
                        Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
                break;

            // Close app
            case R.id.exit:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

        // If we have anything to draw
        // Start or stop animations
        if (item.getItemId() == R.id.RotateRec)
            startRotationAnimation();
        else if (item.getItemId() == R.id.UpDown)
            startJumpAnimation();
        else
            stopAnimations();

        // Set shape to imageView
        imageView.setImageDrawable(shapeDrawable);
        return true;
    }

    /**
     * Starts rotation animation
     */
    private void startRotationAnimation() {
        // Stop all animations
        stopAnimations();

        // Create 1s rotation
        rotationAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        rotationAnimator.setDuration(1000);
        rotationAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        rotationAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {}

            @Override
            public void onAnimationEnd(Animator animator) {
                // Repeat from 0 after finish
                imageView.setRotation(0f);
            }

            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });

        // Start animation
        animatorSet = new AnimatorSet();
        animatorSet.play(rotationAnimator);
        animatorSet.start();
    }

    /**
     * Starts jumping animation (Y)
     */
    private void startJumpAnimation() {
        // Stop all animations
        stopAnimations();

        // Set start and stop Y positions
        float startY = imageView.getY();
        float endY = startY - 100;

        // Create 0.5s jumping animation
        jumpAnimator = ObjectAnimator.ofFloat(imageView, "y", startY, endY);
        jumpAnimator.setDuration(500);
        jumpAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        jumpAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        jumpAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        jumpAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {}

            @Override
            public void onAnimationEnd(Animator animator) {
                // Repeat from start after finish
                imageView.setY(startY);
            }

            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });

        // Start animation
        animatorSet = new AnimatorSet();
        animatorSet.play(jumpAnimator);
        animatorSet.start();
    }

    private void stopAnimations() {
        // Cancel all animations
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
