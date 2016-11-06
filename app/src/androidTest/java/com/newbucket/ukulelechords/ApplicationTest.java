package com.newbucket.ukulelechords;

import android.test.AndroidTestCase;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

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
    }

    public void testChordFinder()
    {
        Chord c = new Chord(Chord.Base.Major, Chord.Modifier.Seven);
        Tuning t = new Tuning(Tuning.StandardTypes.Concert);

        ChordFinder cf = new ChordFinder(t, c);
        assertEquals("G fret is 0", 0, cf.GetFretValues()[0]);
        assertEquals("C fret is 0", 0, cf.GetFretValues()[1]);
        assertEquals("E fret is 0", 0, cf.GetFretValues()[2]);
        assertEquals("A fret is 2", 2, cf.GetFretValues()[3]);
    }
//
//    public void testReturnNoteString()
//    {
//        Note n  = new Note("G", "C");
//        assertEquals("G", n.toString());
//    }
//
//    public void testNextInScale()
//    {
//        System.out.println("Testing the next in scale function");
//        Note tmp = new Note();
//        String res = tmp.getNextInScale("C");
//        assertEquals("Checking for sharp flat", "C#", res);
//
//        res = tmp.getNextInScale("C#");
//        assertEquals("D", res);
//
//        res = tmp.getNextInScale("Db");
//        assertEquals("Checking for flat scale", "D", res);
//
//        res = tmp.getNextInScale("B");
//        assertEquals("Checking for overflow", "C", res);
//    }
}

