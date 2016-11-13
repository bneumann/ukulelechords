package com.newbucket.ukulelechords;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by benni on 16.10.2015.
 */
public class Chord extends ArrayList<Note>
{
//    private ArrayList<Note> mNotes = new ArrayList<>();


    public Chord(Base chord)
    {
        add(new Note(chord.first));
        add(new Note(chord.second));
        add(new Note(chord.third));
    }


    public Chord(Base chord, Note rootNote)
    {
        this(chord);
        transpose(rootNote);
    }

    public Chord(Base chord, Modifier mod)
    {
        this(chord);
        add(new Note(mod.mod));
    }

    public Chord(Base chord, Note rootNote, Modifier mod)
    {
        this(chord, mod);
        transpose(rootNote);
    }

    /**
     * Get a chord by it's name.
     * @param name Name of t he Chord such as "C", Bb" or "Bbmaj7"
     */
    public Chord(String name) {
        this(Base.Major);

        final String regex = "([A-G][b#]?)([a-zA-Z2-9]+)";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(name);

        int transp = 0;

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                if( i == 1 ) {
                    transp = Scale.ParseString(matcher.group(i));
                    this.transpose(transp);
                }
                if( i == 2 )
                {
                    switch(matcher.group(i)) {
                        case "7":
                            add(new Note(Modifier.Seven.mod).transpose(transp));
                            break;
                        case "m":
                            // FIXME: Initialize chords later
                            removeAll(this);
                            add(new Note(Base.Minor.first));
                            add(new Note(Base.Minor.second));
                            add(new Note(Base.Minor.third));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public Chord transpose(Note rootNote)
    {
        this.transpose(rootNote.GetNote());
        return this;
    }

    public Chord transpose(int value)
    {
        for(Note n : this) {
            n.transpose(value);
        }
        return this;
    }

    public int[] GetNoteValues() {
        int[] res = new int[this.size()];
        for(int i = 0; i < res.length; i++)
        {
            res[i] = this.get(i).GetNote();
        }
        return res;
    }

    public ArrayList<Note> GetNotes()
    {
        return this;
    }

    public Chord AddModifier(Modifier mod){
        Note n = new Note(this.get(0).GetNote());
        n.transpose(mod.mod);
        this.add(n);
        return this;
    }

    public void RemoveModifier() {
        while(this.size() > 3) {
            this.remove(this.size() - 1);
        }
    }

    @Override
    public boolean add(Note note)
    {
        boolean ret = false;
        if(!this.contains(note)) {
            ret = super.add(note);
        }
        return ret;
    }

    @Override
    public boolean contains(Object o)
    {
        boolean ret = false;
        if(!(o instanceof Note)) {
            return false;
        }
        for (Note n : this)
        {
            if(n.GetNote() == ((Note)o).GetNote()){
                ret = true;
                break;
            }
        }
        return ret;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Note n : this)
        {
            sb.append(n.toString());
            if(this.indexOf(n) != this.size() - 1)
            {
                sb.append(", ");
            }
        }
        return sb.toString();
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
        None(0),
        Sixth(9),
        Seven(10),
        Major(11),
        Nine(2); // Or 13 halftones

        private int mod;
        Modifier(int mod){
            this.mod = mod;
        }
    }
}
