package ru.myitschool.projectsamsung.arrows;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import ru.myitschool.projectsamsung.MainActivity;
import ru.myitschool.projectsamsung.R;

public class ArrowMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arrow_menu);
    }

    public void startArrowGame(View view) {
        Intent intent = new Intent(ArrowMenu.this, ArrowGameActivity.class);
        startActivity(intent);

    }

    public void openMenu(View view) {
        Intent intent = new Intent(ArrowMenu.this, MainActivity.class);
        startActivity(intent);
    }
}
