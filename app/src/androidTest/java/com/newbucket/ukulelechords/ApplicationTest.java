package com.newbucket.ukulelechords;

import android.test.AndroidTestCase;

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
}