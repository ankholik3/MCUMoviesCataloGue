package com.example.mcumoviescatalogue;

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
import com.bumptech.glide.request.RequestOptions;

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
    }

    public TvShowAdapter(Context context) {
        this.context = context;
        tvShows = new ArrayList<>();
    }
    @Override
    public int getItemCount () {
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
        Log.d("TVShow","data "+tvShow.toString());
        Glide.with(holder.itemView.getContext())
                .load(tvShow.getImgTvShowPoster())
                .apply(new RequestOptions())
                .into(holder.imgTvPoster);
        holder.txtTvTitle.setText(tvShow.getTvShowTitle());
        holder.txtTvDescription.setText(tvShow.getTvShowDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(tvShows.get(position));
            }
        });
    }

   /* @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return 0;
    }*/
/*
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_tv_show, viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        TvShow tvShow = (TvShow) getItem(i);
        viewHolder.bind(tvShow);
        return view;
    }

    private class ViewHolder {
        private TextView txtMovieTitleTvShow;
        private TextView txtDescriptionTvShow;
        private ImageView imgPosterTvShow;
        private TextView txtReleaseDateTvShow;
        private TextView txtSynopsisTvShow;

        ViewHolder(View view) {
            txtMovieTitleTvShow = view.findViewById(R.id.txt_title_tv_show);
            txtDescriptionTvShow = view.findViewById(R.id.txt_description_tv_show);
            imgPosterTvShow = view.findViewById(R.id.img_poster_tv_show);
            //txtReleaseDateTvShow = view.findViewById(R.id.txt_release_date);
            //txtSynopsisTvShow = view.findViewById(R.id.txt_synopsis);
        }

        public void bind(TvShow tvShow) {
            txtMovieTitleTvShow.setText(tvShow.getTvShowTitle());
            txtDescriptionTvShow.setText(tvShow.getTvShowDescription());
            imgPosterTvShow.setImageResource(tvShow.getImgTvShowPoster());
           //txtReleaseDate.setText(movie.getReleaseDate());
            //txtSynopsis.setText(movie.getSynopsis());
        }
    }
*/
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
