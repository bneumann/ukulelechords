package com.newbucket.ukulelechords;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newbucket.musiclib.Chord;

/**
 * This fragment holds the main fret view and has an interface to set a chord
 * @author Benjamin Giesinger
 */

public class FretFragment extends Fragment
{
    private UkeFretView mFretView;
    private Chord mChord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fretFragment = inflater.inflate(R.layout.fretview_fragment, container, false);
        if (mChord == null)
        {
            mChord = new Chord(Chord.Base.Major);
        }
        mFretView = (UkeFretView)fretFragment.findViewById(R.id.fret_board);
        setChord(mChord);
        return fretFragment;
    }

    /**
     * This function sets a chord to be displayed in this fret view
     *
     * @param chord Chord to be displayed on frets
     */
    public void setChord(Chord chord)
    {
        mChord = chord;
        if(mFretView != null)
        {
            mFretView.setChord(chord);
        }
    }

}
