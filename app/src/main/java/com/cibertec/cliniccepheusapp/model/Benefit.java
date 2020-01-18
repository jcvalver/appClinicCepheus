package com.cibertec.cliniccepheusapp.model;

import com.cibertec.cliniccepheusapp.R;

public class Benefit {
    private String name;
    private int imageResourceId;

    public static final Benefit[] benefits = {
            new Benefit("Diavolo", R.drawable.images1),
            new Benefit("Funghi", R.drawable.images2),
            new Benefit("Diavolo2", R.drawable.images3),
            new Benefit("Funghi2", R.drawable.images4)
    };

    private Benefit(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }
    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
