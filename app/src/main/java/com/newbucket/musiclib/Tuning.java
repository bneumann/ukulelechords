package com.newbucket.musiclib;

import java.util.ArrayList;

/**
 * Created by benni on 06.11.2016.
 */

public class Tuning extends ArrayList<Note>
{
    public Tuning() {
        this(StandardTypes.Concert);
    }

    public Tuning(StandardTypes type) {
        switch (type) {
            case Baritone:
                add(new Note(2));
                add(new Note(7));
                add(new Note(11));
                add(new Note(4));
                break;
            case Tenor:
            case Soprano:
            case Concert:
            default:
                add(new Note(7));
                add(new Note(0));
                add(new Note(4));
                add(new Note(9));
                break;
        }
    }


    public enum StandardTypes {
        Concert,
        Tenor,
        Baritone,
        Soprano,
    }
}
