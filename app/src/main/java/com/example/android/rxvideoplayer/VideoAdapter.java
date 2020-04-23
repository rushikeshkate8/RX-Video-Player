package com.example.android.rxvideoplayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoHolder> {
    @NonNull
    private Context context;
    private ArrayList<File> videoArrayList;

    VideoAdapter(@NonNull Context context, ArrayList<File> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new VideoHolder(mview);
    }

    @Override
    // for showing the file name and thumbnail
    public void onBindViewHolder(@NonNull final VideoHolder holder, int position) {
         // for seting the file name
          holder.mFileName.setText(MainActivity.fileArrayList.get(position).getName());
          // for setting the thumbnail
        Bitmap bitmapThumbnail = ThumbnailUtils.createVideoThumbnail(videoArrayList.get(position).getPath(), MediaStore.Images.Thumbnails.MINI_KIND);
        holder.mVideoThumbnail.setImageBitmap(bitmapThumbnail);
       holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, videoPlayerActivity.class);
                intent.putExtra("position", holder.getAdapterPosition());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(videoArrayList.size() > 0)
            return videoArrayList.size();
        else
            return 1;
    }
}
class VideoHolder extends RecyclerView.ViewHolder{
    TextView mFileName;
    ImageView mVideoThumbnail;
    CardView mCardView;
    VideoHolder(View view)
    {
        super(view);
        mFileName = view.findViewById(R.id.video_file_name);
        mVideoThumbnail = view.findViewById(R.id.video_thumbnail);
        mCardView = view.findViewById(R.id.video_cardView);
    }
}
