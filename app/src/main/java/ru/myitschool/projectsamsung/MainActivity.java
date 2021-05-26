package ru.myitschool.projectsamsung;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import ru.myitschool.projectsamsung.arrows.ArrowMenu;
import ru.myitschool.projectsamsung.bubbles.BubbleMenu;
import ru.myitschool.projectsamsung.numbers.NumberMenu;
import ru.myitschool.projectsamsung.squares.SquareMenu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void openSquareMenu(View view) {
        Intent intent = new Intent(MainActivity.this, SquareMenu.class);
        startActivity(intent);

    }

    public void openBubbleMenu(View view) {
        Intent intent = new Intent(MainActivity.this, BubbleMenu.class);
        startActivity(intent);
    }

    public void openNumberMenu(View view) {
        Intent intent = new Intent(MainActivity.this, NumberMenu.class);
        startActivity(intent);
    }

    public void openArrowMenu(View view) {
        Intent intent = new Intent(MainActivity.this, ArrowMenu.class);
        startActivity(intent);
    }

}
