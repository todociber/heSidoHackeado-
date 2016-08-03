package com.todociber.hesidohackeado;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class ResultadosAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private final Context context;
    Cursor cursor;


    public ResultadosAdapter(Context context, Cursor cursor) {


        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (convertView == null) {
            view = inflater.inflate(R.layout.vista_resultado, null);
            holder = new ViewHolder();
            holder.text = (TextView) view.findViewById(R.id.TITULORESULTADO);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        cursor.moveToPosition(position);
        holder.text.setText(cursor.getString(2).toUpperCase());

        return view;
    }

    private class ViewHolder {
        public TextView text;

    }
}
