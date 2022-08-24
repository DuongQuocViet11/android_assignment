package com.example.assginment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assginment.R;
import com.example.assginment.model.Movie;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Movie movie = (Movie) getIntent().getSerializableExtra("MOVIE");
        TextView tvTitle = findViewById(R.id.tvTitle);
        ImageView ivCover = findViewById(R.id.ivCover);
        TextView tvDes = findViewById(R.id.tvDes);
        TextView tvDir = findViewById(R.id.tvDir);
        tvDes.setText(movie.getDescription());
        tvDir.setText(movie.getDirector().getName());
        tvTitle.setText(movie.getName());
        Glide.with(this).load(movie.getThumbnail()).into(ivCover);
    }
}