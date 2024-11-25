package com.example.albums_b;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private final List<Album> albums;

    public AlbumAdapter(List<Album> albums) {
        this.albums = albums;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albums.get(position);
        holder.albumId.setText("ID: " + album.getId());
        holder.userId.setText("User ID: " + album.getUserId());
        holder.albumTitle.setText("Album Title: " + album.getTitle());
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    static class AlbumViewHolder extends RecyclerView.ViewHolder {
        TextView albumId, userId, albumTitle;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            albumId = itemView.findViewById(R.id.albumId);
            userId = itemView.findViewById(R.id.userId);
            albumTitle = itemView.findViewById(R.id.albumTitle);
        }
    }
}
