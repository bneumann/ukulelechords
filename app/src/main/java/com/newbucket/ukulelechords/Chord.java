package com.newbucket.ukulelechords;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by benni on 16.10.2015.
 */
public class Chord
{
    private ArrayList<Note> mNotes = new ArrayList<>();

    public Chord()
    {
    }

    public ArrayList<Note> getNotes()
    {
        return mNotes;
    }

    public void addNote(Note n)
    {
        mNotes.add(n);
    }

    //FIXME: Works only with sharp scale at the moment
    public Chord(String[] notes)
    {
        // str: "C", "G", "E"
        Note tmp = new Note();
        String[] tuning = tmp.getTuning();
        String[] base = tuning.clone();
        // tuning: "G", "C", "E", "A"
        int i = 0;
        int foundFrets = 0;
        while(i < 20)
        {
            for (int t = 0; t < tuning.length; t++)
            {
                if(foundFrets == 0xF)
                {
                    return;
                }
                // Check only unfound notes
                if((foundFrets & (1 << t)) == 0)
                {
                    int idx = Arrays.asList(notes).indexOf(tuning[t]);
                    if(idx >= 0)
                    {
                        addNote(new Note(notes[idx], base[t]));
                        foundFrets |= (1 << t);
                    }
                }
                tuning[t] = tmp.getNextInScale(tuning[t]);
            }
            i++;
        }
    }

}
