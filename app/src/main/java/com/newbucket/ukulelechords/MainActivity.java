package com.newbucket.ukulelechords;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    private final String SYMBOL_FLAT = "\u266D";
    private final String SYMBOL_NATURAL = "\u266E";
    private final String SYMBOL_SHARP = "\u266F";

    public String TAG = "MainActivity";

    protected String mKey = "C";
    protected String mIntonation = SYMBOL_NATURAL;
    protected String mHarmony = "";

    private ViewGroup mChordMap;
    private ViewGroup mHarmonyMap;
    private UkeFretView mFretView;
    private ChordLib mChordlib;
    private Chord mCurrentChord;

    private String[] mChordArray = {"C", "D", "E", "F", "G", "A", "B", SYMBOL_FLAT};
    private String[] mHarmonyArray = {"M", "7", "m", "m7", "dim", "aug", "6", "maj7", "9"};

    private boolean mChordsOpen, mKeysOpen;
    private Animation mAnimationOpen, mAnimationClose;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mChordMap = (ViewGroup) findViewById(R.id.chord_map);
        mHarmonyMap = (ViewGroup) findViewById(R.id.harm_map);
        mFretView = (UkeFretView) findViewById(R.id.fret_view);
        mChordlib = new ChordLib();

        mAnimationOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        mAnimationClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        mChordsOpen = false;
        mKeysOpen = false;

        mFretView.SetChord(mChordlib.getChord(mKey));
        mFretView.setOnLongClickListener(new onFretLongCLickListener());

        // The tree observer throws this event when the layout has been measured.
        ViewTreeObserver vto = mChordMap.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                // draw keys on hidden chord map layout
                drawKeys();
                // draw harmonies on hidden harmony layout
                drawHarmonies();
            }
        });

        FloatingActionButton vAddHarmonies = (FloatingActionButton) findViewById(R.id.addharms);
        vAddHarmonies.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(mKeysOpen) {
                    hide(mHarmonyMap);
                    mKeysOpen = !mKeysOpen;
                }
                if(!mChordsOpen) {
                    reveal(mChordMap);
                }
                else
                {
                    hide(mChordMap);
                }
                mChordsOpen = !mChordsOpen;
            }
        });

        FloatingActionButton vAddChords = (FloatingActionButton) findViewById(R.id.addchords);
        vAddChords.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(mChordsOpen) {
                    hide(mChordMap);
                    mChordsOpen = !mChordsOpen;
                }
                if(!mKeysOpen) {
                    reveal(mHarmonyMap);
                }
                else
                {
                    hide(mHarmonyMap);
                }
                mKeysOpen = !mKeysOpen;
            }
        });
    }


    private void drawKeys()
    {
        ArrayList<View> tList = getAllChildren(mChordMap);
        int counter = 0;
        for (View v : tList)
        {
            // Print key on buttons
            FloatingActionButton fab = (FloatingActionButton) v;
            Bitmap.Config conf = Bitmap.Config.ARGB_4444; // see other conf types
            Bitmap bmp = Bitmap.createBitmap(24, 24, conf); // this creates a MUTABLE bitmap
            Canvas t = new Canvas(bmp);
            Paint p = new Paint();
            p.setColor((Color.WHITE));
            p.setTextSize(20);
            p.setStrokeWidth(12);
            p.setElegantTextHeight(true);
            p.setAntiAlias(true);
            // Draw keys
            if (counter != tList.size() - 1)
            {
                // Remove natural sign if intonation is natural because it looks weird.
                if (mIntonation.equals(SYMBOL_NATURAL))
                {
                    t.drawText(mChordArray[counter], 5, 20, p);
                }
                else
                {
                    t.drawText(mChordArray[counter] + mIntonation, 0, 20, p);
                }
            }
            else // Draw intonation symbol
            {
                t.drawText(mChordArray[counter], 8, 20, p);
            }

            // TODO: Use drawables
            fab.setImageBitmap(bmp);
            fab.setTag(R.id.tag_key_chord, mChordArray[counter++]);

            fab.setOnClickListener(new onKeyClickListener());
        }
    }

    private class onFretLongCLickListener implements View.OnLongClickListener
    {
        @Override
        public boolean onLongClick(View v)
        {
            Log.d(TAG, "Long click detected");
            return true;
        }
    }

    private class onKeyClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            FloatingActionButton fab = (FloatingActionButton) v;
            String key = (String) v.getTag(R.id.tag_key_chord);
            String harm = (String) v.getTag(R.id.tag_key_harmony);
            if (key != null)
            {
                if (key.equals(SYMBOL_SHARP) || key.equals(SYMBOL_FLAT) || key.equals(SYMBOL_NATURAL))
                {
                    switch (mIntonation)
                    {
                        case SYMBOL_NATURAL:
                            mIntonation = SYMBOL_FLAT;
                            mChordArray[mChordArray.length - 1] = SYMBOL_SHARP;
                            break;
                        case SYMBOL_FLAT:
                            mIntonation = SYMBOL_SHARP;
                            mChordArray[mChordArray.length - 1] = SYMBOL_NATURAL;
                            break;
                        case SYMBOL_SHARP:
                            mIntonation = SYMBOL_NATURAL;
                            mChordArray[mChordArray.length - 1] = SYMBOL_FLAT;
                            break;
                    }
                    drawKeys();
                }
                else
                {
                    mKey = key;
                    resetBackgroundColor(mChordMap);
                }
            }
            if (harm != null)
            {
                // Chordlib has no "M" extension
                harm = harm.equals("M") ? "" : harm;
                mHarmony = harm;
                resetBackgroundColor(mHarmonyMap);
            }
            // TODO: This is crappy code. Oh yeah. Rewrite the whole intonation-symbol thingy
            String intonation = "";
            switch (mIntonation)
            {
                case SYMBOL_NATURAL:
                    intonation = "";
                    break;
                case SYMBOL_FLAT:
                    intonation = "b";
                    break;
                case SYMBOL_SHARP:
                    intonation = "#";
                    break;
            }
            String nChord = mKey + intonation + mHarmony;
            Log.d(TAG, nChord);
            mCurrentChord = mChordlib.getChord(nChord);
            mFretView.SetChord(mCurrentChord);
            fab.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorActiveButton));
        }
    }

    private void drawHarmonies()
    {
        ArrayList<View> tList = getAllChildren(mHarmonyMap);
        int counter = 0;
        for (View v : tList)
        {
            // Print key on buttons
            FloatingActionButton fab = (FloatingActionButton) v;
            if (fab.getWidth() == 0 || fab.getHeight() == 0)
            {
                // As long as there is no landscape version we close the menu here
                return;
            }
            Bitmap.Config conf = Bitmap.Config.ARGB_4444; // see other conf types
            Bitmap bmp = Bitmap.createBitmap(fab.getWidth(), fab.getHeight(), conf); // this creates a MUTABLE bitmap
            Canvas canvas = new Canvas(bmp);
            Paint p = new Paint();
            p.setColor((Color.WHITE));
            p.setTextSize(fab.getHeight() * 0.60f);
            //p.setStrokeWidth(12);
            p.setElegantTextHeight(true);
            p.setAntiAlias(true);

            Rect bounds = new Rect();
            p.getTextBounds(mHarmonyArray[counter], 0, mHarmonyArray[counter].length(), bounds);
            int xPos = (canvas.getWidth() / 2) - (bounds.width() / 2);
            int yPos = (int) ((fab.getHeight() / 2) - ((p.descent() + p.ascent()) / 2));
            // Draw harmonies
            canvas.drawText(mHarmonyArray[counter], xPos, yPos, p);

            // TODO: Use drawables
            fab.setImageBitmap(bmp);
            fab.setTag(R.id.tag_key_harmony, mHarmonyArray[counter++]);

            fab.setOnClickListener(new onKeyClickListener());
        }
    }

    private void resetBackgroundColor(ViewGroup buttonGroup)
    {
        ArrayList<View> tList = getAllChildren(buttonGroup);
        for (View b : tList)
        {
            b.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        }
    }

    private ArrayList<View> getAllChildren(ViewGroup group)
    {
        ArrayList<View> reply = new ArrayList<View>();
        for (int i = 0; i < group.getChildCount(); i++)
        {
            View v = group.getChildAt(i);
            if (v instanceof ViewGroup)
            {
                reply.addAll(getAllChildren((ViewGroup) v));
            }
            else if (v instanceof View)
            {
                reply.add(v);
            }
        }
        return reply;
    }

    private void reveal(ViewGroup targetView)
    {
        ArrayList<View> tList = getAllChildren(targetView);
        for (View v : tList)
        {
            reveal(v);
        }
    }

    private void reveal(final View targetView)
    {
        Log.i(TAG, "Starting 'show' animation");
        targetView.startAnimation(mAnimationOpen);
    }

    private void hide(ViewGroup targetView)
    {
        ArrayList<View> tList = getAllChildren(targetView);
        for (View v : tList)
        {
            hide(v);
        }
    }

    private void hide(final View targetView)
    {
        Log.i(TAG, String.format("Starting 'close' animation [View: %d]", targetView.getId()));
        targetView.startAnimation(mAnimationClose);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
