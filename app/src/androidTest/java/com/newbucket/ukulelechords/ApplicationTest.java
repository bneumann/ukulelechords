package com.newbucket.ukulelechords;

import android.os.Environment;
import android.test.AndroidTestCase;
import android.util.Log;

import com.newbucket.musicxml.Note;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends AndroidTestCase{
    private static final String TAG = "TestRunner";

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testXmlLoad()
    {
        File sdcard = Environment.getExternalStorageDirectory();

        //Get the text file
        File file = new File(sdcard,"helloworld.xml");
        try
        {
            InputStream is = new FileInputStream(file.getPath());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(is));
        }catch(Exception exp)
        {
            Log.e(TAG, exp.getMessage());
        }
    }

//    public void testNotesByName(){
//        Note n  = new Note("C", "G");
//        assertEquals(5, n.getFret());
//
//        n  = new Note("D", "C");
//        assertEquals(2, n.getFret());
//
//        n  = new Note("G", "C");
//        assertEquals(7, n.getFret());
//    }
//
//    public void testNotesByFret(){
//        Note n  = new Note("C", 2);
//        assertEquals(2, n.getFret());
//        assertEquals(3, n.getString());
//
//        n  = new Note("G", 2);
//        assertEquals(2, n.getFret());
//        assertEquals(4, n.getString());
//    }
//
//    public void testChordFinder()
//    {
//        String[] aChordIn = new String[]{"C", "G", "E"};
//        ArrayList<String[]> ret = TestUtils.testAutomaticChords(aChordIn);
//        aChordIn = ret.get(0);
//        String[] aChordOut = ret.get(1);
//        for(int i = 0; i < aChordIn.length; i++)
//        {
//            assertEquals(aChordIn[i], aChordOut[i]);
//        }
//
//        aChordIn = new String[]{"C#", "F", "G#"};
//        ret = TestUtils.testAutomaticChords(aChordIn);
//        aChordIn = ret.get(0);
//        aChordOut = ret.get(1);
//        for(int i = 0; i < aChordIn.length; i++)
//        {
//            assertEquals(aChordIn[i], aChordOut[i]);
//        }
//
//        aChordIn = new String[]{"Eb", "G", "Bb"};
//        ret = TestUtils.testAutomaticChords(aChordIn);
//        aChordIn = ret.get(0);
//        aChordOut = ret.get(1);
//        for(int i = 0; i < aChordIn.length; i++)
//        {
//            assertEquals(aChordIn[i], aChordOut[i]);
//        }
//    }
//
//    public void testChordLib()
//    {
//        ChordLib cl = new ChordLib();
//        assertEquals(0, cl.getChord("C").getNotes().get(0).getFret());
//    }
//
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

