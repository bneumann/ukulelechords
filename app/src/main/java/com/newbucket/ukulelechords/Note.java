package com.newbucket.ukulelechords;

import android.util.Log;

import java.io.Console;
import java.util.Arrays;

/**
 * Created by benni on 16.10.2015.
 */
public class Note {
    private int mNote;
    private int mBase;
    private int mTune;
    private String[] maScaleSharp= {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    private String[] maScaleFlat= {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};
    private String[] maTuning = {"A", "E", "C", "G"};


    private boolean mBaseSharp = false;
    private boolean mNoteSharp = false;
    private String TAG = "Note";

    public static final int TUNING_UKULELE[] = {tScale.A, tScale.E, tScale.C, tScale.G};

    public static final class tScale
    {
        public static final int C = 0;
        public static final int Cs = 1;
        public static final int Db = 1;
        public static final int D = 2;
        public static final int Ds = 3;
        public static final int Eb = 3;
        public static final int E = 4;
        public static final int F = 5;
        public static final int Fs = 6;
        public static final int Gb = 6;
        public static final int G = 7;
        public static final int Gs = 8;
        public static final int Ab = 8;
        public static final int A = 9;
        public static final int As = 10;
        public static final int Bb = 10;
        public static final int B = 11;
    }

    public Note()
    {
        mBaseSharp = true;
        mNoteSharp = true;
        mNote = 0;
        mBase = 0;
        mTune = 2; // Because 3rd string no fret is "C"
        createTuneMatrix(new int[]{tScale.A, tScale.E, tScale.C, tScale.G});
    }

    public Note(String note)
    {
        if(note.contains("#"))
        {
            mBaseSharp = true;
            mNoteSharp = true;
        }
        CalculateBaseIndex(note);
        CalculateNoteIndex(note);
    }

    public Note(String note, String base)
    {
        if(base.contains("#"))
        {
            mBaseSharp = true;
        }
        if(note.contains("#"))
        {
            mNoteSharp = true;
        }
        CalculateBaseIndex(base);
        CalculateNoteIndex(note);
    }

    /**
     *
      * @param str The string tune (C, C#, Gb, ...) where the note should be played
     * @param fret The fret from 1 to ... where the note should be played
     */
    public Note(String str, int fret)
    {
        if(str.contains("#"))
        {
            mBaseSharp = true;
        }
        mNoteSharp = true;
        CalculateBaseIndex(str);
        mNote = mBase + fret;
    }

    private void CalculateTuneIndex(String base)
    {
        if(mBaseSharp)
        {
            mTune = Arrays.asList(maTuning).indexOf(base);
        }
        else
        {
            mTune = Arrays.asList(maTuning).indexOf(base);
        }
    }

    private void CalculateBaseIndex(String base)
    {
        if(mBaseSharp)
        {
            mBase = Arrays.asList(maScaleSharp).indexOf(base);
        }
        else
        {
            mBase = Arrays.asList(maScaleFlat).indexOf(base);
        }
        CalculateTuneIndex(base);
    }

    private void CalculateNoteIndex(String note)
    {
        if(mNoteSharp)
        {
            mNote = Arrays.asList(maScaleSharp).indexOf(note);
        }
        else
        {
            mNote = Arrays.asList(maScaleFlat).indexOf(note);
        }
    }

    public int getFret()
    {
        int fret;
        if(mBase <= mNote)
        {
            fret = mNote - mBase;
        }
        else
        {
            fret = (maScaleSharp.length - mBase) + mNote;
        }
        return fret;
    }

    public int getString()
    {
        return mTune + 1;
    }

    public String getNextInScale(String note)
    {
        int idx;
        String[] tmpArray;
        if(note.contains("b"))
        {
            tmpArray = maScaleFlat;
        }
        else
        {
            tmpArray = maScaleSharp;
        }
        idx = Arrays.asList(tmpArray).indexOf(note);
        if(idx == tmpArray.length - 1)
        {
            idx = 0;
        }
        else
        {
            idx++;
        }
        return tmpArray[idx];
    }

    public String[] getTuning()
    {
        return maTuning;
    }

    public void setTuning(String[] tuning) throws StringIndexOutOfBoundsException
    {
        if(tuning.length != 4)
        {
            throw new StringIndexOutOfBoundsException("The tuning array must be have a length of 4");
        }
        maTuning = tuning;
    }

    /**
     * Creates a fret matrix according to the given string tune.
     * @param strings can be any tpye of string numbers and tunings (use tScale type)
     * @return The result is a notes x strings matrix (e.g. Ukulele: result[12][4])
     */
    public static int[][] createTuneMatrix(int strings[])
    {
        int result[][] = new int[12][strings.length];
        for(int st = 0; st < 12; st++)
        {
            for(int sc = 0; sc < strings.length; sc++)
            {
                result[st][sc] = strings[sc]++;
                if(result[st][sc] >= 12)
                {
                    result[st][sc] = 0;
                    strings[sc] = 1;
                }
            }
        }
        return result;
    }

    @Override
    public String toString()
    {
        String ret;
        if (mNoteSharp)
        {
            ret = maScaleSharp[mNote];
        }
        else
        {
            ret = maScaleFlat[mNote];
        }
        return ret;
    }
}
