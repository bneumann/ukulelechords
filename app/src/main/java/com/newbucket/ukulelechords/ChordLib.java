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
        addChord("C7", new Chord(1, 0, 0, 0));
        addChord("Cm", new Chord(3, 3, 3, 0));
        addChord("Cm7", new Chord(3, 3, 3, 3));
        addChord("Cdim", new Chord(3, 2, 3, 2));
        addChord("Caug", new Chord(3, 0, 0, 1));
        addChord("C6", new Chord(0, 0, 0, 0));
        addChord("Cmaj7", new Chord(2, 0, 0, 0));
        addChord("C9", new Chord(1, 0, 2, 0));

        addChord("C#", new Chord(4, 1, 1, 1));
        addChord("C#7", new Chord(2, 1, 1, 1));
        addChord("C#m", new Chord(4, 4, 4, 1));
        addChord("C#m7", new Chord(2, 0, 1, 1));
        addChord("C#dim", new Chord(1, 0, 1, 0));
        addChord("C#aug", new Chord(0, 1, 1, 2));
        addChord("C#6", new Chord(1, 1, 1, 1));
        addChord("C#maj7", new Chord(2, 1, 1, 1));
        addChord("C#9", new Chord(2, 1, 3, 1));

        addChord("Db", new Chord(4, 1, 1, 1));
        addChord("Db7", new Chord(2, 1, 1, 1));
        addChord("Dbm", new Chord(4, 4, 4, 1));
        addChord("Dbm7", new Chord(2, 0, 1, 1));
        addChord("Dbdim", new Chord(1, 0, 1, 0));
        addChord("Dbaug", new Chord(0, 1, 1, 2));
        addChord("Db6", new Chord(1, 1, 1, 1));
        addChord("Dbmaj7", new Chord(2, 1, 1, 1));
        addChord("Db9", new Chord(2, 1, 3, 1));

        addChord("D", new Chord(5, 2, 2, 2));
        addChord("D7", new Chord(3, 2, 2, 2));
        addChord("Dm", new Chord(0, 1, 2, 2));
        addChord("Dm7", new Chord(3, 1, 2, 2));
        addChord("Ddim", new Chord(0, 1, 2, 1));
        addChord("Daug", new Chord(1, 2, 2, 3));
        addChord("D6", new Chord(2, 2, 2, 2));
        addChord("Dmaj7", new Chord(4, 2, 2, 2));
        addChord("D9", new Chord(3, 2, 4, 2));

        addChord("D#", new Chord(1, 3, 3, 0));
        addChord("D#7", new Chord(4, 3, 3, 3));
        addChord("D#m", new Chord(1, 2, 3, 3));
        addChord("D#m7", new Chord(4, 2, 3, 3));
        addChord("D#dim", new Chord(0, 2, 3, 2));
        addChord("D#aug", new Chord(2, 3, 3, 0));
        addChord("D#6", new Chord(3, 3, 3, 3));
        addChord("D#maj7", new Chord(5, 3, 3, 3));
        addChord("D#9", new Chord(1, 1, 1, 0));

        addChord("Eb", new Chord(1, 3, 3, 3));
        addChord("Eb7", new Chord(4, 3, 3, 3));
        addChord("Ebm", new Chord(1, 2, 3, 3));
        addChord("Ebm7", new Chord(4, 2, 3, 3));
        addChord("Ebdim", new Chord(0, 2, 3, 2));
        addChord("Ebaug", new Chord(2, 3, 3, 0));
        addChord("Eb6", new Chord(3, 3, 3, 3));
        addChord("Ebmaj7", new Chord(5, 3, 3, 3));
        addChord("Eb9", new Chord(1, 1, 1, 0));

        addChord("E", new Chord(2, 4, 0, 1));
        addChord("E7", new Chord(2, 0, 2, 1));
        addChord("Em", new Chord(2, 3, 4, 0));
        addChord("Em7", new Chord(2, 0, 2, 0));
        addChord("Edim", new Chord(1, 0, 1, 0));
        addChord("Eaug", new Chord(3, 0, 0, 1));
        addChord("E6", new Chord(4, 4, 4, 4));
        addChord("Emaj7", new Chord(2, 0, 3, 1));
        addChord("E9", new Chord(2, 2, 2, 1));

        // --> F
        addChord("E#", new Chord(0, 0, 1, 3));
        addChord("E#7", new Chord(3, 1, 3, 2));
        addChord("E#m", new Chord(3, 1, 0, 1));
        addChord("E#m7", new Chord(3, 1, 3, 1));
        addChord("E#dim", new Chord(2, 1, 2, 1));
        addChord("E#aug", new Chord(0, 1, 1, 2));
        addChord("E#6", new Chord(3, 1, 2, 2));
        addChord("E#maj7", new Chord(3, 1, 4, 2));
        addChord("E#9", new Chord(3, 3, 3, 2));

        //--> E
        addChord("Fb", new Chord(2, 4, 4, 4));
        addChord("Fb7", new Chord(2, 0, 2, 1));
        addChord("Fbm", new Chord(2, 3, 4, 0));
        addChord("Fbm7", new Chord(2, 0, 2, 0));
        addChord("Fbdim", new Chord(1, 0, 1, 0));
        addChord("Fbaug", new Chord(3, 0, 0, 1));
        addChord("Fb6", new Chord(4, 4, 4, 4));
        addChord("Fbmaj7", new Chord(2, 0, 3, 1));
        addChord("Fb9", new Chord(2, 2, 2, 1));

        addChord("F", new Chord(0, 0, 1, 3));
        addChord("F7", new Chord(3, 1, 3, 2));
        addChord("Fm", new Chord(3, 1, 0, 1));
        addChord("Fm7", new Chord(3, 1, 3, 1));
        addChord("Fdim", new Chord(2, 1, 2, 1));
        addChord("Faug", new Chord(0, 1, 1, 2));
        addChord("F6", new Chord(3, 1, 2, 2));
        addChord("Fmaj7", new Chord(3, 1, 4, 2));
        addChord("F9", new Chord(3, 3, 3, 2));

        addChord("F#", new Chord(1, 2, 1, 3));
        addChord("F#7", new Chord(4, 2, 4, 3));
        addChord("F#m", new Chord(0, 2, 1, 2));
        addChord("F#m7", new Chord(4, 2, 4, 2));
        addChord("F#dim", new Chord(4, 3, 4, 3));
        addChord("F#aug", new Chord(1, 2, 2, 3));
        addChord("F#6", new Chord(4, 2, 3, 3));
        addChord("F#maj7", new Chord(4, 2, 5, 2));
        addChord("F#9", new Chord(1, 0, 1, 1));

        addChord("Gb", new Chord(1, 2, 1, 3));
        addChord("Gb7", new Chord(4, 2, 4, 3));
        addChord("Gbm", new Chord(0, 2, 1, 2));
        addChord("Gbm7", new Chord(4, 2, 4, 2));
        addChord("Gbdim", new Chord(4, 3, 4, 3));
        addChord("Gbaug", new Chord(1, 2, 2, 3));
        addChord("Gb6", new Chord(4, 2, 3, 3));
        addChord("Gbmaj7", new Chord(4, 2, 5, 2));
        addChord("Gb9", new Chord(1, 0, 1, 1));

        addChord("G", new Chord(2, 3, 2, 0));
        addChord("G7", new Chord(2, 1, 2, 0));
        addChord("Gm", new Chord(1, 3, 2, 0));
        addChord("Gm7", new Chord(1, 2, 2, 0));
        addChord("Gdim", new Chord(1, 3, 1, 0));
        addChord("Gaug", new Chord(2, 3, 3, 0));
        addChord("G6", new Chord(2, 0, 2, 0));
        addChord("Gmaj7", new Chord(2, 2, 2, 0));
        addChord("G9", new Chord(1, 0, 1, 1));

        addChord("G#", new Chord(3, 4, 3, 1));
        addChord("G#7", new Chord(4, 3, 4, 1));
        addChord("G#m", new Chord(2, 4, 3, 4));
        addChord("G#m7", new Chord(2, 2, 3, 1));
        addChord("G#dim", new Chord(2, 1, 2, 1));
        addChord("G#aug", new Chord(3, 0, 0, 1));
        addChord("G#6", new Chord(3, 1, 3, 1));
        addChord("G#maj7", new Chord(3, 3, 3, 1));
        addChord("G#9", new Chord(2, 1, 2, 2));

        addChord("Ab", new Chord(3, 4, 3, 5));
        addChord("G#7", new Chord(4, 3, 4, 1));
        addChord("G#m", new Chord(2, 4, 3, 4));
        addChord("G#m7", new Chord(2, 2, 3, 1));
        addChord("G#dim", new Chord(2, 1, 2, 1));
        addChord("G#aug", new Chord(3, 0, 0, 1));
        addChord("G#6", new Chord(3, 1, 3, 1));
        addChord("G#maj7", new Chord(3, 3, 3, 1));
        addChord("G#9", new Chord(2, 1, 2, 2));
        
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
