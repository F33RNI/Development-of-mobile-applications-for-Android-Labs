package com.labs.lr5;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    // Lists of columns
    private final List<TextView> IDTextViews = new ArrayList<>();
    private final List<TextView> XTextViews = new ArrayList<>();
    private final List<TextView> YTextViews = new ArrayList<>();

    private TextView touches, touchLastIndex, releaseLastIndex;


    private boolean touchFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        // Connect status text views
        touches = findViewById(R.id.touches);
        touchLastIndex = findViewById(R.id.touchLastIndex);
        releaseLastIndex = findViewById(R.id.releaseLastIndex);

        // Create 11 rows of text (header + 10 IDs)
        for (int row = 0; row < 11; row++) {
            // Create a new TableRow
            TableRow tableRow = new TableRow(this);

            // Add 4 TextViews to the TableRow
            for (int column = 0; column < 4; column++) {
                TextView textView = new TextView(this);

                // Header
                if (row == 0) {
                    switch (column) {
                        case 0:
                            textView.setText("Index");
                            break;
                        case 1:
                            textView.setText("ID");
                            break;
                        case 2:
                            textView.setText("X");
                            break;
                        case 3:
                            textView.setText("Y");
                            break;
                    }
                }

                // Other ID
                else {
                    switch (column) {
                        // First column (Index)
                        case 0:
                            textView.setText(String.valueOf(row));
                            break;

                        // ID
                        case 1:
                            IDTextViews.add(textView);
                            textView.setText("");
                            break;

                        // X
                        case 2:
                            XTextViews.add(textView);
                            textView.setText("");
                            break;

                        // Y
                        case 3:
                            YTextViews.add(textView);
                            textView.setText("");
                            break;
                    }
                }

                // Add TextView to the TableRow
                tableRow.addView(textView);
            }

            // Add the TableRow to the TableLayout
            tableLayout.addView(tableRow);
        }

        // Stretch all columns
        tableLayout.setStretchAllColumns(true);

        // Connect onTouch to TextView
        findViewById(R.id.textView).setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int actionMask = event.getActionMasked();
        int pointerIndex = event.getActionIndex();
        int pointerCount = event.getPointerCount();

        switch (actionMask) {
            case MotionEvent.ACTION_DOWN:
                touchFlag = true;

            // Set last touch index
            case MotionEvent.ACTION_POINTER_DOWN:
                touchLastIndex.setText(String.valueOf(pointerIndex));
                break;

            // Clear all rows and stats if we released all touches
            case MotionEvent.ACTION_UP:
                touchFlag = false;
                for (int i = 0; i < IDTextViews.size(); i++) {
                    IDTextViews.get(i).setText("");
                    XTextViews.get(i).setText("");
                    YTextViews.get(i).setText("");
                }
                touches.setText("0");

            // Set last release index
            case MotionEvent.ACTION_POINTER_UP:
                releaseLastIndex.setText(String.valueOf(pointerIndex));
                break;

            // Show current touch coordinates
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < 10; i++) {
                    if (i < pointerCount) {
                        IDTextViews.get(i).setText(String.valueOf(event.getPointerId(i)));
                        XTextViews.get(i).setText(String.valueOf((int) event.getX(i)));
                        YTextViews.get(i).setText(String.valueOf((int) event.getY(i)));
                    } else {
                        IDTextViews.get(i).setText("");
                        XTextViews.get(i).setText("");
                        YTextViews.get(i).setText("");
                    }
                }
                break;
        }

        // Set number of touches
        if (touchFlag)
            touches.setText(String.valueOf(pointerCount));
        return true;

    }
}