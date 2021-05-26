package ru.myitschool.projectsamsung.numbers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import ru.myitschool.projectsamsung.MainActivity;
import ru.myitschool.projectsamsung.R;

public class NumberMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_menu);
    }

    public void startNumberGame(View view) {
        Intent intent = new Intent(NumberMenu.this, NumbersGameActivity.class);
        startActivity(intent);
    }

    public void openMenu(View view) {
        Intent intent = new Intent(NumberMenu.this, MainActivity.class);
        startActivity(intent);
    }
}
