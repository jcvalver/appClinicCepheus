package com.cibertec.cliniccepheusapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>{

    private String[] captions;
    private int[] imageIds;
    private static Listener listener;
    private final Context mContext;

    interface Listener {
        void onClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //private CardView cardView;
        public final ImageView imageView;
        public final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.info_image);
            textView = itemView.findViewById(R.id.info_text);
            //cardView = v;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            int mPosition = getLayoutPosition();

            if (listener != null) {
                listener.onClick(mPosition);
            }
        }
    }

    public CaptionedImagesAdapter(Context context, String[] captions, int[] imageIds) {
        this.captions = captions;
        this.imageIds = imageIds;
        this.mContext=context;
    }

    @Override
    public int getItemCount() {
        return captions.length;
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cv = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){

        Drawable drawable = ContextCompat.getDrawable(mContext,
                imageIds[position]);
        holder.imageView.setImageDrawable(drawable);
        holder.imageView.setContentDescription(captions[position]);
        holder.textView.setText(captions[position]);
    }
}

