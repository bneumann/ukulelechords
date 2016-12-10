package com.newbucket.musiclib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class hold a list of notes inside a chord
 * @author Benjamin Giesinger
 */
public class Chord extends ArrayList<Note>
{

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
        this.addAll(getChordParts(name).GetNotes());
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
        private static Map<String, Base> mLookup;
        static {
            Map<String, Base> aMap = new HashMap<>();
            aMap.put("M", Major);
            aMap.put("major", Major);
            aMap.put("m", Minor);
            aMap.put("minor", Minor);
            aMap.put("sus4", Sus4);
            aMap.put("sus2", Sus2);
            aMap.put("dim", Dim);
            aMap.put("aug", Aug);
            mLookup = Collections.unmodifiableMap(aMap);
        }

        Base(int first, int second, int third)
        {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public static Base ParseString(String name) {
            name = name.toLowerCase();
            Base ret = mLookup.get(name);
            return ret == null ? Base.Major : ret;
        }
    }

    public enum Modifier {
        None(0),
        Sixth(9),
        Seven(10),
        Major(11),
        Nine(2); // Or 13 halftones


        private static Map<String, Modifier> mLookup;
        static {
            Map<String, Modifier> aMap = new HashMap<>();
            aMap.put("", None);
            aMap.put("Major", None);
            aMap.put("6", Sixth);
            aMap.put("6th", Sixth);
            aMap.put("7", Seven);
            aMap.put("7th", Seven);
            aMap.put("maj7", Major);
            aMap.put("Major7", Major);
            aMap.put("9", Nine);
            aMap.put("9th", Nine);
            mLookup = Collections.unmodifiableMap(aMap);
        }

        private int mod;
        Modifier(int mod){
            this.mod = mod;
        }

        public static Modifier ParseString(String name) {
            name = name.replaceAll("\\s+", "");
            Modifier ret = mLookup.get(name);
            return ret == null ? Modifier.None : ret;
        }
    }

    private Chord getChordParts(String chordname) {
        final String regex = "([A-G][b#]?)([m|sus[24]|aug|dim]*)([\\d|maj]?)";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(chordname);

        Note tNote;
        Base tChord;
        Modifier tMod;

        if (matcher.matches()) {
            tNote = new Note(Scale.ParseString(matcher.group(1)));
            tChord = Base.ParseString(matcher.group(2));
            tMod = Modifier.ParseString(matcher.group(3));
        } else {
            throw new NullPointerException("Chord has not been found or could not be retrieved from the given string");
        }

        return new Chord(tChord, tNote, tMod);
    }
}
