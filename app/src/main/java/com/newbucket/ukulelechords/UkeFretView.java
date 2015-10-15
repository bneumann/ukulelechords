package com.newbucket.ukulelechords;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by benni on 15.10.2015.
 */
public class UkeFretView extends ImageView {

    private Paint mThickStroke;
    private Paint mThinStroke;

    private ArrayList<Finger> mFingers;

    private final int NUM_FRETS = 5;
    private final int NUM_STRINGS = 4;

    public UkeFretView(Context context) {
        super(context);
        init();
    }

    public UkeFretView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UkeFretView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        mThickStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        mThickStroke.setColor(Color.BLACK);
        mThickStroke.setStrokeWidth(30f);

        mThinStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        mThinStroke.setColor(Color.BLACK);
        mThinStroke.setStrokeWidth(4f);

        mFingers = new ArrayList<>();
        mFingers.add(new Finger(2, 1));
        mFingers.add(new Finger(3, 2));
        mFingers.add(new Finger(2, 3));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //TODO: If rotation we could flip the values here. maybe
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();

        // Horizontal lines
        int rimWidth = (int)(0.8f * (float)height);
        int sideSpace = (height - rimWidth) / 2;
        int div = rimWidth /(NUM_STRINGS - 1);
        for(int i = sideSpace; i <= height - sideSpace / 2; i += div)
        {
            canvas.drawLine(0, i, width - 1, i, mThinStroke);
        }
        // Main bar
        canvas.drawLine(2, sideSpace, 2, height - sideSpace, mThickStroke);
        int fret = (width - 1) / NUM_FRETS;
        // Vertical lines | | |
        for(int i = fret; i <= width; i += fret)
        {
            canvas.drawLine(i, sideSpace, i, height - sideSpace, mThinStroke);
        }
        for(Finger p : mFingers)
        {
            canvas.drawCircle(fret * (p.fret - 0.5f), (p.stringnum - 1) * div + sideSpace, (float)fret * 0.3f, mThickStroke);
        }

        //canvas.rotate(getRotation(), getWidth() / 2, getHeight() / 2);
    }

    public class Finger
    {
        public int fret, stringnum;

        public Finger(int fret, int stringnum)
        {
            this.fret = fret;
            this.stringnum = stringnum;
        }
    }
}
