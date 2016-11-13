package com.newbucket.ukulelechords;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by benni on 12.11.2016.
 */

public class ChordTestLib extends ArrayList<ChordTestLib.TestEntry>
{
    public ChordTestLib()
    {
        add(new TestEntry("C", new int[]{3, 0, 0, 0}));
        add(new TestEntry("C7", new int[]{1, 0, 0, 0}));
        add(new TestEntry("Cm", new int[]{3, 3, 3, 0}));
        add(new TestEntry("Cm7", new int[]{3, 3, 3, 3}));
        add(new TestEntry("Cdim", new int[]{3, 2, 3, 2}));
        add(new TestEntry("Caug", new int[]{3, 0, 0, 1}));
        add(new TestEntry("C6", new int[]{0, 0, 0, 0}));
        add(new TestEntry("Cmaj7", new int[]{2, 0, 0, 0}));
        add(new TestEntry("C9", new int[]{1, 0, 2, 0}));

        add(new TestEntry("C#", new int[]{4, 1, 1, 1}));
        add(new TestEntry("C#7", new int[]{2, 1, 1, 1}));
        add(new TestEntry("C#m", new int[]{4, 4, 4, 1}));
        add(new TestEntry("C#m7", new int[]{2, 0, 1, 1}));
        add(new TestEntry("C#dim", new int[]{1, 0, 1, 0}));
        add(new TestEntry("C#aug", new int[]{0, 1, 1, 2}));
        add(new TestEntry("C#6", new int[]{1, 1, 1, 1}));
        add(new TestEntry("C#maj7", new int[]{2, 1, 1, 1}));
        add(new TestEntry("C#9", new int[]{2, 1, 3, 1}));

        add(new TestEntry("Db", new int[]{4, 1, 1, 1}));
        add(new TestEntry("Db7", new int[]{2, 1, 1, 1}));
        add(new TestEntry("Dbm", new int[]{4, 4, 4, 1}));
        add(new TestEntry("Dbm7", new int[]{2, 0, 1, 1}));
        add(new TestEntry("Dbdim", new int[]{1, 0, 1, 0}));
        add(new TestEntry("Dbaug", new int[]{0, 1, 1, 2}));
        add(new TestEntry("Db6", new int[]{1, 1, 1, 1}));
        add(new TestEntry("Dbmaj7", new int[]{2, 1, 1, 1}));
        add(new TestEntry("Db9", new int[]{2, 1, 3, 1}));

        add(new TestEntry("D", new int[]{5, 2, 2, 2}));
        add(new TestEntry("D7", new int[]{3, 2, 2, 2}));
        add(new TestEntry("Dm", new int[]{0, 1, 2, 2}));
        add(new TestEntry("Dm7", new int[]{3, 1, 2, 2}));
        add(new TestEntry("Ddim", new int[]{0, 1, 2, 1}));
        add(new TestEntry("Daug", new int[]{1, 2, 2, 3}));
        add(new TestEntry("D6", new int[]{2, 2, 2, 2}));
        add(new TestEntry("Dmaj7", new int[]{4, 2, 2, 2}));
        add(new TestEntry("D9", new int[]{3, 2, 4, 2}));

        add(new TestEntry("D#", new int[]{1, 3, 3, 0}));
        add(new TestEntry("D#7", new int[]{4, 3, 3, 3}));
        add(new TestEntry("D#m", new int[]{1, 2, 3, 3}));
        add(new TestEntry("D#m7", new int[]{4, 2, 3, 3}));
        add(new TestEntry("D#dim", new int[]{0, 2, 3, 2}));
        add(new TestEntry("D#aug", new int[]{2, 3, 3, 0}));
        add(new TestEntry("D#6", new int[]{3, 3, 3, 3}));
        add(new TestEntry("D#maj7", new int[]{5, 3, 3, 3}));
        add(new TestEntry("D#9", new int[]{1, 1, 1, 0}));

        add(new TestEntry("Eb", new int[]{1, 3, 3, 3}));
        add(new TestEntry("Eb7", new int[]{4, 3, 3, 3}));
        add(new TestEntry("Ebm", new int[]{1, 2, 3, 3}));
        add(new TestEntry("Ebm7", new int[]{4, 2, 3, 3}));
        add(new TestEntry("Ebdim", new int[]{0, 2, 3, 2}));
        add(new TestEntry("Ebaug", new int[]{2, 3, 3, 0}));
        add(new TestEntry("Eb6", new int[]{3, 3, 3, 3}));
        add(new TestEntry("Ebmaj7", new int[]{5, 3, 3, 3}));
        add(new TestEntry("Eb9", new int[]{1, 1, 1, 0}));

        add(new TestEntry("E", new int[]{2, 4, 0, 1}));
        add(new TestEntry("E7", new int[]{2, 0, 2, 1}));
        add(new TestEntry("Em", new int[]{2, 3, 4, 0}));
        add(new TestEntry("Em7", new int[]{2, 0, 2, 0}));
        add(new TestEntry("Edim", new int[]{1, 0, 1, 0}));
        add(new TestEntry("Eaug", new int[]{3, 0, 0, 1}));
        add(new TestEntry("E6", new int[]{4, 4, 4, 4}));
        add(new TestEntry("Emaj7", new int[]{2, 0, 3, 1}));
        add(new TestEntry("E9", new int[]{2, 2, 2, 1}));

        // --> F
        add(new TestEntry("E#", new int[]{0, 0, 1, 3}));
        add(new TestEntry("E#7", new int[]{3, 1, 3, 2}));
        add(new TestEntry("E#m", new int[]{3, 1, 0, 1}));
        add(new TestEntry("E#m7", new int[]{3, 1, 3, 1}));
        add(new TestEntry("E#dim", new int[]{2, 1, 2, 1}));
        add(new TestEntry("E#aug", new int[]{0, 1, 1, 2}));
        add(new TestEntry("E#6", new int[]{3, 1, 2, 2}));
        add(new TestEntry("E#maj7", new int[]{3, 1, 4, 2}));
        add(new TestEntry("E#9", new int[]{3, 3, 3, 2}));

        //--> E
        add(new TestEntry("Fb", new int[]{2, 4, 4, 4}));
        add(new TestEntry("Fb7", new int[]{2, 0, 2, 1}));
        add(new TestEntry("Fbm", new int[]{2, 3, 4, 0}));
        add(new TestEntry("Fbm7", new int[]{2, 0, 2, 0}));
        add(new TestEntry("Fbdim", new int[]{1, 0, 1, 0}));
        add(new TestEntry("Fbaug", new int[]{3, 0, 0, 1}));
        add(new TestEntry("Fb6", new int[]{4, 4, 4, 4}));
        add(new TestEntry("Fbmaj7", new int[]{2, 0, 3, 1}));
        add(new TestEntry("Fb9", new int[]{2, 2, 2, 1}));

        add(new TestEntry("F", new int[]{0, 0, 1, 3}));
        add(new TestEntry("F7", new int[]{3, 1, 3, 2}));
        add(new TestEntry("Fm", new int[]{3, 1, 0, 1}));
        add(new TestEntry("Fm7", new int[]{3, 1, 3, 1}));
        add(new TestEntry("Fdim", new int[]{2, 1, 2, 1}));
        add(new TestEntry("Faug", new int[]{0, 1, 1, 2}));
        add(new TestEntry("F6", new int[]{3, 1, 2, 2}));
        add(new TestEntry("Fmaj7", new int[]{3, 1, 4, 2}));
        add(new TestEntry("F9", new int[]{3, 3, 3, 2}));

        add(new TestEntry("F#", new int[]{1, 2, 1, 3}));
        add(new TestEntry("F#7", new int[]{4, 2, 4, 3}));
        add(new TestEntry("F#m", new int[]{0, 2, 1, 2}));
        add(new TestEntry("F#m7", new int[]{4, 2, 4, 2}));
        add(new TestEntry("F#dim", new int[]{4, 3, 4, 3}));
        add(new TestEntry("F#aug", new int[]{1, 2, 2, 3}));
        add(new TestEntry("F#6", new int[]{4, 2, 3, 3}));
        add(new TestEntry("F#maj7", new int[]{4, 2, 5, 2}));
        add(new TestEntry("F#9", new int[]{1, 0, 1, 1}));

        add(new TestEntry("Gb", new int[]{1, 2, 1, 3}));
        add(new TestEntry("Gb7", new int[]{4, 2, 4, 3}));
        add(new TestEntry("Gbm", new int[]{0, 2, 1, 2}));
        add(new TestEntry("Gbm7", new int[]{4, 2, 4, 2}));
        add(new TestEntry("Gbdim", new int[]{4, 3, 4, 3}));
        add(new TestEntry("Gbaug", new int[]{1, 2, 2, 3}));
        add(new TestEntry("Gb6", new int[]{4, 2, 3, 3}));
        add(new TestEntry("Gbmaj7", new int[]{4, 2, 5, 2}));
        add(new TestEntry("Gb9", new int[]{1, 0, 1, 1}));

        add(new TestEntry("G", new int[]{2, 3, 2, 0}));
        add(new TestEntry("G7", new int[]{2, 1, 2, 0}));
        add(new TestEntry("Gm", new int[]{1, 3, 2, 0}));
        add(new TestEntry("Gm7", new int[]{1, 2, 2, 0}));
        add(new TestEntry("Gdim", new int[]{1, 3, 1, 0}));
        add(new TestEntry("Gaug", new int[]{2, 3, 3, 0}));
        add(new TestEntry("G6", new int[]{2, 0, 2, 0}));
        add(new TestEntry("Gmaj7", new int[]{2, 2, 2, 0}));
        add(new TestEntry("G9", new int[]{1, 0, 1, 1}));

        add(new TestEntry("G#", new int[]{3, 4, 3, 1}));
        add(new TestEntry("G#7", new int[]{4, 3, 4, 1}));
        add(new TestEntry("G#m", new int[]{2, 4, 3, 4}));
        add(new TestEntry("G#m7", new int[]{2, 2, 3, 1}));
        add(new TestEntry("G#dim", new int[]{2, 1, 2, 1}));
        add(new TestEntry("G#aug", new int[]{3, 0, 0, 1}));
        add(new TestEntry("G#6", new int[]{3, 1, 3, 1}));
        add(new TestEntry("G#maj7", new int[]{3, 3, 3, 1}));
        add(new TestEntry("G#9", new int[]{2, 1, 2, 2}));

        add(new TestEntry("Ab", new int[]{3, 4, 3, 5}));
        add(new TestEntry("Ab7", new int[]{4, 3, 4, 1}));
        add(new TestEntry("Abm", new int[]{2, 4, 3, 4}));
        add(new TestEntry("Abm7", new int[]{2, 2, 3, 1}));
        add(new TestEntry("Abdim", new int[]{2, 1, 2, 1}));
        add(new TestEntry("Abaug", new int[]{3, 0, 0, 1}));
        add(new TestEntry("Ab6", new int[]{3, 1, 3, 1}));
        add(new TestEntry("Abmaj7", new int[]{3, 3, 3, 1}));
        add(new TestEntry("Ab9", new int[]{2, 1, 2, 2}));

        add(new TestEntry("A", new int[]{0, 0, 1, 2}));
        add(new TestEntry("A7", new int[]{0, 0, 1, 0}));
        add(new TestEntry("Am", new int[]{0, 0, 0, 2}));
        add(new TestEntry("Am7", new int[]{0, 0, 0, 0}));
        add(new TestEntry("Adim", new int[]{3, 2, 3, 2}));
        add(new TestEntry("Aaug", new int[]{4, 1, 1, 2}));
        add(new TestEntry("A6", new int[]{0, 2, 1, 2}));
        add(new TestEntry("Amaj7", new int[]{0, 0, 1, 1}));
        add(new TestEntry("A9", new int[]{2, 0, 1, 0}));

        add(new TestEntry("A#", new int[]{1, 1, 2, 3}));
        add(new TestEntry("A#7", new int[]{1, 1, 2, 1}));
        add(new TestEntry("A#m", new int[]{1, 1, 1, 3}));
        add(new TestEntry("A#m7", new int[]{1, 1, 1, 1}));
        add(new TestEntry("A#dim", new int[]{1, 0, 1, 3}));
        add(new TestEntry("A#aug", new int[]{1, 2, 2, 3}));
        add(new TestEntry("A#6", new int[]{1, 1, 2, 0}));
        add(new TestEntry("A#maj7", new int[]{0, 1, 2, 3}));
        add(new TestEntry("A#9", new int[]{3, 4, 2, 3}));

        add(new TestEntry("Bb", new int[]{1, 1, 2, 3}));
        add(new TestEntry("Bb7", new int[]{1, 1, 2, 1}));
        add(new TestEntry("Bbm", new int[]{1, 1, 1, 3}));
        add(new TestEntry("Bbm7", new int[]{1, 1, 1, 1}));
        add(new TestEntry("Bbdim", new int[]{1, 0, 1, 3}));
        add(new TestEntry("Bbaug", new int[]{1, 2, 2, 3}));
        add(new TestEntry("Bb6", new int[]{1, 1, 2, 0}));
        add(new TestEntry("Bbmaj7", new int[]{0, 1, 2, 3}));
        add(new TestEntry("Bb9", new int[]{3, 4, 2, 3}));

        add(new TestEntry("B", new int[]{2, 2, 3, 4}));
        add(new TestEntry("B7", new int[]{2, 2, 3, 2}));
        add(new TestEntry("Bm", new int[]{2, 2, 2, 4}));
        add(new TestEntry("Bm7", new int[]{2, 2, 2, 2}));
        add(new TestEntry("Bdim", new int[]{2, 1, 2, 4}));
        add(new TestEntry("Baug", new int[]{2, 3, 3, 4}));
        add(new TestEntry("B6", new int[]{2, 2, 3, 1}));
        add(new TestEntry("Bmaj7", new int[]{1, 2, 3, 4}));
        add(new TestEntry("B9", new int[]{4, 5, 3, 4}));

        add(new TestEntry("Cb", new int[]{2, 2, 3, 4}));
        add(new TestEntry("Cb7", new int[]{2, 2, 3, 2}));
        add(new TestEntry("Cbm", new int[]{2, 2, 2, 4}));
        add(new TestEntry("Cbm7", new int[]{2, 2, 2, 2}));
        add(new TestEntry("Cbdim", new int[]{2, 1, 2, 4}));
        add(new TestEntry("Cbaug", new int[]{2, 3, 3, 4}));
        add(new TestEntry("Cb6", new int[]{2, 2, 3, 1}));
        add(new TestEntry("Cbmaj7", new int[]{1, 2, 3, 4}));
        add(new TestEntry("Cb9", new int[]{4, 5, 3, 4}));

    }

    public int[] get(final String chordname)
    {
        TestEntry te = new TestEntry("T", new int[] {0, 0, 0, 0});
        for(TestEntry t : this) {
            if(t.ChordName.equals(chordname)) {
                te = t;
                break;
            }
        }
        return te.FretValues;
    }

    public ArrayList<String> getChordNames() {
        ArrayList<String> ret = new ArrayList<>();
        for(TestEntry t : this) {
            ret.add(t.ChordName);
        }
        return ret;
    }

    public class TestEntry
    {
        public String ChordName;
        public int[] FretValues;

        public TestEntry(String name, int[] values)
        {
            ChordName = name;
            FretValues = new int[values.length];
            for(int i = 0; i < values.length; i++) {
                FretValues[i] = values[values.length - i - 1];
            }
        }
    }
}
