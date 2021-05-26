package ru.myitschool.projectsamsung.arrows;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import ru.myitschool.projectsamsung.R;


public class ArrowGameActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    TextView text, helper, arrowTimer;
    GestureDetector gestureDetector = new GestureDetector(this);
    String leftArrow="\u2190";
    String upArrow="\u2191";
    String rightArrow="\u2192";
    String downArrow="\u2193";
    int arrowDir=-10;
    int targetDir=-10;
    int inputDir=-1;
    int wasInput=-1;
    int colorID=0;
    int time=60;
    volatile boolean running = true;
    Handler h;
    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrowgame);
        text=findViewById(R.id.textArrow);
        helper=findViewById(R.id.helper);
        arrowTimer=findViewById(R.id.arrowTimer);
        helper.setTextSize(20);
        helper.setTextColor(Color.BLACK);
        helper.setText("Нажми сюда, чтобы начать");
        helper.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        text.setText("");



        h = new Handler() {
            public void handleMessage(Message msg) {
                arrowTimer.setText(getTimeString(msg.what));
            };
        };

        helper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateArrow();
                helper.setText("Зеленый = \u21BB \n Красный = \u21BA \n Синий = \u21C4");
                helper.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                helper.setOnClickListener(null);
                updater.start();

            }
        });
    }

    public void generateArrow(){
        int temp=arrowDir;
        while(true) {
            arrowDir = (int) (Math.random() * 3);
            if(arrowDir!=temp) break;
        }

        targetDir=arrowDir;
        setArrowColor();
        targetDir=targetDir%4;
        switch(arrowDir){
            case 0:
                text.setText(leftArrow);
                break;
            case 1:
                text.setText(upArrow);
                break;
            case 2:
                text.setText(rightArrow);
                break;
            case 3:
                text.setText(downArrow);
                break;
        }
    }
    public void checkInput(){
        if (inputDir==targetDir){
            generateArrow();
            score++;
            inputDir=-1;
        }
    }
    public void setArrowColor(){
        //BLUE==REVERSE;
        //GREEN==CLOCKWISE
        //RED=COUNTERCLOCKWISE
        colorID = (int) (Math.random()*100);
        if (colorID>75){
            text.setTextColor(Color.BLUE);
            targetDir+=2;
        }else if(colorID>50){
            text.setTextColor(getResources().getColor(R.color.colorExtra));
            targetDir++;
        }else if(colorID>25){
            text.setTextColor(Color.RED);
            targetDir--;
            if(targetDir<0){
                targetDir+=4;
            }
        }else{
            text.setTextColor(Color.BLACK);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result=false;
        float swipeX = e2.getX()-e1.getX();
        float swipeY = e2.getY()-e1.getY();
        if(Math.abs(swipeX)>Math.abs(swipeY)){
            //right or left
            if(Math.abs(velocityX)>100 && Math.abs(swipeX)>100){
                if (swipeX>0){
                    onSwipeRight();
                }else{
                    onSwipeLeft();
                }
                result=true;
            }
        }else{
            //up or down
            if(Math.abs(velocityY)>100 && Math.abs(swipeY)>100){
                if (swipeY>0){
                    onSwipeDown();
                }else{
                    onSwipeUp();
                }
                result=true;
            }
        }
        return result;
    }


    private void onSwipeLeft() {
        inputDir=0;
        wasInput=inputDir;
        checkInput();
    }
    private void onSwipeUp() {
        inputDir=1;
        wasInput=inputDir;
        checkInput();
    }
    private void onSwipeRight() {
        inputDir=2;
        wasInput=inputDir;
        checkInput();
    }
    private void onSwipeDown() {
        inputDir=3;
        wasInput=inputDir;
        checkInput();
    }



    Thread updater = new Thread() {
        @Override
        public void run() {
            try {
                while (running) {
                    h.sendEmptyMessage(time);
                    if(time==0){
                        running=false;
                    }
                    Thread.sleep(1000);
                    time--;
                }
            } catch (InterruptedException e) {
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    result();
                }
            });
        }
    };

    public String getTimeString(int time){
        String time_str = "0:00";
        if(time<10){
            time_str="0:0"+time;
        }else if(time<60){
            time_str="0:"+time;
        }else{
            int min = time/60;
            if((time-min*60)<10) {
                time_str = min + ":0" + (time - min * 60);
            }else{
                time_str=min+":"+(time-min*60);
            }
        }
        return time_str;
    }

    public void result(){
        targetDir=100;
        text.setTextSize(getWindowManager().getDefaultDisplay().getWidth()/20);
        text.setTextColor(getResources().getColor(R.color.colorExtra));
        text.setText("Результат: "+score);
        helper.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        helper.setText("В меню");
        helper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArrowGameActivity.this, ArrowMenu.class);
                startActivity(intent);
            }
        });
    }


    //Not used
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }
}
