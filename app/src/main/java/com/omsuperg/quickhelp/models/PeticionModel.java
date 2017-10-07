package com.omsuperg.quickhelp.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class PeticionModel implements Parcelable {

    private final int id;
    private final String facebookProfileId;
    private final String titulo;
    private final String categoria;
    private final String descripcion;
    private final int statusModel;
    private final int cantidadPersonas;
    private final Date createdAt;
    // TODO: 7/10/17 añadir latlong


    public PeticionModel(int id, String facebookProfileId, String titulo, String categoria, String descripcion, int statusModel, int cantidadPersonas, Date createdAt) {
        this.id = id;
        this.facebookProfileId = facebookProfileId;
        this.titulo = titulo;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.statusModel = statusModel;
        this.cantidadPersonas = cantidadPersonas;
        this.createdAt = createdAt;
    }

    protected PeticionModel(Parcel in) {
        id = in.readInt();
        facebookProfileId = in.readString();
        titulo = in.readString();
        categoria = in.readString();
        descripcion = in.readString();
        statusModel = in.readInt();
        cantidadPersonas = in.readInt();
        createdAt = (java.util.Date) in.readSerializable();
    }

    public static final Creator<PeticionModel> CREATOR = new Creator<PeticionModel>() {
        @Override
        public PeticionModel createFromParcel(Parcel in) {
            return new PeticionModel(in);
        }

        @Override
        public PeticionModel[] newArray(int size) {
            return new PeticionModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getStatusModel() {
        return statusModel;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getFacebookProfileId() {
        return facebookProfileId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(facebookProfileId);
        parcel.writeString(titulo);
        parcel.writeString(categoria);
        parcel.writeString(descripcion);
        parcel.writeInt(statusModel);
        parcel.writeInt(cantidadPersonas);
        parcel.writeSerializable(createdAt);
    }
}
