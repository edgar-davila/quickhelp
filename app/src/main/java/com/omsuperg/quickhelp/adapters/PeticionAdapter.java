package com.omsuperg.quickhelp.adapters;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omsuperg.quickhelp.MainActivity;
import com.omsuperg.quickhelp.R;
import com.omsuperg.quickhelp.models.PeticionModel;
import com.omsuperg.quickhelp.transforms.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PeticionAdapter extends RecyclerView.Adapter<PeticionAdapter.ViewHolder> {
    private Context context;
    private List<PeticionModel> peticionModels;
    private final PeticionListener peticionListener;


    public PeticionAdapter(Context context, PeticionListener peticionListener, List<PeticionModel> peticionModels) {
        this.context = context;
        this.peticionListener = peticionListener;
        this.peticionModels = peticionModels;
    }

    @Override
    public PeticionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_card_peticion, parent, false);
        ViewHolder vh = new ViewHolder(v, peticionListener);
        return vh;
    }

    public void add(PeticionModel peticionModel){
        this.peticionModels.add(0, peticionModel);
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PeticionModel peticionModel = peticionModels.get(position);
        holder.peticionModel = peticionModel;
        holder.textviewTituloPeticion.setText(peticionModel.getTitulo());
        holder.textviewCardDescripcion.setText(peticionModel.getDescripcion());
        Picasso.with(PeticionAdapter.this.context)
                .load("https://graph.facebook.com/" + peticionModel.getFacebookProfileId() + "/picture?type=large")
                .transform(new CircleTransform())
                .into(holder.imageViewCardProfilePeticion);
    }

    @Override
    public int getItemCount() {
        return peticionModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textviewTituloPeticion;
        TextView textviewCategoriaPeticion;
        TextView textviewCardDescripcion;
        ImageView imageViewCardProfilePeticion;
        PeticionModel peticionModel;
        PeticionListener peticionListener;


        ViewHolder(View v, PeticionListener peticionListener) {
            super(v);
            this.cardView = v.findViewById(R.id.card_view);
            this.peticionListener = peticionListener;
            this.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewHolder.this.peticionListener.onClick(peticionModel);
                }
            });
            this.textviewTituloPeticion = v.findViewById(R.id.card_titulo_peticion);
            this.textviewCardDescripcion = v.findViewById(R.id.card_descripcion);
            this.imageViewCardProfilePeticion = v.findViewById(R.id.imageViewCardProfilePeticion);
        }


    }

    public interface PeticionListener {
        void onClick(PeticionModel peticionModel);
    }
}

