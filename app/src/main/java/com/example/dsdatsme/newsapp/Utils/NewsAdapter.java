package com.example.dsdatsme.newsapp.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dsdatsme.newsapp.R;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsStructure> {

    public NewsAdapter(@NonNull Context context, List<NewsStructure> list) {
        super(context, 0,list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        NewsStructure currentNews = getItem(position);

        TextView  section, date, title;

        TextView type =  listItemView.findViewById(R.id.news_type);
        type.setText(currentNews.getNewsType());

        section = listItemView.findViewById(R.id.news_section);
        section.setText(currentNews.getNewsSection());

        date = listItemView.findViewById(R.id.news_date);
        date.setText(currentNews.getNewsDate());

        title = listItemView.findViewById(R.id.news_title);
        title.setText(currentNews.getNewsTitle());
        return listItemView;
    }
}
