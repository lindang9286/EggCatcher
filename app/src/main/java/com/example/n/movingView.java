package com.example.n;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.Random;

public class movingView extends View
{
    private Bitmap nest[] = new Bitmap[4];
    private int nestX;
    private int canvasWidth, canvasHeight;
    private boolean touch = false;
    private int eggX, eggY;
    private int score=0, dem=3;

    private Bitmap egg;
    private Bitmap backgroundImage;
    private Paint scorePaint = new Paint();
    private Bitmap life[] = new Bitmap[2];

    public movingView(Context context)
    {
        super(context);
        nest[0] = BitmapFactory.decodeResource(getResources(), R.drawable.nest0);
        nest[1] = BitmapFactory.decodeResource(getResources(), R.drawable.nest1);
        nest[2] = BitmapFactory.decodeResource(getResources(), R.drawable.nest2);
        nest[3] = BitmapFactory.decodeResource(getResources(), R.drawable.nest3);
        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setTextSize(60);
        scorePaint.setAntiAlias(true);
        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);
        egg = BitmapFactory.decodeResource(getResources(),R.drawable.egg);

        int width  = Resources.getSystem().getDisplayMetrics().widthPixels;
        eggY=0;
        eggX=200;
        nestX=width/2;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundResource(R.drawable.background);
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        int minNestX = 0;
        int maxNestX = canvasWidth - nest[0].getWidth();
        canvas.drawBitmap(nest[0], nestX,canvasHeight-90,null);
        //nestX=canvasWidth/2;
        if (touch)
        {
            if (score == 0){canvas.drawBitmap(nest[0], nestX,canvasHeight-90,null);}
            if (score == 1){canvas.drawBitmap(nest[1], nestX,canvasHeight-90,null);}
            if (score == 2){canvas.drawBitmap(nest[2], nestX,canvasHeight-90,null);}
            if (score >= 3){canvas.drawBitmap(nest[3], nestX,canvasHeight-90,null);}


            if(nestX<minNestX){nestX=minNestX;};
            if(nestX>maxNestX){nestX=maxNestX;};
        }

        eggY=eggY+30;

        if(hitchecker(eggX,eggY))
        {
            eggY=0;
            score=score+1;
            eggX=(int) Math.floor(Math.random()*(canvasWidth-nest[0].getWidth()))+nest[0].getWidth()/2;

        };

        if(eggY>canvasHeight)
        {
            eggY=0;
            dem=dem-1;
            eggX=(int) Math.floor(Math.random()*(canvasWidth-nest[0].getWidth()))+nest[0].getWidth()/2;

        };
        canvas.drawBitmap(egg,eggX,eggY,null);

        if(dem==3)
        {
            canvas.drawBitmap(life[0],canvasWidth-280,10,null);
            canvas.drawBitmap(life[0],canvasWidth-190,10,null);
            canvas.drawBitmap(life[0],canvasWidth-100,10,null);
        }
        if(dem==2)
        {
            canvas.drawBitmap(life[1],canvasWidth-280,10,null);
            canvas.drawBitmap(life[0],canvasWidth-190,10,null);
            canvas.drawBitmap(life[0],canvasWidth-100,10,null);
        }
        if(dem==1)
        {
            canvas.drawBitmap(life[1],canvasWidth-280,10,null);
            canvas.drawBitmap(life[1],canvasWidth-190,10,null);
            canvas.drawBitmap(life[0],canvasWidth-100,10,null);
        }
        if(dem==0)
        {
            canvas.drawBitmap(life[1],canvasWidth-280,10,null);
            canvas.drawBitmap(life[1],canvasWidth-190,10,null);
            canvas.drawBitmap(life[1],canvasWidth-100,10,null);
            Intent gameover= new Intent(getContext(),GameOver.class);
            gameover.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            gameover.putExtra("diem",score);
            getContext().startActivity(gameover);


        }




        canvas.drawText(score+"", 20,60, scorePaint);


    };
    public boolean hitchecker(int x, int y)
    {
        if(nestX<x&&x<(nestX+nest[0].getWidth()-egg.getWidth())&&canvasHeight-nest[0].getHeight()<y&&y<canvasHeight)
        {
            return true;
        };
        return false;
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch=true;
            handleTouch(event);
        }
        return true;
    };

    void handleTouch(MotionEvent m)
    {
        int x=(int) m.getX();
        if(x>0&&x<canvasWidth/2)
        {
            nestX=nestX-100;
        }else{
            nestX=nestX+100;
        };

    }
}
