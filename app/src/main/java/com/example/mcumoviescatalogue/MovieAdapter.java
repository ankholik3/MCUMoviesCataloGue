package com.example.mcumoviescatalogue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ListViewHolder> {
    private Context context;
    private ArrayList<Movie> movies;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        final Movie movi = movies.get(position);
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w185"+movi.getPoster_path())
                .placeholder(R.drawable.no_image)
                .into(holder.imgPoster);
        holder.txtMovieTitle.setText(movi.getName());
        holder.txtDescription.setText(movi.getDescriptionFromAPI());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(movies.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
    /*
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
            }
            ViewHolder viewHolder = new ViewHolder(view);
            Movie movie = (Movie) getItem(i);
            viewHolder.bind(movie);
            return view;
        }
    */
/*

    private class ViewHolder {
        private TextView txtMovieTitle;
        private TextView txtDescription;
        private ImageView imgPoster;
        private TextView txtReleaseDate;
        private TextView txtSynopsis;

        ViewHolder(View view) {
            txtMovieTitle = view.findViewById(R.id.txt_movie_title);
            txtDescription = view.findViewById(R.id.txt_description);
            imgPoster = view.findViewById(R.id.img_poster);
            //txtReleaseDate = view.findViewById(R.id.txt_release_date);
            //txtSynopsis = view.findViewById(R.id.txt_synopsis);
        }

        public void bind(Movie movie) {
            txtMovieTitle.setText(movie.getMovieTitle());
            txtDescription.setText(movie.getDescription());
            imgPoster.setImageResource(movie.getImgPoster());
           //txtReleaseDate.setText(movie.getReleaseDate());
            //txtSynopsis.setText(movie.getSynopsis());
        }
    }
*/
    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMovieTitle;
        private TextView txtDescription;
        private ImageView imgPoster;
        private TextView txtReleaseDate;
        private TextView txtSynopsis;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMovieTitle = itemView.findViewById(R.id.txt_movie_title);
            txtDescription = itemView.findViewById(R.id.txt_description);
            imgPoster = itemView.findViewById(R.id.img_poster);
            //txtReleaseDate = view.findViewById(R.id.txt_release_date);
            //txtSynopsis = view.findViewById(R.id.txt_synopsis);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Movie data);
    }
}
