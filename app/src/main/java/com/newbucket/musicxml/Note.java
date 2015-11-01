package com.newbucket.musicxml;

import org.w3c.dom.Element;

/**
 * Created by benni on 16.10.2015.
 */
public class Note
{
    private Element mNode;
    private Pitch mPitch;
    private int mDuration;
    private String mType;
    private boolean mIsInChord = false;

    public Note(Element xNode)
    {
        mNode = xNode;
        mPitch = new Pitch((Element)xNode.getElementsByTagName("pitch").item(0));
        mDuration = Integer.getInteger(xNode.getElementsByTagName("duration").item(0).getTextContent());
        mType = xNode.getElementsByTagName("type").item(0).getTextContent();
        if(xNode.getElementsByTagName("chord") != null)
        {
            mIsInChord = true;
        }
    }
}
