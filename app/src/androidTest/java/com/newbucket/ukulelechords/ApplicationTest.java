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

    public void testNotesByName(){
        Note n  = new Note("C", "G");
        assertEquals(5, n.getFret());

        n  = new Note("D", "C");
        assertEquals(2, n.getFret());

        n  = new Note("G", "C");
        assertEquals(7, n.getFret());
    }

    public void testNotesByFret(){
        Note n  = new Note("C", 2);
        assertEquals(2, n.getFret());
        assertEquals(3, n.getString());

        n  = new Note("G", 2);
        assertEquals(2, n.getFret());
        assertEquals(4, n.getString());
    }

    public void testChordFinder()
    {
        String[] aChordIn = new String[]{"C", "G", "E"};
        ArrayList<String[]> ret = TestUtils.testAutomaticChords(aChordIn);
        aChordIn = ret.get(0);
        String[] aChordOut = ret.get(1);
        for(int i = 0; i < aChordIn.length; i++)
        {
            assertEquals(aChordIn[i], aChordOut[i]);
        }

        aChordIn = new String[]{"C#", "F", "G#"};
        ret = TestUtils.testAutomaticChords(aChordIn);
        aChordIn = ret.get(0);
        aChordOut = ret.get(1);
        for(int i = 0; i < aChordIn.length; i++)
        {
            assertEquals(aChordIn[i], aChordOut[i]);
        }

        aChordIn = new String[]{"Eb", "G", "Bb"};
        ret = TestUtils.testAutomaticChords(aChordIn);
        aChordIn = ret.get(0);
        aChordOut = ret.get(1);
        for(int i = 0; i < aChordIn.length; i++)
        {
            assertEquals(aChordIn[i], aChordOut[i]);
        }
    }

    public void testReturnNoteString()
    {
        Note n  = new Note("G", "C");
        assertEquals("G", n.toString());
    }

    public void testNextInScale()
    {
        System.out.println("Testing the next in scale function");
        Note tmp = new Note();
        String res = tmp.getNextInScale("C");
        assertEquals("Checking for sharp flat", "C#", res);

        res = tmp.getNextInScale("C#");
        assertEquals("D", res);

        res = tmp.getNextInScale("Db");
        assertEquals("Checking for flat scale", "D", res);

        res = tmp.getNextInScale("B");
        assertEquals("Checking for overflow", "C", res);
    }
}

