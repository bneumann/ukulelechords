package com.newbucket.ukulelechords;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by benni on 21.11.2016.
 */

public class MenuDrawerAdapter extends ArrayAdapter<DrawerItem>
{
    public MenuDrawerAdapter(Context context, int resource, String[] strings)
    {
        super(context, resource);
        for (String s : strings)
        {
            this.add(new DrawerItem(s));
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.menu_list_view, null);
        }

        DrawerItem di = this.getItem(position);

        TextView tv = (TextView) v.findViewById(R.id.MenuText1);
        if(tv != null) {
            tv.setText(di.getItemName());
        }

        return v;
    }
}
