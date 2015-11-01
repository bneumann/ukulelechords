package com.newbucket.controls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.newbucket.ukulelechords.Chord;
import com.newbucket.ukulelechords.Note;

/**
 * Created by benni on 15.10.2015.
 */
public class UkeFretView extends ImageView{

    private static final String TAG = "FretView";
    private Paint mThickStroke;
    private Paint mThinStroke;
    private Paint mNotePaint;

    private Chord mChord;

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

        mNotePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mNotePaint.setColor(Color.BLACK);
        mNotePaint.setStrokeWidth(1f);
        mNotePaint.setShadowLayer(12, 0, 0, Color.YELLOW);
    }

    public void SetChord(Chord chord)
    {
        mChord = chord;
        this.invalidate();
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
        if(mChord != null) {
            for (Note p : mChord.getNotes()) {
                canvas.drawCircle(fret * (p.getFret() - 0.5f), (p.getString() - 1) * div + sideSpace, (float) fret * 0.3f, mNotePaint);
            }
        }

        //canvas.rotate(getRotation(), getWidth() / 2, getHeight() / 2);
    }

}
