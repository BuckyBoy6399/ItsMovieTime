package com.example.rajraval.itsmovietime;

import io.realm.Realm;
import io.realm.RealmObject;

public class MovieModel extends RealmObject {
    String namea,imagea;

    public MovieModel() {
    }

    public MovieModel(String namea, String imagea) {
    }

    public String getName() {
        return namea;
    }

    public void setName(String name) {
        this.namea = namea;
    }

    public String getImage() {
        return imagea;
    }

    public void setImage(String image) {
        this.imagea = imagea;
    }

}
