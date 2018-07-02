package com.travis.movie.adapter;

/**
 * Created by LENOVO on 28-06-2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.travis.movie.R;

/**
 * Created by JUNED on 6/10/2016.
 */
public class Genre extends RecyclerView.Adapter<Genre.ViewHolder>{

    String[] SubjectValues;
    Context context;
    View view1;
    ViewHolder viewHolder1;
//    TextView textView;

    public Genre(Context context1,String[] SubjectValues1){

        SubjectValues = SubjectValues1;
        context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public ViewHolder(View v){

            super(v);

            textView = (TextView)v.findViewById(R.id.genre_details);
        }
    }

    @Override
    public Genre.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        view1 = LayoutInflater.from(context).inflate(R.layout.genre_items,parent,false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        holder.textView.setText(SubjectValues[position]);
    }
    @Override
    public int getItemCount(){

        return SubjectValues.length;
    }
}
