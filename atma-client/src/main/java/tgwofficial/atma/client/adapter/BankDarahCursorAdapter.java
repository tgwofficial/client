package tgwofficial.atma.client.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import tgwofficial.atma.client.R;

public class BankDarahCursorAdapter extends CursorAdapter {
    public BankDarahCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.bankdarah_content_layout, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView spousename = (TextView) view.findViewById(R.id.gol_d);


        String nama = cursor.getString(cursor.getColumnIndexOrThrow("name"));

        
        name.setText("NAMA :"+nama);
    }
}