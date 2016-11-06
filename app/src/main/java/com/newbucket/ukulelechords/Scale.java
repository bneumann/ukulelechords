package com.newbucket.ukulelechords;

/**
 * Created by benni on 06.11.2016.
 */

public class Scale
{
    private static String[][] maScale= {
            {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"},
            {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"}
    };

    public static int length = maScale[0].length;
    public static int Max = maScale[0].length - 1;
    public static int Min = 0;

    /**
     * Parse a string name of a note to it's scale integer value.
     * @param notename string of a note e.g. C, C#, B, Bb, etc.
     * @return The integer representation according to the used scale.
     */
    public static int ParseString(String notename)
    {
        int res = -1;
        for(int i = 0; i < Scale.Max; i++) {
            if (maScale[0][i].equals(notename) || maScale[1][i].equals(notename)){
                res = i;
                break;
            }
        }
        if(res == -1)
        {
            throw new IllegalArgumentException(notename + " not found in scale");
        }
        return res;
    }

    public static String ParseInteger(int value)  {
        if(value < Scale.Min || value > Scale.Max) {
            throw new IllegalArgumentException(value + " out of bounds of scale. Which is between " + Scale.Min + " and " + Scale.Max);
        }
        return maScale[0][value];
    }
}
