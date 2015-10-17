package com.newbucket.ukulelechords;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by benni on 16.10.2015.
 */
public class ChordLib {
    private static final String TAG = "ChordLib";

    private HashMap<String, Chord> mChords;

    public ChordLib()
    {
        mChords = new HashMap<>();
        Chord chord = new Chord();

        chord.addNote(new Note("A", 3));
        addChord("C", chord);

        chord = new Chord();
        chord.addNote(new Note("A", 5));
        chord.addNote(new Note("E", 2));
        chord.addNote(new Note("C", 2));
        chord.addNote(new Note("G", 2));
        addChord("D", chord);

        chord = new Chord();
        chord.addNote(new Note("A", 2));
        chord.addNote(new Note("E", 4));
        chord.addNote(new Note("C", 4));
        chord.addNote(new Note("G", 4));
        addChord("E", chord);

        chord = new Chord();
        chord.addNote(new Note("E", 1));
        chord.addNote(new Note("G", 3));
        addChord("F", chord);

        chord = new Chord();
        chord.addNote(new Note("A", 2));
        chord.addNote(new Note("E", 3));
        chord.addNote(new Note("C", 2));
        addChord("G", chord);

        chord = new Chord();
        chord.addNote(new Note("A", 2));
        chord.addNote(new Note("E", 2));
        chord.addNote(new Note("C", 3));
        chord.addNote(new Note("G", 4));
        addChord("B", chord);
    }

    private void addChord(String key, Chord chord) {
        mChords.put(key, chord);
    }

    public Chord getChord(String key)
    {
        return mChords.get(key);
    }


}
