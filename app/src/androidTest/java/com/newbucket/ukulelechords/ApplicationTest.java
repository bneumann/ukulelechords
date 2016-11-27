package com.newbucket.ukulelechords;
import android.test.AndroidTestCase;

import com.newbucket.musiclib.Chord;
import com.newbucket.musiclib.ChordFinder;
import com.newbucket.musiclib.Note;
import com.newbucket.musiclib.Tuning;

import java.util.Arrays;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends AndroidTestCase{
    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testNoteTranspose()
    {
        Note n = new Note();
        assertEquals("Note initialized as 0", 0, n.GetNote());

        n.transpose(5);
        assertEquals("Note transposed to 5", 5, n.GetNote());

        n.transpose(-4);
        assertEquals("Note transposed to 1", 1, n.GetNote());

        n.transpose(-4);
        assertEquals("Note transposed to 9", 9, n.GetNote());

        n.transpose(24);
        assertEquals("Note transposed two octaves up", 9, n.GetNote());

        n.transpose(-36);
        assertEquals("Note transposed three octaves down", 9, n.GetNote());
    }

    // NOTE: Legacy tests. Quite uneccesary because of new implementation
    public void testNotesByName(){
        Note n  = new Note("C");
        assertEquals(0, n.GetNote());

        n  = new Note("D");
        assertEquals(2, n.GetNote());

        n  = new Note("Bb");
        assertEquals(10, n.GetNote());
    }

    public void testChord(){
        Chord c = new Chord(Chord.Base.Major);

        assertEquals("Major chord created", 3, c.GetNoteValues().length);

        assertEquals("Root note 0", 0, c.GetNoteValues()[0]);
        assertEquals("Second note 4", 4, c.GetNoteValues()[1]);
        assertEquals("Third note 7", 7, c.GetNoteValues()[2]);

        c.transpose(6);

        assertEquals("Root note 6", 6, c.GetNoteValues()[0]);
        assertEquals("Second note 10", 10, c.GetNoteValues()[1]);
        assertEquals("Third note 1", 1, c.GetNoteValues()[2]);

        assertEquals("Root note is F#", "F#", c.GetNotes().get(0).toString());
        assertEquals("Root note is A#", "A#", c.GetNotes().get(1).toString());
        assertEquals("Root note is C#", "C#", c.GetNotes().get(2).toString());

        c.AddModifier(Chord.Modifier.Seven);
        assertEquals("Added the Seven modifier", 4, c.GetNoteValues().length);

        assertEquals("Seven note is 4", 4, c.GetNoteValues()[3]);
        assertEquals("Seven note is E", "E", c.GetNotes().get(3).toString());

        c.transpose(-7);

        assertEquals("Root note 11", 11, c.GetNoteValues()[0]);
        assertEquals("Second note 3", 3, c.GetNoteValues()[1]);
        assertEquals("Third note 6", 6, c.GetNoteValues()[2]);
    }

    public void testChordFinder()
    {
        Chord c = new Chord(Chord.Base.Major);
        Tuning t = new Tuning(Tuning.StandardTypes.Concert);

        ChordFinder cf = new ChordFinder(t, c);
        assertEquals("G fret is 0", 0, cf.GetFretValues()[0]);
        assertEquals("C fret is 0", 0, cf.GetFretValues()[1]);
        assertEquals("E fret is 0", 0, cf.GetFretValues()[2]);
        assertEquals("A fret is 3", 3, cf.GetFretValues()[3]);


        c.AddModifier(Chord.Modifier.Seven);
        cf.UpdateFretValues();
        assertEquals("G fret is 0", 0, cf.GetFretValues()[0]);
        assertEquals("C fret is 0", 0, cf.GetFretValues()[1]);
        assertEquals("E fret is 0", 0, cf.GetFretValues()[2]);
        assertEquals("A fret is 1", 1, cf.GetFretValues()[3]);

        c.transpose(7);
        c.RemoveModifier();
        cf.UpdateFretValues();
        assertEquals("G fret is 0", 0, cf.GetFretValues()[0]);
        assertEquals("C fret is 2", 2, cf.GetFretValues()[1]);
        assertEquals("E fret is 3", 3, cf.GetFretValues()[2]);
        assertEquals("A fret is 2", 2, cf.GetFretValues()[3]);
    }

    public void testCheckAllChords()
    {
        ChordTestLib cl = new ChordTestLib();
        Tuning t = new Tuning(Tuning.StandardTypes.Concert);

        for(String chordname : cl.getChordNames()) {
            Chord c = new Chord(chordname);
            ChordFinder cf = new ChordFinder(t, c);
            cf.UpdateFretValues();
            int[] testFrets = cl.get(chordname);
            int[] calcFrets = cf.GetFretValues();
            assertArrayEquals(testFrets, calcFrets);
        }
    }

    private void assertArrayEquals(int[] testin1, int[] testin2)
    {
        if(testin1.length != testin2.length) {
            failNotSame("Array size don't match!", testin1.length, testin2.length);
        }
        boolean fail = false;
        for(int i = 0; i < testin1.length; i++) {
            if(testin1[i] != testin2[i]) {
                fail = true;
                break;
            }
        }
        if(fail) {
            failNotEquals("Arrays don't match!", Arrays.toString(testin1), Arrays.toString(testin2));
        }
    }
}

