package ru.myitschool.projectsamsung.bubbles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import ru.myitschool.projectsamsung.TimerThread;
import java.util.ArrayList;

public class DrawThread extends Thread {

    TimerThread timer = new TimerThread();

    private SurfaceHolder surfaceHolder;

    private volatile boolean running = true;

    public int width;
    public int height;

    private int pressedX=-1000;
    private int pressedY=-1000;
    private int radius=50;

    private int score = 0;

    private int amountOfCircles=5;
    private ArrayList<Circle> circles= new ArrayList<Circle>();

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public void requestStop() {
        running = false;
    }

    public void deletePoint(int x, int y) {
        pressedX = x;
        pressedY = y;
    }


    @Override
    public void run() {
        Paint paint = new Paint();
        for (int i = 0; i < amountOfCircles; i++) {
            int flag=0;
            int xC = (int)(radius+Math.random()*(width-2*radius));
            int yC = (int)((height/6)+Math.random()*(height-height/3));
            for(int j=0; j<circles.size(); j++){
                if( xC<circles.get(j).getX()+2*radius && xC>circles.get(j).getX()-2*radius && yC<circles.get(j).getY()+2*radius && yC>circles.get(j).getY()-2*radius ){
                    i--;
                    flag=1;
                    break;
                }
            }
            if(flag==1){
                continue;
            }
            circles.add(new Circle(xC, yC, radius));
        }
        timer.start();
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();

            if (canvas != null) {
                try {

                    if(amountOfCircles==1 || timer.getSec()==60){
                        timer.stopRunningTime();
                        circles.clear();
                        paint.setColor(Color.DKGRAY);
                        canvas.drawText("Счет: "+score, canvas.getWidth()/2, canvas.getHeight()/2, paint);
                    }

                    paint.setColor(Color.rgb(255, 244, 79));
                    canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), paint);
                    if(timer.isAlive()){
                        paint.setColor(Color.DKGRAY);
                        paint.setTextSize(canvas.getHeight()/20);
                        canvas.drawText(timer.getTimeString(), canvas.getWidth()-canvas.getWidth()/5, canvas.getHeight()/12, paint);
                        canvas.drawText("Счет: "+score, canvas.getWidth()/100, canvas.getHeight()/12, paint);
                    }else{
                        paint.setColor(Color.rgb(00,128,00));
                        paint.setTextSize(canvas.getHeight()/12);
                        canvas.drawText("Счет: "+score, canvas.getWidth()/9, canvas.getHeight()/2, paint);
                        requestStop();
                    }


                    paint.setColor(Color.BLUE);
                    for (int i = 0; i < circles.size(); i++) {
                        if(i==0){
                            paint.setColor(Color.rgb(0,0,180));
                            canvas.drawCircle(circles.get(i).getX(), circles.get(i).getY(), circles.get(i).getRadius(), paint);
                        }else{
                            paint.setColor(Color.BLUE);
                            canvas.drawCircle(circles.get(i).getX(), circles.get(i).getY(), circles.get(i).getRadius(), paint);
                        }
                    }

                    for (int i = 0; i < circles.size(); i++) {
                        Circle tempCircle = circles.get(i);
                        if(pressedX<=tempCircle.getX()+tempCircle.getRadius() &&
                                pressedX>=tempCircle.getX()-tempCircle.getRadius() &&
                                pressedY<=tempCircle.getY()+tempCircle.getRadius() &&
                                pressedY>=tempCircle.getY()-tempCircle.getRadius()){
                            if(i==0){
                                score++;
                                if(amountOfCircles<60) amountOfCircles++;
                                circles.clear();
                                createTask();
                            }else{
                                score--;
                                if(score<0) score=0;
                                amountOfCircles--;
                                circles.clear();
                                createTask();
                            }
                            pressedX=-1000;
                            pressedY=-1000;
                            break;
                        }
                    }

                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void createTask(){
        for (int i = 0; i < amountOfCircles; i++) {
            int flag = 0;
            int xC = (int) (radius + Math.random() * (width - 2 * radius));
            int yC = (int) ((height / 6) + Math.random() * (height - height / 3));
            for (int j = 0; j < circles.size(); j++) {
                if (xC < circles.get(j).getX() + 2 * radius && xC > circles.get(j).getX() - 2 * radius && yC < circles.get(j).getY() + 2 * radius && yC > circles.get(j).getY() - 2 * radius) {
                    i--;
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                continue;
            }
            circles.add(new Circle(xC, yC, radius));
        }
    }
}

