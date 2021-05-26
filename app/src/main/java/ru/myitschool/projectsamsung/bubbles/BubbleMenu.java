package ru.myitschool.projectsamsung.bubbles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import ru.myitschool.projectsamsung.MainActivity;
import ru.myitschool.projectsamsung.R;

public class BubbleMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bubble_menu);
    }

    public void startBubbleGame(View view) {
        Intent intent = new Intent(BubbleMenu.this, BubbleGameActivity.class);
        startActivity(intent);
    }

    public void openMenu(View view) {
        Intent intent = new Intent(BubbleMenu.this, MainActivity.class);
        startActivity(intent);
    }
}
