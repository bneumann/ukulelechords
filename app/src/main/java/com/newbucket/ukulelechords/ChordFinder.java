package com.newbucket.ukulelechords;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by benni on 06.11.2016.
 */

public class ChordFinder
{
    private int[] mFretValues;
    private Chord mChord;
    private Tuning mTuning;
    private int[][] mResultMatrix;
    private static String TAG = "ChordFinder";

    public ChordFinder(Tuning tuning, Chord chord) {
        mTuning = tuning;
        mChord = chord;

        mFretValues = new int[mTuning.size()];
        Arrays.fill(mFretValues, 0);

        UpdateFretValues();
    }

    public int[] GetFretValues()
    {
        return mFretValues;
    }

    public void UpdateFretValues() {
        ArrayList<Note> c = mChord.GetNotes();

        int[][] tmpMatrix = new int[mTuning.size()][c.size()];
        mResultMatrix = tmpMatrix.clone();
        for(int i = 0; i < mTuning.size(); i++) {
            for(int j = 0; j < c.size(); j++) {
                Note tmpNote = new Note(c.get(j).GetNote());
                int fretPosition = tmpNote.transpose(-mTuning.get(i).GetNote()).GetNote();
                tmpMatrix[i][j] = fretPosition ;
                mResultMatrix[i][j] = fretPosition;
            }
            //FIXME: Find minimum for each note in chord.
            Arrays.sort(tmpMatrix[i]);
            mFretValues[i] = tmpMatrix[i][0];
        }
    }
}
