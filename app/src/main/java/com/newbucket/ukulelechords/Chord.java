package com.newbucket.ukulelechords;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by benni on 16.10.2015.
 */
public class Chord
{
    private ArrayList<Note> mNotes = new ArrayList<>();

    public Chord(Base chord)
    {
        mNotes.add(new Note(chord.first));
        mNotes.add(new Note(chord.second));
        mNotes.add(new Note(chord.third));
    }


    public Chord(Base chord, Note rootNote)
    {
        this(chord);
        transpose(rootNote);
    }

    public Chord(Base chord, Modifier mod)
    {
        this(chord);
        mNotes.add(new Note(mod.mod));
    }

    public Chord transpose(Note rootNote)
    {
        this.transpose(rootNote.GetNote());
        return this;
    }

    public Chord transpose(int value)
    {
        for(Note n : mNotes) {
            n.transpose(value);
        }
        return this;
    }

    public int[] GetNoteValues() {
        int[] res = new int[mNotes.size()];
        for(int i = 0; i < res.length; i++)
        {
            res[i] = mNotes.get(i).GetNote();
        }
        return res;
    }

    public ArrayList<Note> GetNotes()
    {
        return mNotes;
    }

    public Chord AddNote(Note n)
    {
        mNotes.add(n);
        return this;
    }

    public Chord AddModifier(Modifier mod){
        Note n = new Note(mNotes.get(0).GetNote());
        n.transpose(mod.mod);
        mNotes.add(n);
        return this;
    }

    public void addNotes(int firstString, int secondString, int thirdString, int fourthString) {
//        addNote(new Note("A", firstString));
//        addNote(new Note("E", secondString));
//        addNote(new Note("C", thirdString));
//        addNote(new Note("G", fourthString));
    }


    //FIXME: Works only with sharp scale at the moment
    public Chord(String[] notes)
    {
//        // str: "C", "G", "E"
//        Note tmp = new Note();
//        String[] tuning = null; //tmp.getTuning();
//        String[] base = tuning.clone();
//        // tuning: "G", "C", "E", "A"
//        int i = 0;
//        int foundFrets = 0;
//        while(i < 20)
//        {
//            for (int t = 0; t < tuning.length; t++)
//            {
//                if(foundFrets == 0xF)
//                {
//                    return;
//                }
//                // Check only unfound notes
//                if((foundFrets & (1 << t)) == 0)
//                {
//                    int idx = Arrays.asList(notes).indexOf(tuning[t]);
//                    if(idx >= 0)
//                    {
//                        addNote(new Note(notes[idx], base[t]));
//                        foundFrets |= (1 << t);
//                    }
//                }
//                tuning[t] = tmp.getNextInScale(tuning[t]);
//            }
//            i++;
//        }
    }


    public enum Base {
        Major(0, 4, 7),
        Minor(0, 3, 7),
        Sus4(0, 5, 7),
        Sus2(0, 2, 7),
        Dim(0, 3, 6),
        Aug(0, 4, 8);

        private int first, second, third;
        Base(int first, int second, int third)
        {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }

    public enum Modifier {
        Sixth(9),
        Seven(10),
        Major(11);

        private int mod;
        Modifier(int mod){
            this.mod = mod;
        }
    }
}
