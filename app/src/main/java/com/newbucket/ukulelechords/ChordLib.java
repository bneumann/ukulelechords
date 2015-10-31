package com.newbucket.ukulelechords;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by benni on 16.10.2015.
 */
public class ChordLib {
    private static final String TAG = "ChordLib";

    private HashMap<String, Chord> mChords;

    public ChordLib() {
        mChords = new HashMap<>();

        addChord("C", new Chord(3, 0, 0, 0));
        addChord("C#", new Chord(4, 1, 1, 1));
        addChord("Db", new Chord(4, 1, 1, 1));
        addChord("D", new Chord(5, 2, 2, 2));
        addChord("D#", new Chord(1, 3, 3, 3));
        addChord("Eb", new Chord(1, 3, 3, 3));
        addChord("E", new Chord(2, 4, 4, 4));
        addChord("E#", new Chord(0, 0, 1, 3));
        addChord("Fb", new Chord(2, 4, 4, 4));
        addChord("F", new Chord(0, 0, 1, 3));
        addChord("F#", new Chord(1, 2, 1, 3));
        addChord("Gb", new Chord(1, 2, 1, 3));
        addChord("G", new Chord(2, 3, 2, 0));
        addChord("G#", new Chord(3, 4, 3, 1));
        addChord("Ab", new Chord(3, 4, 3, 5));
        addChord("A", new Chord(0, 0, 1, 2));
        addChord("A#", new Chord(1, 1, 2, 3));
        addChord("Bb", new Chord(1, 1, 2, 3));
        addChord("B", new Chord(2, 2, 3, 4));
        addChord("Cb", new Chord(2, 2, 3, 4));
    }

    private void addChord(String key, Chord chord) {
        mChords.put(key, chord);
    }

    public Chord getChord(String key) {
        return mChords.get(key);
    }


}
