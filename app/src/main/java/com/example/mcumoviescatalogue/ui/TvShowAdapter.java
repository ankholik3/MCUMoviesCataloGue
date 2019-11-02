package com.example.mcumoviescatalogue.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mcumoviescatalogue.R;
import com.example.mcumoviescatalogue.model.TvShow;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ListViewHolder> {
    private Context context;
    private ArrayList<TvShow> tvShows;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setTvShows(ArrayList<TvShow> tvShows) {
        this.tvShows = tvShows;
        notifyDataSetChanged();
    }

    public TvShowAdapter(Context context) {
        this.context = context;
        tvShows = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tv_show, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        final TvShow tvShow = tvShows.get(position);
        Log.d("TVShow", "data " + tvShow.toString());
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w185" + tvShow.getPoster_path())
                .placeholder(R.drawable.no_image)
                .into(holder.imgTvPoster);
        holder.txtTvTitle.setText(tvShow.getName());
        holder.txtTvDescription.setText(tvShow.getOverview());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(tvShows.get(position));
            }
        });
    }


    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTvTitle;
        private TextView txtTvDescription;
        private ImageView imgTvPoster;
        private TextView txtTvReleaseDate;
        private TextView txtTvSynopsis;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTvTitle = itemView.findViewById(R.id.txt_title_tv_show);
            txtTvDescription = itemView.findViewById(R.id.txt_description_tv_show);
            imgTvPoster = itemView.findViewById(R.id.img_poster_tv_show);
            //txtReleaseDate = view.findViewById(R.id.txt_release_date);
            //txtSynopsis = view.findViewById(R.id.txt_synopsis);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(TvShow data);
    }


}
