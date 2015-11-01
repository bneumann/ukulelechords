package com.newbucket.musicxml;

import org.w3c.dom.Element;

/**
 * Created by benni on 29.10.2015.
 */
public class Pitch
{
    private Element mNode;
    private String mStep;
    private int mAlter;
    private int mOctave;


    public Pitch(Element xNode)
    {
        mNode = xNode;
        mStep = xNode.getElementsByTagName("step").item(0).getTextContent();
        mAlter = Integer.getInteger(xNode.getElementsByTagName("alter").item(0).getTextContent());
        mOctave = Integer.getInteger(xNode.getElementsByTagName("octave").item(0).getTextContent());
    }

    public int getAlter()
    {
        return mAlter;
    }

    public void setAlter(int mAlter)
    {
        this.mAlter = mAlter;
    }

    public int getOctave()
    {
        return mOctave;
    }

    public void setOctave(int mOctave)
    {
        this.mOctave = mOctave;
    }

    public String getStep()
    {
        return mStep;
    }

    public void setStep(String mStep)
    {
        this.mStep = mStep;
    }
}
