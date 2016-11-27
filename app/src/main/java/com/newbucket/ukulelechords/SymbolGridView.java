package com.newbucket.ukulelechords;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.newbucket.musiclib.Chord;

import org.w3c.dom.Text;


/**
 * Created by benni on 25.11.2016.
 */

public class SymbolGridView extends GridView
{
    private final String TAG = "TEST";

    public SymbolGridView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

         this.setAdapter(new ImageAdapter(getContext()));

        this.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Log.d(TAG, "Test");
            }
        });
    }

    public class ImageAdapter extends BaseAdapter
    {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return 6;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if (convertView == null) {
                textView = new TextView(mContext);
                textView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorButtonActive));
                textView.setText(String.format("%d", position));
                textView.setLayoutParams(new GridView.LayoutParams(85, 85));
                textView.setPadding(2, 2, 2, 2);
                // Set the TextView font and font style
                textView.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
                textView.setTextColor(Color.WHITE);
                textView.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            } else
            {
                textView = (TextView) convertView;
            }
            return textView;
        }

    }
}
