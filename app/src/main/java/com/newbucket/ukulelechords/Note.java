package com.newbucket.ukulelechords;

import java.lang.reflect.Array;
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
    private String[] maTuneing= {"A", "E", "C", "G"};

    private boolean mBaseSharp = false;
    private boolean mNoteSharp = false;

    public Note(String note)
    {
        if(note.contains("#"))
        {
            mBaseSharp = true;
            mNoteSharp = true;
        }
        CaluclateBaseIndex(note);
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
        CaluclateBaseIndex(base);
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
        CaluclateBaseIndex(str);
        mNote = mBase + fret;
    }

    private void CaluclateTuneIndex(String base)
    {
        if(mBaseSharp)
        {
            mTune = Arrays.asList(maTuneing).indexOf(base);
        }
        else
        {
            mTune = Arrays.asList(maTuneing).indexOf(base);
        }
    }

    private void CaluclateBaseIndex(String base)
    {
        if(mBaseSharp)
        {
            mBase = Arrays.asList(maScaleSharp).indexOf(base);
        }
        else
        {
            mBase = Arrays.asList(maScaleFlat).indexOf(base);
        }
        CaluclateTuneIndex(base);
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
}
