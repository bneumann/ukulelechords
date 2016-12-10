package com.newbucket.musiclib;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * This class combines a tuning and a chord to find the best fitting for the fingers
 * @author Benjamin Giesinger
 */

public class ChordFinder
{
    private int[] mFretValues, mNoteValues;
    private Chord mChord;
    private Tuning mTuning;
    private final static String TAG = "ChordFinder";

    public ChordFinder()
    {
        this(new Tuning(), new Chord(Chord.Base.Major));
    }

    public ChordFinder(Tuning tuning, Chord chord) {
        mTuning = tuning;
        mChord = chord;

        mFretValues = new int[mTuning.size()];
        mNoteValues = new int[mTuning.size()];
        Arrays.fill(mFretValues, 0);

        UpdateFretValues();
    }

    public void FindNewChord(Chord chord)
    {
        mChord = chord;
        UpdateFretValues();
    }

    public int[] GetFretValues()
    {
        return mFretValues;
    }

    public void UpdateFretValues() {
        ArrayList<Note> c = mChord.GetNotes();

        Matrix mNoteWeightMatrix = new Matrix(mTuning.size(), c.size());
        Matrix mNoteValueMatrix = new Matrix(mTuning.size(), c.size());
        Matrix tmp;
        for(int i = 0; i < mTuning.size(); i++) {
            for(int j = 0; j < c.size(); j++) {
                Note tmpNote = new Note(c.get(j).GetNote());
                mNoteValueMatrix.set(i, j, tmpNote.GetNote());
                int fretPosition = tmpNote.transpose(-mTuning.get(i).GetNote()).GetNote();
                mNoteWeightMatrix.set(i, j, fretPosition);
            }

            tmp = new Matrix(new Integer[][]{mNoteWeightMatrix.getColumn(i), mNoteValueMatrix.getColumn(i)});
            tmp.sortByRow(0);
            mFretValues[i] = tmp.getRow(0)[0];
            mNoteValues[i] = tmp.getRow(0)[1];
        }

        // If there are less unique notes then we have in our chord, we need to fix it
        if(distinctNumberOfItems(mNoteValues) < c.size()) {
            Log.d(TAG, "Shit");
//            tmp = new Matrix(new Integer[][]{mNoteWeightMatrix.getColumn(i), mNoteValueMatrix.getColumn(i)});
//            tmp.sortByRow(0);
        }
    }

    private class Matrix {
        private Integer[][] mMatrix;
        private int mRows, mCols;

        public Matrix(int rows, int cols) {
            mMatrix = new Integer[rows][cols];
            mRows = rows;
            mCols = cols;
            for (int i = 0; i < mRows; i++) {
                for (int j = 0; j < mCols; j++) {
                    mMatrix[i][j] = 0;
                }
            }
        }

        public Matrix(Integer[][] matrix) {
            this(matrix.length, matrix[0].length);
            for (int i = 0; i < mRows; i++) {
//                for (int j = 0; j < mCols; j++) {
//                    mMatrix[i][j] = matrix[i][j];
//                }
                mMatrix[i] = Arrays.copyOf(matrix[i], matrix[i].length);
            }
        }

        public Integer[] getColumn(int row) {
            return mMatrix[row];
        }

        public Integer[] getRow(int col) {
            Integer[] temp = new Integer[mRows];
            for (int i = 0; i < mRows; i++) {
                temp[i] = mMatrix[i][col];
            }
            return temp;
        }

        public int get(int row, int col) {
            return mMatrix[row][col];
        }

        public void set(int row, int col, int value) {
            mMatrix[row][col] = value;
        }

        public Matrix transpose() {
            Integer[][] temp = new Integer[mCols][mRows];
            for (int i = 0; i < mRows; i++) {
                for (int j = 0; j < mCols; j++) {
                    temp[j][i] = mMatrix[i][j];
                }
            }
            mMatrix = temp;
            int tmp = mRows;
            mRows = mCols;
            mCols = tmp;
            return this;
        }

        public Matrix sortByColumn(final int col) {
            Arrays.sort(mMatrix, new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] o1, Integer[] o2) {
                    return -Integer.compare(o2[col], o1[col]);
                }
            });
            return this;
        }

        public Matrix sortByRow(final int row) {
            this.transpose().sortByColumn(row).transpose();
            return this;
        }

        public int[] size() {
            return new int[]{mRows, mCols};
        }

        public int[][] toPrimitive() {
            int[][] temp = new int[mCols][mRows];
            for (int i = 0; i < mRows; i++) {
                for (int j = 0; j < mCols; j++) {
                    temp[i][j] = mMatrix[i][j];
                }
            }
            return temp;
        }
    }

    private int distinctNumberOfItems(int[] array) {
        if (array.length <= 1) {
            return array.length;
        }

        Set<Integer> set = new HashSet<>();
        for (int i : array) {
            set.add(i);
        }
        return set.size();
    }

}
