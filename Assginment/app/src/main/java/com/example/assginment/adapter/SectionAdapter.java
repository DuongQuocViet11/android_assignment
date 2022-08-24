package com.example.assginment.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assginment.R;
import com.example.assginment.model.Section;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Section> listSections;
    MovieAdapter adapter;

    public SectionAdapter(Activity activity, List<Section> listSections) {
        this.activity = activity;
        this.listSections = listSections;
    }

    public SectionAdapter() {

    }

    @SuppressLint("NotifyDataSetChanged")
    public void reloadSection(List<Section> list){
        listSections = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_section, parent, false);
        SectionHolder holder = new SectionHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SectionHolder vh = (SectionHolder) holder;
        Section data = listSections.get(position);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);

        MovieAdapter adapter = new MovieAdapter(activity, data.getListMovie());
        adapter.setSection(data.getTitle());

        vh.rvSection.setLayoutManager(layoutManager);
        vh.rvSection.setHasFixedSize(true);
        vh.rvSection.setAdapter(adapter);
        vh.tvTitle.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return listSections.size();
    }

    public class SectionHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        RecyclerView rvSection;
        public SectionHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            rvSection = itemView.findViewById(R.id.rvSection);
        }
    }
}
