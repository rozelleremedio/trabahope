package com.example.trabahopeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class CategoryGridAdapter extends BaseAdapter{

    Context context;
    ArrayList<MyCategory> list;
    LayoutInflater inflater;


    public CategoryGridAdapter(Context context, ArrayList<MyCategory> list) {
        this.context = context;
        this.list = list;
        this.inflater =LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemHandler handler = null;
        if (convertView==null) {

            convertView = inflater.inflate(R.layout.category_item_layout, null);
            handler = new ItemHandler();
            handler.catImage = (ImageView) convertView.findViewById(R.id.iconimage);
            handler.catName = (TextView) convertView.findViewById(R.id.textdata);
            convertView.setTag(handler);

        }
        else handler=(ItemHandler)convertView.getTag();
        handler.catImage.setImageResource(list.get(position).getCatImage());
        handler.catName.setText(list.get(position).getCatName());


        return convertView;
    }



    static class ItemHandler{

        ImageView catImage;
        TextView catName;



    }







}
