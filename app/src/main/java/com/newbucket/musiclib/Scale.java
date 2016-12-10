package com.newbucket.musiclib;

/**
 * This class holds the data about the scale used in the instrument.
 */

public class Scale
{
    private static String[][] maScale= {
            {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"},
            {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"}
    };

    /**
     * The length of the scale returns the count of all notes in this scale
     */
    public static int length = maScale[0].length;
    /**
     * The maximum value is the maximum accessible value in the scale
     */
    public static int Max = maScale[0].length - 1;
    /**
     * The minimum value is always 0
     */
    public static int Min = 0;

    /**
     * Parse a string name of a note to it's scale integer value.
     * @param notename string of a note e.g. C, C#, B, Bb, etc.
     * @return The integer representation according to the used scale.
     */
    public static int ParseString(String notename)
    {
        int res = -1;
        for(int i = 0; i < Scale.length; i++) {
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

    /**
     * This function returns the string representation of the current step in the scale. e.g. 0 -> C
     * @param value An integer value between Min and Max
     * @param flat Option to get the flat or sharp representation
     * @return A string that represents the scale step
     */
    public static String ParseInteger(int value, boolean flat)
    {
        if(value < Scale.Min || value > Scale.Max) {
            throw new IllegalArgumentException(value + " out of bounds of scale. Which is between " + Scale.Min + " and " + Scale.Max);
        }

        String res;
        if (flat)
        {
            res = maScale[1][value];
        }
        else
        {
            res = maScale[0][value];
        }

        return res;
    }
}
