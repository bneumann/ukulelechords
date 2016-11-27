package com.newbucket.musiclib;

/**
 * Created by benni on 16.10.2015.
 */
public class Note {

    public static final String CodeFlat = "\u266D";
    public static final String CodeSharp = "\u266F";

    private int mNote = 0;

    public Note()
    {
    }

    public Note(int value)
    {
        // Instead of plain assign we just transpose it to get the
        // scale right.
        transpose(value);
    }

    public Note(String note)
    {
        mNote = Scale.ParseString(note);
    }

    public int GetNote(){
        return mNote;
    }

    public String toString() {
        return Scale.ParseInteger(mNote);
    }

    public Note transpose(int value)
    {
        mNote += value;
        mNote = mNote % Scale.length;
        mNote = mNote < 0 ? mNote += Scale.length : mNote;
        return this;
    }
}
