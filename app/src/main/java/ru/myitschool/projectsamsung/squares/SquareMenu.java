package ru.myitschool.projectsamsung.squares;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import ru.myitschool.projectsamsung.MainActivity;
import ru.myitschool.projectsamsung.R;

public class SquareMenu extends AppCompatActivity {
    int amountSqr = 25;
    Button b5, b6, b7, b8, b9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.square_menu);
        b5=findViewById(R.id.b5);
        b6=findViewById(R.id.b6);
        b7=findViewById(R.id.b7);
        b8=findViewById(R.id.b8);
        b9=findViewById(R.id.b9);
        returnState();
        b5.setBackgroundColor(Color.BLUE);
        b5.setTextColor(Color.WHITE);
    }

    public void startSquareGame(View view) {
        Intent intent = new Intent(SquareMenu.this, SquareGameActivity.class);
        intent.putExtra("amount", amountSqr);
        startActivity(intent);
    }
    public void openMenu(View view) {
        Intent intent = new Intent(SquareMenu.this, MainActivity.class);
        startActivity(intent);
    }

    //For SquareGame
    public void set5(View view) {
        amountSqr=5*5;
        returnState();
        b5.setBackgroundColor(Color.BLUE);
        b5.setTextColor(Color.WHITE);
    }
    public void set6(View view) {
        amountSqr=6*6;
        returnState();
        b6.setBackgroundColor(Color.BLUE);
        b6.setTextColor(Color.WHITE);
    }
    public void set7(View view) {
        amountSqr=7*7;
        returnState();
        b7.setBackgroundColor(Color.BLUE);
        b7.setTextColor(Color.WHITE);
    }
    public void set8(View view) {
        amountSqr=8*8;
        returnState();
        b8.setBackgroundColor(Color.BLUE);
        b8.setTextColor(Color.WHITE);
    }
    public void set9(View view) {
        amountSqr=9*9;
        returnState();
        b9.setBackgroundColor(Color.BLUE);
        b9.setTextColor(Color.WHITE);
    }
    public void returnState(){
        b5.setBackgroundColor(Color.CYAN);
        b5.setTextColor(Color.BLACK);
        b6.setBackgroundColor(Color.CYAN);
        b6.setTextColor(Color.BLACK);
        b7.setBackgroundColor(Color.CYAN);
        b7.setTextColor(Color.BLACK);
        b8.setBackgroundColor(Color.CYAN);
        b8.setTextColor(Color.BLACK);
        b9.setBackgroundColor(Color.CYAN);
        b9.setTextColor(Color.BLACK);
    }



}
