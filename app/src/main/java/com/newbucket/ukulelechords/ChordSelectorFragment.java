package com.newbucket.ukulelechords;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newbucket.musiclib.Chord;
import com.newbucket.musiclib.Note;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * This fragment shows either the selection for the chords or their harmonics.
 * @author Benjamin Giesinger
 */

public class ChordSelectorFragment extends Fragment
{
    private OnChordSelectListener mSelectListeners;
    private final String TAG = "ChordSelector";
    private String mChordName, mHarmName = "";

    private Chord.Base mHarmony = Chord.Base.Major;
    private Chord.Modifier mModifier = Chord.Modifier.None;
    private Chord mOurCurrentChord;

    private List<Button> mChordButtons, mHarmonyButtons;
    private Button mButtonUp;
    private Button mButtonDown;
    private Animation mAnimOpen, mAnimClose;
    private TextView mSelectedChordView;

    private boolean mChordsVisible, mHarmVisible;

    public interface OnChordSelectListener {
        void onChordSelected(Chord chord);
        void onUpPressed();
        void onDownPressed();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View chordSelectFragment = inflater.inflate(R.layout.chordselector_fragment, container, false);

        LinearLayout chordMap = (LinearLayout)chordSelectFragment.findViewById(R.id.chord_map);
        LinearLayout harmMap = (LinearLayout) chordSelectFragment.findViewById(R.id.harm_map);

        mSelectedChordView = (TextView) chordSelectFragment.findViewById(R.id.selected_chord_text);

        mButtonUp = (Button)chordSelectFragment.findViewById(R.id.button_up);
        mButtonDown = (Button)chordSelectFragment.findViewById(R.id.button_down);

        Button lButtonHarm = (Button) chordSelectFragment.findViewById(R.id.button_harm);
        Button lButtonChord = (Button) chordSelectFragment.findViewById(R.id.button_chord);

        mChordButtons = getAllButtons(chordMap);
        mHarmonyButtons = getAllButtons(harmMap);

        mAnimOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        mAnimClose = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);

        for (Button b : mChordButtons)
        {
            b.setOnClickListener(new OnChordButtonPress());
            b.setVisibility(View.INVISIBLE);
        }

        for (Button b : mHarmonyButtons)
        {
            b.setOnClickListener(new OnHarmonyButtonPress());
            b.setVisibility(View.INVISIBLE);
        }

        mChordsVisible = false;
        mHarmVisible = false;

        mButtonUp.setOnClickListener(new OnUpDownButtonPress());
        mButtonDown.setOnClickListener(new OnUpDownButtonPress());

        lButtonChord.setOnClickListener(new OnChordMenuButtonPress());
        lButtonHarm.setOnClickListener(new OnHarmMenuButtonPress());

        if (mOurCurrentChord == null)
        {
            mChordButtons.get(0).callOnClick();
        }

        return chordSelectFragment;
    }

    //region UI event handler
    private class OnChordButtonPress implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            Button b = ((Button)view);
            mChordName = b.getText().toString();

            deactivateButtons(mChordButtons);
            b.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorButtonActive));
            updateChord();
        }
    }

    private class OnHarmonyButtonPress implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            Button b = ((Button) view);
            mHarmName = b.getText().toString();
            deactivateButtons(mHarmonyButtons);
            b.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorButtonActive));
            mModifier = Chord.Modifier.None;
            //region This if else findes the chord and makes it stabil if internationalized
            if (mHarmName.equals(getString(R.string._7th)))
            {
                mHarmony = Chord.Base.Major;
                mModifier = Chord.Modifier.Seven;
            }
            else if (mHarmName.equals(getString(R.string.minor)))
            {
                mHarmony = Chord.Base.Minor;
            }
            else if (mHarmName.equals(getString(R.string.minor7)))
            {
                mHarmony = Chord.Base.Minor;
                mModifier = Chord.Modifier.Seven;
            }
            else if (mHarmName.equals(getString(R.string.dim)))
            {
                mHarmony = Chord.Base.Dim;
            }
            else if (mHarmName.equals(getString(R.string.aug)))
            {
                mHarmony = Chord.Base.Aug;
            }
            else if (mHarmName.equals(getString(R.string._6th)))
            {
                mHarmony = Chord.Base.Major;
                mModifier = Chord.Modifier.Sixth;
            }
            else if (mHarmName.equals(getString(R.string.major7)))
            {
                mHarmony = Chord.Base.Major;
                mModifier = Chord.Modifier.Major;
            }
            else if (mHarmName.equals(getString(R.string._9th)))
            {
                mHarmony = Chord.Base.Major;
                mModifier = Chord.Modifier.Nine;
            }
            else // if(mHarmName.equals(getString(R.string.major)))
            {
                mHarmony = Chord.Base.Major;
                mHarmName = "";
            }
            //endregion
            updateChord();
        }
    }

    private class OnUpDownButtonPress implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            Button b = ((Button)view);
            String cName = "";
            if(b.equals(mButtonUp))
            {
                mSelectListeners.onUpPressed();
                cName = mOurCurrentChord.get(0).toString();
            }
            else if (b.equals(mButtonDown))
            {
                mSelectListeners.onDownPressed();
                cName = mOurCurrentChord.get(0).toStringFlat();
            }
            mSelectedChordView.setText(cName + mHarmName);
        }
    }

    private class OnHarmMenuButtonPress implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            hideButtons(mChordButtons, true);
            mChordsVisible = false;
            if (!mHarmVisible)
            {
                showButtons(mHarmonyButtons, false);
                mHarmVisible = true;
                mSelectedChordView.clearAnimation();
                mSelectedChordView.setVisibility(View.INVISIBLE);
            }
            else
            {
                hideButtons(mHarmonyButtons, false);
                mHarmVisible = false;
                mSelectedChordView.startAnimation(mAnimOpen);
            }
        }
    }

    private class OnChordMenuButtonPress implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            hideButtons(mHarmonyButtons, true);
            mHarmVisible = false;
            if (!mChordsVisible)
            {
                showButtons(mChordButtons, false);
                mChordsVisible = true;
                mSelectedChordView.clearAnimation();
                mSelectedChordView.setVisibility(View.INVISIBLE);
            }
            else
            {
                hideButtons(mChordButtons, false);
                mChordsVisible = false;
                mSelectedChordView.startAnimation(mAnimOpen);
            }
        }
    }
    //endregion

    //region Private methods
    private void deactivateButtons(List<Button> buttons)
    {
        for (Button b : buttons)
        {
            b.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorButton));
        }
    }

    private void showButtons(List<Button> buttons, boolean fast)
    {
        for (Button b : buttons)
        {
            if (fast)
            {
                b.clearAnimation();
                b.setVisibility(View.VISIBLE);
            }
            else
            {
                b.startAnimation(mAnimOpen);
            }
        }
    }

    private void hideButtons(List<Button> buttons, boolean fast)
    {
        for (Button b : buttons)
        {
            if (fast)
            {
                b.clearAnimation();
                b.setVisibility(View.INVISIBLE);
            }
            else
            {
                b.startAnimation(mAnimClose);
            }
        }
    }

    private List<Button> getAllButtons(ViewGroup v)
    {
        List<Button> buttons = new ArrayList();

        for (int i = 0; i < v.getChildCount(); i++)
        {
            View child = v.getChildAt(i);
            if(child instanceof ViewGroup)
            {
                buttons.addAll(getAllButtons((ViewGroup) child));
            }
            else if(child instanceof Button)
            {
                buttons.add((Button) child);
            }
        }
        return buttons;
    }

    private void updateChord()
    {
        mOurCurrentChord = new Chord(mHarmony, new Note(mChordName), mModifier);
        mSelectedChordView.setText(mChordName + mHarmName);
        mSelectListeners.onChordSelected(mOurCurrentChord);
    }
    //endregion

    //region Listener attach
    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    protected void onAttachToContext(Context context)
    {

        if (context instanceof Activity){
            Activity activity = (Activity)context;
            try {
                mSelectListeners = (OnChordSelectListener)activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " must implement OnHeadlineSelectedListener");
            }
        }
    }
    //endregion

}
