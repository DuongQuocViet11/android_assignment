package com.example.assginment.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assginment.R;
import com.example.assginment.event.MessageEvent;
import com.example.assginment.model.Movie;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Movie> listMovies;
    private String section;

    public MovieAdapter(Activity activity, List<Movie> listMovies) {
        this.activity = activity;
        this.listMovies = listMovies;
    }

    public MovieAdapter() {

    }

    @SuppressLint("NotifyDataSetChanged")
    public void reloadData(List<Movie> list){
        listMovies = list;
        notifyDataSetChanged();
    }

    public void setSection(String section) {
        this.section = section;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_movie, parent, false);
        if (section.equalsIgnoreCase("Hot") || section.equalsIgnoreCase("Upcoming")) {
            itemView = activity.getLayoutInflater().inflate(R.layout.item_movie_big_size, parent,false);
        }
        MovieHolder holder = new MovieHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieHolder vh = (MovieHolder) holder;
        Movie data = listMovies.get(position);
        Glide.with(activity).load(data.getThumbnail()).into(vh.ivCover);
        vh.tvName.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivCover;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivCover = itemView.findViewById(R.id.ivCover);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("TAG", "onClick: " + getAdapterPosition());
                    Movie movie = listMovies.get(getAdapterPosition());
                    EventBus.getDefault().post(new MessageEvent.MovieEvent(movie));
                }
            });
        }
    }
}
