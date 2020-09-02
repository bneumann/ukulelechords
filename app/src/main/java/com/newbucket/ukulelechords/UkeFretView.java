package com.newbucket.ukulelechords;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.newbucket.musiclib.Chord;
import com.newbucket.musiclib.ChordFinder;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

/**
 * This class shows a fret and rendres the finger positions for a given chord.
 * @author Benjamin Giesinger
 */
public class UkeFretView extends AppCompatImageView
{

    private static final String TAG = "FretView";
    private Paint mThickStroke;
    private Paint mThinStroke;
    private Paint mNotePaint;

    private Chord mChord;
    private ChordFinder mChordFinder;

    private final float shadowRadius = 4.0f;
    private final float shadowDx = 0.0f;
    private final float shadowDy = 2.0f;
    private final int shadowColor = Color.BLACK;
    private final float mFretWidthRatio = 3f / 3.5f; // Measured from Ukulele

    // TODO: 10.12.2016 This shoudl be settable from outside
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);

        int desiredWidth = 300;
        int desiredHeight = 200;

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = parentWidth;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, parentWidth);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = parentHeight;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
//            height = (int)((float)parentWidth * mFretWidthRatio);
            height = parentHeight;
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }

    private void init()
    {
        // FIXME: 09.12.2016 Add tuning from preferences
        mChordFinder = new ChordFinder();

        int cMain = ContextCompat.getColor(getContext(), R.color.colorPrimary);

        mThickStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        mThickStroke.setColor(cMain);
        mThickStroke.setStrokeWidth(30f);

        mThinStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        mThinStroke.setColor(cMain);
        mThinStroke.setStrokeWidth(4f);

        mNotePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mNotePaint.setColor(Color.BLACK);
        mNotePaint.setStrokeWidth(1f);
        this.setLayerType(LAYER_TYPE_SOFTWARE, mNotePaint);
        mNotePaint.setShadowLayer(shadowRadius, shadowDx, shadowDy, shadowColor);
    }

    public void setChord(Chord chord)
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
            mChordFinder.FindNewChord(mChord);
            int[] fVals = mChordFinder.GetFretValues();
            for (int i = 0; i < fVals.length; i++) {
                canvas.drawCircle(fret * (fVals[i] - 0.5f), (fVals.length - i - 1) * div + sideSpace, (float) fret * 0.3f, mNotePaint);
            }
        }

    }

}
