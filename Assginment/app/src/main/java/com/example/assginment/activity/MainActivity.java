package com.example.assginment.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.assginment.R;
import com.example.assginment.adapter.MovieAdapter;
import com.example.assginment.adapter.SectionAdapter;
import com.example.assginment.event.MessageEvent;
import com.example.assginment.model.BigData;
import com.example.assginment.model.Data;
import com.example.assginment.model.Movie;
import com.example.assginment.model.Section;
import com.example.assginment.network.ApiService;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5};
    List<Section> listSection = new ArrayList<>();
    SectionAdapter sectionAdapter = new SectionAdapter();
    MovieAdapter movieAdapter = new MovieAdapter();
    List<List<Movie>> listTitle = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBanner();
        initView();
    }

    private void initBanner(){
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        });
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Log.d("TAG", "onClick: " + position);
            }
        });
    }

    private void initView(){
        RecyclerView rvHome = findViewById(R.id.rvHome);
        callApiGetData();

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        sectionAdapter = new SectionAdapter(this, listSection);

        rvHome.setLayoutManager(layoutManager);
        rvHome.setAdapter(sectionAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void callApiGetData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.SEVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.apiGetBigData().enqueue(new Callback<BigData>() {
            @Override
            public void onResponse(Call<BigData> call, Response<BigData> response) {
                BigData bigData = response.body();
                Data data = bigData.getData();
                listTitle.add(data.getTrending());
                listTitle.add(data.getHot());
                listTitle.add(data.getPopular());
                listTitle.add(data.getUpcoming());

                movieAdapter.reloadData(listTitle.get(0));
                movieAdapter.reloadData(listTitle.get(1));
                movieAdapter.reloadData(listTitle.get(2));
                movieAdapter.reloadData(listTitle.get(3));

                listSection.add(new Section("Trending", listTitle.get(0)));
                listSection.add(new Section("Hot", listTitle.get(1)));
                listSection.add(new Section("Popular", listTitle.get(2)));
                listSection.add(new Section("Upcoming", listTitle.get(3)));
                sectionAdapter.reloadSection(listSection);
            }

            @Override
            public void onFailure(Call<BigData> call, Throwable t) {

            }
        });
    }


    private void goToDetail(Movie movie){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("MOVIE", movie);
        startActivity(intent);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.MovieEvent movieEvent){
        Movie movie = movieEvent.getMovie();
        Log.d("TAG", "onMessageEvent: " + movie.getName());
        goToDetail(movie);
    }
}