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
    public String TAG = "MainActivity";
    private Chord mCurrentChord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chordlib_activity);
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
