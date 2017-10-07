package com.omsuperg.quickhelp.models;


import java.util.Date;

public class PeticionModel {

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
}
