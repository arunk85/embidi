package com.mm.n_deb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by arun on 13/12/17.
 */

public class BatchAdapter extends BaseAdapter {

    private final Context cxt;
    private final String[] items;

    public BatchAdapter(Context cx, String[] items){
        this.cxt = cx;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final String item = items[i];
        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(cxt);
            view = layoutInflater.inflate(R.layout.batch_grid_item, null);
        }
        final TextView nameTextView = view.findViewById(R.id.textView2);
        nameTextView.setText(item);
        return view;
    }
}
