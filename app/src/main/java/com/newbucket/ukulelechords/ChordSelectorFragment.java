package com.newbucket.ukulelechords;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.newbucket.musiclib.Chord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benni on 25.11.2016.
 */

public class ChordSelectorFragment extends Fragment
{
    private OnChordSelectListener mSelectListeners;
    private final String TAG = "ChordSelector";

    private List<Button> mButtons;
    private Button mButtonUp;
    private Button mButtonDown;

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

        mButtonUp = (Button)chordSelectFragment.findViewById(R.id.button_up);
        mButtonDown = (Button)chordSelectFragment.findViewById(R.id.button_down);

        mButtons = getAllButtons(chordMap);

        for(Button b : mButtons)
        {
            b.setOnClickListener(new OnChordButtonPress());
        }

        mButtonUp.setOnClickListener(new OnUpDownButtonPress());
        mButtonDown.setOnClickListener(new OnUpDownButtonPress());

        mButtons.get(0).callOnClick();

        return chordSelectFragment;
    }


    private class OnChordButtonPress implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            Button b = ((Button)view);
            String chordName = b.getText().toString();
            deactivateButtons();
            b.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorButtonActive));
            mSelectListeners.onChordSelected(new Chord(chordName));
        }
    }

    private class OnUpDownButtonPress implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            Button b = ((Button)view);
            if(b.equals(mButtonUp))
            {
                mSelectListeners.onUpPressed();
            }
            if(b.equals(mButtonUp))
            {
                mSelectListeners.onDownPressed();
            }
        }
    }

    private void deactivateButtons()
    {
        for(Button b : mButtons)
        {
            b.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorButton));
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
