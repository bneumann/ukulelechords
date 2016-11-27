package com.newbucket.ukulelechords;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newbucket.musiclib.Chord;

/**
 * Created by benni on 25.11.2016.
 */

public class FretFragment extends Fragment
{
    private UkeFretView mFretView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fretFragment = inflater.inflate(R.layout.fretview_fragment, container, false);
        mFretView = (UkeFretView)fretFragment.findViewById(R.id.fret_board);
        return fretFragment;
    }

    public void setChord(Chord chord) {
        if(mFretView != null)
        {
            mFretView.setChord(chord);
        }
    }

}
