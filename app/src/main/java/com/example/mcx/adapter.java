package com.example.mcx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {

    private List<data> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, target1,target2,target3,sl;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            target1 = (TextView) view.findViewById(R.id.target1);
            target2 = (TextView) view.findViewById(R.id.target2);
            target3 = (TextView) view.findViewById(R.id.target3);
            sl = (TextView) view.findViewById(R.id.sl);
//                low= (TextView) view.findViewById(R.id.low);
//                year = (TextView) view.findViewById(R.id.year);
        }
    }


    public adapter(List<data> moviesList) {
        this.list = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        data movie = list.get(position);
        holder.title.setText(movie.getTitle());
        holder.target1.setText(movie.getTarget1());
        holder.target2.setText(movie.getTarget2());
        holder.target3.setText(movie.getTareget3());
        holder.sl.setText(movie.getSl());
//            holder.low.setText(movie.getLow());
//            holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}