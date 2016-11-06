package com.newbucket.ukulelechords;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by benni on 06.11.2016.
 */

public class ChordFinder
{
    private int[] mFretValues;

    public ChordFinder(Tuning t, Chord chord) {
        mFretValues = new int[t.size()];
        Arrays.fill(mFretValues, 0);

        ArrayList<Note> c = chord.GetNotes();

        int[][] tmpMatrix = new int[t.size()][c.size()];
        for(int i = 0; i < t.size(); i++) {
            for(int j = 0; j < c.size(); j++) {
                int fretPosition = t.get(i).GetNote() - c.get(j).GetNote();
                if(fretPosition >= 0) {
                    tmpMatrix[i][j] = fretPosition ;
                } else {
                    tmpMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
            Arrays.sort(tmpMatrix[i]);
            mFretValues[i] = tmpMatrix[i][0];
        }
    }

    public int[] GetFretValues()
    {
        return mFretValues;
    }
}
