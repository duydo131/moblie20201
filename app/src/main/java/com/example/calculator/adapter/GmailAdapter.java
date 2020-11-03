package com.example.calculator.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calculator.R;
import com.example.calculator.model.GmailModel;

import java.text.SimpleDateFormat;
import java.util.List;

public class GmailAdapter extends BaseAdapter {
    List<GmailModel> list;
    Context context;

    public GmailAdapter(List<GmailModel> list, Context context) {
        this.list = list;
        this.context = context;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gmail, parent, false);
        }

        TextView avatar = convertView.findViewById(R.id.radius_view);
        TextView textUsername = convertView.findViewById(R.id.text_username);
        TextView textMessage = convertView.findViewById(R.id.text_message);
        TextView time = convertView.findViewById(R.id.time_message);
        TextView favorite = convertView.findViewById(R.id.favorite);

        GmailModel item = (GmailModel) getItem(position);
        SimpleDateFormat f = new SimpleDateFormat("hh : mm ");

        avatar.setText(item.getUsername().charAt(0) + "");
        textUsername.setText(item.getUsername());
        textMessage.setText(item.getMessage());
        time.setText(f.format(item.getDate()));
        if(item.isFavorite()){
            favorite.setBackground(Drawable.createFromPath("@drawable/ic_baseline_star_24"));
        }

        return convertView;
    }
}
