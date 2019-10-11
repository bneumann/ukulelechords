package com.newbucket.ukulelechords;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.menu_list_view, parent);
        }

        DrawerItem di = this.getItem(position);

        TextView tv = (TextView) v.findViewById(R.id.MenuText1);
        if(tv != null) {
            tv.setText(di.getItemName());
        }

        return v;
    }
}
