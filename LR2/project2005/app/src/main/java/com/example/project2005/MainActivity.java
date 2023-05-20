package com.example.project2005;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView my_text = (TextView) findViewById(R.id.my_text);
        my_text.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/penguinattackcyrillic.otf"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final ConstraintLayout mylayout =
                (ConstraintLayout) findViewById(R.id.root);
        switch (item.getItemId()) {
            case R.id.red:

                mylayout.setBackgroundColor(getResources().getColor(R.color.red));
                return true;
            case R.id.green:

                mylayout.setBackgroundColor(getResources().getColor(R.color.green));
                return true;
            case R.id.blue:

                mylayout.setBackgroundColor(getResources().getColor(R.color.blue));
                return true;
            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}