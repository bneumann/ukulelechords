package com.newbucket.ukulelechords;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;

import com.newbucket.musiclib.Chord;
import com.newbucket.musiclib.ChordLib;
import com.newbucket.musiclib.Note;

import java.util.ArrayList;

public class MainActivity extends Activity implements ChordSelectorFragment.OnChordSelectListener
{

    private enum Symbols {
        Flat,
        Natural,
        Sharp
    }

    public String TAG = "MainActivity";

    private Note mRootNote = new Note();
    private Symbols mIntonation = Symbols.Natural;
    private Chord.Base mHarmony = Chord.Base.Major;
    private Chord.Modifier mModifier = Chord.Modifier.None;

    private ViewGroup mChordMap;
    private ViewGroup mHarmonyMap;
    private UkeFretView mFretView;
    private ChordLib mChordlib;
    private Chord mCurrentChord;
    private Fragment mContentFragment;

    private String[] mChordArray = {"C", "D", "E", "F", "G", "A", "B"};
    private String[] mHarmonyArray = {"M", "7", "m", "m7", "dim", "aug", "6", "maj7", "9"};

    private boolean mChordsOpen, mKeysOpen;
    private Animation mAnimationOpen, mAnimationClose;

    private final boolean mOldDesign = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chordlib_activity);

        //region Code for old design
        if (mOldDesign)
        {
            setContentView(R.layout.activity_main);
            // FIXME: Implement accoring to https://developer.android.com/training/implementing-navigation/nav-drawer.html
            DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            ListView mDrawerList = (ListView) findViewById(R.id.left_drawer);

            // Set the adapter for the list view
            mDrawerList.setAdapter(new MenuDrawerAdapter(this,
                    R.layout.menu_list_view, getResources().getStringArray(R.array.slide_menu)));
            mDrawerList.setSelection(1);
            // Set the list's click listener
            mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {

                }
            });

            mDrawerLayout.openDrawer(Gravity.LEFT);

            mChordMap = (ViewGroup) findViewById(R.id.chord_map);
            mHarmonyMap = (ViewGroup) findViewById(R.id.harm_map);
            mFretView = (UkeFretView) findViewById(R.id.fret_view);


            mChordlib = new ChordLib();

            mAnimationOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
            mAnimationClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

            mChordsOpen = false;
            mKeysOpen = false;

            mFretView.setChord(mChordlib.getChord("C"));
            mFretView.setOnLongClickListener(new onFretLongCLickListener());

            FloatingActionButton changeAccidental = (FloatingActionButton) findViewById(R.id.change_accidental);
            changeAccidental.setOnClickListener(new onChangeAccidental());

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
                    if (mKeysOpen)
                    {
                        hide(mHarmonyMap);
                        mKeysOpen = !mKeysOpen;
                    }
                    if (!mChordsOpen)
                    {
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
                    if (mChordsOpen)
                    {
                        hide(mChordMap);
                        mChordsOpen = !mChordsOpen;
                    }
                    if (!mKeysOpen)
                    {
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
        //endregion

    }

    private void drawKeys() {
        ArrayList<View> tList = getAllChildren(mChordMap);
        int counter = 0;
        for (View v : tList)
        {
            // Print key on buttons
            FloatingActionButton fab = (FloatingActionButton) v;
            Bitmap tmpBitmap;
            // Draw only regular key, not the accidental changer
            if (counter != tList.size() - 1)
            {
                // Remove natural sign if intonation is natural because it looks weird.
                if (mIntonation == Symbols.Natural)
                {
                    tmpBitmap = textAsBitmap(mChordArray[counter], fab.getHeight() * 0.75f, Color.WHITE);
                }
                else
                {
                    String accidental = mIntonation == Symbols.Flat ? Note.CodeFlat : Note.CodeSharp;
                    tmpBitmap = textAsBitmap(mChordArray[counter] + accidental, fab.getHeight() * 0.75f, Color.WHITE);
                }
                // TODO: Use drawables
                fab.setImageBitmap(tmpBitmap);
                fab.setOnClickListener(new onKeyClickListener());
                fab.setTag(R.id.tag_key_chord, mChordArray[counter++]);
            }
        }
    }

    private void drawHarmonies() {
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
            fab.setImageBitmap(textAsBitmap(mHarmonyArray[counter], fab.getHeight() * 0.75f, Color.WHITE));
            fab.setTag(R.id.tag_key_harmony, mHarmonyArray[counter++]);

            fab.setOnClickListener(new onHarmonyClickListener());
        }
    }

    private class onFretLongCLickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v)
        {
            Log.d(TAG, "Long click detected");
            return true;
        }
    }

    private class onChangeAccidental implements View.OnClickListener {
        private int mIcon;

        @Override
        public void onClick(View v) {
            Log.d(TAG, String.format("Switching state to %s", mIntonation));
            switch(mIntonation)
            {
                case Natural:
                    mIcon = R.drawable.ic_key_up; // < next Icon
                    mIntonation = Symbols.Flat; // current State
                    break;
                case Flat:
                    mIcon = R.drawable.ic_key_reset;
                    mIntonation = Symbols.Sharp;
                    break;
                case Sharp:
                    mIcon = R.drawable.ic_key_down;
                    mIntonation = Symbols.Natural;
                    break;
                default:
                    mIcon = R.drawable.ic_key_down;
                    mIntonation = Symbols.Natural;
                    break;
            }
            ((FloatingActionButton)v).setImageResource(mIcon);
            drawKeys();
            updateChord();
        }
    }

    private class onHarmonyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v)
        {
            String harm = (String) v.getTag(R.id.tag_key_harmony);
            // Chordlib has no "M" extension
            mModifier = Chord.Modifier.None;
            switch(harm) {
                case "7":
                    mHarmony = Chord.Base.Major;
                    mModifier = Chord.Modifier.Seven;
                    break;
                case "m":
                    mHarmony = Chord.Base.Minor;
                    break;
                case "m7":
                    mHarmony = Chord.Base.Minor;
                    mModifier = Chord.Modifier.Seven;
                    break;
                case "dim":
                    mHarmony = Chord.Base.Dim;
                    break;
                case "aug":
                    mHarmony = Chord.Base.Aug;
                    break;
                case "6":
                    mHarmony = Chord.Base.Major;
                    mModifier = Chord.Modifier.Sixth;
                    break;
                case "maj7":
                    mHarmony = Chord.Base.Major;
                    mModifier = Chord.Modifier.Major;
                    break;
                case "9":
                    mHarmony = Chord.Base.Major;
                    mModifier = Chord.Modifier.Nine;
                    break;
                case "M":
                default:
                    mHarmony = Chord.Base.Major;
            }
            resetBackgroundColor(mHarmonyMap);
            v.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorButtonActive));
            updateChord();
        }
    }

    private class onKeyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v)
        {
            FloatingActionButton fab = (FloatingActionButton) v;
            mRootNote = new Note((String) v.getTag(R.id.tag_key_chord));
            resetBackgroundColor(mChordMap);
            fab.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorButtonActive));
            updateChord();
        }
    }

    public void updateChord() {
        if(mModifier == Chord.Modifier.None) {
            mCurrentChord = new Chord(mHarmony, mRootNote);
        }else {
            mCurrentChord = new Chord(mHarmony, mRootNote, mModifier);
        }
        Log.d(TAG, "Current Chord: " + mCurrentChord.toString());
//        mCurrentChord = mChordlib.getChord(nChord);
        mFretView.setChord(mCurrentChord);
    }

    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }


    private void resetBackgroundColor(ViewGroup buttonGroup)
    {
        ArrayList<View> tList = getAllChildren(buttonGroup);
        for (View b : tList)
        {
            b.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorButton));
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

    /**
     * Reveals a complete ViewGroup
     * @param targetView ViewGroup to be set to visible
     */
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
        targetView.setClickable(true);
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
        targetView.setClickable(false);
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

    private void updateFretFragment()
    {
        Log.d(TAG, mCurrentChord.toString());
        FretFragment fretFrag = (FretFragment)getFragmentManager().findFragmentById(R.id.fret_fragment);
        if(fretFrag != null)
        {
            fretFrag.setChord(mCurrentChord);
        }
        else
        {
            // If we are on a different pane it might occur that we want to load the fret fragment here.
        }
    }

    @Override
    public void onChordSelected(Chord chord)
    {
        mCurrentChord = chord;
        Log.d(TAG, chord.toString());
        updateFretFragment();
    }

    @Override
    public void onUpPressed()
    {
        if(mCurrentChord != null)
        {
            mCurrentChord.transpose(1);
            updateFretFragment();
        }
    }

    @Override
    public void onDownPressed()
    {
        if(mCurrentChord != null)
        {
            mCurrentChord.transpose(-1);
            updateFretFragment();
        }
    }
}
