package com.newbucket.ukulelechords;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.newbucket.controls.PieChart;
import com.newbucket.controls.UkeFretView;

public class MainActivity extends AppCompatActivity
{

    private final String SYMBOL_FLAT = "\u266D";
    private final String SYMBOL_NATURAL = "\u266E";
    private final String SYMBOL_SHARP = "\u266F";

    public String TAG = "MainActivity";

    protected String mKey = "C";
    protected String mIntonation = SYMBOL_NATURAL;
    protected String mHarmony = "";

    private UkeFretView mFretView;
    private PieChart mPieView;
    private ChordLib mChordlib;
    private Chord mCurrentChord;

    private Context mContext;

    private String[] mChordArray = {"C", "D", "E", "F", "G", "A", "B", SYMBOL_FLAT};
    private String[] mHarmonyArray = {"M", "7", "m", "m7", "dim", "aug", "6", "maj7", "9"};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = getApplicationContext();

        mPieView = (PieChart) findViewById(R.id.pie_menu);
        mFretView = (UkeFretView) findViewById(R.id.fret_view);

        mChordlib = new ChordLib();

        mFretView.SetChord(mChordlib.getChord(mKey));
        mFretView.setOnLongClickListener(new onFretLongCLickListener());

        mPieView.addItem("Test", 1, ContextCompat.getColor(mContext, R.color.colorPrimary));
        mPieView.addItem("Test2", 2, ContextCompat.getColor(mContext, R.color.colorActiveButton));
        mPieView.addItem("Test3",1, ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        mPieView.setShowText(true);
        mPieView.invalidate();

        FloatingActionButton vAddChords = (FloatingActionButton) findViewById(R.id.addchords);
        vAddChords.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mPieView.setVisibility(View.VISIBLE);
                Log.d(TAG, "Set Visible");
            }
        });
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
