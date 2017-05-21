package com.example.android.notepad;

import android.content.Context;
import android.database.Cursor;
import android.nfc.TagLostException;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by zspmh on 2017-05-02.
 */

public class MySimpleCursorAdapter extends SimpleCursorAdapter {
    public MySimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    public MySimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        String text1 = cursor.getString(1);
        String name=String.format("题目:"+text1);
        if (text1 == null) {
            text1 = "";
        }
        View v1 = view.findViewById(android.R.id.text1);
        if (v1 instanceof TextView) {
            setViewText((TextView) v1, name);
        }

        String text2 = cursor.getString(2);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = Long.parseLong(text2);
        String dateString2 = format2.format(time);
        if (text2 == null) {
            text2 = "";
        }
        View v2 = view.findViewById(android.R.id.text2);
        if (v2 instanceof TextView) {
            setViewText((TextView) v2, dateString2);
        }
    }
}