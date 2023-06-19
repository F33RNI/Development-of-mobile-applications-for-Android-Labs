package com.example.project2005;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define root layout
        constraintLayout = findViewById(R.id.root);

        // Create a typeface from file
        Typeface font = Typeface.createFromAsset(getAssets(),
                "fonts/DotGothic16-Regular.ttf");

        // Set typeface
        ((TextView) findViewById(R.id.textView)).setTypeface(font);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Menu callback
     * @param item MenuItem
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.red:
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.red));
                return true;

            case R.id.green:
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.green));
                return true;

            case R.id.blue:
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.blue));
                return true;

            case R.id.exit:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}