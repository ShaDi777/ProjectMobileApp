package ru.myitschool.projectsamsung.bubbles;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class BubbleGameActivity extends AppCompatActivity {
    private DrawView DrawView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrawView = new DrawView(this);
        setContentView(DrawView);
    }
}
