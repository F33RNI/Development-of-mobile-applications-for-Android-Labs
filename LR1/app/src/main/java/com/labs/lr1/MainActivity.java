package com.labs.lr1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Formatted text (on activity)
    private EditText editText;

    // Info about text size (on activity)
    private TextView size;

    // Current text size
    private float textSize = .0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edit_text);
        size = (TextView)findViewById(R.id.size);

        // Connect all button clicks to current class (onClick(View v) void)
        findViewById(R.id.button_b).setOnClickListener(this);
        findViewById(R.id.button_i).setOnClickListener(this);
        findViewById(R.id.button_plus).setOnClickListener(this);
        findViewById(R.id.button_minus).setOnClickListener(this);

        findViewById(R.id.button_sans).setOnClickListener(this);
        findViewById(R.id.button_serif).setOnClickListener(this);
        findViewById(R.id.button_monospace).setOnClickListener(this);

        findViewById(R.id.red).setOnClickListener(this);
        findViewById(R.id.orange).setOnClickListener(this);
        findViewById(R.id.yellow).setOnClickListener(this);
        findViewById(R.id.green).setOnClickListener(this);
        findViewById(R.id.blue).setOnClickListener(this);
        findViewById(R.id.purple).setOnClickListener(this);

        // Set initial text size to 16
        textSize = 16.f;
        editText.setTextSize(textSize);
        size.setText(String.format("%.0f", textSize));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_plus:
                if (textSize < 72) {
                    textSize += 2;
                    editText.setTextSize(textSize);
                    size.setText(String.format("%.0f", textSize));
                }
                break;
            case R.id.button_minus:
                if (textSize > 18) {
                    textSize -= 2;
                    editText.setTextSize(textSize);
                    size.setText(String.format("%.0f", textSize));
                }
                break;
            case R.id.button_b:
                if (editText.getTypeface().getStyle() == Typeface.ITALIC)
                    editText.setTypeface(editText.getTypeface(), Typeface.BOLD_ITALIC);
                else if (editText.getTypeface().getStyle() == Typeface.BOLD_ITALIC)
                    editText.setTypeface(editText.getTypeface(), Typeface.ITALIC);
                else if (editText.getTypeface().getStyle() == Typeface.BOLD)
                    editText.setTypeface(Typeface.create(editText.getTypeface(), Typeface.NORMAL));
                else editText.setTypeface(editText.getTypeface(), Typeface.BOLD);
                break;
            case R.id.button_i:
                if (editText.getTypeface().getStyle() == Typeface.BOLD)
                    editText.setTypeface(editText.getTypeface(), Typeface.BOLD_ITALIC);
                else if (editText.getTypeface().getStyle() == Typeface.BOLD_ITALIC)
                    editText.setTypeface(editText.getTypeface(), Typeface.BOLD);
                else if (editText.getTypeface().getStyle() == Typeface.ITALIC)
                    editText.setTypeface(Typeface.create(editText.getTypeface(), Typeface.NORMAL));
                else editText.setTypeface(editText.getTypeface(), Typeface.ITALIC);
                break;
            case R.id.button_sans:
                if (editText.getTypeface().getStyle() == Typeface.ITALIC)
                    editText.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
                else if (editText.getTypeface().getStyle() == Typeface.BOLD_ITALIC)
                    editText.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
                else if (editText.getTypeface().getStyle() == Typeface.BOLD)
                    editText.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                else editText.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                break;
            case R.id.button_serif:
                if (editText.getTypeface().getStyle() == Typeface.ITALIC)
                    editText.setTypeface(Typeface.SERIF, Typeface.ITALIC);
                else if (editText.getTypeface().getStyle() == Typeface.BOLD_ITALIC)
                    editText.setTypeface(Typeface.SERIF, Typeface.BOLD_ITALIC);
                else if (editText.getTypeface().getStyle() == Typeface.BOLD)
                    editText.setTypeface(Typeface.SERIF, Typeface.BOLD);
                else editText.setTypeface(Typeface.SERIF, Typeface.NORMAL);
                break;
            case R.id.button_monospace:
                if (editText.getTypeface().getStyle() == Typeface.ITALIC)
                    editText.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
                else if (editText.getTypeface().getStyle() == Typeface.BOLD_ITALIC)
                    editText.setTypeface(Typeface.MONOSPACE, Typeface.BOLD_ITALIC);
                else if (editText.getTypeface().getStyle() == Typeface.BOLD)
                    editText.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
                else editText.setTypeface(Typeface.MONOSPACE, Typeface.NORMAL);
                break;

            case R.id.red:
                editText.setTextColor(Color.parseColor("#ff0000"));
                break;

            case R.id.orange:
                editText.setTextColor(Color.parseColor("#FF7700"));
                break;

            case R.id.yellow:
                editText.setTextColor(Color.parseColor("#FFFF00"));
                break;

            case R.id.green:
                editText.setTextColor(Color.parseColor("#00FF00"));
                break;

            case R.id.blue:
                editText.setTextColor(Color.parseColor("#0000ff"));
                break;


            case R.id.purple:
                editText.setTextColor(Color.parseColor("#AA00FF"));
                break;


        }
    }
}