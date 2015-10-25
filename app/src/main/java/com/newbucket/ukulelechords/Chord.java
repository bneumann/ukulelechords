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

    public Chord(int[] notes)
    {
        // 12 x 4
        int matrix[][] = Note.createTuneMatrix(Note.TUNING_UKULELE);

    }

    public enum Harmonic
    {
        Major,
        Minor,
        Seven,
        MinorSeven,
        MajorSeven,
        Aug,
        Dim
    }

    public Chord(Note base, Harmonic harm)
    {
        int first = base.getFret();
        int second, third;
        int fourth = first;
        switch(harm)
        {
            case Major:
                second = first + 4;
                third = first + 7;
            break;
            case Minor:
                second = first + 3;
                third = first + 7;
                break;
            case Seven:
                second = first + 4;
                third = first + 7;
                fourth = first + 10;
                break;
            case MinorSeven:
                second = first + 3;
                third = first + 7;
                fourth = first + 10;
                break;
            case Aug:
                second = first + 4;
                third = first + 8;
                break;
        }
        addNote();
    }

}
