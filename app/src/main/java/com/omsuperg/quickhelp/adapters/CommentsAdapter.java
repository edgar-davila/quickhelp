package com.omsuperg.quickhelp.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omsuperg.quickhelp.R;
import com.omsuperg.quickhelp.models.CommentModel;

import java.util.List;

/**
 * Created by Edgar on 10/7/2017.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private final CommentsListener commentsListener;
    private Context context;
    private List<CommentModel> commentModels;


    public CommentsAdapter(Context context, CommentsListener commentsListener, List<CommentModel> commentModels) {
        this.context = context;
        this.commentsListener = commentsListener;
        this.commentModels = commentModels;
    }

    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_card_comment, parent, false);
        ViewHolder vh = new ViewHolder(v, commentsListener);
        return vh;
    }

    public void setItems(List<CommentModel> commentModels) {
        this.commentModels = commentModels;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommentModel commentModel = commentModels.get(position);
        holder.commentModel = commentModel;
        holder.textviewTituloPeticion.setText(commentModel.getUserId());
        holder.textviewCardDescripcion.setText(commentModel.getComment());
    }

    @Override
    public int getItemCount() {
        return commentModels.size();
    }

    public interface CommentsListener {
        void onClick(CommentModel commentModel);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textviewTituloPeticion;
        TextView textviewCategoriaPeticion;
        TextView textviewCardDescripcion;
        ImageView imageViewCardProfilePeticion;
        CommentModel commentModel;
        CommentsListener commentsListener;


        ViewHolder(View v, CommentsListener commentsListener) {
            super(v);
            this.cardView = v.findViewById(R.id.card_view);
            this.commentsListener = commentsListener;
            this.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewHolder.this.commentsListener.onClick(commentModel);
                }
            });
            this.textviewTituloPeticion = v.findViewById(R.id.card_titulo_peticion);
            this.textviewCardDescripcion = v.findViewById(R.id.card_descripcion);
            this.imageViewCardProfilePeticion = v.findViewById(R.id.imageViewCardProfilePeticion);
        }


    }
}