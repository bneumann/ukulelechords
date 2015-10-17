package com.newbucket.ukulelechords;

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

    public Note()
    {
        mBaseSharp = true;
        mNoteSharp = true;
        mNote = 0;
        mBase = 0;
        mTune = 2; // Because 3rd string no fret is "C"
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
