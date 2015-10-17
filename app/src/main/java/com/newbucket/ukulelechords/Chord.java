package com.newbucket.ukulelechords;

import java.util.ArrayList;

/**
 * Created by benni on 16.10.2015.
 */
public class Chord {
    private ArrayList<Note> mNotes;

    public Chord()
    {
        mNotes = new ArrayList<>();
    }

    public ArrayList<Note> getNotes()
    {
        return mNotes;
    }

    public void addNote(Note n)
    {
        mNotes.add(n);
    }

    public void clear()
    {
        mNotes.clear();
    }
}
