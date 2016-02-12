package com.example.williamtygret.pokedex;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by williamtygret on 2/11/16.
 */
public class myAdapter extends CursorAdapter {

    Context mContext;
    public myAdapter(Context context, Cursor c) {
        super(context,c,0);
        mContext=context;
    }

    //customer adapter to give my list view a custom font
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Pokemon GB.ttf");
        TextView tv = (TextView)view.findViewById(R.id.poke_name_textview);

        String s = cursor.getString(cursor.getColumnIndex(PokemonDatabaseHelper.COL_POKEMON_NAME));
        tv.setText(s);
        tv.setTextSize(18);
        tv.setTypeface(tf);

        //Any other modifications you want
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.custom_adapter,parent,false);
    }

}
